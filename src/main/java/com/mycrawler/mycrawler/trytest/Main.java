package com.mycrawler.mycrawler.trytest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
          int j = query();//
            System.out.println(j);//
    }
    public static int query() {
        int i = 0;
        try {
            System.out.print("try\n");
            return i+=10 ;
        } catch (Exception e) {
            System.out.print("catch\n");
            i += 20;
        } finally {
            System.out.print("finally-i:"+i + "\n");
            i += 10;
            System.out.println("finally"+i);
            //return i;
        }
        System.out.print("finish");
        return 200;
    }
}