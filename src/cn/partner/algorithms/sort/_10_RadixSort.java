package cn.partner.algorithms.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * 把数据按个位、十位、百位、...、最高位，依次进行排序，
 * 每次排序的方式是按%10的结果(0~9)映射进n=10的桶，再从桶中按顺序取出。
 * 当最高位出桶的时候就已经是有序的序列了。
 * 每一轮都是一个计数排序，只是桶就只有0~9这10个。思路很简单。
 *
 * 从结果上看，应该是创建链表节点对象的过程比较耗时。
 */
public class _10_RadixSort implements IArraySort {

    @Override
    public void sort(int[] arr) {
        int max = getMax(arr);
        int bit = 1;
        do {
            sort(arr, bit);
            bit = bit * 10;
        } while ((max = max / 10) != 0);
    }

    // 125ms
//    private void sort(int[] arr, int bit) {
//        int[][] buckets = new int[10][];
//        for (int i = 0; i < arr.length; i++) {
//            int index = arr[i] / bit % 10;
//            int[] bucket = buckets[index];
//            if (bucket == null) {
//                bucket = new int[1];
//            } else {
//                bucket = Arrays.copyOf(bucket, bucket.length + 1);
//            }
//            bucket[bucket.length - 1] = arr[i];
//            buckets[index] = bucket;
//        }
//
//        int i = 0;
//        for (int index = 0; index < 10; index++) {
//            if(buckets[index] != null) {
//                int[] bucket = buckets[index];
//                if (bucket != null) {
//                    for (int value : bucket) {
//                        arr[i++] = value;
//                    }
//                }
//            }
//        }
//    }

    // 还是链表快，不白写啊！！！
    private void sort(int[] arr, int bit) {
        IntegerLinkedList[] buckets = new IntegerLinkedList[10];
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] / bit % 10;
            if (buckets[index] == null) {
                buckets[index] = new IntegerLinkedList();
            }
            buckets[index].add(arr[i]);
        }

        int i = 0;
        for (int index = 0; index < 10; index++) {
            if(buckets[index] != null) {
                LinkedList.Node<Integer> p = buckets[index].head;
                while (p != null) {
                    arr[i++] = p.value;
                    p = p.next;
                }
            }
        }
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

