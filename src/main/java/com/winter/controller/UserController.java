package com.winter.controller;


import com.winter.model.LoginToken;
import com.winter.model.User;
import com.winter.service.UserService;
import com.winter.utils.JwtUtil;
import com.winter.utils.RedisUtil;
import com.winter.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by Administrator on 2017/8/16.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/add")
    public Result addUser(@RequestBody User user) {
        Result result = new Result();
        userService.addUser(user);
        return result.success(user.getUserId());
    }


    @RequestMapping(value = "/all/{pageNum}/{pageSize}")
    public Result findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        Result result = new Result();
        List<User> list = userService.findAllUser(pageNum, pageSize);

        return result.success(list);
    }

    @RequestMapping("/login")
    public Result login() {
        Result result = new Result();
        Integer userId = 1111;
        LoginToken loginToken = new LoginToken(userId, "123");
        String token = JwtUtil.sign(loginToken, 1000 * 60 * 3);
        redisUtil.set("token:" + userId, token, 60 * 3);
        return result.success(token);
    }

    @RequestMapping("/test")
    public Result test(HttpServletRequest request) {
        Result result = new Result();
        String token = request.getHeader("token");
        logger.info(token);
        LoginToken loginToken = JwtUtil.unsign(token, LoginToken.class);
        return result.success(loginToken);
    }

}
