package com.message.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 用户类
 * @create: 2020-05-19 16:37
 **/
@Data
public class User2 {
    private Integer id;
    private String name;
    private BigDecimal password;

    public User2(Integer id, String name, BigDecimal password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


}
