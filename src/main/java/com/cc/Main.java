package com.cc;

import java.util.concurrent.ExecutionException;

public class Main {

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0;
        int y = 0;
        int max = 0;
        //0北1东2南3西
        int direct = 0;
        for (int command : commands) {
            if (command == -1) {
                direct = (direct + 1) % 4;
            } else if (command == -2) {
                direct = (direct - 1)<0?3:(direct - 1);
            } else {
                switch (direct) {
                    case 0:
                        y = goY(x,y,command,obstacles,1);
                        break;
                    case 1:
                        x = goX(x,y,command,obstacles,1);
                        break;
                    case 2:
                        y = goY(x,y,command,obstacles,-1);
                        break;
                    default:
                        x = goX(x,y,command,obstacles,-1);
                        break;
                }
            }
            max = Math.max(max,x*x+y*y);
        }
        return max;

    }

    private int goY(int x, int y, int command, int[][] obstacles,int fushu) {
        while (command > 0) {
            y = y + 1*fushu;
            for (int[] obstacle : obstacles) {
                if (obstacle[0] == x && obstacle[1] == y) {
                    return y-(1*fushu);
                }
            }
            command--;
        }
        return y;
    }
    private int goX(int x, int y, int command, int[][] obstacles,int fushu) {
        while (command > 0) {
            x = x + 1*fushu;
            for (int[] obstacle : obstacles) {
                if (obstacle[0] == x && obstacle[1] == y) {
                    return x-(1*fushu);
                }
            }
            command--;
        }
        return x;
    }

    // // find all images without alternate text
    // and give them a red border



    public static void main(String[] args) throws InterruptedException, ExecutionException {



    }
}