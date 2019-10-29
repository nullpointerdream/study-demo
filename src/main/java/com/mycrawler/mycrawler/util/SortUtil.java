package com.mycrawler.mycrawler.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @program: mycrawler
 * @description: 排序工具类
 * @author: 陈家乐
 * @create: 2018-11-29 09:57
 **/

public class SortUtil {

    /**
    * @Description: 判断大小
    * @Param: [a, b]
    * @return: java.lang.Boolean
    * @Author: 陈家乐
    * @Date: 2018/11/29
    */
    public static Boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }


    /**
    * @Description: 交换值
    * @Param: [arr, a, b]
    * @return: void
    * @Author: 陈家乐
    * @Date: 2018/11/29
    */
    public static void exchange(Comparable[] arr,int a,int b){
        Comparable temp = arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    /**
    * @Description: 打印数组
    * @Param: [arr]
    * @return: void
    * @Author: 陈家乐
    * @Date: 2018/11/29
    */
    public static void printf(Comparable[] arr){
        for (Comparable comparable : arr) {
            System.out.print(comparable+" ");
        }
        System.out.println();

    }


    /** 
    * @Description: 生成随机数 
    * @Param: [n] 
    * @return: java.lang.Comparable[] 
    * @Author: 陈家乐 
    * @Date: 2018/11/29
    */ 
    public static Comparable[] createRamdom(int n){
        Comparable[] arr = new Comparable[n];
        for (int i=0;i<n;i++) {
            arr[i]= new Random().nextInt(100);
        }
        return arr;
    }


    public static void main(String[] args) {
        System.out.println(1);
        List<Integer> integers = Arrays.asList(1, 23);
    }



}
