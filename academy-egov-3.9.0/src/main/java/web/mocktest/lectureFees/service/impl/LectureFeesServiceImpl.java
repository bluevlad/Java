package web.mocktest.lectureFees.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mocktest.lectureFees.service.LectureFeesService;
//import web.mocktest.offExamReg.service.OffExamRegService;

@Service
public class LectureFeesServiceImpl implements LectureFeesService {
	@Autowired
	private LectureFeesDAO lectureFeesDao;

	@Override
	public List<HashMap<String, String>> lectureFeesList(HashMap<String, String> params) {
		return lectureFeesDao.lectureFeesList(params);
	}

	@Override
	public int lectureFeesListCount(HashMap<String, String> params) {
		return lectureFeesDao.lectureFeesListCount(params);
	}

	@Override
	public List<HashMap<String, String>> lectureFeesDetailExcelList(HashMap<String, String> params) {
		return lectureFeesDao.lectureFeesDetailExcelList(params);
	}
	@Override
	public List<HashMap<String, String>> lectureFeesRefundDetailExcelList(HashMap<String, String> params) {
		return lectureFeesDao.lectureFeesRefundDetailExcelList(params);
	}
}
