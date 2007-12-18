import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.net.URL;

class LoaderUI extends JPanel implements IView {

    CoreModel model;
    JPanel cards;
    
    JLabel titleLabel;

    JLabel loadMSALabel;
    JLabel loadMSAStatusLabel;
    JButton loadMSAButton;
    JTextField loadMSATextField;

    JLabel loadSecondaryLabel;
    JButton loadSecondaryButton;

    JLabel loadUserDataLabel;
    JLabel loadUserDataStatusLabel;
    JButton loadUserDataButton;

    JPanel required;
    JPanel optional;

    //Icon successIcon;
    //Icon failureIcon;

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
        loadMSALabel = new JLabel("Load multiple sequence alignment:");
        loadMSAStatusLabel = new JLabel("");
        loadMSAButton = new JButton("Choose File...");
        loadMSATextField = new JTextField();
        loadMSATextField.setEnabled(false);

        optional = new JPanel();
        loadSecondaryLabel = new JLabel("Load JPred secondary structure:");
        loadSecondaryButton = new JButton("Choose File...");

        loadUserDataLabel = new JLabel("Load raw values for each amino acid:");
        loadUserDataStatusLabel = new JLabel("");
        loadUserDataButton = new JButton("Choose File...");

        //URL yesUrl = getClass().getResource("icons/gtk-yes.png");
        //URL noUrl = getClass().getResource("icons/gtk-no.png");

        //Toolkit tk = Toolkit.getDefaultToolkit();

        //try {

        //    Image yesImage = tk.createImage(yesUrl);
        //    Image noImage = tk.createImage(noUrl);

        //    successIcon = new ImageIcon(yesImage);
        //    failureIcon = new ImageIcon(noImage);

        //} catch (Exception e) {
        //    System.err.println(e.getCause());
        //}

        doneButton = new JButton("Done");
    }

    private void layoutView() {
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        required.setLayout(new BoxLayout(required, BoxLayout.Y_AXIS));
        required.setBorder(BorderFactory.createTitledBorder("Required"));

        optional.setLayout(new BoxLayout(optional, BoxLayout.Y_AXIS));
        optional.setBorder(BorderFactory.createTitledBorder("Optional"));

        this.add(titleLabel);

        this.add(Box.createVerticalStrut(12));
        required.add(Box.createHorizontalGlue());
        required.add(loadMSALabel);


        required.add(loadMSAButton);
        this.add(required);

        //this.add(Box.createVerticalStrut(12));
        //optional.add(loadSecondaryLabel);
        //optional.add(loadSecondaryButton);

        optional.add(Box.createHorizontalGlue());
        optional.add(loadUserDataLabel);
        optional.add(loadUserDataButton);

        this.add(optional);

        this.add(Box.createVerticalStrut(12));
        this.add(doneButton);
    }

    public void updateView() {
            

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

                        try {
                            UserData ud = Parser.parseUserData(buf.toString(), LoaderUI.this.model.getAlignment());
                            LoaderUI.this.model.setUserData(ud);
                        } catch (Exception e) {
                            System.err.println("Exception parsing user data.");
                        }
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
