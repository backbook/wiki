package com.backbook.wiki.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
//过滤器
public class LogFilter implements Filter {


    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //打印请求信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOG.info("----LogFilter开启----");
        LOG.info("请求地址：{} {} ", httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        LOG.info("远程地址：{} ", httpServletRequest.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.info("---------LogFilter 结束 耗时：{} ms------------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void destroy() {
    }


}
