package com.mycrawler.mycrawler.suanfa.graph;

/**
 * @program: mycrawler
 * @description: 加权边
 * @author: 陈家乐
 * @create: 2019-09-18 09:45
 **/

public class Edge implements Comparable<Edge>{
    private int v;
    private int w;
    private int weight;

    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int other(int vertex){
        return vertex==v?w:v;
    }



    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight<o.weight){
            return -1;
        }else if(this.weight>o.weight){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Edge [" + v + "," + w +"]";
    }
}
