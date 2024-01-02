package com.willbes.cmm.service.impl;

import java.util.Iterator;
import java.util.List;

import com.willbes.cmm.service.FileVO;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : EgovFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *    2014. 9. 14.     kckim     ibatis -> mybatis로 변경
 *                               일부 select -> selectOne으로 수정
 *
 *
 */
@Repository("FileManageDAO")
public class FileManageDAO extends EgovAbstractMapper {

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param vo
     * @throws Exception
     */
    public void insertFileInf(FileVO vo) throws Exception {
        insert("FileManageDAO.insertFile", vo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List<?> fileList) throws Exception {
        FileVO vo = (FileVO) fileList.get(0);
        String atchFileId = String.valueOf(vo.getATCH_FILE_ID());
        Iterator<?> iter = fileList.iterator();
        while (iter.hasNext()) {
            vo = (FileVO) iter.next();
            insert("FileManageDAO.insertFile", vo);
        }
        return atchFileId;
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     * select -> selectOne 으로 변경. by kckim.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
        return (FileVO) selectOne("FileManageDAO.selectFileDetail", fvo);
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectFileInfs(FileVO vo) throws Exception {
        return getSqlSession().selectList("FileManageDAO.selectFileList", vo);
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * select -> selectOne 으로 변경. by kckim.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(FileVO fvo) throws Exception {
        return (Integer) selectOne("FileManageDAO.getMaxFileSN", fvo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void updateFileInfs(List<?> fileList) throws Exception {
        FileVO vo;
        Iterator<?> iter = fileList.iterator();
        while (iter.hasNext()) {
            vo = (FileVO) iter.next();
            insert("FileManageDAO.insertFile", vo);
        }
    }

    /**
     * 하나의 파일을 삭제한다. (ATCH_FILE_ID, FILE_SN)으로 삭제
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(FileVO fvo) throws Exception {
        delete("FileManageDAO.deleteFileDetail", fvo);
    }

    /**
     * 여러 개의 파일을 삭제한다.( 다수의 ATCH_FILE_ID에 해당하는 모든 파일 삭제)
     *
     * @param fileList
     * @throws Exception
     */
    public void deleteFileInfs(List<?> fileList) throws Exception {
        Iterator<?> iter = fileList.iterator();
        FileVO vo;
        while (iter.hasNext()) {
            vo = (FileVO) iter.next();

            delete("FileManageDAO.deleteFile", vo);
        }
    }

    /**
     * ATCH_FILE_ID에 해당하는 모든 파일 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception {
        update("FileManageDAO.deleteFile", fvo);
    }

    /**
     * 파일 아이디 및 경로를 이용하여 파일를 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileByPath(FileVO fvo) throws Exception {
        update("FileManageDAO.deleteFileByPath", fvo);
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception {
        return getSqlSession().selectList("FileManageDAO.selectFileListByFileNm", fvo);
    }

    /**
     * 파일명 검색에 대한 목록 전체 건수를 조회한다.
     * select -> selectOne 으로 변경. by kckim.
     * @param fvo
     * @return
     * @throws Exception
     */
    public int selectFileListCntByFileNm(FileVO fvo) throws Exception {
        return (Integer) selectOne("FileManageDAO.selectFileListCntByFileNm", fvo);
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
        return getSqlSession().selectList("FileManageDAO.selectImageFileList", vo);
    }
}
