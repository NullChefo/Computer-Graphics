package views;

import controller.Controller;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;


public class ColorBalanceView extends javax.swing.JDialog {

    // Variables declaration
    private MainView mainview = null;
    private Controller control = null;

    private javax.swing.JButton buttonBalanceCancel;
    private javax.swing.JButton buttonBalanceExec;
    private javax.swing.JLabel labelAlpha;
    private javax.swing.JLabel labelBlue;
    private javax.swing.JLabel labelGreen;
    private javax.swing.JLabel labelRed;
    private javax.swing.JSlider sliderAlpha;
    private javax.swing.JSlider sliderBlue;
    private javax.swing.JSlider sliderGreen;
    private javax.swing.JSlider sliderRed;

    private javax.swing.JLabel red;
    private javax.swing.JLabel green;
    private javax.swing.JLabel blue;
    private javax.swing.JLabel alpha;

    // End of variables declaration


    public ColorBalanceView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int)(parent.getLocation().getX()+parent.getWidth()-this.getWidth()),(int)(parent.getLocation().getY())));
        this.setVisible(false);
        mainview = (MainView)parent;
        control = mainview.getController();
        sliderRed.addChangeListener(control);
        sliderGreen.addChangeListener(control);
        sliderBlue.addChangeListener(control);
        sliderAlpha.addChangeListener(control);
        buttonBalanceExec.addActionListener(control);
        buttonBalanceCancel.addActionListener(control);
    }

    public JSlider getSliderRed() {
        return sliderRed;
    }

    public JSlider getSliderGreen() {
        return sliderGreen;
    }

    public JSlider getSliderBlue() {
        return sliderBlue;
    }

    public JSlider getSliderAlpha() {
        return sliderAlpha;
    }

    public JButton getButtonBalanceExec() {
        return buttonBalanceExec;
    }

    public JButton getButtonBalanceCancel() {
        return buttonBalanceCancel;
    }

    public JLabel getLabelRed() {
        return labelRed;
    }

    public JLabel getLabelGreen() {
        return labelGreen;
    }

    public JLabel getLabelBlue() {
        return labelBlue;
    }

    public JLabel getLabelAlpha() {
        return labelAlpha;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        red = new javax.swing.JLabel();
        sliderRed = new javax.swing.JSlider();
        labelRed = new javax.swing.JLabel();
        green = new javax.swing.JLabel();
        sliderGreen = new javax.swing.JSlider();
        labelGreen = new javax.swing.JLabel();
        blue = new javax.swing.JLabel();
        sliderBlue = new javax.swing.JSlider();
        labelBlue = new javax.swing.JLabel();
        buttonBalanceExec = new javax.swing.JButton();
        buttonBalanceCancel = new javax.swing.JButton();
        alpha = new javax.swing.JLabel();
        sliderAlpha = new javax.swing.JSlider();
        labelAlpha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        red.setFont(new java.awt.Font("Times New Roman", 0, 14));
        red.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        red.setText("Red");

        sliderRed.setMaximum(200);
        sliderRed.setToolTipText("");
        sliderRed.setValue(100);

        labelRed.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelRed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRed.setText("100");

        green.setFont(new java.awt.Font("Times New Roman", 0, 14));
        green.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        green.setText("Green");

        sliderGreen.setMaximum(200);
        sliderGreen.setToolTipText("");
        sliderGreen.setValue(100);

        labelGreen.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelGreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGreen.setText("100");

        blue.setFont(new java.awt.Font("Times New Roman", 0, 14));
        blue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blue.setText("Blue");

        sliderBlue.setMaximum(200);
        sliderBlue.setToolTipText("");
        sliderBlue.setValue(100);

        labelBlue.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBlue.setText("100");

        buttonBalanceExec.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonBalanceExec.setText("Execute");

        buttonBalanceCancel.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonBalanceCancel.setText("Cancel");

        alpha.setFont(new java.awt.Font("Times New Roman", 0, 14));
        alpha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alpha.setText("Alpha");

        sliderAlpha.setMaximum(200);
        sliderAlpha.setToolTipText("");
        sliderAlpha.setValue(100);

        labelAlpha.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelAlpha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAlpha.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                        .addGroup(layout.createSequentialGroup()

                                .addContainerGap()

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                        .addGroup(layout.createSequentialGroup()

                                                .addComponent(red, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sliderRed, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelRed))

                                        .addGroup(layout.createSequentialGroup()

                                                .addComponent(green, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sliderGreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelGreen))

                                        .addGroup(layout.createSequentialGroup()

                                                .addComponent(buttonBalanceExec)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonBalanceCancel)
                                                .addGap(0, 0, Short.MAX_VALUE))

                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()

                                                                .addComponent(blue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sliderBlue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                                                        .addGroup(layout.createSequentialGroup()

                                                                .addComponent(alpha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sliderAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))

                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelAlpha)
                                                        .addComponent(labelBlue))))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderRed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(red, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelRed))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderGreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(green, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderBlue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(blue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                                        .addComponent(labelBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                        .addComponent(sliderAlpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(alpha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelAlpha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))

                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonBalanceExec)
                                        .addComponent(buttonBalanceCancel))

                                .addContainerGap())
        );

        pack();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ColorBalanceView dialog = new ColorBalanceView(new javax.swing.JFrame(), true);
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
