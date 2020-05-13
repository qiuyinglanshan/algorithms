package cn.partner.algorithms.interview;

public class PalindromeLink {

    public static void main(String[] args) {
        PalindromeLink pl = new PalindromeLink();
        LinkedNode head = pl.init();
        pl.print(head);
        LinkedNode mid = pl.findMid(head);
        System.out.println("mid:" + mid.data);

        System.out.println("\nresult: " + pl.check(head));
    }

    private LinkedNode init() {
        LinkedNode data6 = new LinkedNode(1, null);
        LinkedNode data5 = new LinkedNode(1, data6);
        LinkedNode data4 = new LinkedNode(2, data5);
        LinkedNode data3 = new LinkedNode(2, data4);
        LinkedNode data2 = new LinkedNode(1, data3);
        LinkedNode data1 = new LinkedNode(1, data2);
//        for (int i = 0; i < 7; i++) {
//            node = new LinkedNode();
//            node.data = i + 1;
//            if (head == null) {
//                head = node;
//            }
//            if (prev != null) {
//                prev.next = node;
//            }
//
//            prev = node;
//        }
        return data1;
    }

    private void print(LinkedNode head) {
        while (head != null) {
            System.out.print(head.data + ",");
            head = head.next;
        }
        System.out.println("\n");
    }

    public boolean check(LinkedNode head) {
        LinkedNode mid = findMid(head);
        LinkedNode rHead = reverse(mid);
        while (head != mid && rHead != mid) {
            if (head.data != rHead.data) {
                return false;
            }
            head = head.next;
            rHead = rHead.next;
        }
        return true;
    }

    public LinkedNode findMid(LinkedNode head) {
        LinkedNode slow;
        LinkedNode quick;
        quick = slow = head;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    public LinkedNode reverse(LinkedNode node) {
        LinkedNode prev = null;
        while (node != null) {
            LinkedNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}


class LinkedNode {
    int data;
    LinkedNode next;

    LinkedNode() {}

    LinkedNode(int data) {
        this.data = data;
    }
    LinkedNode(int data, LinkedNode next) {
        this.data = data;
        this.next = next;
    }
}
