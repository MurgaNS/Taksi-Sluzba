package BinarnaPretraga;

import StrukturePodataka.ArrayList;

public class BinarnaPretraga {
    public static int find(ArrayList<Long> array, long target) {
        return binarySearch(array, target, 0, array.size() - 1);
    }

    public static int binarySearch(ArrayList<Long> array, long target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (array.get(mid) == target) {
            return mid;
        } else if (array.get(mid) > target) {
            return binarySearch(array, target, low, mid - 1);
        } else {
            return binarySearch(array, target, mid + 1, high);
        }
    }
}