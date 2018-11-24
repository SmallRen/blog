package com.renbaojia.blog.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
abstract class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     *
     * @param page
     * @return 通用页面跳转
     */
    @RequestMapping("/{page}")
    private String page(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/")
    private String index() {
        return "index.html";

    }
}
