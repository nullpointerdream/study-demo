package com.mycrawler.mycrawler.thread.lock;

import java.util.Collection;

public interface AbstartLock {
     void lock();

     void lock(long mills) throws Exception;

     void unlock();

     Collection<Thread> getCollection();

     int size();


}
