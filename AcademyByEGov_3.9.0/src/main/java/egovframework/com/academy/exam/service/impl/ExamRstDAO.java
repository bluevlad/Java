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
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2020.04.00  		rainend          최초 생성
 *   2020.04.27  		rainend          답안지 채점
 * </pre>
 */
@Repository("examRstDAO")
public class ExamRstDAO extends EgovComAbstractDAO{

    /**
     * @param ExamVO 검색조건
     * @return List 시험 목록정보
     */
    public List<?> selectExamRstList(ExamVO ExamVO) throws Exception{
		return selectList("ExamPass.selectExamRstList", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return int 시험 총갯수
     */
    public int selectExamRstListTotCnt(ExamVO ExamVO) {
        return (Integer)selectOne("ExamPass.selectExamRstListTotCnt", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @return ExamVO 시험  상세정보
     */
    public ExamVO selectExamRstDetail(ExamVO ExamVO){
        return selectOne("ExamPass.selectExamRstDetail", ExamVO);
    }

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
     * @param ExamVO 검색조건
     * @return List 시험 응시 과목목록정보
     */
    public List<?> selectExamRstSbjList(ExamVO ExamVO) throws Exception{
		return selectList("ExamPass.selectExamRstSbjList", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return int 시험 응시과목 총갯수
     */
    public int selectExamRstSbjListTotCnt(ExamVO ExamVO) {
        return (Integer)selectOne("ExamPass.selectExamRstSbjListTotCnt", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return ExamVO 시험 과목 선택 정보
     */
    public ExamVO selectExamRstSbjDetail(ExamVO ExamVO){
        return selectOne("ExamPass.selectExamRstSbjDetail", ExamVO);
    }
    
    /**
     * @param ExamVO 시험 응시과목 정보 등록
     */
    public void insertExamRstSbj(ExamVO ExamVO) throws Exception{
        insert("ExamPass.insertExamRstSbj", ExamVO);
    }

    /**
     * @param ExamVO 시험 응시과목 점수 등록
     */
    public void updateExamRstSbj(ExamVO ExamVO) throws Exception{
        update("ExamPass.updateExamRstSbj", ExamVO);
    }

    /**
     * @param ExamVO 시험 응시과목 정보삭제
     */
    public void deleteExamRstSbj(ExamVO ExamVO) throws Exception{
        delete("ExamPass.deleteExamRstSbj", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return List 시험 응시과목 채점정보
     */
    public List<ExamVO> selectExamRstDetList(ExamVO ExamVO) throws Exception{
		return selectList("ExamPass.selectExamRstDetList", ExamVO);
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

    /**
     * @param ExamVO 검색조건
     * @return String 답안정답여부
     */
    public String selectExamRstDetYN(ExamVO ExamVO) {
        return (String)selectOne("ExamPass.selectExamRstDetYN", ExamVO);
    }

}
