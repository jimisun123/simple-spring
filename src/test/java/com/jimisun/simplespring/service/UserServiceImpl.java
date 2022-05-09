package com.jimisun.simplespring.service;

import com.jimisun.simplespring.core.annotation.Service;

/**
 * @author jimisun
 * @create 2022-05-08 16:53
 **/
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void sayHello() {
        System.out.println("say hello");
    }
}
