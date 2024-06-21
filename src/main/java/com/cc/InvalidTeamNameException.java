package com.cc;

public class InvalidTeamNameException extends Exception {

    public InvalidTeamNameException(String message) {
        super("Invalid team name exception");
    }

}