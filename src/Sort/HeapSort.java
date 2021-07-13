package Sort;

public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        BuildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            Sort.swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    static void BuildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    static void heapify(int[] arr, int i, int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size) {
            if (arr[largest] < arr[left]) {
                largest = left;
            }
        }
        if (right < size) {
            if (arr[largest] < arr[right]) {
                largest = right;
            }
        }
        if (largest != i) {
            Sort.swap(arr, i, largest);
            heapify(arr, largest, size);
        }
    }


    public static void main(String[] args) {
        HeapSort heap = new HeapSort();

        int[] arr = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        for (int i : arr) {
            System.out.print(" " + i);
        }
        System.out.println(" ");
        heap.sort(arr);

        for (int i : arr) {
            System.out.print(" " + i);
        }
    }
}
