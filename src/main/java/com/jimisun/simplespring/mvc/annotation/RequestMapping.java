package com.jimisun.simplespring.mvc.annotation;


import com.jimisun.simplespring.mvc.type.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    //请求路径
    String value() default "";

    //请求方法
    RequestMethod method() default RequestMethod.GET;


}
