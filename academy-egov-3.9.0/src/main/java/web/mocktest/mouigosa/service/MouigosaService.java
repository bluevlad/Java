package web.mocktest.mouigosa.service;

import java.util.List;
import java.util.Map;

public interface MouigosaService {

    @SuppressWarnings({ "rawtypes" })
    int deleteQuestionMouigosa(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getQuestionNumMax(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getItemIdMax(Map keyName);
    // 첨부파일 max값
    @SuppressWarnings({ "rawtypes" })
    int getFileCount(Map keyName);

    // 모의고사 과목 리스트
    @SuppressWarnings({ "rawtypes" })
    List getMouigosaList(Map keyName);

    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    int getCount(Map keyName);

    // 모의고사 과목 등록 팝업화면 과목 조회
    @SuppressWarnings({ "rawtypes" })
    List getSubjectList(Map keyName);

    // 모의고사 사용 과목 조회
    @SuppressWarnings({ "rawtypes" })
    List getClass(Map keyName);

    // 모의고사 사용 과목 조회
    @SuppressWarnings({ "rawtypes" })
    List getClassSubject(Map keyName);

    // 교수 가져오기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode(Map keyName);

    // 년.회차,과목 중복체크
    @SuppressWarnings({ "rawtypes" })
    List getDuplication(Map keyName);

    // 과목 등록
    @SuppressWarnings({ "rawtypes" })
    int insertTccsrsSubject(Map keyName);

    // 과목 등록
    @SuppressWarnings({ "rawtypes" })
    int updateTccsrsSubject(Map keyName);

    // 과목 등록
    @SuppressWarnings({ "rawtypes" })
    int deleteTccsrsSubject(Map keyName);

    // 과목 등록
    @SuppressWarnings({ "rawtypes" })
    int insertMouigosa(Map keyName);

    // 과목과 문제 동시 등록
    @SuppressWarnings({ "rawtypes" })
    int insertMouigosaWthQuestion(Map keyName);

    // 모의고사 과목 수정 팝업화면 불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    List getUpdateDetail(Map keyName);

    // 과목 수정
    @SuppressWarnings({ "rawtypes" })
    int updateMouigosa(Map keyName);

    // 과목 수정( 문제 수정)
    @SuppressWarnings({ "rawtypes" })
    int updateMouigosaWthQuestion(Map keyName);

    // 과목 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTitem(Map keyName);

    // 문제 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTitemPool(Map keyName);

    // 과목 삭제( 문제풀 + 문제)
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaQuestion(Map keyName);

    //문제 프린트 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
    List getQuestionList(Map keyName);

    // 모의고사 문제 리스트
    @SuppressWarnings({ "rawtypes" })
    List getMouigosaQuestionList(Map keyName);

    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    int getMouigosaQuestionCount(Map keyName);

    // 모의고사 문제 등록 팝업화면 과목영역 조회
    @SuppressWarnings({ "rawtypes" })
    List getSubjectAreaList(Map keyName);

    // 문제 등록
    @SuppressWarnings({ "rawtypes" })
    int insertQuestionMouigosa(Map keyName);

    // 모의고사 문제 수정 팝업화면 불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    List getUpdateQuestionDetail(Map keyName);

    // 문제 수정
    @SuppressWarnings({ "rawtypes" })
    int updateQuestionMouigosa(Map keyName);

    // 문제 수정(복수 수정)
    @SuppressWarnings({ "rawtypes" })
    int updateQuestionMouigosas(Map keyName);

    // 문제지정 년
    @SuppressWarnings({ "rawtypes" })
    List getExamYear(Map keyName);

    // 문제지정 회
    @SuppressWarnings({ "rawtypes" })
    List getExamRound(Map keyName);

    // 문제지정 문제번호
    @SuppressWarnings({ "rawtypes" })
    List getQuestionNumber(Map keyName);

    //문제지정 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
    List getQuestionAllList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getQuestionAllList2(Map keyName);

    //문제지정 수정
    @SuppressWarnings({ "rawtypes" })
    int updateQuestionAll(Map keyName);

    // 블러오기 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
    List getMouigosaPopList(Map keyName);

    // 블러오기 팝업 리스트 카운트
    @SuppressWarnings({ "rawtypes" })
    int getPopCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateQuestionOnly(Map keyName);

    // 모의고사 등록 리스트
    @SuppressWarnings({ "rawtypes" })
    List getMouigosaRegistrationList(Map keyName);
    
    // 동형모의고사 등록 리스트
    @SuppressWarnings({ "rawtypes" })
    List D_getMouigosaRegistrationList(Map keyName);

    // 총 건수
    @SuppressWarnings({ "rawtypes" })
    int getRegistrationCount(Map keyName);
    
    // 동형모의고사 총 건수
    @SuppressWarnings({ "rawtypes" })
    int D_getRegistrationCount(Map keyName);

    // 모의고사 등록 등록 팝업화면 '직급' 조회
    @SuppressWarnings({ "rawtypes" })
    List getRegistrationList(Map keyName);

    // 직렬 가져오기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode2(Map keyName);

    // 필수과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode3(Map keyName);

    // 선택과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode4(Map keyName);

    // 필수과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode3_ins(Map keyName);

    // 선택과목(교수) 비동기
    @SuppressWarnings({ "rawtypes" })
    List getSubCode4_ins(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getRegistrationExamYear(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getRegistrationExamRound(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getRegistrationSubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getRegistrationPopList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getRegistrationPopCount(Map keyName);

    // 모의고사 등록 max값
    @SuppressWarnings({ "rawtypes" })
    List getRegistrationMax(Map keyName);

    // 모의고사 등록 등록
    @SuppressWarnings({ "rawtypes" })
    int insertRegistrationMouigosa(Map keyName);

    // 모의고사 등록 등록
    @SuppressWarnings({ "rawtypes" })
    int insertMouigosaTmocksubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmockSubject_ck(Map keyName);

    // 모의고사 등록 등록
    @SuppressWarnings({ "rawtypes" })
    int insertRegistrationMouigosaWthSubj(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_Mock_Insert(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_Mock_Pool_Insert(Map keyName);

    // 모의고사 등록 수정불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    List getUpdateRegistrationDetail(Map keyName);
    
    // 동형모의고사 등록 수정불러오기 상세
    @SuppressWarnings({ "rawtypes" })
    List D_getUpdateRegistrationDetail(Map keyName);

    //직렬 상세 조회
    @SuppressWarnings({ "rawtypes" })
    List getUpdateMouigosaTmockclsclsseriesDetail(Map keyName);

    // 모의고사 등록 수정
    @SuppressWarnings({ "rawtypes" })
    int updateRegistrationMouigosa(Map keyName);
    
    // 동형모의고사 수정
    @SuppressWarnings({ "rawtypes" })
    int D_Mock_Update(Map keyName);

    // 직렬 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmockclsclsseries(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateRegistrationMouigosaWthSubj(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateTuserChoiceSubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTmocksubjectDetailList(Map keyName);

    // 모의고사 등록 필수 , 선택과목 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmocksubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTuserChoiceSubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTofferer(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmockGrade(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTexamineeAnswer(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTwrongAnswerNote(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteRegistrationMouigosa(Map keyName);
    
    //직렬 삭제 후 등록
    @SuppressWarnings({ "rawtypes" })
    int insertMouigosaTmockclsclsseries(Map keyName);

    // 모의고사 등록 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmockregistration(Map keyName);

    // 모의고사 직렬 삭제
    @SuppressWarnings({ "rawtypes" })
    int deleteMouigosaTmockclsclsseries2(Map keyName);
    
    // 모의고사 영역별 과목 리스트
    @SuppressWarnings({ "rawtypes" })
    List getAreaSubjectListAll(Map keyName);
    
	@SuppressWarnings({ "rawtypes" })
    int insertAreaSubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateAreaSubject(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    String getQA_fileno(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_ATTACHFILE_Insert(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List getuploadFile1(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List getuploadFile2(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_AttachDeleteFile1(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_AttachDeleteFile2(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_ATTACHFILE_update(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List D_getPoolList(Map keyName);  
    
    @SuppressWarnings({ "rawtypes" })
    int QUESTIONNUM_INS(Map keyName);  

    @SuppressWarnings({ "rawtypes" })
    int QUESTIONNUM_DEL(Map keyName); 
    
    @SuppressWarnings({ "rawtypes" })
    int D_LecPool_Upd(Map keyName);   
    
    @SuppressWarnings({ "rawtypes" })
    List getPoolImgFile1(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List getPoolImgFile2(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List getPoolImgFile3(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int D_ATTACHFILE_Update(Map keyName); 
    
    @SuppressWarnings({ "rawtypes" })
    int D_LecPool_Img3_Upd(Map keyName); 
    
    @SuppressWarnings({ "rawtypes" })
    int D_LecPool_Upd2(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    int deleteTitem(Map keyName); 
    
    @SuppressWarnings({ "rawtypes" })
    int deleteTitemPool(Map keyName);
    
    @SuppressWarnings({ "rawtypes" })
    List getD_QuestionList(Map keyName);
}
