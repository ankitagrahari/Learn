package com.visa;

public class FlattenLinkedList {

    static ListNode flattenTheLinkedList(ListNode root) {
        ListNode result = root;
        while(root!=null){
            result.next = merge(root, root.next);
            root = root.next;
        }
        return result;
    }

    /**
     * 1-2-5-10
     * | | | |
     * 4 3 9 11
     * | |   |
     * 6 7   12
     * |
     * 8
     *
     * @param node1
     * @param node2
     * @return
     */
    static ListNode merge(ListNode node1, ListNode node2){
        ListNode node;
        if(node1 == null)
            return node2;
        else if(node2 == null)
            return node1;

        if(node1.data < node2.data){
            node = node1;
            node.down = merge(node1.down, node2);
        } else {
            node = node2;
            node.down = merge(node1, node2.down);
        }
        return node;
    }

    public static ListNode push(ListNode head, int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = null;
        newNode.down = head;

        return newNode;
    }

    public static ListNode createVerticalList(ListNode head, int[] arr) {
        for (int key: arr) {
            head = push(head, key);
        }
        return head;
    }

    public static void printList(ListNode head) {
        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.data + " —> ");
            ptr = ptr.down;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        int[] arr1 = { 8, 6, 4, 1 };
        int[] arr2 = { 7, 3, 2 };
        int[] arr3 = { 9, 5 };
        int[] arr4 = { 12, 11, 10 };
        ListNode node = createVerticalList(null, arr1);
        node.next = createVerticalList(node, arr2);
        node.next.next = createVerticalList(node.next, arr3);
        node.next.next.next = createVerticalList(node.next.next, arr4);

        ListNode result = flattenTheLinkedList(node);
        printList(result);
    }
}

class ListNode {
    int data;
    ListNode next;
    ListNode down;

    ListNode(int data) {
        this.data = data;
        this.next = null;
        this.down = null;
    }
}
