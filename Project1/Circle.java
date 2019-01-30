import java.awt.*;

public class Circle {
    private double xPos = 0;
    private double yPos = 0;
    private double radius = 0;
    private Color cirCol = Color.black;

    // constructor
    public Circle(double x, double y, double r) {
        xPos = x;
        yPos = y;
        radius = r;
    }

    // setter methods
    public double calculatePerimeter() {
        double circum = 2*Math.PI*radius;
        return circum;
    }

    public double calculateArea() {
        double area = Math.PI*(radius*radius);
        return area;
    }

    public void setColor(Color col) {
        cirCol = col;
    }

    public void setPos(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public void setRadius(double r) {
        radius = r;
    }

    // getter methods
    public Color getColor() { return cirCol;}

    public double getXPos() {return xPos;}

    public double getYPos() {return yPos;}

    public double getRadius() {return radius;}


}