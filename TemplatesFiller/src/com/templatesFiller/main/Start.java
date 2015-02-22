package com.templatesFiller.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Start {

	public static void main(String[] args) 
	{
		String test = "@test@ so @test2@ odod@test3@";
		Map<String,String> mapWithTaggedKeysAndValues = new HashMap<String,String>();
			mapWithTaggedKeysAndValues.put("test","ZAWARTOSC1");
			mapWithTaggedKeysAndValues.put("test2","ZAWARTOSC2");
			mapWithTaggedKeysAndValues.put("test3","ZAWARTOSC3");
			
		//------------------------------------------------------------------------
		String filledHtmlTemplate=fillMarkedValuesWithProperStrings(test, "@",mapWithTaggedKeysAndValues);
		//-----------------------------------------------------------------------
	}
	
	private static String fillMarkedValuesWithProperStrings(String templateContent, String markupSign, Map<String,String> mapWithTaggedKeysAndValues)
	{
		ArrayList<String> markedKeysList = new ArrayList<String>();
		String templateFileContent= loadFileFromDisk("pathToFile");
		
		BufferedReader bufReader = new BufferedReader(new StringReader(templateContent));
		
		String line=null;
		try 
		{
			while( (line=bufReader.readLine()) != null )
			{
				markedKeysList.addAll((getTaggedValuesFromCurrentLine(line,markupSign)));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("test");
		for (int i=0; i<markedKeysList.size(); i++)
		{
			String currentKey = markedKeysList.get(i);
			if(mapWithTaggedKeysAndValues.containsKey(currentKey))
			{
				templateContent=templateContent.replace(markupSign+currentKey+markupSign, mapWithTaggedKeysAndValues.get(currentKey));
			}
			
		}
		return templateContent;
	}
	
	private static ArrayList<String> getTaggedValuesFromCurrentLine(String lineToAnalyze, String tag)
	{
		//dodawanie elementow do mapToFill
		ArrayList<String> listOfTaggedValues = new ArrayList<String>();
		boolean tagDetected=false;
		boolean endOfTagDetected;;
		char[] lineToAnalyzeCharacterArray = lineToAnalyze.toCharArray();
		for(int i=0; i<lineToAnalyzeCharacterArray.length; i++)
		{
			endOfTagDetected=false;
			char currentChar = lineToAnalyzeCharacterArray[i];
			if(String.valueOf(currentChar).equals(tag))
			{
				StringBuilder valueBetweenTags = new StringBuilder();
				//aktualnie analizowany znak jest tagiem, rozpoczynamy pobieranie zawartosci taga od nastepnego elementu)
				while(endOfTagDetected==false)
				{
					i=i+1;
					char currentCharBetweenTags = lineToAnalyzeCharacterArray[i];
					if(String.valueOf(currentCharBetweenTags).equals(tag)!=true)
					{
						valueBetweenTags.append(currentCharBetweenTags);
					}
					else
					{
						endOfTagDetected=true;
					}
				}
				listOfTaggedValues.add( valueBetweenTags.toString());
			}
		}
		
		return listOfTaggedValues;
	}
	
	private static String loadFileFromDisk(String path)
	{
		String templateFileContent="";
		TXTFileLoader txtFileLoader = new TXTFileLoader();
		try 
		{
			templateFileContent=txtFileLoader.loadTXTFileFromDisc(path);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return templateFileContent;
	}

}
