package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.lecture.service.LecFormService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 학습형태관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.11.00  			rainend          최초 생성
 * </pre>
 */
@Service("lecFormService")
public class LecFormServiceImpl extends EgovAbstractServiceImpl implements LecFormService {

	@Resource(name="lecFormDAO")
	private LecFormDAO lecFormDAO;

	/**
	 * @param LectureVO 검색조건
	 * @return List<?> 학습형태 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectFormList(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormList(LectureVO);
	}

	/**
	 * @param LectureVO 검색조건
	 * @return 총 학습형태 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectFormListCount(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormListCount(LectureVO);
	}

	@Override
	public LectureVO selectFormDetail(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormDetail(LectureVO);
	}
	
	/**
	 * @param LectureVO 강의정보
	 * @throws Exception
	 */
	@Override
	public void insertForm(LectureVO LectureVO) throws Exception {
		lecFormDAO.insertForm(LectureVO);
	}

	/**
	 * LectureVO 학습형태 수정
	 * @param LectureVO 강의정보
	 * @throws Exception
	 */
	@Override
	public void updateForm(LectureVO LectureVO) throws Exception {
		lecFormDAO.updateForm(LectureVO);
	}

	/**
	 * LectureVO 학습형태 삭제
	 * @param LectureVO 강의정보
	 * @throws Exception
	 */
	@Override
	public void deleteForm(LectureVO LectureVO) throws Exception {
		lecFormDAO.deleteForm(LectureVO);
	}
	@Override
	public int selectFormCheck(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormCheck(LectureVO);
	}

	/**
	 * @param LectureVO 
	 * @return List<?> 학습형태 전체 리스트
	 * @throws Exception
	 */
	@Override
	public List<?> selectFormListAll(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormListAll(LectureVO);
	}

}