package web.memberAdminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class MemberAdminManagementDAO extends EgovComAbstractDAO {


	/**
	 * @Method Name  : getMemberList
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자조회 - 운영자 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> getMemberList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("memberAdminManagement.getMemberList", searchMap);
	}
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getMemberListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("memberAdminManagement.getMemberListCount", searchMap);
	}

	/**
	 * @Method Name  : getSiteList
	 * @Date         : 2013. 11. 1.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명    : 권한 리스트
	 * @return
	*/
	public List<HashMap<String, String>> getSiteList() {
		return getSqlSession().selectList("memberAdminManagement.getSiteList");
	}

	/**
	 * @Method Name  : memberIdCheck
	 * @Date         : 2013. 11. 1.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 아이디 중복 체크
	 * @param params
	 * @return
	*/
	public int memberIdCheck(HashMap<String, String> params) {
		return getSqlSession().selectOne("memberAdminManagement.memberIdCheck", params);
	}

	/**
	 * @Method Name  : memberInsertProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 관리 - 관리자조회 - 관리자 등록
	 * @param params
	*/
	public void memberInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("memberAdminManagement.memberInsertProcess", params);
	}
	public void memberInfoInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("memberAdminManagement.memberInfoInsertProcess", params);
	}

	/**
	 * @Method Name  : memberDetail
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  운영자 조회 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> memberDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("memberAdminManagement.memberDetail", params);
	}
	/**
	 * @Method Name  : memberUpdateProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자 조회 수정
	 * @param params
	*/
	public void memberUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("memberAdminManagement.memberUpdateProcess", params);
	}
	/**
	 * @Method Name  : memberDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자 조회 개별 삭제
	 * @param params
	*/
	public void memberDelete(HashMap<String, String> params) {
		getSqlSession().delete("memberAdminManagement.memberDelete", params);
	}

	/**
	 * @Method Name  : memberAdminSendMessage
	 * @Date         : 2013. 11. 1.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 쪽지 전송
	 * @param params
	*/
	public void memberAdminSendMessage(HashMap<String, String> params) {
		getSqlSession().insert("memberAdminManagement.memberAdminSendMessage", params);
	}

	/**
	 * @Method Name  : getMemberAdminEmail
	 * @Date         : 2013. 11. 4.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 메일 전송
	 * @param params
	 * @return
	*/
	public HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params){
		return getSqlSession().selectOne("memberAdminManagement.getMemberAdminEmail", params);
	}
	/**
	 * @Method Name  : MemberAdminInsertEmail
	 * @Date         : 2013. 11. 4.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 메일 전송 내역 저장
	 * @param params
	*/
	public void MemberAdminInsertEmail(HashMap<String, String> params) {
		getSqlSession().insert("memberAdminManagement.MemberAdminInsertEmail", params);
	}

	/**
	 * @Method Name  : getAdminLoginList
	 * @Date         : 2016. 10. 04.
	 * @Method 설명      :	운영자 관리 - 운영자 로그인 정보 조회
	*/
	public List<HashMap<String, Object>> getAdminLoginList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("memberAdminManagement.getAdminLoginList", searchMap);
	}
	
	public int getAdminLoginListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("memberAdminManagement.getAdminLoginListCount", searchMap);
	}

}
