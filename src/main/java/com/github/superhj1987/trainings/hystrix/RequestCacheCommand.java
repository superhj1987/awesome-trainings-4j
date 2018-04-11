package com.github.superhj1987.trainings.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Assert;

/**
 * 请求缓存 Request-Cache
 * <p>
 * 请求缓存可以让(CommandKey/CommandGroup)相同的情况下,直接共享结果，降低依赖调用次数，在高并发和CacheKey碰撞率高场景下可以提升性能.
 * Servlet容器中，可以直接实用Filter机制Hystrix请求上下文
 * <p>
 * Author: Bryant.Hang
 * Date: 2018/4/9
 * Email: superhj1987@126.com
 */
public class RequestCacheCommand extends HystrixCommand<String> {
    private final int id;

    public RequestCacheCommand(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommandGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("RequestCacheCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));

        this.id = id;
    }

    @Override
    protected String run() {
        System.out.println(Thread.currentThread().getName() + " execute id=" + id);
        return "executed=" + id;
    }

    //重写getCacheKey方法,实现区分不同请求的逻辑
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command2a = new RequestCacheCommand(2);
            RequestCacheCommand command2b = new RequestCacheCommand(2);
            System.out.println(command2a.execute());
            //isResponseFromCache判定是否是在缓存中获取结果
            Assert.assertFalse(command2a.isResponseFromCache());
            System.out.println(command2b.execute());
            Assert.assertTrue(command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
        context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command3b = new RequestCacheCommand(2);
            System.out.println(command3b.execute());
            Assert.assertFalse(command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }
}