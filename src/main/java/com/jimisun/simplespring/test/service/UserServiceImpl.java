package com.jimisun.simplespring.test.service;

import com.jimisun.simplespring.core.annotation.Service;

/**
 * @author jimisun
 * @create 2022-05-10 10:39
 **/
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void sayHello() {
        System.out.println("hello user service");
    }
}
