package egovframework.com.academy.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.code.service.CodeMst;
import egovframework.com.academy.code.service.CodeMstVO;
import egovframework.com.academy.code.service.CodeManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
*
* 공통코드에 대한 서비스 구현클래스를 정의한다
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

@Service("CodeManageService")
public class CodeManageServiceImpl extends EgovAbstractServiceImpl implements CodeManageService{

    @Resource(name="codeManageDAO")
    private CodeManageDAO codeManageDAO;

	/**
	 * 공통코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCodeList(CodeMstVO searchVO) throws Exception {
		return codeManageDAO.selectCodeList(searchVO);
	}
	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCodeListTotCnt(CodeMstVO searchVO) throws Exception {
        return codeManageDAO.selectCodeListTotCnt(searchVO);
	}
	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	@Override
	public CodeMst selectCodeDetail(CodeMst Code) throws Exception{
		return codeManageDAO.selectCodeDetail(Code);
	}
	/**
	 * 공통코드를 수정한다.
	 */
	@Override
	public void updateCode(CodeMst Code) throws Exception {
		codeManageDAO.updateCode(Code);
	}
	/**
	 * 공통코드를 등록한다.
	 */
	@Override
	public void insertCode(CodeMst Code) throws Exception {
		codeManageDAO.insertCode(Code);
		
	}
	/**
	 * 공통코드를 삭제한다.
	 */
	@Override
	public void deleteCode(CodeMst Code) throws Exception {
		codeManageDAO.deleteCode(Code);
	}
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCodeSubList(CodeMstVO Code) throws Exception {
        return codeManageDAO.selectCodeSubList(Code);
	}
	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCodeSubListTotCnt(CodeMstVO Code) throws Exception {
        return codeManageDAO.selectCodeSubListTotCnt(Code);
	}
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @throws Exception 
	 */
	@Override
	public CodeMst selectCodeSubDetail(CodeMst Code) throws Exception {
		return codeManageDAO.selectCodeSubDetail(Code);
	}
	/**
	 * 공통상세코드를 등록한다.
	 */
	@Override
	public void insertCodeSub(CodeMst Code) throws Exception {
		codeManageDAO.insertCodeSub(Code);
	}
	/**
	 * 공통상세코드를 수정한다.
	 */
	@Override
	public void updateCodeSub(CodeMst Code) throws Exception {
		codeManageDAO.updateCodeSub(Code);
	}
	/**
	 * 공통상세코드를 삭제한다.
	 * @throws Exception 
	 */
	@Override
	public void deleteCodeSub(CodeMst Code) throws Exception {
		codeManageDAO.deleteCodeSub(Code);
	}

}
