package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class TeacherDAO extends CmmAbstractMapper {

    public List<HashMap<String, String>> getKindList(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.getKindList", params);
    }

    public List<HashMap<String, String>> getSubjectList(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.getSubjectList", params);
    }

    public List<HashMap<String, String>> teacherList(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.teacherList", params);
    }

    public int teacherListCount(HashMap<String, String> params){
        return getSqlSession().selectOne("teacher.teacherListCount", params);
    }

    public List<HashMap<String, String>> teacherAllList(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.teacherAllList", params);
    }

    public int teacherIdCheck(HashMap<String, String> params){
        return getSqlSession().selectOne("teacher.teacherIdCheck", params);
    }

    public void teacherInsert(Object obj){
        getSqlSession().insert("teacher.teacherInsert", obj);
    }

    public List<HashMap<String, String>> teacherView(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.teacherView", params);
    }

    public void teacherUpdate(Object obj){
        getSqlSession().update("teacher.teacherUpdate", obj);
    }

    public void teacherCategoryInsert(Object obj){
        getSqlSession().insert("teacher.teacherCategoryInsert", obj);
    }

    public void teacherSubjectInsert(Object obj){
        getSqlSession().insert("teacher.teacherSubjectInsert", obj);
    }
    
    public void teacherSubjectUpdate(Object obj){
        getSqlSession().update("teacher.teacherSubjectUpdate", obj);
    }
    
    public int teacherSubjectCount(Object obj){
        return getSqlSession().selectOne("teacher.teacherSubjectCount", obj);
    }
    
    public void teacherIsUseUpdate(Object obj){
        getSqlSession().delete("teacher.teacherIsUseUpdate", obj);
    }

    public void teacherDelete(HashMap<String, String> params){
        getSqlSession().delete("teacher.teacherDelete", params);
    }

    public void teacherCategoryDelete(Object obj){
        getSqlSession().delete("teacher.teacherCategoryDelete", obj);
    }

    public void teacherSubjectDelete(Object obj){
        getSqlSession().delete("teacher.teacherSubjectDelete", obj);
    }

    public void teacherSeqUpdate(Object obj){
        getSqlSession().update("teacher.teacherSeqUpdate", obj);
    }

    public List<HashMap<String, String>> teacherBookLog(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.teacherBookLog", params);
    }

	public void teacherMain_Category_Insert(Object obj){
		getSqlSession().insert("teacher.teacherMain_Category_Insert", obj);
	}

	public void teacherMain_Category_Delete(Object obj){
		getSqlSession().delete("teacher.teacherMain_Category_Delete", obj);
	}

	public void teacherIntro_Category_Insert(Object obj){
		getSqlSession().insert("teacher.teacherIntro_Category_Insert", obj);
	}
	
	public void teacherIntro_F_Category_Insert(Object obj){
		getSqlSession().insert("teacher.teacherIntro_F_Category_Insert", obj);
	}

	public void teacherIntro_Category_Delete(Object obj){
		getSqlSession().delete("teacher.teacherIntro_Category_Delete", obj);
	}
	
	public void teacherIntro_F_Category_Delete(Object obj){
		getSqlSession().delete("teacher.teacherIntro_F_Category_Delete", obj);
	}
	
	public List<HashMap<String, String>> teacherMainList(HashMap<String, String> params){
		return getSqlSession().selectList("teacher.teacherMainList", params);
	}

	public int teacherMainListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("teacher.teacherMainListCount", params);
	}

    public List<HashMap<String, String>> findTeacherList(HashMap<String, String> params){
        return getSqlSession().selectList("teacher.findTeacherList", params);
    }

	public int teacherMain_Category_Subject(Object obj){
		return getSqlSession().selectOne("teacher.teacherMain_Category_Subject", obj);
	}
	
	public int teacherIntro_Category_Subject(Object obj){
		return getSqlSession().selectOne("teacher.teacherIntro_Category_Subject", obj);
	}	
	
	public int teacherIntro_F_Category_Subject(Object obj){
		return getSqlSession().selectOne("teacher.teacherIntro_F_Category_Subject", obj);
	}
	
	public List<HashMap<String, String>> teacherIntroList(HashMap<String, String> params){
		return getSqlSession().selectList("teacher.teacherIntroList", params);
	}

	public int teacherIntroListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("teacher.teacherIntroListCount", params);
	}
	
	public List<HashMap<String, String>> teacherIntro_offList(HashMap<String, String> params){
		return getSqlSession().selectList("teacher.teacherIntro_offList", params);
	}

	public int teacherIntro_offListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("teacher.teacherIntro_offListCount", params);
	}
	
	public int teacherAllListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("teacher.teacherAllListCount", params);
	}

}
