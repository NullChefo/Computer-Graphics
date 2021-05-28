package shape;

import java.awt.*;

public class Triangle {

    private final int radius;

    private final Point center;
    private final Polygon triangle;


    public Triangle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.triangle = createTriangle();

    }
    private Polygon createTriangle() {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 3; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 3D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 3D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }

    public Polygon getTriangle() {
        return triangle;
    }


}