package com.mycrawler.mycrawler.suanfa.graph;

/**
 * @program: mycrawler
 * @description: 图
 * @author: 陈家乐
 * @create: 2019-09-17 09:46
 **/

public class Graph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int v){//创建v个顶点 不含边的图
        this.V=v;
        this.E=0;
        adj=(Bag<Integer>[])new Bag[v];
        for(int i=0;i<v;i++){
            adj[i]=new Bag<>();
        }
    }

    public int V(){//返回顶点个数
        return V;
    }

    public int E(){//返回顶边个数
        return E;
    }

    public void addEdge(int v,int w){//添加v-w边
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
            return adj[v];
    }



}
