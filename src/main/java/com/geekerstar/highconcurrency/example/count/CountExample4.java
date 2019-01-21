package com.geekerstar.highconcurrency.example.count;

import com.geekerstar.highconcurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author geekerstar
 * date: 2019/1/21 16:04
 * description:
 *
 * 导致共享变量在线程间不可见的原因：
 *      1、线程交叉执行
 *      2、重排序结合线程交叉执行
 *      3、共享变量更新后的值没有在工作内存与主存间及时更新
 *
 * 可见性-synchronized
 * JMM关于synchronized的两条规定：
 *      线程解锁前，必须把共享变量的最新值刷新到主内存
 *      线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意：加锁与解锁是同一把锁）
 *
 * 可见性-volatile
 * 通过加入【内存屏障】和【禁止重排序】优化来实现
 *      1、对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存
 *      2、对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量
 */
@Slf4j
@NotThreadSafe
public class CountExample4 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
        // 1、count
        // 2、+1
        // 3、count
    }
}

