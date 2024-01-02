package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lecture.service.CategoryService;

@Service
public class CategoryServiceImpl  implements  CategoryService {

    @Autowired
    private CategoryDAO seriesCategoryDao;

    public List<HashMap<String, Object>> getSeriesCateTree(){
        return seriesCategoryDao.getSeriesCateTree();
    }
    public HashMap<String, Object> getDetail(HashMap<String, Object> params){
        return seriesCategoryDao.getDetail(params);
    }
    public void updateProcess(HashMap<String, Object> params){
        seriesCategoryDao.updateProcess(params);
    }
    public int  deleteProcess(HashMap<String, Object> params){
        seriesCategoryDao.deleteCategory(params);
        return seriesCategoryDao.deleteSeries(params);
    }
    public int insertProcess(HashMap<String, Object> params){
        return seriesCategoryDao.insertProcess(params);
    }
    public int  idCheck(HashMap<String, Object> params){
        return seriesCategoryDao.idCheck(params);
    }
    public HashMap<String, Object> getMaxOrdr(Object obj){
        return seriesCategoryDao.getMaxOrdr(obj);
    }

}
