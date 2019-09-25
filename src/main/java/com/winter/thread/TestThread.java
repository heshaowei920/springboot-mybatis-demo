package com.winter.thread;

import java.util.concurrent.*;

/**
 * @author ：heshaowei
 * @date ：Created in 2019/9/5 14:37
 * @description：测试多线程
 * @modified By：
 * @version: v1.0
 */
public class TestThread {

    public static volatile BlockingQueue queue = new LinkedBlockingDeque(100);

    public static void createThreadPoolExecutor() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                new ThreadPoolExecutor.AbortPolicy());

        final CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 1; i < 9; i++) {
            final int currentIndex = i;
            System.out.println("提交第" + i + "个线程");
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ", currentIndex is : " + currentIndex);
                while (!queue.isEmpty()) {
                    System.out.println(currentIndex + ":" + queue.poll());
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            });
        }

        System.out.println("全部提交完毕");

        try {
            System.out.println("准备等待线程池任务执行完毕");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全部线程执行完毕");
    }

    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 100; i++) {
            // 将指定元素添加到此队列中，如果没有可用空间，将一直等待（如果有必要）。
            queue.put(i);
            System.out.println("向阻塞队列中添加了元素:" + i);

        }

        createThreadPoolExecutor();

    }
}
