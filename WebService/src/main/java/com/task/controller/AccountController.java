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
 * Date: 13-11-29
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody void loginAction(HttpServletRequest request, HttpServletResponse response, User user) {
        String email = user.getEmail();
        AccountService as = new AccountService();
        user = as.login(user.getEmail(), user.getPassword());
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(user.getPassword()+user.getUsername());// 用于返回对象参数
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 一定要response.setCharacterEncoding("UTF-8");后面，不然返回乱码
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody void registerAction(HttpServletRequest request, HttpServletResponse response, User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        AccountService as = new AccountService();
        //String result = AccountService.register(user);
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(user.getPassword()+user.getUsername() + user.getEmail());// 用于返回对象参数
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 一定要response.setCharacterEncoding("UTF-8");后面，不然返回乱码
    }
}
