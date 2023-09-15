package egovframework.com.academy.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamStatService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 채점결과 정보에 관한 통계정보 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2020.05.07  		rainend          최초 생성
 * </pre>
 */
@Service("examStatService")
public class ExamStatServiceImpl extends EgovAbstractServiceImpl implements ExamStatService {

	@Resource(name="examStatDAO")
	private ExamStatDAO examStatDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamStatSbjList(ExamVO ExamVO) throws Exception {
		return examStatDAO.selectExamStatSbjList(ExamVO);
	}

}
