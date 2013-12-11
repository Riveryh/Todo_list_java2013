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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void taskCreateAction(HttpServletRequest request, HttpServletResponse response, Task task) {

    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public void taskSetAction(HttpServletRequest request, HttpServletResponse response, Task task) {

    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public void taskGetAction(HttpServletRequest request, HttpServletResponse response, Task task) {

    }

    @RequestMapping(value = "/get/all")
    public void taskGetAllAction(HttpServletRequest request, HttpServletResponse response) {

    }
}
