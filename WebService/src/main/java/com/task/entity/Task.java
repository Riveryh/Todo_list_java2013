package com.task.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-1
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
public class Task {
    private int tid;

    private String title;

    private String description;

    private String tags;

    private int status;

    private int uid;

    private String ctime;

    private String utime;

    private String dtime;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String toString() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tid", tid);
        map.put("title", title);
        map.put("description", description);
        map.put("tags", tags);
        map.put("status", status);
        map.put("uid", uid);
        map.put("ctime", ctime);
        map.put("utime", utime);
        map.put("dtime", dtime);
        return map.toString();
    }
}