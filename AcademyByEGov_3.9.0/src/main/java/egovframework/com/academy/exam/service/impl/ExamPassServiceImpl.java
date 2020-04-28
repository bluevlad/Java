package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamPassService;
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
@Service("examPassService")
public class ExamPassServiceImpl extends EgovAbstractServiceImpl implements ExamPassService {

	@Resource(name="examPassDAO")
	private ExamPassDAO examPassDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamRstList(ExamVO ExamVO) throws Exception {
		return examPassDAO.selectExamRstList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamRstListTotCnt(ExamVO ExamVO) throws Exception {
		return examPassDAO.selectExamRstListTotCnt(ExamVO);
	}

	/**
	 * @param ExamVO 시험 응시정보 등록
	 * @throws Exception
	 */
	@Override
	public void insertExamRst(ExamVO ExamVO) throws Exception {
		examPassDAO.insertExamRst(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시정보 삭제
	 * @throws Exception
	 */
	@Override
	public void deleteExamRst(ExamVO ExamVO) throws Exception {
		examPassDAO.deleteExamRst(ExamVO);
	}

	/**
	 * @param ExamVO 시험 응시정보 등록
	 * @throws Exception
	 */
	@Override
	public void insertExamRstDet(ExamVO ExamVO) throws Exception {
		examPassDAO.insertExamRst(ExamVO);
	}

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시정보 삭제
	 * @throws Exception
	 */
	@Override
	public void deleteExamRstDet(ExamVO ExamVO) throws Exception {
		examPassDAO.deleteExamRst(ExamVO);
	}

}
