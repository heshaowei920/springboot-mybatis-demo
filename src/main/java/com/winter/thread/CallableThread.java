package com.winter.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/10/16 9:10
 * @description：test callable
 * @modified By：
 * @version: v1.0
 */
public class CallableThread implements Callable<String> {

    private static AtomicInteger atomicInteger=new AtomicInteger(0);

    @Override
    public String call() throws Exception {
        Thread.sleep(6000);
        return "123456";

    }
}
