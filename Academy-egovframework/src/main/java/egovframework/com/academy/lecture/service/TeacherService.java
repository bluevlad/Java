package egovframework.com.academy.lecture.service;

import java.util.List;

import egovframework.com.academy.member.service.MemberVO;

public interface TeacherService {

	/**
	 * 강사정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 시험 목록
	 */

	List<?> selectTeacherList(MemberVO MemberVO) throws Exception;

    int selectTeacherListCount(MemberVO MemberVO) throws Exception;

    MemberVO teacherDetail(MemberVO MemberVO) throws Exception;

    void insertTeacher(MemberVO MemberVO) throws Exception;

    void updateTeacher(MemberVO MemberVO) throws Exception;

    void deleteTeacher(MemberVO MemberVO) throws Exception;

}