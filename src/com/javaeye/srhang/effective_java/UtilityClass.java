package com.javaeye.srhang.effective_java;

//不可被实例化的类
public class UtilityClass {
	//避免不小心在类的内部调用构造器
	private UtilityClass(){
		throw new AssertionError();
	}
}
