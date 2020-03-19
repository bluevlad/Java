/*
 * Message.java, @ 2005-05-12
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet;

/**
 * @author goindole
 *
 */
public class Message {
    public final static String INVALID_REQUEST = "잘못된 요청입니다.";
    /**
     * 정상적인 실행 메세지 정의
     */
    // 등록 성공
    public final static String INSERT_OK = " 성공적으로 데이터를 등록 하였습니다.";
    // 삭제 성공
    public final static String DELETE_OK = " 성공적으로 데이터를 삭제 하였습니다.";
    // 수정 성공
    public final static String UPDATE_OK = " 성공적으로 데이터를 수정하였습니다.";
    // 가입 성공
    public final static String JOIN_OK = "☞ 회원가입을 축하 드립니다.";
    // 동아리 폐쇄 성공 
    public final static String CLUB_CLOSE_OK = " 성공적으로 폐쇄되었습니다.";
    // 일정4개이상인 경우 등록,수정 불가능
    public final static String INSERT_DIARY = "해당일에 일정을 4개이상 등록할 수 없습니다.";
    // 이미추천
    public final static String RECM_NO = " 이미 추천하였습니다.";
    // 추천 성공
    public final static String RECM_OK = " 성공적으로 추천하였습니다.";
    // 이미 동아리가입됨
    public final static String JOIN_NO = "☞ 이미 가입된 스터디입니다.";
    // 온라인폴 등록 성공
    public final static String POLL_OK = "성공적으로 온라인설문을  등록 하였습니다. 우수한 정보는 참고하겠습니다.";

    /**
     * 오류 실행 메세지 정의
     */
    // 등록 실패
    public final static String INSERT_FAIL = "☞ 데이터를 등록도중 에러가 발생하였습니다.";
    // 삭제 실패
    public final static String DELETE_FAIL = "☞ 데이터를 삭제도중 에러가 발생하였습니다.";
    // 수정 실패
    public final static String UPDATE_FAIL = "☞ 데이터를 수정도중 에러가 발생하였습니다.";
    //  token fail
    public final static String INVALID_TOKEN = "☞ 잘못된 페이지 요청입니다. 정상적인 경로로 접근 하시거나 페이지를  [새로고침] 후 다시 시도해 주세요.";    

    /**
     * 도서몰 관련 사용 메세지 정의
    **/
    // 장바구니 상품 추가
    public final static String INSERT_BASKET = "☞ 장바구니에 상품을 추가했습니다.";
    // 장바구니 상품 변경
    public final static String UPDATE_BASKET = "☞ 장바구니에서 상품수량을 변경했습니다.";
    // 장바구니 상품 삭제
    public final static String DELETE_BASKET = "☞ 장바구니에서 상품을 삭제했습니다.";

    // 장바구니 상품 추가 에러
    public final static String INSERT_BASKET_FAIL = "☞ 장바구니에 상품을 추가하지 못했습니다.";
    // 장바구니 상품 변경 에러
    public final static String UPDATE_BASKET_FAIL = "☞ 장바구니에서 상품수량을 변경하지 못했습니다.";
    // 장바구니 상품 삭제 에러
    public final static String DELETE_BASKET_FAIL = "☞ 장바구니에서 상품을 삭제하지 못했습니다.";
    // 장바구니에 과정을 두개이상 담으려고 할때
    public final static String INSERT_BASKET_SJT_FAIL = "☞ 교육과정은 장바구니에 한개만 담을 수 있습니다.";


