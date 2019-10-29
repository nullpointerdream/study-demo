package com.mycrawler.mycrawler.suanfa.graph;


/**
 * 基于堆的优先队列
 * @author Alent
 */
public class MinPQ<E extends Comparable<E>> implements Queue<E> {
    private E[] elements;
    private int size=0;
    
    @SuppressWarnings("unchecked")
    public MinPQ(int capacity) {
        elements = (E[])new Comparable[capacity + 1];
    }
    
    @Override public int size() {return size;}

    @Override public boolean isEmpty() {return size == 0;}

    @Override
    public void enQueue(E element) {
        elements[++size] = element;
        swim(size);
    }
    
    //上浮
    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }
    
    private boolean less(int i, int j) {
        return elements[i].compareTo(elements[j]) > 0;
    }

    @Override
    public E deQueue() {
        E result = elements[1];
        swap(1, size--);
        elements[size + 1] = null;
        sink(1);
        return result;
    }
    
    //下沉
    private void sink(int k) {
        while(2*k <= size) {
            int j = 2*k;
            if(j < size && less(j, j+1))
                j++;
            if(!less(k,j))
                break;
            swap(k,j);
            k = j;
        }
    }

    //交换两个元素
    public void swap(int i,int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}