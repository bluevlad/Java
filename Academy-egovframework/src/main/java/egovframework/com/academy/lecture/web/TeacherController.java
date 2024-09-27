package egovframework.com.academy.lecture.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.lecture.service.LecFormService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.academy.lecture.service.SubjectService;
import egovframework.com.academy.lecture.service.TeacherService;
import egovframework.com.academy.member.service.MemberVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 강사정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
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
public class TeacherController {

	@Resource(name = "teacherService")
	private TeacherService teacherService;

	@Resource(name = "subjectService")
	private SubjectService subjectService;

	@Resource(name = "lecFormService")
	private LecFormService lecFormService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 강사 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/leture/teacher/List.do")
	public String ListTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO, ModelMap model) throws Exception {

		MemberVO.setPageUnit(propertyService.getInt("pageUnit"));
		MemberVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(MemberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(MemberVO.getPageUnit());
		paginationInfo.setPageSize(MemberVO.getPageSize());

		MemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		MemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		MemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("resultList", teacherService.selectTeacherList(MemberVO));

		int totCnt = teacherService.selectTeacherListCount(MemberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/lecture/teacher/List";
	}

	/**
	 * 강사 상세조회 조회한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/leture/teacher/Detail.do")
	public String DetailTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
        model.addAttribute("userId", commandMap.get("userId") == null ? "" : (String)commandMap.get("userId"));
        MemberVO.setUserId((String) commandMap.get("userId"));

     	//공통코드 질문유형 조회
        MemberVO = teacherService.teacherDetail(MemberVO);
        model.addAttribute("MemberVO", MemberVO);
        
        LectureVO LectureVO = new LectureVO();
		model.addAttribute("categoryList", subjectService.selectCategoryList(LectureVO)); //직렬(분류) 리스트
		model.addAttribute("subjectList", subjectService.getSubjectList(LectureVO)); //과목 리스트
        
		return "egovframework/com/academy/lecture/teacher/Detail";
	}

	/**
	 * 강사 등록화면으로 이동한다.
	 * @param LectureVO
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/leture/teacher/Regist.do")
	public String RegistTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO) throws Exception {
	
		return "egovframework/com/academy/lecture/teacher/Regist";
	}

	/**
	 * 강사정보를 신규로 등록한다.
	 * @param MemberVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/teacher/Insert.do")
	public String insertTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/teacher/Regist";
		} else {
			MemberVO.setRegId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			MemberVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			teacherService.insertTeacher(MemberVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			
			return "forward:/academy/leture/teacher/List.do";
		}
	}

	/**
	 * 기 등록된 강사정보를 수정한다.
	 * @param MemberVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/teacher/Update.do")
	public String updateTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/teacher/Detail";
		} else {
			MemberVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			teacherService.updateTeacher(MemberVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/academy/leture/teacher/List.do";
		}
	}

	/**
	 * 기 등록된 강사정보를 삭제한다.
	 * @param MemberVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/leture/teacher/Delete.do")
	public String deleteTeacher(@ModelAttribute("MemberVO") MemberVO MemberVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/lecture/teacher/Detail";
		} else {
			MemberVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			teacherService.deleteTeacher(MemberVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/academy/leture/teacher/List.do";
		}
	}

}
