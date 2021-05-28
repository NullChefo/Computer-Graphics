package views;

import controller.Controller;

import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;


public class ToolsPanelView extends javax.swing.JDialog {

    private MainView mainView;
    private Controller controller;
    private Color colorBlack = Color.BLACK;
    private BufferedImage bufferedImage;
    private JColorChooser colorChooser;
    private JDialog dialogView;

    private javax.swing.JButton buttonToolColor;
    private javax.swing.JLabel labelToolSize;
    private javax.swing.JSlider sliderToolSize;
    private javax.swing.JToggleButton toggleButtonToolClear;
    private javax.swing.JToggleButton toggleButtonToolCut;
    private javax.swing.JToggleButton toggleButtonToolDraw;
    private javax.swing.JToggleButton toggleButtonToolFill;
    private javax.swing.JToggleButton toggleButtonToolHand;
    private javax.swing.JToggleButton toggleButtonToolPaint;
    private javax.swing.JToggleButton toggleButtonToolPipette;
    private javax.swing.JToggleButton toggleButtonToolSelect;
    private javax.swing.JToggleButton toggleButtonToolText;
    private javax.swing.JToggleButton toggleButtonToolRectangle;
    private javax.swing.JToggleButton toggleButtonToolLine;
    private javax.swing.JToggleButton toggleButtonToolOval;
    //#TODO add more primitives

    private javax.swing.JToggleButton toggleButtonToolStar;
    private javax.swing.JToggleButton toggleButtonToolHexagon;
    private javax.swing.JToggleButton toggleButtonToolParallelogram;
    private javax.swing.JToggleButton toggleButtonToolPentagon;
    private javax.swing.JToggleButton toggleButtonToolTrapezoid;
    private javax.swing.JToggleButton toggleButtonToolTriangle;
    private javax.swing.JToggleButton toggleButtonToolHearth;


    private int textX;
    private int textY;

