package com.embl.exercise;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by selva on 07/07/2017.
 */
public class AccessionNumberTest {

    @Test
    public void testEmptyAccessionNumberList()
    {
        String inputList = "";
        
        Assert.assertEquals("",AccessionNumber.formatAccessionNumber(inputList));
    }
    
    @Test
    public void testInvalidAccessionNumberList()
    {
        String inputList = "ABC123,AB";
        
        Assert.assertEquals("",AccessionNumber.formatAccessionNumber(inputList));
        
        inputList = "123";
        
        Assert.assertEquals("",AccessionNumber.formatAccessionNumber(inputList));
    }
    
    @Test
    public void testSortedAccessionNumberList()
    {
        String inputList = "DR123,DR0123,ABC123,CBA345,BBA1,ABC0123";
        String outputList = "ABC0123,ABC123,BBA1,CBA345,DR0123,DR123";
        
        Assert.assertNotEquals("",AccessionNumber.formatAccessionNumber(inputList));
        Assert.assertEquals(outputList,AccessionNumber.formatAccessionNumber(inputList));
    }
    
    @Test
    public void testRangeAccessionNumberList()
    {
        String inputList = "ABC123,ABC1235,ABC124,ABC125";
        String outputList = "ABC123-ABC125,ABC1235";
        
        Assert.assertNotEquals("",AccessionNumber.formatAccessionNumber(inputList));
        Assert.assertEquals(outputList,AccessionNumber.formatAccessionNumber(inputList));
    }
    
    @Test
    public void testNotInRangeAccessionNumberList()
    {
        String inputList = "ABC123,ABC0125,BBA999,BBA1000";
        String outputList = "ABC0125,ABC123,BBA1000,BBA999";
        
        Assert.assertNotEquals("",AccessionNumber.formatAccessionNumber(inputList));
        Assert.assertEquals(outputList,AccessionNumber.formatAccessionNumber(inputList));
    }
    
    @Test
    public void testAllValidAccessionNumberList()
    {
        String inputList = "DJ235,BBA0123,DJ234,BBA456,ABC123,ABC0124,ABC0125,ABC0126,ABC0128";
        String outputList = "ABC0124-ABC0126,ABC0128,ABC123,BBA0123,BBA456,DJ234-DJ235";
        
        Assert.assertNotEquals("",AccessionNumber.formatAccessionNumber(inputList));
        Assert.assertEquals(outputList,AccessionNumber.formatAccessionNumber(inputList));
    }
    
    
    
  
    
}
