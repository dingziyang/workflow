package com.imooc.workflow;

import org.activiti.app.conf.ApplicationConfiguration;
import org.activiti.app.servlet.ApiDispatcherServletConfiguration;
import org.activiti.app.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
})
@Import({ApplicationConfiguration.class})

@ComponentScan("com.imooc.workflow.*.**") // 扫描组件，纳入spring容器，这行必须要有
public class WorkflowApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WorkflowApplication.class);
    }

    @Bean
    public ServletRegistrationBean apiDispatcher() {
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(ApiDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(api);
        registrationBean.addUrlMappings("/api/*"); // api下面的所有内容都访问到这里
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        registrationBean.setName("api"); // 不能重复，重复则以最后一个设置的为准

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean appDispatcher() {
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(AppDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(api);
        registrationBean.addUrlMappings("/app/*"); // app下面的所有内容都访问到这里
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        registrationBean.setName("app"); // 不能重复，重复则以最后一个设置的为准

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilter() {
        FilterRegistrationBean<OpenEntityManagerInViewFilter> filterRegistrationBean
                = new FilterRegistrationBean<>(new OpenEntityManagerInViewFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("openEntityManagerInViewFilter");
        filterRegistrationBean.setOrder(-200); // 必须在安全过滤器（-100）之前执行
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));

        return filterRegistrationBean;
    }
}
