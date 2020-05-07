package egovframework.com.academy.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

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
@Repository("examStatDAO")
public class ExamStatDAO extends EgovComAbstractDAO{

    /**
     * @param ExamVO 검색조건
     * @return List 시험 목록정보
     */
    public List<?> selectExamStatSbjList(ExamVO ExamVO) throws Exception{
		return selectList("ExamStat.selectExamStatSbjList", ExamVO);
    }

}