    // 주문이 정상정으로 완료됬을경우
    public final static String INSERT_ORDER_OK = "☞ 정상정으로 주문이 완료되었습니다..";
    // 주문이 실패했을겨우
    public final static String INSERT_ORDER_FAIL = "☞ 주문시 에러가 발생했습니다.";
    // 결제확인을 정상적으로 처리했을경우
    public final static String PAY_ORDER_OK = "☞ 정상정으로 결제완료가 처리되었습니다..";
    // 결제확인을 실패했을겨우
    public final static String PAY_ORDER_FAIL = "☞ 결제완료 처리시 에러가 발생했습니다.";
    // 결제를 취소했을 경우
    public final static String PAY_CANCEL = "☞ 결제를 취소하셨습니다.";
    // 주문을 취소했을 경우
    public final static String ORDER_CANCEL = "☞ 주문을 취소하셨습니다.";

    //------------------------------------------------------------------------------------------------------------------------------

    // 학사력이 없을경우
    public final static String ERROR_HAKGI  = "☞ 학사력이 입력 되지 않았습니다. <br> 학사력 입력 후 사용이 가능합니다.";
    public final static String PERMIT_OK = "☞ 선택한 교수들을 승인하였습니다.";
    public final static String CANCEL_OK = "☞ 선택한 교수들을 승인 거부하였습니다.";
    public final static String BA3_DUP = "☞ 이미 등록되어 있는 데이타입니다. <br> 확인 후 다시 시도 하세요.";

    /**
     * 공통적인 경고 메세지
     */
    // 코드가 중복될때
    public final static String ID_DUPLICATE = "☞ 이미 등록되어 있는 코드입니다.";
    // 코드가 중복되지 않을때
    public final static String ID_DUPLICATEOK = "☞ 사용 가능한 코드 입니다.";
    // 조회나 삭제나 업데이트시 데이타를 찾을 수 없을때
    public final static String DATA_NOT_FOUND = "☞ 요청하신 데이터를 찾을 수 없습니다.";
    // 이미 등록된 주민등록 번호가 있을때
    public final static String SSNB_DUPLICATE = "☞ 이미 등록되어 있는 주민등록번호 입니다.";
    // 이미 등록된 회원 번호가 있을때
	public final static String MEMNO_DUPLICATE = "☞ 이미 등록되어 있는 회원번호입니다.";

    /**
     * 에러성 메세지 정의
     */
    // DB 접속 실패
    public final static String CONNECTION_REFUS = "☞ 접속 사용자가 많습니다. 잠시후에 다시 시도 하시기 바랍니다.";
    // 파일다운로드 실패
    public final static String FILE_NOT_FOUND = "☞ 요청하신 파일을 찾을 수 없습니다.";
    //비밀번호 맞지 않았을때
    public final static String PWD_NOT_MATCH = "☞ 비밀번호가 맞지 않습니다.";

    /**
     * 경고성 메세지 정의
     */
    // 기관 삭제시 과정이 존재하여 삭제 불가
    public final static String EDU_DEL_NOT_ALLOW = "☞ 삭제를 요청한 기관에 과정이 개설되어 있습니다.";
    // 기관 코드 수정시 과정이 존재하여 삭제 불가
    public final static String EDU_EDT_NOT_ALLOW = "☞ 이미 과정이 개설되어 있어 기관코드를 수정할 수 없습니다.";
    // 과정 삭제시 과목이 존재하여 삭제 불가
    public final static String GRP_DEL_NOT_ALLOW = "☞ 삭제를 요청한 과정에 과목이 개설되어 있습니다.";
    // 과정 수정시 과목이 존재하여 삭제 불가
    public final static String GRP_EDT_NOT_ALLOW = "☞ 수정을 요청한 과정에 과목이 개설되어 있습니다.";
    // 과목 삭제시 학사일정이 존재하여 삭제 불가
    public final static String SJT_DEL_NOT_ALLOW = "☞ 삭제를 요청한 과목에 학사일정이 개설되어 있습니다.";
    // 과목 수정시 학사일정이 존재하여 삭제 불가
    public final static String SJT_EDT_NOT_ALLOW = "☞ 수정을 요청한 과목에 학사일정이 개설되어 있습니다.";

