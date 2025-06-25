package com.academy.dashboard.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.locker.service.LockerVO;
import com.academy.mapper.DashBoardMapper;

@Service
public class DashBoardService {

	private DashBoardMapper dashBoardMapper;

	public DashBoardService(DashBoardMapper dashBoardMapper) {
        this.dashBoardMapper = dashBoardMapper;
    }
	
	public JSONObject myRentLocker(LockerVO lockerVO) {
		return dashBoardMapper.myRentLocker(lockerVO);
	}
	
}