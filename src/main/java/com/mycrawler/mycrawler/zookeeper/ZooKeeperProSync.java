package com.mycrawler.mycrawler.zookeeper;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @program: mycrawler
 * @description: zookeeper
 * @author: 陈家乐
 * @create: 2019-07-09 14:47
 **/

public class ZooKeeperProSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8080);
        tomcat.setConnector(connector);

        tomcat.start();
        tomcat.getServer().await();

        //zookeeper配置数据存放路径
        String path = "/username";
//连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper("127.0.0.1:2181", 5000, //
                new ZooKeeperProSync());
        //等待zk连接成功的通知
        connectedSemaphore.await();
        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData(path, true, stat)));
        Thread.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                }
            }
        }
    }

}