    public ToolsPanelView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Tools - Project CG & GPI");
        mainView = (MainView) parent;
        controller = mainView.getController();
        colorChooser = new JColorChooser(colorBlack);
        final JLabel previewLabel = new JLabel("Project computer graphics and GPI", JLabel.CENTER);
        previewLabel.setFont(new Font("New Times Roman", Font.BOLD, 50));
        previewLabel.setSize(previewLabel.getPreferredSize());
        previewLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
        colorChooser.setPreviewPanel(previewLabel);
        ActionListener okActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colorBlack = colorChooser.getColor();
                controller.setCol(colorBlack);
                Graphics2D graphics2D = bufferedImage.createGraphics();
                Color color1 = new Color(102, 102, 102, 255);
                Color color2 = new Color(153, 153, 153, 255);
                Color color3;
                graphics2D.setColor(color2);
                for (int i = 0; i < buttonToolColor.getHeight(); i += 10) {
                    color3 = graphics2D.getColor();
                    for (int j = 0; j < buttonToolColor.getWidth(); j += 10) {
                        graphics2D.fillRect(j, i, 10, 10);
                        if (graphics2D.getColor() == color2)
                            graphics2D.setColor(color1);
                        else
                            graphics2D.setColor(color2);
                    }
                    if (color3 == color1)
                        graphics2D.setColor(color2);
                    else
                        graphics2D.setColor(color1);
                }
                graphics2D.setColor(colorBlack);
                graphics2D.fillRect(0, 0, buttonToolColor.getWidth(), buttonToolColor.getHeight());
                buttonToolColor.setIcon(new ImageIcon(bufferedImage));
            }
        };
        ActionListener cancelActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        };
        dialogView = JColorChooser.createDialog(mainView, "Color Chooser - Project CG & GPI", true, colorChooser, okActionListener, cancelActionListener);
        bufferedImage = new BufferedImage(buttonToolColor.getWidth(), buttonToolColor.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(colorBlack);
        g2d.fillRect(0, 0, buttonToolColor.getWidth(), buttonToolColor.getHeight());
        buttonToolColor.setIcon(new ImageIcon(bufferedImage));
        buttonToolColor.addActionListener(controller);
        toggleButtonToolText.addActionListener(controller);
        toggleButtonToolText.setIcon(new ImageIcon("src/images/text.png"));
        toggleButtonToolHand.addActionListener(controller);
        toggleButtonToolHand.setIcon(new ImageIcon("src/images/move.png"));
        toggleButtonToolDraw.addActionListener(controller);
        toggleButtonToolDraw.setIcon(new ImageIcon("src/images/draw.png"));
        toggleButtonToolPaint.addActionListener(controller);
        toggleButtonToolPaint.setIcon(new ImageIcon("src/images/paint.png"));
        toggleButtonToolClear.addActionListener(controller);
        toggleButtonToolClear.setIcon(new ImageIcon("src/images/clear.png"));
        toggleButtonToolRectangle.addActionListener(controller);
        toggleButtonToolRectangle.setIcon(new ImageIcon("src/images/rect.png"));
        toggleButtonToolFill.addActionListener(controller);
        toggleButtonToolFill.setIcon(new ImageIcon("src/images/fill.png"));
        toggleButtonToolCut.addActionListener(controller);
        toggleButtonToolCut.setIcon(new ImageIcon("src/images/cut.png"));
        toggleButtonToolSelect.addActionListener(controller);
        toggleButtonToolSelect.setIcon(new ImageIcon("src/images/select.png"));
        toggleButtonToolPipette.addActionListener(controller);
        toggleButtonToolPipette.setIcon(new ImageIcon("src/images/pipette.png"));

        toggleButtonToolOval.addActionListener(controller);
        toggleButtonToolOval.setIcon(new ImageIcon("src/images/oval.png"));
        toggleButtonToolLine.addActionListener(controller);
        toggleButtonToolLine.setIcon(new ImageIcon("src/images/line.png"));
        //#TODO add more primitives

        toggleButtonToolStar.addActionListener(controller);
        toggleButtonToolStar.setIcon(new ImageIcon("src/images/Star.png"));
        toggleButtonToolHexagon.addActionListener(controller);
        toggleButtonToolHexagon.setIcon(new ImageIcon("src/images/hexagon.png"));
        toggleButtonToolParallelogram.addActionListener(controller);
        toggleButtonToolParallelogram.setIcon(new ImageIcon("src/images/parallelogram.png"));
        toggleButtonToolPentagon.addActionListener(controller);
        toggleButtonToolPentagon.setIcon(new ImageIcon("src/images/pentagon.png"));
        toggleButtonToolTrapezoid.addActionListener(controller);
        toggleButtonToolTrapezoid.setIcon(new ImageIcon("src/images/trapezoid.png"));
        toggleButtonToolTriangle.addActionListener(controller);
        toggleButtonToolTriangle.setIcon(new ImageIcon("src/images/triangle.png"));
        toggleButtonToolHearth.addActionListener(controller);
        toggleButtonToolHearth.setIcon(new ImageIcon("src/images/heath.png"));


    }

    public JToggleButton getToggleButtonToolText() {
        return toggleButtonToolText;
    }


    public JToggleButton getToggleButtonToolPipette() {
        return toggleButtonToolPipette;
    }

    public JToggleButton getToggleButtonToolSelect() {
        return toggleButtonToolSelect;
    }

    public JToggleButton getToggleButtonToolCut() {
        return toggleButtonToolCut;
    }

    public JToggleButton getToggleButtonToolFill() {
        return toggleButtonToolFill;
    }

    public JToggleButton getToggleButtonToolMove() {
        return toggleButtonToolHand;
    }

    public JToggleButton getToggleButtonToolDraw() {
        return toggleButtonToolDraw;
    }

    public JToggleButton getToggleButtonToolPaint() {
        return toggleButtonToolPaint;
    }

    public JToggleButton getToggleButtonToolRectangle() {
        return toggleButtonToolRectangle;
    }

    public JToggleButton getToggleButtonToolOval() {
        return toggleButtonToolOval;
    }

    public JToggleButton getToggleButtonToolLine() {
        return toggleButtonToolLine;
    }

    public JToggleButton getToggleButtonToolClear() {
        return toggleButtonToolClear;
    }


    //#TODO add

    public JToggleButton getToggleButtonToolStar() {
        return toggleButtonToolStar;
    }

    public JToggleButton getToggleButtonToolHexagon() {
        return toggleButtonToolHexagon;
    }

    public JToggleButton getToggleButtonToolParallelogram() {
        return toggleButtonToolParallelogram;
    }

    public JToggleButton getToggleButtonToolPentagon() {
        return toggleButtonToolPentagon;
    }

    public JToggleButton getToggleButtonToolTrapezoid() {
        return toggleButtonToolTrapezoid;
    }

    public JToggleButton getToggleButtonToolTriangle() {
        return toggleButtonToolTriangle;
    }

    public JToggleButton getToggleButtonToolHearth() {
        return toggleButtonToolHearth;
    }


    public JButton getButtonToolColor() {
        return buttonToolColor;
    }

    public JSlider getSliderToolSize() {
        return sliderToolSize;
    }

    public JDialog getCcView() {
        return dialogView;
    }

    public Color getColorBlack() {
        return colorBlack;
    }

    public void setColorChooser(Color color) {
        colorBlack = color;
        controller.setCol(colorBlack);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        Color color1 = new Color(66, 64, 64, 255);
        Color color2 = new Color(255, 255, 255, 255);
        Color color3;
        graphics2D.setColor(color2);
        for (int i = 0; i < buttonToolColor.getHeight(); i += 10) {
            color3 = graphics2D.getColor();
            for (int j = 0; j < buttonToolColor.getWidth(); j += 10) {
                graphics2D.fillRect(j, i, 10, 10);
                if (graphics2D.getColor() == color2)
                    graphics2D.setColor(color1);
                else
                    graphics2D.setColor(color2);
            }
            if (color3 == color1)
                graphics2D.setColor(color2);
            else
                graphics2D.setColor(color1);
        }
        graphics2D.setColor(colorBlack);
        graphics2D.fillRect(0, 0, buttonToolColor.getWidth(), buttonToolColor.getHeight());
        buttonToolColor.setIcon(new ImageIcon(bufferedImage));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        buttonToolColor = new JButton();
        toggleButtonToolText = new JToggleButton();
        toggleButtonToolHand = new JToggleButton();
        toggleButtonToolSelect = new JToggleButton();
        toggleButtonToolDraw = new JToggleButton();
        toggleButtonToolPaint = new JToggleButton();
        toggleButtonToolClear = new JToggleButton();
        sliderToolSize = new JSlider();
        labelToolSize = new JLabel();
        toggleButtonToolFill = new JToggleButton();
        toggleButtonToolCut = new JToggleButton();
        toggleButtonToolPipette = new JToggleButton();
        toggleButtonToolRectangle = new JToggleButton();
        toggleButtonToolOval = new JToggleButton();
        toggleButtonToolLine = new JToggleButton();


        toggleButtonToolStar = new JToggleButton();
        toggleButtonToolHexagon = new JToggleButton();
        toggleButtonToolParallelogram = new JToggleButton();

        toggleButtonToolPentagon = new JToggleButton();
        toggleButtonToolTrapezoid = new JToggleButton();
        toggleButtonToolTriangle = new JToggleButton();

        toggleButtonToolHearth = new JToggleButton();
        //#TODO add more primitives


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        for (JToggleButton jToggleButton : Arrays.asList(toggleButtonToolText, toggleButtonToolHand, toggleButtonToolSelect, toggleButtonToolDraw, toggleButtonToolPaint, toggleButtonToolClear)) {
            jToggleButton.setMaximumSize(new Dimension(45, 40));
            jToggleButton.setMinimumSize(new Dimension(45, 40));
            jToggleButton.setPreferredSize(new Dimension(45, 40));
        }

        sliderToolSize.setMinimum(2);
        sliderToolSize.setValue(10);
        sliderToolSize.setMaximumSize(new Dimension(137, 26));
        sliderToolSize.setMinimumSize(new Dimension(137, 26));
        sliderToolSize.setPreferredSize(new Dimension(137, 26));
        sliderToolSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                toolSizeStateChanged(evt);
            }
        });

        labelToolSize.setFont(new Font("Times New Roman", 0, 10));
        labelToolSize.setHorizontalAlignment(SwingConstants.CENTER);
        labelToolSize.setText("10");
        labelToolSize.setMaximumSize(new Dimension(18, 26));
        labelToolSize.setMinimumSize(new Dimension(18, 26));
        labelToolSize.setPreferredSize(new Dimension(18, 26));

        //#TODO add more primitives
        for (JToggleButton jToggleButton : Arrays.asList(toggleButtonToolRectangle, toggleButtonToolOval, toggleButtonToolLine, toggleButtonToolFill, toggleButtonToolCut, toggleButtonToolStar, toggleButtonToolHexagon, toggleButtonToolParallelogram, toggleButtonToolPentagon, toggleButtonToolTrapezoid, toggleButtonToolTriangle, toggleButtonToolHearth)) {
            jToggleButton.setMaximumSize(new Dimension(45, 40));
            jToggleButton.setMinimumSize(new Dimension(45, 40));
            jToggleButton.setPreferredSize(new Dimension(45, 40));
        }


        toggleButtonToolPipette.setMaximumSize(new Dimension(45, 40));
        toggleButtonToolPipette.setMinimumSize(new Dimension(45, 40));
        toggleButtonToolPipette.setPreferredSize(new Dimension(45, 40));


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(sliderToolSize, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelToolSize, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(buttonToolColor, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(toggleButtonToolFill, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolHearth, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolPentagon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolStar, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolRectangle, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(toggleButtonToolDraw, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(toggleButtonToolHand, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(toggleButtonToolText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(toggleButtonToolPaint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(toggleButtonToolOval, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(toggleButtonToolHexagon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolTrapezoid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolCut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)



                                                                )

                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(toggleButtonToolSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolLine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolParallelogram, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolTriangle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(toggleButtonToolPipette, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                                                )


                                                        ))


                                                .addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));


        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

                                        .addComponent(toggleButtonToolText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolHand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

                                        .addComponent(toggleButtonToolDraw, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolPaint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)

                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addComponent(toggleButtonToolRectangle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolOval, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolLine, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)


                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addComponent(toggleButtonToolStar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolHexagon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolParallelogram, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)


                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addComponent(toggleButtonToolPentagon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolTrapezoid, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toggleButtonToolTriangle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))

                                .addGap(18, 18, 18)


                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                                        .addComponent(toggleButtonToolHearth, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                //#TODO add more !!!!

                                )

                                .addGap(18, 18, 18)


                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

                                                .addComponent(toggleButtonToolFill, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(toggleButtonToolCut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(toggleButtonToolPipette, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))


                                .addGap(18, 18, 18)
                                .addGap(40, 40, 40)

                                .addComponent(buttonToolColor, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelToolSize, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sliderToolSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())



        );

        pack();
    }

    private void toolSizeStateChanged(javax.swing.event.ChangeEvent evt) {
        labelToolSize.setText(String.valueOf(sliderToolSize.getValue()));
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToolsPanelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToolsPanelView dialog = new ToolsPanelView(new javax.swing.JFrame(), true);
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
