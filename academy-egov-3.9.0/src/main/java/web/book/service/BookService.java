package web.book.service;

import java.util.HashMap;
import java.util.List;

public interface BookService {

	List<HashMap<String, String>> bookList(HashMap<String, String> params);
	
	int bookListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params);
	
	List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params);
	
	int getCaBookSeq(HashMap<String, String> params);
	
	String getCaBookRscId(HashMap<String, String> params);
	
	void bookInsert(HashMap<String, String> params);
	
	List<HashMap<String, String>> bookViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> bookView(HashMap<String, String> params);
	
	int bookUseCheck(HashMap<String, String> params);
	
	void bookUpdate(HashMap<String, String> params);
	
	void bookDelete(HashMap<String, String> params);
	
	List<HashMap<String, String>> bookSellList(HashMap<String, String> params);
	
	int bookSellListCount(HashMap<String, String> params);
	
	List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params);
	
	void bookLogInsert(HashMap<String, String> params);

}