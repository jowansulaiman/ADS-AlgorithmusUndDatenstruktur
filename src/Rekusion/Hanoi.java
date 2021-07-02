package Rekusion;

public class Hanoi {
    static int counter = 0;

    static void hanoi(int n, int from, int via, int to) {
        if (n == 0) return;
        counter++;
        hanoi(n - 1, from, to, via);
        System.out.println("from " + from + " to " + to);
        hanoi(n - 1, via, from, to);
    }

    public static void main(String[] args) {
        hanoi(3, 1, 2, 3);
        System.out.println("Der Algorithmus benoetigt fuer n(2^n - 1) = " + counter + " Zuege");
    }
}
