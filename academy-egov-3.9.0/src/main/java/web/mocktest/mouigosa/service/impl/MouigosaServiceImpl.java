package web.mocktest.mouigosa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mocktest.mouigosa.service.MouigosaService;

@Service
public class MouigosaServiceImpl  implements  MouigosaService{ 

    @Autowired
    private MouigosaDAO mouigosadao;

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteQuestionMouigosa(Map keyName) {
        return mouigosadao.deleteQuestionMouigosa(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public int getQuestionNumMax(Map keyName) {
        return mouigosadao.getQuestionNumMax(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public int getItemIdMax(Map keyName) {
        return mouigosadao.getItemIdMax(keyName);
    }

    // 첨부파일 max값
    @Override @SuppressWarnings("rawtypes")
    public int getFileCount(Map keyName) {
        return mouigosadao.getFileCount(keyName);
    }

    // 모의고사 과목 리스트
    @Override @SuppressWarnings("rawtypes")
    public List getMouigosaList(Map keyName){
        return mouigosadao.getMouigosaList(keyName);
    }

    // 총 건수
    @Override @SuppressWarnings("rawtypes")
    public int getCount(Map keyName) {
        return mouigosadao.getCount(keyName);
    }

    // 모의고사 사용 과목 조회
    @Override @SuppressWarnings("rawtypes")
    public List getClass(Map keyName) {
        return mouigosadao.getClass(keyName);
    }

    // 모의고사 사용 과목 조회
    @Override @SuppressWarnings("rawtypes")
    public List getClassSubject(Map keyName) {
        return mouigosadao.getClassSubject(keyName);
    }

    // 모의고사 과목 등록 팝업화면 과목 조회
    @Override @SuppressWarnings("rawtypes")
    public List getSubjectList(Map keyName) {
        return mouigosadao.getSubjectList(keyName);
    }

    // 교수 가져오기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode(Map keyName) {
        return mouigosadao.getSubCode(keyName);
    }

    // 년.회차,과목 중복체크
    @Override @SuppressWarnings("rawtypes")
    public List getDuplication(Map keyName) {
        return mouigosadao.getDuplication(keyName);
    }

    // 직렬별과목 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertTccsrsSubject(Map keyName) {
        return mouigosadao.insertTccsrsSubject(keyName);
    }

    // 직렬별과목 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateTccsrsSubject(Map keyName) {
        return mouigosadao.updateTccsrsSubject(keyName);
    }

    // 직렬별과목 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteTccsrsSubject(Map keyName) {
        return mouigosadao.deleteTccsrsSubject(keyName);
    }

