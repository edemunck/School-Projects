import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class BallScreenSaver extends AnimationFrame{
    private int ballAmount;
    private final int ballSize = 16;
    private double ballXSpeed, ballYSpeed;
    private Ball[] ballList;
    public final int border = 30;

    // for collisionLogger
    private CollisionLogger collisionLogger;
    private static final int COLLISION_BUCKET_WIDTH = 80;
    private int saveCounter=0;


    public BallScreenSaver(int width, int height, String name, int ballAmount) {
        // outputs object BallScreenSaver
        super(width,height,name);
        this.ballAmount=ballAmount;
        ballList = new Ball[ballAmount];
        // this for loop creates the list of Ball objects
        for(int i = 0; i<ballAmount; i++) {
            Ball b = new Ball(randdouble(border, getWidth() - border),randdouble(border, getHeight()-border),ballSize/2,Color.GREEN);
            b.setColor(Color.GREEN);
            ballList[i] = b;
        }
        // this makes the first ball red, because there will always be at least one ball.
        ballList[0].setColor(Color.red);
        setDirection();
        setFPS(20);
        // collision logger
        collisionLogger = new CollisionLogger(this.getWidth(),this.getHeight(), COLLISION_BUCKET_WIDTH);
    }


    public void setDirection() {
        for(int i = 0; i<ballAmount; i++) {
            double direc = randdouble(0, Math.PI * 2);
            double speed = randInt(5,40);
            ballXSpeed = (Math.cos(direc) * speed);
            ballYSpeed = (Math.sin(direc) * speed);
            ballList[i].setSpeedX(ballXSpeed);
            ballList[i].setSpeedY(ballYSpeed);
        }
    }

    public double randdouble(double min, double max) {
        //a utility method to get a random double between min and max.
        return (Math.random() * (max - min) + min);
    }
    public int randInt(int min, int max){
        //a utility method to get a random int between min and max.
        return (int)(Math.random()*(max-min)+min);
    }


    public void action() {
        for(int i = 0; i<ballAmount; i++){
            ballList[i].updatePosition(1);
            // collisions
            for(int k = i+1; k<ballAmount; k++) {
//                ballList[i].collide(ballList[k]);
                if(ballList[i].intersect(ballList[k])) {
                    ballList[i].collide(ballList[k]);
                    collisionLogger.collide(ballList[i],ballList[k]); }
            }
//            ballList[i].setPos(ballList[i].getXPos()+ballList[i].getSpeedX()/getFPS(),ballList[i].getYPos()+ballList[i].getSpeedY()/getFPS());
            if(ballList[i].getXPos()<border || ballList[i].getXPos()+ballSize>getHeight()-border){
                ballList[i].setSpeedX(ballList[i].getSpeedX()*-1);
            }
            if(ballList[i].getYPos()<border || ballList[i].getYPos()+ballSize>getWidth()-border){
                ballList[i].setSpeedY(ballList[i].getSpeedY()*-1);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.black);
        g.drawRect(border,border,getWidth()-border*2,getHeight()-border*2);
        for(int i = 0; i<ballAmount; i++) {
            g.setColor(ballList[i].getColor());
            g.fillOval((int)ballList[i].getXPos(), (int)ballList[i].getYPos(), ballSize, ballSize);
        }
    }

    public void processKeyEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_RIGHT) {
            for(int i = 0; i<ballAmount; i++) {
                ballList[i].setSpeedX(ballList[i].getSpeedX()*1.1);
                ballList[i].setSpeedY(ballList[i].getSpeedY()*1.1);
            }
        }
        if(e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_LEFT) {
            for(int i = 0; i<ballAmount; i++) {
                ballList[i].setSpeedX(ballList[i].getSpeedX()/1.1);
                ballList[i].setSpeedY(ballList[i].getSpeedY()/1.1);
            }
        }
        if (e.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_P) {
            EasyBufferedImage image = EasyBufferedImage.createImage(collisionLogger.getNormalizedHeatMap());
            try {
                image.save("heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }
        }
    }


    public static void main(String[] args) {
        BallScreenSaver bb = new BallScreenSaver(800, 800, "Bouncing Ball",10);
        bb.start();
    }
}
