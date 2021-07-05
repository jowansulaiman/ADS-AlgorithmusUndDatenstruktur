package PA;

public class CONV {

    static int[] conv(int A[], int B[]) {
        int n = (A).length;
        int m = (B).length;
        int k = Math.min(n, m);
        int r = 0;
        int C[] = new int[k];
        for (int i = 0; i < k; i++) {
            r = 0;
            for (int j = 0; j <= i; j++) {
                int t = A[j] + B[i - j];
                 if(t > r)
                     r = t;
            }
            C[i] = r;
        }
        return C;
    }

    public static void main(String[] args) {

        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        int[] arr1 = {10, 14, 28, 11, 7, 16, 30, 50, 25, 18};
        int c[] = conv(arr, arr1);

        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
    }
}
