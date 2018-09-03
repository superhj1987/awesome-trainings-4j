package me.rowkey.trainings.bytecode;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Bryant.Hang on 16/9/7.
 */
public class CglibExample {
    public static void main(String rags[]) {
        Target target = new Target();
        CglibExample test = new CglibExample();
        Target proxyTarget = (Target) test.createProxy(Target.class);
        String res = proxyTarget.execute();
        System.out.println(res);
    }

    public Object createProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MyMethodInterceptor());
        return enhancer.create();
    }
}

class Target {
    public String execute() {
        String message = "----------test()----------";
        System.out.println(message);
        return message;
    }
}

class MyMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>MethodInterceptor start...");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println(">>>MethodInterceptor ending...");
        return "hahahh";
    }
}


