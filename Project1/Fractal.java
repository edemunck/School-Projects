// Elizabeth DeMunck demun004 5358673

import java.awt.*;
import java.util.Scanner;

public class Fractal {
    // I instantiated area here so that it could be used in each method to calculate the area.
    private static double area = 0;

    public static double drawRec(double x, double y, double w, double h, Canvas drawing, Color col) {
        Rectangle myRec = new Rectangle(x,y,w,h);
        // this series of if-else statements changes the color at each level
        if(col.equals(Color.RED)) {
            col = Color.BLUE;
        }
        else if(col.equals(Color.BLUE)) {
            col = Color.YELLOW;
        }
        else {
            col = Color.RED;
        }
        myRec.setColor(col);
        drawing.drawShape(myRec);
        area += myRec.calculateArea();
        // this if statement draws the fractal to a depth of 7
        if(h > 1.5625) {
            drawRec(x+(w/2)+(w), y+(h/4),w/2,h/2, drawing,col);
            drawRec(x-(w), y+(h/4),w/2,h/2, drawing,col);
            drawRec(x+(w/4),y+(h/2)+(h),w/2, h/2,drawing,col);
            drawRec(x+(w/4),y-(h),w/2, h/2,drawing,col);
        }
        return area;
    }

    public static double drawTri(Canvas drawing, double xL, double yL, double w, double h, Color col) {
        Triangle myTri = new Triangle(xL,yL,w, h);
        // this series of if-else statements changes the color at each level
        if(col.equals(Color.RED)) {
            col = Color.BLUE;
        }
        else if(col.equals(Color.BLUE)) {
            col = Color.GREEN;
        }
        else {
            col = Color.RED;
        }
        myTri.setColor(col);
        drawing.drawShape(myTri);
        area += myTri.calculateArea();
        // this if statement draws the fractal to a depth of 7
        if(w > 5) {
            drawTri(drawing,xL+(w), yL,w/2,h/2, col);
            drawTri(drawing,xL-(w/2), yL,w/2,h/2, col);
            drawTri(drawing,xL+(w/4),yL-(h),w/2,h/2, col);
        }
        return area;
    }

    public static double drawCirc(Canvas drawing, double x, double y, double r, Color col) {
        Circle myCirc = new Circle(x,y,r);
        // this series of if-else statements changes the color at each level
        if(col.equals(Color.RED)) {
            col = Color.BLUE;
        }
        else if(col.equals(Color.BLUE)) {
            col = Color.PINK;
        }
        else {
            col = Color.RED;
        }
        myCirc.setColor(col);
        drawing.drawShape(myCirc);
        area += myCirc.calculateArea();
        // this if statement draws the fractal to a depth of 7
        if(r >= 3.125) {
            drawCirc(drawing,x+(r*2),y,r/2, col);
            drawCirc(drawing,x-(r*2),y,r/2, col);
            drawCirc(drawing,x,y+(r*2),r/2, col);
            drawCirc(drawing,x,y-(r*2),r/2, col);
        }
        return area;
    }

    public static void main(String[] args) {
        System.out.println("Enter a shape(circle, triangle or rectangle): ");
        Scanner scan = new Scanner(System.in);
        String shape = scan.nextLine();
        if(!shape.equals("triangle") && !shape.equals("circle") && !shape.equals("rectangle")) { System.out.println("Error, please try again."); }
        else{
            if(shape.equalsIgnoreCase("triangle")) {
                Canvas drawing = new Canvas(1000,2000);
                System.out.println("Total area: " + drawTri(drawing, 800, 900, 300, 300, Color.RED));
            }
            if(shape.equalsIgnoreCase("rectangle")) {
                Canvas drawing = new Canvas(1000,1000);
                System.out.println("Total area: " + drawRec(450, 400, 150, 80, drawing, Color.RED));
            }
            if(shape.equalsIgnoreCase("circle")) {
                Canvas drawing = new Canvas(1000,1000);
                System.out.println("Total area: " + drawCirc(drawing,500,500,100, Color.RED));
            }
        }
    }
}