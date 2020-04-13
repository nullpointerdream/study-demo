package com.mycrawler.mycrawler.design.facade;

public class CdPlayer {
    private Amplifier amplifier;

    public void on() {
        System.out.println("CdPlayer: on");
    }

    public void off() {
        System.out.println("CdPlayer: off");
    }

    public void eject() {
        System.out.println("CdPlayer: eject");
    }

    public void play(String media) {
        System.out.println("CdPlayer: play " + media);
    }

    public void stop() {
        System.out.println("CdPlayer: stop");
    }

    @Override
    public String toString() {
        return "CdPlayer";
    }

}