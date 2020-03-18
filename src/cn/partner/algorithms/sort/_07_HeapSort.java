package cn.partner.algorithms.sort;

import cn.partner.algorithms.Utils;

/**
 * 堆排序
 */
public class _07_HeapSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        // build heap
        buildHeap(arr);

        // Swap the top of the heap(arr[0]) and the last non-sorted node(arr[i])
        // Since nodes arr[i+1] → arr[arr.length -1] have been already sorted."剪断"
        for (int i = arr.length -1; i > 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i,0);
        }
    }

    // Test Heapify, Output: 10, 5, 3, 4, 1, 2
    public static void main(String[] args) {
//        int[] arr = new int[]{4,10,3,5,1,2};
        int[] arr = new int[]{2,5,3,1,10,4};
        new _07_HeapSort().buildHeap(arr);
        Utils.print(arr);
        new _07_HeapSort().sort(arr);
        Utils.print(arr);

    }
    // build heap
    private void buildHeap(int[] arr) {
        int lastIndex = arr.length - 1;
        int parentIndex = (lastIndex - 1) / 2;
        for (int i = parentIndex; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
    }

    private void heapify(int[] arr, int n, int index) {
        // exit
        if (index >= n) {
            return;
        }
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        int max = index;
        if (leftIndex < n && arr[max] < arr[leftIndex]) {
            max = leftIndex;
        }
        if (rightIndex < n && arr[max] < arr[rightIndex]) {
            max = rightIndex;
        }
        if (index != max) {
            swap(arr, index, max);
            heapify(arr, n, max);
        }
    }

    private void swap (int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
