package com.willbes.web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.adminManagement.service.AdminManagementAuthService;

@Service
public class AdminManagementAuthServiceImpl  implements  AdminManagementAuthService {

    @Autowired
    private AdminManagementAuthDAO adminManagementAuthdao;

    public List<HashMap<String, Object>> authContorlList(	Map<String, Object> searchMap){
        return adminManagementAuthdao.authContorlList(searchMap);
    }
    public int authContorlListCount(Map<String, Object> searchMap){
        return adminManagementAuthdao.authContorlListCount(searchMap);
    }

    public void authControlInsertProcess(HashMap<String, Object> params){
        adminManagementAuthdao.authControlInsertProcess(params);
    }

    public HashMap<String, Object> authControlDetail(HashMap<String, Object> params){
        return adminManagementAuthdao.authControlDetail(params);
    }

    public void authControlUpdateProcess(HashMap<String, Object> params){
        adminManagementAuthdao.authControlUpdateProcess(params);
    }
    public void authControlMenuUpdateProcess(HashMap<String, Object> params){
        adminManagementAuthdao.authControlMenuUpdateProcess(params);
    }

    public void authControlDelete(HashMap<String, Object> params){
        adminManagementAuthdao.authControlDelete(params);
    }

    public void authControlCheckDelete(HashMap<String, Object> params){
        adminManagementAuthdao.authControlCheckDelete(params);
    }

    public List<HashMap<String, Object>> authControlOnMenuList(	HashMap<String, Object> params){
        return adminManagementAuthdao.authControlOnMenuList(params);
    }
    public List<HashMap<String, Object>> authControlOffMenuList(	HashMap<String, Object> params){
        return adminManagementAuthdao.authControlOffMenuList(params);
    }
    public List<HashMap<String, Object>> authControlTestMenuList(    HashMap<String, Object> params){
        return adminManagementAuthdao.authControlTestMenuList(params);
    }

    public void deleteAuthMenu(HashMap<String, Object> params){
        adminManagementAuthdao.deleteAuthMenu(params);
    }
    public  void authMenuInsertProcess(HashMap<String, Object> params){
        adminManagementAuthdao.authMenuInsertProcess(params);
    }

    public List<HashMap<String, Object>> authMenuList(HashMap<String, Object> params){
        return adminManagementAuthdao.authMenuList(params);
    }
    public  void deleteSiteIdAuthMenu(HashMap<String, Object> params){
        adminManagementAuthdao.deleteSiteIdAuthMenu(params);
    }
    public  void deleteSiteIdCheckAuthMenu(HashMap<String, Object> params){
        adminManagementAuthdao.deleteSiteIdCheckAuthMenu(params);
    }
}
