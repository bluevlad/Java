package com.academy.apitest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.board.BoardApi;
import com.academy.common.CORSFilter;
	
@RestController
public class Main extends CORSFilter {

    /** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
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
	
	 @PostMapping("/api/reqBoay")
	 public void reqBoay(@RequestBody Map<String, Object> map) {

		 LOGGER.info("@RequestBody = " + map);
	}	
		
	 @PostMapping("/api/reqParam")
	 public void reqParam(@RequestParam Map<?, ?> map) {

		 LOGGER.info("@RequestParam = " + map);
	}	

}

