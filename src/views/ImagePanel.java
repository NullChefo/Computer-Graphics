package views;

import controller.Controller;
import models.CareTaker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * We give you this class to help you display images.
 * You are free to use it or not, to modify it.
 */
public class ImagePanel extends JPanel implements Serializable {
    private static final long serialVersionUID = -314171089120047242L;
    private String fileName;
    private String filePath;
    private ArrayList<LayerPanel> layers;
    private transient BufferedImage imageRot = null;
    private transient BufferedImage imageCol = null;
    private transient int rotX;
    private transient int rotY;
    private transient int textX;
    private transient int textY;
    private float zoom;
    private transient Thread th = null;
    private transient boolean hasRot = false;
    private transient boolean hasResize = false;
    private transient boolean hasText = false;
    private transient boolean newproj = true;
    private transient boolean resizeProj = true;
    private transient boolean rotateProj = true;
    private transient boolean hasMove = false;
    private transient boolean hasRectangle = false;
    //#TODO 2 add here hasShape
    private transient boolean hasOval = false;
    private transient boolean hasCircle3 = false;


    private transient boolean hasStar = false;
    private transient boolean hasHexagon = false;
    private transient boolean hasParallelogram = false;
    private transient boolean hasPentagon = false;
    private transient boolean hasTrapezoid = false;
    private transient boolean hasTriangle = false;
    private transient boolean hasHearth = false;


    private transient boolean hasLine = false;
    private transient boolean hasCut = false;
    private transient boolean hasSelect = false;
    private transient boolean hasBalance = false;
    private transient boolean hasBright = false;
    private int imageW;
    private int imageH;
    private int nbLayers = 0;
    private int indexSelect = 0;
    private transient Controller control;
    private CareTaker caretaker;
    private transient int cursorX = 0;
    private transient int cursorY = 0;
    private transient int initX = 0;
    private transient int initY = 0;

    public ImagePanel(Controller ctr, CareTaker ct) {
        control = ctr;
        caretaker = ct;
    }

