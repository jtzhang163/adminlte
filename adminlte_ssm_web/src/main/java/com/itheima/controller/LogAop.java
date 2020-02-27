package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime; //开始时间

    private Class clazz;    //访问的类

    private Method method;  //访问的方法

    //前置通知
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();

        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classes);
        }
    }

    //后置通知
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long time = new Date().getTime() - visitTime.getTime();

        //获取URL
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class) {
            //获取注解内容：@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String classValue = classAnnotation.value()[0];
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String methodValue = methodAnnotation.value()[0];
                    url = classValue + methodValue; //url: /orders/findAll.do
                }
            }
        }

        //获取IP：通过request对象
        String ip = request.getRemoteAddr();

        //获取当前用户名
        SecurityContext context = SecurityContextHolder.getContext();

        //另一种获取方法: SecurityContext context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //封装SysLog
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
        sysLog.setUrl(url);
        sysLog.setVisitTime(visitTime);
        sysLogService.save(sysLog);
    }
}
