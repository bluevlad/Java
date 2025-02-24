package com.academy.exam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import com.academy.exam.service.ExamService;
import com.academy.exam.service.ExamVO;
import com.academy.locker.service.LockerVO;

@RestController
public class ExamApi extends CORSFilter {

    private ExamService examService;

    public ExamApi(ExamService examService) {
        this.examService = examService;
    }
    
	// 0. Spring Security 사용자권한 처리 - 추후 해당 부분 모두 추가
	/*
	 * Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	 * if(!isAuthenticated) { model.addAttribute("message",
	 * egovMessageSource.getMessage("fail.common.login")); return
	 * "egovframework/com/uat/uia/EgovLoginUsr"; }
	 * 
	 * //로그인 객체 선언 LoginVO loginVO =
	 * (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	 * 
	 * //아이디 설정 lockerVO.setRegId(loginVO == null ? "" :
	 * EgovStringUtil.isNullToString(loginVO.getUniqId())); lockerVO.setUpdId(loginVO
	 * == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	 */
    
	/**
	 * 사물함 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getExamBankItemList")
	public ArrayList<JSONObject> list(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception, IOException, ParseException { 
		
		JSONObject jsonObject = new JSONObject();
		ExamVO examVO = new ExamVO();
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("lockers", examService.selectExamBankItemlList(examVO));

		int totCnt = examService.selectExamBankItemListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return examService.selectExamBankItemlList(examVO);
	}

	/**
	 * 사물함 등록화면.
	 * @throws Exception
	 */
	@GetMapping(value = "/api/getExamBankItem")
	public JSONObject get(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception, IOException, ParseException { 

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("item", examService.selectExamBankItemDetail(examVO));

		return jsonObject;
	}

	/**
	 * 사물함정보를 신규로 등록한다.
	 * @param lockerVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@GetMapping(value="/api/examBankItemInsert")
	public String Insert(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
		
		examService.insertExamBankItem(examVO);

		return "redirect:/academy/box/List.do";
	}

	/**
	 * 사물함정보를 변경한다.
	 * @param ExamVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@GetMapping(value="/api/examBankItemUpdate")
	public String Update(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {

		// 0. Spring Security 사용자권한 처리

		examService.updateExamBankItem(examVO);

		return "OK";
	}

	/**
	 * @Method Name : ExtendOrder
	 * @작성일 : 2023. 09
	 * @Method 설명 : 사물함 대여 연장 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/selectExamBank")
	public ArrayList<JSONObject> ExtendOrder(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		
		JSONObject jsonObject = new JSONObject();
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("lockers", examService.selectExamBankList(examVO));

		int totCnt = examService.selectExamBankListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return examService.selectExamBankList(examVO);
	}


	/**
	 * @Method Name : boxChangePopProcess
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 사물함 변경 처리
     * - TB_OFF_BOX_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
     * - TB_OFF_BOX_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
     * - TB_OFF_BOX_RENT 테이블을 업데이트한다. (기존 자료에 신규 사물함번호를 저장한다)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping("/api/getExamBank")
	public ModelAndView changePop(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap) throws Exception {

    	ModelAndView ret = new ModelAndView("jsonView");

		JSONObject boxNumChange = examService.selectExamBankDetail(examVO);

		ret.addObject("ret", "OK");
		return ret;
	}

	/**
	 * @Method Name : DeleteOrder
	 * @작성일 : 2023. 11.
	 * @Method 설명 : 사물함 대여 주문정보 삭제 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/inserExamBank")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String DeleteOrder(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {

		// 0. Spring Security 사용자권한 처리

		examService.insertExamBank(examVO);

		return "OK";

	}


	/**
	 * @Method Name : boxRefundProcess
	 * @작성일 : 2023. 11.
	 * @Method 설명 : 사물함 대여 환불 처리
     * - ACM_ORDER_REFUND 테이블에 환불 정보를 인서트한다.(환불금액, 환불일, 유저아이디)
     * - ACM_ORDER_ITEM 테이블에 환불 정보를 인서트한다.
     * - ACM_ORDER_APPROVALS 테이블의 REFUND_PRICE, REFUND_DATE 항목에 환불금액을 저장한다.
     * - ACM_BOX_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 사물함사용상태: 미사용)
     * - ACM_BOX_NUM 사물함 상세정보의 BOX_FLAG = N, USERID = "" 초기화한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/updateExamBank")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxRefund(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {

		// 0. Spring Security 사용자권한 처리
		
		// 1. 환불 테이블(TB_REFUND)에 삽입한다.
		examService.updateExamBank(examVO); //테스트중

		return "OK";
	}

}