package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 插入排序
 * @author: 陈家乐
 * @create: 2018-11-29 09:57
 **/

public class InsertSort {

    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(10);
        SortUtil.printf(arr);
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j>0&&SortUtil.less(arr[j],arr[j-1]);j--){
                SortUtil.exchange(arr,j,j-1);
            }
        }
        SortUtil.printf(arr);
    }
}
