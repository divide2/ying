package test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author bvvy
 * @date 2018/10/16
 */
public class Al {


}

class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int key = arr[lo];
        while (true) {
            while (arr[++i] < key) {
                if (i == hi) break;
            }
            while (arr[--j] > key) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    private static void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 7, 6, 5, 2, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

class LinkList {

    static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverse(Node head) {
        Node p = head.next;
        Node q = head;
        head.next = null;
        while (p != null) {
            Node temp = p.next;
            p.next = q;
            q = p;
            p = temp;
        }
        return q;
    }


    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        Node reverse = reverse(a);
        while (reverse != null) {
            System.out.println(reverse.value);
            reverse = reverse.next;
        }
        LinkList linkList = new LinkList();

        Thread ta = new Thread(new ThreadA(linkList));
        Thread tb = new Thread(new ThreadB(linkList));
        ta.start();
        tb.start();
    }
}

class ThreadA implements Runnable {
    private final LinkList linkList;

    ThreadA(LinkList linkList) {
        this.linkList = linkList;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 1; i < 10; i++) {
                synchronized (linkList) {
                    System.out.print(i);
                    linkList.notifyAll();
                    try {
                        linkList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ss");
                }
            }
        }
    }
}

class ThreadB implements Runnable {

    private final LinkList linkList;

    ThreadB(LinkList linkList) {
        this.linkList = linkList;
    }
    @Override
    public void run() {
        while (true) {
            for (char i = 'a'; i < 'z'; i++) {
                synchronized (linkList) {
                    System.out.print(i);
                    linkList.notifyAll();
                    try {
                        linkList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }
}

class A {
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        b.left = f;
        printN(a);
    }

    private static void print(Node a) {
        if (a == null) {
            return;
        }
        System.out.println(a.value);
        print(a.left);
        print(a.right);
    }

    private static void printN(Node a) {
        Stack<Node> nodes = new Stack<>();
        nodes.push(a);
        while (!nodes.isEmpty()) {
            Node p = nodes.pop();
            if (p != null) {
                System.out.println(p.value);
                nodes.push(p.right);
                nodes.push(p.left);
            }

        }

    }

}
