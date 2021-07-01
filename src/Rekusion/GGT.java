package Rekusion;

public class GGT {

    static int ggT(int x, int y) {
        if (x == y) {
            return x;
        }
        return (x > y) ? ggT(x - y, y) : ggT(x, y - x);
    }

    static int ggTEuc(int x, int y) {
        if (y == 0) {
            return x;
        }
        return ggTEuc(y, x % y);
    }


    public static void main(String[] args) {
        System.out.println("ggT: " + ggT(2, 8));
        System.out.println("ggTEuc: " + ggTEuc(2, 8));
    }
}
