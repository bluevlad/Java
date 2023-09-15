package egovframework.com.academy.exam.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.api.CORSFilter;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시험(과목)정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.04.00  			rainend          최초 생성
 * </pre>
 */

@Controller
public class ExamManageApi extends CORSFilter {

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/api/academy/exam/ExamMstList", method = RequestMethod.GET)
	public ModelAndView ExamList(@ModelAttribute("ExamVO") ExamVO ExamVO) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		ExamVO.setPageUnit(propertyService.getInt("pageUnit"));
		ExamVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ExamVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ExamVO.getPageUnit());
		paginationInfo.setPageSize(ExamVO.getPageSize());

		ExamVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ExamVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ExamVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = examManageService.selectExamListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<?> examList = examManageService.selectExamList(ExamVO);
		modelAndView.addObject(examList);

		return modelAndView;
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/ExamMstDetail.do")
	public String ExamDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("ExamVO", examManageService.selectExamDetail(ExamVO));
		
		return "egovframework/com/academy/exam/ExamMstDetail";
	}*/

	/**
	 * 시험등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/ExamMstRegist.do")
	public String ExamRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/ExamMstRegist";
	}*/

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/ExamMstInsert.do")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamMstRegist";
		} else {
			examManageService.insertExam(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/ExamMstList.do";
		}
	}*/

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/ExamMstUpdate.do")
	public String updateExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamMstDetail";
		} else {
			examManageService.updateExam(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/ExamMstList.do";
		}
	}*/

	/**
	 * 과목 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	/*
	@IncludedInfo(name = "과목정보관리", listUrl = "/academy/exam/SbjList.do", order = 3, gid = 1 )
	@RequestMapping(value = "/academy/exam/SbjList.do")
	public String SubjectList(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ExamVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ExamVO.getPageUnit());
		paginationInfo.setPageSize(ExamVO.getPageSize());

		ExamVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ExamVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ExamVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("subjectList", examManageService.selectSubjectList(ExamVO));

		int totCnt = examManageService.selectSubjectListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/SubjectList";
	}*/

	/**
	 * 과목 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
/*
	@RequestMapping(value = "/academy/exam/SbjDetail.do")
	public String SubjectDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("subjectManage", examManageService.selectSubjectDetail(ExamVO));
		
		return "egovframework/com/academy/exam/SubjectDetail";
	}*/

	/**
	 * 과목등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/SbjRegist.do")
	public String SubjectRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("subjectManage", ExamVO);
		return "egovframework/com/academy/exam/SubjectRegist";
	}*/

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/SbjInsert.do")
	public String insertSubject(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/SubjectRegist";
		} else {
			examManageService.insertSubject(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/SbjList.do";
		}
	}*/

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	/*
	@RequestMapping(value = "/academy/exam/SbjUpdate.do")
	public String updateSubject(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/SubjectDetail";
		} else {
			examManageService.updateSubject(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/SbjList.do";
		}
	}*/

}
