package com.willbes.cmm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.willbes.cmm.ComDefaultCodeVO;

/**
 *
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스
 * @author kckim
 * @since 2014. 9. 14.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.09.14  kckim          최초 생성
 *
 * </pre>
 */
public interface CmmUseService {

    /**
     * 공통코드를 조회한다.
     *
     * @param vo ComDefaultCodeVO
     * @return List(코드)
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, Object>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMap(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, Object>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortNum(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchMap Map<String, Object>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortStr(Map<String, String> searchMap) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchList Map<String, List<String>>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthList(Map<String, List<String>> searchList) throws Exception;

    /**
     * 공통코드를 조회한다.
     * Map을 이용하여 정보를 조회한다
     * @param searchArray Map<String, String[]>
     * @return List(코드)
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthArray(Map<String, String[]> searchArray) throws Exception;

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     *
     * @param voList
     * @return Map(코드)
     * @throws Exception
     */

    public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<?> voList) throws Exception;

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectUnivList(ComDefaultCodeVO vo) throws Exception;

    /**
     * 대학정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<HashMap<String, String>> selectUnivListMap(Map<String, String> searchMap) throws Exception;

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectSubjctList(ComDefaultCodeVO vo) throws Exception;

    /**
     * 학과정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<HashMap<String, String>> selectSubjctListMap(Map<String, String> searchMap) throws Exception;

    /**
     * 조직정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    /*public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception;*/

    /**
     * 그룹정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    /*public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception;*/

}
