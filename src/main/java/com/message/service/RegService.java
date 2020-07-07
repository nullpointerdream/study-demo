package com.message.service;

import com.message.bean.User;
import com.message.dao.DBUtil;

import java.util.Scanner;

/**
 * @description: 注册
 * @create: 2020-05-19 17:00
 **/
public class RegService {

    public User reg() throws Exception {
        System.out.println("请输入用户名:");
        Scanner sc=new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("请输入密码:");
        String pwd = sc.nextLine();
        String sql="insert into user(name,password) values(?,?)";
        DBUtil.executeUpdate(sql,name,pwd);
        System.out.println("注册成功");
        return new LoginService().login(name,pwd);
    }
}
