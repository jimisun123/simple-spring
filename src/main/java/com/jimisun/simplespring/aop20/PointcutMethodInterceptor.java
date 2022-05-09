package com.jimisun.simplespring.aop20;

import com.jimisun.simplespring.aop20.aspect.AspectInfo;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jimisun
 * @create 2022-05-09 16:28
 **/
public class PointcutMethodInterceptor implements MethodInterceptor {

    //被代理对象
    private Class<?> clazz;

    //被代理对象的Aspect
    private List<AspectInfo> aspectInfoList;

    public PointcutMethodInterceptor(Class<?> clazz, List<AspectInfo> aspectInfoList) {
        this.clazz = clazz;
        this.aspectInfoList = sortAspectList(aspectInfoList);
    }


    @Override
    public Object intercept(Object proxyObject, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //重置命中的Aspect列表
        resetHitAspects(aspectInfoList, method);

        Object returnValue = null;
        //执行前置Advice
        for (AspectInfo aspectInfo : aspectInfoList) {
            aspectInfo.getDefaultAspect().before(clazz, method, objects);
        }
        //执行方法
        returnValue = methodProxy.invokeSuper(proxyObject, objects);
        
        //执行后置
        for (int i = aspectInfoList.size() - 1; i >= 0; i--) {
            returnValue = aspectInfoList.get(i).getDefaultAspect().after(clazz, method, objects, returnValue);
        }
        return returnValue;
    }

    private void resetHitAspects(List<AspectInfo> aspectInfoList, Method method) {
        List<AspectInfo> result = new ArrayList<>();
        for (AspectInfo aspectInfo : aspectInfoList) {
            if (aspectInfo.getPointcutLocator().accurateMatches(method)) {
                result.add(aspectInfo);
            }
        }
        this.aspectInfoList = sortAspectList(result);
    }

    private List<AspectInfo> sortAspectList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, Comparator.comparingInt(AspectInfo::getOrder));
        return aspectInfoList;
    }

}
