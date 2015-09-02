package com.github.superhj1987.effective_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test{
	Class<? extends Exception> value();
}

public class AnnotationsSample{
	@Test(ArithmeticException.class) public static void m1(){}
	public static void m2(){}
	@Test(ArithmeticException.class) public static void m3(){
		throw new RuntimeException("Boom");
	}
	public static void m4(){}
	@Test(ArithmeticException.class) public void m5(){}
	public static void m7(){
		throw new RuntimeException("Crash");
	}
	public static void m8(){}
	
	public static void main(String args[]) throws Exception{
		int tests = 0;
		int passed = 0;
		Class testClass = Class.forName("com.javaeye.srhang.effective_java.AnnotationsSample");
		for(Method m : testClass.getDeclaredMethods()){
			if(m.isAnnotationPresent(Test.class)){
				tests++;
				try{
					m.invoke(null);
					passed++;
				}catch(InvocationTargetException wrappedExc){
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + " failed: " + exc );
				}catch(Exception exc){
					System.out.println("INVALID @Test: " + m);
				}
			}
		}
		System.out.printf("Passed:%d,Failed:%d%n", passed,tests - passed);
	}
}
