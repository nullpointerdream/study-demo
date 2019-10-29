package com.mycrawler.mycrawler.suanfa.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @program: mycrawler
 * @description: 广度优先搜索
 * @author: 陈家乐
 * @create: 2019-09-17 11:31
 **/

public class BreadthFirstPaths {
    private boolean[] marked;

    private int[] edgeTo;// 从起点到一个顶点的已知路径上的最后一个顶点
    private int s;// 起点

    public BreadthFirstPaths(Graph graph,int s){
        marked=new boolean[graph.V()];
        edgeTo=new int[graph.V()];
        this.s=s;
        bfs(graph,s);
    }

    private void bfs(Graph graph, int s) {
        marked[s]=true;
        LinkedList<Integer> queue =new LinkedList<>();
        queue.addLast(s);
        while (!queue.isEmpty()){
            Integer pop = queue.pop();
            System.out.println(pop);
            for (Integer v : graph.adj(pop)) {
                if(!marked[v]){
                    marked[v]=true;
                    edgeTo[v] = pop;
                    queue.addLast(v);
                }
            }

        }
    }
    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
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

        BreadthFirstPaths depthFirstPaths = new BreadthFirstPaths(graph, 0);

    }
}
