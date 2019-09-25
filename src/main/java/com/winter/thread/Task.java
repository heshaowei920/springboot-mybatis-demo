package com.winter.thread;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/5 16:50
 * @description：线程任务
 * @modified By：
 * @version: v1.0
 */
public class Task implements Runnable{

    private int index;

    public Task(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(index+"/////////////////////////////////");
    }
}
