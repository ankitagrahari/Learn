package com.visa;

import java.util.*;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 *
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {

    private LinkedHashMap<Integer, Integer> cache;
    int MAX_CAPACITY;

    public LRUCache(int capacity) {
        MAX_CAPACITY = capacity;
        cache = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        int value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if(cache.size()==MAX_CAPACITY){
            if(cache.containsKey(key)) {
                cache.remove(key);
                cache.put(key, value);
            } else {
                Map.Entry<Integer, Integer> first = cache.entrySet().iterator().next();
                cache.remove(first.getKey());
                cache.put(key, value);
            }
        } else {
            if(cache.containsKey(key)){
                cache.remove(key);
            }
            cache.put(key, value);
        }
    }

    public static void main(String[] args) {

//["LRUCache","get","put","get","put","put","get","get"]
//[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        String[] opr = {"LRUCache","put","put","put","put","get","get"};
        int[][] inputs = {{2}, {2,1}, {1, 2}, {2, 3}, {4, 1}, {1}, {2}};
        Integer[] output = new Integer[opr.length];

        LRUCache cache = null;
        for(int i=0; i<opr.length; i++){
            if(opr[i]=="LRUCache"){
                cache = new LRUCache(inputs[i][0]);
                output[i] = null;
            }
            if(opr[i]=="put"){
                if(null!=cache) {
                    cache.put(inputs[i][0], inputs[i][1]);
                    output[i] = null;
                }
            }
            if(opr[i]=="get"){
                if(null!=cache){
                    int tmp = cache.get(inputs[i][0]);
                    output[i] = tmp;
                }
            }
        }

        System.out.println(Arrays.toString(output));
    }
}
