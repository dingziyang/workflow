package com.imooc.workflow.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName: com.sdcm.web.controller.common
 * @CalssName: ErrorPageCtl
 * @Description: 错误页面控制器
 * @Auther: dcm
 * @Date: 2018-5-16 17:00
 */

@Api(value = "/error", description = "错误页接口")
@Controller
@RequestMapping("/error")
public class ErrorPageCtl {

    @GetMapping("/404")
    public ModelAndView e404(){ return new ModelAndView("/common/error/404"); }

    @GetMapping("/405")
    public ModelAndView e405(){ return new ModelAndView("/common/error/405"); }

    @GetMapping("/500")
    public ModelAndView e500(){ return new ModelAndView("/common/error/500"); }

}
