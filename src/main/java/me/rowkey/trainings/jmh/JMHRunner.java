package me.rowkey.trainings.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Bryant.Hang on 16/10/30.
 * <p>
 * 指定好jmh的测试类,运行此main方法即可运行jmh基准性能测试,也可以使用intellij的jmh插件运行
 */
public class JMHRunner {
    public static final String jmhTestClassName = "";

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + jmhTestClassName + ".*")
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
