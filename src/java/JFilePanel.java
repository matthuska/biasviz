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
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class JFilePanel extends JPanel {
    
    java.util.List<ActionListener> listeners;

    public enum StatusType { SUCCESS, FAILURE }
    static public Icon successIcon;
    static public Icon failureIcon;

    JLabel title;
    JButton selectFile;
    JLabel status;
    JLabel filename;
    File file;

    public JFilePanel() {
        listeners = new ArrayList<ActionListener>();
        createWidgets();
        layoutView();
        registerControllers();
    }
    
    public JFilePanel(String newTitle) {
        this();
        this.setTitle(newTitle);
    }

    private void createWidgets() {
        title = new JLabel("Select a file:");
        selectFile = new JButton("Choose File");
        status = new JLabel();
        filename = new JLabel();
    }

    private void layoutView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c;
        Insets insets = new Insets(3, 3, 3, 3);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectFile.setAlignmentX(Component.LEFT_ALIGNMENT);
        filename.setAlignmentX(Component.LEFT_ALIGNMENT);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = insets;
        c.weightx = 1.0;
        this.add(title, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = insets;
        this.add(selectFile, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = insets;
        this.add(filename, c);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = insets;
        c.weightx = 1.0;
        this.add(status, c);

    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String newTitle) {
        title.setText(newTitle);
    }

    public String getStatus() {
        return status.getText();
    }

    public void setStatus(String newStatus) {
        status.setText(newStatus);
    }

    public void addActionListener(ActionListener l) {
        listeners.add(l);
    }

    public File getSelectedFile() {
        return file;
    }

    private void setSelectedFile(File f) {
        file = f;
        filename.setText(f.getName());
    }

    private void registerControllers() {
        this.selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                File file;
                // Use Swing file chooser on Linux, AWT on others
                String os = System.getProperty("os.name");
                if (os != null && !os.startsWith("Linux")) {
                    FileDialog fc = new FileDialog(new Frame(), "Select file", FileDialog.LOAD);
                    fc.setVisible(true);

                    // Check to see if user cancelled the dialog.
                    if (fc.getFile() == null) {
                        return;
                    }
                    String filename = fc.getDirectory() + fc.getFile();
                    file = new File(filename);
                } else {
                    JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(JFilePanel.this);
                    if (returnVal != JFileChooser.APPROVE_OPTION) {
                        return;
                    }
                    file = fc.getSelectedFile();
                }

                if (!file.canRead()) {
                    int choice = JOptionPane.showConfirmDialog(
                        JFilePanel.this, 
                        "The file \"" + file.getName() + 
                        "\" can not be read.\n" +
                        "Please select another file.", 
                        "File Not Readable", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JFilePanel.this.setSelectedFile(file);

                for (ActionListener l: JFilePanel.this.listeners) {
                    l.actionPerformed(evt);
                }
            }
        });
    }
    
}
