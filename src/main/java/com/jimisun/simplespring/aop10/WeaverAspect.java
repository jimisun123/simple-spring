package com.jimisun.simplespring.aop10;

import com.jimisun.simplespring.aop10.annotation.Aspect;
import com.jimisun.simplespring.aop10.annotation.Order;
import com.jimisun.simplespring.aop10.aspect.AspectInfo;
import com.jimisun.simplespring.aop10.aspect.DefaultAspect;
import com.jimisun.simplespring.aop10.utils.CglibProxyCreator;
import com.jimisun.simplespring.core.SimpleBeanFactory;
import com.jimisun.simplespring.utils.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

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

        //step 2 梳理Pointcut和Aspect关系
        Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap();
        for (Class<?> clazz : aspectSet) {
            List<AspectInfo> aspectInfos;
            Order order = clazz.getAnnotation(Order.class);
            Aspect aspect = clazz.getAnnotation(Aspect.class);
            DefaultAspect defaultAspect = (DefaultAspect) simpleBeanFactory.getBean(clazz);
            if (categorizedMap.containsKey(aspect.pointcut())) {
                aspectInfos = categorizedMap.get(aspect.pointcut());
            } else {
                aspectInfos = new ArrayList<>();
            }
            aspectInfos.add(new AspectInfo(order.order(), defaultAspect));
            categorizedMap.put(aspect.pointcut(), aspectInfos);
        }
        // step 3 查找每个切入点在容器中对bean 对每个bean按照切入点对应对aspect进行增强，增强后放入容器中
        for (Class<? extends Annotation> pointcut : categorizedMap.keySet()) {
            weaverByPointcut(pointcut, categorizedMap.get(pointcut));
        }


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
