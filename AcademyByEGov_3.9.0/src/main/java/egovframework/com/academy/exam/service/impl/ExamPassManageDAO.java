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
 *   2020.04.00  		rainend          최초 생성
 * </pre>
 */
@Repository("examPassManageDAO")
public class ExamPassManageDAO extends EgovComAbstractDAO{

    /**
     * @param ExamVO 검색조건
     * @return List 시험 목록정보
     */
    public List<?> selectExamPassList(ExamVO ExamVO) throws Exception{
		return selectList("ExamPassManage.selectExamPassList", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return int 시험 총갯수
     */
    public int selectExamPassListTotCnt(ExamVO ExamVO) {
        return (Integer)selectOne("ExamPassManage.selectExamPassListTotCnt", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @return ExamVO시험별 답안지 정보
     */
    public List<ExamVO> selectExamPassDetail(ExamVO ExamVO){
        return selectList("ExamPassManage.selectExamPassDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험 답안지정보 등록
     */
    public void insertExamPass(ExamVO ExamVO) throws Exception{
        insert("ExamPassManage.insertExamPass", ExamVO);
    }

    /**
     * @param ExamVO 시험 답안지정보삭제
     */
    public void deleteExamPass(ExamVO ExamVO) throws Exception{
        delete("ExamPassManage.deleteExamPass", ExamVO);
    }

}
