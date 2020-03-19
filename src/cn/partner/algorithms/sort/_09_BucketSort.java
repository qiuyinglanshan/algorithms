package cn.partner.algorithms.sort;

import java.util.Arrays;

/**
 * 桶排序
 *
 * 算法是将数组中的数据按照数值的大小映射到指定个数的桶中，将每个桶中的数据排序(使用是其他排序算法)之后，再复制回数组中，即是有序了。
 * 算法的数据结构类似HashMap，数组+链表/数组。
 * 因此也可以看出，本算法比较适合平均分布的数据，这样冲突会比较少，空桶的情况也会比较少。
 *
 * 1. 桶中数据结构是数组的情况下，当桶数(1~n)越大越快，桶数少的话，每次的copyArray比较费时。
 * 2. 桶中数据结构是链表的情况下, 桶数变小，时间也比较平缓，因为“冲突”的时候，不会每次copy数据。
 * 所以，可以根据桶数，来决定使用哪种数据结构。
 *
 * 本例中测试:
 * 数组结构：当桶数 < 300的情况下，的效率会出现明显的降低。当桶数为3w(arr.length)的时候，耗时6ms。
 * 链表结构： 8 ~ 10ms左右。
 */

// Non-thread-safe
public class _09_BucketSort implements IArraySort {

    private int bucketCount;
    public _09_BucketSort() {}

    public _09_BucketSort(int bucketCount) {
        this.bucketCount = bucketCount;
    }

    @Override
    public void sort(int[] arr) {
        if (bucketCount == 0) {
            bucketCount = arr.length;
        }


        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // IntegerLinkedList[] buckets = new IntegerLinkedList[bucketCount];
        int[][] buckets = new int[bucketCount][];

        int step = (max - min) / bucketCount + 1;
        for(int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / step;
            putInBucket(arr[i], buckets, index);
        }
        // sort buckets and iterate value from each bucket
        takeFromBucket(arr, buckets);
    }

    private void putInBucket(int value, int[][] buckets, int index) {
        int[] bucket = buckets[index];
        if (bucket != null) {
            // 系统的copy方法比自己一个个复制过去要快，估计是批量操作。
            bucket = Arrays.copyOf(bucket, bucket.length + 1);
//            int[] newBucket = new int[bucket.length + 1];
//            for (int i = 0; i < bucket.length; i++) {
//                newBucket[i] = bucket[i];
//            }
//            bucket = newBucket;
        } else {
            bucket = new int[1];
        }
        bucket[bucket.length -1] = value;
        buckets[index] = bucket;
    }

    private void takeFromBucket(int[] arr, int[][] buckets) {
        int j = 0;
        _06_QuickSort quickSort = new _06_QuickSort();
        for (int i = 0; i < bucketCount; i++) {
            int[] bucket = buckets[i];
            if (bucket != null) {
                quickSort.sort(bucket);
                for (int k : bucket) {
                    arr[j++] = k;
                }
            }
        }
    }



// -------------------------------------------------------------------------------
// Using Custom LinkedList begin ↓


    private void putInBucket(int value, IntegerLinkedList[] buckets, int index) {
        IntegerLinkedList list = buckets[index];
        if (list == null) {
            list = new IntegerLinkedList();
            buckets[index] = list;
        }
        list.add(value);
    }

    private void takeFromBucket(int[] arr, IntegerLinkedList[] buckets) {
        int j = 0;
        for (int i = 0; i < bucketCount; i++) {
            IntegerLinkedList list = buckets[i];
            if (list != null) {
                // sort each bucket
                list.quickSort();
                // iterator list in bucket
                LinkedList.Node<Integer> p = list.head;
                while (p != null) {
                    arr[j++] = p.value;
                    p = p.next;
                }
            }
        }
    }

    // test LinkedList sort()
    public static void main(String[] args) {
        IntegerLinkedList list = new IntegerLinkedList();
        list.add(6).add(29).add(4).add(6).add(8).add(6).add(2).add(19).add(22).add(12).add(0).add(3);

        System.out.println(list);
        list.quickSort();
        System.out.println(list);
    }

    private static class IntegerLinkedList extends LinkedList<Integer> {
        void quickSort() {
            quickSort(head, tail);
        }

        // quick sort
        void quickSort(Node<Integer> start, Node<Integer> end) {
            if (start == null || end == null ||start == end || start == end.next) {
                return;
            }
            int p = start.value;
            Node<Integer> i = start;
            Node<Integer> j = end;

            boolean forward = false;
            while (i != j) {
                if (!forward) {
                    if (j.value < p) {
                        swap(i , j);
                        i = i.next;
                        forward = true;
                    } else {
                        j = j.prev;
                    }
                } else {
                    if (i.value > p) {
                        swap(i , j);
                        j = j.prev;
                        forward = false;
                    } else {
                        i = i.next;
                    }
                }
            }
            quickSort(start, i.prev);
            quickSort(i.next, end);
        }

        private void swap(Node<Integer> i, Node<Integer> j) {
            int tmp = i.value;
            i.value = j.value;
            j.value = tmp;
        }
    }

    private static class LinkedList<T> {
        Node<T> head;
        Node<T> tail;

        LinkedList<T> add(T value) {
            Node<T> node = new Node<T>();
            node.value = value;

            if (tail != null) {
                tail.next = node;
                node.prev = tail;
                tail = node;
            } else {
                head = tail = node;
            }
            return this;
        }
        static class Node<T> {
            T value;
            Node<T> next;
            Node<T> prev;
        }

        public String toString() {
            LinkedList.Node<T> p = head;
            StringBuilder sb = new StringBuilder("[");
            while (p != null) {
                if (p != head) {
                    sb.append(", ");
                }
                sb.append(p.value.toString());
                p = p.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }

}
