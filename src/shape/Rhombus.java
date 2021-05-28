package shape;


import java.awt.*;

public class Rhombus {

    private final int radius;
    private final Point center;
    private final Polygon rhombus;


    public Rhombus(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.rhombus = createRhombus();

    }

    private Polygon createRhombus() {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 4; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 4D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 4D));
            polygon.addPoint(xval, yval);
        }

        return rhombus;
    }

    public Polygon getRhombus() {
        return rhombus;
    }


}