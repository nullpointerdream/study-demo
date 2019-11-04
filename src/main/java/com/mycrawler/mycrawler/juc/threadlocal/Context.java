package com.mycrawler.mycrawler.juc.threadlocal;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 09:33
 **/

public class Context {
    private String name;

    private String idCard;

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }
}
