package com.mycrawler.mycrawler.suanfa.graph;

/**
 * 基于堆实现的索引优先队列
 */
public class IndexMinPQ<E extends Comparable<E>>{

    private int[] pq; //索引二叉堆
    private int[] qp; // 保存逆序：pq[qp[i]] = i;
    private E[] elements; //元素
    private int size = 0;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int capacity) {
        elements = (E[]) new Comparable[capacity + 1];
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
    //删除最小元素，并返回索引
    public int delMin() {
        int index = pq[1];
        swap(1, size--);
        sink(1);
        elements[pq[size + 1]] = null;
        qp[pq[size + 1]] = -1;
        return index;
    }
    //删除索引k及其元素
    public void delete(int k) {
        int index = qp[k];
        swap(index, size--);
        swim(index);
        sink(index);
        elements[k] = null;
        qp[k] = -1;
    }
    
    //插入元素，将它和索引k关联
    public void insert(int k, E element) {
        size++;
        qp[k] = size;
        pq[size] = k;
        elements[k] = element;
        swim(size);
    }

     //改变索引k关联的元素
    public void change(int k, E element) {
        elements[k] = element;
        swim(qp[k]);
        sink(qp[k]);
    }
    
    //是否包含索引k
    public boolean contains(int k) {
        return qp[k] != -1;
    }
    
    //下沉
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            swap(k, j);
            k = j;
        }
    }

    //上浮
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }
    private boolean less(int i, int j) {
        return elements[pq[i]].compareTo(elements[pq[j]]) > 0;
    }

    //交换两元素
    private void swap(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}