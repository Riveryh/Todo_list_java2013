package com.task.service;

import com.task.entity.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-1
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class TaskService extends Common {
    public Task createTask(Task task) {
        connectDB();
        try {
            String sql = "INSERT INTO task(title, description, status, uid, dtime, ctime, utime) " +
                    "VALUES('" + task.getTitle() + "','" + task.getDescription() + "','" + task.getStatus() +
                    "','" + task.getUid() + "','" + task.getDtime() +
                    "','2011-11-11 00:00:00','2011-11-11 00:00:00')";
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS tid");
            task.setTid(rs.getInt("tid"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return task;
    }

    public Task setTask(Task task) {
        connectDB();
        try {
            String sql = "UPDATE task" +
                    "SET title='" + task.getTitle() + "', description='" + task.getDescription() +
                    "',status='" + task.getStatus() + "',dtime='" + task.getDtime() +
                    "', utime='2011-11-11 00:00:00'" +
                    "WHERE tid=" + task.getTid();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return task;
    }

    public ArrayList<Task> getTaskList(int uid) {
        ArrayList<Task> list = new ArrayList<Task>();
        connectDB();
        try {
            String sql = "SELECT * FROM task WHERE uid=" + uid;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Task t = new Task();
                t.setTid(rs.getInt("tid"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setUid(rs.getInt("uid"));
                t.setStatus(rs.getInt("status"));
                t.setUtime(rs.getString("utime"));
                t.setCtime(rs.getString("ctime"));
                t.setDtime(rs.getString("dtime"));
                list.add(t);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return list;
    }
}
