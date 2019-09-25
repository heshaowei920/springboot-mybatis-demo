package com.winter.utils;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/25 14:45
 * @description：返回结果类
 * @modified By：
 * @version: v1.0
 */
public class Result {

    private Integer resultCode;
    private String message;
    private Object data;

    public Result success(Object data) {
        this.resultCode = 200;
        this.data = data;
        this.message = "请求成功";
        return this;
    }

    public Result success() {
        this.resultCode = 200;
        this.message = "请求成功";
        return this;
    }

    public Result fail(ResultEnum resultEnum) {
        this.resultCode = resultEnum.getValue();
        this.message = resultEnum.getMsg();
        return this;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