    // 과목 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertMouigosa(Map keyName) {
        return mouigosadao.insertMouigosa(keyName);
    }

    // 과목과 문제 동시 등록
    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertMouigosaWthQuestion(Map keyName) {

        int ITEMID = mouigosadao.insertMouigosaWthRetVal(keyName);
        keyName.put("ITEMID", ITEMID);

        int QUESTIONNUM = 0;
        if(null != keyName.get("QUESTIONNUM") && !"".equals(keyName.get("QUESTIONNUM").toString())) {
            QUESTIONNUM = Integer.parseInt(keyName.get("QUESTIONNUM").toString());
        }
        for (int i = 1; i <= QUESTIONNUM; i++) {
            mouigosadao.insertQuestionMouigosa(keyName);
        }

        return ITEMID;
    }

    // 모의고사 과목 수정 팝업화면 불러오기 상세
    @Override @SuppressWarnings("rawtypes")
    public List getUpdateDetail(Map keyName) {
        return mouigosadao.getUpdateDetail(keyName);
    }

    // 과목 수정
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateMouigosa(Map keyName) {
        return mouigosadao.updateMouigosa(keyName);
    }

    // 과목 수정( 문제 수정)
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateMouigosaWthQuestion(Map keyName) {

        int QUESTIONNUM = 0;
        if(null != keyName.get("QUESTIONNUM") && !"".equals(keyName.get("QUESTIONNUM").toString())) {
            QUESTIONNUM = Integer.parseInt(keyName.get("QUESTIONNUM").toString());
        }
        int QUESTIONNUMMAX = getQuestionNumMax(keyName);
        if (QUESTIONNUMMAX > QUESTIONNUM) {
            int QUESTIONNUMMAX_M = QUESTIONNUMMAX - QUESTIONNUM;
            for (int i = 1; i <= QUESTIONNUMMAX_M; i++) {
                mouigosadao.deleteQuestionMouigosa(keyName);
            }
        }else if (QUESTIONNUMMAX < QUESTIONNUM) {
            int QUESTIONNUMMAX_M = QUESTIONNUM - QUESTIONNUMMAX;
            for (int i = 1; i <= QUESTIONNUMMAX_M; i++) {
                mouigosadao.insertQuestionMouigosa(keyName);
            }
        }

        return mouigosadao.updateMouigosa(keyName);
    }

    // 과목 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTitem(Map keyName) {
        return mouigosadao.deleteMouigosaTitem(keyName);
    }

    // 문제 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTitemPool(Map keyName) {
        return mouigosadao.deleteMouigosaTitemPool(keyName);
    }

    // 과목 삭제( 문제풀 + 문제)
    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaQuestion(Map keyName) {

        int deleteCount = 0;
        String deleteIds = null;
        if(null != keyName.get("deleteIds") && !"".equals(keyName.get("deleteIds").toString())) {
            deleteIds = keyName.get("deleteIds").toString();
        }

        if(deleteIds.length() > 0) {
            String [] tmp = deleteIds.split(",");
            for (int j = 0; j < tmp.length; j++) {
                String ITEMID = tmp[j];
                keyName.put("ITEMID", ITEMID);

                //과목삭제
                mouigosadao.deleteMouigosaTitem(keyName);
                //문제삭제
                mouigosadao.deleteMouigosaTitemPool(keyName);

                deleteCount++;
            }
        }

        return deleteCount;
    }

    //문제 프린트 팝업 리스트
    @Override @SuppressWarnings("rawtypes")
    public List getQuestionList(Map keyName){
        return mouigosadao.getQuestionList(keyName);
    }

    //==================================================================//

    @Override @SuppressWarnings("rawtypes")
    public List getMouigosaQuestionList(Map keyName){
        return mouigosadao.getMouigosaQuestionList(keyName);
    }

    // 총 건수
    @Override @SuppressWarnings("rawtypes")
    public int getMouigosaQuestionCount(Map keyName) {
        return mouigosadao.getMouigosaQuestionCount(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getSubjectAreaList(Map keyName) {
        return mouigosadao.getSubjectAreaList(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertQuestionMouigosa(Map keyName) {
        return mouigosadao.insertQuestionMouigosa(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getUpdateQuestionDetail(Map keyName){
        return mouigosadao.getUpdateQuestionDetail(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateQuestionMouigosa(Map keyName) {

        int result = mouigosadao.updateQuestionMouigosa(keyName);
        if(result >= 0) {
            //Procedure 호출, TB_TEXAMINEEANSWER : 정오답 UPDATE
            result = mouigosadao.updateExamAnswerYN(keyName);
            //Procedure 호출, TB_TMOCKGRADE : 점수 갱신
            result = mouigosadao.updateUserGrade(keyName);
            //Procedure 호출, TB_TMOCKGRADE : 점수 갱신
            result = mouigosadao.updateUserGradeAdjust(keyName);
        }

        return result;
    }

    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateQuestionMouigosas(Map keyName) {

        int updateCount = 0;
        ArrayList<HashMap<String, String>> read = null;
        if(null != keyName.get("excelDATAs")) {
            read = (ArrayList<HashMap<String, String>>)keyName.get("excelDATAs");
        }

        for (int k = 0; k < read.size(); k++) {
            //QUESTIONNUMBER = (String)read.get(k).get("번호");
            String QUESTION = (String)read.get(k).get("문제와지문");
            String QUESTIONRANGE = (String)read.get(k).get("영역");
            String LEVELDIFFICULTY = (String)read.get(k).get("난이도");
            String ANSWERNUMBER = (String)read.get(k).get("정답");
            String ANSWEREXPLAIN = (String)read.get(k).get("해설");

            QUESTION = QUESTION.replaceAll("\r\n", "<br>");
            ANSWEREXPLAIN = ANSWEREXPLAIN.replaceAll("\r\n", "<br>");
            QUESTION = QUESTION.replaceAll("\u0020", "&nbsp;");
            ANSWEREXPLAIN = ANSWEREXPLAIN.replaceAll("\u0020", "&nbsp;");

            keyName.put("QUESTIONNUMBER", k+1);
            keyName.put("QUESTION", QUESTION);
            keyName.put("QUESTIONRANGE", QUESTIONRANGE);
            keyName.put("LEVELDIFFICULTY", LEVELDIFFICULTY);
            keyName.put("ANSWERNUMBER", ANSWERNUMBER);
            keyName.put("ANSWEREXPLAIN", ANSWEREXPLAIN);

            mouigosadao.updateQuestionMouigosa(keyName);

            updateCount++;
        }

        return updateCount;
    }

    //==================================================================//

    @Override @SuppressWarnings("rawtypes")
    public List getExamYear(Map keyName){
        return mouigosadao.getExamYear(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getExamRound(Map keyName){
        return mouigosadao.getExamRound(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getQuestionNumber(Map keyName){
        return mouigosadao.getQuestionNumber(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getQuestionAllList(Map keyName){
        return mouigosadao.getQuestionAllList(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getQuestionAllList2(Map keyName){
        return mouigosadao.getQuestionAllList2(keyName);
    }

    // 모의고사 등록 등록
    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateQuestionAll(Map keyName) {

        int updateCount = 0;
        //문제지정 수정
        String[] EXAMYEAR = (String[])keyName.get("EXAMYEAR");
        String[] EXAMROUND = (String[])keyName.get("EXAMROUND");
        String[] QUESTIONNUMBER2 = (String[])keyName.get("QUESTIONNUMBER2");
        String[] QUESTIONNUMBER = (String[])keyName.get("QUESTIONNUMBER");

        for (int i = 0; i < EXAMYEAR.length; i++) {
            if (EXAMYEAR[i].length() > 0) {

                keyName.put("EXAMYEAR", EXAMYEAR[i]);
                keyName.put("EXAMROUND", EXAMROUND[i]);
                keyName.put("QUESTIONNUMBER2", QUESTIONNUMBER2[i]);
                keyName.put("QUESTIONNUMBER", QUESTIONNUMBER[i]);

                //수정 데이터를 위한 문제 리스트
                List<?> list = mouigosadao.getQuestionAllList2(keyName);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map = (HashMap<String, Object>) list.get(0);
                keyName.put("QUESTION",map.get("QUESTION"));
                keyName.put("QUESTIONFILEID",map.get("QUESTIONFILEID"));
                keyName.put("ANSWEREXPLAIN",map.get("ANSWEREXPLAIN"));
                keyName.put("ANSWEREXPLAINFILEID",map.get("ANSWEREXPLAINFILEID"));
                keyName.put("ANSWERNUMBER",map.get("ANSWERNUMBER"));
                keyName.put("QUESTIONRANGE",map.get("QUESTIONRANGE"));
                keyName.put("LEVELDIFFICULTY",map.get("LEVELDIFFICULTY"));

                mouigosadao.updateQuestionAll(keyName);

                updateCount++;
            }
        }
        return updateCount;
    }

    // 블러오기 팝업 리스트
    @Override @SuppressWarnings("rawtypes")
    public List getMouigosaPopList(Map keyName){
        return mouigosadao.getMouigosaPopList(keyName);
    }

    // 블러오기 팝업 리스트 카운트
    @Override @SuppressWarnings("rawtypes")
    public int getPopCount(Map keyName) {
        return mouigosadao.getPopCount(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateQuestionOnly(Map keyName) {
        return mouigosadao.updateQuestionOnly(keyName);
    }

    //==================================================================//

    @Override @SuppressWarnings("rawtypes")
    public List getMouigosaRegistrationList(Map keyName){
        return mouigosadao.getMouigosaRegistrationList(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List D_getMouigosaRegistrationList(Map keyName){
        return mouigosadao.D_getMouigosaRegistrationList(keyName);
    }

    // 총 건수
    @Override @SuppressWarnings("rawtypes")
    public int getRegistrationCount(Map keyName) {
        return mouigosadao.getRegistrationCount(keyName);
    }
    
    // 동형모의고사 총 건수
    @Override @SuppressWarnings("rawtypes")
    public int D_getRegistrationCount(Map keyName) {
        return mouigosadao.D_getRegistrationCount(keyName);
    }

    // 모의고사 등록 등록 팝업화면 '직급' 조회
    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationList(Map keyName) {
        return mouigosadao.getRegistrationList(keyName);
    }

    // 직렬 가져오기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode2(Map keyName) {
        return mouigosadao.getSubCode2(keyName);
    }

    // 필수과목(교수) 비동기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode3(Map keyName) {
        return mouigosadao.getSubCode3(keyName);
    }

    // 선택과목(교수) 비동기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode4(Map keyName) {
        return mouigosadao.getSubCode4(keyName);
    }

    // 필수과목(교수) 비동기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode3_ins(Map keyName) {
        return mouigosadao.getSubCode3_ins(keyName);
    }

    // 선택과목(교수) 비동기
    @Override @SuppressWarnings("rawtypes")
    public List getSubCode4_ins(Map keyName) {
        return mouigosadao.getSubCode4_ins(keyName);
    }

    //==================================================================//

    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationExamYear(Map keyName){
        return mouigosadao.getRegistrationExamYear(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationExamRound(Map keyName){
        return mouigosadao.getRegistrationExamRound(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationSubject(Map keyName){
        return mouigosadao.getRegistrationSubject(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationPopList(Map keyName){
        return mouigosadao.getRegistrationPopList(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    public int getRegistrationPopCount(Map keyName) {
        return mouigosadao.getRegistrationPopCount(keyName);
    }

    //==================================================================//

    // 모의고사 등록 max값
    @Override @SuppressWarnings("rawtypes")
    public List getRegistrationMax(Map keyName) {
        return mouigosadao.getRegistrationMax(keyName);
    }

    // 모의고사 등록 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertRegistrationMouigosa(Map keyName) {
        return mouigosadao.insertRegistrationMouigosa(keyName);
    }

    // 모의고사 등록 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertMouigosaTmocksubject(Map keyName) {
        return mouigosadao.insertMouigosaTmocksubject(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmockSubject_ck(Map keyName) {
        return mouigosadao.deleteMouigosaTmockSubject_ck(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_Mock_Insert(Map keyName) {
        return mouigosadao.D_Mock_Insert(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_Mock_Pool_Insert(Map keyName) {
        return mouigosadao.D_Mock_Pool_Insert(keyName);
    }
    
    // 모의고사 등록 등록
    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertRegistrationMouigosaWthSubj(Map keyName) {

        int count = 1;
        mouigosadao.insertRegistrationMouigosa(keyName);

        //직렬 등록
        String[] getInsertIds = (String[])keyName.get("insertIds");
        for (int i = 0; i < getInsertIds.length; i++) {
            if (getInsertIds[i].length() > 0) {
                String [] tmp = null;
                tmp = getInsertIds[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String CLASSSERIESCODE2 = tmp[j];
                    keyName.put("CLASSSERIESCODE2", CLASSSERIESCODE2);

                    //직렬 삭제 후 등록
                    mouigosadao.insertMouigosaTmockclsclsseries(keyName);
                }
            }
        }

        //필수 과목 등록
        String[] SUBJECTPERIOD1 = (String[])keyName.get("SUBJECTPERIOD1");
        String[] ITEMID1 = (String[])keyName.get("ITEMID1");
        String[] SUBJECT_CD1 = (String[])keyName.get("SUBJECT_CD1");
        String[] SUBJECTORDER1 = (String[])keyName.get("SUBJECTORDER1");

        if (ITEMID1 != null) {
            for (int i = 0; i < SUBJECTPERIOD1.length; i++) {
                if (SUBJECTPERIOD1[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD1[i];
                    String ITEMID = ITEMID1[i];
                    String SUBJECT_CD = SUBJECT_CD1[i];
                    String SUBJECTORDER = SUBJECTORDER1[i];

                    keyName.put("SUBJECTTYPEDIVISION", "1");
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECT_CD", SUBJECT_CD);
                    keyName.put("SUBJECTORDER", SUBJECTORDER);
                    keyName.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    mouigosadao.insertMouigosaTmocksubject(keyName);
                }
            }
        }

        //필수 과목 체크 삭제
        String[] deleteIds = (String[])keyName.get("deleteIds");
        for (int i = 0; i < deleteIds.length; i++) {
            if (deleteIds[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECTTYPEDIVISION", "1");

                    mouigosadao.deleteMouigosaTmockSubject_ck(keyName);
                }
            }
        }

        //선택 과목 등록
        String[] SUBJECTPERIOD2 = (String[])keyName.get("SUBJECTPERIOD2");
        String[] ITEMID2 = (String[])keyName.get("ITEMID2");
        String[] SUBJECT_CD2 = (String[])keyName.get("SUBJECT_CD2");
        String[] SUBJECTORDER2 = (String[])keyName.get("SUBJECTORDER2");

        if (ITEMID2 != null) {
            for (int i = 0; i < SUBJECTPERIOD2.length; i++) {
                if (SUBJECTPERIOD2[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD2[i];
                    String ITEMID = ITEMID2[i];
                    String SUBJECT_CD = SUBJECT_CD2[i];
                    String SUBJECTORDER = SUBJECTORDER2[i];

                    keyName.put("SUBJECTTYPEDIVISION", "2");
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECT_CD", SUBJECT_CD);
                    keyName.put("SUBJECTORDER", SUBJECTORDER);
                    keyName.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    mouigosadao.insertMouigosaTmocksubject(keyName);
                }
            }
        }

        //선택 과목 체크 삭제
        String[] deleteIds2 = (String[])keyName.get("deleteIds2");
        for (int i = 0; i < deleteIds2.length; i++) {
            if (deleteIds2[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds2[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECTTYPEDIVISION", "2");

                    mouigosadao.deleteMouigosaTmockSubject_ck(keyName);
                }
            }
        }

        //return mouigosadao.insertRegistrationMouigosa(keyName);
        return count;
    }

    // 모의고사 등록 수정불러오기 상세
    @Override @SuppressWarnings("rawtypes")
    public List getUpdateRegistrationDetail(Map keyName) {
        return mouigosadao.getUpdateRegistrationDetail(keyName);
    }

    // 모의고사 등록 수정불러오기 상세
    @Override @SuppressWarnings("rawtypes")
    public List D_getUpdateRegistrationDetail(Map keyName) {
        return mouigosadao.D_getUpdateRegistrationDetail(keyName);
    }
    
    //직렬 상세 조회
    @Override @SuppressWarnings("rawtypes")
    public List getUpdateMouigosaTmockclsclsseriesDetail(Map keyName) {
        return mouigosadao.getUpdateMouigosaTmockclsclsseriesDetail(keyName);
    }

    // 모의고사 등록 수정
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateRegistrationMouigosa(Map keyName) {
        return mouigosadao.updateRegistrationMouigosa(keyName);
    }

    // 동형모의고사 수정
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_Mock_Update(Map keyName) {
        return mouigosadao.D_Mock_Update(keyName);
    }
    
    // 직렬 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmockclsclsseries(Map keyName) {
        return mouigosadao.deleteMouigosaTmockclsclsseries(keyName);
    }

    // 모의고사 등록 수정
    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateRegistrationMouigosaWthSubj(Map keyName) {

        int count = 1;
        // 모의고사 수정
        mouigosadao.updateRegistrationMouigosa(keyName);
        // 직렬 삭제
        mouigosadao.deleteMouigosaTmockclsclsseries(keyName);
        //직렬 삭제 후 등록
        String[] getInsertIds = (String[])keyName.get("insertIds");
        for (int i = 0; i < getInsertIds.length; i++) {
            if (getInsertIds[i].length() > 0) {
                String [] tmp = null;
                tmp = getInsertIds[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String CLASSSERIESCODE2 = tmp[j];
                    keyName.put("CLASSSERIESCODE2", CLASSSERIESCODE2);

                    //직렬 삭제 후 등록
                    mouigosadao.insertMouigosaTmockclsclsseries(keyName);
                }
            }
        }

        // 모의고사 등록 필수 , 선택과목 삭제
        mouigosadao.deleteMouigosaTmocksubject(keyName);

        //필수 과목 등록
        String[] SUBJECTPERIOD1 = (String[])keyName.get("SUBJECTPERIOD1");
        String[] ITEMID1 = (String[])keyName.get("ITEMID1");
        String[] SUBJECT_CD1 = (String[])keyName.get("SUBJECT_CD1");
        String[] SUBJECTORDER1 = (String[])keyName.get("SUBJECTORDER1");

        if (ITEMID1 != null) {
            for (int i = 0; i < SUBJECTPERIOD1.length; i++) {
                if (SUBJECTPERIOD1[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD1[i];
                    String ITEMID = ITEMID1[i];
                    String SUBJECT_CD = SUBJECT_CD1[i];
                    String SUBJECTORDER = SUBJECTORDER1[i];

                    keyName.put("SUBJECTTYPEDIVISION", "1");
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECT_CD", SUBJECT_CD);
                    keyName.put("SUBJECTORDER", SUBJECTORDER);
                    keyName.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    mouigosadao.insertMouigosaTmocksubject(keyName);
                }
            }
        }

        //필수 과목 체크 삭제
        String[] deleteIds = (String[])keyName.get("deleteIds");
        for (int i = 0; i < deleteIds.length; i++) {
            if (deleteIds[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECTTYPEDIVISION", "1");

                    mouigosadao.deleteMouigosaTmockSubject_ck(keyName);
                }
            }
        }

        //선택 과목 등록
        String[] SUBJECTPERIOD2 = (String[])keyName.get("SUBJECTPERIOD2");
        String[] ITEMID2 = (String[])keyName.get("ITEMID2");
        String[] SUBJECT_CD2 = (String[])keyName.get("SUBJECT_CD2");
        String[] SUBJECTORDER2 = (String[])keyName.get("SUBJECTORDER2");

        if (ITEMID2 != null) {
            for (int i = 0; i < SUBJECTPERIOD2.length; i++) {
                if (SUBJECTPERIOD2[i].length() > 0) {

                    String SUBJECTPERIOD = SUBJECTPERIOD2[i];
                    String ITEMID = ITEMID2[i];
                    String SUBJECT_CD = SUBJECT_CD2[i];
                    String SUBJECTORDER = SUBJECTORDER2[i];

                    keyName.put("SUBJECTTYPEDIVISION", "2");
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECT_CD", SUBJECT_CD);
                    keyName.put("SUBJECTORDER", SUBJECTORDER);
                    keyName.put("SUBJECTPERIOD", SUBJECTPERIOD);

                    mouigosadao.insertMouigosaTmocksubject(keyName);
                }
            }
        }

        //선택 과목 체크 삭제
        String[] deleteIds2 = (String[])keyName.get("deleteIds2");
        for (int i = 0; i < deleteIds2.length; i++) {
            if (deleteIds2[i].length() > 0) {
                String [] tmp = null;
                tmp = deleteIds2[i].split(",");

                for (int j = 0; j < tmp.length; j++) {
                    String ITEMID = tmp[j];
                    keyName.put("ITEMID", ITEMID);
                    keyName.put("SUBJECTTYPEDIVISION", "2");

                    mouigosadao.deleteMouigosaTmockSubject_ck(keyName);
                }
            }
        }

        // 접수자 과목 변경을위한 수정된 필수 리스트
        keyName.put("SUBJECTTYPEDIVISION", "1");
        List<?> list = mouigosadao.getTmocksubjectDetailList(keyName);
        if (list.size() > 0) {
            for (int k = 0; k < list.size(); k++) {
                HashMap<String, String> map = (HashMap<String, String>) list.get(k);
                String IDENTYID = (String) map.get("IDENTYID");
                String ITEMID = String.valueOf(map.get("ITEMID"));
                String SUBJECTTYPEDIVISION = String.valueOf(map.get("SUBJECTTYPEDIVISION") );
                String ITEMID_2 = String.valueOf(map.get("ITEMID_2"));

                keyName.put("IDENTYID", IDENTYID);
                keyName.put("ITEMID", ITEMID);
                keyName.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);
                keyName.put("ITEMID_2", ITEMID_2);

                //접수자 과목 변경을 위한 필수과목 수정
                mouigosadao.updateTuserChoiceSubject(keyName);
            }
        }

        // 접수자 과목 변경을위한 수정된 선택 리스트
        keyName.put("SUBJECTTYPEDIVISION", "2");
        list = mouigosadao.getTmocksubjectDetailList(keyName);

        if (list.size() > 0) {
            for (int k = 0; k < list.size(); k++) {
                HashMap<String, String> map = (HashMap<String, String>) list.get(k);
                String IDENTYID = (String) map.get("IDENTYID");
                String ITEMID = String.valueOf(map.get("ITEMID"));
                String SUBJECTTYPEDIVISION = String.valueOf(map.get("SUBJECTTYPEDIVISION"));
                String ITEMID_2 = String.valueOf( map.get("ITEMID_2"));

                keyName.put("IDENTYID", IDENTYID);
                keyName.put("ITEMID", ITEMID);
                keyName.put("SUBJECTTYPEDIVISION", SUBJECTTYPEDIVISION);
                keyName.put("ITEMID_2", ITEMID_2);

                //접수자 과목 변경을 위한 선택과목 수정
                mouigosadao.updateTuserChoiceSubject(keyName);
            }
        }

        return count;
    }

    @Override @SuppressWarnings("rawtypes")
    public List getTmocksubjectDetailList(Map keyName){
        return mouigosadao.getTmocksubjectDetailList(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateTuserChoiceSubject(Map keyName) {
        return mouigosadao.updateTuserChoiceSubject(keyName);
    }

    // 모의고사 등록 필수 , 선택과목 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmocksubject(Map keyName) {
        return mouigosadao.deleteMouigosaTmocksubject(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTuserChoiceSubject(Map keyName) {
        return mouigosadao.deleteMouigosaTuserChoiceSubject(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTofferer(Map keyName) {
        return mouigosadao.deleteMouigosaTofferer(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmockGrade(Map keyName) {
        return mouigosadao.deleteMouigosaTmockGrade(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTexamineeAnswer(Map keyName) {
        return mouigosadao.deleteMouigosaTexamineeAnswer(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTwrongAnswerNote(Map keyName) {
        return mouigosadao.deleteMouigosaTwrongAnswerNote(keyName);
    }

    @Override @SuppressWarnings({ "rawtypes", "unchecked" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteRegistrationMouigosa(Map keyName) {
        int deleteCount = 0;
        String deleteIds = null;
        if(null != keyName.get("deleteIds") && !"".equals(keyName.get("deleteIds").toString())) {
            deleteIds = keyName.get("deleteIds").toString();
        }

        if (deleteIds.length() > 0) {
            String [] tmp = deleteIds.split(",");
            for (int j = 0; j < tmp.length; j++) {
                String MOCKCODE = tmp[j];
                keyName.put("MOCKCODE", MOCKCODE);

                //모의고사 삭제
                mouigosadao.deleteMouigosaTmockregistration(keyName);
                //직렬 삭제
                mouigosadao.deleteMouigosaTmockclsclsseries2(keyName);
                //모의고사 등록 필수 , 선택과목 삭제
                mouigosadao.deleteMouigosaTmocksubject(keyName);
                //응시자선택과목 삭제
                mouigosadao.deleteMouigosaTuserChoiceSubject(keyName);
                //신청자관리 삭제
                mouigosadao.deleteMouigosaTofferer(keyName);
                //성적 삭제
                mouigosadao.deleteMouigosaTmockGrade(keyName);
                //응시자답변 삭제
                mouigosadao.deleteMouigosaTexamineeAnswer(keyName);
                //오답노트 삭제
                mouigosadao.deleteMouigosaTwrongAnswerNote(keyName);

                deleteCount++;
            }
        }

        return deleteCount;
    }

    //직렬 삭제 후 등록
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertMouigosaTmockclsclsseries(Map keyName) {
        return mouigosadao.insertMouigosaTmockclsclsseries(keyName);
    }

    // 모의고사 등록 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmockregistration(Map keyName) {
        return mouigosadao.deleteMouigosaTmockregistration(keyName);
    }

    // 모의고사 직렬 삭제
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteMouigosaTmockclsclsseries2(Map keyName) {
        return mouigosadao.deleteMouigosaTmockclsclsseries2(keyName);
    }

    // 모의고사 영역별 과목 리스트
    @Override @SuppressWarnings("rawtypes")
    public List getAreaSubjectListAll(Map keyName){
        return mouigosadao.getAreaSubjectListAll(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertAreaSubject(Map keyName) {
        return mouigosadao.insertAreaSubject(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int updateAreaSubject(Map keyName) {
        return mouigosadao.updateAreaSubject(keyName);
    }    
    
    @Override @SuppressWarnings("rawtypes")
	public String getQA_fileno(Map keyName) {
		return mouigosadao.getQA_fileno(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_ATTACHFILE_Insert(Map keyName) {
        return mouigosadao.D_ATTACHFILE_Insert(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List getuploadFile1(Map keyName) {
        return mouigosadao.getuploadFile1(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List getuploadFile2(Map keyName) {
        return mouigosadao.getuploadFile2(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_AttachDeleteFile1(Map keyName) {
        return mouigosadao.D_AttachDeleteFile1(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_AttachDeleteFile2(Map keyName) {
        return mouigosadao.D_AttachDeleteFile2(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_ATTACHFILE_update(Map keyName) {
        return mouigosadao.D_ATTACHFILE_update(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List D_getPoolList(Map keyName) {
        return mouigosadao.D_getPoolList(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int QUESTIONNUM_INS(Map keyName) {
        return mouigosadao.QUESTIONNUM_INS(keyName);
    }

    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int QUESTIONNUM_DEL(Map keyName) {
        return mouigosadao.QUESTIONNUM_DEL(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_LecPool_Upd(Map keyName) {
        return mouigosadao.D_LecPool_Upd(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List getPoolImgFile1(Map keyName) {
        return mouigosadao.getPoolImgFile1(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List getPoolImgFile2(Map keyName) {
        return mouigosadao.getPoolImgFile2(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    public List getPoolImgFile3(Map keyName) {
        return mouigosadao.getPoolImgFile3(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_ATTACHFILE_Update(Map keyName) {
        return mouigosadao.D_ATTACHFILE_Update(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_LecPool_Img3_Upd(Map keyName) {
        return mouigosadao.D_LecPool_Img3_Upd(keyName);
    }
    
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int D_LecPool_Upd2(Map keyName) {
        return mouigosadao.D_LecPool_Upd2(keyName);
    }
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteTitem(Map keyName) {
        return mouigosadao.deleteTitem(keyName);
    }
    @Override @SuppressWarnings("rawtypes")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int deleteTitemPool(Map keyName) {
        return mouigosadao.deleteTitemPool(keyName);
    }
    @Override @SuppressWarnings("rawtypes")
    public List getD_QuestionList(Map keyName) {
        return mouigosadao.getD_QuestionList(keyName);
    }
}
