package com.imooc.workflow.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @PackageName: com.sdcm.web.config
 * @CalssName: SystemContent
 * @Description: 系统上下文路径
 * @Auther: dcm
 * @Date: 2018-7-10 9:31
 */

public class SystemContent {
    /**上下文中 http request*/
    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

    /**上下文中 http response*/
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponse() {
        return responseLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static HttpSession getSession() {
        return requestLocal.get().getSession();
    }

}
