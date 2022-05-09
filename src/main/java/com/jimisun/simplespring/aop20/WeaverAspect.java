package com.jimisun.simplespring.aop20;


import com.jimisun.simplespring.aop20.annotation.Aspect;
import com.jimisun.simplespring.aop20.annotation.Order;
import com.jimisun.simplespring.aop20.aspect.AspectInfo;
import com.jimisun.simplespring.aop20.aspect.DefaultAspect;
import com.jimisun.simplespring.aop20.aspectj.PointcutLocator;
import com.jimisun.simplespring.aop20.utils.CglibProxyCreator;
import com.jimisun.simplespring.core.SimpleBeanFactory;
import com.jimisun.simplespring.utils.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jimisun
 * @create 2022-05-09 16:43
 **/
public class WeaverAspect {

    private static SimpleBeanFactory simpleBeanFactory;

    public WeaverAspect() {
        this.simpleBeanFactory = SimpleBeanFactory.getInstance();
    }


    public void doAop() {
        //step 1 获取容器中有@Aspect注解的Bean
        Set<Class<?>> aspectSet = simpleBeanFactory.getClassesByAnnotation(Aspect.class);
        if (ValidationUtil.isEmpty(aspectSet)) {
            return;
        }
        //step 2 组装AspectInfo
        List<AspectInfo> aspectInfos = assembleAspectInfo(aspectSet);

        //step 3 遍历容器中所有的Bean 判断其方法命中哪些切点
        Set<Class<?>> classes = simpleBeanFactory.getClasses();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Aspect.class)) {
                //如果该Bean被Aspect注解标注，则跳过
                continue;
            }
            //尝试对该Bean的每个方法进行织入
            attemptWeaverMethod(aspectInfos, clazz);
        }
    }

    private void attemptWeaverMethod(List<AspectInfo> aspectInfos, Class<?> clazz) {
        PointcutMethodInterceptor pointcutMethodInterceptor = new PointcutMethodInterceptor(clazz, aspectInfos);
        Object cglibProxy = CglibProxyCreator.createCglibProxy(clazz, pointcutMethodInterceptor);
        simpleBeanFactory.addBean(clazz, cglibProxy);
    }

    private List<AspectInfo> assembleAspectInfo(Set<Class<?>> aspectSet) {
        List<AspectInfo> aspectInfos = new ArrayList<>();
        for (Class<?> clazz : aspectSet) {
            Aspect aspect = clazz.getAnnotation(Aspect.class);
            DefaultAspect defaultAspect = (DefaultAspect) simpleBeanFactory.getBean(clazz);
            Order order = clazz.getAnnotation(Order.class);
            aspectInfos.add(new AspectInfo(order.order(), defaultAspect, new PointcutLocator(aspect.pointcut())));
        }
        return aspectInfos;
    }

    private static void weaverByPointcut(Class<? extends Annotation> pointcut, List<AspectInfo> aspectInfoList) {
        Set<Class<?>> classesByAnnotation = simpleBeanFactory.getClassesByAnnotation(pointcut);
        if (ValidationUtil.isEmpty(classesByAnnotation)) {
            return;
        }
        for (Class<?> clazz : classesByAnnotation) {
            Object cglibProxy = CglibProxyCreator.createCglibProxy(clazz, new PointcutMethodInterceptor(clazz, aspectInfoList));
            simpleBeanFactory.addBean(clazz, cglibProxy);
        }
    }

    private static boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass);
    }
}
