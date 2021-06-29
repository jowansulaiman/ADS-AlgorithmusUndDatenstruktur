package Sort;

public class InsertionSort implements Sort {

    public static void main(String[] args) {
        InsertionSort Insertion = new InsertionSort();
        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        Insertion.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    @Override
    public void sort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            int j = i - 1;

            while (j >= 0 && x < arr[j]) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = x;
        }
    }
}
