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
import egovframework.com.academy.survey.service.SurveyManageService;
import egovframework.com.academy.survey.service.SurveyRstService;
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
public class SurveyRstController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyRstController.class);

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "SurveyManageService")
	private SurveyManageService SurveyManageService;

	@Resource(name = "surveySetManageService")
	private SurveySetManageService SurveySetManageService;

	@Resource(name = "SurveyRstService")
	private SurveyRstService SurveyRstService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 설문관리 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/academy/survey/mst/Detail";
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/survey/rst/View.do")
	public String egovQustnrManageDetail(
			@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

	
     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

    	SurveyVO resultDetail = SurveyManageService.selectSuveyDetail(SurveyVO);
        model.addAttribute("resultDetail", resultDetail);
        
        SurveyVO.setSetId(resultDetail.getSetId());
        
		List<?> queList = SurveySetManageService.selectSurveySetItemList(SurveyVO);
		model.addAttribute("SurveySetItem", queList);
		
		return "egovframework/com/academy/survey/rst/View";
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
	@RequestMapping(value="/academy/survey/rst/Insert.do")
	public String qustnrManageInsert(
			final HttpServletRequest request, @ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		SurveyVO.setUserId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		String[] v_queids = request.getParameterValues("v_queId");

		if (v_queids != null) {
			SurveyRstService.insertSurveyRst(SurveyVO);
			for (int i = 0; i < v_queids.length; i++) {
				SurveyVO.setQueId(CommonUtil.parseInt(v_queids[i]));
				SurveyVO.setUserAnsw(request.getParameter("userAnsw_" + v_queids[i]));
//				LOGGER.debug("queId : " + SurveyVO.getQueId());
//				LOGGER.debug("userAnsw : " + SurveyVO.getUserAnsw());
				SurveyRstService.insertSurveyRstItem(SurveyVO);
			}
		}

		return "redirect:/academy/survey/rst/View.do?surveyId="+SurveyVO.getSurveyId();
	}

}