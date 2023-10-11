package egovframework.com.academy.box.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
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
public class BoxManageController {

	@Resource(name = "boxManageService")
	private BoxManageService boxManageService;

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
	@RequestMapping(value = "/academy/box/List.do")
	public String List(@ModelAttribute("BoxVO") BoxVO BoxVO, ModelMap model) throws Exception {

		BoxVO.setPageUnit(propertyService.getInt("pageUnit"));
		BoxVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(BoxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(BoxVO.getPageUnit());
		paginationInfo.setPageSize(BoxVO.getPageSize());

		BoxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		BoxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		BoxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("resultList", boxManageService.selectBoxList(BoxVO));

		int totCnt = boxManageService.selectBoxListTotCnt(BoxVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/box/List";
	}

	/**
	 * 사물함 등록화면.
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Regist.do")
	public String Regist(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
		return "egovframework/com/academy/box/Regist";
	}

	/**
	 * 사물함 상세조회 조회한다.
	 * @param boxCd
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Detail.do")
	public String Detail(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
        model.addAttribute("boxCd", commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd"));
        BoxVO.setBoxCd((String) commandMap.get("boxCd"));

        model.addAttribute("BoxVO", boxManageService.selectBoxDetail(BoxVO));
		model.addAttribute("boxnumList", boxManageService.selectBoxNumList(BoxVO));

		return "egovframework/com/academy/box/Detail";
	}

	/**
	 * 사물함정보를 신규로 등록한다.
	 * @param BoxVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/box/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Insert.do")
	public String Insert(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		boxManageService.insertBox(BoxVO);

		int boxCount = CommonUtil.parseInt(commandMap.get("boxCount").toString());	// 사물함 총 갯수
		int startNum = CommonUtil.parseInt(commandMap.get("startNum").toString());	// 시작번호
		int endNum = CommonUtil.parseInt(commandMap.get("endNum").toString());	// 종료번호

		if (boxCount > 0){
			for( int i = startNum; i <= endNum; i++ ) {
				BoxVO.setBoxNum(i);
				boxManageService.insertBoxNum(BoxVO);
			}
		}

		return "redirect:/academy/box/List.do";
	}

	/**
	 * 사물함정보를 변경한다.
	 * @param BoxVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/box/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Update.do")
	public String Update(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		boxManageService.updateBox(BoxVO);

		return "redirect:/academy/box/List.do";
	}

	/**
	 * @Method Name : boxRentWrite
	 * @작성일 : 2023. 09
	 * @Method 설명 : 사물함 대여 신청 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/box/RentWrite.do")
	public String boxRentWrite(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		// 사물함 BOX_CD로 상세정보를 가져온다.
        model.addAttribute("rentSeq", commandMap.get("rentSeq") == null ? "" : (String)commandMap.get("rentSeq"));
		BoxVO.setRentSeq(CommonUtil.parseInt(commandMap.get("rentSeq").toString()));

		BoxVO boxNumRentDetail = null;
		BoxVO boxNumRentOrderDetail = null;
		if (!commandMap.get("rentSeq").toString().isEmpty()) {
			// 사물함 대여 신청 정보(현재)
			boxNumRentDetail = boxManageService.selectBoxNumRentDetail(BoxVO);
			
			// 사물함 대여 현재 결제 정보를 가져온다
			if (boxNumRentDetail != null){
				BoxVO.setOrderno(boxNumRentDetail.getOrderno());
				boxNumRentOrderDetail = boxManageService.selectBoxNumRentOrderDetail(BoxVO);
			}
		}

		if (boxNumRentOrderDetail != null){
			model.addAttribute("WMODE", "EDT");
		} else {
			model.addAttribute("WMODE", "INS");
		}

		// 사물함 대여 결제 이력들
		model.addAttribute("boxNumRentOrderList", boxManageService.selectBoxNumRentOrderList(BoxVO));

        model.addAttribute("BoxVO", boxManageService.selectBoxDetail(BoxVO));
		model.addAttribute("boxNumRentDetail", boxNumRentDetail);
		model.addAttribute("boxNumRentOrderDetail", boxNumRentOrderDetail);

		return "egovframework/com/academy/box/RentWrite";
	}

}
