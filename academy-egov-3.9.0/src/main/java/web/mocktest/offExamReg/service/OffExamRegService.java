package web.mocktest.offExamReg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import web.mocktest.offExamReg.OffExamRegVO;

public interface OffExamRegService {

    List<HashMap<String, String>> offExamList(HashMap<String, String> params);

    int listCount(HashMap<String, String> params);

    HashMap<String, String> offExamView(HashMap<String, Object> params);

    HashMap<String, String>offExamOfferConfirm(HashMap<String, String> params);

    HashMap<String, String> offExamOfferGetSubjectCode(HashMap<String, String> paramMap);

    void offExanInsertData(HashMap<String, Object> insertMap);

    int offExamDelete(HashMap<String, Object> params);

    List<HashMap<String, String>> offExanUpdateList();

    int updateCorrectYn(HashMap<String, String> params);

    List<HashMap<String, String>> offListPop(HashMap<String, String> params);

    int popListCount(HashMap<String, String> params);

    int insertFileInfo(HashMap<String, Object> fiileInfoMap);

    int offExamDeleteFile(HashMap<String, Object> params);

    List<HashMap<String, Object>> offExamGradeList(HashMap<String, Object> params);

    int getAdujustGrade(HashMap<String, Object> map);

    void insertTmockGrade(HashMap<String, Object> params);

    void updateAdjGrade(HashMap<String, Object> map);

    int offExamGradeDelete(HashMap<String, Object> params);

    void offExamUpdateCorrectYN(HashMap<String, String> params);

    void updateAdjustGradeTmockgradeByMockcode(HashMap<String, Object> gradeParamMap);

    void updateTOffererExamstatus(HashMap<String, String> params);

    ArrayList<OffExamRegVO> insertExcelFileData(HashMap<String, Object> params) throws Exception;

}
