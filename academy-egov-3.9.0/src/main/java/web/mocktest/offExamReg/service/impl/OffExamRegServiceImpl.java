package com.willbes.web.mocktest.offExamReg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.mocktest.offExamReg.OffExamRegVO;
import com.willbes.web.mocktest.offExamReg.service.OffExamRegService;

@Service
public class OffExamRegServiceImpl implements OffExamRegService {
    /** log */
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private OffExamRegDAO offExamRegDao;

    @Override
    public List<HashMap<String, String>> offExamList(HashMap<String, String> params) {
        return offExamRegDao.offExamList(params);
    }

    @Override
    public int listCount(HashMap<String, String> params) {
        return offExamRegDao.listCount(params);
    }

    @Override
    public HashMap<String, String> offExamView(HashMap<String, Object> params) {
        return offExamRegDao.offExamView(params);
    }

    @Override
    public HashMap<String, String> offExamOfferConfirm(HashMap<String, String> params) {
        return offExamRegDao.offExamOfferConfirm(params);
    }

    @Override
    public HashMap<String, String> offExamOfferGetSubjectCode(HashMap<String, String> params) {
        return offExamRegDao.offExamOfferGetSubjectCode(params);
    }

    @Override
    public void offExanInsertData(HashMap<String, Object> params) {
        offExamRegDao.offExanInsertData(params);
    }
    @Override
    public int offExamDelete(HashMap<String, Object> params) {
        return offExamRegDao.offExamDelete(params);
    }
    @Override
    public int offExamGradeDelete(HashMap<String, Object> params) {
        return offExamRegDao.offExamGradeDelete(params);
    }
    @Override
    public int offExamDeleteFile(HashMap<String, Object> params) {
        return offExamRegDao.offExamDeleteFile(params);
    }
    @Override
    public List<HashMap<String, String>> offExanUpdateList(){
        return offExamRegDao.offExanUpdateList();
    }
    @Override
    public int updateCorrectYn(HashMap<String, String> params){
        return offExamRegDao.updateCorrectYn(params);
    }

    @Override
    public List<HashMap<String, String>> offListPop(HashMap<String, String> params) {
        return offExamRegDao.offListPop(params);
    }
    @Override
    public int popListCount(HashMap<String, String> params) {
        return offExamRegDao.popListCount(params);
    }
    @Override
    public int insertFileInfo(HashMap<String, Object> params) {
        return offExamRegDao.insertFileInfo(params);
    }
    @Override
    public int getAdujustGrade(HashMap<String, Object> map) {
        return offExamRegDao.getAdujustGrade(map);
    }
    @Override
    public List<HashMap<String, Object>> offExamGradeList(HashMap<String, Object> params) {
        return offExamRegDao.offExamGradeList(params);
    }
    @Override
    public void insertTmockGrade(HashMap<String, Object> params) {
        offExamRegDao.insertTmockGrade(params);
    }
    @Override
    public void updateAdjGrade(HashMap<String, Object> map) {
        offExamRegDao.updateAdjGrade(map);
    }

    @Override
    public void offExamUpdateCorrectYN(HashMap<String, String> params) {
        offExamRegDao.offExamUpdateCorrectYN(params);
    }

    @Override
    public void updateAdjustGradeTmockgradeByMockcode(HashMap<String, Object> gradeParamMap) {
        offExamRegDao.updateAdjustGradeTmockgradeByMockcode(gradeParamMap);
    }

