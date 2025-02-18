package com.academy.locker;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.locker.service.LockerService;
import com.academy.locker.service.LockerVO;

@RestController
public class LockerApi extends CORSFilter {

    private LockerService lockerService;

    public LockerApi(LockerService lockerService) {
        this.lockerService = lockerService;
    }

	@GetMapping(value = "/api/getLockerList")
	public JSONObject list() throws Exception, IOException, ParseException { 
		
		JSONObject jsonObject = new JSONObject();

		LockerVO lockerVO = new LockerVO();

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		lockerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		lockerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
		jsonObject.put("hits", lockerService.selectLockerList(lockerVO));
		
		return jsonObject;
	}

	@GetMapping(value = "/api/getLocker")
	public JSONObject view() throws Exception, IOException, ParseException { 

		String box_cd = "101";
		JSONObject jsonObject = new JSONObject();
				
		LockerVO lockerVO = new LockerVO();
		lockerVO.setBoxCd(box_cd);
		
		jsonObject.put("item", lockerService.getLocker(lockerVO));

		return jsonObject;
	}

}