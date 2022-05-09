package com.jimisun.simplespring.aop10.aspect;

import java.lang.reflect.Method;

/**
 * @author jimisun
 * @create 2022-05-09 16:20
 **/
public abstract class DefaultAspect {

    /**
     * 方法执行之前拦截
     *
     * @param targetClass 被代理对象Class类型
     * @param method      被代理对象方法
     * @param args        被代理的目标方法对应的参数列表
     * @throws Throwable
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * 方法执行之后拦截
     *
     * @param targetClass 被代理对象Class类型
     * @param method      被代理对象方法
     * @param args        被代理的目标方法对应的参数列表
     * @param returnValue 被代理的目标方法执行后的返回值
     * @return
     */
    public Object after(Class<?> targetClass, Method method, Object[] args, Object returnValue) {
        return returnValue;
    }
}
