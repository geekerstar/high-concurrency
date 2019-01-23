## J.U.C

![juc.png](https://github.com/geekerstar/high-concurrency/blob/master/img/juc.png)

## AbstractQueuedSynchronizer-AQS

![aqs.png](https://github.com/geekerstar/high-concurrency/blob/master/img/aqs.png)

- 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
- 利用了一个Int类型表示状态
- 使用方法是继承
- 子类通过继承并通过实现它的方法管理其状态{acquire 和 release}的方法操纵状态
- 可以同时实现排它锁和共享锁模式（独占、共享）

## AQS 同步组件
- CountDownLatch
- Semaphore
- CyclicBarrier
- ReentrantLock
- Condition
- FutureTask

## Semaphore

![semaphore.png](https://github.com/geekerstar/high-concurrency/blob/master/img/semaphore.png)

## CyclicBarrier
![cyclicbarrier.png](https://github.com/geekerstar/high-concurrency/blob/master/img/cyclicbarrier1.png)


## ReentrantLock 与锁
ReentrantLock(可重入锁)和synchronized区别
- 可重入性
- 锁的实现
- 性能的区别
- 功能区别

ReentrantLock独有功能
- 可指定是公平锁还是非公平锁
- 提供了一个Condition类，可以分组唤醒需要唤醒的线程
- 提供能够中断等待锁的线程的机制，lock.lockInterruptibly()

## Fork/Join 框架
![fork.png](https://github.com/geekerstar/high-concurrency/blob/master/img/fork.png)

## BlockingQueue
![block.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block.png)

![block2.png](https://github.com/geekerstar/high-concurrency/blob/master/img/block2.png)

- ArrayBlockingQueue
- DelayQueue
- LinkedBlockingQueue
- PriorityBlockingQueue
- SynchronousQueue

## 线程池
new Thread 弊端
- 每次 new Thread 新建对象，性能差
- 线程缺乏统一管理，可能无限制的新建线程，先过户竞争，有可能占用过多系统资源导致死机或OOM
- 缺少更多功能，如更多执行，定期执行，线程中断

线程池的好处
- 重用存在的线程，减少对象创建、消亡的开销，性能佳
- 可有效控制最大并发线程数，提高系的资源利用率，同时可以避免过多资源竞争，避免阻塞
- 提供定时执行。定期执行、单线程、并发数控制等功能

### 线程池 - ThreadPoolExecutor
- corePoolSize：核心线程数量
- maximumPoolSize：线程最大线程数
- workQueue：阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
- keepAliveTime：线程没有任务执行时最多保持多久时间终止
- unit：keepAliveTime的时间单位
- ThreadFactory：线程工厂，用来创建线程
- rejectHandler：当拒绝处理任务时的策略

![Thread](https://github.com/geekerstar/high-concurrency/blob/master/img/thread.png)

- execute()：提交任务，交给线程池执行
- submit()：提交任务，能够返回执行结果 execute+Future
- shutdown()：关闭线程池，等待任务都执行完
- shutdownNow()：关闭线程池，不等待任务执行完
- getTaskCount()：线程池已执行和未执行的任务总数
- getCompletedTaskCount()：已完成的任务数量
- getPoolSize()：线程池当前的线程数量
- getActiveCount()：当前线程池中正在执行任务的线程数量

![Thread](https://github.com/geekerstar/high-concurrency/blob/master/img/thread2.png)

### 线程池 - Executor 框架接口
- Executors.newCachedThreadPool
- Executors.newFixedThreadPool
- Executors.newScheduledThreadPool
- Executors.newSingleThreadExecutor

### 线程池 - 合理配置
- CPU密集型任务，就需要尽量压榨CPU，参考值可以设为NCPU+1
- IO密集型任务，参考值可以设置为2*NCPU
