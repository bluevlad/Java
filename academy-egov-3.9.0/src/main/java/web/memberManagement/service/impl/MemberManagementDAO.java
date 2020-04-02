package web.memberManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class MemberManagementDAO extends EgovComAbstractDAO {


	/**
	 * @Method Name  : getMemberList
	 * @Date         : 2013. 10.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> getMemberList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("memberManagement.getMemberList", searchMap);
	}
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getMemberListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("memberManagement.getMemberListCount", searchMap);
	}

	/**
	 * @Method Name  : getOldMemberList
	 * @Date         : 2016. 06.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	이관 회원 조회 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> getOldMemberList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("memberManagement.getOldMemberList", searchMap);
	}
	/**
	 * @Method Name  : getOldMemberListCount
	 * @Date         : 2016. 06.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	이관 회원 조회 리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getOldMemberListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("memberManagement.getOldMemberListCount", searchMap);
	}

	/**
	 * @Method Name  : getCategoryList
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 등록 - 관심분야 리스트
	 * @return
	*/
	public List<HashMap<String, String>> getCategoryList() {
		return getSqlSession().selectList("memberManagement.getCategoryList");
	}

	/**
	 * @Method Name  : memberIdCheck
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 회원 ID 중복 체크
	 * @param params
	 * @return
	*/
	public int memberIdCheck(HashMap<String, String> params) {
		return getSqlSession().selectOne("memberManagement.memberIdCheck", params);
	}


	/**
	 * @Method Name  : memberInsertProcess
	 * @Date         : 2013. 10.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 회원 등록
	 * @param params
	*/
	public void memberInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("memberManagement.memberInsertProcess", params);
	}
	public void memberInfoInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("memberManagement.memberInfoInsertProcess", params);
	}

	/**
	 * @Method Name  : memberCategoryDelete
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 관심분야 삭제
	 * @param params
	*/
	public void memberCategoryDelete(HashMap<String, String> params) {
		getSqlSession().delete("memberManagement.memberCategoryDelete", params);
	}

	/**
	 * @Method Name  : memberCategoryInsert
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 관심분야 저장
	 * @param params
	*/
	public void memberCategoryInsert(HashMap<String, String> params) {
		getSqlSession().insert("memberManagement.memberCategoryInsert", params);
	}

	/**
	 * @Method Name  : memberDetail
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 관리 -  - 회원조회 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> memberDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("memberManagement.memberDetail", params);
	}
	/**
	 * @Method Name  : oldMemberDetail
	 * @Date         : 2016. 06.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 관리 - 이관 회원조회 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> oldMemberDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("memberManagement.oldMemberDetail", params);
	}

	/**
	 * @Method Name  : memberUpdateProcess
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 - 회원 상세- 수정
	 * @param params
	*/
	public void memberUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("memberManagement.memberUpdateProcess", params);
	}
	public void memberInfoUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("memberManagement.memberInfoUpdateProcess", params);
	}

	/**
	 * @Method Name  : memberDelete
	 * @Date         : 2013. 10.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 - 회원 상세- 삭제
	 * @param params
	*/
	public void memberDelete(HashMap<String, String> params) {
		getSqlSession().delete("memberManagement.memberDelete", params);
	}
	public void memberInfoDelete(HashMap<String, String> params) {
		getSqlSession().delete("memberManagement.memberInfoDelete", params);
	}

	/**
	 * @Method Name  : searchZipCode
	 * @Date         : 2013. 11. 4.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 우편번호 검색
	 * @param params
	 * @return
	*/
	public List<HashMap<String, String>> searchZipCode(HashMap<String, String> params) {
		return getSqlSession().selectList("memberManagement.searchZipCode", params);
	}

	/**
	 * @Method Name  : memberSendMessage
	 * @Date         : 2013. 11. 1.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 회원 쪽지
	 * @param params
	*/
	public void memberSendMessage(HashMap<String, String> params) {
		getSqlSession().insert("memberManagement.memberSendMessage", params);
	}

	/**
	 * @Method Name  : getMemberAdminEmail
	 * @Date         : 2013. 11. 4.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 회원 조회 - ID별 EMAIL 정보
	 * @param params
	 * @return
	*/
	public HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params){
		return getSqlSession().selectOne("memberManagement.getMemberAdminEmail", params);
	}

	/**
	 * @Method Name  : MemberAdminInsertEmail
	 * @Date         : 2013. 11. 4.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 메일 전송 내역 저장
	 * @param params
	*/
	public void MemberAdminInsertEmail(HashMap<String, String> params) {
		getSqlSession().insert("memberManagement.MemberAdminInsertEmail", params);
	}

	/**
	 * @Method Name  : getMemberStatList
	 * @Date         : 2014. 11
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 가입 분포
	 * @return
	*/
	public List<HashMap<String, String>> getMemberStatList(HashMap<String, String> params) {
		return getSqlSession().selectList("memberManagement.getMemberStatList", params);
	}
	
	public void OldmemberDeleteProcess(HashMap<String, String> params) {
		getSqlSession().delete("memberManagement.OldmemberDeleteProcess", params);
	}
	
	public List<HashMap<String, Object>> getPrimeMemberList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("memberManagement.getPrimeMemberList", searchMap);
	}
	
	public int getPrimeMemberListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("memberManagement.getPrimeMemberListCount", searchMap);
	}
}
