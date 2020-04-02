package web.index.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class IndexDAO extends EgovComAbstractDAO {

    public List<HashMap<String, String>> list(HashMap<String, String> params){
        return getSqlSession().selectList("index.list", params);
    }

    public int listCount(HashMap<String, String> params){
        return getSqlSession().selectOne("index.listCount", params);
    }

    /**
     * @Method Name  : getTopMenuList
     * @Date         : 2013. 10. 29.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      : 로그인 ID 별 TOP 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> getTopMenuList(	HashMap<String, Object> params) {
        return getSqlSession().selectList("index.getTopMenuList", params);
    }

    /**
     * @Method Name  : getLeftMenuList
     * @Date         : 2013. 10. 29.
     * @Author       : rainend
     * @변경이력      :
     * @Method 설명      :	로그인 ID 별 LEFT 메뉴 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> getLeftMenuList(	HashMap<String, Object> params) {
        return getSqlSession().selectList("index.getLeftMenuList", params);
    }

    /**
     * @Method Name  : selectLeftMenuTree
     * @Date         : 2015. 04. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    LEFT 메뉴 트리 리스트
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> selectLeftMenuTree(   HashMap<String, Object> params) {
        return getSqlSession().selectList("index.selectLeftMenuTree", params);
    }

}
