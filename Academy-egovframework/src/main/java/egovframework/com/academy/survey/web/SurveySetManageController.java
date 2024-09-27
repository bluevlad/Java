package egovframework.com.academy.survey.web;

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

import egovframework.com.academy.survey.service.SurveyBankManageService;
import egovframework.com.academy.survey.service.SurveySetManageService;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 설문템플릿 Controller Class 구현
 * 
 * @author 공통서비스 Y.K.KIM
 * @since 2023.04.20
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.20   장동한            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2020.10.30   신용호            파일업로드 제한을위한 파라미터 전달
 *  2023.04.20   Y.K.K      파일업로드 제한을위한 파라미터 전달
 *
 *      </pre>
 */

@Controller
public class SurveySetManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveySetManageController.class);

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "surveySetManageService")
	private SurveySetManageService SurveySetManageService;

	@Resource(name = "surveyBankManageService")
	private SurveyBankManageService SurveyBankManageService;

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
	@RequestMapping(value = "/academy/survey/set/List.do")
	public String egovQustnrTmplatManageList(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap, SurveyVO SurveyVO, ModelMap model) throws Exception {

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

		List<?> sampleList = SurveySetManageService.selectSurveySetList(SurveyVO);
		model.addAttribute("resultList", sampleList);

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		int totCnt = SurveySetManageService.selectSurveySetListTotCnt(SurveyVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/survey/set/List";
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
	@RequestMapping(value = "/academy/survey/set/Detail.do")
	public String egovQustnrTmplatManageDetail(@ModelAttribute("searchVO") ComDefaultVO searchVO, SurveyVO SurveyVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		model.addAttribute("SurveyVO", SurveySetManageService.selectSurveySetDetail(SurveyVO));

		List<?> queList = SurveySetManageService.selectSurveySetItemList(SurveyVO);
		model.addAttribute("SuyveySetItem", queList);

		return "egovframework/com/academy/survey/set/Detail";
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
	@RequestMapping(value = "/academy/survey/set/Update.do")
	public String qustnrTmplatManageModifyActor(final HttpServletRequest request,
			@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		SurveyVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		SurveyVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		SurveySetManageService.updateSurveySet(SurveyVO);

		String[] v_queids = request.getParameterValues("v_queId");

		if (v_queids != null) {
			SurveySetManageService.deleteSurveySetItem(SurveyVO);
			for (int i = 0; i < v_queids.length; i++) {
				SurveyVO.setQueId(CommonUtil.parseInt(v_queids[i]));
				SurveyVO.setQueSeq(CommonUtil.parseInt(request.getParameter("v_queSeq_" + v_queids[i])));
				SurveySetManageService.insertSurveySetItem(SurveyVO);
			}
		}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		return "forward:/academy/survey/set/List.do";
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
	@RequestMapping(value = "/academy/survey/set/Regist.do")
	public String qustnrTmplatManageRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap, @ModelAttribute("SurveyVO") SurveyVO SurveyVO, ModelMap model)
			throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		return "egovframework/com/academy/survey/set/Regist";
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
	@RequestMapping(value = "/academy/survey/set/Insert.do")
	public String qustnrTmplatManageRegistActor(@ModelAttribute("searchVO") ComDefaultVO searchVO, SurveyVO SurveyVO,
			ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 아이디 설정
		SurveyVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		SurveyVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		SurveySetManageService.insertSurveySet(SurveyVO);

		return "redirect:/academy/survey/set/Detail.do";
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
	@RequestMapping(value = "/academy/survey/set/QueListPopup.do")
	public String QueListPopup(@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
			SurveyVO surveyVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		surveyVO.setPageUnit(propertiesService.getInt("pageUnit"));
		surveyVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(surveyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(surveyVO.getPageUnit());
		paginationInfo.setPageSize(surveyVO.getPageSize());

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		model.addAttribute("setId", commandMap.get("setId") == null ? "" : (String) commandMap.get("setId"));

		List<?> surveyBankItemList = SurveyBankManageService.selectSurveyBankList(surveyVO);
		model.addAttribute("resultList", surveyBankItemList);

		int totCnt = SurveyBankManageService.selectSurveyBankListTotCnt(surveyVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/survey/set/QueListPopup";
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
	@RequestMapping(value = "/academy/survey/set/queInsert.do")
	protected String SurveySetQueRegistActor(SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		String sKey = "";
		for (Object key : reqMap.keySet()) {
			sKey = key.toString();
			if (sKey.equals("setId")) {
				SurveyVO.setSetId(Integer.valueOf(request.getParameter(sKey)));
			}
			if (sKey.equals("queId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey)));
			}
		}
		SurveySetManageService.insertSurveySetItem(SurveyVO);

		return "forward:/academy/survey/set/QueListPopup.do";
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
	@RequestMapping(value = "/academy/survey/set/queDelete.do")
	public String qustnrTmplatManageDeleteActor(SurveyVO SurveyVO, ModelMap model, @RequestParam Map<?, ?> reqMap,
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
		SurveyVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		SurveyVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		String sKey = "";
		for (Object key : reqMap.keySet()) {
			sKey = key.toString();
			if (sKey.equals("setId")) {
				SurveyVO.setSetId(Integer.valueOf(request.getParameter(sKey)));
			}
			if (sKey.equals("queId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey)));
			}
		}
		SurveySetManageService.deleteSurveySetItem(SurveyVO);

		return "forward:/academy/survey/set/Detail.do";
	}

}
