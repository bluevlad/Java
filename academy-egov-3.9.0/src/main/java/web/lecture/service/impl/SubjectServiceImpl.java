package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lecture.service.SubjectService;
import web.lecture.service.impl.SubjectDAO;

@Service(value="subjectservice")
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectDAO subjectdao;

	@Override
	public List<HashMap<String, String>> subjectList(HashMap<String, String> params){
		return subjectdao.subjectList(params);
	}

	@Override
	public int subjectListCount(HashMap<String, String> params){
		return subjectdao.subjectListCount(params);
	}

	@Override
	public String subjectGetCode(HashMap<String, String> params){
		return subjectdao.subjectGetCode(params);
	}

	@Override
	public void subjectInsert(HashMap<String, String> params){
		subjectdao.subjectInsert(params);
	}

	@Override
	public List<HashMap<String, String>> subjectView(HashMap<String, String> params){
		return subjectdao.subjectView(params);
	}

	@Override
	public void subjectUpdate(HashMap<String, String> params){
		subjectdao.subjectUpdate(params);
	}

	@Override
	public void subjectDelete(HashMap<String, String> params){
		subjectdao.subjectDelete(params);
	}

	@Override
	public int subjectCheck(HashMap<String, String> params){
		return subjectdao.subjectCheck(params);
	}

	@Override
	public void subjectCategoryInsert(HashMap<String, String> params){
		subjectdao.subjectCategoryInsert(params);
	}

	@Override
	public void subjectCategoryDelete(HashMap<String, String> params){
		subjectdao.subjectCategoryDelete(params);
	}
	
	@Override
	public void subjectCategoryDeleteByCat(HashMap<String, String> params){
		subjectdao.subjectCategoryDeleteByCat(params);
	}
	
	@Override
	public void subjectCategoryOrderInsert(HashMap<String, String> params){
		subjectdao.subjectCategoryOrderInsert(params);
	}
	
	@Override
	public int chkSubjectCategoryOrderCnt(HashMap<String, String> params){
		return subjectdao.chkSubjectCategoryOrderCnt(params);
	}
	
	@Override
	public int chkSubjectCategoryCnt(HashMap<String, String> params){
		return subjectdao.chkSubjectCategoryCnt(params);
	}
	
	@Override
	public String getSubjectCategoryOrderIdx(HashMap<String, String> params){
		return subjectdao.getSubjectCategoryOrderIdx(params);
	}
	
	@Override
	public void subjectCategoryOrderDeleteByOnoff(HashMap<String, String> params){
		subjectdao.subjectCategoryOrderDeleteByOnoff(params);
	}
	
	@Override
	public void subjectCategoryOrderDelete(HashMap<String, String> params){
		subjectdao.subjectCategoryOrderDelete(params);
	}

	@Override
	public List<HashMap<String, String>> subjectCategoryView(HashMap<String, String> params){
		return subjectdao.subjectCategoryView(params);
	}

    @Override
    public List<HashMap<String, String>> findSubjectCategoryList(HashMap<String, String> params){
        return subjectdao.findSubjectCategoryList(params);
    }
	
	@Override
	public void main_category_subject_order_Insert(HashMap<String, String> params){
		subjectdao.main_category_subject_order_Insert(params);
	}

}
