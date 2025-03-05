package com.academy.exam;

import java.io.IOException;
import java.util.HashMap;
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

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import com.academy.exam.service.ExamService;
import com.academy.exam.service.ExamVO;

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
	 * 문제은행 문제 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getExamBankItemList")
	public JSONObject listItem(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap) throws Exception { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		String curPage = "1";
		if(!CommonUtil.empty(commandMap.get("curPage"))){
			curPage = (String)commandMap.get("curPage");
		}
		examVO.setPageIndex(CommonUtil.parseInt(curPage));
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("exambankItemList", examService.selectExamBankItemlList(examVO));

		int totCnt = examService.selectExamBankItemListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		jsonObject.put("paginationInfo", paginationInfo);
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 문제은행 문제 상세정보.
	 * @throws Exception
	 */
	@GetMapping(value = "/api/getExamBankItem")
	public JSONObject getItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception, IOException, ParseException { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("examBankItem", examService.selectExamBankItemDetail(examVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

	/**
	 * 문제은행 문제 신규로 등록한다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping(value="/api/insertExamBankItem")
	public JSONObject insertItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			examService.insertExamBankItem(examVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 문제은행 문제 정보를 변경한다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping(value="/api/examBankItemUpdate")
	public JSONObject updateItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {

		// 0. Spring Security 사용자권한 처리
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			examService.updateExamBankItem(examVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
	
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 문제은행 정보를 가져온다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping(value = "/api/selectExamBank")
	public JSONObject list(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		String curPage = "1";
		if(!CommonUtil.empty(commandMap.get("curPage"))){
			curPage = (String)commandMap.get("curPage");
		}
		examVO.setPageIndex(CommonUtil.parseInt(curPage));
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("exambankList", examService.selectExamBankList(examVO));

		int totCnt = examService.selectExamBankListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		jsonObject.put("paginationInfo", paginationInfo);
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}


	/**
	 * 문제은행 상세 정보를 가져온다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping("/api/getExamBank")
	public JSONObject get(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("examBank", examService.selectExamBankDetail(examVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

	/**
	 * 문제은행 정보를 등록온다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping(value = "/api/inserExamBank")
	@Transactional( readOnly=false, rollbackFor=Exception.class)
	public JSONObject insert(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			examService.insertExamBank(examVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}


	/**
	 * 문제은행 정보를 변경한다.
	 * @param ExamVO
	 * @throws Exception
	 */
	@GetMapping(value = "/api/updateExamBank")
	@Transactional(readOnly=false,  rollbackFor=Exception.class)
	public JSONObject update(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			examService.updateExamBank(examVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

}