package egovframework.com.academy.lecture.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.member.service.MemberVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 강사정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.08.10
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
@Repository("teacherDAO")
public class TeacherDAO extends EgovComAbstractDAO{

    public List<?> teacherList(MemberVO MemberVO) throws Exception{
        return getSqlSession().selectList("teacher.teacherList", MemberVO);
    }

    public int teacherListCount(MemberVO MemberVO) throws Exception{
        return getSqlSession().selectOne("teacher.teacherListCount", MemberVO);
    }

    public MemberVO teacherDetail(MemberVO MemberVO) throws Exception{
        return getSqlSession().selectOne("teacher.teacherDetail", MemberVO);
    }

    public void insertTeacher(MemberVO MemberVO) throws Exception{
        getSqlSession().insert("teacher.insertTeacher", MemberVO);
    }

    public void updateTeacher(MemberVO MemberVO) throws Exception{
        getSqlSession().update("teacher.updateTeacher", MemberVO);
    }

    public void deleteTeacher(MemberVO MemberVO) throws Exception{
        getSqlSession().delete("teacher.deleteTeacher", MemberVO);
    }

}
