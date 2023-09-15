package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.academy.lecture.service.SubjectService;
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
@Service("subjectService")
public class SubjectServiceImpl extends EgovAbstractServiceImpl implements SubjectService {

	@Resource(name="subjectDAO")
	private SubjectDAO subjectDAO;

	/**
	 * @param LectureVO 검색조건
	 * @return List<?> 과목 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectSubjectList(LectureVO LectureVO) throws Exception {
		return subjectDAO.selectSubjectList(LectureVO);
	}

	/**
	 * @param LectureVO 검색조건
	 * @return 총 과목 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectSubjectListTotCnt(LectureVO LectureVO) throws Exception {
		return subjectDAO.selectSubjectListTotCnt(LectureVO);
	}

	/**
	 * @param subjectCd 상세조회대상 과목코드
	 * @return LectureVO 과목 상세정보
	 * @throws Exception
	 */
	@Override
	public LectureVO selectSubjectDetail(LectureVO LectureVO) throws Exception {
		return subjectDAO.selectSubjectDetail(LectureVO);
	}

	/**
	 * @param LectureVO 과목 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertSubject(LectureVO LectureVO) throws Exception {
		subjectDAO.insertSubject(LectureVO);
	}

	/**
	 * LectureVO 과목정보 수정
	 * @param LectureVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateSubject(LectureVO LectureVO) throws Exception {
		subjectDAO.updateSubject(LectureVO);
	}

	/**
	 * LectureVO 과목정보 삭제
	 * @param LectureVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void deleteSubject(LectureVO LectureVO) throws Exception {
		subjectDAO.deleteSubject(LectureVO);
	}

}