package com;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReadAllCSVFiles {
    
    public static boolean del(String d){
        File directory = new File(d);
        Set<String> setALL = new HashSet<>();
        // 列出目录下所有CSV文件
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("0621")) {
                    file.delete();
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
     //   String directoryPath = "C:\\Users\\chenjl40\\Downloads\\JP";
        String directoryPath = "C:\\Users\\chenjl40\\Downloads\\20";
       /* if(del(directoryPath)){
            return;
        }*/
        File directory = new File(directoryPath);
        Map<String,Set<String>> allMap = new HashMap<>();
        // 列出目录下所有CSV文件
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                
                if (file.getName().contains("0620")) {
                    File file11 = new File(file.getAbsolutePath().replace("0620", "0611"));
                    if(!file11.exists()){
                        continue;
                    }
                    String country = file.getName().split("_")[1];
                    Set<String> setA = new HashSet<>();
                    Set<String> setB = new HashSet<>();
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split("\t");
                            if("YES".equals(values[7])){
                                setA.add(values[5]);
                            }
                        }

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try (BufferedReader br2 = new BufferedReader(new FileReader(file11))) {
                        String line2 = "";
                        while ((line2 = br2.readLine()) != null) {
                            String[] values2 = line2.split("\t");
                            if("YES".equals(values2[7])){
                                setB.add(values2[5]);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    // 找出只存在于setA中而不在setB中的元素
                    Set<String> difference = new HashSet<>(setA);
                    difference.removeAll(setB);
                    if(allMap.containsKey(country)){
                        allMap.get(country).addAll(difference);
                    }else {
                        allMap.put(country,difference);
                    }
                   //System.out.println(file.getName() + difference);
                }


                // 这里可以调用上面的CSVReader来读取每个CSV文件
            }
        }
        String sql="select product_number,display_to,'%s' as country FROM product where display_to  like '@' and product_type in (0,3) and (is_outlet is null or is_outlet =0) and  product_number in(%s)";
        /*for (String s : setALL) {
          System.out.println(s);
        }*/
        StringBuilder sb = new StringBuilder();
        for (String country : allMap.keySet()) {
            Set<String> strings = allMap.get(country);
            String result = "'"+strings.stream()
                    .collect(Collectors.joining("','"))+"'";

            sb.append(String.format(sql,country,result)+"\n");
            sb.append("UNION ALL ");
        }
        System.out.println(sb.toString());
       // System.out.println(")");


    }

}
