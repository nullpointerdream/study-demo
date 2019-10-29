package com.mycrawler.mycrawler.suanfa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CartesianArith {

    public <T> List<List<T>> cartesianProduct(T[]... sets) {
        if (sets == null || sets.length == 0) {
            return Collections.emptyList();
        }
        int total = 1;
        //声明进位指针cIndex
        int cIndex = sets.length - 1;
        //声明counterMap(角标 - counter)
        int[] counterMap = new int[sets.length];
        for (int i = 0; i < sets.length; i++) {
            counterMap[i] = 0;
            total *= (sets[i] == null || sets[i].length == 0 ? 1 : sets[i].length);//获取总的组合数
        }
        List<List<T>> rt = new ArrayList<>(total);
        //开始求笛卡尔积
        while (cIndex >= 0) {
            List<T> element = new ArrayList<>(sets.length);
            for (int j = 0; j < sets.length; j++) {
                T[] set = sets[j];
                //忽略空集
                if (set != null && set.length > 0) {
                    element.add(set[counterMap[j]]);
                }
                //从末位触发指针进位
                if (j == sets.length - 1) {
                    if (set == null || ++counterMap[j] > set.length - 1) {
                        //重置指针
                        counterMap[j] = 0;
                        //进位
                        int cidx = j;
                        while (--cidx >= 0) {
                            //判断如果刚好前一位也要进位继续重置指针进位
                            if (sets[cidx] == null || ++counterMap[cidx] > sets[cidx].length - 1) {
                                counterMap[cidx] = 0;
                                continue;
                            }
                            break;
                        }
                        if (cidx < cIndex) {
                            //移动进位指针
                            cIndex = cidx;
                        }
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
                cartesianProduct(
                        new String[]{"a", "b"}, new String[]{"0", "1", "2"}
                ).toArray()));
    }

    public static void main(String[] args) {
        CartesianArith cartesianArith =new CartesianArith();
        cartesianArith.testCartesianProduct2();
    }

}
