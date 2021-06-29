package Sort;

public class InsertionSort {

    static void sort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            for (int j = 0; j < i; j++) {
                if (x < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }
}
