package com.mycrawler.mycrawler.design.command.controller;

import com.mycrawler.mycrawler.design.command.Command;

/**
 * @program: study-demo
 * @description: 遥控器
 * @author: chenjiale
 * @create: 2020-04-12 15:28
 **/
public class SimpleRemoteController {
    Command slot;

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}
