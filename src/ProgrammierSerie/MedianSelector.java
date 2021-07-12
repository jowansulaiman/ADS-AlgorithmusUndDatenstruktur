package ProgrammierSerie;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MedianSelector {
    /**
     * Computes and retrieves the lower median of the given array of
     * numbers using the Median algorithm presented in the lecture.
     *
     * @param numbers numbers.
     * @return the lower median.
     * @throw IllegalArgumentException if the array is {@code null} or empty.
     */
    public static int lowerMedian(int[] numbers) {
        //Wenn die Liste leer ist, dann exception werfen.
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("the array is or empty.");
        }
        // Länge der Liste.
        int n = numbers.length;

        // Falls nur ein Element in der Liste ist.
        if (n == 1) {
            return numbers[n - 1];
        }
        // den Median finden.
        return lowerMedian(numbers, 0, n - 1, (n + 1) / 2);
    }

    /**
     * Computes and retrieves the lower median of the given array of
     * numbers using the Median algorithm presented in the lecture.
     *
     * @param numbers       ein Feld von ganzenZahlen.
     * @param left          repräsentiert der linken Seite der Array.
     * @param right         repräsentiert der rechten Seite der Array.
     * @param indexOfMedian die Position, an der sich der Median befinden soll, wenn die Liste sortiert wäre.
     * @return the lower median.
     */
    public static int lowerMedian(int[] numbers, int left, int right, int indexOfMedian) {
        // die Länge der Liste bestimmen.
        int n = right - left + 1;

        // Ein Array zum Speichern von allen Median der allen Gruppen.
        int[] median = new int[(n + 4) / 5];
        // jede Gruppe sortieren und den Median bestimmen, dann ihn in den Feld median speichern.
        int i;
        for (i = 0; i < n / 5; i++) {
            median[i] = median(numbers, left + i * 5, 5 - 1);
        }
        // Wennn doch ein paar Elemente mehr sind(verbleibende Elemente). Dann soll sie auch sortiert und den Median von den bestimmt werden.
        if (i * 5 < n) {
            median[i] = median(numbers, left + i * 5, (n % 5) - 1);
            i++;
        }
        // Bestimme rekursiv den Median x der ⌈n/5⌉ Mediane.
        int medianOfMedians = (i == 1) ? median[i - 1] : lowerMedian(median, 0, i - 1, i / 2);

        //Partitioniere die Liste L (wie bei Quiksort) in Teillisten L1 und L2 mit Zahlen
        //< x bzw. > x. Bestimme die Längen ℓj der Teillisten Lj (dann steht x an Position
        //k = ℓ1 + 1),
        int postionOfMedian = QuickSort.partition(numbers, left, right, medianOfMedians);

        //  Wenn die Position bzw. den Median aller Medianen gefunden wurde, dann wird den Median zurückgegeben.
        if (postionOfMedian - left == indexOfMedian - 1) {
            return numbers[postionOfMedian];
        }

        //  Bestimme rekursiv das i.te Element in L1 (falls i < k) bzw. das (i − k).te Element in L2 (falls i > k
        if (postionOfMedian - left > indexOfMedian - 1) {
            return lowerMedian(numbers, left, postionOfMedian - 1, indexOfMedian);
        } else {
            return lowerMedian(numbers, postionOfMedian + 1, right, indexOfMedian - postionOfMedian + left - 1);
        }
    }

    /**
     * @param arr   ein Feld von ganzenZahlen.
     * @param left  repräsentiert der linken Seite der Array.
     * @param right repräsentiert der rechten Seite der Array.
     * @return gibt den Median zurück.
     */
    static int median(int arr[], int left, int right) {
        // Sortiere die Gruppe [n/5]
        QuickSort.sort(arr, left, (left + right));

        //gibt Median zurück.
        return arr[left + (right / 2)];
    }

    /**
     * <h1>QuickSort.</h1>
     * Eine Klasse, die drei Methoden zur Verfügung stellt, die beim Sortieren von ganzen Zahlen sehr hilfsreich sind.
     * <p>
     * <b>Sort:</b> Die Hauptfunktion, die QuickSort implementiert.
     *
     * <b>partation:</b>  Diese Funktion nimmt das letzte Element als Pivot, platziert d as Pivot-Element an seiner richtigen Position im sortierten
     * Array, und platziert alle kleineren (kleiner als Pivot) links vom Drehpunkt und alle größeren Elemente rechts des Drehpunkts.
     *
     * <b>exchange:</b> Eine Nutzenfunktion zum Vertauschen zweier Elemente innerhalb des Arrays.
     */
    private static class QuickSort {
        /**
         * Vertauschen zweier Elemente in einem Array.
         *
         * @param arr das Array von ganzen Zahlen.
         * @param i   die i.te Stelle.
         * @param j   die j.te Stelle.
         */
        static void exchange(int arr[], int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /**
         * @param arr   das Array von ganzen Zahlen.
         * @param left  repräsentiert der linken Seite der Array.
         * @param right repräsentiert der rechten Seite der Array.
         * @param x     das Pivot.
         * @return gibt die Position vom Povit zurück.
         */
        private static int partition(int[] arr, int left, int right, int x) {
            // Nach dem Index von x suchen.
            for (int i = left; i < right; i++) {
                if (arr[i] == x) {
                    exchange(arr, i, right);
                    break;
                }
            }
            int i = left;

            // Index des kleineren Elements und gibt die
            // rechte Position des bisher gefundenen Pivots
            for (int j = left; j <= right - 1; j++) {
                // Wenn aktuelles Element kleiner als der Drehpunkt ist.
                if (arr[j] <= x) {
                    //zwischen j und i tauschen.
                    exchange(arr, i, j);
                    // Index des kleineren Elements inkrementieren
                    i++;
                }
            }
            exchange(arr, i, right);
            // die Position von x zurückgeben.
            return i;
        }

        /**
         * @param arr das Array von ganzen Zahlen.
         * @param l   repräsentiert der linken Seite der Array.
         * @param r   repräsentiert der rechten Seite der Array.
         * @throw IllegalArgumentException wenn die linke klein 0 ist und rechte Seite grosser als die Laenge der Liste ist.
         */
        static void sort(int arr[], int l, int r) {
            if (l < 0 || r > arr.length) {
                throw new IllegalArgumentException("Der Scope vom Array darf nicht verletzt werden.");
            }

            if (l < r) {
                // d ist Partitionsindex, arr[p]
                // ist jetzt an der richtigen Stelle
                int q = partition(arr, l, r, arr[r]);

                // Separate Sortierung der Elemente vor
                // Partition und nach Partition
                sort(arr, l, q - 1);
                sort(arr, q + 1, r);
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {7, 9, 10, 8, 5, 6, 1, 3, 2, 4};
        System.out.println("5: " + numbers.length + " " + MedianSelector.lowerMedian(numbers));

        int[] numbers1 = {7, 6, 3, 7};
        System.out.println("6: " + numbers1.length + " " + MedianSelector.lowerMedian(numbers1));

        int[] numbers2 = {-7, -5, 0, -8, -9, -6, -1, 3, 9, 4};
        System.out.println("-5: " + numbers2.length + " " + MedianSelector.lowerMedian(numbers2));

        int[] numbers3 = {-7, 5, 0, 8, -9, 6, 1, 3, 4, 12, -42};
        System.out.println("3: " + numbers3.length + " " + MedianSelector.lowerMedian(numbers3));
        Arrays.sort(numbers3);
        for (int i = 0; i < numbers3.length; i++) {
            System.out.print(numbers3[i] + " ");
        }
        int[] numbers4 = {-7, 13, 0, -133, -95, 6, 84, 10, 43, -8, 3, 21, 59, 8, 54, -20, -68, 5, 1, -3, 9, 4, 12};
        System.out.println(MedianSelector.lowerMedian(numbers4));

        int[] numbers5 = {-71, 51, -12, 81, -95, 6071, 84, 275, 42, 18, 41, 56, 69, 28, 4, 54, -92, 601, 1, 31, -142, 39, -13, -7, 5, 13, 890, -94, 167, -93, 597, 333, -81, -11, 17, 32, 8, 15, 502, 68, 6, 21, 3, 9, -55, 12, 7, 0};
        System.out.println(MedianSelector.lowerMedian(numbers5));
    }
}
