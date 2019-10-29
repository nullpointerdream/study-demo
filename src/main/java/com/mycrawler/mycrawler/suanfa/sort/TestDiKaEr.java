package com.mycrawler.mycrawler.suanfa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @program: mycrawler
 * @description: 测试笛卡尔积
 * @author: 陈家乐
 * @create: 2019-01-24 17:06
 **/

public class TestDiKaEr {

    //第一个标志这个方法是泛型方法，第二个是List<T>是返回值
    public <T> List<TreeSet<T>>  ssdo(T[] ... lists){
        int length = lists.length;
        int[] counterIndex=new int[length];//每个list当前角标
        //声明进位指针cIndex
        int cIndex = length - 1;
        //获取笛卡尔总数
        int total=1;
        for(T[] one :lists){
            total*=one.length;
        }
        List<TreeSet<T>> rt = new ArrayList<>(total);
        while (cIndex>=0){
            TreeSet<T> element = new TreeSet<>();
            for(int i=0;i<=length-1;i++){
                T[] list = lists[i];
                element.add(list[counterIndex[i]]);
                if(i==length-1&&++counterIndex[i]>=list.length){
                    counterIndex[i]=0;
                    int index=i;
                    while (--index>=0){
                        if(++counterIndex[index]>=lists[index].length){
                            counterIndex[index]=0;
                            continue;
                        }
                        break;
                    }
                    if(index<cIndex) {
                        cIndex = index;
                    }
                }
            }
            if (element.size() > 0) {
                rt.add(element);
            }
        }

        return rt;
    }
    public void testCartesianProduct2() {
        System.out.println(Arrays.deepToString(
                ssdo(
                        new Integer[]{1,13}, new Integer[]{3, 4}, new Integer[]{5, 6},new Integer[]{7, 8}
                ).toArray()));
    }


    public static void main(String[] args) {
       // TestDiKaEr cartesianArith =new TestDiKaEr();
       // cartesianArith.testCartesianProduct2();
        try {
            for (;;){
                int a =2/0;
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        } finally {
            System.out.println("fin");
        }

    }
}
