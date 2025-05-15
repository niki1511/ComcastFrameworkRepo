package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		
		FileReader fileR= new FileReader("./configAppData/appCommonData.json"); //java object of physical file
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fileR);
		JSONObject map=(JSONObject)obj;  //downcasting to json object
		String data= (String) map.get(key);
		
		return data;
	}

}
