package Rekusion;

public class Faku {

    static int fak(int n) {
        if (n == 0) {
            return 1;
        }
        return n * fak(n - 1);
    }

    static int fakIt(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Fakultät: " + fakIt(5));
        System.out.println("Fakultät: " + fak(5));
    }
}
