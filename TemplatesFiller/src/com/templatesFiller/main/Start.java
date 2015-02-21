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
		String test = "@test@ so @test2@ odod";
		HashMap<String,String> mapOfTaggedValuesLists = new HashMap<String,String>();
		ArrayList<String> listOfTaggedValuesInCurrentLine = getTaggedValuesFromCurrentLine(test, "@");
		//Map<String,String> keysWithValues = new HashMap<String,String>();
		//String filledTemplteFile = fillMarkedValuesWithProperStrings("","",keysWithValues);
	}
	
	private static String fillMarkedValuesWithProperStrings(String templateContent, String markupSign, Map<String,String>keysWithValues)
	{
		Map<String,String> markedKeysMap = new HashMap<String,String>();
		String templateFileContent= loadFileFromDisk("pathToFile");
		BufferedReader bufReader = new BufferedReader(new StringReader(templateFileContent));
		
		String line=null;
		try 
		{
			while( (line=bufReader.readLine()) != null )
			{
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//Iterate trough every line of String getting marked values with Markup sign
		//Add markedValues to map 'markedyKeysMap'
		//Loop - iterate trough marked keys map, if markedKeysMap contains key same as keys map - replace in 'templateContent'
		//markedValue with walue from key	sWithValues map

		return "";
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
