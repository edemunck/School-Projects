import java.awt.*;

public class Rectangle {
    private double xPos = 0;
    private double yPos = 0;
    private double width = 0;
    private double height = 0;
    private Color cirCol = Color.black;

    // constructor
    public Rectangle(double x, double y, double w, double h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
    }

    public double calculatePerimeter() {
        double perimeter = width*2 + height*2;
        return perimeter;
    }

    public double calculateArea() {
        double area = width*height;
        return area;
    }

    // setters
    public void setColor(Color col) {
        cirCol = col;
    }

    public void setPos(double x, double y) {
        xPos = x;
        yPos = y;}

    public void setHeight(double h) {
        height = h;
    }

    public void setWidth(double w) {
        width = w;
    }

    // getters
    public Color getColor() {return cirCol;}

    public double getXPos() {return xPos;}

    public double getYPos() {return yPos;}

    public double getHeight() {return height;}

    public double getWidth() {return width;}
}