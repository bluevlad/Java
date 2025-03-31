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
	
	public JSONObject getRequestExamUser(ExamReqVO examReqVO) {
		return examMapper.getRequestExamUser(examReqVO);
	}
	public JSONObject getRequestExam(ExamReqVO examReqVO) {
		return examMapper.getRequestExam(examReqVO);
	}
	public ArrayList<JSONObject> selectExamResultList(ExamVO examVO) {
		return examMapper.selectExamResultList(examVO);
	}
    public void insertRequestExam(ExamReqVO examReqVO) {
    	examMapper.insertRequestExam(examReqVO);
    }
    public void updateRequestExam(ExamReqVO examReqVO) {
    	examMapper.updateRequestExam(examReqVO);
    }

    public void insertExamAnswer(ExamVO examVO) {
    	examMapper.insertExamAnswer(examVO);
    }
    public void deleteExamAnswer(ExamVO examVO) {
    	examMapper.deleteExamAnswer(examVO);
    }
    
}