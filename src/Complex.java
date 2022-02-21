package src;

public class Complex {
    public double a = 0.0;
    public double bi = 0.0;

    Complex(double a, double bi) {
        this.a = a;
        this.bi = bi;
    }

    static double absolute(Complex x) {
        return Math.sqrt(((x.a * x.a) + (x.bi * x.bi)));
    }

    static Complex square(Complex x) {
        return new Complex((x.a * x.a) + ((x.bi * x.bi) * -1 ), x.a * x.bi * 2);
    }

    static Complex add(Complex x, Complex y) {
        return new Complex(x.a + y.a, x.bi + y.bi);
    }

    static String toString(Complex x) {
        return Double.toString(x.a) + ", " + Double.toString(x.bi);
    }
}
