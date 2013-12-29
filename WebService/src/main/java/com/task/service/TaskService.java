package com.task.service;

import com.task.entity.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-1
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class TaskService extends Common {

    /**
     * 创建task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-05
     */
    public Task createTask(Task task) {
        connectDB();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String now = df.format(new Date());// new Date()为获取当前系统时间
            String sql = "INSERT INTO task(title, description, status, uid, dtime, ctime, utime) " +
                    "VALUES('" + task.getTitle() + "','" + task.getDescription() + "'," + task.getStatus() +
                    "," + task.getUid() + ",'" + task.getDtime() + "','" + now + "','" + now + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS tid");
            task.setTid(rs.getInt("tid"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return task;
    }

    /**
     * 设置task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-07
     */
    public Task setTask(Task task) {
        connectDB();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String now = df.format(new Date());// new Date()为获取当前系统时间
            String sql = "UPDATE task" +
                    "SET title='" + task.getTitle() + "', description='" + task.getDescription() +
                    "',status='" + task.getStatus() + "',dtime='" + task.getDtime() +
                    "', utime='" + now + "'" +
                    "WHERE tid=" + task.getTid() + "AND uid=" + task.getUid();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
        return task;
    }

    /**
     * 删除task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-15
     */
    public void removeTask(Task task) {
        connectDB();
        try {
            String sql = "DELETE FROM task WHERE tid=" + task.getTid() +"AND uid=" + task.getUid();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        closeDB();
    }

    /**
     * 获取task列表
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-17
     */
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
