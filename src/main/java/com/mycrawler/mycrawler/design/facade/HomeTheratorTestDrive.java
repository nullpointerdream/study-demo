package com.mycrawler.mycrawler.design.facade;

public class HomeTheratorTestDrive {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        Tuner tuner = new Tuner();
        DvdPlayer dvd = new DvdPlayer();
        CdPlayer cd = new CdPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        PopcornPopper popper = new PopcornPopper();

        HomeTheratorFacade homeTheator = 
                new HomeTheratorFacade(amp, tuner, dvd, cd,
                        projector, screen, lights, popper);
        homeTheator.watchMovie("Radiers of the Lost Ark");
        homeTheator.endMovie();
    }
}