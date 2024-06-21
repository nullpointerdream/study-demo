package com.message.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @description: 用户类
 * @create: 2020-05-19 16:37
 **/
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;


    private List<Integer> list;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    static ThreadLocal<String> a = new ThreadLocal<>();

    public static void main(String[] args) {

        String str = "aa";
        a.set(str);

        bb(str);

    }

    private static void bb(String str) {
        str = "ADAD";
        System.out.println(a.get());
    }
}