    /**
    * 온라인 평가
    */
    // 온라인평가를 학생들에게 출제할때 수강중인 학생이 없을 때 오류
    public final static String STD_NOT_FOUND = "☞ 수강중인 학생이 없습니다.";
    // 온라인평가의 문제를 교수가 선택하여 문제를 학생들에게 출제할때 선택한 문제가 없을 때 오류
    public final static String EXM_NOT_FOUND = "☞ 선택한 시험문제가 없습니다.";
    // 온라인 평가를 학생들에게 출제하였을 때
    public final static String EXM_SUBMIT_SUCCESS = "☞ 시험문제를 학생들에게 출제 하였습니다.";
    // 온라인평가의 문제를 교수가 선택하여 문제를 학생들에게 출제할때 학생들에게 이미 문제를 출제하였을 때 오류
    public final static String EXM_SUBMIT_NOT_SUCCESS = "☞ 시험문제를 학생들에게 이미 출제 하였습니다.";
    // 온라인평가 문제은행의 문제를 교수가 삭제할 때
    public final static String EXM_BNK_DELETE = "☞ 선택했던 문제를 삭제하였습니다.";
    // 온라인평가 문제은행의 문제를 교수가 삭제할 때 오류
    public final static String EXM_BNK_DELETE_FAIL = "☞ 삭제하려는 문제가 없습니다.";
    // 온라인평가 문제은행의 문제를 교수가 등록할 때
    public final static String EXM_BNK_INSERT = "☞ 선택한 문제를 등록 하였습니다.";
    // 온라인평가 문제은행의 문제를 교수가 등록할 때 오류
    public final static String EXM_BNK_INSERT_FAIL = "☞ 선택한 문제가 등록되지 않았습니다.";
    // 온라인평가 문제은행의 문제를 교수가 수정할 때
    public final static String EXM_BNK_UPDATE = "☞ 선택한 문제가 수정되었습니다.";
    // 온라인평가 문제은행의 문제를 교수가 수정할 때 오류
    public final static String EXM_BNK_UPDATE_FAIL = "☞ 선택한 문제가 수정되지 않았습니다.";
    // 온라인평가 문제은행에서 교수가 문제를 선택하여 취합할 때
    public final static String EXM_TMP_INSERT = "☞ 문제를 등록하였습니다.";
    // 온라인평가 문제은행에서 교수가 문제를 선택하여 취합할 때 오류
    public final static String EXM_TMP_INSERT_FAIL = "☞ 선택한 문제가 이미 있어 문제를 취합할 수 없습니다.";
    // 온라인평가 문제은행에서 교수가 선택했던 문제를 삭제할 때
    public final static String EXM_TMP_DELETE = "☞ 선택했던 문제를 취소하였습니다.";
    // 온라인평가 문제은행에서 교수가 선택했던 문제를 삭제할 때 오류
    public final static String EXM_TMP_DELETE_FAIL = "☞ 선택했던 문제를 취소하지 못했습니다.";
    // 온라인평가 문제은행에서 교수가 선택했던 문제를 수정할 때
    public final static String EXM_TMP_UPDATE = "☞ 선택했던 문제를 수정하였습니다.";
    // 온라인평가 문제은행에서 교수가 선택했던 문제를 수정할 때 오류
    public final static String EXM_TMP_UPDATE_FAIL = "☞ 선택했던 문제를 수정하지 못했습니다.";
    // 온라인평가 등록
    public final static String EXM_OMG_INSERT = "☞ 온라인평가를 등록 하였습니다.";
    // 온라인평가 등록 오류
    public final static String EXM_OMG_INSERT_FAIL = "☞ 온라인평가가 이미 등록되어 있습니다.";
    // 온라인평가 수정
    public final static String EXM_OMG_UPDATE = "☞ 온라인평가를 수정하였습니다.";
    // 온라인평가 수정 오류
    public final static String EXM_OMG_UPDATE_FAIL = "☞ 온라인평가를 수정하지 못했습니다.";
    // 온라인평가 삭제
    public final static String EXM_OMG_DELETE = "☞ 온라인평가를 삭제하였습니다.";
    // 온라인평가 수정 오류
    public final static String EXM_OMG_DELETE_FAIL = "☞ 온라인평가를 삭제하지 못했습니다.";
    // 온라인평가를 교수가 랜덤으로 출제할 때
    public final static String EXM_RND_REG = "☞ 학생들에게 문제가 출제되었습니다.";
    // 온라인평가를 교수가 랜덤으로 출제할 때 오류
    public final static String EXM_RND_REG_FAIL = "☞ 코드의 오류로 인하여  시험문제를 출제할 수 없습니다.";
    // 학생들에게 출제된 문제 삭제
    public final static String EXM_STD_DELETE = "☞ 학생들에게 제출한 문제를 삭제하였습니다.";
    // 학생들에게 출제된 문제 삭제 오류
    public final static String EXM_STD_DELETE_FAIL = "☞ 학생들에게 출제한 문제가 없거나 코드오류로 인하여 학생들에게 제출한 문제를 삭제하지 못했습니다.";
    // 학생이 문제를 풀었을 때 메시지
    public final static String EXM_STD_SUBMIT = "☞ 풀이한 문제를 등록 하였습니다.";
    // 학생이 문제를 풀었을 때 오류 메시지
    public final static String EXM_STD_SUBMIT_FAIL = "☞ 풀이한 문제를 등록하지 못했습니다.";
    // 재시험을 등록
    public final static String EXM_STD_RESUBMIT = "☞ 재시험을 등록 하였습니다.";
    // 재시험을 등록 에러 메시지
    public final static String EXM_STD_RESUBMIT_FAIL = "☞ 이미 온라인평가에 응시를 하셨습니다.";
    // 시험정보 삭제
    public final static String EXM_STD_RESULT_DELETE = "☞ 응시했던 시험결과 정보를 삭제 하였습니다.";
    // 시험정보 삭제 에러 메시지
    public final static String EXM_STD_RESULT_DELETE_FAIL = "☞ 응시했던 시험결과 정보를 삭제하지 못했습니다.";

