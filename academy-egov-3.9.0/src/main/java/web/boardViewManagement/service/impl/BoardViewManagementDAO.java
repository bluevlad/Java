package web.boardViewManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class BoardViewManagementDAO extends EgovComAbstractDAO {

	/**
	 * @Method Name  : getCommonCodeList
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> getBoardMngList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("boardViewManagement.getBoardMngList", searchMap);
	}
	/**
	 * @Method Name  : getCommonCodeListCount
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getBoardMngListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("boardViewManagement.getBoardMngListCount", searchMap);
	}
	/**
	 * @Method Name  : commonCodeInsertProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 등록
	 * @param params
	*/
	public void boardMngInsertProcess(HashMap<String, Object> params) {
		getSqlSession().insert("boardViewManagement.boardMngInsertProcess", params);
	}
	/**
	 * @Method Name  : commonCodeDetail
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> boardMngDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("boardViewManagement.boardMngDetail", params);
	}
	/**
	 * @Method Name  : commonCodeUpdateProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 수정
	 * @param params
	*/
	public void boardMngUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("boardViewManagement.boardMngUpdateProcess", params);
	}
	/**
	 * @Method Name  : commonCodeDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 개별 삭제
	 * @param params
	*/
	public void boardMngDelete(HashMap<String, Object> params) {
		getSqlSession().delete("boardViewManagement.boardMngDelete", params);
	}
	/**
	 * @Method Name  : commonCodeCheckDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 일괄 삭제
	 * @param params
	*/
	public void boardMngCheckDelete(HashMap<String, Object> params) {
		getSqlSession().delete("boardViewManagement.boardMngCheckDelete", params);
	}
	public List<HashMap<String, String>>  getBoardTypeList() {
		return getSqlSession().selectList("boardViewManagement.getBoardTypeList");
	}

	public List<HashMap<String, String>> boardCSList(HashMap<String, String> params) {
		return getSqlSession().selectList("boardViewManagement.boardCSList", params);
	}
	
	public int boardCSListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardViewManagement.boardCSListCount", params);
	}

}
