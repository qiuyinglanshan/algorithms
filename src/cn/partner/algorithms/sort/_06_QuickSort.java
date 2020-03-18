package cn.partner.algorithms.sort;

/**
 * 快速排序
 *
 * 从数组中取出一个数，然后通过每一趟的交换，让它前边的数都比它小，后边的数都比它大。
 * 然后再对两边分别进行这样的递归操作。
 *
 * 具体每一趟的交换是从数组两端各一个指针向中间遍历，遇到顺序不一致的数据就进行交换，直到两个指针碰面。
 */
public class _06_QuickSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length -1);
    }

    public void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int barrier = arr[start];
        int i = start;
        int j = end;
        boolean flag = false;
        while(i != j) {
            if (!flag) {
                if (arr[j] >= barrier) {
                    j--;
                } else {
                    arr[i] = arr[j];
                    arr[j] = barrier;
                    i++;
                    flag = true;
                }
            } else {
                if (arr[i] <= barrier) {
                    i++;
                } else {
                    arr[j] = arr[i];
                    arr[i] = barrier;
                    j--;
                    flag = false;
                }
            }
        }
        sort(arr, start, i - 1);
        sort(arr, i + 1, end);
    }

}
