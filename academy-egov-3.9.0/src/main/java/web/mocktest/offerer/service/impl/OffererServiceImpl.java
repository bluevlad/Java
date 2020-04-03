package web.mocktest.offerer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mocktest.offerer.service.OffererService;
import web.mocktest.offerer.service.impl.OffererDAO;

@Service(value="offererservice")
public class OffererServiceImpl implements OffererService{

    @Autowired
    private OffererDAO offererdao;

    @Override
    public List<HashMap<String, String>> offererClsClsList(HashMap<String, String> params){
        return offererdao.offererClsClsList(params);
    }

    @Override
    public List<HashMap<String, Object>> offererList(HashMap<String, Object> params){
        return offererdao.offererList(params);
    }

    @Override
    public int offererListCount(HashMap<String, Object> params){
        return offererdao.offererListCount(params);
    }

    @Override
    public List<HashMap<String, String>> offererMouiList(HashMap<String, String> params){
        return offererdao.offererMouiList(params);
    }

    @Override
    public List<HashMap<String, String>> offererMouiInfoList(HashMap<String, String> params){
        return offererdao.offererMouiInfoList(params);
    }

    @Override
    public int offererMouiInfoListCount(HashMap<String, String> params){
        return offererdao.offererMouiInfoListCount(params);
    }

    @Override
    public List<HashMap<String, String>> offererMouiClassInfo(HashMap<String, String> params){
        return offererdao.offererMouiClassInfo(params);
    }

    @Override
    public List<HashMap<String, String>> offererModifyMouiList(HashMap<String, String> params){
        return offererdao.offererModifyMouiList(params);
    }

    @Override
    public List<HashMap<String, String>> offererModifyMouiClassInfo(HashMap<String, String> params){
        return offererdao.offererModifyMouiClassInfo(params);
    }

    @Override
    public String commonCodeValueGet(HashMap<String, String> params){
        return offererdao.commonCodeValueGet(params);
    }

    @Override
    public String offererIdGet(HashMap<String, String> params){
        return offererdao.offererIdGet(params);
    }

