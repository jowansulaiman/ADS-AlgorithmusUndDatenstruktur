package DynamicProgramming;

public class StrassenMatrizen {

    static int[][] multiply(int A[][], int B[][]) {
        int n = A.length;
        int C[][] = new int[n][n];

        // Base case
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        int[][] A11 = new int[n / 2][n / 2];
        int[][] A12 = new int[n / 2][n / 2];
        int[][] A21 = new int[n / 2][n / 2];
        int[][] A22 = new int[n / 2][n / 2];
        int[][] B11 = new int[n / 2][n / 2];
        int[][] B12 = new int[n / 2][n / 2];
        int[][] B21 = new int[n / 2][n / 2];
        int[][] B22 = new int[n / 2][n / 2];

        // Step 2: Dividing matrix A into 4 halves
        split(A, A11, 0, 0);
        split(A, A12, 0, n / 2);
        split(A, A21, n / 2, 0);
        split(A, A22, n / 2, n / 2);

        // Step 2: Dividing matrix B into 4 halves
        split(B, B11, 0, 0);
        split(B, B12, 0, n / 2);
        split(B, B21, n / 2, 0);
        split(B, B22, n / 2, n / 2);

        int[][] M1 = multiply(add(A11, A22), add(B11, B22));

        // M2:=(A2+A4)×(B3+B4)
        int[][] M2 = multiply(add(A21, A22), B11);

        // M3:=(A1−A4)×(B1+A4)
        int[][] M3 = multiply(A11, sub(B12, B22));

        // M4:=A1×(B2−B4)
        int[][] M4 = multiply(A22, sub(B21, B11));

        // M5:=(A3+A4)×(B1)
        int[][] M5 = multiply(add(A11, A12), B22);

        // M6:=(A1+A2)×(B4)
        int[][] M6 = multiply(sub(A21, A11), add(B11, B12));

        // M7:=A4×(B3−B1)
        int[][] M7 = multiply(sub(A12, A22), add(B21, B22));

        // P:=M2+M3−M6−M7
        int[][] C11 = add(sub(add(M1, M4), M5), M7);

        // Q:=M4+M6
        int[][] C12 = add(M3, M5);

        // R:=M5+M7
        int[][] C21 = add(M2, M4);

        // S:=M1−M3−M4−M5
        int[][] C22 = add(sub(add(M1, M3), M2), M6);

        // Step 3: Join 4 halves into one result matrix
        join(C11, C, 0, 0);
        join(C12, C, 0, n / 2);
        join(C21, C, n / 2, 0);
        join(C22, C, n / 2, n / 2);

        return C;
    }

    // Outer loop for rows
    static void join(int[][] C, int[][] P, int iB, int jB) {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)

                P[i2][j2] = C[i1][j1];
    }

    static void split(int[][] P, int[][] C, int iB, int jB) {
        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    static int[][] add(int A[][], int B[][]) {
        int n = A.length;
        int C[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    static int[][] sub(int A[][], int B[][]) {
        int n = A.length;
        int C[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void main(String[] args) {
        // Display message
        System.out.println("Strassen Multiplication Algorithm Implementation For Matrix Multipication :\n");

        // Size of matrix
        // Cosidering size as 4 in order to illustrate
        int N = 4;

        // Matrix A
        // Custom input to matrix
        int[][] A = {{1, 2, 3, 4},
                {4, 3, 0, 1},
                {5, 6, 1, 1},
                {0, 2, 5, 6}};

        // Matrix B
        // Custom input to matrix
        int[][] B = {{1, 0, 5, 1},
                {1, 2, 0, 2},
                {0, 3, 2, 3},
                {1, 2, 1, 2}};

        // Matrix C calling method to get Result
        int[][] C = multiply(A, B);

        // Display message
        System.out.println(
                "Product of matrices A and  B : ");

        // Iterating over elements of 2D matrix
        // using nested for loops

        for (int i = 0; i < N; i++) {

            System.out.print("{");
            // Inner loop for columns
            for (int j = 0; j < N; j++)
                System.out.print("(" + A[i][j] + ")");
            System.out.print("} + {");
            for (int j = 0; j < N; j++)
                System.out.print("(" + B[i][j] + ")");
            System.out.print("}");
            System.out.print("  = ");

            System.out.print("{");
            for (int j = 0; j < N; j++)
                System.out.print("(" + C[i][j] + ")");
            System.out.print("}");
            System.out.println();
        }
    }
}
