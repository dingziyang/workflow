package com.imooc.workflow.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @PackageName: com.sdcm.web.controller
 * @CalssName: ErrorPageConfig
 * @Description: 自定义错误页面对应的url。需要自己实现对应的controller
 * @Auther: dcm
 * @Date: 2018-5-16 17:03
 */

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    /**
      * @author dcm
      * @date 2018-5-17 10:28
      * @Description: 注册错误页面跳转的url path
      * @param: registry
      * @return void
      * @throws
      */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage e405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        registry.addErrorPages(e404, e405, e500);
    }

}
