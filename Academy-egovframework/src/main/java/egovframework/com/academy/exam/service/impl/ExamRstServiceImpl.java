package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamRstService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험(과목)관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.04.00  			rainend          최초 생성
 *   2020.04.27  		rainend          답안지 채점
 * </pre>
 */
@Service("examRstService")
public class ExamRstServiceImpl extends EgovAbstractServiceImpl implements ExamRstService {

	@Resource(name="examRstDAO")
	private ExamRstDAO examRstDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamRstList(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamRstListTotCnt(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstListTotCnt(ExamVO);
	}

	/**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamRstDetail(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험 응시정보 등록
	 * @throws Exception
	 */
	@Override
	public void insertExamRst(ExamVO ExamVO) throws Exception {
		examRstDAO.insertExamRst(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시정보 삭제
	 * @throws Exception
	 */
	@Override
	public void deleteExamRst(ExamVO ExamVO) throws Exception {
		examRstDAO.deleteExamRst(ExamVO);
	}
	/**
	 * @param ExamVO 검색조건
	 * @return List<?> 시시험 응시 과목목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamRstSbjList(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstSbjList(ExamVO);
	}

	/**
	 * @param ExamVO 검색조건
	 * @return 시험 응시과목 총갯수
	 * @throws Exception
	 */
	@Override
	public int selectExamRstSbjListTotCnt(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstSbjListTotCnt(ExamVO);
	}

	/**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return ExamVO 시험 과목 선택 정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamRstSbjDetail(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstSbjDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험 응시과목 정보 등록
	 * @throws Exception
	 */
	@Override
	public void insertExamRstSbj(ExamVO ExamVO) throws Exception {
		examRstDAO.insertExamRstSbj(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시과목 점수 등록
	 * @throws Exception
	 */
	@Override
	public void updateExamRstSbj(ExamVO ExamVO) throws Exception {
		examRstDAO.updateExamRstSbj(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시과목 정보삭제
	 * @throws Exception
	 */
	@Override
	public void deleteExamRstSbj(ExamVO ExamVO) throws Exception {
		examRstDAO.deleteExamRstSbj(ExamVO);
	}

	/**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return List 시시험 응시과목 채점정보
	 * @throws Exception
	 */
	@Override
	public List<ExamVO> selectExamRstDetList(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstDetList(ExamVO);
	}

	/**
	 * @param ExamVO 시험 응시정보 등록
	 * @throws Exception
	 */
	@Override
	public void insertExamRstDet(ExamVO ExamVO) throws Exception {
		examRstDAO.insertExamRstDet(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시정보 삭제
	 * @throws Exception
	 */
	@Override
	public void deleteExamRstDet(ExamVO ExamVO) throws Exception {
		examRstDAO.deleteExamRstDet(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public String selectExamRstDetYN(ExamVO ExamVO) throws Exception {
		return examRstDAO.selectExamRstDetYN(ExamVO);
	}

}
