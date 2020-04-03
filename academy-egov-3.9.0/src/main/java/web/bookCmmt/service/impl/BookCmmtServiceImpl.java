package com.willbes.web.bookCmmt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.bookCmmt.service.BookCmmtService;

@Service
public class BookCmmtServiceImpl  implements  BookCmmtService {

    @Autowired
    private BookCmmtDAO bookCmmtDao;

    public List<HashMap<String, Object>> bookCmmtList(Object obj){
        return bookCmmtDao.bookCmmtList(obj);
    }

    public List<HashMap<String, Object>> bookCmmtDetailList(Object obj){
        return bookCmmtDao.bookCmmtDetailList(obj);
    }

    public int bookCmmtDetailListCount(Object obj){
        return bookCmmtDao.bookCmmtDetailListCount(obj);
    }

    public int bookCmmtListCount(Object obj){
        return bookCmmtDao.bookCmmtListCount(obj);
    }

    public void cmmtDelete(Object obj){
        bookCmmtDao.cmmtDelete(obj);
    }
}
