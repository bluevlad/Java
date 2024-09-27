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
@Repository("examManageDAO")
public class ExamManageDAO extends EgovComAbstractDAO{

    /**
     * @param searchVO 검색조건
     * @return List 시험 목록정보
     */
    public List<?> selectExamList(ExamVO searchVO) throws Exception{
		return selectList("ExamManage.selectExamList", searchVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 시험 총갯수
     */
    public int selectExamListTotCnt(ExamVO searchVO) {
        return (Integer)selectOne("ExamManage.selectExamListTotCnt", searchVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @return ExamVO 시험  상세정보
     */
    public ExamVO selectExamDetail(ExamVO ExamVO){
        return selectOne("ExamManage.selectExamDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험정보 등록
     */
    public void insertExam(ExamVO ExamVO) throws Exception{
        insert("ExamManage.insertExam", ExamVO);
    }

    /**
     * @param ExamVO 시험정보 등록
     */
    public int insertExamRetcd(ExamVO ExamVO) throws Exception{
        return getSqlSession().insert("ExamManage.insertExamRetcd", ExamVO);
    }

    /**
     * @param ExamVO 시험정보 수정(삭제)
     */
    public void updateExam(ExamVO ExamVO) throws Exception{
        update("ExamManage.updateExam", ExamVO);
    }

    /**
     * @param examSearchVO 검색조건
     * @return List 과목 목록정보
     */
    public List<ExamVO> selectSubjectList(ExamVO ExamVO) throws Exception{
		return selectList("ExamManage.selectSubjectList", ExamVO);
    }

    /**
     * @param examSearchVO 검색조건
     * @return int 과목 총갯수
     */
    public int selectSubjectListTotCnt(ExamVO ExamVO) {
        return (Integer)selectOne("ExamManage.selectSubjectListTotCnt", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @return ExamVO 시험  상세정보
     */
    public ExamVO selectSubjectDetail(ExamVO ExamVO){
        return (ExamVO) selectOne("ExamManage.selectSubjectDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험정보 등록
     */
    public void insertSubject(ExamVO ExamVO){
        insert("ExamManage.insertSubject", ExamVO);
    }

    /**
     * @param ExamVO 시험정보 수정(삭제)
     */
    public void updateSubject(ExamVO ExamVO){
        update("ExamManage.updateSubject", ExamVO);
    }

}
