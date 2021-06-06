package shape;

import java.awt.*;
import java.awt.geom.*;

public class Circle3 {

    private int x,y,w,h,radius;
    private Color color;
    private Graphics2D graphics2D;

    public Circle3(int x, int y, int w, int h, Color color, Graphics2D graphics2D,int radius) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.color=color;
        this.graphics2D=graphics2D;
        this.radius=radius;
        createCircle3();
    }

    public void createCircle3() {

        GeneralPath gp = new GeneralPath();
        gp.append(new Ellipse2D.Double(x, y, w, h), true);


        // #TODO fix

        for (double angle = 0; angle < 180; angle += 110) {
            Point2D startPoint = pointOnCircle(angle, radius);
            Point2D endPoint = pointOnCircle(angle + 180, radius);

            gp.moveTo(startPoint.getX(), startPoint.getY());
            gp.lineTo(endPoint.getX(), endPoint.getY());
        }

        Graphics2D g2d = (Graphics2D) graphics2D.create();

        g2d.setColor(color);
        g2d.fill(gp);

        g2d.setColor(Color.white);
        g2d.draw(gp);

    }
    protected Point2D pointOnCircle(double degrees, double radius) {
        double origin = radius;
        double rads = Math.toRadians(degrees);
        double x = origin + (Math.cos(rads) * radius);
        double y = origin + (Math.sin(rads) * radius);

        return new Point2D.Double(x, y);
    }


}

