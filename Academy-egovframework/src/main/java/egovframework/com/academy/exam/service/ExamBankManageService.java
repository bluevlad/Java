package egovframework.com.academy.exam.service;

import java.io.InputStream;
import java.util.List;

import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;

public interface ExamBankManageService {
	
	/**
	 * 시험문제 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험문제 목록
	 */
	public List<?> selectExamBankList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamBankListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public ExamVO selectExamBankDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertExamBank(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateExamBank(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험문제 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	public List<?> selectExamBankItemlList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamBankItemListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param ExamMst
	 * @return ExamMst
	 */
	public ExamVO selectExamBankItemDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExamBankItem(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateExamBankItem(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<ExamVO> selectExamBankListExcel(InputStream inputStream)throws Exception;
	
}
