package com.mycrawler.mycrawler.suanfa.graph;

/**
 * @program: mycrawler
 * @description: 及时最小生成树
 * @author: 陈家乐
 * @create: 2019-09-18 13:38
 **/

public class PrimMST {
    private Edge[] edgeTo; //点离生成树最近的边
    private int[] distTo; //点到生成树的距离
    private boolean[] marked;
    private IndexMinPQ<Integer> pq; //索引优先队列，关联顶点与distTo

    public PrimMST(EdgeWeightGraph g) {
        edgeTo = new Edge[g.V()];
        distTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        pq = new IndexMinPQ<>(g.V());
        distTo[0] = 0;
        pq.insert(0, 0);
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());
        }

    }

    private void visit(EdgeWeightGraph wg, int ver) {
        marked[ver] = true;
        for(Edge e : wg.adj(ver)) {
            int vertex = e.other(ver); //边的另一个点
            if(marked[vertex])
                continue;
            if(e.getWeight() < distTo[vertex]) {
                edgeTo[vertex] = e; //被覆盖的边失效
                distTo[vertex] = e.getWeight();
                if(pq.contains(vertex)) {
                    pq.change(vertex, distTo[vertex]);
                }else {
                    pq.insert(vertex, distTo[vertex]);
                }
            }
        }
    }

}
