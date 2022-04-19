package com.amazon;

/**
 * Given two sorted arrays A and B of size M and N respectively. Each array may have some elements in common with the
 * other array. Find the maximum sum of a path from the beginning of any array to the end of any of the two arrays.
 * We can switch from one array to another array only at the common elements which are present at the any index.
 * Note: Only one repeated value is considered in the valid path sum.
 * Example 1:
 *
 * Input:
 * M = 5, N = 4
 * A[] = {2,3,7,10,12}
 * B[] = {1,5,7,8}
 * Output: 35
 * Explanation: The path will be 1+5+7+10+12
 * = 35.
 *
 * Your Task:
 * You don't need to read input or print anything. Complete the function max_path_sum() which takes the two arrays A
 * and B along with their sizes M and N as input parameters. It returns the maximum path sum.
 *
 * Expected Time Complexity: O(M + N)
 * Expected Auxiliary Space: O(1)
 */
public class MaxSumPath {

    int maxPathSum(int ar1[], int ar2[]) {
        int i=0, j=0, commonIndex1=0, commonIndex2=0;

        // Since we have to pick where commonIndex1 == commonIndex2
        while(i<ar1.length && j<ar2.length){
            if(ar1[i]<ar2[j]) i++;
            else if(ar1[i]>ar2[j]) j++;
            else if(ar1[i]==ar2[j]){
                commonIndex1 = i;
                commonIndex2 = j;
                break;
            }
        }

        int ar1Left=0, ar1Right=0, ar2Left=0, ar2Right=0;
        for(int k=0; k<ar1.length; k++){
            if(k<commonIndex1) ar1Left += ar1[k];
            else if(k>commonIndex1) ar1Right += ar1[k];
        }

        for(int k=0; k<ar2.length; k++){
            if(k<commonIndex2) ar2Left += ar2[k];
            else if(k>commonIndex2) ar2Right += ar2[k];
        }

        if((ar1Left + ar2Right) > (ar2Left + ar1Right)){
            return ar1Left + ar2Right + ar1[commonIndex1];
        } else {
            return ar2Left + ar1Right + ar2[commonIndex2];
        }
    }

    public static void main(String[] args) {
        MaxSumPath obj = new MaxSumPath();

        int[] ar1 = {2,3,7,10,12};
        int[] ar2 = {1,5,7,8};
        System.out.println(obj.maxPathSum(ar1, ar2));
    }

}
