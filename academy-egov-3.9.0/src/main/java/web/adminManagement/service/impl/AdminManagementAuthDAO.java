package web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class AdminManagementAuthDAO extends EgovComAbstractDAO {


    /**
     * @Method Name  : authContorlList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 리스트
     * @param searchMap
     * @return
     */
    public List<HashMap<String, Object>> authContorlList(Map<String, Object> searchMap) {
        return getSqlSession().selectList("adminManagementAuth.authContorlList", searchMap);
    }
    /**
     * @Method Name  : authContorlListCount
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 리스트 총 갯수
     * @param searchMap
     * @return
     */
    public int authContorlListCount(Map<String, Object> searchMap) {
        return getSqlSession().selectOne("adminManagementAuth.authContorlListCount", searchMap);
    }
    /**
     * @Method Name  : authControlInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 등록
     * @param params
     */
    public void authControlInsertProcess(HashMap<String, Object> params) {
        getSqlSession().insert("adminManagementAuth.authControlInsertProcess", params);
    }
    /**
     * @Method Name  : authControlDetail
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 상세
     * @param params
     * @return
     */
    public HashMap<String, Object> authControlDetail(HashMap<String, Object> params) {
        return getSqlSession().selectOne("adminManagementAuth.authControlDetail", params);
    }
    /**
     * @Method Name  : authControlUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 수정
     * @param params
     */
    public void authControlUpdateProcess(HashMap<String, Object> params) {
        getSqlSession().update("adminManagementAuth.authControlUpdateProcess", params);
    }
    /**
     * @Method Name  : authControlMenuUpdateProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 수정
     * @param params
     */
    public void authControlMenuUpdateProcess(HashMap<String, Object> params) {
        getSqlSession().update("adminManagementAuth.authControlMenuUpdateProcess", params);
    }

    /**
     * @Method Name  : authControlDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 개별 삭제
     * @param params
     */
    public void authControlDelete(HashMap<String, Object> params) {
        getSqlSession().delete("adminManagementAuth.authControlDelete", params);
    }
    /**
     * @Method Name  : authControlCheckDelete
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한 일괄 삭제
     * @param params
     */
    public void authControlCheckDelete(HashMap<String, Object> params) {
        getSqlSession().delete("adminManagementAuth.authControlCheckDelete", params);
    }
    /**
     * @Method Name  : authControlOnMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온라인 등록 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> authControlOnMenuList(HashMap<String, Object> params) {
        return getSqlSession().selectList("adminManagementAuth.authControlOnMenuList", params);
    }
    /**
     * @Method Name  : authControlOffMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 오프라인 등록 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> authControlOffMenuList(HashMap<String, Object> params) {
        return getSqlSession().selectList("adminManagementAuth.authControlOffMenuList", params);
    }
    /**
     * @Method Name  : authControlTestMenuList
     * @Date         : 2015. 05. 02.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    운영자 관리 - 권한관리 - 권한별 모의고사 등록 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> authControlTestMenuList(HashMap<String, Object> params) {
        return getSqlSession().selectList("adminManagementAuth.authControlTestMenuList", params);
    }
    /**
     * @Method Name  : deleteAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온/오프라인 등록 메뉴 삭제
     * @param params
     */
    public void deleteAuthMenu(HashMap<String, Object> params) {
        getSqlSession().delete("adminManagementAuth.deleteAuthMenu", params);
    }
    /**
     * @Method Name  : authMenuInsertProcess
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온/오프라인 메뉴 등록
     * @param params
     */
    public void authMenuInsertProcess(HashMap<String, Object> params) {
        getSqlSession().insert("adminManagementAuth.authMenuInsertProcess", params);
    }
    /**
     * @Method Name  : authMenuList
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온/오프라인 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> authMenuList(	HashMap<String, Object> params) {
        return getSqlSession().selectList("adminManagementAuth.authMenuList", params);
    }
    /**
     * @Method Name  : deleteSiteIdAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온/오프라인 메뉴 삭제
     * @param params
     */
    public void deleteSiteIdAuthMenu(HashMap<String, Object> params) {
        getSqlSession().delete("adminManagementAuth.deleteSiteIdAuthMenu", params);
    }
    /**
     * @Method Name  : deleteSiteIdCheckAuthMenu
     * @Date         : 2020.03
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	운영자 관리 - 권한관리 - 권한별 온/오프라인 메뉴  일괄 삭제
     * @param params
     */
    public void deleteSiteIdCheckAuthMenu(HashMap<String, Object> params) {
        getSqlSession().delete("adminManagementAuth.deleteSiteIdCheckAuthMenu", params);
    }
}
