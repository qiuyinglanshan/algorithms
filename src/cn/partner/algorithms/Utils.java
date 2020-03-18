package cn.partner.algorithms;

import cn.partner.algorithms.linkedlist.Node;
import cn.partner.algorithms.sort.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {

    public static void print(int[] arr) {
        System.out.println(Arrays.stream(arr).mapToObj(Integer::toString)
                .collect(Collectors.joining(", ")));
    }
    public static void print(Node<Integer> linkedList) {
        System.out.print(linkedList.value);
        if (linkedList.next != null) {
            System.out.print(", ");
            print(linkedList.next);
        }
    }

    public static Node<Integer> arrayToLinkedList(int[] arr) {
        return createNode(arr, 0);
    }

    private static Node<Integer> createNode(int[] arr, int i) {
        if (i == arr.length) {
            return null;
        }
        Node<Integer> node = new Node<>();
        node.value = arr[i];
        node.next = createNode(arr, i + 1);
        return node;
    }

    public static int[] gen(int bound, int n) {
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(bound);
        }
        return arr;
    }

    public static Node<Integer> readAsLinkedList(String file) {
        int[] arr = readAsArray(file);
        Node<Integer> linkList = arrayToLinkedList(arr);
        return linkList;
    }

    public static Node<Integer> tail(Node<Integer> list) {
        Node<Integer> p = list;
        while(p.next != null) {
            p = p.next;
        }
        return p;
    }
    public static void concat(Node<Integer> list1, Node<Integer> list2) {
        tail(list1).next = list2;
    }

    public static int[] readAsArray(String file) {
        int[] arr = null;
        InputStream is = null;
        try {
            String path = Test.class.getResource(file).getPath();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            StringBuffer sb = new StringBuffer();
            String s;
            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            String[] strings = sb.toString().split(",");
            arr = Arrays.stream(strings).map(String::trim).mapToInt(Integer::new).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
