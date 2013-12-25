package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import com.task.service.*;
import com.task.entity.*;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-1
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    /**
     * 创建task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-05
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void taskCreateAction(HttpServletRequest request, HttpServletResponse response, Task task) {
        TaskService ts = new TaskService();
        task = ts.createTask(task);
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(task.getTid());// 用于返回对象参数
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 一定要response.setCharacterEncoding("UTF-8");后面，不然返回乱码
    }

    /**
     * 修改task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-05
     */
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public void taskSetAction(HttpServletRequest request, HttpServletResponse response, Task task) {
        TaskService ts = new TaskService();
        task = ts.setTask(task);
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(task.getTid());// 用于返回对象参数
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 一定要response.setCharacterEncoding("UTF-8");后面，不然返回乱码
    }

    /**
     * 删除task
     *
     * @author wanghaojie<haojie0429@126.com>
     * @since 2013-12-15
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void taskRemoveAction(HttpServletRequest request, HttpServletResponse response, Task task) {
        TaskService ts = new TaskService();
        ts.removeTask(task);
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(task.getTid());// 用于返回对象参数
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 一定要response.setCharacterEncoding("UTF-8");后面，不然返回乱码
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public void taskGetAction(HttpServletRequest request, HttpServletResponse response, Task task) {

    }

    @RequestMapping(value = "/get/all")
    public void taskGetAllAction(HttpServletRequest request, HttpServletResponse response) {

    }
}
