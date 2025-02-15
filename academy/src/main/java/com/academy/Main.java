package com.academy;

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
public class Main {

	@GetMapping(value = "/api/data")
	public JSONObject index(Model model) throws IOException, ParseException { 

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\jee-2024-12\\upload\\data.json");
		JSONObject jsonObject = (JSONObject) parser.parse(reader);         

		return jsonObject;
	}

}