    /**
    * 일정 (calendar)
    */
    // 일정 등록시 기 데이터 존재.
    public final static String CAL_EXIST = "☞ 이미 일정이 등록되어 있으므로, 수정하여 추가하시기 바랍니다.";


    /**
    * 온라인 설문
    */
    // 온라인 설문 등록
    public final static String SMG_MNG_INSERT = "☞ 온라인 설문을 등록 하였습니다..";
    // 온라인 설문 등록 에러
    public final static String SMG_MNG_INSERT_FAIL = "☞ 이미 등록된 온라인 설문이 있어 등록에 실패하였습니다.";
    // 온라인 설문 삭제 메시지
    public final static String SMG_MNG_DELETE = "☞ 해당 설문을 삭제 하였습니다.";
    // 온라인 설문 삭제 오류 메시지
    public final static String SMG_MNG_DELETE_FAIL = "☞ 해당 설문을 삭제하지 못했습니다.";
    // 온라인 설문 수정 메시지
    public final static String SMG_MNG_UPDATE = "☞ 해당 설문을 수정 하였습니다.";
    // 온라인 설문 수정 오류 메시지
    public final static String SMG_MNG_UPDATE_FAIL = "☞ 해당 설문을 수정하지 못했습니다.";
    // 온라인 설문 질문 등록 메시지
    public final static String SMG_QUE_INSERT = "☞ 질문을 등록 하였습니다.";
    // 온라인 설문 질문 등록 오류 메시지
    public final static String SMG_QUE_INSERT_FAIL = "☞ 질문을 등록하지 못했습니다.";
    // 온라인 설문 질문 삭제 메시지
    public final static String SMG_QUE_DELETE = "☞ 해당 질문을 삭제 하였습니다.";
    // 온라인 설문 질문 삭제 오류 메시지
    public final static String SMG_QUE_DELETE_FAIL = "☞ 해당 질문을 삭제 하였습니다.";
    // 온라인 설문 질문 수정 메시지
    public final static String SMG_QUE_UPDATE = "☞ 해당 질문을 수정 하였습니다.";
    // 온라인 설문 질문 수정 오류메시지
    public final static String SMG_QUE_UPDATE_FAIL = "☞ 해당 질문을 수정하지 못했습니다.";
    // 학생이 온라인 설문에 응답
    public final static String SMG_QUE_STD_INSERT = "☞ 작성한 설문을 등록 하였습니다.";
    // 학생이 온라인 설문에 응답 오류 메시지
    public final static String SMG_QUE_STD_INSERT_FAIL = "☞ 작성한 설문을 등록 하지 못했습니다.";
    // 이미 설문에 응답 했을 경우
    public final static String SMG_QUE_STD_ALREADY = "☞ 이미 설문을 작성 하였습니다.";


