package com.mycrawler.mycrawler.suanfa.priorityqueue.merge;


import lombok.Data;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-29 14:18
 **/
@Data
public class NumInfo implements Comparable<NumInfo>{
    private int id;
    private int value;

    public NumInfo(int id, int value) {
        this.id = id;
        this.value = value;
    }


    @Override
    public int compareTo(NumInfo o) {
        if (value > o.getValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
