package com.amazon;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode sumNode = new ListNode();
        while(null!=l1 || null!=l2){
            int sum = l1.val + l2.val;
            sum = 0<carry && carry<10 ? sum+carry : sum;
            carry = sum/10;
            sumNode.next = new ListNode(carry%10);
            l1 = l1.next;
            l2 = l2.next;
        }
        return sumNode;
    }

    private ListNode createLL(int[] arr){
        ListNode l1 = new ListNode(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            l1.next = new ListNode(arr[i]);
        }
        return l1;
    }

    private void traverseLL(ListNode l1){
        while(null!=l1){
            System.out.print(l1.val+"-->");
            l1 = l1.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AddTwoNumbers obj = new AddTwoNumbers();
        ListNode l1 = obj.createLL(new int[]{2, 4, 3});
        ListNode l2 = obj.createLL(new int[]{5, 6, 4});
        obj.traverseLL(l1);
        obj.traverseLL(l2);

        ListNode l = obj.addTwoNumbers(l1, l2);
        obj.traverseLL(l);
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
