package com.backbook.wiki.aspect;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);


    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.backbook.*.controller..*Controller.*(..))")
    public void controllerPointcut(){

    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //开始请求打印日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        //打印请求信息
        LOG.info("--------------LogAspect--------------");
        LOG.info("请求地址：{} {}",request.getRequestURL(),request.getMethod());
        LOG.info("类名方法：{}",signature.getDeclaringType().getName());
        LOG.info("远程地址：{}",request.getRemoteAddr());
        //打印请求参数
        Object[] args = joinPoint.getArgs();

        Object[] argments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile){
                continue;
            }
            argments[i] = args[i];
        }
        String[] execludProperties = {"password","file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(execludProperties);
        LOG.info("请求参数: {}", JSONObject.toJSONString(argments,excludeFilter));
    }


    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //排除字段
        String[] execludProperties = {"password","file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(execludProperties);
        LOG.info("返回结果：{}",JSONObject.toJSONString(result,excludeFilter));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }



}
