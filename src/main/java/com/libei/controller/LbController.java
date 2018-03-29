package com.libei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangboqing
 * @date 2018/1/25
 */
@Controller
public class LbController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String loginPage(String flag, Model model) throws Exception {

        if (!StringUtils.isEmpty(flag) && flag.equals("1")) {
            model.addAttribute("message","登录失效，请重新登录！！！");
        } else {
            model.addAttribute("message","");
        }
        return "loginAndRegister";
    }


    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String indexPage() throws Exception {

        return "index";
    }


    @RequestMapping(value = {"/index2"}, method = RequestMethod.GET)
    public String index2Page() throws Exception {

        return "index2";
    }
}
