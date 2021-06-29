package Sort;

interface Sort {
    public void sort(int arr[]);

    public default void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


