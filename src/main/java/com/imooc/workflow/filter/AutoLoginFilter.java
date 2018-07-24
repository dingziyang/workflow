package com.imooc.workflow.filter;

import com.imooc.workflow.config.SystemContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName: com.sdcm.web.controller
 * @CalssName: AutoLoginFilter
 * @Description: 自动登录过滤器
 * @Auther: dcm
 * @Date: 2018-5-16 17:00
 */

@Configuration
@WebFilter(filterName = "autoLoginFilter", urlPatterns = "/*")
public class AutoLoginFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AutoLoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[------自定义filter init......]");
        // this.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        log.info("[------自定义filter do......]");
        // 获取出需要使用的对象
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //将上下文信息装入thread local
        SystemContent.setRequest(request);
        SystemContent.setResponse(response);

        // 对请求进行判断,去除静态路径
        String url = request.getRequestURI().substring(request.getContextPath().length());
        if(url.endsWith(".js") || url.endsWith(".woff") || url.endsWith(".png") || url.endsWith(".css")) {

        } else {
            log.info("---------url:" + url);
        }

        // 登录相关操作不拦截
        if (url.startsWith("/login/")) {
            chain.doFilter(request, response);//不拦截
            return;
        }

        // swagger api相关操作不拦截
        if (url.startsWith("/swagger") || url.startsWith("/v2/api-docs")) {
            chain.doFilter(request, response);//不拦截
            return;
        }

        // actuator监控相关操作不拦截
        if (url.startsWith("/actuator")) {
            chain.doFilter(request, response);//不拦截
            return;
        }

        // 静态资源不拦截
        if (url.startsWith("/css") || url.startsWith("/fonts") || url.startsWith("/images") || url.startsWith("/js") || url.startsWith("/static")) {
            chain.doFilter(request, response);
            return;
        }
        else { // 非静态资源
//            User user = (User)((HttpServletRequest) req).getSession().getAttribute("loginUser");
//            if (user != null) { // 用户已登录
//                if (url.equals("/")) { // 已登录，访问根路径，自动跳转到主页面
//                    response.sendRedirect( "/index");
//                    return;
//                }
//                chain.doFilter(request, response);//不拦截
//                return;
//            } else {
//                log.info("session中没有用户的信息，跳转到login页面");
//                // 其它情况，跳转到登录页
//                response.sendRedirect("/login/toLogin");
//            }
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

        log.info("[------自定义filter destroy......]");
        //this.destroy();
    }
}
