package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.lecture.service.LecFormService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 과목관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.08.00  			rainend          최초 생성
 * </pre>
 */
@Service("lecFormService")
public class LecFormServiceImpl extends EgovAbstractServiceImpl implements LecFormService {

	@Resource(name="lecFormDAO")
	private LecFormDAO lecFormDAO;

	/**
	 * @param LectureVO 검색조건
	 * @return List<?> 과목 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectFormList(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormList(LectureVO);
	}

	/**
	 * @param LectureVO 검색조건
	 * @return 총 과목 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectFormListCount(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormListCount(LectureVO);
	}

	/**
	 * @param subjectCd 상세조회대상 과목코드
	 * @return LectureVO 과목 상세정보
	 * @throws Exception
	 */
	@Override
	public String selectFormGetCode(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormGetCode(LectureVO);
	}
	
	@Override
	public LectureVO selectFormDetail(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectFormDetail(LectureVO);
	}
	
	@Override
	public List<?> selectCodeList(LectureVO LectureVO) throws Exception {
		return lecFormDAO.selectCodeList(LectureVO);
	}

	/**
	 * @param LectureVO 과목 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertForm(LectureVO LectureVO) throws Exception {
		lecFormDAO.insertForm(LectureVO);
	}

	/**
	 * LectureVO 과목정보 수정
	 * @param LectureVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateForm(LectureVO LectureVO) throws Exception {
		lecFormDAO.updateForm(LectureVO);
	}

	/**
	 * LectureVO 과목정보 삭제
	 * @param LectureVO 시험정보
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

}