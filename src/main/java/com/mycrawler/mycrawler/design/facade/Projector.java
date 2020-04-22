package com.mycrawler.mycrawler.design.facade;

public class Projector {
    private DvdPlayer dvdPlayer;

    public void on() {
        System.out.println("Projector: on");
    }

    public void off() {
        System.out.println("Projector: off");
    }

    public void tvMode() {
        System.out.println("Projector: tvMode");
    }

    public void wideScreenMode() {
        System.out.println("Projector: wideScreenMode");
    }

    @Override
    public String toString() {
        return "Projector";
    }
}
