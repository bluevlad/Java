package web.boardViewManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : AdminManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03 
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 service
 */
public interface BoardViewManagementService {
	
	/**
	 * @Method Name  : getCommonCodeList
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 	운영자 관리 -  코드관리  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> getBoardMngList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : getCommonCodeListCount
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getBoardMngListCount(Map<String, Object> searchMap);
	
	/**
	 * @Method Name  : commonCodeInsertProcess
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  - 등록 프로세스
	 * @param params
	*/
	void boardMngInsertProcess(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeDetail
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리 - 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> boardMngDetail(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeUpdateProcess
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  - 수정 프로세스
	 * @param params
	*/
	void boardMngUpdateProcess(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeDelete
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  개별 삭제
	 * @param params
	*/
	void boardMngDelete(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeCheckDelete
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  체크박스 일괄 삭제
	 * @param params
	*/
	void boardMngCheckDelete(HashMap<String, Object> params);
	
	
	List<HashMap<String, String>> getBoardTypeList();

	List<HashMap<String, String>> boardCSList(HashMap<String, String> params);

	int boardCSListCount(HashMap<String, String> params);
	
}
