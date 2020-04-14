package egovframework.com.academy.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.code.service.CodeSub;
import egovframework.com.academy.code.service.CodeSubVO;
import egovframework.com.academy.code.service.CodeSubManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
*
* 공통상세코드에 대한 서비스 구현클래스를 정의한다
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
@Service("CodeSubManageService")
public class CodeSubManageServiceImpl extends EgovAbstractServiceImpl implements CodeSubManageService{
	
    @Resource(name="CodeSubManageDAO")
    private CodeSubManageDAO codeSubManageDAO;
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCodeSubList(CodeSubVO searchVO) throws Exception {
        return codeSubManageDAO.selectCodeSubList(searchVO);
	}
    
	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCodeSubListTotCnt(CodeSubVO searchVO) throws Exception {
        return codeSubManageDAO.selectCodeSubListTotCnt(searchVO);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @throws Exception 
	 */
	@Override
	public CodeSub selectCodeSubDetail(CodeSubVO CodeDetailVO) throws Exception {
		CodeSub ret = codeSubManageDAO.selectCodeSubDetail(CodeDetailVO);
    	return ret;
	}

	/**
	 * 공통상세코드를 등록한다.
	 */
	@Override
	public void insertCodeSub(CodeSubVO CodeDetailVO) throws Exception {
		codeSubManageDAO.insertCodeSub(CodeDetailVO);
	}

	/**
	 * 공통상세코드를 수정한다.
	 */
	@Override
	public void updateCodeSub(CodeSubVO CodeDetailVO) throws Exception {
		codeSubManageDAO.updateCodeSub(CodeDetailVO);
	}

	/**
	 * 공통상세코드를 삭제한다.
	 * @throws Exception 
	 */
	@Override
	public void deleteCodeSub(CodeSubVO CodeDetailVO) throws Exception {
		codeSubManageDAO.deleteCodeSub(CodeDetailVO);
	}
	
}
