package com.embl.exercise;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by selva on 07/07/2017.
 */
public class AccessionNumber {
	
	private static Logger logger = Logger.getLogger("AccessionNumber");
	
	public static void main(String args[])
	{
		String formattedAccNumList;
		
		if(args.length > 0)
			formattedAccNumList = formatAccessionNumber(args[0]);
		else
			formattedAccNumList =formatAccessionNumber("");
		
		if(formattedAccNumList!="")
		    System.out.println("OutputList :"+formattedAccNumList);
	}

    public static String formatAccessionNumber(String inputList)
    {

        if(StringUtils.isEmpty(inputList))
        {
        	logger.info("Please input a list of Accession Numbers in the format L..LN..N,L..LN..N(eg:ABCD1234,ABCD1235)");
        	return "";
        }
        
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(inputList.split(","));
        
        //Pattern Matching matches String Starting with one or more A-Z Chars and ending with one or more 0-9 Digits
        Pattern patt = Pattern.compile("^([A-Z]+)(\\d+)$");
        
        //Check whether all the Numbers in the list is valid
        if(streamSupplier.get().anyMatch(accNum -> !patt.matcher(accNum).find()))
        {
        	logger.info("Please input a valid list of Accession Numbers in the format..\n L..LN..N,L..LN..N(eg:ABCD1234,ABCD1235)");
        	return "";
        }
        
        List<String> strList = new ArrayList<String>();
        
        					                 //Compare by extracting L..L Split by "\\d" - digits AB123.split("\\d")[0] return AB
        Comparator<String> compByChars    = (aAccNum, bAccNum) -> aAccNum.split("\\d")[0].compareTo(bAccNum.split("\\d")[0]);
        									
        									 //Compare by extracting N..N AB12.replaceAll("[A-Z]","") returns 12
        Comparator<String> compByNumbers  = (aAccNum, bAccNum) -> Integer.valueOf(Integer.parseInt("1" + aAccNum.replaceAll("[A-Z]","")))
        															.compareTo(Integer.parseInt("1" + bAccNum.replaceAll("[A-Z]","")));

        //Sorting By Natural order gives ABC123,ABC1235,ABC124-So sorting by L..L and then N..N gives ABC123,ABC124,ABC1235(Just to check range as per my logic)
        streamSupplier.get().distinct().sorted(compByChars.thenComparing(compByNumbers)).forEach( a ->
        {
        	String preAccNumber="";
        	String strtOfRange="";        	
        	
        	if(strList.size() > 0)
        	{
        		preAccNumber = strList.get(strList.size()-1);                     //Get Previous AccessionNumber
        	}
        	
        	//Check whether Previous AccessionNumber a range
        	if(preAccNumber.split("-").length > 1)
        	{
        		strtOfRange = preAccNumber.split("-")[0];
        		preAccNumber = preAccNumber.split("-")[1];
        	}
        
        	Matcher m = patt.matcher(preAccNumber);
        	Matcher n = patt.matcher(a);
        	
        	
        	if( m.find() && n.find() && 	
        		  m.group(1).equals(n.group(1)) && 						            //m.group(1) returns L..L (A-Z Characters)
        		  m.group(2).length()==n.group(2).length() &&						//m.group(2) returns N..N (0-9 Digits)
        		   Integer.parseInt(m.group(2))+1 == Integer.parseInt(n.group(2)))  //ParseNumbers and find whether its consecutive
        	{
        		if(strtOfRange!="")
        			strList.set(strList.size()-1,strtOfRange+"-"+a);
        		else
        			strList.set(strList.size()-1,preAccNumber+"-"+a);
        	}
        	else
        	{
        		strList.add(a);
        	}
        	
        });
        
        //Finally return Naturally Ordered Accession Number List
        return strList.stream().sorted().collect(Collectors.joining(","));
    }
}