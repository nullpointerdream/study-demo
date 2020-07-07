package com.message.service;

import com.message.bean.User;
import com.message.dao.DBUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions.add;

/**
 * @description:
 * @create: 2020-05-20 06:30
 **/
public class MessageService {
    public void menu(User user) throws Exception {
        System.out.println("********* 1.query *********");
        System.out.println("********* 2.add  **********");
        System.out.println("********* 3.delete  **********");
        System.out.println("********* 4.exit  **********");
        System.out.println("请输入序号");
        int i = MenuService.inputMenu(4);
        switch (i){
            case 1:query(user);break;
            case 2:add(user);break;
            case 3:delete(user);break;
            default:new MenuService().show();break;
        }
    }

    private void add(User user) throws Exception {
        System.out.println("请输入留言内容");
        Scanner scanner=new Scanner(System.in);
        String content=scanner.next();
        System.out.println("请输入是否公开1：是 2：否");
        int isPublic = MenuService.inputMenu(2);
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String sql="insert into message(content,author,isPublic,createTime)" +
                "values(?,?,?,?)";
        DBUtil.executeUpdate(sql,content,user.getName(),isPublic,time);
        System.out.println("留言成功");
        menu(user);

    }

    private void delete(User user) throws Exception {
        show(user);
        System.out.println("输入你要删除的序号");
        int id = MenuService.inputMenu(Integer.MAX_VALUE);
        String sql="delete from message where id=?";
        boolean flag = DBUtil.executeUpdate(sql, id);
        if(flag){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败,序号不存在");
        }
        menu(user);
    }

    private void query(User user) throws Exception {
        show(user);
        menu(user);
    }

    private void show(User user) {
        String sql="select * from message where isPublic=1 or author=?";
        List<Map<String, Object>> maps = DBUtil.executeQuery(sql, user.getName());
        System.out.println("序号\t留言内容\t留言姓名\t留言时间");
        for(Map<String,Object> map:maps){
            System.out.println(map.get("id").toString()+"\t"+map.get("content").toString()+"\t"+map.get("author").toString()
                    +"\t"+map.get("createTime").toString());
        }
    }
}
