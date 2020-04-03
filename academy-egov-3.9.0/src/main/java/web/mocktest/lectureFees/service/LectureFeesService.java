package web.mocktest.lectureFees.service;

import java.util.HashMap;
import java.util.List;

public interface LectureFeesService {

	List<HashMap<String, String>> lectureFeesList(HashMap<String, String> params);
	int lectureFeesListCount(HashMap<String, String> params);
	List<HashMap<String, String>> lectureFeesDetailExcelList(HashMap<String, String> params);
	List<HashMap<String, String>> lectureFeesRefundDetailExcelList(HashMap<String, String> params);
}
