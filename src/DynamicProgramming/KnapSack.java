package DynamicProgramming;

public class KnapSack {
    static int knapSack(int capacity, int wt[], int val[]) {
        int n = val.length;
        int K[][] = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= capacity; k++) {
                if (i == 0 || k == 0) {
                    K[i][k] = 0;
                } else if (wt[i - 1] <= k) {
                    K[i][k] = Integer.max(val[i - 1] + K[i - 1][k - wt[i - 1]], K[i - 1][k]);
                } else {
                    K[i][k] = K[i - 1][k];
                }
            }
        }

        return K[n][capacity];
    }


    public static void main(String[] args) {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};

        int W = 60;
        System.out.println(knapSack(W, wt, val));
    }
}
