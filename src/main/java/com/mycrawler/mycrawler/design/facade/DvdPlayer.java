package com.mycrawler.mycrawler.design.facade;

public class DvdPlayer {
    private Amplifier amplifier;

    public void on() {
        System.out.println("DvdPlayer: on");
    }

    public void off() {
        System.out.println("DvdPlayer: off");
    }

    public void eject() {
        System.out.println("DvdPlayer: eject");
    }

    public void play(String movie) {
        System.out.println("DvdPlayer: play " + movie);
    }

    private void setSurroundAudio() {
        System.out.println("DvdPlayer: setSurroundAudio");
    }

    private void setTwoChanelAudio() {
        System.out.println("DvdPlayer: setTwoChanelAudio");
    }

    public void stop() {
        System.out.println("DvdPlayer: stop");
    }

    @Override
    public String toString() {
        return "DvdPlayer";
    }
}
