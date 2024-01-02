package com.willbes.cmm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.willbes.cmm.ComDefaultCodeVO;
import com.willbes.cmm.service.CmmUseService;
import com.willbes.cmm.service.CmmnDetailCode;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : CmmUseServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
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
@Service("CmmUseService")
public class CmmUseServiceImpl extends EgovAbstractServiceImpl implements CmmUseService {

    @Resource(name = "cmmUseDAO")
    private CmmUseDAO cmmUseDAO;

    /**
     * 공통코드를 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeDetail(vo);
    }

    /**
     * 공통코드를 조회한다.
     * Map 형식을 이용한 정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMap(Map<String, String> searchMap) throws Exception {
        return cmmUseDAO.selectCmmCodeMap(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map 형식을 이용한 정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortNum(Map<String, String> searchMap) throws Exception {
        return cmmUseDAO.selectCmmCodeMapSortNum(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map 형식을 이용한 정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCodeMapSortStr(Map<String, String> searchMap) throws Exception {
        return cmmUseDAO.selectCmmCodeMapSortStr(searchMap);
    }

    /**
     * 공통코드를 조회한다.
     * Map 형식을 이용한 정보 조회
     * @param vo(list)
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthList(Map<String, List<String>> searchList) throws Exception {
        return cmmUseDAO.selectCmmCdMultiCondWthList(searchList);
    }

    /**
     * 공통코드를 조회한다.
     * Map 형식을 이용한 정보 조회
     * @param vo(array)
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> selectCmmCdMultiCondWthArray(Map<String, String[]> searchArray) throws Exception {
        return cmmUseDAO.selectCmmCdMultiCondWthArray(searchArray);
    }

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     *
     * @param voList
     * @return
     * @throws Exception
     */
    public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<?> voList) throws Exception {
        ComDefaultCodeVO vo;
        Map<String, List<CmmnDetailCode>> map = new HashMap<String, List<CmmnDetailCode>>();

        Iterator<?> iter = voList.iterator();
        while (iter.hasNext()) {
            vo = (ComDefaultCodeVO)iter.next();
            map.put(vo.getSYS_CD(), cmmUseDAO.selectCmmCodeDetail(vo));
        }

        return map;
    }

    @Override
    public List<CmmnDetailCode> selectUnivList(ComDefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectUnivList(vo);
    }

    @Override
    public List<HashMap<String, String>> selectUnivListMap(Map<String, String> searchMap) throws Exception {
        return cmmUseDAO.selectUnivListMap(searchMap);
    }

    @Override
    public List<CmmnDetailCode> selectSubjctList(ComDefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectSubjctList(vo);
    }

    @Override
    public List<HashMap<String, String>> selectSubjctListMap(Map<String, String> searchMap) throws Exception {
        return cmmUseDAO.selectSubjctListMap(searchMap);
    }

    /**
     * 조직정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    /*public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectOgrnztIdDetail(vo);
    }*/

    /**
     * 그룹정보를 코드형태로 리턴한다.
     *
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    /*public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectGroupIdDetail(vo);
    }*/

}
