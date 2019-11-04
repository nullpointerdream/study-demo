package com.mycrawler.mycrawler.juc.forkJoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 14:08
 **/

public class MapReduceTest {
    public static void main(String[] args) {
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
        "fork join in world"};
        // 创建 ForkJoin 线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        MR mr= new MR(fc);
        Map<String, Long> result =
                fjp.invoke(mr);
        // 输出结果
        result.forEach((k, v)->
                System.out.println(k+":"+v));


    }

    static class MR extends RecursiveTask<Map<String, Long>> {

        private String[] fc;
        private int start, end;
        MR(String[] fc){
            this(fc,0,fc.length-1);
        }
        // 构造函数
        MR(String[] fc, int fr, int to){
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            }
            int mid=start+(end-start)/2;
            MR m1=new MR(fc,start,mid);
            m1.fork();
            MR mr2=new MR(fc,mid,end);
            mr2.fork();
            return merge(mr2.join(),
                    m1.join());
        }

        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();
            // 分割单词
            String [] words = line.split("\\s+");
            // 统计单词数量
            for (String w : words) {
                Long v = result.get(w);
                if (v != null)
                    result.put(w, v+1);
                else
                    result.put(w, 1L);
            }
            return result;
        }


        private Map<String, Long> merge(Map<String, Long> m1, Map<String, Long> m2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(m1);
            // 合并结果
            m2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null)
                    result.put(k, c+v);
                else
                    result.put(k, v);
            });
            return result;
        }

    }
}
