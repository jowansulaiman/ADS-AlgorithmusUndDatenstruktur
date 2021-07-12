package Sort;

interface Sort {
    public default void sort(int arr[]) {
    }

    public default void sort(int arr[], int left, int right) {
    }

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


