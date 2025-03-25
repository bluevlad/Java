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

    public void insertAnswer(ExamVO examVO) {
    	examMapper.insertAnswer(examVO);
    }

}