package egovframework.com.academy.box.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험문제관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2021.08.00  			rainend          최초 생성
 * </pre>
 */
@Service("boxManageService")
public class BoxManageServiceImpl extends EgovAbstractServiceImpl implements BoxManageService {

	@Resource(name="boxManageDAO")
	private BoxManageDAO boxManageDAO;

	/**
	 * @param BoxVO 검색조건
	 * @return List<?> 사물함 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectBoxList(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxList(BoxVO);
	}

	/**
	 * @param BoxVO 검색조건
	 * @return 사물함 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectBoxListTotCnt(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxListTotCnt(BoxVO);
	}

	/**
	 * @return BoxVO 사물함 상세정보
	 * @throws Exception
	 */
	@Override
	public BoxVO selectBoxDetail(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxDetail(BoxVO);
	}
	
	@Override
	public List<?> selectBoxNumList(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxNumList(BoxVO);
	}

	@Override
	public BoxVO selectBoxNumRentDetail(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxNumRentDetail(BoxVO);
	}
	
	@Override
	public void insertBox(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertBox(BoxVO);
	}

	@Override
	public void insertBoxNum(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertBoxNum(BoxVO);
	}

	@Override
	public void updateBox(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateBox(BoxVO);
	}

	@Override
	public BoxVO selectBoxNumRentOrderDetail(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxNumRentOrderDetail(BoxVO);
	}
	
	@Override
	public List<?> selectBoxNumRentOrderList(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxNumRentOrderList(BoxVO);
	}

	@Override
	public void updateBoxFlag(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateBoxFlag(BoxVO);
	}

	/**
	 * @param BoxVO
	 * @return 주문번호(String)
	 * @throws Exception
	 */
	@Override
	public String selectOrderno(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectOrderno(BoxVO);
	}

	@Override
	public void insertOrderItem(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertOrderItem(BoxVO);
	}

	@Override
	public void insertOrders(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertOrders(BoxVO);
	}

	@Override
	public void insertApprovals(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertApprovals(BoxVO);
	}

	@Override
	public void updateApprovals(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateApprovals(BoxVO);
	}

	@Override
	public void insertBoxRent(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertBoxRent(BoxVO);
	}
	@Override
	public int getBoxRentSeq(BoxVO BoxVO) throws Exception {
		return boxManageDAO.getBoxRentSeq(BoxVO);
	}

	@Override
	public void updateBoxNum(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateBoxNum(BoxVO);
	}

	@Override
	public void updateBoxNumRent(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateBoxNumRent(BoxVO);
	}
	
}
