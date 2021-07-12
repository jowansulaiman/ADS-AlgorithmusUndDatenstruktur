package DynamicProgramming;

import java.math.BigInteger;
import java.util.Random;

public class Karazuba {
    static BigInteger karatsuba(BigInteger x, BigInteger y) {
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 20) {
            return x.multiply(y);
        }
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
    }

    public static void main(String[] args) {
        // Showcasing karatsuba multiplication
        // case 1: Big integer lengths
        Random random = new Random();

        int N = Integer.MAX_VALUE;
        BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);

        BigInteger c = karatsuba(a, b);
        System.out.println("Product 1 : " + c);

    }

}
