import java.util.concurrent.ThreadLocalRandom;

public class euler {
    public double finalY;
    public double initialY;
    public double finalX;
    public double initialX;
    public double stepSize;
    public int steps;
    public double root;
    public final double g=9.81; // Gravity
    public double kfg; // Kinetic friction (grass)
    public double initialVelX;
    public double initialVelY;
    public double xMov;
    public double yMov;
    public double mass;
    public euler() {
        this.initialX=0;// Initial condition of X
        this.initialY=1; // Initial condition of Y
        this.finalX=10;
        this.finalY=0.0;
        this.steps=1000; // The higher, the better
        this.stepSize=0.0001; // The smaller, the better
        this.root=root;
        this.kfg=kfg;
        this.initialVelX=initialVelX;
        this.initialVelY=initialVelY;
        this.xMov=xMov;
        this.yMov=yMov;
        this.mass=0.0459;
    }

    public double grassFriction(){
        return ThreadLocalRandom.current().nextDouble(0.049999999999, 0.100000000000001);
    }
    public double eulerCalculation(double x, double h){
        while (initialX<finalX){
                root=initialX+stepSize;

                finalY=initialY+stepSize*obtaionfXY(x);

                initialX=root;
                initialY=finalY;
            System.out.println(finalY);
            System.out.println(finalX);
        }
        System.out.println("Done");
        return finalY;

    }
    // dy/dx=(x + y + xy)
    public double obtaionfXY(double x){
        return x+initialY+2*initialY;
    }
    /*
    Square root calculation method.
     */
    public double sqrt(double num){
        double a;
        double squareroot = num/2;
        do {
            a = squareroot;
            squareroot = (a + (num / a)) / 2;
        }
        while ((a-squareroot) != 0);
            return squareroot;
    }
    public double root(double num){return num*num;}
    /*
    Calculate acceleration of X and Y
     */
    public double  ballMovX(){
        return xMov=(-g*eulerCalculation(initialX,stepSize))-grassFriction()*g*(initialVelX/sqrt(root(initialVelX)+root(initialVelY)));
    }
    public double  ballMovY(){
        return yMov=(-g*eulerCalculation(initialY,stepSize))-grassFriction()*g*(initialVelX/sqrt(root(initialVelX)+root(initialVelY)));
    }
    public double normalForce(){
       return ((mass*g)/sqrt(1+(root(eulerCalculation(initialX, stepSize))+root(eulerCalculation(initialY, stepSize)))));
    }
    public static void main(String[] args) {
        euler euler = new euler();
//        euler.EulerCalculation(100,0.01);
        euler.ballMovY();
        euler.ballMovX();
    }
}