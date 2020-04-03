package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class SubjectDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> subjectList(HashMap<String, String> params){
		return getSqlSession().selectList("subject.subjectList", params);
	}

	public int subjectListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.subjectListCount", params);
	}

	public String subjectGetCode(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.subjectGetCode", params);
	}

	public void subjectInsert(HashMap<String, String> params){
		getSqlSession().insert("subject.subjectInsert", params);
	}

	public List<HashMap<String, String>> subjectView(HashMap<String, String> params){
		return getSqlSession().selectList("subject.subjectView", params);
	}

	public void subjectUpdate(HashMap<String, String> params){
		getSqlSession().update("subject.subjectUpdate", params);
	}

	public void subjectDelete(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectDelete", params);
	}

	public int subjectCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.subjectCheck", params);
	}

	public void subjectCategoryInsert(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryInsert", params);
	}

	public void subjectCategoryDelete(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryDelete", params);
	}
	
	public void subjectCategoryDeleteByCat(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryDeleteByCat", params);
	}

	public void subjectCategoryOrderInsert(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryOrderInsert", params);
	}
	
	public int chkSubjectCategoryOrderCnt(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.chkSubjectCategoryOrderCnt", params);
	}
	
	public int chkSubjectCategoryCnt(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.chkSubjectCategoryCnt", params);
	}
	
	public String getSubjectCategoryOrderIdx(HashMap<String, String> params){
		return getSqlSession().selectOne("subject.getSubjectCategoryOrderIdx", params);
	}
	
	public void subjectCategoryOrderDeleteByOnoff(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryOrderDeleteByOnoff", params);
	}
	
	public void subjectCategoryOrderDelete(HashMap<String, String> params){
		getSqlSession().delete("subject.subjectCategoryOrderDelete", params);
	}

	public List<HashMap<String, String>> subjectCategoryView(HashMap<String, String> params){
		return getSqlSession().selectList("subject.subjectCategoryView", params);
	}

    public List<HashMap<String, String>> findSubjectCategoryList(HashMap<String, String> params){
        return getSqlSession().selectList("subject.findSubjectCategoryList", params);
    }

	public void main_category_subject_order_Insert(HashMap<String, String> params){
		getSqlSession().insert("subject.main_category_subject_order_Insert", params);
	}	

}
