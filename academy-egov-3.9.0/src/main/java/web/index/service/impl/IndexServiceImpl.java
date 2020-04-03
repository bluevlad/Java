package com.willbes.web.index.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.index.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexDAO indexdao;

    @Override
    public List<HashMap<String, String>> list(HashMap<String, String> params) {
        return indexdao.list(params);
    }

    @Override
    public int listCount(HashMap<String, String> params) {
        return indexdao.listCount(params);
    }

    @Override
    public List<HashMap<String, Object>> getTopMenuList(HashMap<String, Object> params) {
        return indexdao.getTopMenuList(params);
    }

    @Override
    public List<HashMap<String, Object>> getLeftMenuList(HashMap<String, Object> params) {
        return indexdao.getLeftMenuList(params);
    }

    @Override
    public List<HashMap<String, Object>> selectLeftMenuTree(HashMap<String, Object> params) {
        return indexdao.selectLeftMenuTree(params);
    }

}
