package com.mycrawler.mycrawler.hash;


import com.mycrawler.mycrawler.util.HashFunction;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: mycrawler
 * @description: 一致性hash算法
 * @author: 陈家乐
 * @create: 2019-05-16 11:54
 **/

public class MyConsistentHash<T> {
    private SortedMap<Long,T> server=new TreeMap();
    private Integer replic=0;
    MyConsistentHash(int replic){
        this.replic=replic;
    }

    public void addServer(T node){


        for(int i=0;i<replic;i++){
            server.put(HashFunction.hash(node.toString()+i),node);
            System.out.println("节点"+node.toString()+"的hash值"+HashFunction.hash(node.toString()+i));
        }
    }
    public T getServer(T node){
        SortedMap<Long, T> longTSortedMap =server.tailMap(HashFunction.hash(node.toString()));
        if(longTSortedMap==null){
            return server.get(server.firstKey());
        }
        return server.get(longTSortedMap.firstKey());
    }

    public static void main(String[] args) {
        MyConsistentHash consistentHash = new MyConsistentHash(5);
        String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
                "192.168.0.3:111", "192.168.0.4:111"};
        for (int i = 0; i < servers.length; i++) {
            consistentHash.addServer(servers[i]);
        }

        String[] nodes = {"127.0.0.1:1111", "Ascs", "133131123svefesfes"};
        for (int i = 0; i < nodes.length; i++) {
            System.out.println("[" + nodes[i] + "]的hash值为" + HashFunction.hash(nodes[i]) + ", 被路由到结点[" + consistentHash.getServer(nodes[i]) + "]");
        }
    }


}
