package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author chenjl40
 * @Date 2023/10/31 10:30
 */
public class DD {
    public  static void aa(String a) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\ideaProject\\study-demo\\src\\main\\java\\com\\aa"));
        String stre=null;
        Set<String> stringSet2 = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        int i=0;
        while ((stre =bufferedReader.readLine())!=null) {
                sb.append("'"+stre+"',");
            }



           /* if(jsonObject.getString("lastUpdateTimeStr").compareTo("2024-03-16 23:59:59")>=0
            && jsonObject.getString("lastUpdateTimeStr").compareTo("2024-03-18 00:00:00")<=0){

            }*/

        System.out.println(sb.toString());

    }

    public static void main(String[] args) throws IOException {
        aa(null);
       /* BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\chenjl40\\Downloads\\export_result (1).JSON"));
        String stre=null;
        Set<String> stringSet2 = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        int i=0;
        while ((stre =bufferedReader.readLine())!=null) {
            JSONArray objects = JSON.parseArray(stre);
            for (Object object : objects) {
                JSONObject object1 = (JSONObject) object;
                sb.append("'"+object1.getString("bizId")+"',");
            }



           *//* if(jsonObject.getString("lastUpdateTimeStr").compareTo("2024-03-16 23:59:59")>=0
            && jsonObject.getString("lastUpdateTimeStr").compareTo("2024-03-18 00:00:00")<=0){

            }*//*
        }
        System.out.println(sb.toString());


*/




    }

    public static void main(Set<String> set) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\ideaProject\\study-demo\\src\\main\\java\\com\\aa"));
        String stre=null;
        StringBuffer SQL=new StringBuffer("update goodscenter.product_avr set change_record =null where  country_code ='NZ' and channel ='B2C' and product_number in(");
        Set<String> stringSet = new HashSet<>();
        int i=0;
        while ((stre =bufferedReader.readLine())!=null) {
            stringSet.add(stre);
        }
        /*//SQL.append("'"+stre+"',");
        //System.out.println(SQL.toString());
        System.out.println(stringSet.size());
        String commaSeparated = stringSet.stream()
                .collect(Collectors.joining(","));

        System.out.println(commaSeparated);*/


    }
}
