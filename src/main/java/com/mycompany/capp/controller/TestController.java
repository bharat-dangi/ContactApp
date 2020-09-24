package com.mycompany.capp.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    
    @RequestMapping("/test/hello")
    public String helloWorld(){
        return "hello";
    }
    
    @RequestMapping("/test/ajax_test")
    public String testPage(){
        return "test";
    }
    
    @RequestMapping("/test/get_time")
    @ResponseBody
    public String getServerTime(){
        System.out.println("getservertime");
        Date d=new Date();
        return d.toString();
    }
    
}
