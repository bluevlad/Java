package egovframework.com.academy.code.service;

import java.util.List;

/**
*
* 공통코드에 관한 서비스 인터페이스 클래스를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
 *  2020.03.00	rainend		myProject 적용
* </pre>
*/

public interface CodeManageService {
	
	/**
	 * 공통코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return List(공통분류코드 목록)
	 * @throws Exception
	 */
	List<?> selectCodeList(CodeMstVO searchVO) throws Exception;
	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int(공통분류코드 총 갯수)
	 * @throws Exception 
	 */
	int selectCodeListTotCnt(CodeMstVO searchVO) throws Exception;
	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param Code
	 * @return Code(공통코드)
	 * @throws Exception
	 */
	CodeMst selectCodeDetail(CodeMst Code) throws Exception;
	/**
	 * 공통코드를 등록한다.
	 * @param Code
	 * @throws Exception
	 */
	void insertCode(CodeMst Code) throws Exception;
	/**
	 * 공통코드를 수정한다.
	 * @param CodeVO
	 * @throws Exception
	 */
	void updateCode(CodeMst Code) throws Exception;
	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCode(CodeMst Code) throws Exception;
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통상세코드 목록)
	 * @throws Exception
	 */
	List<?> selectCodeSubList(CodeMstVO searchVO) throws Exception;
	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int(공통상세코드 총 갯수)
	 */
	int selectCodeSubListTotCnt(CodeMstVO searchVO) throws Exception;
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param CodeDetailVO
	 * @return CmmnDetailCode(공통상세코드)
	 * @throws Exception
	 */
	CodeMst selectCodeSubDetail(CodeMst Code) throws Exception;
	/**
	 * 공통상세코드를 등록한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void insertCodeSub(CodeMst Code) throws Exception;
	/**
	 * 공통상세코드를 수정한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void updateCodeSub(CodeMst CodeSubVO) throws Exception;
	/**
	 * 공통상세코드를 삭제한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void deleteCodeSub(CodeMst CodeSubVO) throws Exception;

}
