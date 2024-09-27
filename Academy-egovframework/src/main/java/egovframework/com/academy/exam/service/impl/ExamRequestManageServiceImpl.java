package egovframework.com.academy.exam.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.academy.exam.service.ExamRequestManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험신청정보관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2021.11.00  			rainend          최초 생성
 * </pre>
 */
@Service("examRequestManageService")
public class ExamRequestManageServiceImpl extends EgovAbstractServiceImpl implements ExamRequestManageService {

	@Resource(name="examRequestManageDAO")
	private ExamRequestManageDAO examRequestManageDAO;

	@Resource(name="examManageDAO")
	private ExamManageDAO examManageDAO;


	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> offererList(ExamVO ExamVO) throws Exception {
		return examRequestManageDAO.offererList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int offererListCount(ExamVO ExamVO) throws Exception {
		return examRequestManageDAO.offererListCount(ExamVO);
	}

    @Override
    public ExamVO offererView(ExamVO ExamVO) throws Exception {
        return examRequestManageDAO.offererView(ExamVO);
    }

    @Override
    public void offererInsert(ExamVO ExamVO) throws Exception {
    	ExamVO exam = examManageDAO.selectExamDetail(ExamVO);
    	
    	ExamVO.setExamStarttime(exam.getExamStarttime());
    	ExamVO.setExamEndtime(exam.getExamEndtime());
    	ExamVO.setExamSparetime(exam.getExamSparetime());
    	
    	examRequestManageDAO.offererInsert(ExamVO);
    }

    @Override
    public void offererUpdate(ExamVO ExamVO) throws Exception {
    	examRequestManageDAO.offererUpdate(ExamVO);
    }

}
