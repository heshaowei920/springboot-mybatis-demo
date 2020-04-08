package com.winter.interceptor;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.winter.model.LoginToken;
import com.winter.utils.JwtUtil;
import com.winter.utils.RedisUtil;
import com.winter.utils.Result;
import com.winter.utils.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/25 17:08
 * @description：验证登陆token
 * @modified By：
 * @version: v1.0
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Result result = new Result();
        String url = request.getServletPath();
        response.setCharacterEncoding("utf-8");
        logger.info(url);
        if (url.contains("/login")) {
            return true;
        }
        //todo:先不做验证token
        return true;

        /*
        String token = request.getHeader("token");
        if (token == null) {
            responseMessage(response, response.getWriter(), result);
            return false;
        }
        LoginToken loginToken = JwtUtil.unsign(token, LoginToken.class);

        if (loginToken == null) {
            responseMessage(response, response.getWriter(), result);
            return false;
        }

        String userId = loginToken.getUserId().toString();
        Object obj = redisUtil.get("token:" + userId);
        if (obj == null) {
            responseMessage(response, response.getWriter(), result);
            return false;
        }

        if (obj.toString().equals(token)) {
            return true;
        }

        return false;*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void responseMessage(HttpServletResponse response, PrintWriter out, Result result) {
        result.fail(ResultEnum.USER_TOKEN_EXPRIE);
        response.setContentType("application/json; charset=utf-8");
        Gson gson = new Gson();
        String json = new Gson().toJson(result);

        Result result1 = gson.fromJson(json, Result.class);
        logger.info(result1.getMessage());
        JsonObject object=(JsonObject) new JsonParser().parse(json);
        logger.info(object.get("message").getAsString());
        out.print(json);
        out.flush();
        out.close();
    }
}
