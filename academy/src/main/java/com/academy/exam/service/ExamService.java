package com.academy.exam.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.ExamMapper;

@Service
public class ExamService {

	private ExamMapper examMapper;
	
	public ExamService(ExamMapper examMapper) {
		this.examMapper = examMapper;
	}
	
	public ArrayList<JSONObject> selectExamList(ExamVO examVO) {
		return examMapper.selectExamList(examVO);
	}
    public int selectExamListTotCnt(ExamVO examVO) {
        return examMapper.selectExamListTotCnt(examVO);
    }
	
	public JSONObject selectExamDetail(ExamVO examVO) {
		return examMapper.selectExamDetail(examVO);
	}
	
	public ArrayList<JSONObject> selectExamQueList(ExamVO examVO) {
		return examMapper.selectExamQueList(examVO);
	}
	
	public JSONObject getRequestExam(ExamVO examVO) {
		return examMapper.getRequestExam(examVO);
	}
    public void insertExamAnswer(ExamVO examVO) {
    	examMapper.insertExamAnswer(examVO);
    }
    public void insertRequestExam(ExamReqVO examReqVO) {
    	examMapper.insertRequestExam(examReqVO);
    }
    public void updateRequestExam(ExamReqVO examReqVO) {
    	examMapper.updateRequestExam(examReqVO);
    }

}