    /**
     * 세미나실 관리
     */
    // 세미나실 삭제 실패
    public final static String SEM_DEL_FAIL =  "☞ 세미나실 정보 삭제 중 오류가 발생하였습니다.";
    // 세미나실 조원 삭제 실패
    public final static String SEM_MEM_DEL_FAIL =  "☞ 세미나실 조원 정보 삭제 중 오류가 발생하였습니다.";
    // 세미나실 수정 실패
    public final static String SEM_EDT_FAIL =  "☞ 세미나실 정보 수정 중 오류가 발생하였습니다.";
    // 세미나실 조원 수정 실패
    public final static String SEM_MEM_EDT_FAIL =  "☞ 세미나실 조원 수정 중 오류가 발생하였습니다.";
    // 세미나실 등록 실패
    public final static String SEM_INS_FAIL =  "☞ 세미나실 등록 중 오류가 발생하였습니다.";
    // 세미나실 조원 등록 실패
    public final static String SEM_MEM_INS_FAIL =  "☞ 세미나실 조원 등록 중 오류가 발생하였습니다.";

    /**
     * 퀴즈 등록 관리
     */
    // 퀴즈 일반정보 등록 성공
    public final static String QIZ_INSERT_OK =  "☞ 퀴즈 일반정보가 등록되었습니다.";
    // 퀴즈 일반정보 등록 실패
    public final static String QIZ_INSERT_FAIL =  "☞ 퀴즈 일반정보 등록 중 오류가 발생하였습니다.";
    // 퀴즈 일반정보 수정 성공
    public final static String QIZ_UPDATE_OK =  "☞ 퀴즈 일반정보가 수정되었습니다.";
    // 퀴즈 일반정보 수정 실패
    public final static String QIZ_UPDATE_FAIL =  "☞ 퀴즈 일반정보 수정 중 오류가 발생하였습니다.";
    // 퀴즈 일반정보 삭제 성공
    public final static String QIZ_DELETE_OK =  "☞ 퀴즈가 삭제되었습니다.";
    // 퀴즈 일반정보 삭제 실패
    public final static String QIZ_DELETE_FAIL =  "☞ 퀴즈 삭제 중 오류가 발생하였습니다.";

