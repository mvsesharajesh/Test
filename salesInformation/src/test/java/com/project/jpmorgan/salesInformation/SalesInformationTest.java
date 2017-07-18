package com.project.jpmorgan.salesInformation;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SalesInformationTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SalesInformationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SalesInformationTest.class );
    }

    public void testReadMessagesFromFileForNull()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	File file = salesInformation.readFile(null);
    	assertEquals(file, null);
    }
    
    public void testReadMessagesFromFileThatDoesNotExist()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	File file = salesInformation.readFile("ABC.txt");
    	assertEquals(file, null);
    }
    
    public void testProcessSalesInformationFromFileForNull()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	salesInformation.initializeRequiredDataElements();
    	assertFalse(salesInformation.processSalesInformationFromFile(null));
    }
    
    public void testProcessSalesInformationFromFileForException()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	salesInformation.initializeRequiredDataElements();
    	assertFalse(salesInformation.processSalesInformationFromFile("SalesMessages.txt"));
    }
    
    public void testProcessSalesInformationFromFileForHappyFlow()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	salesInformation.initializeRequiredDataElements();
    	assertTrue(salesInformation.processSalesInformationFromFile("SalesMessages.txt"));
    }
    
    public void testProcessSalesInformationFromFileForHappyForMultipleProducts()
    {
    	SalesInformation salesInformation = new SalesInformation();
    	salesInformation.initializeRequiredDataElements();
    	assertTrue(salesInformation.processSalesInformationFromFile("SecondSalesMessage.txt"));
    }
}
