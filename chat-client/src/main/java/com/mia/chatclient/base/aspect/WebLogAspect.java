package com.mia.chatclient.base.aspect;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: guandezhi
 * @Date: 2019/1/11 14:17
 * 全局日志记录
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    private final static ThreadLocal<String> methodDescribe = new ThreadLocal<>();


    @Pointcut("execution(* com.mia.chatclient.controller.*.*(..))")
    public void poincut() {
    }

    @Before("poincut()")
    public void doBefore(JoinPoint jp) {
        //获取切入方法描述
        log.info(getMethodDescribe(jp) + "，请求：{}", parseArgs(jp.getArgs()));
    }

    private String parseArgs(Object[] args) {
        StringBuffer sb = new StringBuffer();
        if (null != args) {
            for (Object arg : args) {
                if (arg instanceof BindingResult ||
                        arg instanceof HttpServletRequest ||
                        arg instanceof HttpServletResponse ||
                        arg instanceof MultipartFile ||
                        arg instanceof MultipartFile[]) {
                    continue;
                }
                sb.append(" ");
                sb.append(JSONObject.toJSONString(arg));
            }
        }
        return sb.toString();
    }


    @AfterReturning(returning = "ret", pointcut = "poincut()")
    public void doAfter(Object ret) {
        log.info(methodDescribe.get() + "，返回：{}", JSONObject.toJSONString(ret));
        //清除线程变量
        methodDescribe.remove();
    }

    private String getMethodDescribe(JoinPoint jp) {
        Method method = getMethod(jp);
        String value = "";
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            value = apiOperation.value();
            if (StringUtils.isNotEmpty(value)) {
                methodDescribe.set(value);
            }
        }
        return value;
    }

    private Method getMethod(JoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        return method;
    }


}
