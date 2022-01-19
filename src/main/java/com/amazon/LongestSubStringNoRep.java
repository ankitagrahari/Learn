package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubStringNoRep {

    public static int lengthOfLongestSubstring(String s) {
        int start=0, end=0;
        Map<Character, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        if(s.length()==1)
            return 1;

        while(end>=start && end<s.length() && start<s.length()){
            if(map.containsKey(s.charAt(end))){
                list.add(s.substring(start, end));
                map.clear();
                start++;
                end = start-1;
            } else {
                map.put(s.charAt(end), 1);
            }
            end++;
        }
        if(end==s.length() && list.size()==0){
            return s.length();
        }
        System.out.println(list);
        return list.size()>0 ? list.stream().map(a -> a.length()).max(Integer::compareTo).get() : 0;
    }

    public static void main(String[] args) {
        System.out.println(LongestSubStringNoRep.lengthOfLongestSubstring("aab"));
    }
}
