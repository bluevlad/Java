package web.adminManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : AdminManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 service
 */
public interface AdminManagementAuthService {

    /*
     * =====================권한 관리===========================================
     * */

    /**
     * @Method Name  : authContorlList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 리스트
     * @param searchMap
     * @return
     */
    List<HashMap<String, Object>> authContorlList(	Map<String, Object> searchMap);
    /**
     * @Method Name  : authContorlListCount
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 리스트 총 갯수
     * @param searchMap
     * @return
     */
    int authContorlListCount(Map<String, Object> searchMap);
    /**
     * @Method Name  : authControlInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 등록 프로세스
     * @param params
     */
    void authControlInsertProcess(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlDetail
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 상세
     * @param params
     * @return
     */
    HashMap<String, Object> authControlDetail(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 수정 프로세스
     * @param params
     */
    void authControlUpdateProcess(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlMenuUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 수정 프로세스
     * @param params
     */
    void authControlMenuUpdateProcess(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 개별 삭제
     * @param params
     */
    void authControlDelete(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlCheckDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 일괄 삭제
     * @param params
     */
    void authControlCheckDelete(HashMap<String, Object> params);
    /**
     * @Method Name  : authControlOnMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리   온라인 등록 메뉴 리스트
     * @param params
     * @return
     */
    List<HashMap<String, Object>> authControlOnMenuList(	HashMap<String, Object> params);
    /**
     * @Method Name  : authControlOffMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리   오프라인 등록 메뉴 리스트
     * @param params
     * @return
     */
    List<HashMap<String, Object>> authControlOffMenuList(	HashMap<String, Object> params);
    /**
     * @Method Name  : authControlTestMenuList
     * @Date         : 2015. 05. 06.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    운영자 관리 -  권한관리   모의고사 등록 메뉴 리스트
     * @param params
     * @return
     */
    List<HashMap<String, Object>> authControlTestMenuList(   HashMap<String, Object> params);
    /**
     * @Method Name  : deleteAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 별  등록 메뉴 삭제
     * @param params
     */
    void deleteAuthMenu(HashMap<String, Object> params);
    /**
     * @Method Name  : authMenuInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 별   메뉴 등록
     * @param params
     */
    void authMenuInsertProcess(HashMap<String, Object> params);
    /**
     * @Method Name  : authMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 별   등록 메뉴 리스트
     * @param params
     * @return
     */
    List<HashMap<String, Object>> authMenuList(HashMap<String, Object> params);
    /**
     * @Method Name  : deleteSiteIdAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 별   등록된 메뉴 개별 삭제
     * @param params
     */
    void deleteSiteIdAuthMenu(HashMap<String, Object> params);
    /**
     * @Method Name  : deleteSiteIdCheckAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 -  권한관리  권한 별   등록된 메뉴 일괄 삭제
     * @param params
     */
    void deleteSiteIdCheckAuthMenu(HashMap<String, Object> params);

    /*
     * =====================권한 관리	end========================================
     * */
}
