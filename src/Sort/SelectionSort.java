package Sort;

public class SelectionSort implements Sort {


    public void sort(int arr[]) {
        for (int i = 0; i < arr.length - 3; i++) {
            int min = arr[i];
            int indexOfMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    indexOfMin = j;
                }
            }
            swap(arr, i, indexOfMin);
        }
    }


    public static void main(String[] args) {
        SelectionSort Selection = new SelectionSort();

        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        int[] arr1 = { 10, 14, 28, 11, 7, 16, 30, 50, 25, 18};
        int arr2[] = {12, 34, 10, 6, 40};
        Selection.sort(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(" " + arr1[i]);
        }
    }
}
