package Sort;

import java.util.LinkedList;

public class BucketSort implements Sort {


    @Override
    public void sort(int arr[]) {
       int n = maxOfArray(arr);
        LinkedList<Integer> nr[] = new LinkedList[n];

        for (int i = 0; i < nr.length; i++) {
            nr[i] = new LinkedList<>();
        }

        for (int i = 0; i < arr.length; i++) {
            nr[arr[i]].add(arr[i]);
        }

        for (int i = 0; i < nr.length; i++) {
            if (nr[i].size() > 0) {
                for (int j = 0; j < nr[i].size(); j++) {
                    nr[0].add(nr[i].get(j));
                }
            }
        }
        System.out.print(nr[0] + " ");
    }

    static int maxOfArray(int arr[]) {
        int MAX = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            MAX = Integer.max(MAX, arr[i]);
        }
        return MAX + 1;
    }

    public static void main(String[] args) {

        BucketSort Bucket = new BucketSort();

        int arr[] = {3, 2, 0, 8, 6, 4, 3};
        int[] arr1 = {10, 14, 28, 28, 28, 1, 11, 12, 12, 11, 7, 16, 30, 50, 25, 18};
        int arr2[] = {12, 34, 10, 6, 40};
        Bucket.sort(arr1);
    }

}
