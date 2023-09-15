package egovframework.com.academy.survey.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.academy.survey.service.SurveyManageService;
import egovframework.com.academy.survey.service.SurveySetManageService;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 설문항목관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class SurveyMstManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyMstManageController.class);


	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "SurveyManageService")
	private SurveyManageService SurveyManageService;

	@Resource(name = "surveySetManageService")
	private SurveySetManageService SurveySetManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 설문관리 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param model
	 * @return  "egovframework/com/academy/survey/mst/List"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/List.do")
	public String egovQustnrManageList(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

    	/** EgovPropertyService.sample */
		SurveyVO.setPageUnit(propertiesService.getInt("pageUnit"));
		SurveyVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(SurveyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(SurveyVO.getPageUnit());
		paginationInfo.setPageSize(SurveyVO.getPageSize());

		SurveyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		SurveyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		SurveyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = SurveyManageService.selectSurveyList(SurveyVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = SurveyManageService.selectSurveyListCnt(SurveyVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/survey/mst/List";
	}

	/**
	 * 설문관리 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/Detail";
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/Detail.do")
	public String egovQustnrManageDetail(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        model.addAttribute("surveyId", commandMap.get("surveyId") == null ? "" : (String)commandMap.get("surveyId"));
        SurveyVO.setSurveyId(CommonUtil.parseInt((String) commandMap.get("surveyId")));

    	SurveyVO resultDetail = SurveyManageService.selectSuveyDetail(SurveyVO);
        model.addAttribute("surveyVO", resultDetail);
        
		SurveyVO.setFirstIndex(0);
		SurveyVO.setRecordCountPerPage(100);

        List<?> sampleList = SurveySetManageService.selectSurveySetList(SurveyVO);
        model.addAttribute("resultList", sampleList);

		return "egovframework/com/academy/survey/mst/Detail";
	}

	/**
	 * 설문관리를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/Modify"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/Modify.do")
	public String qustnrManageModify(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

		//아이디 설정
		SurveyVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		SurveyVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		SurveyManageService.updateSurvey(SurveyVO);

		return "redirect:/academy/survey/mst/Detail.do?surveyId="+SurveyVO.getSurveyId();
	}

	/**
	 * 설문관리를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/EgovQustnrManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/Regist.do")
	public String qustnrManageRegist(
			@ModelAttribute("surveyVO") SurveyVO surveyVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);
        
    	surveyVO.setFirstIndex(0);
    	surveyVO.setRecordCountPerPage(100);

        List<?> sampleList = SurveySetManageService.selectSurveySetList(surveyVO);
        model.addAttribute("resultList", sampleList);

		return "egovframework/com/academy/survey/mst/Regist";
	}

	/**
	 * 설문관리를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/EgovQustnrManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/Insert.do")
	public String qustnrManageInsert(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		SurveyVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		SurveyVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		SurveyManageService.insertSurvey(SurveyVO);

		return "redirect:/academy/survey/mst/Detail.do?surveyId="+SurveyVO.getSurveyId();
	}

	/**
	 * 설문관리 팝업 목록을 조회한다.
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/ListUserPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/ListUserPopup.do")
	public String egovQustnrManageListPopup(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, ModelMap model) throws Exception {

        List<?> sampleList = SurveyManageService.selectSurveyUserList(SurveyVO);
        model.addAttribute("resultList", sampleList);

		return "egovframework/com/academy/survey/mst/ListUserPopup";
	}

	/**
	 * 설문관리 팝업 목록을 조회한다.
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/ListBankPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/ListBankPopup.do")
	public String ListBankPopup(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, ModelMap model) throws Exception {

        List<?> sampleList = SurveyManageService.selectSurveyBankList(SurveyVO);
        model.addAttribute("resultList", sampleList);

		return "egovframework/com/academy/survey/mst/ListBankPopup";
	}

	/**
	 * 설문관리 팝업 목록을 조회한다.
	 * @param commandMap
	 * @param SurveyVO
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/ListStatPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/mst/ListStatPopup.do")
	public String ListStatPopup(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, ModelMap model) throws Exception {

    	List<?> sampleList = SurveyManageService.selectSurveyRstList(SurveyVO);
    	model.addAttribute("resultList", sampleList);

		return "egovframework/com/academy/survey/mst/ListStatPopup";
	}

}