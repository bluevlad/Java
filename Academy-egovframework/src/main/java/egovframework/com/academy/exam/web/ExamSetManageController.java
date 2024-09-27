package egovframework.com.academy.exam.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.exam.service.ExamBankManageService;
import egovframework.com.academy.exam.service.ExamSetManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시험템플릿 Controller Class 구현
 * 
 * @author 공통서비스 Y.K.KIM
 * @since 2023.04.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2023.05.20   Y.K.K      시험세트 등록
 *
 *      </pre>
 */

@Controller
public class ExamSetManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExamSetManageController.class);

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "examSetManageService")
	private ExamSetManageService ExamSetManageService;

	@Resource(name = "examBankManageService")
	private ExamBankManageService ExamBankManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "academy/survey/set/List"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/List.do")
	public String egovQustnrTmplatManageList(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap, ExamVO ExamVO, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

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

		List<?> sampleList = ExamSetManageService.selectExamSetList(ExamVO);
		model.addAttribute("resultList", sampleList);

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		int totCnt = ExamSetManageService.selectExamSetListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/exam/set/List";
	}

	/**
	 * 설문템플릿 목록을 상세조회 조회한다.
	 * 
	 * @param searchVO
	 * @param SurveyVO
	 * @param commandMap
	 * @param model
	 * @return "academy/survey/set/Detail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/Detail.do")
	public String egovQustnrTmplatManageDetail(@ModelAttribute("searchVO") ComDefaultVO searchVO, ExamVO ExamVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		model.addAttribute("ExamVO", ExamSetManageService.selectExamSetDetail(ExamVO));

		List<?> queList = ExamSetManageService.selectExamSetItemList(ExamVO);
		model.addAttribute("ExamSetItem", queList);

		return "egovframework/com/academy/exam/set/Detail";
	}

	/**
	 * 설문템플릿를 수정처리 한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "academy/survey/set/Update"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/Update.do")
	public String ExmSetUpdate(final HttpServletRequest request,
			@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
			@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		ExamVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		ExamVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		ExamSetManageService.updateExamSet(ExamVO);

		String[] v_queids = request.getParameterValues("v_queId");

		if (v_queids != null) {
			ExamVO.setQueId(0);
			ExamSetManageService.deleteExamSetItem(ExamVO);
			for (int i = 0; i < v_queids.length; i++) {
				ExamVO.setQueId(CommonUtil.parseInt(v_queids[i]));
				ExamVO.setQueSeq(CommonUtil.parseInt(request.getParameter("v_queSeq_" + v_queids[i])));
				ExamSetManageService.insertExamSetItem(ExamVO);
			}
		}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		return "forward:/academy/exam/set/List.do";
	}

	/**
	 * 설문템플릿를 등록한다. / 초기등록페이지
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "academy/survey/set/Regist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/Regist.do")
	public String qustnrTmplatManageRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap, @ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model)
			throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		return "egovframework/com/academy/exam/set/Regist";
	}

	/**
	 * 설문템플릿를 등록 처리 한다. / 등록처리
	 * 
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "academy/survey/set/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/Insert.do")
	public String qustnrTmplatManageRegistActor(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		ExamVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		ExamVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		ExamSetManageService.insertExamSet(ExamVO);

		return "redirect:/academy/exam/set/List.do";
	}

	/**
	 * 설문템플릿 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "/academy/survey/set/QueListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/QueListPopup.do")
	public String QueListPopup(@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
			ExamVO ExamVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		ExamVO.setPageUnit(propertiesService.getInt("pageUnit"));
		ExamVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ExamVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ExamVO.getPageUnit());
		paginationInfo.setPageSize(ExamVO.getPageSize());

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		model.addAttribute("setId", commandMap.get("setId") == null ? "" : (String) commandMap.get("setId"));

		List<?> examBankItemList = ExamBankManageService.selectExamBankItemlList(ExamVO);
		model.addAttribute("resultList", examBankItemList);

		int totCnt = ExamBankManageService.selectExamBankItemListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/exam/set/QueListPopup";
	}

	/**
	 * 설문템플릿를 등록 처리 한다. / 등록처리
	 * 
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "academy/survey/set/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/queInsert.do")
	protected String SurveySetQueRegistActor(ExamVO ExamVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		String sKey = "";
		for (Object key : reqMap.keySet()) {
			sKey = key.toString();
			if (sKey.equals("setId")) {
				ExamVO.setSetId(Integer.valueOf(request.getParameter(sKey)));
			}
			if (sKey.equals("queId")) {
				ExamVO.setQueId(Integer.valueOf(request.getParameter(sKey)));
			}
		}
		ExamVO.setQueSeq(ExamSetManageService.selectExamSetQueSeq(ExamVO));
		
		ExamSetManageService.insertExamSetItem(ExamVO);

		return "forward:/academy/exam/set/QueListPopup.do";
	}

	/**
	 * 설문템플릿를 등록 처리 한다. / 등록처리
	 * 
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "academy/survey/set/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/exam/set/queDelete.do")
	public String qustnrTmplatManageDeleteActor(ExamVO ExamVO, ModelMap model, @RequestParam Map<?, ?> reqMap,
			HttpServletRequest request) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		ExamVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		ExamVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		String sKey = "";
		for (Object key : reqMap.keySet()) {
			sKey = key.toString();
			if (sKey.equals("setId")) {
				ExamVO.setSetId(Integer.valueOf(request.getParameter(sKey)));
			}
			if (sKey.equals("queId")) {
				ExamVO.setQueId(Integer.valueOf(request.getParameter(sKey)));
			}
		}
		ExamSetManageService.deleteExamSetItem(ExamVO);

		return "forward:/academy/exam/set/Detail.do";
	}

}
