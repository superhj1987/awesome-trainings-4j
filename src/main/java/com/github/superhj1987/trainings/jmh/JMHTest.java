package com.github.superhj1987.trainings.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Bryant.Hang on 16/10/30.
 */
@BenchmarkMode(Mode.SampleTime)
@Warmup(iterations = 5)
@State(Scope.Benchmark)
@Measurement(iterations = 2)
public class JMHTest {
    private String[] str;

    @Setup
    public void setup(){
        str = new String[]{"1", "b", "v"};
    }

    @Benchmark
    public void testSb() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (String s : str) {
            sb.append(s);
        }
    }

    @Benchmark
    public void testSb1() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*JMHTest.*")
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
