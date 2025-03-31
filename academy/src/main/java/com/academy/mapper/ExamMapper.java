package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.exam.service.ExamReqVO;
import com.academy.exam.service.ExamVO;

/**
 * 시험에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.03.01
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
public interface ExamMapper {

	public ArrayList<JSONObject> selectExamList(ExamVO examVO);
	public int selectExamListTotCnt(ExamVO examVO);

	public JSONObject selectExamDetail(ExamVO examVO);
	public ArrayList<JSONObject> selectExamQueList(ExamVO examVO);

	public JSONObject getRequestExamUser(ExamReqVO examReqVO);
	public JSONObject getRequestExam(ExamReqVO examReqVO);
	public ArrayList<JSONObject> selectExamResultList(ExamVO examVO);

	public void insertRequestExam(ExamReqVO examReqVO);
    public void updateRequestExam(ExamReqVO examReqVO);
	
    public void insertExamAnswer(ExamVO examVO);
    public void deleteExamAnswer(ExamVO examVO);

}
