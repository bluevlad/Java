package com.academy.locker;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.Configurations;
import com.academy.locker.service.LockerService;
import com.academy.locker.service.LockerVO;
import com.academy.mapper.LockerMapper;

@RestController
public class getLockerApi extends CORSFilter {

    private LockerService lockerService;

    public getLockerApi(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    private Connection conn;
	private Configurations config;
	private LockerMapper lockerMapper;
	
	@GetMapping(value = "/api/getLockerList")
	public JSONObject list() throws Exception, IOException, ParseException { 
		
		config = new Configurations();
		ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
		JSONObject jsonObject = new JSONObject();

		LockerVO lockerVO = new LockerVO();
				
//		arrayJson = lockerService.selectBoxList(lockerVO);
					
		jsonObject.put("hits", lockerService.selectLockerList(lockerVO));
		
		return jsonObject;
	}

	/*
	@GetMapping(value = "/api/getBox")
	public JSONObject viewx() throws IOException, ParseException { 

		String box_cd = "101";
		config = new Configurations();
		config.getConfig();
		JSONObject objJson = new JSONObject();
				
		BoxService dao = new BoxService();
		
		try {
					
			conn = DBUtil.getConnection(config);
			objJson = dao.getBox(conn, box_cd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
		
		return objJson;
	}
*/
}