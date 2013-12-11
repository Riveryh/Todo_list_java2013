package com.task.entity;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-11-29
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int uid;

    private String username;

    private String password;

    private String email;

    private String utime;

    private String ctime;

    public User() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }
}
