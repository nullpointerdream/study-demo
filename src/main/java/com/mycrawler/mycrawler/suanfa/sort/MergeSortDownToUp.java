package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 归并排序自底向上
 * @author: 陈家乐
 * @create: 2018-11-29 10:40
 **/

public class MergeSortDownToUp {

    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(10);
        SortUtil.printf(arr);
        for(int i=1;i<arr.length;i=i+i){//1 2 4 8
            for(int j=0;j<arr.length-i;j=j+i+i){
                merage(arr,j,j+i-1,Math.min(j+i+i-1,arr.length-1));//0-1 和2-3 的合并
            }
        }
        SortUtil.printf(arr);
    }

    /**
    * @Description: 数组start->mid  和 数组mid+1到end  的merge
    * @Param: [arr, start, mid, end]
    * @return: void
    * @Author: 陈家乐
    * @Date: 2018/11/29
    */
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
