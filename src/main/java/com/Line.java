package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author chenjl40
 * @Date 2024/6/25 15:20
 */
public class Line {
    public static void LINE(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\ideaProject\\study-demo\\src\\main\\java\\com\\line"));
        String stre = null;
        StringBuilder sb = new StringBuilder();
        while ((stre = bufferedReader.readLine()) != null) {
            sb.append("'" + stre + "',");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        Set<String> ess = new HashSet();
        Set<String> cleaness = new HashSet();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\ideaProject\\study-demo\\src\\main\\java\\com\\FF"));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("D:\\ideaProject\\study-demo\\src\\main\\java\\com\\aa"));
        String stre = null;
        StringBuilder sb = new StringBuilder();
        while ((stre = bufferedReader.readLine()) != null) {
            stre = stre.substring(1, stre.length() - 1);
            String[] essPartsArr = stre.split(",");
            for (String s : essPartsArr) {
                ess.add(s.trim());
            }


        }


        String stre2 = null;
        while ((stre2 = bufferedReader2.readLine()) != null) {
            cleaness.add(stre2);
        }

        // 使用retainAll方法求交集
        ess.retainAll(cleaness);
        for (String s : ess) {
            System.out.println(s);
        }


    }



}
