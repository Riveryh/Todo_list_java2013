package com.task.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import sun.jdbc.odbc.JdbcOdbcDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.task.entity.*;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-11-29
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AccountService extends Common{

    /**
     * 登录验证
     *
     * @return Map<String, Object>
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-11-29
     */
    public Map<String, Object> login(String email, String password) {
        this.connectDB();
        User user = new User();
        int errcode = 104;
        System.out.println(sha1(password));
        try {
            String sql = "SELECT * FROM user WHERE email='" + email + "'" +
                        " AND password='" + sha1(password) + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            System.out.println(rs.getFetchSize());
            if(rs.next()) {
                user.setEmail(email);
                user.setUsername(rs.getString("username"));
                user.setUid(rs.getInt("uid"));
                errcode = 100;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        user.setUid(1);
        errcode = 100;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errcode", errcode);
        map.put("user", user);
        return map;
    }

    /**
     * 注册
     *
     * @return Map<String, Object>
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-11-29
     */
    public Map<String, Object> register(User user)
    {
        this.connectDB();
        int uid = 0;
        int errcode = 105;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String now = df.format(new Date());// new Date()为获取当前系统时间
            String sql = "INSERT INTO user(email, username, password, ctime, utime) " +
                        "VALUES('" + user.getEmail() + "','" + user.getUsername() + "','" +    sha1(user.getPassword()) +
                        "','" + now + "','" + now + "')";
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS uid");
            if(rs.next()) {
                uid = rs.getInt("uid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errcode", errcode);
        map.put("uid", uid);
        return map;
    }
}
