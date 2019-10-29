package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-08-19 18:04
 **/

public class MyQuickSort {
    public static void sort(Integer[] arr,int start,int end){
        if(end<=start){
            return;
        }
        int partition=partition(arr,start,end);
        sort(arr,start,partition-1);
        sort(arr,partition+1,end);
    }

    private static int partition(Integer[] arr, int lo, int hi) {
        int key=arr[lo];
        int start=lo;
        int end=hi+1;
        while (true){
            while (arr[++start]<key){
                if(start>=end)
                    break;
            }
            while (arr[--end]>key){

            }
            if(start>=end){
                break;
            }
            int tmp=arr[start];
            arr[start]=arr[end];
            arr[end]=tmp;
        }
        System.out.println(start+"   "+end);
        arr[lo]=arr[end];
        arr[end]=key;
        SortUtil.printf(arr);
        return end;

    }

    public static void main(String[] args) {
        Integer[] arr={3,2,6,2,76,3,35};
        SortUtil.printf(arr);
        sort(arr,0,arr.length-1);
        SortUtil.printf(arr);
    }
}
