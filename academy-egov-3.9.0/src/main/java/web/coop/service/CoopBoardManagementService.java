package web.coop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : CoopBoardManagementService.java
 * @Project    : willbesWebAdmin
 * @Date       : 2016. 02. 01. 
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 제휴사 게시판 관리 service
 */
public interface CoopBoardManagementService {
	
	List<HashMap<String, Object>> getCoopboardList(Map<String, Object> params);

	int getCoopboardListCount(Map<String, Object> params);
	
	HashMap<String, Object> CoopboardView(HashMap<String, Object> params);

	void insertCoopboard(HashMap<String, Object> params);

	void updateCoopboard(HashMap<String, Object> params);

	void deleteCoopboard(HashMap<String, Object> params);

	List<HashMap<String, Object>> getCoopCodeList(Map<String, Object> params);
	
	void CoopboardDeleteFile(HashMap<String, Object> params);

	void updateCoopRank(HashMap<String, Object> params);
}