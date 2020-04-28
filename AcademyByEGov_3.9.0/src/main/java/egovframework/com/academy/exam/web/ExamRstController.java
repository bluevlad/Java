package egovframework.com.academy.exam.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamPassManageService;
import egovframework.com.academy.exam.service.ExamRstService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시험 답안지 정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.04.00  			rainend          최초 생성
 *   2020.04.27  		rainend          답안지 채점
 * </pre>
 */

@Controller
public class ExamRstController {

	@Resource(name = "examRstService")
	private ExamRstService examRstService;

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	@Resource(name = "examPassManageService")
	private ExamPassManageService examPassManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험 응시자 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/exam/rst/List.do")
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

		model.addAttribute("examList", examRstService.selectExamRstList(ExamVO));

		int totCnt = examRstService.selectExamRstListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/rst/UserRstList";
	}

	/**
	 * 시험 응시자 정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/rst/Detail.do")
	public String ExamDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamVO.setFirstIndex(0);
		ExamVO.setRecordCountPerPage(100);
		model.addAttribute("examList", examManageService.selectExamList(ExamVO));
		
		model.addAttribute("ExamVO", examRstService.selectExamRstDetail(ExamVO));
		
		return "egovframework/com/academy/exam/rst/UserRstDetail";
	}

	/**
	 * 시험 응시자등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/rst/Regist.do")
	public String ExamRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamVO.setFirstIndex(0);
		ExamVO.setRecordCountPerPage(100);
		model.addAttribute("examList", examManageService.selectExamList(ExamVO));
		
		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/rst/UserRstRegist";
	}

	/**
	 * 시험 응시자 정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/rst/insert.do")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, 
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/rst/UserRstRegist";
		} else {
			examRstService.insertExamRst(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/rst/List.do";
		}
	}

	/**
	 * 기 등록된 시험 응시자 정보를 삭제한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/exam/rst/delete.do")
	public String deleteExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/rst/USerRstDetail";
		} else {
			examRstService.deleteExamRst(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
			return "forward:/exam/rst/List.do";
		}
	}

}
