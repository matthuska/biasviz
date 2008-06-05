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

class SaveUI extends JPanel implements IView {

    CoreModel model;
    JPanel cards;
    
    JLabel titleLabel;

    JPanel data;
    JPanel image;

    JButton exportCsvButton;
    //JFilePanel loadMSAPanel;
    //JFilePanel secondaryPanel;
    //JFilePanel loadUserDataPanel;

    //CurrentTracksUI currentTracks;

    JButton doneButton;

    public SaveUI(CoreModel m, JPanel c) {
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
        titleLabel = new JLabel("<html><b>Save BiasViz Output</b></html>");

        data = new JPanel();
        image = new JPanel();

        exportCsvButton = new JButton("Export Data as CSV File");

        doneButton = new JButton("<< Back to Graphical View");
    }

    private void layoutView() {
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
        data.setBorder(BorderFactory.createTitledBorder("Save Data"));
        data.add(exportCsvButton);

        image.setLayout(new BoxLayout(image, BoxLayout.Y_AXIS));
        image.setBorder(BorderFactory.createTitledBorder("Save Image"));
        //image.add(secondaryPanel);
        //image.add(Box.createVerticalStrut(12));
        //image.add(loadUserDataPanel);

        //titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //required.setAlignmentX(Component.LEFT_ALIGNMENT);
        data.setAlignmentX(Component.LEFT_ALIGNMENT);
        image.setAlignmentX(Component.LEFT_ALIGNMENT);
        doneButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(titleLabel);
        this.add(Box.createVerticalStrut(12));
        this.add(data);
        //this.add(Box.createVerticalStrut(12));
        //this.add(image);
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

        this.exportCsvButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();

                //FileFilter filter = new CsvFilenameFilter();
                //fc.setFileFilter(filter);

                fc.setSelectedFile(new File("biasviz-output.csv"));
                int retval = fc.showSaveDialog(SaveUI.this);

                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        if (file.exists()) {
                            int choice = JOptionPane.showConfirmDialog(
                                SaveUI.this, 
                                "The file \"" + file.getName() + 
                                "\" already exists in that location.\n" +
                                "Do you want to replace it with the one you are saving?", 
                                "Replace Existing File?", 
                                JOptionPane.YES_NO_OPTION);
                            if (choice != JOptionPane.YES_OPTION) {
                                return;
                            }
                        }
                        file.createNewFile();
                        BufferedWriter out = new BufferedWriter(new FileWriter(file));
                        out.write(model.getCSV());
                        out.close();
                    } catch (IOException io) {
                        System.err.println("Error writing file " + io.getMessage());
                    }
                }
            }
        });

        this.doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(SaveUI.this.cards.getLayout());
                cl.previous(SaveUI.this.cards);
            }
        });

    }

}
