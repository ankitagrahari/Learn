package com.recursion;

public class MinCostClimbing {


    /**
     * Top Down Approach
     *
     * Base Case:
     * minCost(0) = 0
     * minCost(1) = 1
     *
     * Recurrence Relation:
     * minCost(i) = Math.min(minCost(i-1)+cost[i], minCost(i-2)+cost[i])
     *
     * @param cost
     * @return
     */
    private int dp(int index, int[] cost){

        if(index==0)
            return cost[0];

        if(index==1)
            return cost[1];

        return Math.min(
                dp(index-1, cost) + cost[index],
                dp(index-2, cost) + cost[index]);
    }

    private int bu_dp(int[] cost){
        int[] dp = new int[cost.length];

        //Base Cases
        dp[0] = cost[0];
        dp[1] = cost[1];

        //Iteration
        for(int i=2; i<cost.length; i++){
            //Recurrence Relation
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }

        return dp[dp.length-1];
    }

    public int minCostClimbingStairs(int[] cost) {
        return dp(cost.length-1, cost);
    }

    public static void main(String[] args) {
        MinCostClimbing obj = new MinCostClimbing();
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        System.out.println(obj.minCostClimbingStairs(cost));
        System.out.println(obj.bu_dp(cost));
    }
}
