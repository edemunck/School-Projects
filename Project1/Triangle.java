import java.awt.*;

public class Triangle {
    private double xLPos = 0;
    private double yLPos = 0;
    private double width = 0;
    private double height = 0;
    private Color cirCol = Color.black;

    // constructor
    public Triangle(double xL, double yL, double w, double h) {
        xLPos = xL;
        yLPos = yL;
        width = w;
        height = h;
    }

    public double calculatePerimeter() {
        double perimeter = 2*(Math.sqrt((height*height)+(width/2)*(width/2)))+width;
        return perimeter;
    }

    public double calculateArea() {
        double area = (width*height)/2;
        return area;
    }

    // setters
    public void setColor(Color col) {
        cirCol = col;
    }

    public void setPos(double x, double y) {
        xLPos = x;
        yLPos = y;
    }

    public void setHeight(double h) {
        height = h;
    }

    public void setWidth(double w) {
        width = w;
    }

    // getters
    public Color getColor() {return cirCol;}

    public double getXPos() {return xLPos;}

    public double getYPos() {return yLPos;}

    public double getHeight() {return height;}

    public double getWidth() {return width;}
}