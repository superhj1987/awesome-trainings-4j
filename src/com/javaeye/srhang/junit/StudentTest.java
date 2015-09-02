package srhang.junit;

import junit.framework.TestCase;

public class StudentTest extends TestCase {
	public void testCreate(){
		Student student = new Student("Kobe Bryant");
		String name = student.getName();
		assertEquals("Kobe Bryant",name);
	}

}
