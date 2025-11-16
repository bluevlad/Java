package com.academy.exam.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.ExamBankMapper;

@Service
public class ExamBankService {

	private ExamBankMapper examBankMapper;
	
	public ExamBankService(ExamBankMapper examBankMapper) {
		this.examBankMapper = examBankMapper;
	}
	
	public ArrayList<JSONObject> selectExamBankItemlList(ExamVO examVO) {
		return examBankMapper.selectExamBankItemlList(examVO);
	}
    public int selectExamBankItemListTotCnt(ExamVO examVO) {
        return examBankMapper.selectExamBankItemListTotCnt(examVO);
    }
	
	public JSONObject selectExamBankItemDetail(ExamVO examVO) {
		return examBankMapper.selectExamBankItemDetail(examVO);
	}

    public void insertExamBankItem(ExamVO examVO) {
    	examBankMapper.insertExamBankItem(examVO);
    }

    public void updateExamBankItem(ExamVO examVO) {
    	examBankMapper.updateExamBankItem(examVO);
    }
    
    public ArrayList<JSONObject> selectExamBankList(ExamVO examVO) {
		return examBankMapper.selectExamBankList(examVO);
    }

    public int selectExamBankListTotCnt(ExamVO examVO) {
        return examBankMapper.selectExamBankListTotCnt(examVO);
    }
    
	public JSONObject selectExamBankDetail(ExamVO examVO) {
		return examBankMapper.selectExamBankDetail(examVO);
	}

    public void insertExamBank(ExamVO examVO) {
    	examBankMapper.insertExamBank(examVO);
    }

    public void updateExamBank(ExamVO examVO) {
    	examBankMapper.updateExamBank(examVO);
    }

}