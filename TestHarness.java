package week04;

import test.TestEngine;

/**
 * File: TestHarness.java
 */
class TestHarness
{
    public static void main(String[] args)
    {
    	trace("Starting test...");

		trace(" -- setup test data");
    	TestEngine engine = new TestEngine();
    	engine.addTest(new TestApp());
    	engine.addTest(new TestLogging());

    	engine.runTests();

    	trace("Completed test");
    }

	static private void trace(String msg)
	{
		System.out.println(msg);
	}
}