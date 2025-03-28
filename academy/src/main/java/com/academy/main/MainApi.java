package com.academy.main;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.main.service.MainService;
import com.academy.main.service.MenuVO;

@RestController
public class MainApi extends CORSFilter {

    private MainService mainService;

    public MainApi(MainService mainService) {
        this.mainService = mainService;
    }
    
	/**
	 * 사물함 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getRouter")
	public JSONObject router(@ModelAttribute("MenuVO") MenuVO menuVO) throws Exception { 
				
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		jsonObject.put("router", mainService.selectRouterList(menuVO));

		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}
}