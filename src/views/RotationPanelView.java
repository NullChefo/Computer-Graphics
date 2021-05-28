package views;

import controller.Controller;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class RotationPanelView extends javax.swing.JDialog implements Observer {
    private MainView mainview = null;
    private Controller control = null;

    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonRotation;
    private javax.swing.JSlider sliderAngle;
    private javax.swing.JSpinner spinnerAngle;
    private javax.swing.JLabel labelAngle;
    private javax.swing.JLabel labelDegree;

    /**
     * Creates new form RotationView
     */
    public RotationPanelView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int)(parent.getLocation().getX()+parent.getWidth()-this.getWidth()),(int)(parent.getLocation().getY())));
        this.setVisible(false);
        mainview = (MainView)parent;
        control = mainview.getController();
        control.addObserver(this);
        spinnerAngle.addChangeListener(control);
        sliderAngle.addChangeListener(control);
        buttonRotation.addActionListener(control);
        buttonReset.addActionListener(control);
        buttonCancel.addActionListener(control);
    }

    public JSpinner getSpinnerAngle() {
        return spinnerAngle;
    }

    public JSlider getSliderAngle() {
        return sliderAngle;
    }

    public JButton getButtonRotation() {
        return buttonRotation;
    }

    public JButton getButtonReset() {
        return buttonReset;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        labelDegree = new javax.swing.JLabel();
        labelAngle = new javax.swing.JLabel();
        spinnerAngle = new javax.swing.JSpinner();
        sliderAngle = new javax.swing.JSlider();
        buttonRotation = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 140));

        labelDegree.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelDegree.setText("°");

        labelAngle.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelAngle.setText("Angle");

        spinnerAngle.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerAngle.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(-180.0f), Float.valueOf(180.0f), Float.valueOf(1.0f)));

        sliderAngle.setMaximum(180);
        sliderAngle.setMinimum(-180);
        sliderAngle.setToolTipText("");
        sliderAngle.setValue(0);

        buttonRotation.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonRotation.setText("Rotation");

        buttonReset.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonReset.setText("Reset");

        buttonCancel.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sliderAngle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(labelAngle)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(spinnerAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelDegree))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonRotation)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonCancel)))
                                                .addGap(0, 18, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelAngle)
                                        .addComponent(spinnerAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDegree))
                                .addGap(18, 18, 18)
                                .addComponent(sliderAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonRotation)
                                        .addComponent(buttonReset)
                                        .addComponent(buttonCancel))
                                .addContainerGap())
        );

        pack();
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RotationPanelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RotationPanelView dialog = new RotationPanelView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }



    @Override
    public void update(Observable obs, Object o) {
        if (o == spinnerAngle)
            sliderAngle.setValue(Math.round((float) spinnerAngle.getValue()));
        if (o == sliderAngle)
            spinnerAngle.setValue((float) sliderAngle.getValue());
    }
}
