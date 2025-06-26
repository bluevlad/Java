package com.academy.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.dashboard.service.DashBoardService;
import com.academy.locker.service.LockerVO;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardApi extends CORSFilter {

    private DashBoardService dashBoardService;

    public DashBoardApi(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }
    
	/**
	 * router
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/myRentLocker")
	public JSONObject myRentLocker(@ModelAttribute("LockerVO") LockerVO lockerVO, 
			@RequestParam Map<?, ?> commandMap) throws Exception { 
				
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		String userId = String.valueOf(commandMap.get("userId"));
		lockerVO.setUserId(userId);
		
		jsonObject.put("myRentLocker", dashBoardService.myRentLocker(lockerVO));

		return new JSONObject(jsonObject);
	}
    
}