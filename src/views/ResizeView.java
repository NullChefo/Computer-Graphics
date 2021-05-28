package views;

import controller.Controller;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class ResizeView extends javax.swing.JDialog implements Observer {
    private MainView mainview;
    private Controller control;
    private boolean bind = true;
    private boolean hasChange = false;


    public ResizeView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int)(parent.getLocation().getX()+parent.getWidth()-this.getWidth()),(int)(parent.getLocation().getY())));
        this.setVisible(false);
        mainview = (MainView)parent;
        control = mainview.getController();
        control.addObserver(this);
        spinnerResizeWidth.addChangeListener(control);
        spinnerResizeHeight.addChangeListener(control);
        buttonResizeBind.addActionListener(control);
        buttonResizeResize.addActionListener(control);
        buttonResizeReset.addActionListener(control);
        buttonResizeBind.setIcon(new ImageIcon("src/images/bind.png"));
    }

    public JSpinner getSpinnerResizeWidth() {
        return spinnerResizeWidth;
    }

    public JSpinner getSpinnerResizeHeight() {
        return spinnerResizeHeight;
    }

    public JButton getButtonResizeBind() {
        return buttonResizeBind;
    }

    public JButton getButtonResizeReset() {
        return buttonResizeReset;
    }

    public JButton getButtonResizeResize() {
        return buttonResizeResize;
    }

    public boolean getBind() {
        return bind;
    }

    public void setBind(boolean b) {
        bind = b;
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        labelWidth = new javax.swing.JLabel();
        labelHeight = new javax.swing.JLabel();
        spinnerResizeWidth = new javax.swing.JSpinner();
        spinnerResizeHeight = new javax.swing.JSpinner();
        buttonResizeBind = new javax.swing.JButton();
        buttonResizeResize = new javax.swing.JButton();
        buttonResizeReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setFont(labelWidth, labelHeight);

        spinnerResizeWidth.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerResizeWidth.setModel(new javax.swing.SpinnerNumberModel(640, 1, 2000, 1));

        spinnerResizeHeight.setFont(new java.awt.Font("Times New Roman", 0, 14));
        spinnerResizeHeight.setModel(new javax.swing.SpinnerNumberModel(420, 1, 2000, 1));

        buttonResizeResize.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonResizeResize.setText("Resize");

        buttonResizeReset.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonResizeReset.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(spinnerResizeWidth)
                                                        .addComponent(labelWidth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelHeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(spinnerResizeHeight)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(buttonResizeBind, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(buttonResizeResize, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(buttonResizeReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelWidth)
                                        .addComponent(labelHeight))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(spinnerResizeWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinnerResizeHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonResizeBind, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonResizeResize)
                                        .addComponent(buttonResizeReset))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    static void setFont(JLabel jLabel1, JLabel jLabel2) {
        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("width (px)");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("height (px)");
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
            java.util.logging.Logger.getLogger(ResizeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ResizeView dialog = new ResizeView(new javax.swing.JFrame(), true);
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
    // Variables declaration
    private javax.swing.JButton buttonResizeBind;
    private javax.swing.JButton buttonResizeReset;
    private javax.swing.JButton buttonResizeResize;
    private javax.swing.JSpinner spinnerResizeHeight;
    private javax.swing.JSpinner spinnerResizeWidth;
    private javax.swing.JLabel labelWidth;
    private javax.swing.JLabel labelHeight;
    // End of variables declaration

    @Override
    public void update(Observable abs, Object o) {
        if (o == buttonResizeBind) {
            if (bind) {
                bind = false;
                buttonResizeBind.setIcon(new ImageIcon("src/images/unbind.png"));
            }
            else {
                bind = true;
                buttonResizeBind.setIcon(new ImageIcon("src/images/bind.png"));
            }
        }
        else if (o == spinnerResizeWidth) {
            if (bind) {
                if (mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getResizeProj()) {
                    if (!hasChange) {
                        ImagePanel img = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                        hasChange = true;
                        spinnerResizeHeight.setValue((int)((float)((int) spinnerResizeWidth.getValue())*img.getImgH()/img.getImgW()));
                        hasChange = false;
                    }
                }
                else if (!hasChange) {
                    ImagePanel img = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    float w = img.getLayers().get(img.getIndexSelect()).getImage().getWidth();
                    float h = img.getLayers().get(img.getIndexSelect()).getImage().getHeight();
                    hasChange = true;
                    spinnerResizeHeight.setValue((int)((float)((int) spinnerResizeWidth.getValue())*h/w));
                    hasChange = false;
                }
            }
        }
        else if (o == spinnerResizeHeight) {
            if (bind) {
                if (mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getResizeProj()) {
                    if (!hasChange) {
                        ImagePanel img = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                        hasChange = true;
                        spinnerResizeHeight.setValue((int)((float)((int) spinnerResizeWidth.getValue())*img.getImgW()/img.getImgH()));
                        hasChange = false;
                    }
                }
                else if (!hasChange) {
                    ImagePanel img = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    float w = img.getLayers().get(img.getIndexSelect()).getImage().getWidth();
                    float h = img.getLayers().get(img.getIndexSelect()).getImage().getHeight();
                    hasChange = true;
                    spinnerResizeWidth.setValue((int)((float)((int) spinnerResizeHeight.getValue())*w/h));
                    hasChange = false;
                }
            }
        }
    }
}
