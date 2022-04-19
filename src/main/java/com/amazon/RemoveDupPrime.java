package com.amazon;

import java.util.ArrayList;

/**
 * Given an array consisting of only prime numbers, remove all duplicate numbers from it.
 *
 * Example 1:
 *
 * Input:
 * N = 6
 * A[] = {2,2,3,3,7,5}
 * Output: 2 3 7 5
 * Explanation: After removing the duplicate
 * 2 and 3 we get 2 3 7 5.
 * Your Task:
 *
 * Complete the function removeDuplicate() that takes the array of integers and N as input parameters and returns the
 * modified array which has no duplicates. Retain only the first occurrence of the duplicate element. The elements in
 * the returning array should be in the same order as they appear in the original array.
 *
 * Expected Time Complexity: O(N).
 * Expected Auxiliary Space: O(1).
 *
 * Constraints:
 * 1<=N=106
 * 2<=A[i]<100
 */
public class RemoveDupPrime {

    // one approach is to use the HashSet or ArrayList to check if value is present or not. else add it to the list
    // This other approach works for a limited set of array, where the prime numbers product is not crossing some higher
    // range. This uses the prime features, which states that it can be divided by 1 or itself. So if the product is
    // divided by the next number in array, then dont add it to the list
    ArrayList<Integer> removeDuplicate(int arr[], int n) {
        ArrayList<Integer> unique = new ArrayList<>();
        int product = 1;
        for (int i = 0; i < n; i++) {
            if(product%arr[i]==0){
                continue;
            } else {
                product *= arr[i];
                unique.add(arr[i]);
            }
        }
        return unique;
    }

    public static void main(String[] args) {
        RemoveDupPrime obj = new RemoveDupPrime();
        int[] a = {37, 47, 43, 43, 43, 37, 7, 5, 2, 2, 3, 41, 11, 13, 37, 2, 3, 43, 11, 5, 5, 17, 5, 2, 41, 2, 31, 3, 43, 19, 31, 31, 11, 19, 17, 7, 11, 19, 13, 5, 23, 13, 47, 37, 29, 29, 37, 23, 19, 43, 37, 19, 13, 47, 23, 2, 41, 11, 2, 37, 37, 37, 17, 47, 3, 37, 5, 7, 11, 17, 7, 31, 37, 7, 17, 7, 37, 2, 41, 3, 41, 29, 29, 5, 17, 2, 5, 7, 11, 7, 2, 47, 41, 11, 43, 37, 3, 43, 47, 7, 5, 7, 47, 37, 13, 11, 2, 47, 7, 43, 47, 2, 13, 17, 2, 37, 19, 3, 47, 31, 5, 41, 29, 47, 5, 13, 23, 5, 3, 17, 11, 11, 23, 3, 3, 37, 13, 47, 37, 19, 41, 23, 19, 2, 47, 19, 41, 19, 23, 31, 5, 29, 13, 29, 17, 17, 41, 2, 23, 47, 19, 37, 5, 2, 41, 7, 31, 7, 3, 17, 23, 37, 47, 2, 41, 37, 17, 29, 11, 43, 5, 11, 19, 23, 37, 43, 2, 29, 43, 19, 29, 7, 7, 29, 5, 2, 41, 41, 5, 47, 3, 31, 31, 43, 29, 17, 31, 3, 47, 41, 41, 5, 47, 11, 29, 37, 2, 19, 13, 41, 47, 43, 3, 3, 19, 3, 47, 7, 37, 5, 2, 31, 37, 29, 17, 7, 3, 5, 5, 2, 41, 47, 2, 37, 5, 31, 13, 5, 5, 37, 2, 2, 19, 47, 3, 2, 47, 3, 3, 31, 3, 5, 11, 31, 29, 37, 47, 31, 37, 2, 29, 23, 47, 29, 5, 5, 5, 23, 5, 13, 5, 7, 7, 31};
        System.out.println(obj.removeDuplicate(a, a.length));
    }
}
