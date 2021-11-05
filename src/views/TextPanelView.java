package views;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class TextPanelView extends javax.swing.JDialog {
    private MainView mainview;
    private Controller control;
    private javax.swing.JButton buttonFontCancel;
    private javax.swing.JButton buttonFontCreate;
    private javax.swing.JComboBox comboBoxFont;
    private javax.swing.JComboBox comboBoxFontStyle;
    private javax.swing.JSpinner spinnerFontSize;
    private javax.swing.JTextField textFieldFontText;
    private javax.swing.JLabel labelFont;
    private javax.swing.JLabel labelStyle;
    private javax.swing.JLabel labelSize;
    private javax.swing.JLabel labelPreview;


    public TextPanelView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int) (parent.getLocation().getX() + parent.getWidth() - this.getWidth()), (int) (parent.getLocation().getY())));
        this.setVisible(false);
        this.setTitle("Text Layer - Project CG & GPI");
        mainview = (MainView) parent;
        control = mainview.getController();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = e.getAvailableFontFamilyNames();
        for (String s : fonts) {
            comboBoxFont.addItem(s);
        }
        comboBoxFont.setSelectedItem("Times New Roman");
        comboBoxFont.addActionListener(control);
        comboBoxFontStyle.addActionListener(control);
        spinnerFontSize.addChangeListener(control);
        textFieldFontText.addKeyListener(control);
        buttonFontCreate.addActionListener(control);
        buttonFontCancel.addActionListener(control);
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextPanelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TextPanelView dialog = new TextPanelView(new javax.swing.JFrame(), true);
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

    public JComboBox getComboBoxFont() {
        return comboBoxFont;
    }

    public JComboBox getComboBoxFontStyle() {
        return comboBoxFontStyle;
    }

    public JSpinner getSpinnerFontSize() {
        return spinnerFontSize;
    }

    public JTextField getTextFieldFontText() {
        return textFieldFontText;
    }

    public JButton getButtonFontCreate() {
        return buttonFontCreate;
    }

    public JButton getButtonFontCancel() {
        return buttonFontCancel;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        labelFont = new javax.swing.JLabel();
        comboBoxFont = new javax.swing.JComboBox();
        labelStyle = new javax.swing.JLabel();
        comboBoxFontStyle = new javax.swing.JComboBox();
        spinnerFontSize = new javax.swing.JSpinner();
        labelSize = new javax.swing.JLabel();
        textFieldFontText = new javax.swing.JTextField();
        buttonFontCreate = new javax.swing.JButton();
        buttonFontCancel = new javax.swing.JButton();
        labelPreview = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 0, 14));

        labelFont.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelFont.setText("Font");

        comboBoxFont.setFont(new java.awt.Font("Times New Roman", 0, 14));

        labelStyle.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelStyle.setText("Style");

        comboBoxFontStyle.setFont(new java.awt.Font("Times New Roman", 0, 14));
        comboBoxFontStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Plain", "Bold", "Italic", "Bold Italic"}));

        spinnerFontSize.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerFontSize.setModel(new javax.swing.SpinnerNumberModel(12, 1, 100, 1));

        labelSize.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelSize.setText("Size");

        textFieldFontText.setFont(new java.awt.Font("Times New Roman", 0, 14));
        textFieldFontText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        buttonFontCreate.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonFontCreate.setText("Create");

        buttonFontCancel.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonFontCancel.setText("Cancel");

        labelPreview.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelPreview.setText("Type your text for preview on image");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                        .addComponent(labelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                        .addComponent(textFieldFontText)
                                        .addGroup(layout.createSequentialGroup()

                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                                                        .addComponent(comboBoxFont, 0, 149, Short.MAX_VALUE)
                                                                        .addComponent(labelFont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                                                        .addComponent(comboBoxFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(labelStyle))

                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                                                        .addComponent(spinnerFontSize)
                                                                        .addComponent(labelSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))

                                                        .addGroup(layout.createSequentialGroup()

                                                                .addComponent(buttonFontCreate)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(buttonFontCancel)))

                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                                        .addComponent(labelFont)
                                        .addComponent(labelStyle)
                                        .addComponent(labelSize))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                                        .addComponent(comboBoxFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBoxFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinnerFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelPreview)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldFontText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                                        .addComponent(buttonFontCreate)
                                        .addComponent(buttonFontCancel))

                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }


}
