package com.willbes.web.mocktest.offerer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class OffererDAO extends CmmAbstractMapper {

    public List<HashMap<String, String>> offererClsClsList(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererClsClsList", params);
    }

    public int offererListCount(Object params){
        return getSqlSession().selectOne("offerer.offererListCount", params);
    }

    public List<HashMap<String, Object>> offererList(Object params){
        return getSqlSession().selectList("offerer.offererList", params);
    }

    public List<HashMap<String, String>> offererMouiList(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererMouiList", params);
    }

    public List<HashMap<String, String>> offererMouiInfoList(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererMouiInfoList", params);
    }

    public int offererMouiInfoListCount(Object params){
        return getSqlSession().selectOne("offerer.offererMouiInfoListCount", params);
    }

    public List<HashMap<String, String>> offererMouiClassInfo(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererMouiClassInfo", params);
    }

    public List<HashMap<String, String>> offererModifyMouiList(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererModifyMouiList", params);
    }

    public List<HashMap<String, String>> offererModifyMouiClassInfo(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererModifyMouiClassInfo", params);
    }

    public void offererInsert(Object obj) {
        getSqlSession().insert("offerer.offererInsert", obj);
    }

    public void offererDivSubjectInsert(Object obj) {
        getSqlSession().insert("offerer.offererDivSubjectInsert", obj);
    }

    public void offererSubjectInsert(Object obj) {
        getSqlSession().insert("offerer.offererSubjectInsert", obj);
    }

    public String offererIdGet(HashMap<String, String> params){
        return getSqlSession().selectOne("offerer.offererIdGet", params);
    }

    public String identyIdGet(HashMap<String, String> params){
        return getSqlSession().selectOne("offerer.identyIdGet", params);
    }

    public String commonCodeValueGet(Object obj) {
        return getSqlSession().selectOne("offerer.commonCodeValueGet", obj);
    }

    public void offererPaymentInsert(Object obj) {
        getSqlSession().insert("offerer.offererPaymentInsert", obj);
    }

    public List<HashMap<String, String>> offererView(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererView", params);
    }

    public List<HashMap<String, String>> offererSubjectView(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererSubjectView", params);
    }

    public void offererPaymentUpdate(Object obj) {
        getSqlSession().update("offerer.offererPaymentUpdate", obj);
    }

    public void offererSubjectDelete(Object obj) {
        getSqlSession().delete("offerer.offererSubjectDelete", obj);
    }

    public List<HashMap<String, String>> offererMouiSearchSel(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererMouiSearchSel", params);
    }

    public void offererUpdate(Object obj) {
        getSqlSession().update("offerer.offererUpdate", obj);
    }

    public void offererModifyUpdate(Object obj) {
        getSqlSession().update("offerer.offererModifyUpdate", obj);
    }

    public void offererDelete(Object obj) {
        getSqlSession().delete("offerer.offererDelete", obj);
    }

    public void offererPaymentDelete(Object obj) {
        getSqlSession().delete("offerer.offererPaymentDelete", obj);
    }

    public void offererExamineeanswerDelete(Object obj) {
        getSqlSession().delete("offerer.offererExamineeanswerDelete", obj);
    }

    public void offererWronganswernoteDelete(Object obj) {
        getSqlSession().delete("offerer.offererWronganswernoteDelete", obj);
    }

    public void offererMockgradeDelete(Object obj) {
        getSqlSession().delete("offerer.offererMockgradeDelete", obj);
    }

    public void offererPrintDelete(Object obj) {
        getSqlSession().update("offerer.offererPrintDelete", obj);
    }

    public void offererPrintUpdate(Object obj) {
        getSqlSession().update("offerer.offererPrintUpdate", obj);
    }

    public void offererTrefundInsert(Object obj) {
        getSqlSession().insert("offerer.offererTrefundInsert", obj);
    }

    public void offererTrefundDelete(Object obj) {
        getSqlSession().delete("offerer.offererTrefundDelete", obj);
    }

    public String offererSubjectOrderGet(HashMap<String, String> params){
        return getSqlSession().selectOne("offerer.offererSubjectOrderGet", params);
    }

    public String offererCurrentCnt(HashMap<String, String> params){
        return getSqlSession().selectOne("offerer.offererCurrentCnt", params);
    }

    public List<HashMap<String, String>> offererSeqGet(HashMap<String, String> params){
        return getSqlSession().selectList("offerer.offererSeqGet", params);
    }

    public void offererSeqUpdate(Object obj) {
        getSqlSession().update("offerer.offererSeqUpdate", obj);
    }

    public void offererSeqInsert(Object obj) {
        getSqlSession().insert("offerer.offererSeqInsert", obj);
    }

    public void offererPaymentCardUpdate(Object obj) {
        getSqlSession().update("offerer.offererPaymentCardUpdate", obj);
    }

    public String GetOffererId(Object obj) {
        return getSqlSession().selectOne("offerer.GetOffererId", obj);
    }

    public String GetIdentyId(Object obj) {
        return getSqlSession().selectOne("offerer.GetIdentyId", obj);
    }
}
