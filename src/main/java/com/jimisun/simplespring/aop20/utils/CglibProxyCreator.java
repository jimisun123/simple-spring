package com.jimisun.simplespring.aop20.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author jimisun
 * @create 2022-05-09 16:31
 **/
public class CglibProxyCreator {

    public static Object createCglibProxy(Class<?> clazz, MethodInterceptor methodInterceptor) {
        return Enhancer.create(clazz, methodInterceptor);
    }
}
