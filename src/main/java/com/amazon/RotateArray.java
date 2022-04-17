package com.amazon;

import java.util.Arrays;

public class RotateArray {

    //Function to rotate an array by d elements in counter-clockwise direction.
    // Rotate Array one by one
    //2,4,6,8,10,12,14,16,18,20  -> 8 10 12 14 16 18 20 2 4 6   rotated by 3
    static void leftRotateArrbyK(int arr[], int d, int n){

        for(int i=1; i<=d; i++){
            int temp = arr[0];
            for(int j=1; j<n; j++){
                arr[j-1] = arr[j];
            }
            arr[n-1] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    private static void reverse(int[] a, int from, int to){
        for(int i=from, j=to; i<j; i++, j--){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    static void rightRotateByK(int[] arr, int d, int n){

        if(d<0) return;
        if(d>n) d=d%n;

        // Reverse array for last d elements
        reverse(arr, n-d, n-1);
        // Reverse array for n-d elements
        reverse(arr, 0, n-d-1);
        // Reverse now the entire array
        reverse(arr, 0, n-1);

        System.out.println(Arrays.toString(arr));
    }

    static void leftRotateByK(int[] arr, int d, int n){

        if(d<0) return;
        if(d>n) d=d%n;

        // Reverse array for last d elements
        reverse(arr, 0, d-1);
        // Reverse array for n-d elements
        reverse(arr, d, n-1);
        // Reverse now the entire array
        reverse(arr, 0, n-1);

        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        int[] a= {2,4,6,8,10,12,14,16,18,20};
        // 6, 4, 2, 20, 18, 16, 14, 12, 10, 8
        // 8, 10, 12, 14, 16, 18, 20, 2, 4, 6
//        RotateArray.rotateArrbyK(a, 3, a.length);
        RotateArray.rightRotateByK(a, 3, a.length);
        RotateArray.leftRotateByK(a, 3, a.length);

    }
}
