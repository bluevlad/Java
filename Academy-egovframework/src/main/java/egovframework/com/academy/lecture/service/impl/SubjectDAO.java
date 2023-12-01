package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 과목정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2023.08.00  		rainend          최초 생성
 * </pre>
 */
@Repository("subjectDAO")
public class SubjectDAO extends EgovComAbstractDAO{

    /**
     * @param LectureVO 검색조건
     * @return List 과목 목록정보
     */
    public List<?> selectSubjectList(LectureVO LectureVO) throws Exception{
		return selectList("Subject.selectSubjectList", LectureVO);
    }

    /**
     * @param LectureVO 검색조건
     * @return int 과목 총갯수
     */
    public int selectSubjectListTotCnt(LectureVO LectureVO) {
        return (Integer)selectOne("Subject.selectSubjectListTotCnt", LectureVO);
    }

    /**
     * @param subjectCd 상세조회대상 과목코드
     * @return LectureVO 과목  상세정보
     */
    public LectureVO selectSubjectDetail(LectureVO LectureVO){
        return selectOne("Subject.selectSubjectDetail", LectureVO);
    }

    /**
     * @param LectureVO 과목 등록
     */
    public void insertSubject(LectureVO LectureVO) throws Exception{
        insert("Subject.insertSubject", LectureVO);
    }

    /**
     * @param LectureVO 과목 수정
     */
    public void updateSubject(LectureVO LectureVO) throws Exception{
        update("Subject.updateSubject", LectureVO);
    }

    /**
     * @param LectureVO 과목 삭제
     */
    public void deleteSubject(LectureVO LectureVO) throws Exception{
        update("Subject.deleteSubject", LectureVO);
    }

    /**
     * @param LectureVO 검색조건
     * @return List 직렬정보(분류)
     */
    public List<?> selectCategoryList(LectureVO LectureVO) throws Exception{
		return selectList("Subject.selectCategoryList", LectureVO);
    }

    /**
     * @param LectureVO 검색조건
     * @return List 과목 목록정보
     */
    public List<?> getSubjectList(LectureVO LectureVO) throws Exception{
		return selectList("Subject.getSubjectList", LectureVO);
		
    }
}
