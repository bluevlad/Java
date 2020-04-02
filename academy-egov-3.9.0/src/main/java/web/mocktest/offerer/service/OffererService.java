package web.mocktest.offerer.service;

import java.util.HashMap;
import java.util.List;

public interface OffererService {

    List<HashMap<String, String>> offererClsClsList(HashMap<String, String> params);

    int offererListCount(HashMap<String, Object> params);

    List<HashMap<String, Object>> offererList(HashMap<String, Object> params);

    List<HashMap<String, String>> offererMouiList(HashMap<String, String> params);

    List<HashMap<String, String>> offererMouiInfoList(HashMap<String, String> params);

    int offererMouiInfoListCount(HashMap<String, String> params);

    List<HashMap<String, String>> offererMouiClassInfo(HashMap<String, String> params);

    List<HashMap<String, String>> offererModifyMouiList(HashMap<String, String> params);

    List<HashMap<String, String>> offererModifyMouiClassInfo(HashMap<String, String> params);

    String commonCodeValueGet(HashMap<String, String> params);

    String offererIdGet(HashMap<String, String> params);

    String identyIdGet(HashMap<String, String> params);

    String GetOffererId(HashMap<String, String> params);

    String GetIdentyId(HashMap<String, String> params);

    void offererInsert(HashMap<String, Object> params);

    void offererDivSubjectInsert(HashMap<String, String> params);

    void offererSubjectInsert(HashMap<String, String> params);

    void offererPaymentInsert(HashMap<String, String> params);

    List<HashMap<String, String>> offererView(HashMap<String, String> params);

    List<HashMap<String, String>> offererSubjectView(HashMap<String, String> params);

    List<HashMap<String, String>> offererMouiSearchSel(HashMap<String, String> params);

    void offererUpdate(HashMap<String, Object> params);

    void offererModifyUpdate(HashMap<String, String> params);

    void offererPaymentUpdate(HashMap<String, String> params);

    void offererSubjectDelete(HashMap<String, String> params);

    void offererDelete(HashMap<String, String> params);

    void offererPaymentDelete(HashMap<String, String> params);

    void offererExamineeanswerDelete(HashMap<String, String> params);

    void offererWronganswernoteDelete(HashMap<String, String> params);

    void offererMockgradeDelete(HashMap<String, String> params);

    void offererPrintDelete(HashMap<String, String> params);

    void offererPrintUpdate(HashMap<String, String> params);

    void offererTrefundInsert(HashMap<String, String> params);

    void offererRefundInsert(HashMap<String, String> params);

    void offererTrefundDelete(HashMap<String, String> params);

    void offererRefundDelete(HashMap<String, String> params);

    String offererSubjectOrderGet(HashMap<String, String> params);

    String offererCurrentCnt(HashMap<String, String> params);

    List<HashMap<String, String>> offererSeqGet(HashMap<String, String> params);

    void offererSeqUpdate(HashMap<String, String> params);

    void offererSeqInsert(HashMap<String, String> params);

    void offererPaymentCardUpdate(HashMap<String, String> params);

    void offererPaymentCardDelete(HashMap<String, String> params);

    List<HashMap<String, String>> getAreaList(HashMap<String, String> params);
}