    @Override
    public String identyIdGet(HashMap<String, String> params){
        return offererdao.identyIdGet(params);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void offererInsert(HashMap<String, Object> params){

        // step1. 주문번호 & 응시번호 생성 및 setting
        String ORDERNO; // 주문번호
        String IDENTYID;    // 응시번호

        // 주문번호 자동 생성
        params.put("paramVal", params.get("TEMP_ORDERNO").toString());
        ORDERNO = offererdao.GetOffererId(params);
        params.put("ORDERNO", ORDERNO);

        // 응시번호 자동 생성
        params.put("paramVal", params.get("TEMP_IDENTYID").toString());
        IDENTYID = offererdao.GetIdentyId(params);
        params.put("IDENTYID", IDENTYID);

        // step2. 신청자 정보등록 프로세스
        // 회원일 경우 - 2015-12-07
        if(null == params.get("USER_ID") || "".equals(String.valueOf(params.get("USER_ID")))){
            params.put("USER_ID", IDENTYID);
        }
        offererdao.offererInsert(params);

        // step3. 응시과목 프로세스
        offererdao.offererDivSubjectInsert(params);

        HashMap<String, String> paramsSubject = new  HashMap<String, String>();
        paramsSubject.put("EXAMCODE", params.get("EXAMCODE").toString());
        String[] CHOICEITEMARR = (String[])params.get("CHOICEITEMARR");
        if(CHOICEITEMARR != null){
            for(int i=0; i<CHOICEITEMARR.length; i++){
                paramsSubject.put("ITEMID", CHOICEITEMARR[i]);
                params.put("SUBJECTORDER", offererdao.offererSubjectOrderGet(paramsSubject));
                params.put("ITEMID", CHOICEITEMARR[i]);

                offererdao.offererSubjectInsert(params);
            }
        }

        // step4. 결제정보 프로세스
        // 관리자등록시 결제거레번호가 없으므로 소문자'd' + 주문번호 + 응시번호 결합코드를 넣기로 함
        params.put("TID", "d" + ORDERNO + IDENTYID);
        offererdao.offererPaymentInsert(params);
    }

    @Override
    public void offererDivSubjectInsert(HashMap<String, String> params){
        offererdao.offererDivSubjectInsert(params);
    }

    @Override
    public void offererSubjectInsert(HashMap<String, String> params){
        offererdao.offererSubjectInsert(params);
    }

    @Override
    public void offererPaymentInsert(HashMap<String, String> params){
        offererdao.offererPaymentInsert(params);
    }

    @Override
    public List<HashMap<String, String>> offererView(HashMap<String, String> params){
        return offererdao.offererView(params);
    }

    @Override
    public List<HashMap<String, String>> offererSubjectView(HashMap<String, String> params){
        return offererdao.offererSubjectView(params);
    }

    @Override
    public List<HashMap<String, String>> offererMouiSearchSel(HashMap<String, String> params){
        return offererdao.offererMouiSearchSel(params);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void offererUpdate(HashMap<String, Object> params){

        offererdao.offererModifyUpdate(params);
        offererdao.offererSubjectDelete(params);
        offererdao.offererDivSubjectInsert(params);

        HashMap<String, String> paramsSubject = new  HashMap<String, String>();
        paramsSubject.put("EXAMCODE", params.get("EXAMCODE").toString());
        String[] CHOICEITEMARR = (String[])params.get("CHOICEITEMARR");
        if(CHOICEITEMARR != null){
            for(int i=0; i<CHOICEITEMARR.length; i++){
                paramsSubject.put("ITEMID", CHOICEITEMARR[i]);
                params.put("SUBJECTORDER", offererdao.offererSubjectOrderGet(paramsSubject));
                params.put("ITEMID", CHOICEITEMARR[i]);

                offererdao.offererSubjectInsert(params);
            }
        }

        // 관리자등록시 결제거레번호가 없으므로 소문자'd' + 주문번호 + 응시번호 결합코드를 넣기로 함 , 미결제 상태에서 수정시 결제처리함
        if("0".equals(params.get("PAYMENTSTATE"))){
            params.put("PAYMENTSTATE","1");
            params.put("TID", "d" + params.get("ORDERNO") + params.get("IDENTYID"));

            offererdao.offererPaymentInsert(params);
            offererdao.offererPaymentUpdate(params);
        }else {
        }
    }

    @Override
    public void offererModifyUpdate(HashMap<String, String> params){
        offererdao.offererModifyUpdate(params);
    }

    @Override
    public void offererPaymentUpdate(HashMap<String, String> params){
        offererdao.offererPaymentUpdate(params);
    }

    @Override
    public void offererSubjectDelete(HashMap<String, String> params){
        offererdao.offererSubjectDelete(params);
    }

    @Override
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void offererDelete(HashMap<String, String> params){
        offererdao.offererDelete(params);
        offererdao.offererSubjectDelete(params);
        offererdao.offererPaymentDelete(params);
        offererdao.offererTrefundDelete(params);
        offererdao.offererExamineeanswerDelete(params);
        offererdao.offererWronganswernoteDelete(params);
        offererdao.offererMockgradeDelete(params);
    }

    @Override
    public void offererPaymentDelete(HashMap<String, String> params){
        offererdao.offererPaymentDelete(params);
    }

    @Override
    public void offererExamineeanswerDelete(HashMap<String, String> params){
        offererdao.offererExamineeanswerDelete(params);
    }

    @Override
    public void offererWronganswernoteDelete(HashMap<String, String> params){
        offererdao.offererWronganswernoteDelete(params);
    }

    @Override
    public void offererMockgradeDelete(HashMap<String, String> params){
        offererdao.offererMockgradeDelete(params);
    }

    @Override
    public void offererPrintDelete(HashMap<String, String> params){
        offererdao.offererPrintDelete(params);
    }

    @Override
    public void offererPrintUpdate(HashMap<String, String> params){
        offererdao.offererPrintUpdate(params);
    }

    @Override
    public void offererTrefundInsert(HashMap<String, String> params){
        offererdao.offererTrefundInsert(params);
    }

    @Override
    public void offererRefundInsert(HashMap<String, String> params){
        offererdao.offererTrefundInsert(params);
        params.put("PAYMENTSTATE","2");
        offererdao.offererPaymentUpdate(params);
    }

    @Override
    public void offererTrefundDelete(HashMap<String, String> params){
        offererdao.offererTrefundDelete(params);
    }

    @Override
    public void offererRefundDelete(HashMap<String, String> params){
        offererdao.offererTrefundDelete(params);
        params.put("PAYMENTSTATE","1");
        offererdao.offererPaymentUpdate(params);
    }

    @Override
    public String offererSubjectOrderGet(HashMap<String, String> params){
        return offererdao.offererSubjectOrderGet(params);
    }

    @Override
    public String offererCurrentCnt(HashMap<String, String> params){
        return offererdao.offererCurrentCnt(params);
    }

    @Override
    public List<HashMap<String, String>> offererSeqGet(HashMap<String, String> params){
        return offererdao.offererSeqGet(params);
    }

    @Override
    public void offererSeqUpdate(HashMap<String, String> params){
        offererdao.offererSeqUpdate(params);
    }

    @Override
    public void offererSeqInsert(HashMap<String, String> params){
        offererdao.offererSeqInsert(params);
    }

    @Override
    public void offererPaymentCardUpdate(HashMap<String, String> params){
        offererdao.offererPaymentCardUpdate(params);
    }

    @Override
    public void offererPaymentCardDelete(HashMap<String, String> params){
        offererdao.offererPaymentCardUpdate(params);
        params.put("PAYMENTSTATE","3");
        offererdao.offererPaymentUpdate(params);
    }

    @Override
    public String GetOffererId(HashMap<String, String> params) {
        return offererdao.GetOffererId(params);
    }

    @Override
    public String GetIdentyId(HashMap<String, String> params) {
        return offererdao.GetIdentyId(params);
    }

    @Override
    public List<HashMap<String, String>> getAreaList(HashMap<String, String> params){
        return offererdao.getAreaList(params);
    }
}
