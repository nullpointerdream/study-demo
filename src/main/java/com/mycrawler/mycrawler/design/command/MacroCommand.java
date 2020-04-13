package com.mycrawler.mycrawler.design.command;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-04-12 15:52
 **/
public class MacroCommand {
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void excute(){
        for(int i=0;i<commands.length;i++){
            commands[i].execute();
        }
    }
}
