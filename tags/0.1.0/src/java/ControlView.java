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
import javax.swing.event.*;
import javax.swing.text.*;
import java.io.*;

public class ControlView extends JPanel implements IView {

    CompositionModel model;

    JLabel aminoAcidsLabel;
    JTextField aminoAcids;

    JLabel windowSizeLabel;
    JPanel windowSizeControls;
    JSlider windowSizeSlide;
    JSpinner windowSizeSpin;

    JButton zoomIn;
    JButton zoomOut;

    JLabel downloadLabel;
    JButton download;

    JLabel displayOptions;
    JPanel displayPanel;
    ButtonGroup displayGroup;
    JRadioButton dynamicIntensity;
    JRadioButton fixedIntensity;
    JRadioButton threshold;
    JPanel thresholdLevelControls;
    JSlider thresholdLevelSlider;
    JSpinner thresholdLevelSpinner;
    DisplaySelectionListener displayListener;

    JComboBox aaGroups;

    public ControlView(CompositionModel model) {
        super();

        assert model != null;
        this.model = model;

        this.aminoAcidsLabel = new JLabel("Amino Acids:");
        this.aminoAcids = new JTextField(model.getAminoAcids(), 10);
        this.aaGroups = new JComboBox(AAConstants.AMINO_ACID_GROUPS.keySet().toArray(new String[1]));
        this.aaGroups.setSelectedIndex(-1); // Default to no selected item

        this.aminoAcidsLabel.setLabelFor(aminoAcids);
        this.aminoAcids.setMinimumSize(aminoAcids.getPreferredSize());

        this.windowSizeLabel = new JLabel("Windows Size:");
        this.windowSizeSlide = new JSlider(1, 200, model.getWindowSize());
        // FIXME: make an adapter for this interface in our model
        this.windowSizeSpin = new JSpinner(new SpinnerNumberModel(model.getWindowSize(), 1, 200, 1));
        this.windowSizeControls = new JPanel();

        windowSizeControls.setLayout(new BorderLayout());
        windowSizeControls.add(windowSizeSlide, BorderLayout.CENTER);
        windowSizeControls.add(windowSizeSpin, BorderLayout.EAST);

        this.zoomIn = new JButton("Zoom In");
        this.zoomOut = new JButton("Zoom Out");

        this.downloadLabel = new JLabel("Download data as CSV:");
        this.download = new JButton("Download");

        this.displayOptions = new JLabel("Display options:");
        this.displayOptions.setVerticalAlignment(JLabel.TOP);

        this.displayPanel = new JPanel();
        GridLayout displayGrid = new GridLayout(4, 1);
        displayGrid.setVgap(0);
        displayPanel.setLayout(displayGrid);
        //displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        this.displayGroup = new ButtonGroup();
        this.dynamicIntensity = new JRadioButton("Dynamic intensity", true);
        this.displayGroup.add(dynamicIntensity);
        this.displayPanel.add(dynamicIntensity);
        this.fixedIntensity = new JRadioButton("Percent intensity");
        this.displayGroup.add(fixedIntensity);
        this.displayPanel.add(fixedIntensity);
        this.threshold = new JRadioButton("Threshold");
        this.displayGroup.add(threshold);
        this.displayPanel.add(threshold);
        this.displayListener = new DisplaySelectionListener(model);

        this.thresholdLevelSlider = new JSlider(1, 100, 15);
        this.thresholdLevelSpinner = new JSpinner(new SpinnerNumberModel(15, 1, 100, 1));
        // default to disabled
        this.thresholdLevelSpinner.setEnabled(false);
        this.thresholdLevelSlider.setEnabled(false);

        this.thresholdLevelControls = new JPanel();
        thresholdLevelControls.setLayout(new BorderLayout());
        thresholdLevelControls.add(thresholdLevelSlider, BorderLayout.CENTER);
        thresholdLevelControls.add(thresholdLevelSpinner, BorderLayout.EAST);
        this.displayPanel.add(thresholdLevelControls);

        this.layoutView();

        this.registerControllers();

        this.model.addView(this);
    }

