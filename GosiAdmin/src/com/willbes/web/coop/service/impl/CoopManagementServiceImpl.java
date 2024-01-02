package com.willbes.web.coop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.coop.service.CoopManagementService;

@Service
public class CoopManagementServiceImpl  implements  CoopManagementService{

    @Autowired
    private CoopManagementDAO coopManagementdao;

    public List<HashMap<String, Object>> CoopList(HashMap<String, Object> params){
        return coopManagementdao.CoopList(params);
    }

    public void CoopInsertProcess(HashMap<String, Object> params){
    	coopManagementdao.CoopInsertProcess(params);
    }

    public void CoopUpdateProcess(HashMap<String, Object> params){
    	coopManagementdao.CoopUpdateProcess(params);
    }

    public List<HashMap<String, Object>> CoopIpList(HashMap<String, Object> params){
        return coopManagementdao.CoopIpList(params);
    }

    public void CoopIpInsertProcess(HashMap<String, Object> params){
    	coopManagementdao.CoopIpInsertProcess(params);
    }

    public void CoopIpDeleteProcess(HashMap<String, Object> params){
    	coopManagementdao.CoopIpDeleteProcess(params);
    }
}