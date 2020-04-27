package egovframework.com.academy.exam.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 시험정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2020.04.00  		rainend          최초 생성
 *   2020.04.27  		rainend          답안지 채점
 * </pre>
 */
@Repository("examPassDAO")
public class ExamPassDAO extends EgovComAbstractDAO{

    /**
     * @param ExamVO 시험 응시정보 등록
     */
    public void insertExamRst(ExamVO ExamVO) throws Exception{
        insert("ExamPass.insertExamRst", ExamVO);
    }

    /**
     * @param ExamVO 시험 응시정보삭제
     */
    public void deleteExamRst(ExamVO ExamVO) throws Exception{
        delete("ExamPass.deleteExamRst", ExamVO);
    }

    /**
     * @param ExamVO 시험 채점정보 등록
     */
    public void insertExamRstDet(ExamVO ExamVO) throws Exception{
        insert("ExamPass.insertExamRstDet", ExamVO);
    }

    /**
     * @param ExamVO 시험 채점정보삭제
     */
    public void deleteExamRstDet(ExamVO ExamVO) throws Exception{
        delete("ExamPass.deleteExamRstDet", ExamVO);
    }

}
