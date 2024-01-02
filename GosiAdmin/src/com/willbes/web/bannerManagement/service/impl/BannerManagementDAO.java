package com.willbes.web.bannerManagement.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class BannerManagementDAO extends CmmAbstractMapper {

    public List<HashMap<String, String>> getCateKindList(HashMap<String, String> params){
        return getSqlSession().selectList("bannerManagement.getCateKindList", params);
    }

    public List<HashMap<String, String>> getMenuKindList(HashMap<String, String> params){
        return getSqlSession().selectList("bannerManagement.getMenuKindList", params);
    }

    /**
     * @Method Name  : getBannerList
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 -  배너 마스터 조회 리스트
     * @param searchMap
     * @return
     */
    public List<HashMap<String, String>> getBannerList(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getBannerList", searchMap);
    }

    /**
     * @Method Name  : getBannerListCount
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 -  배너 마스터 조회 리스트 총 갯수
     * @param searchMap
     * @return
     */
    public int getBannerListCount(Map<String, String> searchMap) {
        return getSqlSession().selectOne("bannerManagement.getBannerListCount", searchMap);
    }

    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 마스터 변경
     * @param params
     */
    public void changeProcess(Object obj) {
        getSqlSession().insert("bannerManagement.changeProcess", obj);
    }

    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 마스터 등록
     * @param params
     */
    public void insertProcess(HashMap<String, String> params) {
        getSqlSession().insert("bannerManagement.insertProcess", params);
    }

    /**
     * @Method Name  : view
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 조회 상세
     * @param params
     * @return
     */
    public List<HashMap<String, String>> view(HashMap<String, String> params) {
        return getSqlSession().selectList("bannerManagement.view", params);
    }

    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 조회 수정
     * @param params
     */
    public void updateProcess(HashMap<String, String> params) {
        getSqlSession().update("bannerManagement.updateProcess", params);
    }

    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터  삭제
     * @param params
     */
    public void deleteProcess(HashMap<String, String> params) {
        getSqlSession().delete("bannerManagement.deleteProcess", params);
    }

    /**
     * @Method Name  : deleteItemProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터연결 하위 배너 모두 삭제
     * @param params
     */
    public void deleteItemProcess(HashMap<String, String> params) {
        getSqlSession().delete("bannerManagement.deleteItemProcess", params);
    }

    /**
     * @Method Name  : getBannerSubList
     * @Date         : 2013. 10. 28.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 - 배너 리스트
     * @param searchMap
     * @return
     */
    public List<HashMap<String, String>> getBannerSubList(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getBannerSubList", searchMap);
    }

    /**
     * @Method Name  : getBannerSubListCount
     * @Date         : 2013. 10. 28.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 - 배너  리스트 총 갯수
     * @param searchMap
     * @return
     */
    public int getBannerSubListCount(Map<String, String> searchMap) {
        return getSqlSession().selectOne("bannerManagement.getBannerSubListCount", searchMap);
    }

    /**
     * @Method Name  : bannerInsertProcess
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 등록
     * @param params
     */
    public void bannerInsertProcess(HashMap<String, String> params) {
        getSqlSession().insert("bannerManagement.bannerInsertProcess", params);
    }

    /**
     * @Method Name  : bannerDetail
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 조회 상세
     * @param params
     * @return
     */
    public List<HashMap<String, String>> bannerDetail(HashMap<String, String> params) {
        return getSqlSession().selectList("bannerManagement.bannerDetail", params);
    }

    /**
     * @Method Name  : bannerUpdateProcess
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 조회 수정
     * @param params
     */
    public void bannerUpdateProcess(HashMap<String, String> params) {
        getSqlSession().update("bannerManagement.bannerUpdateProcess", params);
    }

    /**
     * @Method Name  : bannerDelete
     * @Date         : 2013. 10. 28.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 - 배너 조회 개별 삭제
     * @param params
     */
    public void bannerDelete(HashMap<String, String> params) {
        getSqlSession().delete("bannerManagement.bannerDelete", params);
    }

    /**
     * @Method Name  : updateItemOrder
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 순서 변경
     * @param params
     */
    public void updateItemOrder(Object obj) {
        getSqlSession().insert("bannerManagement.updateItemOrder", obj);
    }

    public List<HashMap<String, String>> getCateBannerList(HashMap<String, String> params){
        return getSqlSession().selectList("bannerManagement.getCateBannerList", params);
    }
    public int getBannerCountByCate(Map<String, String> searchMap) {
        return getSqlSession().selectOne("bannerManagement.getBannerCountByCate", searchMap);
    }
    public void bannerInsertProcessByBannerNo(HashMap<String, String> params) {
        getSqlSession().insert("bannerManagement.bannerInsertProcessByBannerNo", params);
    }
    public void bannerUpdateProcessByBannerNo(HashMap<String, String> params) {
        getSqlSession().update("bannerManagement.bannerUpdateProcessByBannerNo", params);
    }
    public List<HashMap<String, String>> getCateBannerListWGseq(HashMap<String, String> params){
        return getSqlSession().selectList("bannerManagement.getCateBannerListWGseq", params);
    }

}
