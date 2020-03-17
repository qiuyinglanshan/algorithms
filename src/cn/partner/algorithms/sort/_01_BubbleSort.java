package cn.partner.algorithms.sort;

/**
 * 冒泡排序
 *
 * 每一轮排序都从头拿一个数据向后遍历，依次和相邻节点比较， 顺序不对就替换。
 * 相当于每一轮都把最大（小）的节点移动到后端，因此叫冒泡。
 */
public class _01_BubbleSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
