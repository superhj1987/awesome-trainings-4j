package com.github.superhj1987.trainings.effective_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generic {
	private int size = 10;
	
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings,new Integer(42));
		String s = strings.get(0);
	}

	private static void unsafeAdd(List list,Object o){
		list.add(o);
	}
	
	public <T> T[] toArray(T[] a){
		Object elements[] = new Object[size];
		if(a.length < size){
			@SuppressWarnings("unchecked") T[] result = (T[])Arrays.copyOf(elements,size,a.getClass());
			return result;
		}
		
		System.arraycopy(elements, 0, a, 0, size);
		if(a.length > size)
			a[size] = null;
		return a;
	}
}
