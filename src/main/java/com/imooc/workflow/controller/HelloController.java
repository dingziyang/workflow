package com.imooc.workflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName: com.imooc.workflow
 * @CalssName: HelloController
 * @Description: TODO
 * @Auther: dcm
 * @Date: 2018-7-23 9:35
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/str")
    public String hello(){
        return "测试返回字符串：hello world";
    }

    @GetMapping("/page")
    public ModelAndView page(){

        ModelAndView modelAndView = new ModelAndView("/hello");
        return modelAndView;
    }
}
