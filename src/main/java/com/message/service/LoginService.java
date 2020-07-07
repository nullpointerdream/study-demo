package com.message.service;

import com.message.bean.User;
import com.message.dao.DBUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: study-demo
 * @description: 登陆
 * @author: chenjiale
 * @create: 2020-05-19 16:59
 **/
public class LoginService {
    public User login() throws Exception {

            System.out.println("请输入用户名:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            System.out.println("请输入密码:");
            String pwd = sc.nextLine();
                User user=login(name,pwd);
                return user;

    }

    public User login(String name, String pwd)  {
        String sql="select * from user where name=? and password=?";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, name, pwd);
        if(maps.isEmpty()){
            System.out.println("账户或密码错误");
           return null;
        }
        Map<String, Object> stringObjectMap = maps.get(0);
        int id = (int) stringObjectMap.get("id");

        return new User(id,name);
    }
}
