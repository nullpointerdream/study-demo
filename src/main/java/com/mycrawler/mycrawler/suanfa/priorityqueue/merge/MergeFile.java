package com.mycrawler.mycrawler.suanfa.priorityqueue.merge;

import java.io.*;
import java.util.PriorityQueue;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-29 13:37
 **/

public class MergeFile {
    public static void main(String[] args) throws IOException {
        //1，默认实现的是最小堆，元素按照natural ordering排序（自然排序，例如，数字的从小到大）
        BufferedWriter bufferedWriter =new BufferedWriter(new FileWriter(FileFactory.path+"merage"));
        File[] files = new File(FileFactory.path).listFiles();
        PriorityQueue priorityQueue = new PriorityQueue<NumInfo>(files.length);
        FileBuffer[] fileBuffers = new FileBuffer[files.length];

        for (int i = 0; i < files.length; i++) {
            fileBuffers[i] = new FileBuffer(i, new BufferedReader(new FileReader(files[i])));
        }

        for (int i = 0; i < files.length; i++) {
            FileBuffer fileBuffer = fileBuffers[i];
            priorityQueue.offer(new NumInfo(i, fileBuffer.readNum()));
        }

        while (!checkFinsh(fileBuffers)||!priorityQueue.isEmpty()) {

            NumInfo poll = (NumInfo) priorityQueue.poll();
            if(poll==null){
                break;
            }
            bufferedWriter.write(poll.getValue()+"");
            bufferedWriter.newLine();
            FileBuffer fileBuffer=fileBuffers[poll.getId()];
            int num = fileBuffer.readNum();
            if(num<0){
                fileBuffer.flag=true;
            }else {
                priorityQueue.offer(new NumInfo(poll.getId(),num));
            }
        }
        bufferedWriter.flush();
    }

    private static boolean checkFinsh(FileBuffer[] fileBuffers) {
        for(FileBuffer one :fileBuffers){
            if(!one.flag){
                return false;
            }
        }
        return true;
    }


    static class FileBuffer{
        private BufferedReader bufferedReader;

        private int id;

        private boolean flag;

        public FileBuffer(int id ,BufferedReader refecne) {
            this.bufferedReader = refecne;
            this.id=id;
        }

        public int readNum(){
            if(flag){
                return -1;
            }
            String s = null;
            try {
                s = bufferedReader.readLine();
                if(s!=null){
                    return Integer.parseInt(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag=true;
            return -1;


        }
    }
}
