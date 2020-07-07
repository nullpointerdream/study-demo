package com.message.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类？
 * 一、增删改的通用方法
 * 二、查询的通用方法
 * 三、关闭的通用方法
 */
public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/webstore?useUnicode=true&characterEncoding=utf-8", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增删改的通用方法
     *
     * @paramString sql  要执行的sql
     * @paramObject[] obj    对象类型的数组  里面存放着 sql执行的占位符参数
     * 【name，age，id】
     * 【id】
     * 【name，age】
     * Object... 可变参数
     */
    public static boolean executeUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            int i = ps.executeUpdate();

            if (i > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            close(conn, ps, null);
        }
        return false;
    }

    /**
     * c查询的通用方法
     *
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... args) { //可变参数  Object... args
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            /* 有可能有参数 */
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*执行*/
            set = ps.executeQuery();
            /*需要将所有数据都存放到 List中    每一行 用一个 map存放*/
            List<Map<String, Object>> list = new ArrayList<>();
            /*获取本次查询结果集有多少列*/
            int count = set.getMetaData().getColumnCount();

            while (set.next()) {
                Map<String, Object> map = new HashMap<>();//一行数据 用一个map 接收
                /*
                我们不用在乎数据库表中有几列
                通过  getMetData().getColumnLabel() 获取列
                因为用的map键值对集合  得到了列  就能得到相应的values值

                 */
                for (int i = 0; i < count; i++) {
                    String name = set.getMetaData().getColumnLabel(i + 1);
                    map.put(name, set.getObject(name));
                }
                /*将每行的map存放到 List中*/
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, set);
        }
        return null;
    }

    /**
     * 关闭的通用方法
     * 先进后出的原则
     */
    private static void close(Connection conn, PreparedStatement st, ResultSet set) {
        try {
            if (set != null) {
                set.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
