package views;

//region imports
import controller.Controller;
import java.awt.Point;
import javax.swing.*;
//endregion



public class BrightnessPanelView extends javax.swing.JDialog {



    // Variables declaration
    private MainView mainview = null;
    private Controller control = null;
    private javax.swing.JButton buttonBrightCancel;
    private javax.swing.JButton buttonBrightExec;
    private javax.swing.JLabel labelBright;
    private javax.swing.JLabel labelContrast;
    private javax.swing.JSlider sliderBright;
    private javax.swing.JSlider sliderContrast;
    private javax.swing.JLabel labelBrightness;
    private javax.swing.JLabel labelContras;
    // End of variables declaration

    public BrightnessPanelView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int)(parent.getLocation().getX()+parent.getWidth()-this.getWidth()),(int)(parent.getLocation().getY())));
        this.setVisible(false);
        mainview = (MainView)parent;
        control = mainview.getController();
        sliderBright.addChangeListener(control);
        sliderContrast.addChangeListener(control);
        buttonBrightExec.addActionListener(control);
        buttonBrightCancel.addActionListener(control);
    }
    public JLabel getLabelBright() {
        return labelBright;
    }
    public JLabel getLabelContrast() {
        return labelContrast;
    }
    public JSlider getSliderBright() {
        return sliderBright;
    }
    public JButton getBrightCancel() {
        return buttonBrightCancel;
    }
    public JSlider getSliderContrast() {
        return sliderContrast;
    }
    public JButton getBrightExec() {
        return buttonBrightExec;
    }



    @SuppressWarnings("unchecked")
    private void initComponents() {

        labelBrightness = new javax.swing.JLabel();
        sliderBright = new javax.swing.JSlider();
        labelBright = new javax.swing.JLabel();
        labelContras = new javax.swing.JLabel();
        sliderContrast = new javax.swing.JSlider();
        labelContrast = new javax.swing.JLabel();
        buttonBrightExec = new javax.swing.JButton();
        buttonBrightCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelBrightness.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelBrightness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBrightness.setText("Brightness");

        sliderBright.setMaximum(300);
        sliderBright.setValue(100);

        labelBright.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelBright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBright.setText("100");

        labelContras.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelContras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelContras.setText("Contrast");

        sliderContrast.setMaximum(1000);
        sliderContrast.setValue(100);

        labelContrast.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelContrast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelContrast.setText("100");

        buttonBrightExec.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonBrightExec.setText("Execute");

        buttonBrightCancel.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonBrightCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()

                                                .addComponent(buttonBrightExec)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonBrightCancel)
                                                .addGap(0, 0, Short.MAX_VALUE))

                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()

                                                                .addComponent(labelContras, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sliderContrast, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))

                                                        .addGroup(layout.createSequentialGroup()

                                                                .addComponent(labelBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sliderBright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))

                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelBright)
                                                        .addComponent(labelContrast))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderBright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelBrightness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelBright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderContrast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelContras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelContrast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                                        .addComponent(buttonBrightExec)
                                        .addComponent(buttonBrightCancel))

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
            java.util.logging.Logger.getLogger(BrightnessPanelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BrightnessPanelView dialog = new BrightnessPanelView(new javax.swing.JFrame(), true);
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

}
