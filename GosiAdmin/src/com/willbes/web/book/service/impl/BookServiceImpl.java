package com.willbes.web.book.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.book.service.BookService;
import com.willbes.web.book.service.impl.BookDAO;

@Service(value="bookservice")
public class BookServiceImpl implements BookService{
	@Autowired 
	private BookDAO bookdao;
	
	@Override
	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return bookdao.bookList(params);
	}		
	
	@Override
	public int bookListCount(HashMap<String, String> params){
		return bookdao.bookListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params){
		return bookdao.getLearningFormList(params);
	}
	
	@Override
	public List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params){
		return bookdao.getCaSubjectTeacherList(params);
	}
	
	@Override
	public int getCaBookSeq(HashMap<String, String> params){
		return bookdao.getCaBookSeq(params);
	}
	
	@Override
	public String getCaBookRscId(HashMap<String, String> params){
		return bookdao.getCaBookRscId(params);
	}
	
	@Override
	public void bookInsert(HashMap<String, String> params){
		bookdao.bookInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookViewList(HashMap<String, String> params){
		return bookdao.bookViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookView(HashMap<String, String> params){
		return bookdao.bookView(params);
	}		
	
	@Override
	public int bookUseCheck(HashMap<String, String> params){
		return bookdao.bookUseCheck(params);
	}	
	
	@Override
	public void bookUpdate(HashMap<String, String> params){
		bookdao.bookUpdate(params);
	}	
	
	@Override
	public void bookDelete(HashMap<String, String> params){
		bookdao.bookDelete(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookSellList(HashMap<String, String> params){
		return bookdao.bookSellList(params);
	}		
	
	@Override
	public int bookSellListCount(HashMap<String, String> params){
		return bookdao.bookSellListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params){
		return bookdao.bookSellListExcel(params);
	}			
}