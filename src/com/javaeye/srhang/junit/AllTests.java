package srhang.junit;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for srhang.junit");
		//$JUnit-BEGIN$
		suite.addTestSuite(StudentTest.class);
		suite.addTestSuite(HelloTest.class);
		//$JUnit-END$
		return suite;
	}

}
