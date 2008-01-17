/*
 * Copyright (c) 2007 Matthew R. Huska
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.net.URL;

class LoaderUI extends JPanel implements IView {

    CoreModel model;
    JPanel cards;
    
    JLabel titleLabel;

    JPanel required;
    JPanel optional;

    JFilePanel loadMSAPanel;
    JFilePanel secondaryPanel;
    JFilePanel loadUserDataPanel;

    JButton doneButton;

    public LoaderUI(CoreModel m, JPanel c) {
        assert m != null;
        model = m;

        assert c != null;
        cards = c;

        createWidgets();
        layoutView();
        registerControllers();

        model.addView(this);
    }

    private void createWidgets() {
        titleLabel = new JLabel("<html><b>Load BiasViz Input</b></html>");

        required = new JPanel();
        optional = new JPanel();

        loadMSAPanel = new JFilePanel("Load multiple sequence alignment:");
        secondaryPanel = new JFilePanel("Load JPred secondary structure:");
        loadUserDataPanel = new JFilePanel("Load raw values for each amino acid:");

        doneButton = new JButton("Done");
    }

    private void layoutView() {
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        required.setLayout(new BoxLayout(required, BoxLayout.Y_AXIS));
        required.setBorder(BorderFactory.createTitledBorder("Required"));
        required.add(loadMSAPanel);

        optional.setLayout(new BoxLayout(optional, BoxLayout.Y_AXIS));
        optional.setBorder(BorderFactory.createTitledBorder("Optional"));
        optional.add(secondaryPanel);
        optional.add(Box.createVerticalStrut(12));
        optional.add(loadUserDataPanel);

        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        required.setAlignmentX(Component.LEFT_ALIGNMENT);
        optional.setAlignmentX(Component.LEFT_ALIGNMENT);
        doneButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(titleLabel);
        this.add(Box.createVerticalStrut(12));
        this.add(required);
        this.add(Box.createVerticalStrut(12));
        this.add(optional);
        this.add(Box.createVerticalStrut(12));
        this.add(doneButton);

        // Used to push everything to the top. Vertical glue wasn't working.
        Dimension minsize = new Dimension(1, 1);
        Dimension maxsize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
        this.add(new Box.Filler(minsize, maxsize, maxsize));
    }

    public void updateView() {
    }

    private void registerControllers() {

        this.loadMSAPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                JFilePanel p = LoaderUI.this.loadMSAPanel;
                File file = p.getSelectedFile();

                StringBuffer buf = new StringBuffer();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = new String();
                    while ((line = in.readLine()) != null) {
                        buf.append(line + "\n");
                    }
                } catch (IOException e) {
                    p.setStatus("Parsing input failed.");
                }

                try {
                    CoreModel m = LoaderUI.this.model;
                    Alignment align = Parser.parseFasta(buf.toString());
                    m.setAlignment(align, buf.toString());
                    BaseTrack t = TrackFactory.getTrack("Amino Acid Composition", m);
                    m.addTrack(t);
                    p.setStatus("Loaded successfully.");
                } catch (Exception e) {
                    p.setStatus("Parsing input failed.");
                }
            }
        });

        this.loadUserDataPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                JFilePanel p = LoaderUI.this.loadUserDataPanel;
                File file = p.getSelectedFile();

                StringBuffer buf = new StringBuffer();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = new String();
                    while ((line = in.readLine()) != null) {
                        buf.append(line + "\n");
                    }
                } catch (IOException e) {
                    p.setStatus("Parsing input failed.");
                    System.err.println(e);
                    e.printStackTrace();
                }

                try {
                    CoreModel m = LoaderUI.this.model;
                    UserData ud = Parser.parseUserData(buf.toString(), 
                            m.getAlignment());
                    m.setUserData(ud);
                    BaseTrack t = TrackFactory.getTrack("Raw User Submitted Data", m);
                    m.addTrack(t);
                    p.setStatus("Loaded successfully.");
                } catch (Exception e) {
                    p.setStatus("Parsing input failed 2.");
                    System.err.println(e);
                    e.printStackTrace();
                }
            }
        });

        this.secondaryPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFilePanel p = LoaderUI.this.secondaryPanel;
                File file = p.getSelectedFile();

                StringBuffer buf = new StringBuffer();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = new String();
                    while ((line = in.readLine()) != null) {
                        buf.append(line + "\n");
                    }
                } catch (IOException e) {
                    p.setStatus("Parsing input failed.");
                }

                String secondary;

                try {
                    CoreModel m = LoaderUI.this.model;
                    secondary = Parser.parseJPred(buf.toString());
                    m.setSecondary(secondary);
                    BaseTrack t = TrackFactory.getTrack("Secondary Structure", m);
                    m.addTrack(t);
                    p.setStatus("Loaded successfully.");
                } catch (Exception e) {
                    p.setStatus("Parsing input failed 2.");
                    System.err.println(e);
                    e.printStackTrace();
                }
            }
        });

        this.doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(LoaderUI.this.cards.getLayout());
                cl.next(LoaderUI.this.cards);
            }
        });

    }

}
