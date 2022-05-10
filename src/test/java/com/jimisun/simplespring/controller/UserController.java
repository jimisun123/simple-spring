package com.jimisun.simplespring.controller;

import com.jimisun.simplespring.core.annotation.Controller;
import com.jimisun.simplespring.injection.annotation.Resource;
import com.jimisun.simplespring.service.UserService;

/**
 * @author jimisun
 * @create 2022-05-08 16:52
 **/
@Controller
public class UserController {


    @Resource
    private UserService userService;


    public void handler() {
        userService.sayHello();
    }


}
