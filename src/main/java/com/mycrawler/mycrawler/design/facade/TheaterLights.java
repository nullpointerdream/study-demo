package com.mycrawler.mycrawler.design.facade;

public class TheaterLights {
    public void on() {
        System.out.println("TheraterLights: on");
    }

    public void off() {
        System.out.println("TheraterLights: off");
    }

    public void dim(int dim) {
        System.out.println("TheraterLights: dim " + dim);
    }

    @Override
    public String toString() {
        return "Screen";
    }
}