    // 퀴즈 문제 등록 성공
    public final static String QIZ_EXP_INSERT_OK =  "☞ 퀴즈 문제가 등록되었습니다.";
    // 퀴즈 문제 등록 실패
    public final static String QIZ_EXP_INSERT_FAIL =  "☞ 퀴즈 문제 수정 중 오류가 발생하였습니다.";
    // 퀴즈 문제 수정 성공
    public final static String QIZ_EXP_UPDATE_OK =  "☞ 퀴즈 문제가 수정되었습니다.";
    // 퀴즈 문제 수정 실패
    public final static String QIZ_EXP_UPDATE_FAIL =  "☞ 퀴즈 문제 수정 중 오류가 발생하였습니다.";
    // 퀴즈 문제 삭제 성공
    public final static String QIZ_EXP_DELETE_OK =  "☞ 퀴즈 문제가 삭제되었습니다.";
    // 퀴즈 문제 삭제 실패
    public final static String QIZ_EXP_DELETE_FAIL =  "☞ 퀴즈 문제 삭제 중 오류가 발생하였습니다.";
    // 퀴즈 파일 삭제 성공
    public final static String QIZ_FILE_DELETE_OK =  "☞ 퀴즈 파일이 삭제되었습니다.";
    // 퀴즈 파일 삭제 실패
    public final static String QIZ_FILE_DELETE_FAIL =  "☞ 퀴즈 파일 삭제 중 오류가 발생하였습니다.";
    // 퀴즈 응시정보 등록 성공
    public final static String QIZ_ANS_INSERT_OK  =  "☞ 퀴즈 응시정보를 등록하였습니다.";
    // 퀴즈 응시정보 등록 실패
    public final static String QIZ_ANS_INSERT_FAIL  =  "☞ 퀴즈 응시정보 등록 중 오    류가 발생하였습니다.";
    // 퀴즈 응시정보 수정 실패
    public final static String QIZ_ANS_UPDATE_FAIL  =  "☞ 퀴즈 응시정보 등록 중 오류가 발생하였습니다.";
    // 퀴즈 패스 성공
    public final static String QIZ_PASS_OK = "☞ 퀴즈 성적이 기준치보다 우수하여 \n 다음 진도로 넘어가실 수 있습니다.";
    // 퀴즈 패스 실패
    public final static String QIZ_PASS_FAIL = "☞ 퀴즈 성적이 기준치 보다 미달되는 점수입니다.\n 다시 한번 문제를 풀어주시기 바랍니다.";


    /*
     * 재학생 수강신청
     */
    // 재학생 수강신청 성곧.
    public final static String REQ_STD_CREATE =  "☞ 선택한 강의에 대해 수강신청 되었습니다.";
    public final static String REQ_STD_CREATE_FAIL =  "☞ 선택한 강의에 대해 수강신청중 오류가 발생하였습니다.";
    public final static String REQ_STD_CREATE_USED =  "☞ 사용된 강의쿠폰입니다..";
    public final static String REQ_STD_NULL =  "<br><bR>☞ 변경된 내역이 없습니다.<br><br><br>강의를 선택하시지 않았거나, 이미 신청한 강의입니다.";

    //수강신청 취소관련..
    public final static String REQCANCEL_OK =  "☞ 수강신청 취소 요청을 승인하였습니다.";
    public final static String REQCANCEL_REQ =  "☞ 수강신청 취소 요청을 등록하였습니다.";

    /**
     * 실시간 쪽지
     */
    // ID, 메일코드가 넘어오지 않은 경우
    public final static String MAL_PAM_NO = "☞ 실시간 메일 전송 대상 정보가 없습니다.";
    // 메일 전송과정에서 session이 종료된 경우
    public final static String MAL_SESSION_CLOSED =  "☞ 메일 전송 중 세션이 종료되었습니다.";
    // 메일 전송과정 실패
    public final static String MAL_SEND_FAIL =  "☞ 메일 전송 중 오류가 발생하였습니다.";


    /**
     * 동아리 대화방 관리
     */
    // 동아리 대화방 삭제 실패
    public final static String CHT_DEL_FAIL =  "☞ 동아리 대화방 정보 삭제 중 오류가 발생하였습니다.";
    // 동아리 대화방 조원 삭제 실패
    public final static String CHT_MEM_DEL_FAIL =  "☞ 동아리 대화방 조원 정보 삭제 중 오류가 발생하였습니다.";
    // 동아리 대화방 수정 실패
    public final static String CHT_EDT_FAIL =  "☞ 동아리 대화방 정보 수정 중 오류가 발생하였습니다.";
    // 동아리 대화방 조원 수정 실패
    public final static String CHT_MEM_EDT_FAIL =  "☞ 동아리 대화방 조원 수정 중 오류가 발생하였습니다.";
    // 동아리 대화방 등록 실패
    public final static String CHT_INS_FAIL =  "☞ 동아리 대화방 등록 중 오류가 발생하였습니다.";
    // 동아리 대화방 조원 등록 실패
    public final static String CHT_MEM_INS_FAIL =  "☞ 동아리 대화방 조원 등록 중 오류가 발생하였습니다.";

