package com.academy.exam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
    
	/**
	 * 문제은행 문제 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getExamList")
	public JSONObject listItem(@ModelAttribute("ExamVO") ExamVO examVO, @RequestParam Map<?, ?> commandMap) throws Exception { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
//		String curPage = "1";
//		if(!CommonUtil.empty(commandMap.get("curPage"))){
//			curPage = (String)commandMap.get("curPage");
//		}
//		examVO.setPageIndex(CommonUtil.parseInt(curPage));
		
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
	 * 문제은행 문제 상세정보.
	 * @throws Exception
	 */
	@GetMapping(value = "/api/getExamView")
	public JSONObject getItem(@ModelAttribute("ExamVO") ExamVO examVO) throws Exception, IOException, ParseException { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("examDetail", examService.selectExamDetail(examVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

}