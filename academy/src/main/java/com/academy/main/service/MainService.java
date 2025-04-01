package com.academy.main.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.MainMapper;

@Service
public class MainService {

	private MainMapper mainMapper;
	
	public MainService(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}
	
	public ArrayList<JSONObject> selectRouterList(MenuVO menuVO) {
		return mainMapper.selectRouterList(menuVO);
	}
	
	public ArrayList<JSONObject> selectLeftMenuList(MenuVO menuVO) {
		return mainMapper.selectLeftMenuList(menuVO);
	}

}