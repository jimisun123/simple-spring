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
@Aspect(pointcut = "execution(* com.jimisun.simplespring.controller.*.*(..))")
@Order(order = 1)
@Component
public class ControllerAspect extends DefaultAspect {


    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        System.out.println("Controller AOP 前置Advice 1 ...");
    }

    @Override
    public Object after(Class<?> targetClass, Method method, Object[] args, Object returnValue) {
        System.out.println("Controller AOP 后置Advice 1 ...");
        return returnValue;
    }
}
