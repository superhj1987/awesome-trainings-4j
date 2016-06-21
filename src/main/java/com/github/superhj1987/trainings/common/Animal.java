package com.github.superhj1987.trainings.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Animal {
	public void eat() {
		System.out.println("吃东西");
	}

	public static void a() {
		System.out.println("aa");
	}

	public static void main(String[] args) throws ParseException {
		/*
		 * Animal an = new Cat(); an.eat(); Animal.a();
		 * System.out.println(an.getClass()); Integer a = 1; Integer b = 2;
		 * Integer c = 3; Integer d = 3; Integer e = 321; Integer f = 321; Long
		 * g =3L; System.out.println(c==(a+b));
		 */
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2012-03-04");
	}
}

class Cat extends Animal {
	@Override
	public void eat() {
		System.out.println("吃鱼");
	}
}