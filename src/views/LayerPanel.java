package views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.JPanel;

public class LayerPanel extends JPanel implements Serializable {
    private int localX;

    private int localY;

    private int localW;

    private int localH;

    private transient BufferedImage image;

    private int[] pixels;

    private String fileName;

    private boolean isShowed = true;

    public LayerPanel(BufferedImage img, String n, int lx, int ly) {
        image = img;
        fileName = n;
        localX = lx;
        localY = ly;
    }

    public void resetDim() {
        localW = image.getWidth();
        localH = image.getHeight();
    }

    public void setIsShowed(boolean b) {
        isShowed = b;
    }

    public boolean getIsShowed() {
        return isShowed;
    }

    public LayerPanel getLayerPanelCopy() {
        LayerPanel layerCopy = new LayerPanel(image,fileName, localX, localY);
        layerCopy.setIsShowed(isShowed);
        layerCopy.ImageToPixels();
        return layerCopy;
    }

    public LayerPanel getLaySavedCopy() {
        BufferedImage layerImage = new BufferedImage(localW, localH, BufferedImage.TYPE_4BYTE_ABGR);
        layerImage.setRGB(0, 0, localW, localH, pixels, 0, localW);
        LayerPanel layer = new LayerPanel(layerImage,fileName, localX, localY);
        layer.setIsShowed(isShowed);
        return layer;
    }

    public void ImageToPixels() {
        localW = image.getWidth();
        localH = image.getHeight();
        pixels = image.getRGB(0, 0, localW, localH, null, 0, localW);
    }

    public void PixelsToImage() {
        image = new BufferedImage(localW, localH, BufferedImage.TYPE_4BYTE_ABGR);
        image.setRGB(0, 0, localW, localH, pixels, 0, localW);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage img) {
        image = img;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String n) {
        fileName = n;
    }

    public int getLocalX() {
        return localX;
    }

    public void setLocalX(int lx) {
        localX = lx;
    }

    public int getLocalY() {
        return localY;
    }

    public void setLocalY(int ly) {
        localY = ly;
    }

    public void incrLocalX() {
        localX++;
    }

    public void decrLocalX() {
        localX--;
    }

    public void incrLocalY() {
        localY++;
    }

    public void decrLocalY() {
        localY--;
    }

    public int getLocalW() {
        return localW;
    }

    public int getLocalH() {
        return localH;
    }

    public BufferedImage getLayIcon() {
        BufferedImage ico = new BufferedImage(50,50,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = ico.createGraphics();

        Color c1 = new Color(102,102,102,255);
        Color c2 = new Color(153,153,153,255);
        Color c3;
        graphics2D.setColor(c2);
        for (int i = 0; i < 50; i+=10) {
            c3 = graphics2D.getColor();
            for (int j = 0; j < 50; j+=10) {
                graphics2D.fillRect(j, i, 10, 10);
                if (graphics2D.getColor() == c2)
                    graphics2D.setColor(c1);
                else
                    graphics2D.setColor(c2);
            }
            if (c3 == c1)
                graphics2D.setColor(c2);
            else
                graphics2D.setColor(c1);
        }

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.drawImage(image,0,0, 50, 50, null);
        graphics2D.dispose();
        return ico;
    }
}
