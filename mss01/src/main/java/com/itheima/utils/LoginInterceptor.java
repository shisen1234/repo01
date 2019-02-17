package com.itheima.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行");
        /*if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }*/
        //如果session不为空的话,放行
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("user") != null);
        if (session.getAttribute("user") != null) {

            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return false;
        }


}
