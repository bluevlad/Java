package egovframework.com.academy.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.code.service.CodeMst;
import egovframework.com.academy.code.service.CodeMstVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
*
* 공통코드에 대한 데이터 접근 클래스를 정의한다
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

@Repository("CodeManageDAO")
public class CodeManageDAO extends EgovComAbstractDAO {

	   /**
		 * 공통코드 목록을 조회한다.
	     * @param searchVO
	     * @return List(공통코드 목록)
	     * @throws Exception
	     */
		public List<?> selectCodeList(CodeMstVO searchVO) throws Exception{
			 return list("CodeManage.selectCodeList", searchVO);
		}

   /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
	public int selectCodeListTotCnt(CodeMstVO searchVO) throws Exception{
		return (Integer)selectOne("CodeManage.selectCodeListTotCnt", searchVO);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param Code
	 * @return Code(공통코드)
	 */
	public CodeMst selectCodeDetail(CodeMst Code) throws Exception{
		return selectOne("CodeManage.selectCodeDetail", Code);
}

	/**
	 * 공통코드를 수정한다.
	 * @param Code
	 * @throws Exception
	 */
	public void updateCode(CodeMst Code) throws Exception{
		update("CodeManage.updateCode", Code);
	}

	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void insertCode(CodeMst Code) throws Exception{
		insert("CodeManage.insertCode", Code);
	}

	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void deleteCode(CodeMst Code) {
		delete("CodeManage.deleteCode", Code);
	}

}
