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

    static int knap(int B, int W[], int P[]) {
        int n = W.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            max = max + P[i];
        }
        int A[][] = new int[n][max + 1];
        A[0][0] = 0;

        for (int t = 1; t <= max; t++) {
            if (P[0] != t) {
                A[0][0] = B + 1;
            }else {
                A[0][t] = W[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= max; t++) {
                if (t < P[i]) {
                    A[i][t] = A[i - 1][t];
                }else     {
                    A[i][t] = Math.min(A[i - 1][t], A[i - 1][t - P[i]] + W[i]);
                }
            }
        }
        int j = 0;
        for (int t = 0; t <= max; t++) {
            if (A[n - 1][t] <= B) {
                j = t;
            }
        }
        return  j;
    }

    public static void main(String[] args) {
        int val[] = new int[]{60, 100000, 100000000};
        int wt[] = new int[]{10, 20, 30};

        int W = 20;
        System.out.println(knapSack(W, wt, val));
        System.out.println(knap(W, wt, val));

    }
}
