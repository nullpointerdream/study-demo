package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 选择排序
 * @author: 陈家乐
 * @create: 2018-11-29 09:57
 **/

public class SelectSort {

    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(10);
        SortUtil.printf(arr);
        for(int i=0;i<arr.length-1;i++){
            int tmp=i;
            for(int j=i+1;j<arr.length;j++){
                if(SortUtil.less(arr[j],arr[tmp])){
                    tmp=j;
                }
            }
            SortUtil.exchange(arr,i,tmp);
        }
        SortUtil.printf(arr);
    }
}
