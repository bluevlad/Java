package web.mocktest.offExamReg.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class OffExamRegDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> offExamList(HashMap<String, String> params){
		return getSqlSession().selectList("offExamReg.offExamList", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("offExamReg.offExamListCount", params);
	}

	public HashMap<String, String> offExamView(HashMap<String, Object> params) {
		return getSqlSession().selectOne("offExamReg.offExamView", params);
	}

	public HashMap<String, String> offExamOfferConfirm(HashMap<String, String> params) {
		return getSqlSession().selectOne("offExamReg.offExamOfferConfirm", params);
	}
	public HashMap<String, String> offExamOfferGetSubjectCode(HashMap<String, String> params) {
		return getSqlSession().selectOne("offExamReg.offExamOfferGetSubjectCode", params);
	}

	public void offExanInsertData(HashMap<String, Object> params) {
		 getSqlSession().insert("offExamReg.offExanInsertData", params);
	}

	public int offExamDelete(HashMap<String, Object> params) {
		return getSqlSession().delete("offExamReg.offExamDelete", params);
	}

	public List<HashMap<String, String>> offExanUpdateList() {
		return getSqlSession().selectList("offExamReg.offExanUpdateList");
	}

	public int updateCorrectYn(HashMap<String, String> params) {
		return getSqlSession().update("offExamReg.updateCorrectYn", params);
	}

	public List<HashMap<String, String>> offListPop(HashMap<String, String> params) {
		return getSqlSession().selectList("offExamReg.offListPop", params);
	}
	public int popListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("offExamReg.popListCount", params);
	}

	public int insertFileInfo(HashMap<String, Object> params) {
		return getSqlSession().insert("offExamReg.insertFileInfo", params);
	}

	public int offExamDeleteFile(HashMap<String, Object> params) {
		return getSqlSession().delete("offExamReg.offExamDeleteFile", params);
	}

	public List<HashMap<String, Object>> offExamGradeList(HashMap<String, Object> params) {
		return getSqlSession().selectList("offExamReg.offExamGradeList" , params);
	}

	public int getAdujustGrade(HashMap<String, Object> map) {
		return getSqlSession().selectOne("offExamReg.getAdujustGrade", map);
	}

	public void insertTmockGrade(HashMap<String, Object> params) {
		getSqlSession().insert("offExamReg.insertTmockGrade", params);
	}

	public void updateAdjGrade(HashMap<String, Object> map) {
		getSqlSession().update("offExamReg.updateAdjGrade", map);
	}

	public int offExamGradeDelete(HashMap<String, Object> params) {
		return getSqlSession().delete("offExamReg.offExamGradeDelete", params);
	}

	public void offExamUpdateCorrectYN(HashMap<String, String> params) {
		getSqlSession().update("offExamReg.offExamUpdateCorrectYN", params);
	}

	public void updateAdjustGradeTmockgradeByMockcode(HashMap<String, Object> gradeParamMap) {
		getSqlSession().update("offExamReg.updateAdjustGradeTmockgradeByMockcode", gradeParamMap);
	}

	public void updateTOffererExamstatus(HashMap<String, String> params) {
		getSqlSession().update("offExamReg.updateTOffererExamstatus", params);
	}

}
