package com.willbes.web.lecture.service;

import java.util.HashMap;
import java.util.List;

/**
 * @FileName   : SeriesCategoryService.java
 * @Project    :
 * @Date       : 2015. 05. 20.
 * @Author     : miraenet
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 service
 */
public interface CategoryService {

    /**
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	 트리 리스트
     * @return
     */
    List<HashMap<String, Object>> getSeriesCateTree();
    /**
     * @Method Name  : getDetail
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	 상세
     * @param params
     * @return
     */
    HashMap<String, Object> getDetail(HashMap<String, Object> params);
    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	 수정 프로세스
     * @param params
     */
    void updateProcess(HashMap<String, Object> params);
    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	삭제 프로세스
     * @param params
     * @return
     */
    int deleteProcess(HashMap<String, Object> params);

    /**
     * @Method Name  : idCheck
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	ID 중복체크
     * @param params
     * @return
     */
    int idCheck(HashMap<String, Object> params);
    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	등록 프로세스
     * @param params
     * @return
     */
    int insertProcess(HashMap<String, Object> params);

    HashMap<String, Object> getMaxOrdr(Object obj);
}
