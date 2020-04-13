package com.mycrawler.mycrawler.design.facade;

public class Amplifier {
    private Tuner tuner;
    private DvdPlayer dvdPlayer;
    private CdPlayer cdPlayer;

    public void on() {
        System.out.println("Amplifier: on");
    }

    public void off() {
        System.out.println("Amplifier: off");
    }

    public void setCd(CdPlayer cdPlayer) {
        System.out.println("Amplifier: setCd");
        this.cdPlayer = cdPlayer;
    }

    public void setDvd(DvdPlayer dvdPlayer) {
        System.out.println("Amplifier: setDvd");
        this.dvdPlayer = dvdPlayer;
    }

    public void setStereoSound() {
        System.out.println("Amplifier: setStereoSound");
    }

    public void setSurroundSound() {
        System.out.println("Amplifier: setSurroundSound");
    }

    public void setTuner() {
        System.out.println("Amplifier: setTuner");
    }

    public void setVolume(int volume) {
        System.out.println("Amplifier: setVolume: " + volume);
    }

    @Override
    public String toString() {
        return "Amplifier";
    }
}








