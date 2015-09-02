package srhang.junit;

import junit.framework.TestCase;

public class HelloTest extends TestCase {
	Hello hello;
	
	protected void setUp() throws Exception {
		super.setUp();
		hello = new Hello();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAbs() {
		assertEquals(hello.abs(4), 4);
		assertEquals(hello.abs(-5), 5);
	}
	
	public void testAb() {
		assertEquals(hello.abs(3), 3);
	}

}
