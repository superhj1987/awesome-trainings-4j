package com.github.superhj1987.trainings.hystrix;

/**
 * Created by Bryant.Hang on 16/12/2.
 */

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Bryant.Hang on 16/12/1.
 */
public class HystrixTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(new CommandHelloWorld("World").queue().get());
    }
}

class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), 1000);
        this.name = name;
    }

    @Override
    protected String run() throws IOException {
        return this.name;
    }

    @Override
    protected String getFallback() {
        return "failure";
    }
}
