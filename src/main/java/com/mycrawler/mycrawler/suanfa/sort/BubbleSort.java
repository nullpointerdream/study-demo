package com.mycrawler.mycrawler.suanfa.sort;

import com.mycrawler.mycrawler.util.SortUtil;

/**
 * @program: mycrawler
 * @description: 冒泡排序
 * @author: 陈家乐
 * @create: 2018-11-29 10:11
 **/

public class BubbleSort {
    public static void main(String[] args) {
        Comparable[] arr = SortUtil.createRamdom(20);
        SortUtil.printf(arr);
        for(int i=0;i<arr.length;i++){
            Boolean flag=true;
            for(int j=0;j<arr.length-i-1;j++){
                if(!SortUtil.less(arr[j],arr[j+1])){
                    flag=false;
                    SortUtil.exchange(arr,j,j+1);
                }
            }
            if(flag){
                break;
            }

        }
        SortUtil.printf(arr);
    }
}
