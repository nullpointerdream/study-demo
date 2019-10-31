package com.mycrawler.mycrawler.suanfa.priorityqueue.schedule;


public class ScheduleTask implements Comparable<ScheduleTask>{

  /** 定时运行的时间点 */
  private long runTime;

  /** 定时任务的时间点 */
  private Runnable runable;

    public ScheduleTask(long runTime, Runnable runable) {
        this.runTime = runTime;
        this.runable = runable;
    }

    public long getRunTime() {
    return runTime;
  }

  public void setRunTime(long runTime) {
    this.runTime = runTime;
  }

  public Runnable getRunable() {
    return runable;
  }

  public void setRunable(Runnable runable) {
    this.runable = runable;
  }

  @Override
  public int compareTo(ScheduleTask o) {
    return (int) (runTime-o.runTime);
  }
}