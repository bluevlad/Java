package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamSetManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험문제관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2023.02.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.02.00  			rainend          최초 생성
 * </pre>
 */
@Service("examSetManageService")
public class ExamSetManageServiceImpl extends EgovAbstractServiceImpl implements ExamSetManageService {

	@Resource(name="examSetManageDAO")
	private ExamSetManageDAO examSetManageDAO;

	/**
	 * @param ExamVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamSetList(ExamVO ExamVO) throws Exception {
		return examSetManageDAO.selectExamSetList(ExamVO);
	}

	/**
	 * @param ExamVO 검색조건
	 * @return 총시험문제 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamSetListTotCnt(ExamVO ExamVO) throws Exception {
		return examSetManageDAO.selectExamSetListTotCnt(ExamVO);
	}

	/**
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamSetDetail(ExamVO ExamVO) throws Exception {
		return examSetManageDAO.selectExamSetDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExamSet(ExamVO ExamVO) throws Exception {
		examSetManageDAO.insertExamSet(ExamVO);
	}

	/**
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateExamSet(ExamVO ExamVO) throws Exception {
		examSetManageDAO.updateExamSet(ExamVO);
		
	}

	/**
	 * @param ExamVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamSetItemList(ExamVO ExamVO) throws Exception {
		return examSetManageDAO.selectExamSetItemList(ExamVO);
	}

	/**
	 * @param ExamVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExamSetItem(ExamVO ExamVO) throws Exception {
		examSetManageDAO.insertExamSetItem(ExamVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void deleteExamSetItem(ExamVO ExamVO) throws Exception {
		examSetManageDAO.deleteExamSetItem(ExamVO);
	}

	/**
	 * @param ExamVO 검색조건
	 * @return 시험문제세트 순서 max
	 * @throws Exception
	 */
	@Override
	public int selectExamSetQueSeq(ExamVO ExamVO) throws Exception {
		return examSetManageDAO.selectExamSetQueSeq(ExamVO);
	}

}
