package me.rowkey.trainings.netty;

import me.rowkey.trainings.netty.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Author: Bryant
 * Date: 14/11/5
 * Time: 下午11:33
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting application context");
        @SuppressWarnings("resource")
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
                SpringConfig.class);
        ctx.registerShutdownHook();
    }

}
