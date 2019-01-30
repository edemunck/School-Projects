public class CollisionLogger {
    /* add data members here */
    private int screenWidth, screenHeight, bucketWidth;
    private int[][] HeatMap;

    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
        /* constructor code here */
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.bucketWidth = bucketWidth;
        this.HeatMap = new int[screenHeight/bucketWidth][screenWidth/bucketWidth];
    }

    /**
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
        /* update data members to reflect the collision event between ball "one" and ball "two" */
        int xone = (int)one.getXPos();
        int xtwo = (int)two.getXPos();
        int yone = (int)one.getYPos();
        int ytwo = (int)two.getYPos();
        // adds collision to map
        int xs = (xone + xtwo)/2;
        int ys = (yone + ytwo)/2;
        if(xs < 0) { xs = 0; }
        if(xs > screenWidth) { xs = screenWidth; }
        if(ys < 0) { ys = 0; }
        if(ys > screenHeight) { ys = screenHeight; }
        HeatMap[ys/bucketWidth][xs/bucketWidth] += 1;
    }

    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {
        int[][] normalizedHeatMap = new int[HeatMap.length][HeatMap[0].length];
        int maxx = maxCollisions();
        for(int i = 0; i<HeatMap.length; i++) {
            for(int j = 0; j<HeatMap[i].length; j++) {
                normalizedHeatMap[i][j] = (int)((((double) HeatMap[i][j]) / maxx) * 255);
            }
        }

//        int[][] normalizedHeatMap = new int[1][1]; //NOTE-- these dimensions need to be updated properly!!
        /* implement your code to produce a normalized heat map of type int[][] here */

        return normalizedHeatMap;
    }

    public int maxCollisions() {
        int max = Integer.MIN_VALUE;
        for(int i =0; i<HeatMap.length; i++) {
            for(int j = 0; j<HeatMap[i].length; j++) {
                if(HeatMap[i][j] > max) {
                    max = HeatMap[i][j];
                }
            }
        }
        return max;
    }
}
