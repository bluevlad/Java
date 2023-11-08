package egovframework.com.academy.lecture.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.academy.lecture.service.LecFormService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 과목정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.08.00  			rainend          최초 생성
 * </pre>
 */

@Controller
public class LecFormController {

	@Resource(name = "lecFormService")
	private LecFormService lecFormService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 과목 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/leture/form/List.do")
	public String SubjectList(@ModelAttribute("LectureVO") LectureVO LectureVO, ModelMap model) throws Exception {

		LectureVO.setPageUnit(propertyService.getInt("pageUnit"));
		LectureVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(LectureVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(LectureVO.getPageUnit());
		paginationInfo.setPageSize(LectureVO.getPageSize());

		LectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		LectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
		LectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("resultList", lecFormService.selectFormList(LectureVO));

		int totCnt = lecFormService.selectFormListCount(LectureVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/leture/form/List";
	}

	/**
	 * 과목 상세조회 조회한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/leture/form/Detail.do")
	public String SubjectDetail(@ModelAttribute("LectureVO") LectureVO LectureVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
        model.addAttribute("subjectCd", commandMap.get("subjectCd") == null ? "" : (String)commandMap.get("subjectCd"));
        LectureVO.setSubjectCd((String) commandMap.get("subjectCd"));

        LectureVO = lecFormService.selectFormDetail(LectureVO);
        model.addAttribute("LectureVO", LectureVO);
        
		return "egovframework/com/academy/leture/form/Detail";
	}

	/**
	 * 과목 상세조회 조회한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/leture/form/Regist.do")
	public String SubjectRegist(@ModelAttribute("LectureVO") LectureVO LectureVO) throws Exception {
	
		return "egovframework/com/academy/leture/form/Regist";
	}

	/**
	 * @Method Name : codeCheck
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 코드중복체크
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/leture/form/codeCheck.do")
	@ResponseBody
	public String codeCheck(@ModelAttribute("LectureVO") LectureVO LectureVO) throws Exception {

	    int listCount = lecFormService.selectFormCheck(LectureVO);
	    String returnStr = "Y";
	    if(listCount > 0)
	    	returnStr = "N";
	    return returnStr;
	}

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/form/Insert.do")
	public String insertSubject(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/subject/Regist";
		} else {
			LectureVO.setRegId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			lecFormService.insertForm(LectureVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			
			return "forward:/academy/leture/form/List.do";
		}
	}

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/form/Update.do")
	public String updateSubjct(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/subject/Detail";
		} else {
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			lecFormService.updateForm(LectureVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/academy/leture/form/List.do";
		}
	}

	/**
	 * 기 등록된 과목정보를 삭제한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/subject/Delete.do")
	public String deleteSubject(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/subject/Detail";
		} else {
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			lecFormService.deleteForm(LectureVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/academy/leture/subject/List.do";
		}
	}

}
