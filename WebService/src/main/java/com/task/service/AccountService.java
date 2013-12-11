package com.task.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import sun.jdbc.odbc.JdbcOdbcDriver;

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
    public User login(String email, String password) {
        this.connectDB();
        User user = null;
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
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return user;
    }

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
