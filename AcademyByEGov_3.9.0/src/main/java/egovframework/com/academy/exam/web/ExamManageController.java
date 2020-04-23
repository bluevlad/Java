package egovframework.com.academy.exam.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamMst;
import egovframework.com.academy.exam.service.ExamVO;
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
public class ExamManageController {

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
	@RequestMapping(value = "/exam/List.do")
	public String ExamList(@ModelAttribute("searchVO") ExamVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> examList = examManageService.selectExamList(searchVO);
		model.addAttribute("resultList", examList);

		int totCnt = examManageService.selectExamListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/ExamList";
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/Detail.do")
	public String ExamDetail(@ModelAttribute("searchVO") ExamVO searchVO, @ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamMst vo = examManageService.selectExamDetail(searchVO);
		model.addAttribute("ExamVO", vo);
		
		return "egovframework/com/academy/exam/ExamDetail";
	}

	/**
	 * 시험등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/Regist.do")
	public String ExamRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/ExamRegist";
	}

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/insert.do")
	public String insertExam(@ModelAttribute("searchVO") ExamVO examVO, @ModelAttribute("ExamMst") ExamMst ExamMst, 
									BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamRegist";
		} else {
			examManageService.insertExam(ExamMst);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/List.do";
		}
	}

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/update.do")
	public String updateExam(@ModelAttribute("ExamVO") ExamVO examVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamDetail";
		} else {
			examManageService.updateExam(examVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/exam/List.do";
		}
	}

	/**
	 * 과목 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/subject/List.do")
	public String SubjectList(@ModelAttribute("ExamVO") ExamVO examVO, ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("deptManageList", examManageService.selectSubjectList(examVO));

		int totCnt = examManageService.selectSubjectListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/com/academy/exam/SubjectList";
	}

	/**
	 * 과목 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */

	@RequestMapping(value = "/subject/Detail.do")
	public String SubjectDetail(@ModelAttribute("ExamVO") ExamVO examVO, ModelMap model) throws Exception {

		model.addAttribute("result", examManageService.selectSubjectDetail(examVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/com/academy/exam/SubjectDetail";
	}

	/**
	 * 과목등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/subject/Regist.do")
	public String SubjectRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		return "egovframework/com/academy/exam/SubjectRegist";
	}

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/subject/insert.do")
	public String insertSubject(@ModelAttribute("ExamVO") ExamVO examVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/SubjectRegist";
		} else {
			examManageService.insertSubject(examVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/subject/List.do";
		}
	}

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/subject/update.do")
	public String updateSubject(@ModelAttribute("ExamVO") ExamVO examVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/SubjectDetail";
		} else {
			examManageService.updateSubject(examVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/subject/List.do";
		}
	}

}
