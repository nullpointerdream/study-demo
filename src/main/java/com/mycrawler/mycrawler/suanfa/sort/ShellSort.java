package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 希尔排序
 * @author: 陈家乐
 * @create: 2018-11-29 09:57
 **/

public class ShellSort {

    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(20);
        SortUtil.printf(arr);
        int N = arr.length;
        int k=1;
        while(k<N/3){
            k=k*3+1;
        }
        while(k>0){
            for(int i=0;i<arr.length-k;i=i+k){
                for(int j=i+k;j>=k&&SortUtil.less(arr[j],arr[j-k]);j=j-k) {
                    SortUtil.exchange(arr, j, j-k);
                }
            }
            k/=3;

        }
        SortUtil.printf(arr);

    }
}
