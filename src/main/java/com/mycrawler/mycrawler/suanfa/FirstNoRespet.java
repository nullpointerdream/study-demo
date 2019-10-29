package com.mycrawler.mycrawler.suanfa;

import java.util.Scanner;

/**
 * @program: mycrawler
 * @description: 第一个没重复的字符
 * @author: 陈家乐
 * @create: 2019-01-28 09:56
 **/

public class FirstNoRespet {

    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        String str = sc.next();
        int[] arr=new int[256];
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(arr[chars[i]]==0){
                arr[chars[i]]=1;
            }else {
                arr[chars[i]]+=1;
            }
        }
        for(int i=0;i<chars.length;i++){
            if(arr[chars[i]]==1){
                System.out.println(chars[i]);
                break;
            }
        }

    }

}
