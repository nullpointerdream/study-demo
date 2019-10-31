package com.mycrawler.mycrawler.suanfa.priorityqueue.schedule;

import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-29 15:48
 **/

public class RunTask {
    public static void main(String[] args) {
        PriorityQueue<ScheduleTask> scheduleTasks=new PriorityQueue<>();
        Stream.of(1*60*1000,2*60*1000,3*60*1000).forEach(bean-> {
            scheduleTasks.offer(new ScheduleTask(System.currentTimeMillis() + bean, () -> {
                System.out.println(bean + "----run ");
            }));
        });

        while (!scheduleTasks.isEmpty()){
            ScheduleTask poll = scheduleTasks.poll();
            long runTime =poll.getRunTime()- System.currentTimeMillis();
            if(runTime>0){
                try {
                    Thread.sleep(runTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            new Thread(poll.getRunable()).start();
        }


    }
}
