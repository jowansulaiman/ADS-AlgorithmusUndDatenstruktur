package Rekusion;

public class Hanoi {

    static void hanoi(int n, int from, int via, int to) {
        if (n == 0) return;
        hanoi(n - 1, from, to, via);
        System.out.println("from " + from + " to " + to);
        hanoi(n - 1, via, from, to);
    }
    public static void main(String[] args) {
        hanoi(3, 1, 2, 3);
    }
}
