package egovframework.com.academy.code.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.academy.code.service.CodeMst;
import egovframework.com.academy.code.service.CodeMstVO;
import egovframework.com.academy.code.service.CodeSub;
import egovframework.com.academy.code.service.CodeSubVO;
import egovframework.com.academy.code.service.CodeManageService;
import egovframework.com.academy.code.service.CodeSubManageService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
*
* 공통상세코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
*   2009.04.01  이중호       최초 생성
*   2011.08.26	정진오	IncludedInfo annotation 추가
*   2017.08.08	이정은	표준프레임워크 v3.7 개선
 *  2020.03.00	rainend		myProject 적용
* </pre>
*/

@Controller
public class CodeSubManageController {

	@Resource(name = "CodeSubManageService")
   private CodeSubManageService codeSubManageService;

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
		 * 공통상세코드 목록을 조회한다.
	     * @param loginVO
	     * @param searchVO
	     * @param model
	     * @return "egovframework/com/academy/CodeSubList"
	     * @throws Exception
	     */
		@IncludedInfo(name="공통상세코드", listUrl="/code/SelectCodeSubList.do", order = 970 ,gid = 60)
	    @RequestMapping(value="/code/SelectCodeSubList.do")
		public String selectlCodeSubList (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") CodeSubVO searchVO, 
								ModelMap model) throws Exception {
			
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

	        List<?> CmmnCodeList = codeSubManageService.selectCodeSubList(searchVO);
	        model.addAttribute("resultList", CmmnCodeList);

	        int totCnt = codeSubManageService.selectCodeSubListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);

	        return "egovframework/com/academy/code/CodeSubList";
		}
		
		/**
		 * 공통상세코드 상세항목을 조회한다.
		 * @param loginVO
		 * @param CodeSubVO
		 * @param model
		 * @return "egovframework/com/academy/code/CodeSubDetail"
		 * @throws Exception
		 */
		@RequestMapping(value="/code/SelectCodeSubDetail.do")
	 	public String selectCodeSubDetail (@ModelAttribute("loginVO") LoginVO loginVO, CodeSubVO CodeSubVO, ModelMap model)	throws Exception {
	    	CodeSub vo = codeSubManageService.selectCodeSubDetail(CodeSubVO);
			model.addAttribute("result", vo);

			return "egovframework/com/academy/code/CodeSubDetail";
		}
	    
		/**
		 * 공통상세코드 등록을 위한 등록페이지로 이동한다.
		 * 
		 * @param CodeDetailVO
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/code/CodeSubRegist.do")
		public String CodeSubRegist(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("CodeVO") CodeMstVO CodeVO,
				@ModelAttribute("CodeSubVO") CodeSubVO CodeDetailVO, ModelMap model) throws Exception {
			
        	CodeMstVO searchCodeVO = new CodeMstVO();
        	searchCodeVO.setRecordCountPerPage(999999);
            searchCodeVO.setFirstIndex(0);
        	searchCodeVO.setSearchCondition("clCode");
            searchCodeVO.setSearchKeyword(CodeVO.getCodeId());
            
	        List<?> CodeList = codeManageService.selectCodeList(searchCodeVO);
	        model.addAttribute("codeList", CodeList);
	        
			return "egovframework/com/academy/code/CodeSubRegist";
		}
		
		/**
	     * 공통상세코드를 등록한다.
	     * 
	     * @param CodeSubVO
	     * @param CodeSubVO
	     * @param status
	     * @param model
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping("/code/insertCodeSub.do")
	    public String insertCodeSub(@ModelAttribute("CodeSubVO") CodeSubVO CodeSubVO,
	    		BindingResult bindingResult, ModelMap model) throws Exception {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			
	    	CodeMstVO searchCodeVO = new CodeMstVO();
		
			beanValidator.validate(searchCodeVO, bindingResult);
		
			if (bindingResult.hasErrors()) {
				List<?> CodeList = codeManageService.selectCodeList(searchCodeVO);
				model.addAttribute("CodeList", CodeList);
		        
			    return "egovframework/com/academy/code/CodeSubRegist";
			}
			
			if(CodeSubVO.getCodeId() != null){
				
				CodeSub vo = codeSubManageService.selectCodeSubDetail(CodeSubVO);
				if(vo != null){
					model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCde.validate.codeCheck"));
					
					List<?> CodeList = codeManageService.selectCodeList(searchCodeVO);
					model.addAttribute("clCodeList", CodeList);
			        
				    return "egovframework/com/academy/code/CodeSubRegist";
				}
			}
		
	    	CodeSubVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	codeSubManageService.insertCodeSub(CodeSubVO);
		
			return "forward:/code/SelectCodeSubList.do";
	    }
	    
	    /**
	     * 공통상세코드를 수정한다.
	     * 
	     * @param CodeSubVO
	     * @param model
	     * @return "egovframework/com/academy/code/updateCodeSub"
	     * @throws Exception
	     */
	    @RequestMapping("/code/updateCodeSub.do")
	    public String updateCodeSub(@ModelAttribute("CodeSubVO") CodeSubVO CodeSubVO, ModelMap model, BindingResult bindingResult ) throws Exception {
	    	
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
	    	beanValidator.validate(CodeSubVO, bindingResult);
	    	
	    	if (bindingResult.hasErrors()){
	    	CodeSub result = codeSubManageService.selectCodeSubDetail(CodeSubVO);
	    	model.addAttribute("CodeDetailVO", result);
	    	
	    	return "egovframework/com/academy/code/CodeSubDetail";
	    	}
	    	
	    	CodeSubVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	codeSubManageService.updateCodeSub(CodeSubVO);
	    	
	        return "forward:/code/SelectCodeSubList.do";
	    }
		
		/**
		 * 공통상세코드를 삭제한다.
		 * @param loginVO
		 * @param CodeDetailVO
		 * @param model
		 * @return "forward:/code/deleteCodeSub.do"
		 * @throws Exception
		 */
	    @RequestMapping(value="/sym/ccm/cde/deleteCodeSub.do")
		public String deleteCodeSub (@ModelAttribute("loginVO") LoginVO loginVO, CodeSubVO CodeSubVO	, ModelMap model) throws Exception {
	    	codeSubManageService.deleteCodeSub(CodeSubVO);
	    	
	        return "forward:/code/SelectCodeSubList.do";
		}
		
}
