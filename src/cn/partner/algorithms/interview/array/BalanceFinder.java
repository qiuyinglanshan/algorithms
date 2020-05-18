package cn.partner.algorithms.interview.array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 平衡点问题
 * 一个数组中的元素，如果其前面的部分等于后面的部分，那么这个点的位序就是平衡点。
 * 比如列表numbers = [1, 3, 5, 7, 8, 25, 4, 20]，25前面的元素总和为24，25后面的元素总和也是24，那么25就是这个列表的平衡点。
 *
 * 要求编写程序，寻找并返回任意一个列表的所有平衡点。
 */
public class BalanceFinder {

    public static void main(String[] args) {
        int[] numbers = new int[] {1, 3, 5, 7, 8, 25, 4, 20};
        int[] result = findBalance(numbers);
        if (result != null) {
            String str = Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
            System.out.println(str);
        }
    }

    public static int[] findBalance(int[] arr) {
        if (arr.length < 3) {
            return null;
        }
        int[] result = new int[0];

        int beforeSum = 0;
        int afterSum = 0;

        // get sum
        for (int j = arr.length - 1; j > 1; j--) {
            afterSum += arr[j];
        }

        for (int i = 0; i < arr.length - 2; i++) {
            beforeSum += arr[i];
            if (beforeSum == afterSum) {
                result = push(arr[i + 1], result);
            }
            afterSum = afterSum - arr[i + 2];
        }
        return result;
    }

    private static int[] push(int i, int[] result) {
        result = Arrays.copyOf(result, result.length + 1);
        result[result.length - 1] = i;
        return result;
    }
}

