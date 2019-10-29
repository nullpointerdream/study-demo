package com.mycrawler.mycrawler.suanfa.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: mycrawler
 * @description: 位图法适合实际量大的
 * @author: 陈家乐
 * @create: 2019-05-13 10:19
 **/

public class BitMapSort {
    private int[] arr=new int[100001];
    private List list1=new ArrayList();
    public void bitMap(List<Integer> list){
        for (Integer integer : list) {
            arr[integer]=1;
        }

        for (Integer integer : arr) {
            if(integer==1){
                list1.add(integer);
            }
        }


    }

    public static void main(String[] args) {
        List<Integer>  sort1= new ArrayList<>();
        List<Integer>  sort2= new ArrayList<>();
        BitMapSort b=new BitMapSort();
        for(int i=0;i<100000;i++){
            sort1.add(i);
            sort2.add(i);
        }
        Collections.shuffle(sort1);
        Collections.shuffle(sort2);
        long startTime=System.nanoTime();
        Collections.sort(sort1);
        long end=System.nanoTime();
        System.out.println("jdk自带排序耗时:"+(end-startTime)+"s");

        long startTime1=System.nanoTime();
        b.bitMap(sort2);
        long end1=System.nanoTime();
        System.out.println("位图法排序耗时:"+(end1-startTime1)+"s");
    }
}
