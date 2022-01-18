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
     *
     * GodSend: S[k] = MAX(S[k - 1], S[k - 2] + A[k] * k)
     */
    private int dp(int index, int[] set, Map<Integer, Integer> map){
        if(map.size()==0)
            return 0;

        int value = set[index];
        if(map.containsKey(value)) {

            if (map.containsKey(value + 1))
                map.remove(value + 1);
            if (map.containsKey(value - 1))
                map.remove(value - 1);

            return value * map.get(value) + dp(index+1, set, map);
        }

        return 0;
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

        int[] set = new int[frequency.size()];
        int i=0;
        for(Map.Entry<Integer, Integer> e : frequency.entrySet()){
            set[i] = e.getKey();
        }

        Map<Integer, Integer> map = frequency;
        System.out.println(dp(0, set, map));

        return 0;
    }

    public static void main(String[] args) {
        DeleteAndEarn obj = new DeleteAndEarn();
        int[] nums = {2,2,3,3,3,4};
        System.out.println(obj.deleteAndEarn(nums));
    }
}
