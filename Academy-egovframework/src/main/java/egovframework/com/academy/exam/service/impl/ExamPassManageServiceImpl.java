package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamPassManageService;
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
@Service("examPassManageService")
public class ExamPassManageServiceImpl extends EgovAbstractServiceImpl implements ExamPassManageService {

	@Resource(name="examPassManageDAO")
	private ExamPassManageDAO examPassManageDAO;


	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamPassList(ExamVO ExamVO) throws Exception {
		return examPassManageDAO.selectExamPassList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamPassListTotCnt(ExamVO ExamVO) throws Exception {
		return examPassManageDAO.selectExamPassListTotCnt(ExamVO);
	}

	/**
	 * @param examCd 상세조회대상 시험코드
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public List<ExamVO> selectExamPassDetail(ExamVO ExamVO) throws Exception {
		return examPassManageDAO.selectExamPassDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExamPass(ExamVO ExamVO) throws Exception {
		examPassManageDAO.insertExamPass(ExamVO);
	}

	/**
	 * ExamVO 시험정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void deleteExamPass(ExamVO ExamVO) throws Exception {
		examPassManageDAO.deleteExamPass(ExamVO);
	}

}
