package cn.partner.algorithms.linkedlist;

/**
 * 链表
 *
 * @param <T> 数据类型
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
