

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Demo {
    private static boolean checkNanAndInfinity(Float a) {
        return a.isNaN() || a.isInfinite();
    }

    private static boolean checkNanAndInfinity(Double a) {
        return a.isNaN() || a.isInfinite();
    }

    private static void makeExpression(float a, float b, float c) {
        //System.out.println("A:" + a + " " + "B:" + b + " " + "C:" + c);
        if (checkNanAndInfinity(a) || checkNanAndInfinity(b) || checkNanAndInfinity(c)) {
            System.out.println("Someone var is NaN or infinity");
            return;
        }
        if (a == 0) {
            if (b != 0) {
                System.out.println("Root is:" + (-c / b));
                return;
            } else {
                System.out.println("'a' and 'b' equal 0");
                return;
            }
        }
        double D = pow(b, 2) - 4 * a * c;
        //System.out.println("D: " + D);
        if (checkNanAndInfinity(D)) {
            System.out.println("D is NaN or infinity");
            return;
        }
        double x1, x2;
        if (D > 0) {
            x1 = (-b + sqrt(D)) / (2 * a);
            x2 = (-b - sqrt(D)) / (2 * a);
            System.out.println("First root: " + x1);
            System.out.println("Second root: " + x2);
        } else if (D == 0) {
            x1 = -b / (2 * a);
            System.out.println("Have one root: " + x1);
        } else {
            System.out.println("No roots");
        }

    }

    public static void main(String[] args) {
        //float b=Float.POSITIVE_INFINITY;
        //float c = Float.NaN;
        //float b = Float.NaN;
        //float a = Float.NaN;
        float a = 0, b = 0, c = 2;
        makeExpression(a, b, c);

    }
}
