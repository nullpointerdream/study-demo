package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 堆排序
 * @author: 陈家乐
 * @create: 2018-11-29 13:34
 **/
public class HeapSort {
    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(10);
        SortUtil.printf(arr);
        int N = arr.length-1;
        //构造堆
        for(int i=N/2;i>=0;i--){
            sink(arr,i,N);
        }
        for(int i=N;i>=1;i--){
            SortUtil.exchange(arr,0,i);
            sink(arr,0,i-1);
        }
        SortUtil.printf(arr);

    }
    
    /** 
    * @Description: 下沉 
    * @Param: [arr, k, n] 
    * @return: void 
    * @Author: 陈家乐 
    * @Date: 2018/11/29
    */ 
    public static void sink(Comparable[] arr,int k,int n){
            int j=k*2+1;
            while (j<=n){
                if(j<n&&SortUtil.less(arr[j],arr[j+1])){
                    j++;
                }
                if(SortUtil.less(arr[j],arr[k])){
                   break;
                }
                SortUtil.exchange(arr,j,k);
                k=j;
                j=k*2+1;
            }
    }
}
