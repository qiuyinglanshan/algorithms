package cn.partner.algorithms.sort;

/**
 * 一个简单的双向链表
 */

class LinkedList<T> {
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

    @Override
    public String toString() {
        Node<T> p = head;
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



class IntegerLinkedList extends LinkedList<Integer> {
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


    // test LinkedList sort()
    public static void main(String[] args) {
        IntegerLinkedList list = new IntegerLinkedList();
        list.add(6).add(29).add(4).add(6).add(8).add(6).add(2).add(19).add(22).add(12).add(0).add(3);

        System.out.println(list);
        list.quickSort();
        System.out.println(list);
    }
}
