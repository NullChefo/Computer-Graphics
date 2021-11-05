/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.*;
import java.awt.*;


public class Panels extends JPanel {
    private boolean hasRectangle = false;
    private JButton buttonClose;
    private JLabel title;
    private ImagePanel image;

    public Panels(ImagePanel imagePanel) {
        image = imagePanel;
    }

    public void setRect() {
        hasRectangle = true;
    }

    public void clearRect() {
        hasRectangle = false;
        this.repaint();
    }

    public JButton getButtonClose() {
        return buttonClose;
    }

    public void setButtonClose(JButton but) {
        buttonClose = but;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel label) {
        title = label;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}

