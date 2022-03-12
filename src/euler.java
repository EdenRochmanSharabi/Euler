import java.util.Random;

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
    public euler() {
        this.initialX=0;// Initial condition of X
        this.initialY=1; // Initial condition of Y
        this.finalX=1000;
        this.finalY=0.0;
        this.steps=1000; // The higher, the better
        this.stepSize=0.0001; // The smaller, the better
        this.root=root;
        this.kfg=kfg;
        this.initialVelX=initialVelX;
        this.initialVelY=initialVelY;
        this.xMov=xMov;
        this.yMov=yMov;
    }

    /*
    This is a work in process.
    Somehow i can't figure out how to get the random in between my bounds.
     */
    public double grassFriction(){
        Random rand = new Random(); //instance of random class
        double max=0.1;
        double min=0.05;
        double random_double=0.0;
        for (int i = 0; i < 100000; i++) {
            random_double=Math.random()*(max-min);
            System.out.println(random_double);
        }
        return random_double;
    }
    public double EulerCalculation(double x, double h){
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
    public void ballMov(){
        xMov=(-g*EulerCalculation(initialX,stepSize))-kfg*g*(initialVelX/sqrt(root(initialVelX))*sqrt(root(initialVelY)));
        yMov=(-g*EulerCalculation(initialY,stepSize))-kfg*g*(initialVelX/sqrt(root(initialVelX))*sqrt(root(initialVelY)));
    }

    public static void main(String[] args) {
        euler euler = new euler();
//        euler.EulerCalculation(100,0.01);

    }
}