    /**
     *
     */
    // 졸업승인
    public final static String GRD_APPROVAL      =  "☞ 졸업예정자으로 승인 하였습니다.";
    public final static String GRD_DEPT_APPROVAL =  "☞ 해당 학과의 졸업예정자를 졸업자로 승인 하였습니다.";
    public final static String GRD_RESERVATION   =  "☞ 졸업을 유보하였습니다.";

    /**
     *
     */
    // 학위등록
    public final static String DGR_APPEND   =  "☞ 학위를 등록하였습니다.";

    /**
     * 학적관련
     */
    public final static String CHG_OK   =  "☞ 학적변동을 승인하였습니다.";
    public final static String CHG_NO   =  "☞ 학적변동을 거부하였습니다.";
    public final static String CHG_HAKGI1   =  "☞ 학기가 변동었습니다.";
    public final static String CHG_HAKGI2   =  "☞ 학기가 변동었습니다.";
    public final static String CHG_ERR2   =  "☞ 자신의 과로 전과신청을 할 수 없습니다.";
    public final static String CHG_ERR3   =  "☞ 존재하지 않는 학번입니다.";
    public final static String CHG_ERR4   =  "☞ 휴학은 3회 이상 처리할 수 없습니다.";
    public final static String CHG_ELSE   =  "☞ 학적변동 신청 등록중 오류가 발생하였습니다.";
    public static final String DUPLICATION = "중복입력(데이터가 존재합니다.)";
     /**
     * 장학,등록관련
     */
    public final static String ACCOUNT_OK = " 무통장 입금 신청이 완료 되었습니다. <br><br> 등록 기간 내에 해당 은행에 입금 하세요.";
    public final static String WHAN_GBN = " 이미 환불 처리가 완료 되었습니다.";
    public final static String WHAN_AGREE_OK = " 성공적으로 환불 승인 되었습니다.";
    public final static String WHAN_CANCEL_OK = " 성공적으로 환불 취소 되었습니다.";
    public final static String DENG_YN = " 등록금 납부 내역이 없습니다. <br> 환불 신청이 불가능 합니다.";
    public final static String STATE_OK = " 학적 상태가 재학생이 아닙니다. <br><br> 서비스 이용이 불가능 합니다.";
    public final static String JANG_GIGAN_ERR = " 장학 신청 기간이 아닙니다.";
    public final static String DENG_GIGAN_ERR = " 등록금 납부 기간이 아닙니다.";
    public final static String WHAN_GIGAN_ERR = " 환불 기간이 아닙니다.";
    public final static String DELETE_NO = " 현재 사용중인 코드 입니다. 삭제가 불가능 합니다.";
    public final static String FILE_ERR = " 파일이 존재 하지 않습니다.";
    public final static String ENTER_CD = " 시간제 등록생은 <br> 서비스 이용이 불가능합니다.";
    public final static String REG_FLAG = " 이미 등록이 완료 되었습니다.";
    public final static String JANG_YN = " 장학생으로 선발 완료 되었습니다. <br> 장학신청이 불가능 합니다..";
    public final static String REG_GBN = " 등록금 납부 내역이 없습니다. <br> 장학 신청이 불가능 합니다.";

