package DynamicProgramming;

class binomialCoeff {
    static int binCof(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        int bin[][] = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {

                if (j == 0 || j == i) {
                    bin[i][j] = 1;
                } else {
                    bin[i][j] = bin[i - 1][j - 1] + bin[i - 1][j];
                }
            }
        }
        return bin[n][k];
    }

    public static void main(String[] args) {
        int n = 2;
        int k = 3;
        System.out.println("binCof[" + n + "][" + k + "]: " + binCof(n, k));
    }
}
