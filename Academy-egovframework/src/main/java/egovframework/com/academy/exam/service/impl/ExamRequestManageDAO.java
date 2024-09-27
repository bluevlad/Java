package egovframework.com.academy.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 시험정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2021.11.00  		rainend          최초 생성
 * </pre>
 */
@Repository("examRequestManageDAO")
public class ExamRequestManageDAO extends EgovComAbstractDAO{

    /**
     * @param ExamRequestVO 검색조건
     * @return List 시험 목록정보
     */

    public List<?> offererList(ExamVO ExamVO){
        return selectList("ExamRequestManage.offererList", ExamVO);
    }

    public int offererListCount(ExamVO ExamVO){
        return (Integer)selectOne("ExamRequestManage.offererListCount", ExamVO);
    }

    public ExamVO offererView(ExamVO ExamVO){
        return (ExamVO) selectOne("ExamRequestManage.offererView", ExamVO);
    }

    public void offererInsert(ExamVO ExamVO) {
        insert("ExamRequestManage.offererInsert", ExamVO);
    }

    public void offererUpdate(ExamVO ExamVO) {
        update("ExamRequestManage.offererUpdate", ExamVO);
    }

}
