package egovframework.com.academy.code.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.academy.code.service.CodeMst;
import egovframework.com.academy.code.service.CodeMstVO;
import egovframework.com.academy.code.service.CodeManageService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
*
* 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
*   2011.8.26	정진오			IncludedInfo annotation 추가
*   2017.08.16	이정은	표준프레임워크 v3.7 개선
 *  2020.03.00	rainend		myProject 적용
* </pre>
*/

@Controller
public class CodeManageController {
	
	@Resource(name = "CodeManageService")
    private CodeManageService codeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	
	
	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/academy/code/CodeList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "공통코드", listUrl = "/code/SelectCodeList.do", order = 980, gid = 60)
	@RequestMapping(value = "/code/SelectCodeList.do")
	public String selectCodeList(@ModelAttribute("searchVO") CodeMstVO searchVO, ModelMap model)
			throws Exception {
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> CodeList = codeManageService.selectCodeList(searchVO);
		model.addAttribute("resultList", CodeList);

		int totCnt = codeManageService.selectCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/code/CodeMstList";
	}
	
	/**
	 * 공통코드 상세항목을 조회한다.
	 * 
	 * @param loginVO
	 * @param CodeVO
	 * @param model
	 * @return "egovframework/com/academy/code/CodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/code/SelectCodeDetail.do")
	public String selectCodeDetail(@ModelAttribute("loginVO") LoginVO loginVO, CodeMstVO CodeVO, ModelMap model) throws Exception {
		
		CodeMst vo = codeManageService.selectCodeDetail(CodeVO);
		
		model.addAttribute("result", vo);

		return "egovframework/com/academy/code/CodeMstDetail";
	}
	
	/**
	 * 공통코드 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param CodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/code/CodeRegist.do")
	public String CodeRegist(@ModelAttribute("CodeVO") CodeMstVO CodeVO, ModelMap model) throws Exception {
		
		CodeMstVO searchVO = new CodeMstVO();
		searchVO.setFirstIndex(0);
        List<?> CodeList = codeManageService.selectCodeList(searchVO);
        
        model.addAttribute("CodeList", CodeList);

		return "egovframework/com/academy/code/CodeMstRegist";
	}
	
	/**
     * 공통코드를 등록한다.
     * 
     * @param CodeVO
     * @param CodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/code/insertCode.do")
    public String insertCode(@ModelAttribute("searchVO") CodeMstVO Code, @ModelAttribute("CodeVO") CodeMstVO CodeVO,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		CodeMstVO searchVO = new CodeMstVO();
	
		beanValidator.validate(CodeVO, bindingResult);
	
		if (bindingResult.hasErrors()) {
			
	        List<?> CodeList = codeManageService.selectCodeList(searchVO);
	        model.addAttribute("CodeList", CodeList);
	        
		    return "egovframework/com/academy/code/CodeMstRegist";
		}
		
		if(Code.getCodeId() != null){
			CodeMst vo = codeManageService.selectCodeDetail(Code);
			if(vo != null){
				model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCca.validate.codeCheck"));
				
				searchVO.setFirstIndex(0);
		        List<?> CodeList = codeManageService.selectCodeList(searchVO);
		        model.addAttribute("CodeList", CodeList);
		        
				return "egovframework/com/academy/code/EgovCcmCmmnCodeRegist";
			}
		}
	
		CodeVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		codeManageService.insertCode(CodeVO);
	
		return "forward:/code/SelectCodeList.do";
    }
    
    /**
     * 공통코드를 수정한다.
     * 
     * @param CodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/code/updateCode.do")
    public String updateCode(@ModelAttribute("searchVO") CodeMstVO cmmnCode, @ModelAttribute("CodeVO") CodeMstVO CodeVO,
	    BindingResult bindingResult, ModelMap model) throws Exception {

				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		beanValidator.validate(CodeVO, bindingResult);
		if (bindingResult.hasErrors()) {
	
			CodeMst result = codeManageService.selectCodeDetail(cmmnCode);
		    model.addAttribute("CodeVO", result);
	
		    return "egovframework/com/sym/ccm/cca/EgovCcmCmmnCodeUpdt";
		}
	
		CodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		codeManageService.updateCode(CodeVO);

		return "forward:/sym/ccm/cca/SelectCcmCmmnCodeList.do";
    }
       
    /**
     * 공통코드를 삭제한다.
     * 
     * @param CodeVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/code/deleteCode.do")
    public String deleteCode(@ModelAttribute("CodeVO") CodeMstVO CodeVO, BindingResult bindingResult, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		CodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		codeManageService.deleteCode(CodeVO);

		return "forward:/code/SelectCodeList.do";
    }
	
}
