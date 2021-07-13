package Sort;

public class HeapSort implements Sort {
    @Override
    public void sort(int[] arr) {
        // bilde die Heap Eigenschaft.
        buildHeap(arr);
        // rueckwaerts zu position 1, zwischen 0 und das lezte Element wechseln.
        for (int i = arr.length - 1; i > 0; i--) {
            Sort.swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    /**
     * Eine Methode, die mit Hilfe der Methode heapify am Anfang erstellt.
     *
     * @param arr eine Liste von ganzyahlen.
     */
    static void buildHeap(int[] arr) {

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    /**
     * Eine Methode, die fuer die Erstellung der Heap-Eigenschaft sorgt.
     *
     * @param arr  eine Liste von ganzyahlen.
     * @param i    der Index, wo gecheckt werden soll, ob die Eigenschaft vom heap verletzt ist.
     * @param size die Size von der Liste, die uebergeben wird.
     */
    static void heapify(int[] arr, int i, int size) {

        int largest = i;
        // linkes Kind  2 * i + 1
        int left = 2 * i + 1;
        // rechtes Kind  2 * i + 1
        int right = 2 * i + 2;
        // wenn das linke Kind definiert ist.
        if (left < size) {
            // ist der Vater(i) kleiner als  das linke Kind.
            if (arr[largest] < arr[left]) {
                largest = left;
            }
        }
        //wenn das rechte Kind definiert ist.
        if (right < size) {
            // ist der Vater(i) kleiner als  das rechte Kind.
            if (arr[largest] < arr[right]) {
                largest = right;
            }
        }
        // wenn largest != i, dann ...
        if (largest != i) {
            // swape das ite Element und das largestElement in der Liste.
            Sort.swap(arr, i, largest);
            // wieder eine Route ausfuehren, um die Eihenschaft zu erstellen.
            heapify(arr, largest, size);
        }
    }

    /**
     * Eine Methode, die die Elemente in der Liste an die Console ausdrueckt.
     *
     * @param arr eine Liste von ganzyahlen.
     */
    static void toString(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        HeapSort heap = new HeapSort();
        int[] arr = {16, 4, 10, 90, 4, 0, 123, 14, 7, 9, 3, 2, 8, 1};

        heap.toString(arr);
        heap.sort(arr);
        heap.toString(arr);
    }
}
