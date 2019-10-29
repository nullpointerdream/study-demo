package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-24 14:55
 **/

public class SearchTopK {
    public static void main(String[] args) {
        int[] arr2={3};
        System.out.println(findKthLargest(arr2,1));

        Arrays.sort(SortUtil.createRamdom(20));
        List<String> strings = Arrays.asList("6", "1", "3", "1","2");

        Collections.sort(strings);//sort方法在这里
       // Collections.sort(SortUtil.createRamdom(20));

    }

    public static int findKthLargest(int[] arr, int k) {
        return sort(arr,0,arr.length-1, arr.length-k);
    }

    private static int sort(int[] arr, int lo, int hi,int key) {
        if(lo>=hi){
            return arr[lo];
        }
        int j =partion(arr,lo,hi);
        if(j==key){
            return arr[j];
        }else if(j<key){
            return sort(arr,j+1,hi,key);
        }else {
           return sort(arr,lo,j-1,key);
        }

    }

    private static int partion(int[] arr, int lo, int hi) {
        int key = arr[lo];
        int start=lo;
        int end=hi+1;
        while (true){
            while (less(arr[++start],key) && start<hi){

            }

            while (less(key,arr[--end])){

            }

            if(end<=start){
                break;
            }
           exchange(arr,start,end);
        }

        exchange(arr,end,lo);
        return end;
    }

    private static void exchange(int[] arr, int start, int end) {
        int tem=arr[start];
        arr[start]=arr[end];
        arr[end]=tem;
    }

    private static boolean less(int i, int key) {
        return i<key;
    }
}
