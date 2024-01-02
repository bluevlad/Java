package com.willbes.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.willbes.cmm.ComDefaultCodeVO;
import com.willbes.cmm.service.CmmnDetailCode;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CmmUseDAO.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
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
@Repository("cmmUseDAO")
public class CmmUseDAO extends EgovAbstractMapper {

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (CODE_NO)를 이용하여 조회
     * @return list CmmnDetailCode
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCodeDetail", vo);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (SYS_CD)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMap(Map<String, String> searchMap) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCodeMap", searchMap);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (SYS_CD)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortNum(Map<String, String> searchMap) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCodeMapSortNum", searchMap);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (SYS_CD)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortStr(Map<String, String> searchMap) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCodeMapSortStr", searchMap);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (list)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthList(Map<String, List<String>> searchList) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCdMultiCondWthList", searchList);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (list)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthArray(Map<String, String[]> searchArray) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCdMultiCondWthArray", searchArray);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo (CODE_NO)를 이용하여 조회
     * @return list CmmnDetailCode
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCode(ComDefaultCodeVO vo) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectCmmCodeByNo", vo);
    }

    /**
     * 주어진 조건에 따른 대학정보를 불러온다.
     *
     * @param vo (CODE_NO)를 이용하여 조회
     * @return list CmmnDetailCode
     * @throws Exception
     */
    public List<CmmnDetailCode> selectUnivList(ComDefaultCodeVO vo) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectUnivList", vo);
    }

    /**
     * 주어진 조건에 따른 대학정보를 불러온다.
     *
     * @param vo (SYS_CD)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectUnivListMap(Map<String, String> searchMap) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectUnivListMap", searchMap);
    }

    /**
     * 주어진 조건에 따른 학과정보를 불러온다.
     *
     * @param vo (CODE_NO)를 이용하여 조회
     * @return list CmmnDetailCode
     * @throws Exception
     */
    public List<CmmnDetailCode> selectSubjctList(ComDefaultCodeVO vo) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectSubjctList", vo);
    }

    /**
     * 주어진 조건에 따른 학과정보를 불러온다.
     *
     * @param vo (SYS_CD)를 이용하여 조회
     * @return list HashMap
     * @throws Exception
     */
    public List<HashMap<String, String>> selectSubjctListMap(Map<String, String> searchMap) throws Exception {
        return getSqlSession().selectList("cmmUseDAO.selectSubjctListMap", searchMap);
    }

    /**
     * 공통코드로 사용할 조직정보를 를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    /*@SuppressWarnings({ "unchecked", "deprecation" })
    public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception {
        return (List<CmmnDetailCode>) list("cmmUseDAO.selectOgrnztIdDetail", vo);
    }*/

    /**
     * 공통코드로 사용할그룹정보를 를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    /*@SuppressWarnings({ "unchecked", "deprecation" })
    public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception {
        return (List<CmmnDetailCode>) list("cmmUseDAO.selectGroupIdDetail", vo);
    }*/
}
