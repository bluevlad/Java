package com.willbes.web.bookCmmt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class BookCmmtDAO extends CmmAbstractMapper {

    /**
     * @Method Name  : bookCmmtList
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	 리스트
     * @param searchMap
     * @return
     */
    public List<HashMap<String, Object>> bookCmmtList(Object obj) {
        return getSqlSession().selectList("bookCmmnt.bookCmmtList", obj);
    }
    /**
     * @Method Name  : bookCmmtListCount
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	 리스트 총 갯수
     * @param searchMap
     * @return
     */
    public int bookCmmtListCount(Object obj) {
        return getSqlSession().selectOne("bookCmmnt.bookCmmtListCount", obj);
    }
    public List<HashMap<String, Object>> bookCmmtDetailList(Object obj) {
        return getSqlSession().selectList("bookCmmnt.bookCmmtDetailList", obj);
    }

    public int bookCmmtDetailListCount(Object obj) {
        return getSqlSession().selectOne("bookCmmnt.bookCmmtDetailListCount", obj);
    }
    /**
     * @Method Name  : cmmtDelete
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :
     * @param params
     */
    public void cmmtDelete(Object obj) {
        getSqlSession().delete("bookCmmnt.cmmtDelete", obj);
    }
}
