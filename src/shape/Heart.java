package shape;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Heart {

    private Shape shape;


    public Shape Heart(int x, int y, int HEART_RADIUS) {
        double radius = HEART_RADIUS;
        double root2 = Math.sqrt(2);
        double cX = x;
        double cY = y;
        double dX = radius / root2;
        double heights = 3 * dX + radius;
        double top = cY - heights / 2;
        double bottom = cY + heights / 2;

        Path2D base = new Path2D.Double();
        Shape s = new Arc2D.Double(cX - dX - radius, top, 2 * radius, 2 * radius, 45, 180, Arc2D.OPEN);
        base.append(s, false);
        s = new Line2D.Double(cX, bottom, cX - 2 * dX, bottom - 2 * dX);
        base.append(s, true);
        s = new Line2D.Double(cX, bottom, cX + 2 * dX, bottom - 2 * dX);
        base.append(s, true);
        s = new Arc2D.Double(cX + dX - radius, top, 2 * radius, 2 * radius, -45, 180, Arc2D.OPEN);
        base.append(s, true);

        base.closePath();
        shape = base;
        return base;
    }


}