    public void layoutView() {
        SpringLayout sl = new SpringLayout();
        this.setLayout(sl);

        this.add(aminoAcidsLabel);

        JPanel aaPanel = new JPanel();
        aaPanel.setLayout(new BorderLayout());
        aaPanel.add(aminoAcids, BorderLayout.CENTER);
        aaPanel.add(aaGroups, BorderLayout.EAST);
        this.add(aaPanel);

        this.add(zoomIn);

        this.add(windowSizeLabel);
        this.add(windowSizeControls);

        this.add(zoomOut);

        this.displayPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(displayOptions);
        this.add(displayPanel);

        this.add(new JLabel());

        this.add(downloadLabel);
        JPanel downloadPanel = new JPanel();
        downloadPanel.setLayout(new BoxLayout(downloadPanel, BoxLayout.X_AXIS));
        downloadPanel.add(download);
        this.add(downloadPanel);
        this.add(new JLabel());

        SpringUtilities.makeCompactGrid(this,
                4, 3, // Rows, Cols
                6, 6,
                6, 6);

    }

    /* Update widgets to reflect current model state */
    public void updateView() {
        this.windowSizeSpin.setValue(model.getWindowSize());
        this.windowSizeSlide.setValue(model.getWindowSize());

        this.thresholdLevelSpinner.setValue(model.getDisplayThreshold());
        this.thresholdLevelSlider.setValue(model.getDisplayThreshold());

        if (model.getDisplayType() != model.DISPLAY_THRESHOLD) {
            this.thresholdLevelSpinner.setEnabled(false);
            this.thresholdLevelSlider.setEnabled(false);
        } else {
            this.thresholdLevelSpinner.setEnabled(true);
            this.thresholdLevelSlider.setEnabled(true);
        }


        // Causes exception (trying to mutate on notification)
        try {
            this.aminoAcids.setText(model.getAminoAcids());
        } catch (Exception e) {
            // FIXME: ignoring exceptions tends to be a bad idea
        }
 
    }

    /* Controllers using anonymous classes */
    public void registerControllers() {

        this.aminoAcids.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                try {
                    model.setAminoAcids(d.getText(0, d.getLength()));
                } catch (BadLocationException change) {
                    System.err.println(change.getMessage());
                }
            }
            public void insertUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                try {
                    model.setAminoAcids(d.getText(0, d.getLength()));
                } catch (BadLocationException insert) {
                    System.err.println(insert.getMessage());
                }
            }
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                try {
                    model.setAminoAcids(d.getText(0, d.getLength()));
                } catch (BadLocationException remove) {
                    System.err.println(remove.getMessage());
                }
            }
        });

        this.windowSizeSlide.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider)e.getSource();
                model.setWindowSize(slider.getValue());
            }
        });

        this.windowSizeSpin.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner)e.getSource();
                Integer val = (Integer)spinner.getValue();
                model.setWindowSize(val.intValue());
            }
        });

        this.thresholdLevelSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider)e.getSource();
                model.setDisplayThreshold(slider.getValue());
            }
        });

        this.thresholdLevelSpinner.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner)e.getSource();
                Integer val = (Integer)spinner.getValue();
                model.setDisplayThreshold(val.intValue());
            }
        });

        this.zoomIn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                model.incZoomWidth();
            }
        });

        this.zoomOut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                model.decZoomWidth();
            }
        });

        this.download.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();

                int retval = fc.showSaveDialog(ControlView.this);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        if (file.exists()) {
                            int choice = JOptionPane.showConfirmDialog(
                                    ControlView.this, 
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

        this.aaGroups.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String group = (String)cb.getSelectedItem();
                if (AAConstants.AMINO_ACID_GROUPS.containsKey(group)) {
                    model.setAminoAcids((String)AAConstants.AMINO_ACID_GROUPS.get(group));
                }
            }
        });

        this.dynamicIntensity.addActionListener(displayListener);
        this.fixedIntensity.addActionListener(displayListener);
        this.threshold.addActionListener(displayListener);

    }

    class DisplaySelectionListener implements ActionListener {
        private CompositionModel model;

        public DisplaySelectionListener(CompositionModel m) {
            assert model != null;
            this.model = m;
        }

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command == "Dynamic intensity") {
                model.setDisplayType(model.DISPLAY_DYNAMIC);
            } else if (command == "Percent intensity") {
                model.setDisplayType(model.DISPLAY_FIXED);
            } else if (command == "Threshold") {
                model.setDisplayType(model.DISPLAY_THRESHOLD);
            } else {
                System.err.println("Undefined display type: " + command);
            }
        }
    }

}