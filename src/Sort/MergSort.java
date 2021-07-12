package Sort;

public class MergSort implements Sort {
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        int firstPart = n / 2;
        int secondPart = n - (n / 2);

        if (n >= 2) {
            int S1[] = new int[firstPart];
            int S2[] = new int[secondPart];

            fill(S1, 0, firstPart, arr);
            fill(S2, 0, firstPart, secondPart, arr);

            sort(S1);
            sort(S2);
            merge(S1, S2, arr);
        }
    }

    @Override
    public void sort(int[] arr, int left, int right) {

    }

    public static void merge(int S1[], int S2[], int S[]) {
        int left = 0, right = 0;
        int mid = 0;
        while (left < S1.length && right < S2.length) {
            if (S1[left] < S2[right]) {
                S[mid] = S1[left];
                left++;
            } else {
                S[mid] = S2[right];
                right++;
            }
            mid++;
        }
        while (left < S1.length) {
            S[mid] = S1[left];
            left++;
            mid++;
        }
        while ( right < S2.length) {
            S[mid] = S2[right];
            right++;
            mid++;
        }
    }

    public static void fill(int[] a, int fromIndex, int toIndex, int[] val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            a[i] = val[i];
        }
    }

    public static void fill(int[] a, int fromIndex, int leftAddToRightIndex, int toIndex, int[] val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            a[i] = val[i + leftAddToRightIndex];
        }
    }

    static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    public static void main(String[] args) {
        MergSort mergSort = new MergSort();

        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        int[] arr1 = {10, 14, 28, 11, 7, 16, 30, 50, 25, 18};
        int arr2[] = {12, 34, 10, 6, 40};
        mergSort.sort(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
    }


}
