package com.jimisun.simplespring.test.controller;

import com.jimisun.simplespring.core.annotation.Controller;
import com.jimisun.simplespring.injection.annotation.Resource;
import com.jimisun.simplespring.mvc.annotation.RequestMapping;
import com.jimisun.simplespring.mvc.annotation.ResponseBody;
import com.jimisun.simplespring.mvc.type.RequestMethod;
import com.jimisun.simplespring.test.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jimisun
 * @create 2022-05-10 10:38
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/handler", method = RequestMethod.GET)
    @ResponseBody
    public Object handler() {
        userService.sayHello();
        Map map = new HashMap();
        map.put("code", 200);
        map.put("data", "success");
        return map;
    }
}
