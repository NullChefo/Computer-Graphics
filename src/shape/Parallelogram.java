package shape;

import java.awt.*;

public class Parallelogram{

    private int x,y,w,h,angle;
    private Color color;
    private Graphics2D graphics2D;


    public Parallelogram(int x, int y, int w, int h, int angle, Color color,Graphics2D graphics2D) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.angle=angle;
        this.h=h;
        this.color=color;
        this.graphics2D=graphics2D;
        createParallelogram();

    }

    public void createParallelogram () {


        Graphics2D g2d = (Graphics2D) graphics2D.create();

        int[] xAux = {x, x + w/2, x + w, x + w/2};
        int[] yAux = {y, y, y + h , y + h};

        g2d.rotate(Math.toRadians(angle), (xAux[1] + xAux[2])/2,(yAux[0] + yAux[1])/2);

        g2d.setColor(color);
        g2d.fillPolygon(xAux, yAux, 4);


        g2d.drawPolygon(xAux, yAux, 4);
        g2d.dispose();
    }
}
