package com.academy.main;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.main.service.MainService;
import com.academy.main.service.MenuVO;

@RestController
@RequestMapping("/api/main")
public class MainApi extends CORSFilter {

    private MainService mainService;

    public MainApi(MainService mainService) {
        this.mainService = mainService;
    }
	
	@GetMapping(value = "/getSales")
	public JSONObject reportsBar(Model model) throws Exception, IOException, ParseException { 

		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\jee-2024-12\\upload\\dashSalesData.json");
		
		JSONObject jsonObject = (JSONObject) parser.parse(reader);         

		return jsonObject;
	}
    
	/**
	 * router
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/getRouter")
	public JSONObject router(@ModelAttribute("MenuVO") MenuVO menuVO) throws Exception { 
				
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		menuVO.setMenuDepth(0);
		jsonObject.put("router", mainService.selectRouterList(menuVO));

		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}
    
	/**
	 * router
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/getLeftMenu")
	public JSONObject leftmenu(@ModelAttribute("MenuVO") MenuVO menuVO) throws Exception { 
	    List<JSONObject> buildMenu = buildMenuTree(0);
	    JSONObject response = new JSONObject();
	    response.put("items", buildMenu);
	    return response;
	}

	private List<JSONObject> buildMenuTree(int parentId) {
	    List<JSONObject> menuTree = new ArrayList<>();
	    MenuVO menuVO = new MenuVO();
	    menuVO.setMenuUpperId(parentId);

	    List<JSONObject> menuItems = mainService.selectLeftMenuList(menuVO);

	    for (JSONObject item : menuItems) {
	        int menuId = CommonUtil.parseInt(item.get("menu_no"));
	        List<JSONObject> children = buildMenuTree(menuId);
	        
	        if (!children.isEmpty()) {
	            item.put("children", children);
	        }
	        
	        menuTree.add(item);
	    }
	    return menuTree;
	}
	
}