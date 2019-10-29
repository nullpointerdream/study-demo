package com.mycrawler.mycrawler.suanfa.graph;

/**
 * @program: mycrawler
 * @description: 加权无向图
 * @author: 陈家乐
 * @create: 2019-09-18 09:45
 **/

public class EdgeWeightGraph {
    private int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightGraph(int v){//创建v个顶点 不含边的图
        this.V=v;
        this.E=0;
        adj=(Bag<Edge>[])new Bag[v];
        for(int i=0;i<v;i++){
            adj[i]=new Bag<Edge>();
        }
    }

    public int V(){//返回顶点个数
        return V;
    }

    public int E(){//返回顶边个数
        return E;
    }

    public void addEdge(Edge edge){//添加v-w边
        int v=edge.getV();
        int w=edge.getW();
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }


}
