package Search;

public class BinarySearch {
    public static int search(int arr[]) {
        int mid = arr.length / 2;
        int left = 0;
        int right = arr.length - 1;

        while (left != right) {
            if (left == right) {
                return mid;
            }
            if (left < right){
            }
            if (left > right){

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {7, 10, 11, 14, 16, 18, 25, 28, 30, 50};
    }
}
