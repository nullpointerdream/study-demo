package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-01-11 14:59
 **/

public class QuickSort1 {
    public static void main(String[] args) {
        Comparable[] ramdom = SortUtil.createRamdom(1);
        SortUtil.printf(ramdom);
        sort(ramdom,0,ramdom.length-1);
        SortUtil.printf(ramdom);
    }

    public static void sort(Comparable[] arr,int start,int end){
        if(start>=end)
            return;
        int i = pain(arr,start,end);
        sort(arr,start,i-1);
        sort(arr,i+1,end);

    }

    private static int pain(Comparable[] arr, int start, int end) {
        int lo=start;
        int hi=end+1;
        Comparable key = arr[start];
        while(true){
            while (!SortUtil.less(key,arr[++lo])&&lo<end){

            }
            while (SortUtil.less(key,arr[--hi])){

            }
            if(lo<hi){
                SortUtil.exchange(arr,lo,hi);
            }else
                break;

        }
        SortUtil.exchange(arr,start,hi);
        return hi;

    }
}