    public ImagePanel(BufferedImage image, String name, Controller ctr) {
        control = ctr;
        caretaker = new CareTaker();
        layers = new ArrayList();
        fileName = name;
        filePath = name;
        imageW = image.getWidth();
        imageH = image.getHeight();
        LayerPanel lay = new LayerPanel(image, fileName, 0, 0);
        layers.add(lay);
        zoom = 100;
        this.setPreferredSize(new Dimension(imageW + 4, imageH + 4));
        this.setSize(new Dimension(imageW + 4, imageH + 4));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 3, 3, false));
    }

    public ImagePanel(File file, Controller ctr) {
        control = ctr;
        caretaker = new CareTaker();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
            fileName = file.getName();
            filePath = file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        layers = new ArrayList();
        imageW = bufferedImage.getWidth();
        imageH = bufferedImage.getHeight();
        BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_4BYTE_ABGR);
        image.getGraphics().drawImage(bufferedImage, 0, 0, null);
        LayerPanel lay = new LayerPanel(image, file.getName(), 0, 0);
        layers.add(lay);
        zoom = 100;
        this.setPreferredSize(new Dimension(imageW + 4, imageH + 4));
        this.setSize(new Dimension(imageW + 4, imageH + 4));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 3, 3, false));
    }

    public boolean getHasBalance() {
        return hasBalance;
    }

    public void setHasBalance(boolean b) {
        hasBalance = b;
    }

    public boolean getHasBright() {
        return hasBright;
    }

    public void setHasBright(boolean b) {
        hasBright = b;
    }

    public BufferedImage getImgCol() {
        return imageCol;
    }

    public void setImgCol(BufferedImage img) {
        imageCol = img;
    }

    public void resetSelectLay() {
        indexSelect = 0;
    }

    public void buildLayersPix() {
        for (LayerPanel lp : layers) {
            lp.ImageToPixels();
        }
    }

    public void buildLayersImg() {
        for (LayerPanel lp : layers) {
            lp.PixelsToImage();
        }
    }

    public boolean getHasSelect() {
        return hasSelect;
    }

    public void setHasSelect(boolean b) {
        hasSelect = b;
    }

    public boolean getHasMove() {
        return hasMove;
    }

    public void setHasMove(boolean b) {
        hasMove = b;
    }

    public boolean getHasCut() {
        return hasCut;
    }

    public void setHasCut(boolean b) {
        hasCut = b;
    }

    public boolean getHasRect() {
        return hasRectangle;
    }

    public void setHasRect(boolean b) {
        hasRectangle = b;
    }


    //#TODO 3 --> From controller setHasShape

    public boolean getHasOval() {
        return hasOval;
    }

    public void setHasOval(boolean b) {
        hasOval = b;
    }

    public void setHasStar(boolean b) {
        hasStar = b;
    }

    public void setHasHexagon(boolean b) {
        hasHexagon = b;
    }

    public void setHasParallelogram(boolean b) {
        hasParallelogram = b;
    }

    public void setHasPentagon(boolean b) {
        hasPentagon = b;
    }

    public void setHasTrapezoid(boolean b) {
        hasTrapezoid = b;
    }

    public void setHasTriangle(boolean b) {
        hasTriangle = b;
    }

    public void setHasHearth(boolean b) {
        hasHearth = b;
    }

    public void setHasCircle3(boolean b) {
        hasCircle3 = b;
    }

    public boolean getHasLine() {
        return hasLine;
    }

    public void setHasLine(boolean b) {
        hasLine = b;
    }

    public int getCursorX() {
        return cursorX;
    }

    public void setCursorX(int cursorX) {
        this.cursorX = cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public void setCursorY(int cursorY) {
        this.cursorY = cursorY;
    }

    public int getInitX() {
        return initX;
    }

    public void setInitX(int cursorX) {
        initX = cursorX;
    }

    public int getInitY() {
        return initY;
    }

    public void setInitY(int cursorY) {
        initY = cursorY;
    }

    public int getIndexSelect() {
        return indexSelect;
    }

    public void setIndexSelect(int i) {
        indexSelect = i;
    }

    public ImagePanel getImagePanelCopy() {
        ImagePanel imageCopy = new ImagePanel(control, caretaker);
        imageCopy.addMouseListener(control);
        imageCopy.addMouseWheelListener(control);
        imageCopy.setFileName(fileName);
        imageCopy.setFilePath(filePath);
        imageCopy.setCopyLayers(layers);
        imageCopy.resetSelectLay();
        imageCopy.setZoom(zoom);
        imageCopy.resetDims(imageW, imageH);
        imageCopy.setNbLayers(nbLayers);
        imageCopy.setIndexSelect(indexSelect);
        imageCopy.setInitX(initX);
        imageCopy.setInitY(initY);
        imageCopy.setCursorX(cursorX);
        imageCopy.setCursorY(cursorY);
        imageCopy.setPreferredSize(new Dimension(imageW + 4, imageH + 4));
        imageCopy.setSize(new Dimension(imageW + 4, imageH + 4));
        imageCopy.setOpaque(false);
        imageCopy.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 3, 3, false));
        return imageCopy;
    }

    public ImagePanel getImgSavedCopy() {
        ImagePanel imageCopy = new ImagePanel(control, caretaker);
        imageCopy.addMouseListener(control);
        imageCopy.addMouseWheelListener(control);
        imageCopy.setFileName(fileName);
        imageCopy.setFilePath(filePath);
        imageCopy.setSavedLayers(layers);
        imageCopy.resetSelectLay();
        imageCopy.setZoom(zoom);
        imageCopy.resetDims(imageW, imageH);
        imageCopy.setNbLayers(nbLayers);
        imageCopy.setIndexSelect(indexSelect);
        imageCopy.setInitX(initX);
        imageCopy.setInitY(initY);
        imageCopy.setCursorX(cursorX);
        imageCopy.setCursorY(cursorY);
        imageCopy.setPreferredSize(new Dimension(imageW + 4, imageH + 4));
        imageCopy.setSize(new Dimension(imageW + 4, imageH + 4));
        imageCopy.setOpaque(false);
        imageCopy.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 3, 3, false));
        return imageCopy;
    }

    public void setCopyLayers(ArrayList<LayerPanel> lays) {
        layers = new ArrayList();
        for (LayerPanel layerPanel : lays)
            layers.add(layerPanel.getLayerPanelCopy());
    }

    public void setSavedLayers(ArrayList<LayerPanel> lays) {
        layers = new ArrayList();
        for (LayerPanel layerPanel : lays)
            layers.add(layerPanel.getLaySavedCopy());
    }

    public int getNbLayers() {
        return nbLayers;
    }

    public void setNbLayers(int nb) {
        nbLayers = nb;
    }

    public CareTaker getCareTaker() {
        return caretaker;
    }

    public void resetDims(int rw, int rh) {
        imageW = rw;
        imageH = rh;
    }

    public void newLayer(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);
        LayerPanel lay = new LayerPanel(bufferedImage, file.getName(), imageW / 2 - image.getWidth() / 2, imageH / 2 - image.getHeight() / 2);
        indexSelect = layers.size();
        layers.add(lay);
    }

    public void newLayer(BufferedImage img) {
        nbLayers++;
        LayerPanel layer = new LayerPanel(img, "newLayer" + nbLayers, imageW / 2 - img.getWidth() / 2, imageH / 2 - img.getHeight() / 2);
        indexSelect = layers.size();
        layers.add(layer);
    }

    public void newLayer(BufferedImage img, String layerName) {
        LayerPanel lay = new LayerPanel(img, layerName, imageW / 2 - img.getWidth() / 2, imageH / 2 - img.getHeight() / 2);
        indexSelect = layers.size();
        layers.add(lay);
    }

    public void newLayer(BufferedImage img, int locX, int locY) {
        nbLayers++;
        LayerPanel layer = new LayerPanel(img, "newLayer" + nbLayers, locX, locY);
        indexSelect = layers.size();
        layers.add(layer);
    }

    public void rotateSimule(float ang) {
        if (rotateProj) {

        } else {
            double rot = Math.toRadians(ang);
            double s = Math.abs(Math.sin(rot));
            double c = Math.abs(Math.cos(rot));
            int w = layers.get(indexSelect).getImage().getWidth();
            int h = layers.get(indexSelect).getImage().getHeight();
            int nw = (int) Math.floor(w * c + h * s);
            int nh = (int) Math.floor(h * c + w * s);
            imageRot = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR);
            int r = 255;
            int g = 255;
            int b = 255;
            int a = 0;
            int col = (a << 24) | (r << 16) | (g << 8) | b;
            for (int i = 0; i < nw; i++)
                for (int j = 0; j < nh; j++)
                    imageRot.setRGB(i, j, col);
            Graphics2D graphics2D = imageRot.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.translate((nw - w) / 2, (nh - h) / 2);
            graphics2D.rotate(rot, w / 2, h / 2);
            graphics2D.drawRenderedImage(layers.get(indexSelect).getImage(), null);
            rotX = layers.get(indexSelect).getLocalX() - (nw - w) / 2;
            rotY = layers.get(indexSelect).getLocalY() - (nh - h) / 2;
            graphics2D.dispose();
        }

        this.repaint();
    }

    public void rotateExec(float ang) {
        if (rotateProj) {
            double rot = Math.toRadians(ang);
            double s = Math.abs(Math.sin(rot));
            double c = Math.abs(Math.cos(rot));
            int w, h, nw, nh, argb;
            BufferedImage layerBuffer;
            Graphics2D graphics2D;
            argb = new Color(255, 255, 255, 0).getRGB();
            nw = (int) Math.floor(imageW * c + imageH * s);
            nh = (int) Math.floor(imageH * c + imageW * s);
            imageW = nw;
            imageH = nh;

            for (LayerPanel layerPanel : layers) {
                layerBuffer = layerPanel.getImage();
                w = layerBuffer.getWidth();
                h = layerBuffer.getHeight();
                nw = (int) Math.floor(w * c + h * s);
                nh = (int) Math.floor(h * c + w * s);
                imageRot = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR);
                for (int i = 0; i < nw; i++)
                    for (int j = 0; j < nh; j++)
                        imageRot.setRGB(i, j, argb);
                graphics2D = imageRot.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.translate((nw - w) / 2, (nh - h) / 2);
                graphics2D.rotate(rot, w / 2, h / 2);
                graphics2D.drawRenderedImage(layerBuffer, null);
                graphics2D.dispose();
                layerPanel.setImage(imageRot);
            }
            this.updateZoomImg();
            this.getParent().getParent().validate();
        } else {
            double rot = Math.toRadians(ang);
            double s = Math.abs(Math.sin(rot));
            double c = Math.abs(Math.cos(rot));
            int w = layers.get(indexSelect).getImage().getWidth();
            int h = layers.get(indexSelect).getImage().getHeight();
            int nw = (int) Math.floor(w * c + h * s);
            int nh = (int) Math.floor(h * c + w * s);
            imageRot = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR);
            int r = 255;
            int g = 255;
            int b = 255;
            int a = 0;
            int col = (a << 24) | (r << 16) | (g << 8) | b;
            for (int i = 0; i < nw; i++)
                for (int j = 0; j < nh; j++)
                    imageRot.setRGB(i, j, col);
            Graphics2D graphics2D = imageRot.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.translate((nw - w) / 2, (nh - h) / 2);
            graphics2D.rotate(rot, w / 2, h / 2);
            graphics2D.drawRenderedImage(layers.get(indexSelect).getImage(), null);
            graphics2D.dispose();
            layers.get(indexSelect).setLocalX(layers.get(indexSelect).getLocalX() - (nw - w) / 2);
            layers.get(indexSelect).setLocalY(layers.get(indexSelect).getLocalY() - (nh - h) / 2);
            layers.get(indexSelect).setImage(imageRot);
        }
        this.updateZoomImg();
        this.getParent().getParent().validate();
    }

    public void resizeExec(int nw, int nh) {
        if (resizeProj) {
            double ratioW = (double) nw / (double) imageW;
            double ratioH = (double) nh / (double) imageH;
            imageW = nw;
            imageH = nh;
            int nx, lw;
            int ny, lh;
            BufferedImage newImage;
            Graphics2D graphics2D;
            for (LayerPanel layerPanel : layers) {
                lw = layerPanel.getImage().getWidth();
                lh = layerPanel.getImage().getHeight();
                nw = (int) ((double) lw * ratioW);
                nh = (int) ((double) lh * ratioH);
                layerPanel.setLocalX((int) ((double) layerPanel.getLocalX() * ratioW));
                layerPanel.setLocalY((int) ((double) layerPanel.getLocalY() * ratioH));
                newImage = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR);
                graphics2D = newImage.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.drawImage(layerPanel.getImage(), 0, 0, nw, nh, null);
                graphics2D.dispose();
                layerPanel.setImage(newImage);
            }
            this.updateZoomImg();
            this.getParent().getParent().validate();
        } else {
            BufferedImage newImage = new BufferedImage(nw, nh, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2D = newImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.drawImage(layers.get(indexSelect).getImage(), 0, 0, nw, nh, null);
            graphics2D.dispose();
            layers.get(indexSelect).setImage(newImage);
            this.updateZoomImg();
            this.getParent().getParent().validate();
        }
    }

    public void updateZoomImg() {
        this.setPreferredSize(new Dimension((int) ((float) imageW * zoom / 100) + 4, (int) ((float) imageH * zoom / 100) + 4));
        this.setSize(new Dimension((int) ((float) imageW * zoom / 100) + 4, (int) ((float) imageH * zoom / 100) + 4));

    }

    public void updateRotLoc() {
        int w = layers.get(indexSelect).getImage().getWidth();
        int h = layers.get(indexSelect).getImage().getHeight();
        int nw = imageRot.getWidth();
        int nh = imageRot.getHeight();
        rotX = layers.get(indexSelect).getLocalX() - (nw - w) / 2;
        rotY = layers.get(indexSelect).getLocalY() - (nh - h) / 2;
    }

    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        int size = (int) ((float) control.getMainView().getToolsView().getSliderToolSize().getValue() * zoom / 100);
        Color color1 = new Color(102, 102, 102, 255);
        Color color2 = new Color(153, 153, 153, 255);
        Color color3;
        g.setColor(color2);
        for (int i = 0; i < this.getHeight(); i += 10) {
            color3 = g.getColor();
            for (int j = 0; j < this.getWidth(); j += 10) {
                g.fillRect(j, i, 10, 10);
                if (g.getColor() == color2)
                    g.setColor(color1);
                else
                    g.setColor(color2);
            }
            if (color3 == color1)
                g.setColor(color2);
            else
                g.setColor(color1);
        }

        if (hasRot && false) {

        } else {
            if (layers.get(indexSelect) != null) {
                for (LayerPanel layerPanel : layers) {
                    if (layerPanel == layers.get(indexSelect)) {
                        if (layerPanel.getIsShowed()) {
                            if (imageRot != null && hasRot) {
                                g.drawImage(imageRot, (int) ((float) rotX * zoom / 100) + 2, (int) ((float) rotY * zoom / 100) + 2, (int) ((float) imageRot.getWidth() * zoom / 100), (int) ((float) imageRot.getHeight() * zoom / 100), null);
                                g.setColor(Color.BLACK);
                                g.drawRect((int) ((float) rotX * zoom / 100), (int) ((float) rotY * zoom / 100), (int) ((float) imageRot.getWidth() * zoom / 100) + 3, (int) ((float) imageRot.getHeight() * zoom / 100) + 3);
                                g.drawRect((int) ((float) rotX * zoom / 100) + 1, (int) ((float) rotY * zoom / 100) + 1, (int) ((float) imageRot.getWidth() * zoom / 100) + 1, (int) ((float) imageRot.getHeight() * zoom / 100) + 1);
                            } else if (imageCol != null && (hasBalance || hasBright)) {
                                g.drawImage(imageCol, (int) ((float) layerPanel.getLocalX() * zoom / 100) + 2, (int) ((float) layerPanel.getLocalY() * zoom / 100) + 2, (int) ((float) imageCol.getWidth() * zoom / 100), (int) ((float) imageCol.getHeight() * zoom / 100), null);
                            } else {
                                if (hasMove)
                                    g.drawImage(layerPanel.getImage(), (int) ((float) layerPanel.getLocalX() * zoom / 100) + cursorX - initX + 2, (int) ((float) layerPanel.getLocalY() * zoom / 100) + cursorY - initY + 2, (int) ((float) layerPanel.getImage().getWidth() * zoom / 100), (int) ((float) layerPanel.getImage().getHeight() * zoom / 100), null);
                                else
                                    g.drawImage(layerPanel.getImage(), (int) ((float) layerPanel.getLocalX() * zoom / 100) + 2, (int) ((float) layerPanel.getLocalY() * zoom / 100) + 2, (int) ((float) layerPanel.getImage().getWidth() * zoom / 100), (int) ((float) layerPanel.getImage().getHeight() * zoom / 100), null);
                            }
                        }
                    } else {
                        if (layerPanel.getIsShowed())
                            g.drawImage(layerPanel.getImage(), (int) ((float) layerPanel.getLocalX() * zoom / 100) + 2, (int) ((float) layerPanel.getLocalY() * zoom / 100) + 2, (int) ((float) layerPanel.getImage().getWidth() * zoom / 100), (int) ((float) layerPanel.getImage().getHeight() * zoom / 100), null);
                    }
                }

                g.setColor(Color.RED);
                g.drawRect((int) ((float) layers.get(indexSelect).getLocalX() * zoom / 100), (int) ((float) layers.get(indexSelect).getLocalY() * zoom / 100), (int) ((float) layers.get(indexSelect).getImage().getWidth() * zoom / 100) + 3, (int) ((float) layers.get(indexSelect).getImage().getHeight() * zoom / 100) + 3);
                g.drawRect((int) ((float) layers.get(indexSelect).getLocalX() * zoom / 100) + 1, (int) ((float) layers.get(indexSelect).getLocalY() * zoom / 100) + 1, (int) ((float) layers.get(indexSelect).getImage().getWidth() * zoom / 100) + 1, (int) ((float) layers.get(indexSelect).getImage().getHeight() * zoom / 100) + 1);

                if (hasText) {
                    g.setColor(control.getCol());
                    g.setFont(control.getTextFont());
                    g.drawString(control.getTextAdd(), textX, textY);
                }

                g.setColor(Color.BLACK);
                if (control.getHasPaint()) {
                    g.drawOval(cursorX - size / 2, cursorY - size / 2, size, size);
                } else if (control.getHasDraw() || control.getHasClear()) {
                    g.drawRect(cursorX - size / 2, cursorY - size / 2, size, size);
                } else if (hasRectangle) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //    g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasOval) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //   g.fillOval(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                }

                // #TODO 4 --> after setHasShape  edit maybe  !!! FIX bellow

                else if (hasStar) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //    g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));


                } else if (hasCircle3) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //    g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));


                } else if (hasHexagon) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //    g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));

                } else if (hasParallelogram) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //    g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasPentagon) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //      g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasTrapezoid) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //     g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasTriangle) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //     g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasHearth) {
                    g.setColor(control.getCol());
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    //     g.fillRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                } else if (hasLine) {
                    g.setColor(control.getCol());
                    g.drawLine(initX, initY, cursorX, cursorY);
                } else if (hasCut || hasSelect) {
                    g.setColor(Color.BLACK);
                    int ix = (initX >= cursorX) ? cursorX : initX;
                    int iy = (initY >= cursorY) ? cursorY : initY;
                    g.drawRect(ix, iy, Math.abs(initX - cursorX), Math.abs(initY - cursorY));
                }
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String s) {
        fileName = s;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String s) {
        filePath = s;
    }

    public Thread getThread() {
        return th;
    }

    public void setThread(Thread thr) {
        th = thr;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float z) {
        zoom = z;
    }

    public boolean getHasResize() {
        return hasResize;
    }

    public void setHasResize(boolean hasResize) {
        this.hasResize = hasResize;
    }

    public boolean getHasText() {
        return hasText;
    }

    public void setHasText(boolean b) {
        hasText = b;
    }

    public boolean getHasRot() {
        return hasRot;
    }

    public void setHasRot(boolean hasRoot) {
        hasRot = hasRoot;
    }

    public int getImgW() {
        return imageW;
    }

    public int getImgH() {
        return imageH;
    }

    public ArrayList<LayerPanel> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<LayerPanel> lays) {
        layers = lays;
    }

    public boolean getResizeProj() {
        return resizeProj;
    }

    public void setResizeProj(boolean b) {
        resizeProj = b;
    }

    public boolean getRotateProj() {
        return rotateProj;
    }

    public void setRotateProj(boolean b) {
        rotateProj = b;
    }

    public int getTextX() {
        return textX;
    }

    public void setTextX(int tx) {
        textX = tx;
    }

    public int getTextY() {
        return textY;
    }

    public void setTextY(int ty) {
        textY = ty;
    }

    public BufferedImage getBuiltImage() {
        BufferedImage img = new BufferedImage(imageW, imageH, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g2d = img.createGraphics();
        for (LayerPanel lp : layers) {
            g2d.drawImage(lp.getImage(), lp.getLocalX(), lp.getLocalY(), lp.getImage().getWidth(), lp.getImage().getHeight(), null);
        }
        return img;
    }

    public void cutImg() {
        int ix = (int) ((float) initX * 100 / zoom);
        int fx = (int) ((float) cursorX * 100 / zoom);
        fx = (fx > imageW - 1) ? imageW - 1 : fx;
        fx = (fx < 0) ? 0 : fx;
        int iy = (int) ((float) initY * 100 / zoom);
        int fy = (int) ((float) cursorY * 100 / zoom);
        fy = (fy > imageH - 1) ? imageH - 1 : fy;
        fy = (fy < 0) ? 0 : fy;
        int sx = (ix >= fx) ? fx : ix;
        int sy = (iy >= fy) ? fy : iy;
        imageW = Math.abs(ix - fx);
        imageH = Math.abs(iy - fy);
        this.setPreferredSize(new Dimension(imageW + 4, imageH + 4));
        this.setSize(new Dimension(imageW + 4, imageH + 4));
        for (LayerPanel layer : layers) {
            layer.setLocalX(layer.getLocalX() - sx);
            layer.setLocalY(layer.getLocalY() - sy);
        }
    }
}
