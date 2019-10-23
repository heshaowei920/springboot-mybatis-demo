package com.winter.controller;


import com.winter.model.LoginToken;
import com.winter.model.User;
import com.winter.service.UserService;
import com.winter.utils.JwtUtil;
import com.winter.utils.RedisUtil;
import com.winter.utils.RedissonLockUtil;
import com.winter.utils.Result;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    public <T> void doTest(List<T> list){
        for (Object userId:list){
            System.out.println(userId.toString());
        }
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

        String key="aaa";
        boolean flag=RedissonLockUtil.tryLock(key,60,60);
        logger.info("lock status is {}",flag);

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


    public static void readTxt(String filePath) {

        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                int count=0;
                while ((lineTxt = br.readLine()) != null) {
                    if (lineTxt.contains("\"userId\":")){
                        count++;
                        int count1 = lineTxt.indexOf("\"userId\":");
                        System.out.println(lineTxt.substring(count1,count1+18));
                    }
                }
                System.out.println(count);
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }

    }


    public static void main(String[] args) {
        String filePath = "E:\\cglive-api.log";
        readTxt(filePath);
    }
}
