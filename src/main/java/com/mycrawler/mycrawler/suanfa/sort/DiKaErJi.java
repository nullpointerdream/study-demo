package com.mycrawler.mycrawler.suanfa.sort;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-02-19 14:31
 **/

public class DiKaErJi {

    public void testCartesianProduct2() {
        System.out.println(Arrays.deepToString(
                ssdo(
                        new Integer[]{1,13}, new Integer[]{3, 4}, new Integer[]{5, 6},new Integer[]{7, 8}
                ).toArray()));
    }
    public static <T> List<TreeSet<T>> ssdo(T[] ... lists){

        return null;
    }

    public static void main(String[] args) {
        TestDiKaEr cartesianArith =new TestDiKaEr();
        cartesianArith.testCartesianProduct2();
    }
}
