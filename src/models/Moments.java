package models;

import java.io.Serializable;
import views.ImagePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class Moments implements Serializable {
    private String state;
    private ImagePanel image;
    private int[] iconPix;

    public Moments(String state, ImagePanel imageSave, BufferedImage imageIcon) {
        this.state = state;
        image = imageSave;

        BufferedImage bufferedImage = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        Color color1 = new Color(102,102,102,255);
        Color color2 = new Color(153,153,153,255);
        Color color3;
        graphics2D.setColor(color2);
        for (int i = 0; i < 50; i+=10) {
            color3 = graphics2D.getColor();
            for (int j = 0; j < 50; j+=10) {
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
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.drawImage(imageIcon,0,0, 50, 50, null);
        graphics2D.dispose();
        iconPix = bufferedImage.getRGB(0, 0, 50, 50, null, 0, 50);
    }

    public Moments(String state, ImagePanel imageSave, int[] pix) {
        this.state = state;
        image = imageSave;
        iconPix = pix;
    }

    public String getSavedState() {
        return state;
    }

    public ImagePanel getSavedImg() {
        return image;
    }

    public int[] getIconPix() {
        return iconPix;
    }

    public BufferedImage getIcon() {
        BufferedImage ico = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
        ico.setRGB(0, 0, 50, 50, iconPix, 0, 50);
        return ico;
    }
}
