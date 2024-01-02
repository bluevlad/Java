package com.willbes.web.mocktest.lectureFees.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class LectureFeesDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> lectureFeesList(	HashMap<String, String> params) {
		return getSqlSession().selectList("mockTestLectureFees.lectureFeesList", params);
	}

	public int lectureFeesListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("mockTestLectureFees.lectureFeesListCount", params);
	}

	public List<HashMap<String, String>> lectureFeesDetailExcelList(	HashMap<String, String> params) {
		return getSqlSession().selectList("mockTestLectureFees.lectureFeesDetailExcelList", params);
	}

	public List<HashMap<String, String>> lectureFeesRefundDetailExcelList(	HashMap<String, String> params) {
		return getSqlSession().selectList("mockTestLectureFees.lectureFeesRefundDetailExcelList", params);
	}

}
