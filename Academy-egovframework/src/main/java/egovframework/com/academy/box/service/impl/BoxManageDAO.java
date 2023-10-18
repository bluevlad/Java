package egovframework.com.academy.box.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.academy.lecture.service.LectureVO;
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
@Repository("boxManageDAO")
public class BoxManageDAO extends EgovComAbstractDAO{

    /**
     * @param BoxVO 검색조건
     * @return List 사물함 목록정보
     */
    public List<?> selectBoxList(BoxVO BoxVO) throws Exception{
		return selectList("box.selectBoxList", BoxVO);
    }

    /**
     * @param BoxVO 검색조건
     * @return int 사물함 총갯수
     */
    public int selectBoxListTotCnt(BoxVO BoxVO) {
        return (Integer)selectOne("box.selectBoxListTotCnt", BoxVO);
    }

	public BoxVO selectBoxDetail(BoxVO BoxVO) {
		return getSqlSession().selectOne("box.selectBoxDetail", BoxVO);
	}

    public List<?> selectBoxNumList(BoxVO BoxVO) throws Exception{
		return selectList("box.selectBoxNumList", BoxVO);
    }

	public BoxVO selectBoxNumRentDetail(BoxVO BoxVO) {
		return getSqlSession().selectOne("box.selectBoxNumRentDetail", BoxVO);
	}

    public void insertBox(BoxVO BoxVO) throws Exception{
        insert("box.insertBox", BoxVO);
    }

    public void insertBoxNum(BoxVO BoxVO) throws Exception{
        insert("box.insertBoxNum", BoxVO);
    }

    public void updateBox(BoxVO BoxVO) throws Exception{
        update("box.updateBox", BoxVO);
    }

	public BoxVO selectBoxNumRentOrderDetail(BoxVO BoxVO) {
		return getSqlSession().selectOne("box.selectBoxNumRentOrderDetail", BoxVO);
	}

	public List<?> selectBoxNumRentOrderList(BoxVO BoxVO) {
		return selectList("box.selectBoxNumRentOrderList", BoxVO);
	}

    public void updateBoxFlag(BoxVO BoxVO) throws Exception{
        update("box.updateBoxFlag", BoxVO);
    }

    public String selectOrderno(BoxVO BoxVO) {
        return (String)selectOne("box.selectOrderno", BoxVO);
    }

    public void insertOrderItem(BoxVO BoxVO) throws Exception{
        insert("box.insertOrderItem", BoxVO);
    }

    public void insertOrders(BoxVO BoxVO) throws Exception{
        insert("box.insertOrders", BoxVO);
    }

    public void insertApprovals(BoxVO BoxVO) throws Exception{
        insert("box.insertApprovals", BoxVO);
    }

    public void insertBoxRent(BoxVO BoxVO) throws Exception{
        insert("box.insertBoxRent", BoxVO);
    }

    public int getBoxRentSeq(BoxVO BoxVO) {
        return (Integer)selectOne("box.getBoxRentSeq", BoxVO);
    }

    public void updateBoxNum(BoxVO BoxVO) throws Exception{
        update("box.updateBoxNum", BoxVO);
    }
	
}
