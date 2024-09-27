package egovframework.com.academy.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.com.academy.lecture.service.TeacherService;
import egovframework.com.academy.member.service.MemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 강사관리에 관한 비지니스 클래스를 정의한다.
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
@Service("teacherService")
public class TeacherServiceImpl extends EgovAbstractServiceImpl implements TeacherService {

	@Resource(name="teacherDAO")
	private TeacherDAO teacherDAO;

	/**
	 * @param MemberVO 검색조건
	 * @return List<?> 강사 목록정보
	 * @throws Exception
	 */
    @Override
    public List<?> selectTeacherList(MemberVO MemberVO) throws Exception {
        return teacherDAO.selectTeacherList(MemberVO);
    }

    @Override
    public int selectTeacherListCount(MemberVO MemberVO) throws Exception {
        return teacherDAO.selectTeacherListCount(MemberVO);
    }

    @Override
    public MemberVO teacherDetail(MemberVO MemberVO) throws Exception {
        return teacherDAO.teacherDetail(MemberVO);
    }

    public void insertTeacher(MemberVO MemberVO) throws Exception {
    	teacherDAO.insertTeacher(MemberVO);
    }

    public void updateTeacher(MemberVO MemberVO) throws Exception {
    	teacherDAO.updateTeacher(MemberVO);
    }

    @Override
    public void deleteTeacher(MemberVO MemberVO) throws Exception {
    	teacherDAO.deleteTeacher(MemberVO);
    }

}