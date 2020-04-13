package com.mycrawler.mycrawler.design.command;

import com.mycrawler.mycrawler.design.command.object.Light;

/**
 * @program: study-demo
 * @description: 开灯
 * @author: chenjiale
 * @create: 2020-04-12 15:24
 **/
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
            light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
