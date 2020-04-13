package com.mycrawler.mycrawler.design.command;

import com.mycrawler.mycrawler.design.command.object.Light;

/**
 * @program: study-demo
 * @description: 开灯
 * @author: chenjiale
 * @create: 2020-04-12 15:24
 **/
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
            light.on();
    }

    @Override
    public void undo() {
        light.off();

    }
}
