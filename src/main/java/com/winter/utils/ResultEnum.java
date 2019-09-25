package com.winter.utils;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/19 11:20
 * @description：返回信息枚举
 * @modified By：
 * @version: v1.0
 */
public enum ResultEnum {

    USER_TOKEN_EXPRIE(1000,"token过期"),
    USER_EMPTY(1001,"用户id不能为空"),
    TENCENT_ERROR(1002,"调用腾讯接口错误"),
    DOMAINNAME_EMPTY(1003,"domainName is empty!"),
    TYPE_EMPTY(1004,"缺少类型"),
    THUMB_FAIL(1006,"点赞错误");

    private Integer value;
    private String msg;
    ResultEnum(Integer value, String msg){
        this.value = value;
        this.msg = msg;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
