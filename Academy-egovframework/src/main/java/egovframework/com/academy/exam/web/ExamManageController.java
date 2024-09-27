package egovframework.com.academy.exam.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamSetManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
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
 *  2023.06.00  			rainend          시험관리 고도화
 * </pre>
 */

@Controller
public class ExamManageController {

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	@Resource(name = "examSetManageService")
	private ExamSetManageService ExamSetManageService;

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
	@IncludedInfo(name = "시험정보관리", listUrl = "/academy/exam/mst/List.do", order = 2, gid = 1 )
	@RequestMapping(value = "/academy/exam/mst/List.do")
	public String ExamList(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

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

		model.addAttribute("examList", examManageService.selectExamList(ExamVO));

		int totCnt = examManageService.selectExamListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/mst/List";
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/mst/Detail.do")
	public String ExamDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("ExamVO", examManageService.selectExamDetail(ExamVO));

		model.addAttribute("ExamSet", ExamSetManageService.selectExamSetDetail(ExamVO));
		
		return "egovframework/com/academy/exam/mst/Detail";
	}

	/**
	 * 시험등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/mst/Regist.do")
	public String ExamRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/mst/Regist";
	}

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/mst/Insert.do")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/mst/Regist";
		} else {
			examManageService.insertExam(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/mst/List.do";
		}
	}

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/mst/Update.do")
	public String updateExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/mst/Detail";
		} else {
			examManageService.updateExam(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/mst/List.do";
		}
	}

	/**
	 * 과목 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@IncludedInfo(name = "과목정보관리", listUrl = "/academy/exam/sbj/List.do", order = 3, gid = 1 )
	@RequestMapping(value = "/academy/exam/sbj/List.do")
	public String ExamSubjectList(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		/** paging */
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
		
		return "egovframework/com/academy/exam/sbj/List";
	}

	/**
	 * 과목 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */

	@RequestMapping(value = "/academy/exam/sbj/Detail.do")
	public String ExamSubjectDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("subjectManage", examManageService.selectSubjectDetail(ExamVO));
		
		return "egovframework/com/academy/exam/sub/Detail";
	}

	/**
	 * 과목등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/sbj/Regist.do")
	public String ExamSubjectRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		model.addAttribute("subjectManage", ExamVO);
		return "egovframework/com/academy/exam/sbj/Regist";
	}

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/sbj/Insert.do")
	public String insertExamSubject(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/sbj/tRegist";
		} else {
			examManageService.insertSubject(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/sbj/List.do";
		}
	}

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/sbj/Update.do")
	public String updateExamSubject(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/sbj/tDetail";
		} else {
			examManageService.updateSubject(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/sbj/List.do";
		}
	}

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "/academy/exam/mst/SetListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/mst/SetListPopup.do")
	public String QueListPopup(@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
			ExamVO ExamVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		ExamVO.setPageUnit(propertyService.getInt("pageUnit"));
		ExamVO.setPageSize(propertyService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ExamVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ExamVO.getPageUnit());
		paginationInfo.setPageSize(ExamVO.getPageSize());

		model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		List<?> sampleList = ExamSetManageService.selectExamSetList(ExamVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = ExamSetManageService.selectExamSetListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/exam/mst/SetListPopup";
	}

}
