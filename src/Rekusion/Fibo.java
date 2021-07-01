package Rekusion;

public class Fibo {

    static int fib(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        return fib(n - 1) + fib(n - 2);
    }

    static int fibIt(int n) {
        int fib[] = new int[n + 1];
        fib[0] = 0;   fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n] ;
    }

    public static void main(String[] args) {
        System.out.println("Fib: " + fibIt(6));
    }
}
