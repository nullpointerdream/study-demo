package com.mycrawler.mycrawler.suanfa.priorityqueue.merge;

import java.io.*;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-29 13:19
 **/

public class FileFactory {
    public static String path = "/Users/chenjiale/Desktop/test/";

    public static void main(String[] args) throws IOException {
       // Stream.of(1, 2, 3, 4, 5).forEach(FileFactory::createFile);
        test(1);
    }

    public static void createFile(int start) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path + "test-" + start));
            for (int i = start; i < 10000; i++) {
                bufferedWriter.write("" + i);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void test(int start) throws IOException {
        File[] files = new File(FileFactory.path).listFiles();
        for (File file : files) {
            System.out.println(file);
        }


           /* BufferedReader[] bufferedReaders=new BufferedReader[files.length];

            for (int i = 0; i < files.length; i++) {
                bufferedReaders[i] =new BufferedReader(new FileReader(files[i]));
            }
            File
            BufferedReader a =new BufferedReader(new FileReader(new File(path + "test-" + start)));
            String s = null;

            while ((s=a.readLine())!=null){
                System.out.println(s);
            }*/

    }
}
