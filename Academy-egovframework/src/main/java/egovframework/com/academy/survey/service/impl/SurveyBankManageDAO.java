package egovframework.com.academy.survey.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 설문문항은행정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.02.10
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
@Repository("surveyBankManageDAO")
public class SurveyBankManageDAO extends EgovComAbstractDAO{

    /**
     * @param SurveyVO 검색조건
     * @return List 설문문항 목록정보
     */
    public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception{
		return selectList("SurveyBankManage.selectSurveyBankList", SurveyVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 설문문항 총갯수
     */
    public int selectSurveyBankListTotCnt(SurveyVO SurveyVO) {
        return (Integer)selectOne("SurveyBankManage.selectSurveyBankListTotCnt", SurveyVO);
    }

    /**
     * @param examCd 상세조회대상 문제번호코드
     * @return SurveyVO 설문문항  상세정보
     */
    public SurveyVO selectSurveyBankDetail(SurveyVO SurveyVO){
        return selectOne("SurveyBankManage.selectSurveyBankDetail", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 등록
     */
    public void insertSurveyBank(SurveyVO SurveyVO) throws Exception{
        insert("SurveyBankManage.insertSurveyBank", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 수정(삭제)
     */
    public void updateSurveyBank(SurveyVO SurveyVO) throws Exception{
        update("SurveyBankManage.updateSurveyBank", SurveyVO);
    }

}
