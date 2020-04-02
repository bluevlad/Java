package web.bannerManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : BannerManagementService.java
 * @Project    :
 * @Date       : 2015. 07
 * @Author     :
 * @변경이력    :
 * @프로그램 설명 : 배너 관리 service
 */
public interface BannerManagementService {

    List<HashMap<String, String>> getCateKindList(HashMap<String, String> params);

    List<HashMap<String, String>> getMenuKindList(HashMap<String, String> params);

    /**
     * @Method Name  : getBannerList
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 	배너 관리 -  배너조회  리스트
     * @param searchMap
     * @return
     */
    List<HashMap<String, String>> getBannerList(Map<String, String> searchMap);
    /**
     * @Method Name  : getMemberListCount
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 관리 -  배너조회  리스트 총 갯수
     * @param searchMap
     * @return
     */
    int getBannerListCount(Map<String, String> searchMap);
    /**
     * @Method Name  : changeProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 변경
     * @param params
     */
    void changeProcess(Object obj);

    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 등록 프로세스
     * @param params
     */
    void insertProcess(HashMap<String, String> params);

    /**
     * @Method Name  : view
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 상세 페이지
     * @param params
     * @return
     */
    List<HashMap<String, String>> view(HashMap<String, String> params);
    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 수정 프로세스
     * @param params
     */
    void updateProcess(HashMap<String, String> params);
    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 마스터 삭제
     * @param params
     */
    void deleteProcess(HashMap<String, String> params);

    /**
     * @Method Name  : getBannerList
     * @Date         : 2020.03
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 -  배너 상세 조회  리스트
     * @param searchMap
     * @return
     */
    List<HashMap<String, String>> getBannerSubList(Map<String, String> searchMap);
    /**
     * @Method Name  : getMemberListCount
     * @Date         : 2013. 11. 1.
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 관리 -  배너 상세 조회  리스트 총 갯수
     * @param searchMap
     * @return
     */
    int getBannerSubListCount(Map<String, String> searchMap);

    /**
     * @Method Name  : bannerInsertProcess
     * @Date         : 2020.03
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 등록 프로세스
     * @param params
     */
    void bannerInsertProcess(HashMap<String, String> params);

    /**
     * @Method Name  : bannerDetail
     * @Date         : 2020.03
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 상세 페이지
     * @param params
     * @return
     */
    List<HashMap<String, String>> bannerDetail(HashMap<String, String> params);
    /**
     * @Method Name  : bannerUpdateProcess
     * @Date         : 2020.03
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 수정 프로세스
     * @param params
     */
    void bannerUpdateProcess(HashMap<String, String> params);
    /**
     * @Method Name  : bannerDelete
     * @Date         : 2020.03
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 개별 삭제
     * @param params
     */
    void bannerDelete(HashMap<String, String> params);
    /**
     * @Method Name  : updateItemOrder
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 순서 변경
     * @param params
     */
    void updateItemOrder(Object obj);

    List<HashMap<String, String>> getCateBannerList(HashMap<String, String> params);

    List<HashMap<String, String>> getCateBannerListWGseq(HashMap<String, String> params);
    
    List<HashMap<String, String>> getOnAirBannerList(Map<String, String> searchMap);
    
    int getOnAirBannerListCount(Map<String, String> searchMap);
    
    List<HashMap<String, String>> getTeacher_NM(Map<String, String> searchMap);
     
    int getOnAirBannerSeq(Map<String, String> searchMap);
    
    void OnAir_insertProcess(HashMap<String, String> params);
    
    void OnAir_insertProcess2(HashMap<String, String> params);
    
    List<HashMap<String, String>> getOnAirBannerDayList(Map<String, String> searchMap);
    
    HashMap<String, String> Onairview(Map<String, String> searchMap);
    
    List<HashMap<String, String>> Onair_Datelist(Map<String, String> searchMap);
    
    void OnAir_updateProcess1(HashMap<String, String> params);
    
    void OnAir_deleteProcess2(HashMap<String, String> params);
    
    void OnAir_deleteProcess(HashMap<String, String> params);
    
    List<HashMap<String, String>> getClassRoom(Map<String, String> searchMap);
}
