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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

class CoreUI extends JPanel implements IView {
    
    public static final Color SCROLL_BACKGROUND = Color.WHITE;

    CoreModel model;
    JPanel cards;

    JScrollPane scroll;
    Rule aarule;
    JTabbedPane controls;

    JPanel topPanel;
    JButton loadFilesButton;
    ZoomControlView zoomControls;

    public CoreUI(CoreModel m, JPanel c) {
        assert m != null;
        model = m;

        assert c != null;
        cards = c;

        // Holds Zoom and Plot Type controls
        topPanel = new JPanel();

        // Layout main window with one column and as many rows as required
        scroll = new JScrollPane(new JPanel(new GridLayout(0, 1)));

        zoomControls = new ZoomControlView(model);
        loadFilesButton = new JButton("<< Change Input Files");

        aarule = new Rule(model, Rule.HORIZONTAL);
        controls = new JTabbedPane();

        layoutView();
        registerControllers();

        model.addView(this);
    }

    private void layoutView() {

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(Box.createHorizontalStrut(6));
        topPanel.add(loadFilesButton);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(zoomControls);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

        // Layout Graphical area with scroll window
        scroll.setColumnHeaderView(aarule);
        scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JPanel());
        this.setScrollBackground();
        this.add(scroll, BorderLayout.CENTER);

        this.add(controls, BorderLayout.SOUTH);

    }

    public void updateView() {

        List<BaseTrack> tracks = model.getTracks();

        //GridLayout layout = new GridLayout(0, 1);
        SpringLayout layout = new SpringLayout();

        //layout.setVgap(5);

        JPanel main = new JPanel(layout);
        JPanel labels = new JPanel(layout);

        // Add all interface components from each track to their proper areas
        if (tracks.size() == 0) {
            controls.removeAll();
        } else {
            for (BaseTrack track: tracks) {
                // Labels next to each row in the plot
                labels.add(track.getLabels());

                // Track's plot
                main.add(track.getPlot());

                // Add the track's controls
                if (track.hasControls()) {
                    controls.addTab(track.getName(), track.getControls());
                }
            }
        }

        // Push everything upwards
        main.add(Box.createVerticalGlue());
        labels.add(Box.createVerticalGlue());

        SpringUtilities.makeCompactGrid(main,
                main.getComponentCount(), 1, // Rows, Cols
                0, 0,
                0, 4);

        SpringUtilities.makeCompactGrid(labels,
                labels.getComponentCount(), 1, // Rows, Cols
                0, 0,
                0, 4);


        scroll.setViewportView(main);
        scroll.setRowHeaderView(labels);
        this.setScrollBackground();
    }

    void setScrollBackground() {
        JViewport viewport = scroll.getViewport();
        if (viewport != null) {
            viewport.setBackground(SCROLL_BACKGROUND);
            for (Component c: viewport.getComponents()) {
                c.setBackground(SCROLL_BACKGROUND);
            }
        }
        
        Component corner = scroll.getCorner(JScrollPane.UPPER_LEFT_CORNER);
        if (corner != null) {
            corner.setBackground(SCROLL_BACKGROUND);
        }

        JComponent rowHeader = scroll.getRowHeader();
        if (rowHeader != null) {
            rowHeader.setBackground(SCROLL_BACKGROUND);
            for (Component c: rowHeader.getComponents()) {
                c.setBackground(SCROLL_BACKGROUND);
            }
        }

        JComponent columnHeader = scroll.getColumnHeader();
        if (columnHeader != null) {
            columnHeader.setBackground(SCROLL_BACKGROUND);
        }
    }

    private void registerControllers() {
        //this.plotType.addActionListener(new ActionListener()
        //{
        //    public void actionPerformed(ActionEvent e) {
        //        JComboBox cb = (JComboBox)e.getSource();
        //        String name = (String)cb.getSelectedItem();

        //        ////CoreUI.ui.remove(name);
        //        //CoreUI.this.remove(ui);
        //        //CoreUI.this.ui = UIFactory.getUI(name, model);
        //        //CoreUI.this.add(CoreUI.this.ui, BorderLayout.CENTER);
        //        //CoreUI.this.revalidate();
        //    }
        //});

        this.loadFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(CoreUI.this.cards.getLayout());
                cl.next(CoreUI.this.cards);
            }
        });
    }
}
