package cn.partner.algorithms.linkedlist;

import cn.partner.algorithms.Utils;

public class _01_FindIntersection {

//     370ms stupid algorithm
//    Node<Integer> findIntersectionStupid(Node<Integer> list1, Node<Integer> list2) {
//        Node<Integer> p1 = list1;
//        Node<Integer> p2 = list2;
//        while(p1 != null) {
//            while(p2 != null) {
//                if(p1 == p2) {
//                    return p1;
//                }
//                p2 = p2.next;
//            }
//              // reset p2 as head
//            p2 = list2;
//            p1 = p1.next;
//        }
//        return null;
//    }

    // 1ms
    Node<Integer> findIntersection(Node<Integer> list1, Node<Integer> list2) {
        Node<Integer> pLong, pShort;
        int length1 = getLength(list1);
        int length2 = getLength(list2);

        // 找到长指针
        if (length1 > length2) {
            pLong = list1;
            pShort = list2;
        } else {
            pLong = list2;
            pShort = list1;
        }
        // 长多少
        int offset = Math.abs(length1 - length2);
        while (offset-- > 0) {
            pLong = pLong.next;
        }

        while (pLong != null) {
            // 指针相同，说明找到了
            if (pLong == pShort) {
                return pLong;
            }
            pLong = pLong.next;
            pShort = pShort.next;
        }
        // 没找到
        return null;
    }

    <T> int getLength(Node<T> list) {
        int length = 0;
        Node<T> p = list;
        while (p != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    // Test
    public static void main(String[] args) {
//        Node<Integer> list = Utils.arrayToLinkedList(new int[]{100, 101, 102, 103, 104, 105});
//        Node<Integer> list1 = Utils.arrayToLinkedList(new int[]{1, 3, 5, 7, 9});
//        Node<Integer> list2 = Utils.arrayToLinkedList(new int[]{2, 4, 6});

        Node<Integer> list = Utils.readAsLinkedList("/long_numbers.txt");
        Node<Integer> list1 = Utils.readAsLinkedList("/middle_numbers.txt");
        Node<Integer> list2 = Utils.readAsLinkedList("/short_numbers.txt");
        Utils.concat(list1, list);
        Utils.concat(list2, list);
        System.out.printf("list1: ");
        Utils.print(list1);
        System.out.printf("\nlist2: ");
        Utils.print(list2);
        System.out.printf("\nSame data: ");
        Utils.print(list);
        long start = System.currentTimeMillis();
        Node<Integer> intersection = new _01_FindIntersection().findIntersection(list1, list2);
        long cost = System.currentTimeMillis() - start;
        System.out.printf("\n\nintersection: %d, cost: %dms\n", intersection.value, cost);
    }
}
