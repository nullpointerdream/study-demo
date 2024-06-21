package com.message.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 用户类
 * @create: 2020-05-19 16:37
 **/
@Data
public class User3 {
    private Integer id;
    private String name;
    private BigDecimal password;
    private String passwordStr;

    public String getPassword() {
        return passwordStr;
    }

    public static void main(String[] args) {
      List<String> a = Arrays.asList("1","2","3");
      Set<String> h = new HashSet<>(a);
        System.out.println(JSON.toJSONString(h));
        System.out.println(h.remove("11"));
        System.out.println(h.remove("1"));

    }
}
