/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabbedPanes extends JTabbedPane {
    private BufferedImage background = null;

    public TabbedPanes() {
        File file = new File("src/images/background.png");
        try {
            background = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        BufferedImage image = new BufferedImage(this.getWidth() - 4, this.getHeight() - 4, background.getType());
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.drawImage(background, 0, 0, this.getWidth() - 4, this.getHeight() - 4, null);
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.dispose();
        graphics.drawImage(image, 2, 2, null);
        super.paintComponent(graphics);
    }
}
