package com.jimisun.simplespring.aop10.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {

    Class<? extends Annotation> pointcut();
}
