package com.jimisun.simplespring;

import com.jimisun.simplespring.aop20.WeaverAspect;
import com.jimisun.simplespring.controller.UserController;
import com.jimisun.simplespring.core.SimpleBeanFactory;
import com.jimisun.simplespring.injection.DependencyInjector;

/**
 * @author jimisun
 * @create 2022-05-08 16:52
 **/
public class Test {

    public static void main(String[] args) {

        SimpleBeanFactory factory = SimpleBeanFactory.getInstance();
        factory.loadBeans("com.jimisun.simplespring");
        new WeaverAspect().doAop();
        new DependencyInjector().doDi();
        UserController userController = (UserController) factory.getBean(UserController.class);
        userController.handler();

    }
}
