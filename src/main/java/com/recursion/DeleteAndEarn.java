package com.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DeleteAndEarn {

    Map<Integer, Integer> frequency = new HashMap<>();

    /**
     * State:
     * at i index, the state will define the maximum points earned from i to the end of the sorted array
     * there is only variable index, which will be responsible
     *
     * Base Case:
     * index reaches the end of the array
     *
     * Recurrence Relation:
     * delete the element and delete all occurrence of element+1 and element-1 in sorted array
     *
     * @param index
     * @return
     */
    private int dp(int index, int[] nums){
        if(frequency.size()==0) {
            return nums[index-1];
        } else {
            int value = nums[index];
            int count = 0;
            if (frequency.containsKey(value)) {
                count = frequency.get(value);

                if (count > 1) {
                    frequency.put(value, count - 1);
                } else if (count == 1) {
                    frequency.remove(value);
                }
                if (frequency.containsKey(value + 1))
                    frequency.remove(value + 1);
                if (frequency.containsKey(value - 1))
                    frequency.remove(value - 1);

                return value + dp(index + 1, nums);
            } else {
                return dp(index + 1, nums);
            }
        }
    }

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for(int i:nums){
            if (frequency.containsKey(i)) {
                frequency.put(i, frequency.get(i) + 1);
            } else {
                frequency.put(i, 1);
            }
        }
        System.out.println(frequency);

        return dp(0, nums);
    }

    public static void main(String[] args) {
        DeleteAndEarn obj = new DeleteAndEarn();
        int[] nums = {2,2,3,3,3,4};
        System.out.println(obj.deleteAndEarn(nums));
    }
}
