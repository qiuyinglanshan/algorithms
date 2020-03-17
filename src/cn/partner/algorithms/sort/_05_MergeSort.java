package cn.partner.algorithms.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 归并排序
 * 递归思想化整为零，将数组平分两组分别排序，然后再合并。
 * 利用空间换时间，实现两个有序数组的快速合并。
 */
public class _05_MergeSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int start, int end) {
        if (end - start <= 1) {
            if (arr[start] > arr[end]) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
            return;
        }

        int middle = (start + end) / 2;
        sort(arr, start, middle);
        sort(arr, middle + 1, end);
        merge(arr, start, end);

    }

    // 7ms
    private void merge(int[] arr, int start, int end) {
        // 数组copy
        int[] snapshot = copy(arr, start, end);

        int snapshotStart, snapshotEnd, middle, i, j, k;

        snapshotStart = 0;
        snapshotEnd = snapshot.length - 1;
        middle = (snapshotStart + snapshotEnd) / 2;

        for (k = start, i = snapshotStart, j = middle + 1; i <= middle && j <= snapshot.length - 1; k++) {
            if (snapshot[i] <= snapshot[j]) {
                arr[k] = snapshot[i++];
            } else {
                arr[k] = snapshot[j++];
            }
        }

        while (i <= middle) {
            arr[k++] = snapshot[i++];
        }
        while (j <= snapshot.length - 1) {
            arr[k++] = snapshot[j++];
        }
    }

    // like Arrays.copyOfRange, but note the end index is different
    private static int[] copy(int[] arr, int start, int end) {
        if (start < 0 || end > arr.length - 1) {
            throw new IllegalArgumentException("Argument is invalid.");
        }

        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = arr[i];
        }
        return result;
    }

//    public static void testCopy() {
//        System.out.println(Arrays
//                .stream(copy(new int[]{4, 5, 2, 5, 6, 2, 5, 25, 88, 2, 5, 24, 5, 2, 22, 55}, 2, 3))
//                .mapToObj(String::valueOf)
//                .collect(Collectors.joining(",")));
//    }

//    // 440ms
//    private void merge(int[] arr, int start, int end) {
//        int[] copy = Arrays.copyOf(arr, arr.length);
//        int i = start;
//        int middle = (end + start) / 2;
//        int j = middle + 1;
//        int k = start;
//        while(i <= middle && j <= end) {
//            if (copy[i] <= copy[j]) {
//                arr[k++] = copy[i++];
//            } else {
//                arr[k++] = copy[j++];
//            }
//        }
//
//        while (i <= middle) {
//            arr[k++] = copy[i++];
//        }
//        while (j <= end) {
//            arr[k++] = copy[j++];
//        }
//        int[] copy = Arrays.copyOfRange(arr, start, end + 1);
//        int i = 0;
//        int middle = (copy.length - 1) / 2;
//        int j = middle + 1;
//        int k = start;
//        while(i <= middle && j <= copy.length - 1) {
//            if (copy[i] <= copy[j]) {
//                arr[k++] = copy[i++];
//            } else {
//                arr[k++] = copy[j++];
//            }
//        }
//
//        while (i <= middle) {
//            arr[k++] = copy[i++];
//        }
//        while (j <= copy.length - 1) {
//            arr[k++] = copy[j++];
//        }
//    }


//    // 360ms
//    private void merge(int[] arr, int start, int end) {
//        int middle = (start + end) / 2;
//        for (int i = middle + 1; i <= end; i++) {
//            if (arr[i] >= arr[i -1]) {
//                return;
//            }
//            int tmp = arr[i];
//            int j = i;
//            while(j > start) {
//                if(tmp < arr[j - 1]) {
//                    arr[j] = arr[j - 1];
//                    j--;
//                } else {
//                    start = j + 1;
//                }
//            }
//            arr[j] = tmp;
//        }
//    }
}
