package com.mycrawler.mycrawler.design.facade.no.web;

import com.mycrawler.mycrawler.design.facade.no.domain.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * 博  客：http://bugstack.cn
 * Create by 小傅哥 on @2020
 */
@RestController
public class HelloWorldController {

    private int port=8080;


    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
            // 做白名单拦截
            List<String> userList = new ArrayList<String>();
            userList.add("1001");
            userList.add("aaaa");
            userList.add("ccc");
            if (!userList.contains(userId)) {
                return new UserInfo("1111", "非白名单可访问用户拦截！");
            }

            return new UserInfo("虫虫:" + userId, 19, "天津市南开区旮旯胡同100号");
        }


}