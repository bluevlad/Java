package egovframework.com.academy.box.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 사물함 정보에 관한 데이터 접근 클래스를 정의한다.
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
 *   2020.05.21  		rainend          최초 생성
 * </pre>
 */
@Repository("boxManageDAO")
public class BoxManageDAO extends EgovComAbstractDAO{

    /**
     * @param searchVO 검색조건
     * @return List 사물함 목록정보
     */
    public List<?> selectBoxList(BoxVO searchVO) throws Exception{
		return selectList("BoxManage.selectBoxList", searchVO);
    }

    /**
     * @param searchVO 검색조건
     * @return int 사물함 총갯수
     */
    public int selectBoxListTotCnt(BoxVO searchVO) {
        return (Integer)selectOne("BoxManage.selectBoxListTotCnt", searchVO);
    }

    /**
     * @param boxCd 상세조회대상 사물함코드
     * @return BoxVO 사물함  상세정보
     */
    public BoxVO selectBoxDetail(BoxVO BoxVO){
        return selectOne("BoxManage.selectBoxDetail", BoxVO);
    }

    /**
     * @param ExamVO 사물함 정보 등록
     */
    public void insertBox(BoxVO BoxVO) throws Exception{
        insert("BoxManage.insertBox", BoxVO);
    }

    /**
     * @param ExamVO 사물함 정보 변경
     */
    public void updateBox(BoxVO BoxVO) throws Exception{
        insert("BoxManage.updateBox", BoxVO);
    }

    /**
     * @param BoxVO 사물함 정보 삭제
     */
    public void deleteBox(BoxVO BoxVO) throws Exception{
        update("BoxManage.deleteBox", BoxVO);
    }

    /**
     * @param searchVO 검색조건
     * @return List 과목 목록정보
     */
    public List<?> selectBoxNumList(BoxVO searchVO) throws Exception{
		return selectList("BoxManage.selectBoxNumList", searchVO);
    }

    /**
     * @param examSearchVO 검색조건
     * @return int 과목 총갯수
     */
    public int selectBoxNumListTotCnt(BoxVO searchVO) {
        return (Integer)selectOne("BoxManage.selectBoxNumListTotCnt", searchVO);
    }

    /**
     * @param BoxVO 사물함 등록
     */
    public int insertBoxNum(BoxVO BoxVO) throws Exception{
        return getSqlSession().insert("BoxManage.insertBoxNum", BoxVO);
    }

    /**
     * @param BoxVO 사물함 등록
     */
    public int deleteBoxNum(BoxVO BoxVO) throws Exception{
        return getSqlSession().insert("BoxManage.deleteBoxNum", BoxVO);
    }

}
