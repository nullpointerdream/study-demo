package com.cc;

public enum PlayerCategory {
    GOALKEEPER(0), MIDFIELDER(5), STRIKER(10), DEFENDER(2);
    int jerseyNumber;

    PlayerCategory(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

}
