package com.recursion;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {

    Map<Integer, Integer> map = new HashMap<>();
    /**
     * Base Case:
     * T(0)=0, T(1)=1, T(2)=1
     *
     * Recurrence Relation:
     * T(n) = T(n-3) + T(n-2) + T(n-1)
     *
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if(n==0) return 0;
        if(n==1 || n==2) return 1;

        if(!map.containsKey(n))
            map.put(n, tribonacci(n-3) + tribonacci(n-2) + tribonacci(n-1));
        return map.get(n);
    }

    public int bu_tribonacci(int n) {
        int[] dp = new int[n+1];
        dp[0]=0; dp[1]=1; dp[2]=1;
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Tribonacci obj = new Tribonacci();
        System.out.println(obj.tribonacci(37));
        System.out.println(obj.bu_tribonacci(37));
    }

}
