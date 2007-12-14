import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

class LoaderUI extends JPanel {

    CoreModel model;
    JPanel cards;
    
    JLabel titleLabel;

    JLabel loadMSALabel;
    JButton loadMSAButton;
    JTextField loadMSATextField;

    JLabel loadSecondaryLabel;
    JButton loadSecondaryButton;

    JLabel loadUserDataLabel;
    JButton loadUserDataButton;

    JButton doneButton;

    public LoaderUI(CoreModel m, JPanel c) {
        assert m != null;
        model = m;

        assert c != null;
        cards = c;

        createWidgets();
        layoutWidgets();
        registerControllers();

        //model.addView(this);
    }

    private void createWidgets() {
        titleLabel = new JLabel("<html><b>Load BiasViz Input</b></html>");

        loadMSALabel = new JLabel("Load multiple sequence alignment:");
        loadMSAButton = new JButton("Choose File...");
        loadMSATextField = new JTextField();
        loadMSATextField.setEnabled(false);

        loadSecondaryLabel = new JLabel("Load JPred secondary structure:");
        loadSecondaryButton = new JButton("Choose File...");

        loadUserDataLabel = new JLabel("Load raw values for each amino acid:");
        loadUserDataButton = new JButton("Choose File...");


        doneButton = new JButton("Done");
    }

    private void layoutWidgets() {
        this.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);

        this.add(loadMSALabel);
        //this.add(loadMSATextField);
        this.add(loadMSAButton);

        //this.add(loadSecondaryLabel);
        //this.add(loadSecondaryButton);

        this.add(loadUserDataLabel);
        this.add(loadUserDataButton);

        this.add(doneButton);
    }

    private void registerControllers() {
        this.loadMSAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int retval = fc.showOpenDialog(LoaderUI.this);

                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        if (!file.canRead()) {
                            int choice = JOptionPane.showConfirmDialog(
                                    LoaderUI.this, 
                                    "The file \"" + file.getName() + 
                                    "\" can not be read.\n" +
                                    "Please select another file.", 
                                    "File Not Readable", 
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //file.createNewFile();
                        StringBuffer buf = new StringBuffer();
                        BufferedReader in = new BufferedReader(new FileReader(file));
                        String line = new String();
                        while ((line = in.readLine()) != null) {
                            buf.append(line + "\n");
                        }

                        // FIXME: Check to see if parsing was successful, inform
                        // the user if it isn't.
                        LoaderUI.this.model.setAlignment(buf.toString());
                    } catch (IOException io) {
                        JOptionPane.showMessageDialog(null,
                                "Could not save the file in that location.",
                                "File Save Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.loadUserDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int retval = fc.showOpenDialog(LoaderUI.this);

                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        if (!file.canRead()) {
                            int choice = JOptionPane.showConfirmDialog(
                                    LoaderUI.this, 
                                    "The file \"" + file.getName() + 
                                    "\" can not be read.\n" +
                                    "Please select another file.", 
                                    "File Not Readable", 
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //file.createNewFile();
                        StringBuffer buf = new StringBuffer();
                        BufferedReader in = new BufferedReader(new FileReader(file));
                        String line = new String();
                        while ((line = in.readLine()) != null) {
                            buf.append(line + "\n");
                        }

                        // FIXME: Check to see if parsing was successful, inform
                        // the user if it isn't.
                        LoaderUI.this.model.setUserData(buf.toString());
                    } catch (IOException io) {
                        JOptionPane.showMessageDialog(null,
                                "Could not save the file in that location.",
                                "File Save Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.loadSecondaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int retval = fc.showOpenDialog(LoaderUI.this);

                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        if (!file.canRead()) {
                            int choice = JOptionPane.showConfirmDialog(
                                    LoaderUI.this, 
                                    "The file \"" + file.getName() + 
                                    "\" can not be read.\n" +
                                    "Please select another file.", 
                                    "File Not Readable", 
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //file.createNewFile();
                        StringBuffer buf = new StringBuffer();
                        BufferedReader in = new BufferedReader(new FileReader(file));
                        String line = new String();
                        while ((line = in.readLine()) != null) {
                            buf.append(line + "\n");
                        }

                        // FIXME: Check to see if parsing was successful, inform
                        // the user if it isn't.
                        LoaderUI.this.model.setSecondary(buf.toString());
                    } catch (IOException io) {
                        JOptionPane.showMessageDialog(null,
                                "Could not save the file in that location.",
                                "File Save Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
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
