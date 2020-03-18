package cn.partner.algorithms.sort;

/**
 * 计数排序
 *
 * 最快了，时间复杂度为o(n)
 *
 * 典型的空间换时间算法。获取待排序数组的最大值max，构建一个新的长度为max的数组。
 * 将元素组中的值映射到新数组对应的下标，并累计个数。
 * 之后遍历新数组，根据记录的个数，依次按顺序设置到原先数组中。
 */
public class _08_CountingSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                throw new IllegalArgumentException("Data in array must not be negative");
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] counter = new int[max +1];
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]]++;
        }

        for (int i = 0, j = 0; i <= max; i++) {
            while (counter[i]-- != 0) {
                arr[j++] = i;
            }
        }
    }
}
