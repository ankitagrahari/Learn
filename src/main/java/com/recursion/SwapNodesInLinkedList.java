package com.recursion;

import java.util.List;

public class SwapNodesInLinkedList {

    public ListNode swapPairs(ListNode head){
        if(head==null || head.next==null)
            return head;

        ListNode temp = head.next;
        head.next = swapPairs(temp.next);;
        temp.next = head;

        return temp;
    }

    protected ListNode prepareLL(int[] arr){
        ListNode head = new ListNode(arr[0]);
        ListNode start = head;
        for(int i=1; i<arr.length; i++){
            head.next = new ListNode(arr[i]);
            head = head.next;
        }

        return start;
    }

    protected void traverseLL(ListNode head){
        while(head!=null){
            System.out.print(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        SwapNodesInLinkedList obj = new SwapNodesInLinkedList();
        int[] arr = {1, 2, 3, 4};

        ListNode head = obj.prepareLL(arr);
        obj.traverseLL(head);
        ListNode node = obj.swapPairs(head);
        obj.traverseLL(node);
    }
}

class ListNode{
    int val;
    ListNode next;

    ListNode(){}

    ListNode(int val){
        this.val = val;
    }

    ListNode(ListNode node, int val){
        this.val = val;
        this.next = node;
    }
}
