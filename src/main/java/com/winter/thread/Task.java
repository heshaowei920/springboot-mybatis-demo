package com.winter.thread;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/5 16:50
 * @description：线程任务
 * @modified By：
 * @version: v1.0
 */
public class Task implements Runnable {

    private static int index = 100;

    private int count;

    public Task(int count) {
        this.count = count;
    }

    private Object obj = new Object();//对象锁只能有一个

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (index > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    index--;
                    System.out.println(Thread.currentThread().getName() + "线程-->" + index);
                }
            }
        }
    }
}
