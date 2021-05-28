package shape;

import java.awt.*;
import java.awt.geom.Path2D;

public class Trapezoid {

    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;
    private Graphics2D graphics2D;

    public Trapezoid(int x, int y, int width,int height, Color color,Graphics2D graphics2D) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.graphics2D=graphics2D;

        drawTrapezoid();
    }

    public void drawTrapezoid(){
        Path2D.Double trapezoid = new Path2D.Double();
        trapezoid.moveTo(x,y+height);
        trapezoid.lineTo(x+width,y+height);
        trapezoid.lineTo(x+width*.75,y);
        trapezoid.lineTo(x+width*.25,y);
        trapezoid.lineTo(x,y+height);
        graphics2D.setColor(color);
        graphics2D.fill(trapezoid);
    }
}