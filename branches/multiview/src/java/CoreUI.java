import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CoreUI extends JPanel {
    
    CoreModel model;
    BaseUI ui;
    ZoomControlView zoomControls;

    JLabel plotTypeLabel;
    JComboBox plotType;

    CoreUI(CoreModel m) {
        assert m != null;
        model = m;

        plotTypeLabel = new JLabel("Plot Type:");
        plotType = new JComboBox(UIFactory.getUINames());

        ui = new CompositionUI(model);
        zoomControls = new ZoomControlView();

        layoutWidgets();
        registerControllers();
    }

    void layoutWidgets() {
        this.add(plotTypeLabel);
        this.add(plotType);

        this.setLayout(new BorderLayout());
        this.add(zoomControls, BorderLayout.NORTH);
        this.add(plotType, BorderLayout.SOUTH);
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
    }
}