    @Override
    public void updateTOffererExamstatus(HashMap<String, String> params) {
        offExamRegDao.updateTOffererExamstatus(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<OffExamRegVO> insertExcelFileData(HashMap<String, Object> params) throws Exception {

        String mockCode =  params.get("mockCode").toString();
        String REG_ID =  params.get("REG_ID").toString();

        ArrayList<OffExamRegVO> errors = new ArrayList<OffExamRegVO>();

        // 파일 정보 insert
        HashMap<String, Object> fiileInfoMap = (HashMap<String, Object>)params.get("fileInfoMap");
        try {
            offExamRegDao.insertFileInfo(fiileInfoMap);
        } catch (Exception e) {
            throw e;
        }

        ArrayList<OffExamRegVO> read =  (ArrayList<OffExamRegVO>)params.get("excelFileData");
        if (log.isDebugEnabled()) {
            log.debug("***** off응시자등록 엑셀 데이터 ***** ");
            log.debug("***** read.size() : " + read.size());
        }

        for (int k = 0; k < read.size(); k++) {
            OffExamRegVO vo = read.get(k);

            String identyId = vo.getIDENTITY_ID();
            String subjectInfo = vo.getSUBJECT_NM();
            String makingInfo = vo.getMARKINGS();
            if (log.isDebugEnabled()) {
                log.debug("***** index " + k);
                log.debug("***** identyId " + identyId);
                log.debug("***** subjectInfo " + subjectInfo);
                log.debug("***** makingInfo " + makingInfo);
            }

            if (null != makingInfo && !"".equals(makingInfo) && !makingInfo.isEmpty()) {
                String[] making_arr = makingInfo.split("/");
                if (making_arr.length > 10) {
                    HashMap<String, String> paramMap = new HashMap<String, String>();
                    paramMap.put("identyId", identyId);
                    paramMap.put("subjectInfo", subjectInfo);
                    paramMap.put("mockCode", mockCode);

                    //신청자 관리 테이블에 신청 내역이 있는지 확인
                    HashMap<String, String> confirmMap = offExamRegDao.offExamOfferConfirm(paramMap);

                    if(confirmMap != null){
                        //과목명과  모의고사 코드를 이용하여 문제은행구분번호와  모의고사 코드를 가져온다
                        HashMap<String, String> subjectCode = offExamRegDao.offExamOfferGetSubjectCode(paramMap);
                        if (subjectCode != null) {
                            String quesMockCode =  subjectCode.get("MOCKCODE");
                            String itemId =  String.valueOf(subjectCode.get("ITEMID"));

                            //인설트 맵 setting -  문항번호  ,  문제은행구분번호 , 응시번호 , 모의고사코드 , 문제답변 , 생성자 , 생성일시 , 수정자 , 수정일시
                            for (int i = 1; i < making_arr.length+1; i++) {
                                //최종 응시자답변 테이블에 인설트될 데이터 맵
                                HashMap<String, Object> insertMap = new HashMap<String, Object>();
                                insertMap.put("questionNumber", i); //문항번호
                                insertMap.put("itemId", String.valueOf(itemId)); //문제은행구분번호
                                insertMap.put("identyId", String.valueOf(identyId)); //응시번호
                                insertMap.put("mockCode", quesMockCode); // 모의고사 코드

                                switch (making_arr[i-1]) {
                                case "1" : insertMap.put("answerNumber", making_arr[i-1] ); break; // 문제답변
                                case "2" : insertMap.put("answerNumber", making_arr[i-1] ); break;
                                case "3" : insertMap.put("answerNumber", making_arr[i-1] ); break;
                                case "4" : insertMap.put("answerNumber", making_arr[i-1] ); break;
                                case "5" : insertMap.put("answerNumber", making_arr[i-1] ); break;
                                default : insertMap.put("answerNumber", making_arr[i-1] ); break; // 문제답변
                                }
                                insertMap.put("createId", REG_ID ); // 생성자
                                insertMap.put("correctYn", "N" ); // 정답여부

                                try {
                                    offExamRegDao.offExanInsertData(insertMap);
                                } catch (Exception ex) {
                                    vo.setERR_YN("Y");
                                    vo.setERR_DESC("답안지 정보 등록 오류");
                                    continue;
                                }
                            }
                        } else {
                            vo.setERR_YN("Y");
                            vo.setERR_DESC("모의고사 정보 및 문제은행구분번호 불일치");
                        }
                    } else {
                        vo.setERR_YN("Y");
                        vo.setERR_DESC("응시자정보 누락");
                    }
                } else {
                    vo.setERR_YN("Y");
                    vo.setERR_DESC("마킹정보 10개 이하");
                }
            } else {
                vo.setERR_YN("Y");
                vo.setERR_DESC("마킹정보 누락");
            }

            //정답 일괄 업데이트 (업로드하는 모의고사코드에 대한 답안지만)
            HashMap<String, String> sparams =  new HashMap<String, String>();
            sparams.put("identyId", identyId);
            sparams.put("mockCode", mockCode);

            try {
                offExamRegDao.offExamUpdateCorrectYN(sparams);
            } catch (Exception ex2) {
                vo.setERR_YN("Y");
                vo.setERR_DESC("답안지 정오표 변경 오류");
                errors.add(vo);
                continue;
            }

            try {
                offExamRegDao.updateTOffererExamstatus(sparams);
            } catch (Exception ex2) {
                vo.setERR_YN("Y");
                vo.setERR_DESC("시험응시 정보 변경 오류");
                errors.add(vo);
                continue;
            }

            if("Y".equals(vo.getERR_YN())) {
                errors.add(vo);
            }
        }

        // 모의고사 성적을 TMOCKGRADE 테이블에 저장한다.
        HashMap<String, Object> gradeParamMap = new HashMap<String, Object>();
        gradeParamMap.put("mockCode", mockCode);
        // TMOCKGRADE 테이블에서 해당 모의고사 코드에 해당하는 데이터를 모두 삭제한다.
        offExamRegDao.offExamGradeDelete(gradeParamMap);

        // 모의고사 접수자 중에서 결제완료(PAYMENTSTATE=1)되고 응시 제출완료(EXAMSTATUS=3)한 신청자에 대하여
        // 전달한 모의고사코드(MOCKCODE)에 대한 모든 응시과목의 원점수와 조정점수를 동일하게 TMOCKGRADE 테이블에 삽입한다.
        // 온라인, 오프라인 구분없이 처리한다.
        offExamRegDao.insertTmockGrade(gradeParamMap);

        // 선택과목인 경우 TMOCKGRADE 테이블에서 조정점수를 업데이트 한다.
        offExamRegDao.updateAdjustGradeTmockgradeByMockcode(gradeParamMap);

        return errors;

    }

}
