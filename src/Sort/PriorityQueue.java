package Sort;

public class PriorityQueue {
    static HeapSort heap = new HeapSort();
    static int arr[];
    static int size;

    PriorityQueue(int arr[]) {
        heap.buildHeap(arr);
        this.arr = new int[arr.length];
        this.arr = arr;
        size = arr.length;
    }

    static int EXTRACTMAXIMUM() {
        int max = arr[0];
        arr[0] = arr[size - 1];
        size--;

        int temParr[] = new int[size];
        temParr = arr;
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = temParr[i];
        }
        heap.buildHeap(arr);
        return max;
    }

    static void add(int x) {
        size++;
        int temParr[] = new int[size];
        for (int i = 0; i < arr.length; i++) {
            temParr[i] = arr[i];
        }
        temParr[size - 1] = x;
        arr = new int[size];
        arr = temParr;
        heap.buildHeap(arr);
    }

    static int MAXIMUM() {
        return arr[0];
    }

    static void toString(int x) {
        heap.toString(arr);
    }

    public static void main(String[] args) {
        int[] arr = {16, 4, 10, 90, 4, 0, 123, 14, 7, 9, 3, 2, 8, 1};
        int[] arr1 = {16, 4, 10, 90};

        PriorityQueue priorityQueue = new PriorityQueue(arr1);
        priorityQueue.EXTRACTMAXIMUM();
        priorityQueue.add(13);
        priorityQueue.toString(3);
        System.out.println(priorityQueue.MAXIMUM());
    }
}
