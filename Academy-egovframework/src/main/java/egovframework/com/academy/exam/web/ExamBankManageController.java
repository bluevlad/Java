package egovframework.com.academy.exam.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.exam.service.ExamBankManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 시험문제정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2021.08.00  			rainend          최초 생성
 * </pre>
 */

@Controller
public class ExamBankManageController {

	@Resource(name = "examBankManageService")
	private ExamBankManageService examBankManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험문제 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/exam/bank/List.do")
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

		model.addAttribute("examBankItemList", examBankManageService.selectExamBankItemlList(ExamVO));

		int totCnt = examBankManageService.selectExamBankItemListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/bank/List";
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/bank/Detail.do")
	public String ExamDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		model.addAttribute("ExamVO", examBankManageService.selectExamBankItemDetail(ExamVO));
		
		return "egovframework/com/academy/exam//bank/Detail";
	}

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/bank/Insert.do")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamBankItemRegist";
		} else {
			ExamVO.setRegId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			examBankManageService.insertExamBankItem(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/bank/List.do";
		}
	}

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/bank/Update.do")
	public String updateExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/bank/Detail";
		} else {
			ExamVO.setUpdId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			examBankManageService.updateExamBankItem(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/bank/List.do";
		}
	}
	
}
