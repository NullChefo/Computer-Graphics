package shape;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse {

    private int x,y,w,h,angle;
    private Color color;
    private Graphics2D graphics2D;


    public Ellipse(int x, int y, int w, int h, int angle, Color color, Graphics2D graphics2D) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.angle=angle;
        this.h=h;
        this.color=color;
        this.graphics2D=graphics2D;
        createEllipse();

    }


    public void createEllipse() {

            Graphics2D g2d = (Graphics2D) graphics2D.create();
            g2d.rotate(Math.toRadians(angle), x + w, y + y / 2);

            g2d.setColor(color);
            g2d.fill(new Ellipse2D.Float(x, y, w, h));

        }
    }

