package egovframework.com.academy.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 시험문제세트정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.05.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2023.05.00  		rainend          최초 생성
 * </pre>
 */
@Repository("examSetManageDAO")
public class ExamSetManageDAO extends EgovComAbstractDAO{

    /**
     * @param ExamVO 검색조건
     * @return List 시험문항 목록정보
     */
    public List<?> selectExamSetList(ExamVO ExamVO) throws Exception{
		return selectList("ExamSetManage.selectExamSetList", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return int 시험문항 총갯수
     */
    public int selectExamSetListTotCnt(ExamVO ExamVO) {
        return (Integer)selectOne("ExamSetManage.selectExamSetListTotCnt", ExamVO);
    }

    /**
     * @param examCd 상세조회대상 문제번호코드
     * @return ExamVO 시험문항  상세정보
     */
    public ExamVO selectExamSetDetail(ExamVO ExamVO){
        return selectOne("ExamSetManage.selectExamSetDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험문항 등록
     */
    public void insertExamSet(ExamVO ExamVO) throws Exception{
        insert("ExamSetManage.insertExamSet", ExamVO);
    }

    /**
     * @param ExamVO 시험문항 수정(삭제)
     */
    public void updateExamSet(ExamVO ExamVO) throws Exception{
        update("ExamSetManage.updateExamSet", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return List 시험문제세트 문항 목록정보
     */
    public List<?> selectExamSetItemList(ExamVO ExamVO) throws Exception{
		return selectList("ExamSetManage.selectExamSetItemList", ExamVO);
    }

    /**
     * @param ExamVO 시험문항 세트 추가
     */
    public void insertExamSetItem(ExamVO ExamVO) throws Exception{
        insert("ExamSetManage.insertExamSetItem", ExamVO);
    }

    /**
     * @param ExamVO 시험문항 세트 삭제
     */
    public void deleteExamSetItem(ExamVO ExamVO) throws Exception{
        delete("ExamSetManage.deleteExamSetItem", ExamVO);
    }

    /**
     * @param ExamVO 검색조건
     * @return int 시험문제세트 순서 max
     */
    public int selectExamSetQueSeq(ExamVO ExamVO) {
        return (Integer)selectOne("ExamSetManage.selectExamSetQueSeq", ExamVO);
    }

}
