package com.mycrawler.mycrawler.suanfa.graph;

import com.mycrawler.mycrawler.suanfa.dijkstra.graph;

/**
 * @program: mycrawler
 * @description: 深度优先搜索
 * @author: 陈家乐
 * @create: 2019-09-17 11:09
 **/

public class DepthFirstSearch {
    private boolean[] marked;

    public DepthFirstSearch(Graph graph,int s){
        marked=new boolean[graph.V()];
        dfs(graph,s);
    }

    private void dfs(Graph graph, int s) {
        marked[s]=true;
        for (Integer v : graph.adj(s)) {
            if(!marked[v]){
                dfs(graph,v);
            }
        }
    }

    public boolean marked(int v){
       return marked[v];
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

        new DepthFirstSearch(graph,0);
    }
}
