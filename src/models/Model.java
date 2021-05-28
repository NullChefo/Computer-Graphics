package models;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JSpinner;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import views.ImagePanel;


public class Model extends Observable {
    private ConcurrentHashMap<String,Class> classes = null;

    public Model(Observer o) {
        this.addObserver(o);
    }

    public void sendNotification(Object o) {
        setChanged();
        notifyObservers(o);
    }



    public ConcurrentHashMap<String,Class> getClasses() {
        return classes;
    }

    public void zoomImg(JSpinner spinner, ImagePanel image, float zoom) {
        image.setZoom(zoom);
        image.updateZoomImg();
        this.sendNotification(spinner);
    }


    public void fillImageColor(BufferedImage img, int ARGB) {
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++)
                img.setRGB(i, j, ARGB);
    }

    public void fillDetect(BufferedImage bufferedImage, int fillX, int fillY, int argb, int ARGB) {
        LinkedList<Point> points = new LinkedList();
        if (fillX >= 0 && fillX <= bufferedImage.getWidth()-1 &&
                fillY >= 0 && fillY <= bufferedImage.getHeight()-1)
            points.add(new Point(fillX, fillY));
        while (!points.isEmpty()) {
            Point p = points.removeFirst();
            if (p.getX() >= 0 && p.getX() <= bufferedImage.getWidth()-1 &&
                    p.getY() >= 0 && p.getY() <= bufferedImage.getHeight()-1 &&
                    bufferedImage.getRGB((int)p.getX(), (int)p.getY()) == argb) {
                bufferedImage.setRGB((int)p.getX(), (int)p.getY(), ARGB);
                points.addLast(new Point((int)p.getX(),(int)p.getY()-1));
                points.addLast(new Point((int)p.getX(),(int)p.getY()+1));
                points.addLast(new Point((int)p.getX()+1,(int)p.getY()));
                points.addLast(new Point((int)p.getX()-1,(int)p.getY()));
            }
        }
    }
}
