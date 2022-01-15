package com.recursion;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {

    Map<Integer, Integer> map = new HashMap<>();
    //Top Down Approach - Recursive Function (optional) with HashMap for memoization.
    private int td_dp(int index, int[] money) {

        //Base Cases
        if(index==0)
            return money[0];

        if(index==1)
            return Math.max(money[0], money[1]);

        //Recurrence Relation -- if I am at i position to rob, then I can calculate the max amount to rob is
        // either by robbing - money[i] + maximumAmount(i-2) ..money from that house with amount robbed from i-2 position house. i-1 cannot be robbed.
        // or not robbing - maximumAmount(i-1) ..getting the amount of money robbed till i-1 position house.
        // SO at i, max amount robbed will be : Max(maximumAmount(i-1), money[i] + maximumAmount(i-2))
        if( !map.containsKey(index))
            map.put(index, Math.max(
                    td_dp(index-1, money),
                    money[index] + td_dp(index-2, money)));

        return map.get(index);
    }

    //Bottom-up Approach: Use Arrays and Iterations
    private int bu_dp(int[] money){
        int[] dp = new int[money.length];

        //Base Cases
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);

        for(int i=2; i<money.length; i++){
            //Recurrence Relation
            dp[i] = Math.max(dp[i-1], money[i]+dp[i-2]);
        }

        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        HouseRobber obj = new HouseRobber();
        int[] money = {2,7,9,6,1};
        System.out.println(obj.td_dp(money.length-1, money));

        System.out.println(obj.bu_dp(money));
    }
}
