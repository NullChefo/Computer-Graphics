package shape;

import java.awt.*;

public class Pentagon {

    private final int radius;
    private final Point center;
    private final Polygon pentagon;


    public Pentagon(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.pentagon = createPentagon();

    }
    private Polygon createPentagon() {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 5D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 5D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }

    public Polygon getPentagon() {
        return pentagon;
    }


}