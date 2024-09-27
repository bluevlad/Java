package egovframework.com.academy.lecture.web;

import java.util.Map;

import javax.annotation.Resource;

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
 * 강의형태 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.11.00  			rainend          최초 생성
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
	 * 학습형태 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/lecture/form/List.do")
	public String List(@ModelAttribute("LectureVO") LectureVO LectureVO, ModelMap model) throws Exception {

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
		
		return "egovframework/com/academy/lecture/form/List";
	}

	/**
	 * 학습형태 상세조회 조회한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/lecture/form/Detail.do")
	public String Detail(@ModelAttribute("LectureVO") LectureVO LectureVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
        model.addAttribute("formCode", commandMap.get("formCode") == null ? "" : (String)commandMap.get("formCode"));
        LectureVO.setFormCode((String) commandMap.get("formCode"));

        LectureVO = lecFormService.selectFormDetail(LectureVO);
        model.addAttribute("LectureVO", LectureVO);
        
		return "egovframework/com/academy/lecture/form/Detail";
	}

	/**
	 * 학습형태 등록한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/lecture/form/Regist.do")
	public String Regist(@ModelAttribute("LectureVO") LectureVO LectureVO) throws Exception {
	
		return "egovframework/com/academy/lecture/form/Regist";
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
	@RequestMapping(value="/academy/lecture/form/codeCheck.do")
	@ResponseBody
	public String codeCheck(@ModelAttribute("LectureVO") LectureVO LectureVO) throws Exception {

	    int listCount = lecFormService.selectFormCheck(LectureVO);
	    String returnStr = "Y";
	    if(listCount > 0)
	    	returnStr = "N";
	    return returnStr;
	}

	/**
	 * 학습형태정보를 신규로 등록한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/lecture/form/Insert.do")
	public String insert(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/form/Regist";
		} else {
			LectureVO.setRegId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			
			lecFormService.insertForm(LectureVO);
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			
			return "forward:/academy/lecture/form/List.do";
		}
	}

	/**
	 * 기 등록된 학습형태정보를 수정한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/lecture/form/Update.do")
	public String update(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/form/Detail";
		} else {
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			
			lecFormService.updateForm(LectureVO);
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/academy/lecture/form/List.do";
		}
	}

	/**
	 * 기 등록된 학습형태정보를 삭제한다.
	 * @param LectureVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/lecture/form/Delete.do")
	public String delete(@ModelAttribute("LectureVO") LectureVO LectureVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/form/Detail";
		} else {
			LectureVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			
			lecFormService.deleteForm(LectureVO);
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));

			return "forward:/academy/lecture/form/List.do";
		}
	}

}
