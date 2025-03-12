package com.academy.apitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
	
@RestController
public class Main extends CORSFilter {

	@GetMapping(value = "/api/reportsBarChartData")
	public JSONObject reportsBar(Model model) throws Exception, IOException, ParseException { 

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\jee-2024-12\\upload\\reportsBarChartData.json");
		
		JSONObject jsonObject = (JSONObject) parser.parse(reader);         

		return jsonObject;
	}

	@GetMapping(value = "/api/reportsLineChartData")
	public JSONObject reportsLine(Model model) throws Exception, IOException, ParseException { 

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\jee-2024-12\\upload\\reportsLineChartData.json");
		
		JSONObject jsonObject = (JSONObject) parser.parse(reader);         

		return jsonObject;
	}

}

