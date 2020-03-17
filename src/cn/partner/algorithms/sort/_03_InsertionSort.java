package cn.partner.algorithms.sort;

/**
 * 插入排序
 *
 * 每轮找地i个节点应该的位置，此时前(0 ~ i-1)个节点已经排序好了。
 * 每轮从新节点向前遍历排序好了的数据, 查找里头比它大(小)的位置插入。
 * 查找过程中，将比较了的的节点后移。
 */
public class _03_InsertionSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {

            int j = i;
            // 暂存新节点数据
            int tmp = arr[j];
            // 向前遍历已排好序的节点
            while(arr[j - 1] > tmp) {
                arr[j] = arr[j - 1];
                if (--j == 0) {
                    break;
                }
            }
            // 循环结束，将tmp插到这个位置
            arr[j] = tmp;
        }
    }
}
