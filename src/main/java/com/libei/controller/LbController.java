package com.libei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangboqing
 * @date 2018/1/25
 */
@Controller
public class LbController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String loginPage() throws Exception {

        return "loginAndRegister";
    }


    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String indexPage() throws Exception {

        return "index";
    }
}
