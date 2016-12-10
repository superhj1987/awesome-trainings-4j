package com.github.superhj1987.trainings.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;
import static sun.plugin.javascript.navig.JSType.Location;

import com.sun.btrace.AnyType;

import java.lang.reflect.Field;


/**
 * Created by Bryant.Hang on 16/12/10.
 */
@BTrace
public class BtraceExample {
    private static Field fdField = field("java.io.FileInputStream", "fd");
    private static Field noJdkClassField = field(classForName("me.rowkey.TestClass",contextClassLoader()), "title");

    @OnMethod(clazz = "xx.xx.xxx.xx", method = "xxx", location = @Location(Kind.RETURN))
    public static void test(@Self Object self, int a, int b, @Return AnyType result) {
        println(a + " | " + b);

        println(concat("finalizing ", str(self)));

        printFields(self)
        printFields(get(fdField, self));
        printFields(get(noJdkClassField, self));
        println(result)

        jstack();
    }
}
