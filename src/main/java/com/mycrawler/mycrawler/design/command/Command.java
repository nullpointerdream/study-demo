package com.mycrawler.mycrawler.design.command;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-04-12 15:24
 **/
public interface Command {
     void execute();
     void undo();
}
