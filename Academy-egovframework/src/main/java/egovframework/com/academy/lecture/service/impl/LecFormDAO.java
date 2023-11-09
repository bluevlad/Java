package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 과목정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.11.10
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
@Repository("lecFormDAO")
public class LecFormDAO extends EgovComAbstractDAO{

	public List<?> selectFormList(LectureVO LectureVO) throws Exception{
		return selectList("lectureForm.selectFormList", LectureVO);
	}

	public int selectFormListCount(LectureVO LectureVO) throws Exception{
		return (Integer)selectOne("lectureForm.selectFormListCount", LectureVO);
	}

	public String selectFormGetCode(LectureVO LectureVO) throws Exception{
		return selectOne("lectureForm.selectFormGetCode", LectureVO);
	}

	public LectureVO selectFormDetail(LectureVO LectureVO) throws Exception{
		return selectOne("lectureForm.selectFormDetail", LectureVO);
	}

	public List<?> selectCodeList(LectureVO LectureVO) throws Exception{
		return selectList("lectureForm.selectCodeList", LectureVO);
	}

	public void insertForm(LectureVO LectureVO) throws Exception{
		insert("lectureForm.insertForm", LectureVO);
	}

	public void updateForm(LectureVO LectureVO) throws Exception{
		update("lectureForm.updateForm", LectureVO);
	}

	public void deleteForm(LectureVO LectureVO) throws Exception{
		delete("lectureForm.deleteForm", LectureVO);
	}

	public int selectFormCheck(LectureVO LectureVO) throws Exception{
		return selectOne("lectureForm.selectFormCheck", LectureVO);
	}

}
