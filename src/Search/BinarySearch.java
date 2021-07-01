package Search;

public class BinarySearch {
    public static int search(int arr[], int x) {
        int mid;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] < x) {
                left = mid + 1;
            }
            if (arr[mid] > x) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {7, 10, 11, 14, 16, 18, 25, 28, 30, 50};

        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " " + arr[i]);
        }
        System.out.println(" " + search(arr, -1));
    }
}
