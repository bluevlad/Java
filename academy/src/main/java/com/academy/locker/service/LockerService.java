package com.academy.locker.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.LockerMapper;

@Service
public class LockerService {

	private LockerMapper lockerMapper;
	
	public LockerService(LockerMapper lockerMapper) {
		this.lockerMapper = lockerMapper;
	}
	
	public ArrayList<JSONObject> selectLockerList(LockerVO lockerVO) {
		return lockerMapper.selectLockerList(lockerVO);
	}
	
	public JSONObject getLocker(LockerVO lockerVO) {
		return lockerMapper.getLocker(lockerVO);
	}

}