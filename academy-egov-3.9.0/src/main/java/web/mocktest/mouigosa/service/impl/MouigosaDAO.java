package com.willbes.web.mocktest.mouigosa.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class MouigosaDAO extends CmmAbstractMapper {

    @SuppressWarnings({ "rawtypes" })
    public int deleteQuestionMouigosa(Map keyName){
        return getSqlSession().delete("mouigosa.deleteQuestionMouigosa", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int getQuestionNumMax(Map keyName){
        return getSqlSession().selectOne("mouigosa.getQuestionNumMax", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int getItemIdMax(Map keyName){
        return getSqlSession().selectOne("mouigosa.getItemIdMax", keyName);
    }

    // 첨부파일 max값
    @SuppressWarnings({ "rawtypes" })
    public int getFileCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.getFileCount", keyName);
    }

    // 모의고사 과목 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getMouigosaList(Map keyName){
        return getSqlSession().selectList("mouigosa.getMouigosaList", keyName);
    }

    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    public int getCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.getCount", keyName);
    }

    // 모의고사 과목 등록 팝업화면 과목 조회
    @SuppressWarnings({ "rawtypes" })
    public List getSubjectList(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubjectList", keyName);
    }

    // 모의고사 사용 과목 조회
    @SuppressWarnings({ "rawtypes" })
    public List getClass(Map keyName){
        return getSqlSession().selectList("mouigosa.getClass", keyName);
    }

    // 모의고사 사용 과목 조회
    @SuppressWarnings({ "rawtypes" })
    public List getClassSubject(Map keyName){
        return getSqlSession().selectList("mouigosa.getClassSubject", keyName);
    }

    // 교수 가져오기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode", keyName);
    }

    // 년.회차,과목 중복체크
    @SuppressWarnings({ "rawtypes" })
    public List getDuplication(Map keyName){
        return getSqlSession().selectList("mouigosa.getDuplication", keyName);
    }

    // 직렬별과목 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertTccsrsSubject(Map keyName){
        return getSqlSession().insert("mouigosa.insertTccsrsSubject", keyName);
    }

    // 직렬별과목 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateTccsrsSubject(Map keyName){
        return getSqlSession().update("mouigosa.updateTccsrsSubject", keyName);
    }

    // 직렬별과목 등록
    @SuppressWarnings({ "rawtypes" })
    public int deleteTccsrsSubject(Map keyName){
        return getSqlSession().delete("mouigosa.deleteTccsrsSubject", keyName);
    }

    // 과목 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertMouigosa(Map keyName){
        return getSqlSession().insert("mouigosa.insertMouigosa", keyName);
    }

    // 과목 등록 (키값: ITEMID  반환)
    @SuppressWarnings({ "rawtypes" })
    public int insertMouigosaWthRetVal(Map keyName){
        getSqlSession().insert("mouigosa.insertMouigosaWthRetVal", keyName); //반환값 Row 갯수
        return Integer.parseInt(keyName.get("ITEMID").toString());
    }

    // 모의고사 과목 수정 팝업화면 불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    public List getUpdateDetail(Map keyName){
        return getSqlSession().selectList("mouigosa.getUpdateDetail", keyName);
    }

    // 과목 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateMouigosa(Map keyName){
        return getSqlSession().update("mouigosa.updateMouigosa", keyName);
    }

    // 과목 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTitem(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTitem", keyName);
    }

    // 문제 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTitemPool(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTitemPool", keyName);
    }

    //문제 프린트 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getQuestionList(Map keyName){
        return getSqlSession().selectList("mouigosa.getQuestionList", keyName);
    }

    //==================================================================//

    // 모의고사 문제 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getMouigosaQuestionList(Map keyName){
        return getSqlSession().selectList("mouigosa.getMouigosaQuestionList", keyName);
    }

    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    public int getMouigosaQuestionCount(Map keyName){
        return getSqlSession().update("mouigosa.getMouigosaQuestionCount", keyName);
    }

    // 모의고사 문제 등록 팝업화면 과목영역 조회
    @SuppressWarnings({ "rawtypes" })
    public List getSubjectAreaList(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubjectAreaList", keyName);
    }

    // 문제 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertQuestionMouigosa(Map keyName){
        return getSqlSession().update("mouigosa.insertQuestionMouigosa", keyName);
    }

    // 모의고사 문제 수정 팝업화면 불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    public List getUpdateQuestionDetail(Map keyName){
        return getSqlSession().selectList("mouigosa.getUpdateQuestionDetail", keyName);
    }

    // 문제 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateQuestionMouigosa(Map keyName){
        return getSqlSession().update("mouigosa.updateQuestionMouigosa", keyName);
    }

    // 문제 수정에 따른 사용자 답안지 오답 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateExamAnswerYN(Map keyName){
        return getSqlSession().update("mouigosa.updateExamAnswerYN", keyName);
    }

    // 문제 수정에 따른 사용자 점수 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateUserGrade(Map keyName){
        return getSqlSession().update("mouigosa.updateUserGrade", keyName);
    }

    // 문제 수정에 따른 사용자 조정점수 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateUserGradeAdjust(Map keyName){
        return getSqlSession().update("mouigosa.updateUserGradeAdjust", keyName);
    }

    //==================================================================//

    // 문제지정 년
    @SuppressWarnings({ "rawtypes" })
    public List getExamYear(Map keyName){
        return getSqlSession().selectList("mouigosa.getExamYear", keyName);
    }

    // 문제지정 회
    @SuppressWarnings({ "rawtypes" })
    public List getExamRound(Map keyName){
        return getSqlSession().selectList("mouigosa.getExamRound", keyName);
    }

    // 문제지정 문제번호
    @SuppressWarnings({ "rawtypes" })
    public List getQuestionNumber(Map keyName){
        return getSqlSession().selectList("mouigosa.getQuestionNumber", keyName);
    }

    // 문제지정 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getQuestionAllList(Map keyName){
        return getSqlSession().selectList("mouigosa.getQuestionAllList", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getQuestionAllList2(Map keyName){
        return getSqlSession().selectList("mouigosa.getQuestionAllList2", keyName);
    }

    //문제지정 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateQuestionAll(Map keyName){
        return getSqlSession().update("mouigosa.updateQuestionAll", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getMouigosaPopList(Map keyName){
        return getSqlSession().selectList("mouigosa.getMouigosaPopList", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int getPopCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.getPopCount", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int updateQuestionOnly(Map keyName){
        return getSqlSession().update("mouigosa.updateQuestionOnly", keyName);
    }

    //==================================================================//

    // 모의고사 등록 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getMouigosaRegistrationList(Map keyName){
        return getSqlSession().selectList("mouigosa.getMouigosaRegistrationList", keyName);
    }

    // 동형모의고사 등록 리스트
    @SuppressWarnings({ "rawtypes" })
    public List D_getMouigosaRegistrationList(Map keyName){
        return getSqlSession().selectList("mouigosa.D_getMouigosaRegistrationList", keyName);
    }

    // 동형모의고사 총 건수
    @SuppressWarnings({ "rawtypes" })
    public int D_getRegistrationCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.D_getRegistrationCount", keyName);
    }
        
    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    public int getRegistrationCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.getRegistrationCount", keyName);
    }

    // 모의고사 등록 등록 팝업화면 '직급' 조회
    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationList(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationList", keyName);
    }

    // 직렬 가져오기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode2(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode2", keyName);
    }

    // 필수과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode3(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode3", keyName);
    }

    //선택과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode4(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode4", keyName);
    }

    // 필수과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode3_ins(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode3_ins", keyName);
    }

    //선택과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    public List getSubCode4_ins(Map keyName){
        return getSqlSession().selectList("mouigosa.getSubCode4_ins", keyName);
    }

    //==================================================================//

    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationExamYear(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationExamYear", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationExamRound(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationExamRound", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationSubject(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationSubject", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationPopList(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationPopList", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int getRegistrationPopCount(Map keyName){
        return getSqlSession().selectOne("mouigosa.getRegistrationPopCount", keyName);
    }

    //==================================================================//

    // 모의고사 등록 max값
    @SuppressWarnings({ "rawtypes" })
    public List getRegistrationMax(Map keyName){
        return getSqlSession().selectList("mouigosa.getRegistrationMax", keyName);
    }

    // 모의고사 등록 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertRegistrationMouigosa(Map keyName){
        return getSqlSession().insert("mouigosa.insertRegistrationMouigosa", keyName);
    }

    // 모의고사 등록 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertMouigosaTmocksubject(Map keyName){
        return getSqlSession().insert("mouigosa.insertMouigosaTmocksubject", keyName);
    }
	// 동형모의고사 등록 등록
	@SuppressWarnings({ "rawtypes" })
    public int D_Mock_Insert(Map keyName){
        return getSqlSession().insert("mouigosa.D_Mock_Insert", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_Mock_Pool_Insert(Map keyName){
        return getSqlSession().insert("mouigosa.D_Mock_Pool_Insert", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmockSubject_ck(Map keyName){
        return getSqlSession().insert("mouigosa.deleteMouigosaTmockSubject_ck", keyName);
    }

    // 모의고사 등록 수정불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    public List getUpdateRegistrationDetail(Map keyName){
        return getSqlSession().selectList("mouigosa.getUpdateRegistrationDetail", keyName);
    }
    
    // 동형모의고사 등록 수정불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    public List D_getUpdateRegistrationDetail(Map keyName){
        return getSqlSession().selectList("mouigosa.D_getUpdateRegistrationDetail", keyName);
    }

    //직렬 상세 조회
    @SuppressWarnings({ "rawtypes" })
    public List getUpdateMouigosaTmockclsclsseriesDetail(Map keyName){
        return getSqlSession().selectList("mouigosa.getUpdateMouigosaTmockclsclsseriesDetail", keyName);
    }

    // 모의고사 등록 수정
    @SuppressWarnings({ "rawtypes" })
    public int updateRegistrationMouigosa(Map keyName){
        return getSqlSession().update("mouigosa.updateRegistrationMouigosa", keyName);
    }
    
     // 동형모의고사 수정
    @SuppressWarnings({ "rawtypes" })
    public int D_Mock_Update(Map keyName){
        return getSqlSession().update("mouigosa.D_Mock_Update", keyName);
    }

    // 직렬 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmockclsclsseries(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTmockclsclsseries", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public List getTmocksubjectDetailList(Map keyName){
        return getSqlSession().selectList("mouigosa.getTmocksubjectDetailList", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int updateTuserChoiceSubject(Map keyName){
        return getSqlSession().update("mouigosa.updateTuserChoiceSubject", keyName);
    }

    // 모의고사 등록 필수 , 선택과목 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmocksubject(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTmocksubject", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTuserChoiceSubject(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTuserChoiceSubject", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTofferer(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTofferer", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmockGrade(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTmockGrade", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTexamineeAnswer(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTexamineeAnswer", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTwrongAnswerNote(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTwrongAnswerNote", keyName);
    }

    //직렬 삭제 후 등록
    @SuppressWarnings({ "rawtypes" })
    public int insertMouigosaTmockclsclsseries(Map keyName){
        return getSqlSession().insert("mouigosa.insertMouigosaTmockclsclsseries", keyName);
    }

    // 모의고사 등록 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmockregistration(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTmockregistration", keyName);
    }

    // 모의고사 직렬 삭제
    @SuppressWarnings({ "rawtypes" })
    public int deleteMouigosaTmockclsclsseries2(Map keyName){
        return getSqlSession().delete("mouigosa.deleteMouigosaTmockclsclsseries2", keyName);
    }

    // 모의고사 영역별 과목 리스트
    @SuppressWarnings({ "rawtypes" })
    public List getAreaSubjectListAll(Map keyName){
        return getSqlSession().selectList("mouigosa.getAreaSubjectListAll", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
    public int insertAreaSubject(Map keyName){
        return getSqlSession().insert("mouigosa.insertAreaSubject", keyName);
    }

	@SuppressWarnings({ "rawtypes" })
    public int updateAreaSubject(Map keyName){
        return getSqlSession().insert("mouigosa.updateAreaSubject", keyName);
    }

	@SuppressWarnings({ "rawtypes" })
    public String getQA_fileno(Map keyName) {
		return getSqlSession().selectOne("mouigosa.getQA_fileno", keyName);
	}
	@SuppressWarnings({ "rawtypes" })
    public int D_ATTACHFILE_Insert(Map keyName){
        return getSqlSession().insert("mouigosa.D_ATTACHFILE_Insert", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getuploadFile1(Map keyName){
        return getSqlSession().selectList("mouigosa.getuploadFile1", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getuploadFile2(Map keyName){
        return getSqlSession().selectList("mouigosa.getuploadFile2", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_AttachDeleteFile1(Map keyName){
        return getSqlSession().update("mouigosa.D_AttachDeleteFile1", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_AttachDeleteFile2(Map keyName){
        return getSqlSession().delete("mouigosa.D_AttachDeleteFile2", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_ATTACHFILE_update(Map keyName){
        return getSqlSession().update("mouigosa.D_ATTACHFILE_update", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List D_getPoolList(Map keyName){
        return getSqlSession().selectList("mouigosa.D_getPoolList", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int QUESTIONNUM_INS(Map keyName){
        return getSqlSession().insert("mouigosa.QUESTIONNUM_INS", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int QUESTIONNUM_DEL(Map keyName){
        return getSqlSession().delete("mouigosa.QUESTIONNUM_DEL", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_LecPool_Upd(Map keyName){
        return getSqlSession().update("mouigosa.D_LecPool_Upd", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getPoolImgFile1(Map keyName){
        return getSqlSession().selectList("mouigosa.getPoolImgFile1", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getPoolImgFile2(Map keyName){
        return getSqlSession().selectList("mouigosa.getPoolImgFile2", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getPoolImgFile3(Map keyName){
        return getSqlSession().selectList("mouigosa.getPoolImgFile3", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_ATTACHFILE_Update(Map keyName){
        return getSqlSession().update("mouigosa.D_ATTACHFILE_Update", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_LecPool_Img3_Upd(Map keyName){
        return getSqlSession().update("mouigosa.D_LecPool_Img3_Upd", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int D_LecPool_Upd2(Map keyName){
        return getSqlSession().update("mouigosa.D_LecPool_Upd2", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int deleteTitem(Map keyName){
        return getSqlSession().delete("mouigosa.deleteTitem", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int deleteTitemPool(Map keyName){
        return getSqlSession().delete("mouigosa.deleteTitemPool", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public List getD_QuestionList(Map keyName){
        return getSqlSession().selectList("mouigosa.getD_QuestionList", keyName);
    }
    
}
