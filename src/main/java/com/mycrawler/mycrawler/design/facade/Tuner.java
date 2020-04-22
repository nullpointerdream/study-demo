package com.mycrawler.mycrawler.design.facade;

public class Tuner {
    private Amplifier amplifier;

    public void on() {
        System.out.println("Tuner: on");
    }

    public void off() {
        System.out.println("Tuner: off");
    }

    public void setAm() {
        System.out.println("Tuner: setAm");
    }

    public void setFm() {
        System.out.println("Tuner: setFm");
    }

    public void setFreuency() {
        System.out.println("Tuner: setFreuency");
    }

    @Override
    public String toString() {
        return "Tuner";
    }
}