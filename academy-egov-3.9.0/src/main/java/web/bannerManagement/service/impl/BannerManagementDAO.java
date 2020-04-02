package web.bannerManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class BannerManagementDAO extends EgovComAbstractDAO {

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
     * @Date         : 2020.03
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
     * @Date         : 2020.03
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
     * @Date         : 2020.03
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
     * @Date         : 2020.03
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
     * @Date         : 2020.03
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
     * @Date         : 2020.03
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
    public List<HashMap<String, String>> getOnAirBannerList(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getOnAirBannerList", searchMap);
    }
    public int getOnAirBannerListCount(Map<String, String> searchMap) {
        return getSqlSession().selectOne("bannerManagement.getOnAirBannerListCount", searchMap);
    }
    public List<HashMap<String, String>> getTeacher_NM(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getTeacher_NM", searchMap);
    }
    public int getOnAirBannerSeq(Map<String, String> searchMap) {
        return getSqlSession().selectOne("bannerManagement.getOnAirBannerSeq", searchMap);
    }
    public void OnAir_insertProcess(HashMap<String, String> params) {
        getSqlSession().insert("bannerManagement.OnAir_insertProcess", params);
    }
    public void OnAir_insertProcess2(HashMap<String, String> params) {
        getSqlSession().insert("bannerManagement.OnAir_insertProcess2", params);
    }
    public List<HashMap<String, String>> getOnAirBannerDayList(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getOnAirBannerDayList", searchMap);
    }
    public HashMap<String, String> Onairview(Map<String, String> searchMap){
        return getSqlSession().selectOne("bannerManagement.Onairview", searchMap);
    }
    public List<HashMap<String, String>> Onair_Datelist(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.Onair_Datelist", searchMap);
    }
    public void OnAir_updateProcess1(HashMap<String, String> params) {
        getSqlSession().update("bannerManagement.OnAir_updateProcess1", params);
    }
    public void OnAir_deleteProcess2(HashMap<String, String> params) {
        getSqlSession().delete("bannerManagement.OnAir_deleteProcess2", params);
    }
    public void OnAir_deleteProcess(HashMap<String, String> params) {
        getSqlSession().delete("bannerManagement.OnAir_deleteProcess", params);
    }
    public List<HashMap<String, String>> getClassRoom(Map<String, String> searchMap) {
        return getSqlSession().selectList("bannerManagement.getClassRoom", searchMap);
    }

}
