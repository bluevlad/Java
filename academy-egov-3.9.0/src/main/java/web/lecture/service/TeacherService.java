package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface TeacherService {

    List<HashMap<String, String>> getKindList(HashMap<String, String> params);

    List<HashMap<String, String>> getSubjectList(HashMap<String, String> params);

    List<HashMap<String, String>> teacherList(HashMap<String, String> params);

    int teacherListCount(HashMap<String, String> params);

    List<HashMap<String, String>> teacherAllList(HashMap<String, String> params);

    int teacherIdCheck(HashMap<String, String> params);

    void teacherInsert(Object obj);

    List<HashMap<String, String>> teacherView(HashMap<String, String> params);

    void teacherUpdate(Object obj);

    void teacherCategoryInsert(HashMap<String, String> params);

    void teacherSubjectInsert(HashMap<String, String> params);

    void teacherIsUseUpdate(Object obj);

    void teacherDelete(HashMap<String, String> params);

    void teacherCategoryDelete(HashMap<String, String> params);

    void teacherSubjectDelete(HashMap<String, String> params);

    void teacherSeqUpdate(Object obj);

    List<HashMap<String, String>> teacherBookLog(HashMap<String, String> params);

	void teacherMain_Category_Insert(HashMap<String, String> params);

	void teacherMain_Category_Delete(HashMap<String, String> params);

	List<HashMap<String, String>> teacherMainList(HashMap<String, String> params);

	int teacherMainListCount(HashMap<String, String> params);

    List<HashMap<String, String>> findTeacherList(HashMap<String, String> params);

	int teacherMain_Category_Subject(HashMap<String, String> params);
	
	int teacherIntro_Category_Subject(HashMap<String, String> params);	
	
	int teacherIntro_F_Category_Subject(HashMap<String, String> params);
	
	List<HashMap<String, String>> teacherIntroList(HashMap<String, String> params);

	int teacherIntroListCount(HashMap<String, String> params);
	
	void teacherIntro_Category_Insert(HashMap<String, String> params);
	
	void teacherIntro_F_Category_Insert(HashMap<String, String> params);

	void teacherIntro_Category_Delete(HashMap<String, String> params);	
	
	void teacherIntro_F_Category_Delete(HashMap<String, String> params);
	
	List<HashMap<String, String>> teacherIntro_offList(HashMap<String, String> params);

	int teacherIntro_offListCount(HashMap<String, String> params);

}
