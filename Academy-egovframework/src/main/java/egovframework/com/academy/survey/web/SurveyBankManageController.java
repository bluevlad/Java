package egovframework.com.academy.survey.web;

import java.util.HashMap;
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

import egovframework.com.academy.survey.service.SurveyBankManageService;
import egovframework.com.academy.survey.service.SurveyManageService;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 설문문항을 처리하는 Controller Class 구현
 * @author 공통서비스 YK.KIM
 * @since  2023.03.03
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.8.26	정진오	   IncludedInfo annotation 추가
 *   2017.09.04  김예영          표준프레임워크 v3.7 개선
 *   2023.03.03  YK.KIM    설문문항 관리
 *
 * </pre>
 */

@Controller
public class SurveyBankManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyBankManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "surveyBankManageService")
	private SurveyBankManageService surveyBankManageService;

	@Resource(name = "SurveyManageService")
	private SurveyManageService SurveyManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 설문문항 목록을 조회한다.
	 * @param SurveyVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/academy/survey/bank/List"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/bank/List.do")
	public String SurveyBankManageList(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

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

        /** 2017.09.04 model에 addAttribute 추가 */
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));
        
		List<?> surveyBankItemList = surveyBankManageService.selectSurveyBankList(SurveyVO);
        model.addAttribute("resultList", surveyBankItemList);

        int totCnt = surveyBankManageService.selectSurveyBankListTotCnt(SurveyVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/academy/survey/bank/List";
	}

	/**
	 * 설문문항을 새로 등록한다
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/academy/survey/bank/Add"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/bank/Add.do")
	public String SurveyBankManageAdd(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);
    	
		return "egovframework/com/academy/survey/bank/Add";
	}

	/**
	 * 설문문항 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/academy/survey/bank/Detail"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/bank/Detail.do")
	public String SurveyBankManageDetail(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);
    	
    	SurveyVO sampleList = surveyBankManageService.selectSurveyBankDetail(SurveyVO);
        model.addAttribute("resultDetail", sampleList);

		return "egovframework/com/academy/survey/bank/Detail";
	}

	/**
	 * 설문문항를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/survey/bank/Modify"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/bank/Modify.do")
	public String SurveyBankManageModify(@ModelAttribute("surveyMst") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String sLocationUrl = "egovframework/com/academy/survey/bank/Detail";

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);

		//서버  validate 체크
        beanValidator.validate(SurveyVO, bindingResult);
		if (bindingResult.hasErrors()){
        	return sLocationUrl;
		}

		//아이디 설정
		SurveyVO.setQueOwner(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		surveyBankManageService.updateSurveyBank(SurveyVO);

		return "redirect:/academy/survey/bank/Detail.do?" + "&queId=" + SurveyVO.getQueId();
	}

	/**
	 * 설문문항를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/survey/bank/Regist"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/bank/Regist.do")
	public String SurveyBankManageRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap, @ModelAttribute("SurveyVO") SurveyVO SurveyVO,
			BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);

		//서버  validate 체크
        beanValidator.validate(SurveyVO, bindingResult);
		if (bindingResult.hasErrors()){
			return "egovframework/com/academy/survey/bank/Detail";
		}

		surveyBankManageService.insertSurveyBank(SurveyVO);

		String sLocationUrl = "redirect:/academy/survey/bank/List.do?";

		return sLocationUrl;
	}

    /**
     * 설문문항 통계를 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "egovframework/com/academy/survey/bank/Statistics"
     * @throws Exception
     */
    @RequestMapping(value="/academy/survey/bank/Statistics.do")
	public String SurveyBankManageStatistics(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);
    	
    	SurveyVO sampleList = surveyBankManageService.selectSurveyBankDetail(SurveyVO);
        model.addAttribute("resultDetail", sampleList);

    	List<?> statisticsList = SurveyManageService.selectSurveyRstList(SurveyVO);
    	model.addAttribute("statisticsList", statisticsList);

    	List<?> answerList = SurveyManageService.selectSurveyAnswerList(SurveyVO);
    	model.addAttribute("answerList", answerList);
        
	    return "egovframework/com/academy/survey/bank/Statistics";
	}

}
