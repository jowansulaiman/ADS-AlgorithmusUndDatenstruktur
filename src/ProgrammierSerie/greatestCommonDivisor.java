package ProgrammierSerie;

public class greatestCommonDivisor {
    /**
     * Computes the gcd (greatest common divisor) using Euclid’s algorithm.
     *
     * @param a first input number.
     * @param b second input number.
     * @return the gcd of a and b.
     */
    public static int greatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return (a > 0) ? a : a * -1;
    }

}
