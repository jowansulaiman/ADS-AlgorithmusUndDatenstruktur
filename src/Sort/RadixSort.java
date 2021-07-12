package Sort;

import java.util.LinkedList;

public class RadixSort implements Sort {
    @Override
    public void sort(int arr[]) {
        int maxLength = String.valueOf(maxValueOfArray(arr)).length();

        LinkedList<Integer> nr[] = new LinkedList[10];

        for (int i = 0; i < nr.length; i++) {
            nr[i] = new LinkedList<>();
        }

        for (int i = 0; i < arr.length; i++) {
            nr[arr[i]].add(arr[i]);
        }

        System.out.print(nr[0] + " ");
    }

    static int positionOfNr(int nr, int n) {
        int p = 0;
        for (int i = 0; i <= n; i++) {
            p = nr % 10;
            nr = nr / 10;
        }
        return p;
    }

    static int maxValueOfArray(int arr[]) {
        int MAX = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            MAX = Integer.max(MAX, arr[i]);
        }
        return MAX + 1;
    }

    public static void main(String[] args) {

        RadixSort Bucket = new RadixSort();
        int x = positionOfNr(52, 1);
        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        int[] arr1 = {10, 14, 28, 28, 28, 1, 11, 12, 12, 11, 7, 16, 30, 50, 25, 18};
        int arr2[] = {12, 34, 10, 6, 40};
        Bucket.sort(arr1);
    }
}
