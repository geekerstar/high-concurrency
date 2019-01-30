## 并发模拟

Postman：HTTP请求模拟工具

Apache Bench（AB）：Apache附带的工具，测试网站性能

JMeter：Apache组织开发的压力测试工具

### postman

![](https://github.com/geekerstar/high-concurrency/blob/master/img/post.jpg)

### 并发模拟 - CountDownLatch
![](https://github.com/geekerstar/high-concurrency/blob/master/img/cut.jpg)


# 多线程与并发
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

## 死锁 - 必要条件
- 互斥条件
- 请求和保持条件
- 不剥夺条件
- 环路等待条件

## 多线程并发最佳实践
- 使用本地变量
- 使用不可变类
- 最小化锁的作用域范围：S=1/(1-a+a/n)
- 使用线程池的Executor，而不是直接new Thread执行
- 宁可使用同步也不要使用线程的wait和notify
- 使用BlockingQueue实现生产-消费模式
- 使用并发集合而不是加了锁的同步集合
- 使用Semaphore创建有界的访问
- 宁可使用同步代码块，也不使用同步的方法
- 避免使用静态变量

## Spring与线程安全
- Spring bean：singleton、prototype
- 无状态对象

## HashMap与ConcurrentHashMap
### HashMap
![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap2.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap3.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap4.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap5.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/hashmap6.png)

### ConcurrentHashMap
![](https://github.com/geekerstar/high-concurrency/blob/master/img/chm.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/chm2.png)

## 多线程并发与线程安全总结
![](https://github.com/geekerstar/high-concurrency/blob/master/img/d.jpg)

----

# 高并发

## 扩容
- 垂直扩容（纵向扩展）：提高系统部件能力
- 水平扩容（横向扩展）：增加更多系统成员来实现

## 扩容 - 数据库
- 读操作扩展：memcache、Redis、CDN等缓存
- 写操作扩展：Cassandra、HBase等

## 缓存
![](https://github.com/geekerstar/high-concurrency/blob/master/img/huancun.png)


### 缓存特征
- 命中率：命中数/（命中数+没有命中数）
- 最大元素（空间）
- 清空策略：FIFO、LFU、LRU、过期时间、随机等

### 缓存命中率影响因素
- 业务场景和业务需求
- 缓存的设计（粒度和策略）
- 缓存容量和基础设施

### 缓存分类和应用场景
- 本地缓存：编程实现（成员变量、局部变量、静态变量）、Guava Cache
- 分布式缓存：Memcache、Redis

### 缓存 - Guava Cache

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache1.png)

### 缓存 - Memcache

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache2.jpg)

### Memcache 内存结构

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache3.png)

### 缓存 - Redis

![](https://github.com/geekerstar/high-concurrency/blob/master/img/cache4.png)

## 高并发场景下缓存常见问题
### 缓存一致性
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h1.png)

### 缓存并发问题
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h2.jpg)

### 缓存穿透问题
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h3.jpg)

### 缓存的雪崩现象
![](https://github.com/geekerstar/high-concurrency/blob/master/img/h4.jpg)


## 消息队列

![](https://github.com/geekerstar/high-concurrency/blob/master/img/m.png)

### 消息队列特性
- 业务无关：只做消息分发
- FIFO：先投递先到达
- 容灾：节点的动态增删和消息的持久化
- 性能：吞吐量提升，系统内部通信效率提高

### 为什么需要消息队列
- 【生产】和【消费】的速度或稳定性等因素不一致

### 消息队列的好处
- 业务解耦
- 最终一致性
- 广播
- 错峰与流控


### 消息队列-Kafka
![](https://github.com/geekerstar/high-concurrency/blob/master/img/kafka.jpg)


### 消息队列-RabbitMQ
![](https://github.com/geekerstar/high-concurrency/blob/master/img/rabbit.png)

## 应用拆分
![](https://github.com/geekerstar/high-concurrency/blob/master/img/y.png)


### 应用拆分 - 原则
- 业务优先
- 循序渐进
- 兼顾技术：重构、分层
- 可靠测试

### 应用拆分 - 思考
- 应用之间通信：RPC（dubbo等）、消息队列
- 应用之间数据库设计：每个应用都有独立的数据库
- 避免事务操作跨应用

### Dubbo

![](https://github.com/geekerstar/high-concurrency/blob/master/img/dubbo.png)


### 微服务

![](https://github.com/geekerstar/high-concurrency/blob/master/img/w.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/w2.png)


## 应用限流

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x.png)

### 应用限流 - 算法
#### 计数器法

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x2.png)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x3.png)

#### 滑动窗口

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x4.png)

#### 漏桶算法 (Leaky Bucket)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x5.png)

#### 令牌桶算法(Token Bucket)

![](https://github.com/geekerstar/high-concurrency/blob/master/img/x6.png)

## 服务降级与服务熔断
- 服务降级
- 服务熔断

### 服务降级分类
- 自动降级：超时、失败次数、故障、限流
- 人工降级：秒杀、双11大促

### 共性与区别
- 共性：目的、最终表现、粒度4、自治
- 区别：触发原因、管理目标层次、实现方式

### 服务降级要考虑的问题
- 核心服务、非核心服务
- 是否支持降级、降级策略
- 业务放通场景，策略

### Hystrix
- 在通过第三方客户端访问（通常是通过网络）依赖服务出现高延迟或者失败时，为系统提供保护和控制
- 在分布式系统中防止级联失败
- 快速失败（Fail fast）同时能快速回复
- 提供失败回退（Fallback)和优雅的服务降级机制


## 数据库切库、分库、分表
数据库瓶颈
- 单个库数据量太大（1T~2T）：多个库
- 单个数据库服务器压力过大、读写瓶颈：多个库
- 单个表数据量过大：分表

### 数据库切库
- 切库的基础及实际运用：读写分离
- 支持多数据源、分库

### 数据库分表
- 什么时候考虑分表
- 横向（水平）分表与纵向（垂直）分表
- 数据库分表：mybatis分表插件 shardbatis2.0

## 高可用的一些手段
- 任务调度系统分布式：elastic-job + zookeeper
- 主备切换：Apache curator + zookeeper 分布式锁实现
- 监控报警机制
