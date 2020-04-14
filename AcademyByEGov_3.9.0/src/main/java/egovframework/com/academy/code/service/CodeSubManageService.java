package egovframework.com.academy.code.service;

import java.util.List;

import egovframework.com.academy.code.service.CodeSubVO;

/**
*
* 공통상세코드에 관한 서비스 인터페이스 클래스를 정의한다
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

public interface CodeSubManageService {
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통상세코드 목록)
	 * @throws Exception
	 */
	List<?> selectCodeSubList(CodeSubVO searchVO) throws Exception;

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int(공통상세코드 총 갯수)
	 */
	int selectCodeSubListTotCnt(CodeSubVO searchVO) throws Exception;
	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param CodeDetailVO
	 * @return CmmnDetailCode(공통상세코드)
	 * @throws Exception
	 */
	CodeSub selectCodeSubDetail(CodeSubVO CodeSubVO) throws Exception;

	/**
	 * 공통상세코드를 등록한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void insertCodeSub(CodeSubVO CodeSubVO) throws Exception;

	/**
	 * 공통상세코드를 수정한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void updateCodeSub(CodeSubVO CodeSubVO) throws Exception;

	/**
	 * 공통상세코드를 삭제한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	void deleteCodeSub(CodeSubVO CodeSubVO) throws Exception;

}
