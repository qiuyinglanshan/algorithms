package cn.partner.algorithms.sort;

import cn.partner.algorithms.Utils;

/**
 * 堆排序
 *
 * 堆排序的过程就是先根据给出的数组 -->构建一个大顶堆，然后根据大顶堆得到一个有序数组。
 * 大顶堆的结构是一个满足parent>children的完全二叉树，完全二叉树的父子节点的索引值关系：
 *    parentIndex = (childIndex - 1) / 2
 *    leftChildIndex = parentIndex * 2 + 1
 *    rightChildIndex = parentIndex * 2 + 2
 * 正是通过这样的关系，才能方便我们在构建堆的时候可以实现父子互换(swap)。
 *
 * 1. 先将数组构建成一个大顶堆：
 * 从最下边的父节点开始向根迭代，对每一个父节点转成大顶堆，如果有互换的情况发生，则递归对子节点的大顶堆再构建
 *
 * 2. 在一个合格的大顶堆中，祖先节点一定是最大的，和最后(last non-sorted)一个节点互换，就是一个最大值，将它从堆中割断(做法是将堆的边界 -1)。
 * 然后在剩余的堆中，按照互换完的堆顶元素重新构建大顶堆，然后再互换+割断+构建...,
 * 迭代这个过程，将出现一个个的最大值到数组的右边，直到结束。
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
