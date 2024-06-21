package com.message.bean;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 留言版
 * @create: 2020-05-19 16:38
 **/
public class Message {
    private Integer id;
    private String content;
    private String author;
    //1 公开 2私有
    private int isPublic;
    private String createTime;
    public Message(Integer id){
        this.id=id;
    }

    public Message(String id){
        this.content=id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }


    public static void main(String[] args) {
        List<Message> list = new ArrayList<>();
        list.add(new Message("1"));
        list.add(new Message("343222"));
        list.add(new Message("3222"));
        list.add(new Message("4222"));
        list.add(new Message("22"));
        System.out.println(JSON.toJSONString(list));
        Collections.sort(list,Comparator.comparing(k -> k, Comparator.comparingInt(a -> StringUtils.length(a.getContent()))));
        System.out.println(JSON.toJSONString(list));

    }
}
