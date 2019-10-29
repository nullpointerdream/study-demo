package com.mycrawler.mycrawler.suanfa.graph;

import java.util.Stack;

/**
 * @program: mycrawler
 * @description: 深度优先搜索路径
 * @author: 陈家乐
 * @create: 2019-09-17 11:22
 **/

public class DepthFirstPaths {
    private boolean[] marked;

    private int[] edgeTo;// 从起点到一个顶点的已知路径上的最后一个顶点
    private int s;// 起点

    public DepthFirstPaths(Graph graph,int s){
        marked=new boolean[graph.V()];
        edgeTo=new int[graph.V()];
        this.s=s;
        dfs(graph,s);
    }

    private void dfs(Graph graph, int s) {
        marked[s]=true;
        for (Integer v : graph.adj(s)) {
            if(!marked[v]){
                edgeTo[v]=s;
                dfs(graph,v);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph graph =new Graph(6);
        graph.addEdge(0,5);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.addEdge(1,2);
        graph.addEdge(0,1);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(0,2);

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);
        for (Integer integer : depthFirstPaths.pathTo(1)) {
            System.out.println(integer+" ");
        }
        ;
    }
}
