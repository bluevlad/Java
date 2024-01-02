package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class CategoryDAO extends CmmAbstractMapper {

    /**
     * @Method Name  : getTree
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 트리 리스트
     * @return
     */
    public List<HashMap<String, Object>> getSeriesCateTree() {
        return getSqlSession().selectList("kind.getSeriesCateTree");
    }
    /**
     * @Method Name  : getDetail
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 상세
     * @param params
     * @return
     */
    public HashMap<String, Object> getDetail(HashMap<String, Object> params) {
        return getSqlSession().selectOne("series.seriesView", params);
    }
    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 수정
     * @param params
     */
    public void updateProcess(HashMap<String, Object> params) {
        getSqlSession().update("series.seriesUpdate", params);
    }
    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 삭제
     * @param params
     * @return
    public int deleteProcess(HashMap<String, Object> params) {
        return getSqlSession().delete("series.seriesDelete", params);
    }
     */
    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    직렬 삭제
     * @param params
     * @return
     */
    public int deleteCategory(HashMap<String, Object> params) {
        return getSqlSession().delete("series.categoryDelete", params);
    }
    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    직렬 삭제
     * @param params
     * @return
     */
    public int deleteSeries(HashMap<String, Object> params) {
        return getSqlSession().delete("series.seriesDelete", params);
    }
    /**
     * @Method Name  : idCheck
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 ID 중복 체크
     * @param params
     * @return
     */
    public int idCheck(HashMap<String, Object> params) {
        return getSqlSession().selectOne("series.seriesCheck", params);
    }
    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 등록
     * @param params
     * @return
     */
    public int insertProcess(HashMap<String, Object> params) {
        return getSqlSession().insert("series.seriesInsert", params);
    }
    /**
     * @Method Name  : getMaxOrdr
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	직렬 등록시 ID 맥스값 추출
     * @param params
     * @return
     */
    public HashMap<String, Object> getMaxOrdr(Object obj) {
        return getSqlSession().selectOne("kind.getMaxOrdr", obj);
    }

}
