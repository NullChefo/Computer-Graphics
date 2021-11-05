package models;

import views.ImagePanel;

import java.awt.image.BufferedImage;


public class Creator {
    private String state;
    private ImagePanel image;

    public Moments saveToMemento(String st, ImagePanel imageSave, BufferedImage imageIcon) {
        return new Moments(st, imageSave, imageIcon);
    }

    public void restoreFromMemento(Moments moments) {
        state = moments.getSavedState();
        image = moments.getSavedImg();
    }

    public String getState() {
        return state;
    }

    public ImagePanel getImage() {
        return image;
    }
}
