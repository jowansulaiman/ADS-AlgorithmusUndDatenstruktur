package DynamicProgramming;

public class BackTracking {

    static boolean checkRows(boolean arr[][], int row) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[row][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkClos(boolean arr[][], int cl) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j][cl]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDigo(boolean arr[][], int row, int cl) {
        for (int i = row; i < arr.length; i++) {
            for (int j = cl; j < arr[i].length; j++) {
                if (arr[j][cl]) {
                    return true;
                }
            }
        }
        return false;
    }


}
