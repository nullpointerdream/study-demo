package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 快速排序
 * @author: 陈家乐
 * @create: 2018-11-29 11:35
 **/

public class QuickSort {
    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(20);
        SortUtil.printf(arr);
        sort(arr,0,arr.length-1);
        SortUtil.printf(arr);
    }

    public static void sort(Comparable[] arr,int start,int end){
        if(end<=start){
            return;
        }
       int j=part(arr,start,end);
        sort(arr,start,j-1);
        sort(arr,j+1,end);
    }

    private static int part(Comparable[] arr, int start, int end) {
        Comparable key=arr[start];
        int lo=start;
        int hi=end+1;
        while(true){
            while(SortUtil.less(arr[++lo],key)){

                if(lo>=end){
                    break;
                }
            }
            while(SortUtil.less(key,arr[--hi])){

            }
            if(lo>=hi){
                break;
            }
            SortUtil.exchange(arr,lo,hi);
        }
        SortUtil.exchange(arr,hi,start);
        return hi;
    }
}
