package cn.partner.algorithms.sort;

/**
 * 希尔排序
 *
 * 是插入排序的升级版。
 * 从理论上来说明，确实不容易接受，因此直接直接记住按照一定步长进行插入排序。
 * 初始步长为n/2,排一趟。
 * 之后n/4排一趟,n/8排一趟...，直到步长为1，最后一趟排序就完事。
 *
 * 思想就是看到了插入排序的弊端是每次都需要跟排好了的对比，再插入，依赖于整个数组的顺序程度。
 * 当数组比较乱序的时候，插入排序是O(n²)，性能不好。
 * 希尔排序则是先让数组相对比较有序的情况下，局部排好的情况下进行一次插入排序。
 */
public class _04_ShellSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        for (int step = arr.length / 2; step >= 1; step = step / 2) {
            for (int i = step; i < arr.length; i++) {
                int j = i;
                int tmp = arr[j];
                while (arr[j - step] > tmp) {
                    arr[j] = arr[j - step];
                    if ((j = j - step) < step) {
                        break;
                    }
                }
                arr[j] = tmp;
            }
        }
    }
}
