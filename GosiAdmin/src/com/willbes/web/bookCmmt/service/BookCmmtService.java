package com.willbes.web.bookCmmt.service;

import java.util.HashMap;
import java.util.List;

/**
 * @FileName   : BookCmmtService.java
 * @Project    :
 * @Date       :
 * @Author     :
 * @변경이력    :
 * @프로그램 설명 : 교재구매후기 게시판 service
 */
public interface BookCmmtService {

    /**
     * @Method Name  : bookCmmtList
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 	 리스트
     * @param searchMap
     * @return
     */
    List<HashMap<String, Object>> bookCmmtList(Object obj);
    /**
     * @Method Name  : bookCmmtListCount
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	리스트 총 갯수
     * @param searchMap
     * @return
     */
    int bookCmmtListCount(Object obj);

    List<HashMap<String, Object>> bookCmmtDetailList(Object obj);

    int bookCmmtDetailListCount(Object obj);

    /**
     * @Method Name  : cmmtDelete
     * @Date         :
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	 삭제
     * @param params
     */
    void cmmtDelete(Object obj);

}
