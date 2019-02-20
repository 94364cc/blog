package com.shop.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/2/1.
 */
@Controller
public class LoginContrller {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
