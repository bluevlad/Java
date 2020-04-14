package egovframework.com.academy.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.code.service.CodeSub;
import egovframework.com.academy.code.service.CodeSubVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
*
* 공통상세코드에 대한 데이터 접근 클래스를 정의한다
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

@Repository("CodeSubManageDAO")
public class CodeSubManageDAO extends EgovComAbstractDAO {
    
    /**
	 * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통상세코드 목록)
     * @throws Exception
     */
    public List<?> selectCodeSubList(CodeSubVO searchVO) throws Exception {
        return list("CodeSubManage.selectCodeSubList", searchVO);
    }

    /**
	 * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    public int selectCodeSubListTotCnt(CodeSubVO searchVO) throws Exception {
        return (Integer)selectOne("CodeSubManage.selectCodeSubListTotCnt", searchVO);
    }

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param CodeDetailVO
	 * @return CodeDetailVO(공통상세코드)
	 */
	public CodeSub selectCodeSubDetail(CodeSubVO CodeDetailVO) throws Exception{
		return (CodeSub) selectOne("CodeSubManage.selectCodeSubDetail", CodeDetailVO);
	}

	/**
	 * 공통상세코드를 등록한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	public void insertCodeSub(CodeSubVO CodeDetailVO) throws Exception{
		insert("CodeSubManage.insertCodeSub", CodeDetailVO);
		
	}

	/**
	 * 공통상세코드를 수정한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	public void updateCodeSub(CodeSubVO CodeDetailVO) throws Exception{
		insert("CodeSubManage.updateCodeSub", CodeDetailVO);
		
	}
	
	/**
	 * 공통상세코드를 삭제한다.
	 * @param CodeDetailVO
	 * @throws Exception
	 */
	public void deleteCodeSub(CodeSubVO CodeDetailVO) throws Exception{
		delete("CodeSubManage.deleteCodeSub", CodeDetailVO);
		
	}
    
}
