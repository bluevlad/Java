package egovframework.com.academy.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 시험문제은행정보에 관한 데이터 접근 클래스를 정의한다.
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
 *   2021.08.00  		rainend          최초 생성
 * </pre>
 */
@Repository("examBankManageDAO")
public class ExamBankManageDAO extends EgovComAbstractDAO{

    /**
     * @param searchVO 검색조건
     * @return List 시험문제 목록정보
     */
    public List<?> selectExamBankList(ExamVO searchVO) throws Exception{
		return selectList("ExamBankManage.selectExamBankList", searchVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 시험문제 총갯수
     */
    public int selectExamBankListTotCnt(ExamVO searchVO) {
        return (Integer)selectOne("ExamBankManage.selectExamBankListTotCnt", searchVO);
    }

    /**
     * @param examCd 상세조회대상 문제번호코드
     * @return ExamVO 시험문제  상세정보
     */
    public ExamVO selectExamBankDetail(ExamVO ExamVO){
        return selectOne("ExamBankManage.selectExamBankDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험문제정보 등록
     */
    public void insertExamBank(ExamVO ExamVO) throws Exception{
        insert("ExamBankManage.insertExamBank", ExamVO);
    }

    /**
     * @param ExamVO 시험문제정보 수정(삭제)
     */
    public void updateExamBank(ExamVO ExamVO) throws Exception{
        update("ExamBankManage.updateExamBank", ExamVO);
    }

    /**
     * @param searchVO 검색조건
     * @return List 시험문제 목록정보
     */
    public List<?> selectExamBankItemlList(ExamVO searchVO) throws Exception{
		return selectList("ExamBankManage.selectExamBankItemlList", searchVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 시험문제 총갯수
     */
    public int selectExamBankItemListTotCnt(ExamVO searchVO) {
        return (Integer)selectOne("ExamBankManage.selectExamBankItemListTotCnt", searchVO);
    }

    /**
     * @param examCd 상세조회대상 시험코드
     * @return ExamVO 시험문제  상세정보
     */
    public ExamVO selectExamBankItemDetail(ExamVO ExamVO){
        return selectOne("ExamBankManage.selectExamBankItemDetail", ExamVO);
    }

    /**
     * @param ExamVO 시험문제정보 등록
     */
    public void insertExamBankItem(ExamVO ExamVO) throws Exception{
        insert("ExamBankManage.insertExamBankItem", ExamVO);
    }

    /**
     * @param ExamVO 시험문제정보 수정(삭제)
     */
    public void updateExamBankItem(ExamVO ExamVO) throws Exception{
        update("ExamBankManage.updateExamBankItem", ExamVO);
    }

}