    /**
     * 주문정보
     */
    public final static String ORDER_OK   =  " 주문이 정상적으로 이루어졌습니다.";
    public final static String ORDER_FAIL =  " 주문시 에러가 발생했습니다.";
    public final static String PAY_OK   =  " 성공적으로 결제 완료 되었습니다.";
    public final static String PAY_FAIL =  " 결제가 실패하였습니다.";

    public final static String Sisul =  " 2004년 3월 1일부터 서비스 됩니다.";

    /**
     * 예약정보
     */
    public final static String CBT_LEND_OK   =  " CBT룸 예약이 되었습니다.";
    public final static String CBT_LEND_FAIL   =  " CBT룸 예약이 실패했습니다. 다시 예약해주십시요.";
    public final static String CBT_LEND_CANCEL   =  " CBT룸 예약이 취소되었습니다.";
    public final static String CBT_LEND_CANCEL_FAIL   =  " CBT룸 예약이 취소가 실패했습니다. 다시 취소해주십시요";
    public final static String LAB_LEND_OK   =  " 자습실 예약이 되었습니다.";
    public final static String LAB_LEND_FAIL   =  " 자습실 예약이 실패했습니다. 다시 예약해주십시요.";
    public final static String LAB_LEND_CANCEL   =  " 자습실 예약이 취소되었습니다.";
    public final static String LAB_LEND_CANCEL_FAIL   =  " 자습실 예약이 취소가 실패했습니다. 다시 취소해주십시요";
    public final static String VID_LEND_OK   =  " 자습실 예약이 되었습니다.";
    public final static String VID_LEND_FAIL   =  " 자습실 예약이 실패했습니다. 다시 예약해주십시요.";
    public final static String VID_LEND_CANCEL   =  " 자습실 예약이 취소되었습니다.";
    public final static String VID_LEND_CANCEL_FAIL   =  " 자습실 예약이 취소가 실패했습니다. 다시 취소해주십시요";
    public final static String LOCKER_LEND_OK   =  " 사물함 예약이 되었습니다.";
    public final static String LOCKER_LEND_FAIL   =  " 사물함 예약이 실패했습니다. 다시 예약해주십시요.";
    public final static String LOCKER_LEND_CANCEL   =  " 사물함 예약이 취소되었습니다.";
    public final static String LOCKER_LEND_CANCEL_FAIL   =  " 사물함 예약이 취소가 실패했습니다. 다시 취소해주십시요";
    public final static String STUDY_GROUP_OK   =  " 스터디그룹 신청이 접수되었습니다.";
    public final static String STUDY_GROUP_FAIL   =  " 스터디그룹 신청이 실패했습니다. 다시 신청해주십시요.";
    public final static String CONSULT_CARD_OK   =  " 상담카드 작성이 완료되었습니다.";
    public final static String CONSULT_CARD_FAIL   =  " 상담카드 작성이 실패했습니다. 다시 작성해주십시요.";
    public final static String VISIT_CONSULT_OK   =  " 방문상담 예약이 완료되었습니다.";
    public final static String VISIT_CONSULT_FAIL   =  " 방문상담 예약이 실패했습니다. 상담희망일자를  변경하여 다시 작성해주십시요.";
    public final static String EVALUATION_OK   =  " Evaluation 신청이 완료되었습니다.";
    public final static String EVALUATION_FAIL   =  " Evaluation 신청이 실패했습니다. 다시 작성해주십시요.";
    public final static String FILING_OK   =  " Filing 신청이 완료되었습니다.";
    public final static String FILING_FAIL   =  " Filing 신청이 실패했습니다. 다시 작성해주십시요.";
    public final static String RECORD_OK   =  " 성적발송 신청이 완료되었습니다.";
    public final static String RECORD_FAIL   =  " 성적발송 신청이 실패했습니다. 다시 작성해주십시요.";
    public final static String FAIR_OK   =  " 공개설명회가 신청되었습니다.";
    public final static String FAIR_FAIL   =  " 공개설명회 신청이 실패했습니다. 다시 신청해주십시요.";
}
