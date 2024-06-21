package com;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ReadAllCSVFiles {
    public static void main(String[] args) throws Exception {
     //   String directoryPath = "C:\\Users\\chenjl40\\Downloads\\JP";
        String directoryPath = "C:\\Users\\chenjl40\\Downloads\\US";
        File directory = new File(directoryPath);
        Set<String> setALL = new HashSet<>();
        // 列出目录下所有CSV文件
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("0621")) {
                    Set<String> setA = new HashSet<>();
                    Set<String> setB = new HashSet<>();
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            String[] values = line.split("\t");
                            setA.add(values[5]);
                        }

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    for (File file2 : files) {
                        if (file2.getName().contains("0621")) {
                            continue;
                        }
                        if (file2.getName().equals(file.getName().replace("0621", "0611"))) {
                            try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
                                String line2 = "";
                                while ((line2 = br2.readLine()) != null) {
                                    String[] values2 = line2.split("\t");
                                    setB.add(values2[5]);
                                }
                            }
                            break;
                        }
                    }
                    // 找出只存在于setA中而不在setB中的元素
                    Set<String> difference = new HashSet<>(setA);
                    difference.removeAll(setB);
                    setALL.addAll(difference);
                  // System.out.println(file.getName() + difference);
                }


                // 这里可以调用上面的CSVReader来读取每个CSV文件
            }
        }
        System.out.println("select product_number,display_to  FROM product where product_number in(");
        for (String s : setALL) {
            System.out.print("'"+s+"',");
        }
        System.out.println(")");


    }

}
