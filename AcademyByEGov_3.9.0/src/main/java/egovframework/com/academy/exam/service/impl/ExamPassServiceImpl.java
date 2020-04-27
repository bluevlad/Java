package egovframework.com.academy.exam.service.impl;

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
