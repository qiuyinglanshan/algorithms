package cn.partner.algorithms.sort;

/**
 * 选择排序
 *
 * 每一轮遍历都找到最大(小）数据的位置，将这个位置的数据放到前面。
 */
public class _02_SelectionSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }
}
