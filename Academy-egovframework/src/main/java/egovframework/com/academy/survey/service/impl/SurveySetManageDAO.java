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
@Repository("surveySetManageDAO")
public class SurveySetManageDAO extends EgovComAbstractDAO{

    /**
     * @param SurveyVO 검색조건
     * @return List 설문문항 목록정보
     */
    public List<?> selectSurveySetList(SurveyVO SurveyVO) throws Exception{
		return selectList("SurveySetManage.selectSurveySetList", SurveyVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 설문문항 총갯수
     */
    public int selectSurveySetListTotCnt(SurveyVO SurveyVO) {
        return (Integer)selectOne("SurveySetManage.selectSetListTotCnt", SurveyVO);
    }

    /**
     * @param examCd 상세조회대상 문제번호코드
     * @return SurveyVO 설문문항  상세정보
     */
    public SurveyVO selectSurveySetDetail(SurveyVO SurveyVO){
        return selectOne("SurveySetManage.selectSurveySetDetail", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 등록
     */
    public void insertSurveySet(SurveyVO SurveyVO) throws Exception{
        insert("SurveySetManage.insertSurveySet", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 수정(삭제)
     */
    public void updateSurveySet(SurveyVO SurveyVO) throws Exception{
        update("SurveySetManage.updateSurveySet", SurveyVO);
    }

    /**
     * @param SurveyVO 검색조건
     * @return List 설문세트 문항 목록정보
     */
    public List<?> selectSurveySetItemList(SurveyVO SurveyVO) throws Exception{
		return selectList("SurveySetManage.selectSurveySetItemList", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 세트 추가
     */
    public void insertSurveySetItem(SurveyVO SurveyVO) throws Exception{
        insert("SurveySetManage.insertSurveySetItem", SurveyVO);
    }

    /**
     * @param SurveyVO 설문문항 세트 삭제
     */
    public void deleteSurveySetItem(SurveyVO SurveyVO) throws Exception{
        delete("SurveySetManage.deleteSurveySetItem", SurveyVO);
    }

}
