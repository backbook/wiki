package com.backbook.wiki.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
//拦截器
public class LogInterceptor implements HandlerInterceptor {


    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("--------------LogInterceptor--------------");
        LOG.info("请求地址：{} {} ", request.getRequestURL(), request.getMethod());
        LOG.info("远程地址：{} ", request.getRemoteAddr());
        long startTime = System.currentTimeMillis();
        request.setAttribute("interceptorRequestStartTime",startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("interceptorRequestStartTime");
        LOG.info("-------------LogInterceptor结束 耗时:{} ms-------------",System.currentTimeMillis() - startTime);
    }

}
