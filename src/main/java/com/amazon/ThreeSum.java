package com.amazon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        TwoSum obj = new TwoSum();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int[] a = obj.twoSum( nums, -nums[i]);
            System.out.println(Arrays.toString(a));
            List<Integer> triplet = Arrays.stream(a).map(q -> nums[q]).boxed().collect(Collectors.toList());
            triplet.add(nums[i]);
            lists.add(triplet);
        }
        return lists.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        System.out.println(obj.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }


}
