package com.mycrawler.mycrawler.trytest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
      //  int j = query();//
      //  System.out.println(j);//

       // System.out.println();
        //1111111110011100
        //1111111110011011
        //0000000001100100
    }
    public static int query() {
        int i = 0;
        try {
            System.out.print("try\n");
            return i += 10;
        } catch (Exception e) {
            System.out.print("catch\n");
            i += 20;
        } finally {
            System.out.print("finally-i:"+i + "\n");
            i += 10;
            System.out.print("finally\n");
            //return i;
        }
        System.out.print("finish");
        return 200;
    }
}