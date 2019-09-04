package com.winter.controller;

import com.tencentyun.TLSSigAPIv2;
import com.winter.model.User;
import com.winter.model.Users;
import com.winter.service.user.UserService;
import com.winter.utils.RedisUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    MongoTemplate mongotemplate;

    @Autowired
    private RedisUtil redisUtil;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping("/aa")
    @ResponseBody
    public String home() {
        Users users = new Users();
        users.setName("酒仙");
        mongotemplate.insert(users, "userList");

        mongotemplate.save(users);

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("酒仙"));
        String name = mongotemplate.findOne(query, Users.class).getName();


        Users users1 = new Users();
        users1.setName("dd");
        List<Object> list = new ArrayList<>();
        list.add(users);
        list.add(users1);
        redisUtil.lSet("users", list);

        List<Object> list1 = redisUtil.lGet("users", 0, -1);
        for (Object o : list1) {
            Users u = (Users) o;
            System.out.println(u.getName());
        }




        return name;
    }


    public static void main(String[] args) throws Exception{
        String dateStr="2018-12-01 08:30:00";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=f.parse(dateStr);
        System.out.println(to16Hex(date));

        Map map=new HashMap();

        TLSSigAPIv2 api = new TLSSigAPIv2(1400000000, "5bd2850fff3ecb11d7c805251c51ee463a25727bddc2385f3fa8bfee1bb93b5e");
        System.out.print(api.genSig("xiaojun", 180*86400));
    }

    public static String to16Hex(Date date) {
        Long ab = date.getTime() / 1000;
        String a = Long.toHexString(ab);
        return a;
    }

}
