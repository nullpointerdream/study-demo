package com.mycrawler.mycrawler.suanfa.sort;

import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-05-24 10:00
 **/
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] arr = {17,18,18,4,6,1};
        int[][] arrs = {{1,-1},{-1,-1}};
        System.out.println(JSONObject.toJSONString(main.replaceElements( arr)));
    }

    public int[] replaceElements(int[] arr) {
        int mx = -1, n = arr.length, a;
        for (int i = n - 1; i >= 0; --i) {
            a = arr[i];
            arr[i] = mx;
            mx = Math.max(mx, a);
        }
        return arr;
    }
}
