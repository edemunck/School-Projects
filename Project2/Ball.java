import java.awt.*;

class Ball extends Circle {
    private double speedHz, speedVr, time;
    private Color color;

    public Ball(double x, double y, double r, Color color) {
        super(x,y,r);
        this.color=color;
    }

    public void setSpeedX(double speedHz) {
        // update ball's x speed to input value
        this.speedHz=speedHz; }
    public void setSpeedY(double speedVr) {
        //update ball's y speed to input value
        this.speedVr=speedVr; }
    public double getSpeedX() { return speedHz; }
    public double getSpeedY() { return speedVr; }

    public void setColor(Color color) { this.color = color; }
    public Color getColor() { return color; }


    public void updatePosition(double time) {
        // update ball's position based on its current velocity and the time elapsed
        this.time=time;
        x += time*speedHz;
        y += time*speedVr;
        setPos(x,y);
    }

    public boolean intersect(Ball ball) {
        // check if the passed ball object overlaps with the current ball (helps w collisions)
        double xx = ball.getXPos()-getXPos();
        double yy = ball.getYPos()-getYPos();
        double dist = Math.sqrt(xx*xx+yy*yy);
        if(dist<getRadius()+ball.getRadius()) {
            return true;
        } else { return false; }
    }

    public void collide(Ball ball) {
        // Confirm balls have collided (use intersect)
        // implement physics of collision (new x and y velocities)
        if(intersect(ball)){
            // new velocities
            // change color
            double xx = getXPos()-ball.getXPos();
            double yy = getYPos()-ball.getYPos();
            double dist = Math.sqrt(xx*xx+yy*yy);
            // delta x and delta y
            double xchange = xx/dist;
            double ychange = yy/dist;

            // new coordinate system velocities
            double ball1newColVel = getSpeedX()*xchange + getSpeedY()*ychange;
            double ball1newNormVel = -1*getSpeedX()*ychange + getSpeedY()*xchange;
            double ball2newColVel = ball.getSpeedX()*xchange + ball.getSpeedY()*ychange;
            double ball2newNormVel = -1*ball.getSpeedX()*ychange + ball.getSpeedY()*xchange;

            double f = ball1newColVel;
            ball1newColVel = ball2newColVel;
            ball2newColVel = f;


            // changing back to old coordinate system velocities
            double ball1finalx = ball1newColVel*xchange - ball1newNormVel*ychange;
            double ball1finaly = ball1newColVel*ychange + ball1newNormVel*xchange;
            double ball2finalx = ball2newColVel*xchange - ball2newNormVel*ychange;
            double ball2finaly = ball2newColVel*ychange + ball2newNormVel*xchange;

            // reassigning
            setSpeedX(ball1finalx);
            setSpeedY(ball1finaly);
            ball.setSpeedX(ball2finalx);
            ball.setSpeedY(ball2finaly);

            if(getColor().equals(Color.red)) {
                ball.setColor(Color.red);
            }
            if(ball.getColor().equals(Color.red)) {
                this.setColor(Color.red);
            }
        }
    }
}


