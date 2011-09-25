package com.example;

import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAppDocumentBuilder() throws Exception
    {
        assertEquals( Arrays.asList(new String[] { "me", "and", "the", "corner" }), Arrays.asList(new App().namesFromDocumentBuilder())  );
    }
    public void testAppNokogiri() throws Exception
    {
        assertEquals( Arrays.asList(new String[] { "me", "and", "the", "corner" }), Arrays.asList(new App().namesFromNokogiri())  );
    }
}
