package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface SubjectService {

	List<HashMap<String, String>> subjectList(HashMap<String, String> params);

	int subjectListCount(HashMap<String, String> params);

	String subjectGetCode(HashMap<String, String> params);

	void subjectInsert(HashMap<String, String> params);

	List<HashMap<String, String>> subjectView(HashMap<String, String> params);

	void subjectUpdate(HashMap<String, String> params);

	void subjectDelete(HashMap<String, String> params);

	int subjectCheck(HashMap<String, String> params);

	void subjectCategoryInsert(HashMap<String, String> params);

	void subjectCategoryDelete(HashMap<String, String> params);
	
	void subjectCategoryDeleteByCat(HashMap<String, String> params);

	void subjectCategoryOrderInsert(HashMap<String, String> params);
	
	int chkSubjectCategoryOrderCnt(HashMap<String, String> params);

	int chkSubjectCategoryCnt(HashMap<String, String> params);
	
	void subjectCategoryOrderDeleteByOnoff(HashMap<String, String> params);
	
	String getSubjectCategoryOrderIdx(HashMap<String, String> params);
	
	void subjectCategoryOrderDelete(HashMap<String, String> params);

	List<HashMap<String, String>> subjectCategoryView(HashMap<String, String> params);

	List<HashMap<String, String>> findSubjectCategoryList(HashMap<String, String> params);

	void main_category_subject_order_Insert(HashMap<String, String> params);

}
