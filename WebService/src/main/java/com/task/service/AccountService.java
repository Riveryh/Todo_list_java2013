package com.task.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import sun.jdbc.odbc.JdbcOdbcDriver;

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
     * @param String email
     * @param String password
     * @return Map<String, Object>
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-11-29
     */
    public Map<String, Object> login(String email, String password) {
        this.connectDB();
        User user = null;
        int errcode = 104;
        try {
            String sql = "SELECT * FROM user WHERE email='" + email + "'" +
                        "AND password='" + sha1(password) + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                user.setEmail(email);
                user.setUsername(rs.getString("username"));
                user.setUid(rs.getInt("uid"));
                user.setUtime(rs.getString("utime"));
                user.setCtime(rs.getString("ctime"));
                errcode = 100;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errcode", errcode);
        map.put("user", user);
        return map;
    }

    /**
     * 注册
     *
     * @param User user
     * @return Map<String, Object>
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-11-29
     */
    public int register(User user)
    {
        this.connectDB();
        int uid = 0;
        try {
            String sql = "INSERT INTO user(email, username, password, ctime, utime) " +
                        "VALUES('" + user.getEmail() + "','" + user.getUsername() + "','" + user.getPassword() +
                        "','2011-11-11 00:00:00','2011-11-11 00:00:00')";
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS uid");
            uid = rs.getInt("uid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return uid;
    }
}
