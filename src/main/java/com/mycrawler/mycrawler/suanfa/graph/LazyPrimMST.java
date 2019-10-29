package com.mycrawler.mycrawler.suanfa.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @program: mycrawler
 * @description: 最小生成树
 * @author: 陈家乐
 * @create: 2019-09-18 10:07
 **/

public class LazyPrimMST {
    private boolean[] marked; //标记顶点
    private LinkedList<Edge> mst; //存储最小生成树的边
    private MinPQ<Edge> pq; //优先队列，权值越最小优先级越高

    public LazyPrimMST(EdgeWeightGraph g){
        marked=new boolean[g.V()];
        pq=new MinPQ(g.E());
        mst=new LinkedList();
        visit(g,0);
        while (!pq.isEmpty()){
            Edge e = pq.deQueue();//权重最小的边
            int ver1 = e.getV();
            int ver2 = e.other(ver1);
            if(marked[ver1] && marked[ver2]) {
                continue; //边失效
            }
            mst.addLast(e);//边入队
            if(!marked[ver1])
                visit(g, ver1);
            if(!marked[ver2])
                visit(g, ver2);
        }
    }




    private void visit(EdgeWeightGraph g, int ver) {
        marked[ver] = true; //标记顶点
        for(Edge e : g.adj(ver)) {
            if(!marked[e.other(ver)])
                pq.enQueue(e);
        }
    }

    public Iterable<Edge> getMST() {
        return mst;
    }

    public static void main(String[] args) {
        int[][] data = {
                {0, 2, 26},
                {0, 7, 16},
                {0, 4, 38},
                {0, 6, 58},
                {7, 1, 19},
                {7, 5, 28},
                {7, 2, 34},
                {7, 4, 37},
                {4, 5, 35},
                {4, 6, 93},
                {1, 5, 32},
                {1, 2, 36},
                {2, 3, 17},
                {3, 6, 52},
                {2, 6, 40},
                {1, 3, 29}
        };
        EdgeWeightGraph wg = new EdgeWeightGraph(8);
        for (int[] datum : data) {
            Edge edge =new Edge(datum[0],datum[1],datum[2]);
            wg.addEdge(edge);
        }


        LazyPrimMST lpm = new LazyPrimMST(wg);
        for(Edge e : lpm.getMST()) {
            System.out.println(e.toString());
        }
    }

}
