package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 归并排序自顶向下
 * @author: 陈家乐
 * @create: 2018-11-29 10:40
 **/

public class MergeSortUpToDown {
    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(10);
        SortUtil.printf(arr);
        sort(arr,0,arr.length-1);
        SortUtil.printf(arr);
    }

    public static void sort(Comparable[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int mid =(start+end)/2;
        sort(arr,start,mid);
        sort(arr,mid+1,end);
        merage(arr,start,mid,end);
    }

    private static void merage(Comparable[] arr, int start, int mid, int end) {
        Comparable[] newArr =new Comparable[arr.length];
        for(int i=0;i<arr.length;i++){
            newArr[i]=arr[i];
        }
        int k=start;
        int lo=start;
        int hi=mid+1;
        for(int i=start;i<=end;i++){
            if(lo>mid){
                arr[k++]=newArr[hi++];
            }else if(hi>end){
                arr[k++]=newArr[lo++];
            }else  if(SortUtil.less(newArr[lo],newArr[hi])){
                arr[k++]=newArr[lo++];
            }else {
                arr[k++]=newArr[hi++];
            }
        }
    }
}
