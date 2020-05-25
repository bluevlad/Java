package egovframework.com.academy.box.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사물함 관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.05.21  			rainend          최초 생성
 * </pre>
 */
@Service("boxManageService")
public class BoxManageServiceImpl extends EgovAbstractServiceImpl implements BoxManageService {

	@Resource(name="boxManageDAO")
	private BoxManageDAO boxManageDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 사물함 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectBoxList(BoxVO searchVO) throws Exception {
		return boxManageDAO.selectBoxList(searchVO);
	}

	/**
	 * @param searchVO 검색조건
	 * @return 사물함 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectBoxListTotCnt(BoxVO searchVO) throws Exception {
		return boxManageDAO.selectBoxListTotCnt(searchVO);
	}

	/**
	 * @param boxCd 상세조회대상 사물함코드
	 * @return BoxVO 사물함 상세정보
	 * @throws Exception
	 */
	@Override
	public BoxVO selectBoxDetail(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxDetail(BoxVO);
	}

	/**
	 * @param BoxVO 사물함 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertBox(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertBox(BoxVO);
	}

	/**
	 * @param BoxVO 사물함 등록정보
	 * @throws Exception
	 */
	@Override
	public void updateBox(BoxVO BoxVO) throws Exception {
		boxManageDAO.updateBox(BoxVO);
	}

	/**
	 * BoxVO 사물함 수정
	 * @param BoxVO 사물함
	 * @throws Exception
	 */
	@Override
	public void deleteBox(BoxVO BoxVO) throws Exception {
		boxManageDAO.deleteBox(BoxVO);
	}

	/**
	 * @param BoxVO 검색조건
	 * @return List<BoxVO> 사물함 목록정보
	 * @throws Exception
	 */
	public List<?> selectBoxInfoList(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxInfoList(BoxVO);
	}

	/**
	 * @param boxCd 상세조회대상 사물함코드
	 * @param boxNum 상세조회대상 사물함번호
	 * @return BoxVO 사물함 상세정보
	 * @throws Exception
	 */
	@Override
	public BoxVO selectBoxInfoDetail(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxInfoDetail(BoxVO);
	}

	/**
	 * @param BoxVO 사물함 등록
	 * @param boxCd 사물함코드
	 * @throws Exception
	 */
	public void insertBoxNum(BoxVO BoxVO) throws Exception {
		boxManageDAO.insertBoxNum(BoxVO);
	}

	/**
	 * BoxVO 사물함 삭제
	 * @param boxCd 사물함코드
	 * @param boxNum 사물함번호
	 * @throws Exception
	 */
	public void deleteBoxNum(BoxVO BoxVO) throws Exception {
		boxManageDAO.deleteBoxNum(BoxVO);
	}

	/**
	 * @param boxCd 상세조회대상 사물함코드
	 * @param boxNum 상세조회대상 사물함번호
     * @param rentSeq 상세조회대상 대여번호
	 * @return BoxVO 사물함 상세정보
	 * @throws Exception
	 */
	public BoxVO selectBoxRentInfo(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxRentInfo(BoxVO);
	}

	/**
	 * @param boxCd 상세조회대상 사물함코드
	 * @param boxNum 상세조회대상 사물함번호
	 * @return List<BoxVO> 사물함 대여 목록정보
	 * @throws Exception
	 */
	public List<?> selectBoxRentList(BoxVO BoxVO) throws Exception {
		return boxManageDAO.selectBoxRentList(BoxVO);
	}

}
