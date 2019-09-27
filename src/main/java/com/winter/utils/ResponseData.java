package com.winter.utils;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/26 8:58
 * @description：统一返回泛型类
 * @modified By：
 * @version: v1.0
 */
public class ResponseData <T> {
    private Integer resultCode;
    private String message;
    private T data;

    public ResponseData(){}

    public ResponseData(Integer resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public ResponseData<T> success(T data){
        this.resultCode = 200;
        this.message = "请求成功";
        this.data=data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
