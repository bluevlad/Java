package com.academy.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import com.academy.exam.service.ExamService;
import com.academy.exam.service.ExamVO;

@RestController
@RequestMapping("/api/exam")
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
	@GetMapping(value = "/getExamList")
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
	@GetMapping(value = "/getExamView")
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
	@PostMapping(value="/insertExamResult")
	public JSONObject insertExamResult(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
				examVO.setExamId(CommonUtil.parseInt(commandMap.get("examId")));
			    String userId = String.valueOf(commandMap.get("userId"));
			    examVO.setuUerId(userId);
			    examVO.setIdentyId(userId);
			    examVO.setRegId(userId);
			    examVO.setUpdId(userId);
				
			 	ArrayList<JSONObject> QueList = examService.selectExamQueList(examVO);
			 	
			 	HashMap<String, String> answersMap = new HashMap<String, String>();
			 	
		        for (int i = 0; i < QueList.size() ; i++) {
		        	JSONObject QueItem = new JSONObject(QueList.get(i));
		        	String queId = QueItem.get("que_id").toString();  // 문제 ID 가져오기
		        	String userAnswer = answersMap.get("answers[" + queId + "]"); // 사용자가 선택한 답안 가져오기
		        	
		            examVO.setQueId(CommonUtil.parseInt(queId));
		            examVO.setAnswer(userAnswer);
					
		            if (userAnswer != null && userAnswer.equals(QueItem.get("answer").toString())) {
		                examVO.setCorrectYn("Y");
		            } else {
		                examVO.setCorrectYn("N");
		            }
					examService.insertAnswer(examVO);
		        }
			jsonObject.put("retMsg", "제출완료");
		} catch (Exception e){
			jsonObject.put("retMsg", "응시실패");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);
        
		return jObject;
	}

}