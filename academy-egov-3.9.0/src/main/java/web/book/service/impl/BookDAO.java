package web.book.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class BookDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return getSqlSession().selectList("book.bookList", params);
	}

	public int bookListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("book.bookListCount", params);
	}

	public List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params){
		return getSqlSession().selectList("book.getLearningFormList", params);
	}

	public List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params){
		return getSqlSession().selectList("book.getCaSubjectTeacherList", params);
	}

	public int getCaBookSeq(HashMap<String, String> params){
		return getSqlSession().selectOne("book.getCaBookSeq", params);
	}

	public String getCaBookRscId(HashMap<String, String> params){
		return getSqlSession().selectOne("book.getCaBookRscId", params);
	}

	public void bookInsert(HashMap<String, String> params){
		getSqlSession().insert("book.bookInsert", params);
	}

	public List<HashMap<String, String>> bookViewList(HashMap<String, String> params){
		return getSqlSession().selectList("book.bookViewList", params);
	}

	public List<HashMap<String, String>> bookView(HashMap<String, String> params){
		return getSqlSession().selectList("book.bookView", params);
	}

	public int bookUseCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("book.bookUseCheck", params);
	}

	public void bookUpdate(HashMap<String, String> params){
		getSqlSession().update("book.bookUpdate", params);
	}

	public void bookDelete(HashMap<String, String> params){
		getSqlSession().delete("book.bookDelete", params);
	}

	public List<HashMap<String, String>> bookSellList(HashMap<String, String> params){
		return getSqlSession().selectList("book.bookSellList", params);
	}

	public int bookSellListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("book.bookSellListCount", params);
	}

	public List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params){
		return getSqlSession().selectList("book.bookSellListExcel", params);
	}

	public void bookLogInsert(HashMap<String, String> params){
		getSqlSession().insert("book.bookLogInsert", params);
	}

}