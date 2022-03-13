import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class engine {
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
    public engine() {
        this.initialX=0;// Initial condition of X
        this.initialY=0; // Initial condition of Y
        this.finalX=4;
        this.finalY=1;
        this.steps=1000; // The higher, the better
        this.stepSize=0.1; // The smaller, the better
        this.root=root;
        this.kfg=0.1;
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
                root=initialX+h;
            for (int i = 0; i < steps; i++) {
                finalY=initialY+h*obtaionfXY(x);
                System.out.println(initialX);
                System.out.println(initialY);
            }


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
    public double  ballAccelerationX(){
        return xMov=(-g*eulerCalculation(initialX,stepSize))-grassFriction()*g*(initialVelX/sqrt(root(initialVelX)+root(initialVelY)));
    }
    public double  ballAccelerationY(){
        return yMov=(-g*eulerCalculation(initialY,stepSize))-grassFriction()*g*(initialVelX/sqrt(root(initialVelX)+root(initialVelY)));
    }
    public double normalForce(double a){
       return ((mass*g*eulerCalculation(a, stepSize))/(1+(root(eulerCalculation(initialX, stepSize))+root(eulerCalculation(initialY, stepSize)))));
    }
    public double totalFriction(){
        double frictionMassGrav = (grassFriction()*mass*g);
        double firstSquareRoots = (sqrt(1+root(initialVelX)+root(initialVelY)));
        double secondSquareRoot = sqrt(root(initialVelX)+root(initialVelY)+root(eulerCalculation(initialX, stepSize)*initialVelX+eulerCalculation(initialY, stepSize)*initialVelY));
        return ((-1)*(frictionMassGrav/(firstSquareRoots*secondSquareRoot)));
    }


    public double finalMotionX(){
        return normalForce(initialVelX) - totalFriction()*initialVelX;
    }
    public double finalMotionY(){
        return normalForce(initialVelY) - totalFriction()*initialVelY;
    }
    public double xyDivision(double x, double y){ return ((x-y)/2);}
    public double rungeKutte(double x0, double y0, double h){
        //Count number of iterations using step size or step height h
        int count = (int)((finalX-x0)/h);
        double k1, k2, k3, k4, k5;
        // Iterate for number of iterations
        double y = y0;
        for (int i = 0; i <= count; i++) {
            k1 = h * (xyDivision(x0, y));
            k2 = h * (xyDivision(x0 + 0.5 * h, y + 0.5 * k1));
            k3 = h * (xyDivision(x0 + 0.5 * h, y + 0.5 * k2));
            k4 = h * (xyDivision(x0 + h, y + k3));
            // Update next value of y
            y = y + (1.0 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            // Update next value of x
            x0 = x0 + h;
        }
        return y;
    }



    public static void main(String[] args) {
        engine engine = new engine();
//        System.out.println(euler.eulerCalculation(0, 0.1));
        System.out.println(engine.rungeKutte(0, 1, 0.2));
    }
}