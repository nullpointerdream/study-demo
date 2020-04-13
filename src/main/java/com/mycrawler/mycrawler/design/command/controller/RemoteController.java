package com.mycrawler.mycrawler.design.command.controller;

import com.mycrawler.mycrawler.design.command.Command;
import com.mycrawler.mycrawler.design.command.object.NoCommand;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-04-12 15:35
 **/
public class RemoteController {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteController(){
        onCommands=new Command[7];
        offCommands=new Command[7];
        Command noCommand=new NoCommand();
        for(int i=0;i<7;i++){
            offCommands[i]=noCommand;
            onCommands[i]=noCommand;
        }
        undoCommand=noCommand;
    }
    public void setCommand(int slot,Command onCommand,Command offCommand){
        offCommands[slot]=offCommand;
        onCommands[slot]=onCommand;
    }
    public void onButtonPress(int slot){
        onCommands[slot].execute();
        undoCommand=onCommands[slot];
    }

    public void offButtonPress(int slot){
        offCommands[slot].execute();
        undoCommand=offCommands[slot];
    }

    public void undo(){
        undoCommand.undo();
    }

}
