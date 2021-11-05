package views;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class NewView extends javax.swing.JDialog {
    private MainView mainview;
    private Controller control;
    // Variables declaration
    private javax.swing.JButton buttonNewCancel;
    private javax.swing.JButton buttonNewCreate;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.JSpinner spinnerNewHeight;
    private javax.swing.JSpinner spinnerNewWidth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    /**
     * Creates new form NewView
     */
    public NewView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        mainview = (MainView) parent;
        control = mainview.getController();
        final JLabel previewLabel = new JLabel("Project CG & GPI", JLabel.CENTER);
        previewLabel.setFont(new Font("New Times Roman", Font.BOLD, 50));
        previewLabel.setSize(previewLabel.getPreferredSize());
        previewLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
        colorChooser.setPreviewPanel(previewLabel);
        this.buttonNewCreate.addActionListener(control);
        this.buttonNewCancel.addActionListener(control);
        this.setTitle("New image - Project CG & GPI");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewView dialog = new NewView(new javax.swing.JFrame(), true);
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

    public JButton getButtonNewCreate() {
        return buttonNewCreate;
    }

    public JButton getButtonNewCancel() {
        return buttonNewCancel;
    }

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    public JSpinner getSpinnerNewWidth() {
        return spinnerNewWidth;
    }

    public JSpinner getSpinnerNewHeight() {
        return spinnerNewHeight;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        spinnerNewWidth = new javax.swing.JSpinner();
        spinnerNewHeight = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        colorChooser = new javax.swing.JColorChooser();
        buttonNewCreate = new javax.swing.JButton();
        buttonNewCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(null);

        spinnerNewWidth.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerNewWidth.setModel(new javax.swing.SpinnerNumberModel(640, 1, 2000, 1));

        spinnerNewHeight.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerNewHeight.setModel(new javax.swing.SpinnerNumberModel(420, 1, 2000, 1));

        ResizeView.setFont(jLabel1, jLabel2);

        colorChooser.setFont(new java.awt.Font("Times New Roman", 0, 12)); //

        buttonNewCreate.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonNewCreate.setText("Create");

        buttonNewCancel.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonNewCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(spinnerNewWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(spinnerNewHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonNewCreate)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonNewCancel)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(spinnerNewWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinnerNewHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(colorChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonNewCreate)
                                        .addComponent(buttonNewCancel))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // End of variables declaration
}
