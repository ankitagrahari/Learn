package com.amazon;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length-1;
        int maxArea = 0;
        while(start<end){
            int area = Math.min(height[start], height[end]) * (end-start);
            maxArea = Math.max(maxArea, area);

            if(height[start]<height[end])
                start++;
            else
                end--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater obj = new ContainerWithMostWater();
        System.out.println(obj.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(obj.maxArea(new int[]{3,9,3,4,7,2,12,6}));
    }
}
