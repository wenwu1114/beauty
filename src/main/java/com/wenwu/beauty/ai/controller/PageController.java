package com.wenwu.beauty.ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index/index")
    public String index(){
        return "index";
    }
}
