import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CoreUI extends JPanel {
    
    CoreModel model;
    JPanel cards;

    BaseUI ui;
    ZoomControlView zoomControls;

    JLabel plotTypeLabel;
    JComboBox plotType;

    JPanel topPanel;
    JButton loadFilesButton;

    public CoreUI(CoreModel m, JPanel c) {
        assert m != null;
        model = m;

        assert c != null;
        cards = c;

        // Holds Zoom and Plot Type controls
        topPanel = new JPanel();

        plotTypeLabel = new JLabel("Plot Type:");
        plotType = new JComboBox(UIFactory.getUINames());

        ui = new CompositionUI(model);
        zoomControls = new ZoomControlView(model);
        loadFilesButton = new JButton("Change Input Files");

        layoutWidgets();
        registerControllers();
    }

    private void layoutWidgets() {

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.add(Box.createHorizontalStrut(6));
        topPanel.add(loadFilesButton);
        topPanel.add(plotTypeLabel);
        topPanel.add(plotType);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(zoomControls);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(ui, BorderLayout.CENTER);
    }

    private void registerControllers() {
        this.plotType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String name = (String)cb.getSelectedItem();
                CoreUI.this.remove(ui);
                CoreUI.this.ui = UIFactory.getUI(name, model);
                CoreUI.this.add(CoreUI.this.ui, BorderLayout.CENTER);
                CoreUI.this.revalidate();
            }
        });

        this.loadFilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(CoreUI.this.cards.getLayout());
                cl.next(CoreUI.this.cards);
            }
        });
    }
}
