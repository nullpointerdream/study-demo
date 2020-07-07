package com.message;

import com.message.bean.User;
import com.message.service.LoginService;
import com.message.service.MenuService;
import com.message.service.RegService;

import java.util.Scanner;

/**
 * @create: 2020-05-19 16:46
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        MenuService menuService=new MenuService();
        menuService.show();
    }


}
