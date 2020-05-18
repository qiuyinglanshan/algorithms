package cn.partner.algorithms.interview.linkedlist;

public class DeleteFromEnd {

    public static void main(String[] args) {
        Node node1 = new Node("7", null);
        Node node2 = new Node("6", node1);
        Node node3 = new Node("5", node2);
        Node node4 = new Node("4", node3);
        Node node5 = new Node("3", node4);
        Node node6 = new Node("2", node5);
        Node node7 = new Node("1", node6);
        System.out.println(node7);
        System.out.println("----");
        Node node = new DeleteFromEnd().deleteFromEnd(node7, 8);
        System.out.println(node);
    }



    public Node deleteFromEnd(Node head, int n) {
        Node quick, slow;
        quick = slow =head;
        int i = 0;
        while (i < n && quick != null) {
            quick = quick.next;
            i++;
        }
        if (i < n) {
            throw new IllegalArgumentException("n must not larger than the length of the linked list: " + i);
        }
        if (quick == null){
            return head.next;
        }

        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }
        Node toDelete = slow.next;
        slow.next = toDelete.next;
        return head;
    }
}

class Node {
    String data;
    Node next;

    Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        Node node = this;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.data);
            if (node.next != null) {
                sb.append(" -> ");
            }
            node = node.next;
        }
        return sb.toString();
    }
}