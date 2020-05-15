package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamManageService;
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
 * </pre>
 */
@Service("examManageService")
public class ExamManageServiceImpl extends EgovAbstractServiceImpl implements ExamManageService {

	@Resource(name="examManageDAO")
	private ExamManageDAO examManageDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamList(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectExamList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamListTotCnt(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectExamListTotCnt(ExamVO);
	}

	/**
	 * @param examCd 상세조회대상 시험코드
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamDetail(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectExamDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExam(ExamVO ExamVO) throws Exception {
		examManageDAO.insertExam(ExamVO);
	}

	/**
	 * @param ExamVO 시험 등록정보
	 * @throws Exception
	 */
	@Override
	public int insertExamRetcd(ExamVO ExamVO) throws Exception {
		return examManageDAO.insertExamRetcd(ExamVO);
	}

	/**
	 * ExamVO 시험정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateExam(ExamVO ExamVO) throws Exception {
		examManageDAO.updateExam(ExamVO);
	}

	/**
	 * @param subjectSearchVO 검색조건
	 * @return List<ExamVO> 과목 목록정보
	 * @throws Exception
	 */
	public List<ExamVO> selectSubjectList(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectSubjectList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	public int selectSubjectListTotCnt(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectSubjectListTotCnt(ExamVO);
	}

	/**
	 * @param sbjCd 상세조회대상 과목코드
	 * @return ExamVO 과목 상세정보
	 * @throws Exception
	 */
	public ExamVO selectSubjectDetail(ExamVO ExamVO) throws Exception {
		return examManageDAO.selectSubjectDetail(ExamVO);
	}

	/**
	 * @param ExamVO 과목 등록정보
	 * @throws Exception
	 */
	public void insertSubject(ExamVO ExamVO) throws Exception {
		examManageDAO.insertSubject(ExamVO);
	}

	/**
	 * ExamVO 과목정보 수정
	 * @param ExamVO 과목정보
	 * @throws Exception
	 */
	public void updateSubject(ExamVO ExamVO) throws Exception {
		examManageDAO.updateSubject(ExamVO);
	}

}
