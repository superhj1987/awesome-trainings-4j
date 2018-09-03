package me.rowkey.trainings.hystrix;

/**
 * Created by Bryant.Hang on 16/12/2.
 */

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by Bryant.Hang on 16/12/1.
 */
public class HystrixTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new CommandHelloWorld("").toObservable().subscribe(s -> {
            countDownLatch.countDown();
            System.out.println("aaa");
        });


        System.out.println("bbb");
        countDownLatch.await();
    }
}

class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), 20000);
        this.name = name;
    }

    @Override
    protected String run() throws IOException, InterruptedException {
        Thread.sleep(10000);

        return this.name;
    }

    @Override
    protected String getFallback() {
        return "failure";
    }
}
