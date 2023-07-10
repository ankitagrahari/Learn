package com.mustdo.tree;

import java.util.Arrays;
import java.util.List;

public class LeftView {

    public static void main(String[] args) {
        LeftView obj = new LeftView();
        int[] arr = {40, 20, 60, 30, 10};
        Node root = obj.prepareTree(arr);
        obj.inOrderTraversal(root);
    }
    
    Node prepareTree(int[] arr){
        // Sort the array
        Arrays.sort(arr);
        
        int min = 0;
        int max = arr.length-1;
        
        return prepareTree(min, max, arr, null);
    }
    
    private Node prepareTree(int min, int max, int[] arr, Node root){
        Node node = null;
        //Base Case 1
        if (min == max){
            return root;
        } else if(min < max){
            int median = (min+max)%2==0 ? (min+max)/2 : (min+max+1)/2;
            root = new Node(arr[median]);
            root.left = prepareTree(min, median-1, arr, root);
            root.right = prepareTree(median+1, max, arr, root);
        }
        return root;
    }
    
    void inOrderTraversal(Node root){
        System.out.print(root.data + "-->");
        if(null!=root.left)
            inOrderTraversal(root.left);
        if(null!=root.right)
            inOrderTraversal(root.right);
    }
    
    void postOrderTraversal(Node root){
        System.out.println(root.data);
        if(null!=root.right)
            postOrderTraversal(root.right);
        if(null!=root.left)
            postOrderTraversal(root.left);
    }
}


class Node {
    int data;
    Node left, right;
    
    Node(int item) {
        data = item;
        left = right = null;
    }
}