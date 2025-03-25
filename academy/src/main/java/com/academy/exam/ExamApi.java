package com.academy.exam;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.exam.service.ExamBankVO;
import com.academy.exam.service.ExamService;
import com.academy.exam.service.ExamVO;

@RestController
public class ExamApi extends CORSFilter {

    private ExamService examService;

    public ExamApi(ExamService examService) {
        this.examService = examService;
    }
    
	/**
	 * 시험 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getExamList")
	public JSONObject listItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(examVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
		paginationInfo.setPageSize(examVO.getPageSize());

		examVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		examVO.setLastIndex(paginationInfo.getLastRecordIndex());
		examVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("examList", examService.selectExamList(examVO));

		int totCnt = examService.selectExamListTotCnt(examVO);
		paginationInfo.setTotalRecordCount(totCnt);
		jsonObject.put("paginationInfo", paginationInfo);
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 시험 상세정보.
	 * @throws Exception
	 */
	@GetMapping(value = "/api/getExamView")
	public JSONObject getItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("examDetail", examService.selectExamDetail(examVO));
		jsonObject.put("QueList", examService.selectExamQueList(examVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}
	
	/**
	 * @Method Name : insertExamResult
	 * @작성일 : 2025. 03.
	 * @Method 설명 : 시험 응시
	 * @throws Exception
	 */
	@PostMapping(value="/api/insertExamResult")
	public JSONObject insertExamResult(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();


		try {

			examVO.setAnswer(commandMap.get("que_id_").toString());

			/*
			 * int QUECOUNT =
			 * Integer.parseInt(String.valueOf(surveyList.get(i).get("QUECOUNT")));
			 * 
			 * for(int j=1; j<=QUECOUNT;j++){ params.put("QSEQ", String.valueOf(j));
			 * params.put("USER_ANSW",
			 * CommonUtil.isNull(request.getParameter("ex_"+QUETYPE+"_"+QUEID+"_"+j),""));
			 * serveyService.insertSurveyRstItem(params); }
			 */				 
			examService.insertAnswer(examVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);
        
		return jObject;
	}

}