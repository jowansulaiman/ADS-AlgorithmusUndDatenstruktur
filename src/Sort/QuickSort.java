package Sort;

public class QuickSort implements Sort {

    public void sort(int arr[]) {
        sort(arr, 0, arr.length - 1);
    }

    @Override
    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            int d = partition(arr, left, right);
            sort(arr, left, d - 1);
            sort(arr, d + 1, right);
        }
    }

    static int partition1(int arr[], int left, int right) {
        int pivot = arr[(right)];

        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                Sort.swap(arr, i, j);
            }
        }
        Sort.swap(arr, i + 1, right);

        return (i + 1);
    }

    static int partition(int arr[], int low, int high) {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }
            Sort.swap(arr, j, i);
        }
    }


    public static void main(String[] args) {
        QuickSort Quick = new QuickSort();

        int arr[] = {85, 24, 63, 45, 17, 31, 96, 50};
        int[] arr1 = {10, 14, 28, 11, 7, 16, 30, 50, 25, 18};
        int arr2[] = {12, 34, 10, 6, 40};
        Quick.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }
}
