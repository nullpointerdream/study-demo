package com.message.service;

import com.message.bean.User;

import java.util.Scanner;

/**
 * @program: study-demo
 * @description: 主菜单
 * @author: chenjiale
 * @create: 2020-05-19 17:30
 **/
public class MenuService {

    public void show() throws Exception {
        System.out.println("*********欢迎来到留言板系统*********");
        System.out.println("请输入序号1.登陆\t2.注册\t3.退出系统");
        int i=inputMenu(3);
        User user=null;
        if(i==1){
            LoginService loginService=new LoginService();
            user= loginService.login();
        }else if(i==2){
            RegService regService=new RegService();
            user=regService.reg();
        }else {
            return;
        }
        if(user==null){
            show();
        }else {
            new MessageService().menu(user);
        }

    }
    public static int inputMenu(int max){
        int i=0;
        while (true) {
            try {
                Scanner sc=new Scanner(System.in);
                i = sc.nextInt();
                if (i >= 1 && i <=max) {
                    return i;
                }
            } catch (Exception e) {
            }
            System.out.println("输入错误！请重新输入");
        }
    }
}
