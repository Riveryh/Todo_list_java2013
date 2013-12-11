package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-1
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "hello";
    }
}
