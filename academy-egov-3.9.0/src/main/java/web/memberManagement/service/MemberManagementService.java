package web.memberManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : MemberManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 10
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 회원조회  service
 */
public interface MemberManagementService {
	
	/**
	 * @Method Name  : getMemberList
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 	회원조회  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> getMemberList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 관리 -  회원조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getMemberListCount(Map<String, Object> searchMap);
	
	
	/**
	 * @Method Name  : getoldMemberList
	 * @Date         : 2016. 06. 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 	이관 회원조회  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> getOldMemberList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : getOldMemberListCount
	 * @Date         : 2016. 06. 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 관리 - 이관 회원조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getOldMemberListCount(Map<String, Object> searchMap);
	
	
	/**
	 * @Method Name  : getCategoryList
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :  관심분야 리스트
	 * @return
	*/
	List<HashMap<String, String>> getCategoryList();
	
	/**
	 * @Method Name  : memberIdCheck
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 회원 ID 중복 체크
	 * @param params
	 * @return
	*/
	int memberIdCheck(HashMap<String, String> params);
	
	/**
	 * @Method Name  : memberCategoryDelete
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 관심분야 삭제
	 * @param params
	*/
	void memberCategoryDelete(HashMap<String, String> params);
	
	/**
	 * @Method Name  : memberCategoryInsert
	 * @Date         : 2013. 10 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	관심분야 등록
	 * @param params
	*/
	void memberCategoryInsert(HashMap<String, String> params);
	/**
	 * @Method Name  : memberInsertProcess
	 * @Date         : 2013. 10.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회  - 등록 프로세스
	 * @param params
	*/
	void memberInsertProcess(HashMap<String, String> params);
	/**
	 * @Method Name  : memberDetail
	 * @Date         : 2013. 10.  
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 - 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> memberDetail(HashMap<String, Object> params);
	/**
	 * @Method Name  : oldMemberDetail
	 * @Date         : 2016. 06.  
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	이관 회원 조회 - 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> oldMemberDetail(HashMap<String, Object> params);
	/**
	 * @Method Name  : memberUpdateProcess
	 * @Date         : 2013. 10. 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원조회  - 수정 프로세스
	 * @param params
	*/
	void memberUpdateProcess(HashMap<String, String> params);
	/**
	 * @Method Name  : memberDelete
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 조회 - 삭제
	 * @param params
	*/
	void memberDelete(HashMap<String, String> params);
	
	/**
	 * @Method Name  : searchZipCode
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	우편번호 검색
	 * @param params
	 * @return
	*/
	List<HashMap<String, String>> searchZipCode(HashMap<String, String> params);
	
	/**
	 * @Method Name  : memberSendMessage
	 * @Date         : 2013. 11. 4. 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 회원 쪽지 보내기
	 * @param params
	*/
	void memberSendMessage(HashMap<String, String> params);
	
	/**
	 * @Method Name  : getMemberAdminEmail
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	회원 EMAIL 정보 가져오기
	 * @param params
	 * @return
	*/
	HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params);
	
	/**
	 * @Method Name  : MemberAdminInsertEmail
	 * @Date         : 2013. 10
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :전송 메일 정보 저장
	 * @param params
	*/
	void MemberAdminInsertEmail(HashMap<String, String> params);
	
	/**
	 * @Method Name  : getMemberStatList
	 * @Date         : 2014. 11
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :  회원가입분포
	 * @return
	*/
	List<HashMap<String, String>> getMemberStatList(HashMap<String, String> params);
	
	void OldmemberDeleteProcess(HashMap<String, String> params);
	
	List<HashMap<String, Object>> getPrimeMemberList(	Map<String, Object> searchMap);
	
	int getPrimeMemberListCount(Map<String, Object> searchMap);

}
