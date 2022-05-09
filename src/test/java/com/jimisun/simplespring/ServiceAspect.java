package com.jimisun.simplespring;


import com.jimisun.simplespring.aop20.annotation.Aspect;
import com.jimisun.simplespring.aop20.annotation.Order;
import com.jimisun.simplespring.aop20.aspect.DefaultAspect;
import com.jimisun.simplespring.core.annotation.Component;

import java.lang.reflect.Method;

/**
 * @author jimisun
 * @create 2022-05-09 17:17
 **/
@Aspect(pointcut = "execution(* com.jimisun.simplespring.service.*.*(..))")
@Order(order = 1)
@Component
public class ServiceAspect extends DefaultAspect {


    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        System.out.println("Service AOP 前置Advice...");
    }

    @Override
    public Object after(Class<?> targetClass, Method method, Object[] args, Object returnValue) {
        System.out.println("Service AOP 后置Advice...");
        return returnValue;
    }
}
