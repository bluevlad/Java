package com.willbes.web.bannerManagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.bannerManagement.service.BannerManagementService;

@Service
public class BannerManagementServiceImpl  implements  BannerManagementService{

    @Autowired
    private BannerManagementDAO bannerManagementDAO;

    @Override
    public List<HashMap<String, String>> getCateKindList(HashMap<String, String> params){
        return bannerManagementDAO.getCateKindList(params);
    }

    @Override
    public List<HashMap<String, String>> getMenuKindList(HashMap<String, String> params){
        return bannerManagementDAO.getMenuKindList(params);
    }

    public List<HashMap<String, String>> getBannerList(Map<String, String> searchMap){
        return bannerManagementDAO.getBannerList(searchMap);
    }

    public int getBannerListCount(Map<String, String> searchMap){
        return bannerManagementDAO.getBannerListCount(searchMap);
    }

    @SuppressWarnings("unchecked")
    public void changeProcess(Object obj){
        if(null != obj) {
            ArrayList<HashMap<String, String>> banners = (ArrayList<HashMap<String, String>>)obj;
            if(null != banners && banners.size() > 0) {
                for(HashMap<String, String> banner : banners) {
                    bannerManagementDAO.changeProcess(banner);
                    bannerManagementDAO.deleteItemProcess(banner);
                }
            }
        }
    }

    public void insertProcess(HashMap<String, String> params){
        bannerManagementDAO.insertProcess(params);
    }

    public List<HashMap<String, String>> view(HashMap<String, String> params){
        return bannerManagementDAO.view(params);
    }

    public void updateProcess(HashMap<String, String> params){
        bannerManagementDAO.updateProcess(params);
    }
    public void deleteProcess(HashMap<String, String> params){
        bannerManagementDAO.deleteProcess(params);

        bannerManagementDAO.deleteItemProcess(params);
    }

    public List<HashMap<String, String>> getBannerSubList(Map<String, String> searchMap){
        return bannerManagementDAO.getBannerSubList(searchMap);
    }

    public int getBannerSubListCount(Map<String, String> searchMap){
        return bannerManagementDAO.getBannerSubListCount(searchMap);
    }

    public void bannerInsertProcess(HashMap<String, String> params){
        if(null != String.valueOf(params.get("SCREEN_GUBUN")) && "S".equals(String.valueOf(params.get("SCREEN_GUBUN")))) {
            if(null != params.get("CATEGORY_CDs") && params.get("CATEGORY_CDs").length() > 0) {
                String [] code_arr = params.get("CATEGORY_CDs").split("/");
                for (int i = 0; i < code_arr.length; i++) {
                    params.put("CATEGORY_CD", code_arr[i]);
                    if(bannerManagementDAO.getBannerCountByCate(params) > 0) {
                        bannerManagementDAO.bannerInsertProcessByBannerNo(params);
                    } else {
                        bannerManagementDAO.bannerInsertProcess(params);
                    }
                }
            } else {
                bannerManagementDAO.bannerInsertProcess(params);
            }
        } else {
            bannerManagementDAO.bannerInsertProcess(params);
        }
    }

    public List<HashMap<String, String>> bannerDetail(HashMap<String, String> params){
        return bannerManagementDAO.bannerDetail(params);
    }

    public void bannerUpdateProcess(HashMap<String, String> params){
        bannerManagementDAO.bannerUpdateProcess(params);
    }

    public void bannerDelete(HashMap<String, String> params){
        bannerManagementDAO.bannerDelete(params);
    }

    @SuppressWarnings("unchecked")
    public void updateItemOrder(Object obj){
        if(null != obj) {
            ArrayList<HashMap<String, String>> banners = (ArrayList<HashMap<String, String>>)obj;
            if(null != banners && banners.size() > 0) {
                for(HashMap<String, String> banner : banners) {
                    bannerManagementDAO.updateItemOrder(banner);
                }
            }
        }
    }

    @Override
    public List<HashMap<String, String>> getCateBannerList(HashMap<String, String> params){
        return bannerManagementDAO.getCateBannerList(params);
    }

    @Override
    public List<HashMap<String, String>> getCateBannerListWGseq(HashMap<String, String> params){
        return bannerManagementDAO.getCateBannerListWGseq(params);
    }

}
