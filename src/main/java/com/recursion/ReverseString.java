package com.recursion;

import java.util.Arrays;

public class ReverseString {

    public void reverseString(char[] s) {
        helper(s.length-1, s);
    }

    public void helper(int index, char[] s){
        if(index<0){
            return;
        }

        String temp = new String(s).substring(0, index);
        System.out.println(s[index]);
        index--;
        helper(index, temp.toCharArray());
    }

    //You must do this by modifying the input array in-place with O(1) extra memory.
    public void helper(int start, int end, char[]  s){
        if(start<0 || end>s.length || start>end)
            return;
        System.out.println("--start:"+start+", end:"+end+"--"+Arrays.toString(s));
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        helper(start+1, end-1, s);
    }

    public static void main(String[] args) {
        ReverseString obj = new ReverseString();
        char[] s = "hello".toCharArray();
//        obj.reverseString(s.toCharArray());
        obj.helper(0, s.length-1, s);
        System.out.println(Arrays.toString(s));
    }
}
