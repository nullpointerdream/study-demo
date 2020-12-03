package com.mycrawler.mycrawler.mapstruct;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-03 10:21
 **/
public class Test {

    public Object convertEntity() {
        User user = User.builder()
                .id(1)
                .name("张三")
                .createTime("2020-04-01 11:05:07")
                .updateTime(LocalDateTime.now())
                .build();
        List<Object> objectList = new ArrayList<>();

        objectList.add(user);

        // 使用mapstruct
        UserVO1 userVO1 = UserCovertBasic.INSTANCE.toConvertVO1(user);
        objectList.add("userVO1:" + UserCovertBasic.INSTANCE.toConvertVO1(user));
        objectList.add("userVO1转换回实体类user:" + UserCovertBasic.INSTANCE.fromConvertEntity1(userVO1));
        // 输出转换结果
        objectList.add("userVO2:" + " | " + UserCovertBasic.INSTANCE.toConvertVO2(user));
        // 使用BeanUtils
        UserVO2 userVO22 = new UserVO2();
        BeanUtils.copyProperties(user, userVO22);
        objectList.add("userVO22:" + " | " + userVO22);

        return objectList;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(new Test().convertEntity()));
    }

}
