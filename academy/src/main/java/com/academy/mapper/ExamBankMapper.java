package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.exam.service.ExamVO;

/**
 * 시험문제은행에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.02.01  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface ExamBankMapper {

	public ArrayList<JSONObject> selectExamBankItemlList(ExamVO examVO);
	public int selectExamBankItemListTotCnt(ExamVO examVO);

	public JSONObject selectExamBankItemDetail(ExamVO examVO);

    public void insertExamBankItem(ExamVO examVO);

    public void updateExamBankItem(ExamVO examVO);

	public ArrayList<JSONObject> selectExamBankList(ExamVO examVO);
    public int selectExamBankListTotCnt(ExamVO examVO);

	public JSONObject selectExamBankDetail(ExamVO examVO);

    public void insertExamBank(ExamVO examVO);

    public void updateExamBank(ExamVO examVO);

}
