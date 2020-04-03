package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lecture.service.TeacherService;
import web.lecture.service.impl.TeacherDAO;

@Service(value="teacherservice")
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDAO teacherdao;

    @Override
    public List<HashMap<String, String>> getKindList(HashMap<String, String> params){
        return teacherdao.getKindList(params);
    }

    @Override
    public List<HashMap<String, String>> getSubjectList(HashMap<String, String> params){
        return teacherdao.getSubjectList(params);
    }

    @Override
    public List<HashMap<String, String>> teacherList(HashMap<String, String> params){
        return teacherdao.teacherList(params);
    }

    @Override
    public int teacherListCount(HashMap<String, String> params){
        return teacherdao.teacherListCount(params);
    }

    @Override
    public List<HashMap<String, String>> teacherAllList(HashMap<String, String> params){
        return teacherdao.teacherAllList(params);
    }

    @Override
    public int teacherIdCheck(HashMap<String, String> params){
        return teacherdao.teacherIdCheck(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void teacherInsert(Object obj){

        teacherdao.teacherInsert(obj);
        teacherdao.teacherInfoInsert(obj);

        String[] ORI_CATEGORY_CODE = (String[])((HashMap<String, Object>)obj).get("ORI_CATEGORY_CODE");
        String[] CETCARR = (String[])((HashMap<String, Object>)obj).get("CETCARR");

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                teacherdao.teacherCategoryInsert(obj);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    ((HashMap<String, String>)obj).put("DEL_CATEGORY_CODE", ORI_CATEGORY_CODE[i]);
                    teacherdao.teacherCategoryDelete(obj);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                    teacherdao.teacherCategoryInsert(obj);
                }
            }
        }

        teacherdao.teacherSubjectDelete(obj);

        String[] SETCARR = (String[])((HashMap<String, Object>)obj).get("SETCARR");
        if(SETCARR != null){
            for(int i=0; i<SETCARR.length; i++){
                ((HashMap<String, String>)obj).put("SUBJECT_CD", SETCARR[i]);
                int teacherMain_Category_Subject = teacherdao.teacherMain_Category_Subject(obj); // 등록
                int teacherIntro_Category_Subject = teacherdao.teacherIntro_Category_Subject(obj); // 등록
                teacherdao.teacherSubjectInsert(obj);
                
				if(ORI_CATEGORY_CODE == null) {
					teacherdao.teacherMain_Category_Insert(obj); // 등록
					teacherdao.teacherIntro_Category_Insert(obj);
					teacherdao.teacherIntro_F_Category_Insert(obj);
				}else{
					
					if(teacherMain_Category_Subject == 0){
						
						teacherdao.teacherMain_Category_Insert(obj); // 수정
					}else{
					
					}
					if(teacherIntro_Category_Subject == 0){
						
						teacherdao.teacherIntro_Category_Insert(obj);
						teacherdao.teacherIntro_F_Category_Insert(obj);
					}else{
					
					}					
				}               

            }
        }

    }

    @Override
    public List<HashMap<String, String>> teacherView(HashMap<String, String> params){
        return teacherdao.teacherView(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void teacherUpdate(Object obj){
        teacherdao.teacherUpdate(obj);
        teacherdao.teacherInfoUpdate(obj);

        String[] ORI_CATEGORY_CODE = (String[])((HashMap<String, Object>)obj).get("ORI_CATEGORY_CODE");
        String[] CETCARR = (String[])((HashMap<String, Object>)obj).get("CETCARR");

        if(ORI_CATEGORY_CODE == null) { // 등록
            for(int i=0; i<CETCARR.length; i++){
                ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                teacherdao.teacherCategoryInsert(obj);
            }
        } else {    // 수정
            for(int i=0; i<ORI_CATEGORY_CODE.length;i++){
                boolean isDel = true;
                for(int j=0; j<CETCARR.length; j++){
                    if(ORI_CATEGORY_CODE[i].equals(CETCARR[j])) {
                        isDel = false;
                    }
                }
                if(isDel) {
                    ((HashMap<String, String>)obj).put("DEL_CATEGORY_CODE", ORI_CATEGORY_CODE[i]);
                    teacherdao.teacherCategoryDelete(obj);
                }
            }

            for(int i=0; i<CETCARR.length; i++){
                boolean isIns = true;
                for(int j=0; j<ORI_CATEGORY_CODE.length;j++){
                    if(CETCARR[i].equals(ORI_CATEGORY_CODE[j])) {
                        isIns = false;
                    }
                }
                if(isIns) {
                    ((HashMap<String, String>)obj).put("CATEGORY_CODE", CETCARR[i]);
                    teacherdao.teacherCategoryInsert(obj);
                }
            }
        }

        teacherdao.teacherSubjectDelete(obj); 
        teacherdao.teacherMain_Category_Delete(obj);
        teacherdao.teacherIntro_Category_Delete(obj);
        teacherdao.teacherIntro_F_Category_Delete(obj);
        
        String[] SETCARR = (String[])((HashMap<String, Object>)obj).get("SETCARR");
        String[] OFF_SETCARR = (String[])((HashMap<String, Object>)obj).get("OFF_SETCARR");
        String ON_SBJ = "";
        if(SETCARR != null && SETCARR.length > 0){
            for(int i=0; i<SETCARR.length; i++){
                ((HashMap<String, String>)obj).put("SUBJECT_CD", SETCARR[i]);
                ((HashMap<String, String>)obj).put("USE_ON", "Y");
                ((HashMap<String, String>)obj).put("USE_OFF", "N");
                teacherdao.teacherSubjectInsert(obj);
                
                if(ORI_CATEGORY_CODE == null) {
                    teacherdao.teacherMain_Category_Insert(obj); // 등록
                    teacherdao.teacherIntro_Category_Insert(obj);
                    teacherdao.teacherIntro_F_Category_Insert(obj);
                }else{
                	int teacherMain_Category_Subject = teacherdao.teacherMain_Category_Subject(obj); 
                    int teacherIntro_Category_Subject = teacherdao.teacherIntro_Category_Subject(obj); 
                    int teacherIntro_F_Category_Subject = teacherdao.teacherIntro_F_Category_Subject(obj);
                    if(teacherMain_Category_Subject == 0){
                        
                        teacherdao.teacherMain_Category_Insert(obj); // 수정
                        
                    }
                    if(teacherIntro_Category_Subject == 0){
						teacherdao.teacherIntro_Category_Insert(obj); // 수정
					}
                    if(teacherIntro_F_Category_Subject == 0){
						teacherdao.teacherIntro_F_Category_Insert(obj);
					}

                }
                ON_SBJ += SETCARR[i]+",";
          }
       }
        ((HashMap<String, String>)obj).put("USE_ON", "");
        ((HashMap<String, String>)obj).put("USE_OFF", "");
        if(OFF_SETCARR != null && OFF_SETCARR.length > 0){
            for(int j=0; j<OFF_SETCARR.length; j++){
            	if(ON_SBJ.contains(OFF_SETCARR[j])){
            		((HashMap<String, String>)obj).put("USE_OFF", "Y");
        			teacherdao.teacherSubjectUpdate(obj);
            	}else{
            		((HashMap<String, String>)obj).put("SUBJECT_CD", OFF_SETCARR[j]);
            		((HashMap<String, String>)obj).put("USE_ON", "N");
                    ((HashMap<String, String>)obj).put("USE_OFF", "Y");
                    int sbjCnt = teacherdao.teacherSubjectCount(obj);
                    if(sbjCnt==0){
                    	teacherdao.teacherSubjectInsert(obj);
                        
                        if(ORI_CATEGORY_CODE == null && (SETCARR == null || SETCARR.length < 1)) {
                            teacherdao.teacherMain_Category_Insert(obj); // 등록
                            teacherdao.teacherIntro_Category_Insert(obj);
                            teacherdao.teacherIntro_F_Category_Insert(obj);
                        }else{
                        	int teacherMain_Category_Subject = teacherdao.teacherMain_Category_Subject(obj); 
                            int teacherIntro_Category_Subject = teacherdao.teacherIntro_Category_Subject(obj); 
                            int teacherIntro_F_Category_Subject = teacherdao.teacherIntro_F_Category_Subject(obj);
                            if(teacherMain_Category_Subject == 0){
                                
                                teacherdao.teacherMain_Category_Insert(obj); // 수정
                                
                            }
                            if(teacherIntro_Category_Subject == 0){
        						teacherdao.teacherIntro_Category_Insert(obj); // 수정
        					}
                            if(teacherIntro_F_Category_Subject == 0){
        						teacherdao.teacherIntro_F_Category_Insert(obj);
        					} 
                        }
                    }
            	}
            	((HashMap<String, String>)obj).put("USE_ON", "");
                ((HashMap<String, String>)obj).put("USE_OFF", "");
            }
        }

    }

    @Override
    public void teacherCategoryInsert(HashMap<String, String> params){
        teacherdao.teacherCategoryInsert(params);
    }

    @Override
    public void teacherSubjectInsert(HashMap<String, String> params){
        teacherdao.teacherSubjectInsert(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void teacherIsUseUpdate(Object obj){
        String[] DEL_ARR = (String[])((HashMap<String, Object>)obj).get("DEL_ARR");
        if(DEL_ARR != null && DEL_ARR.length > 0){
            for(int i=0; i<DEL_ARR.length; i++){
                HashMap<String, String> params = new  HashMap<String, String>();
                params.put("USER_ID", DEL_ARR[i]);

                teacherdao.teacherIsUseUpdate(params);
            }
        }
    }

    @Override
    public void teacherDelete(HashMap<String, String> params){
        teacherdao.teacherDelete(params);
    }

    @Override
    public void teacherCategoryDelete(HashMap<String, String> params){
        teacherdao.teacherCategoryDelete(params);
    }

    @Override
    public void teacherSubjectDelete(HashMap<String, String> params){
        teacherdao.teacherSubjectDelete(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void teacherSeqUpdate(Object obj){
        String[] NUM = (String[])((HashMap<String, Object>)obj).get("NUM");
        String[] SEQARR = (String[])((HashMap<String, Object>)obj).get("SEQ");
        String[] PROFESSOR_USER_IDARR = (String[])((HashMap<String, Object>)obj).get("USER_ID");
        String[] SUBJECT_CD = (String[])((HashMap<String, Object>)obj).get("SUBJECT_CD");

        if(SEQARR != null && SEQARR.length > 0){
            for(int i=0; i<SEQARR.length; i++){
                HashMap<String, String> params = new  HashMap<String, String>();
                params.put("NUM", NUM[i]);
                params.put("SEQ", SEQARR[i]);
                params.put("USER_ID", PROFESSOR_USER_IDARR[i]);
                params.put("SUBJECT_CD", SUBJECT_CD[i]);
                params.put("ONOFFDIV", ((HashMap<String, Object>)obj).get("ONOFFDIV").toString());
                params.put("SEARCHCATEGORY", ((HashMap<String, Object>)obj).get("SEARCHCATEGORY").toString());

                teacherdao.teacherSeqUpdate(params);
            }
        }
    }

    @Override
    public List<HashMap<String, String>> teacherBookLog(HashMap<String, String> params){
        return teacherdao.teacherBookLog(params);
    }

	@Override
	public void teacherMain_Category_Insert(HashMap<String, String> params){
		teacherdao.teacherMain_Category_Insert(params);
	}

	@Override
	public void teacherMain_Category_Delete(HashMap<String, String> params){
		teacherdao.teacherMain_Category_Delete(params);
	}
	
	@Override
	public void teacherIntro_Category_Insert(HashMap<String, String> params){
		teacherdao.teacherIntro_Category_Insert(params);
	}
	
	@Override
	public void teacherIntro_F_Category_Insert(HashMap<String, String> params){
		teacherdao.teacherIntro_F_Category_Insert(params);
	}

	@Override
	public void teacherIntro_Category_Delete(HashMap<String, String> params){
		teacherdao.teacherIntro_Category_Delete(params);
	}
	
	@Override
	public void teacherIntro_F_Category_Delete(HashMap<String, String> params){
		teacherdao.teacherIntro_F_Category_Delete(params);
	}

	@Override
	public List<HashMap<String, String>> teacherMainList(HashMap<String, String> params){
		return teacherdao.teacherMainList(params);
	}

	@Override
	public int teacherMainListCount(HashMap<String, String> params){
		return teacherdao.teacherMainListCount(params);
	}

    @Override
    public List<HashMap<String, String>> findTeacherList(HashMap<String, String> params){
        return teacherdao.findTeacherList(params);
    }

	@Override
	public int teacherMain_Category_Subject(HashMap<String, String> params){
		return teacherdao.teacherMain_Category_Subject(params);
	}
	
	@Override
	public int teacherIntro_Category_Subject(HashMap<String, String> params){
		return teacherdao.teacherIntro_Category_Subject(params);
	}
	
	@Override
	public int teacherIntro_F_Category_Subject(HashMap<String, String> params){
		return teacherdao.teacherIntro_F_Category_Subject(params);
	}
	
	@Override
	public List<HashMap<String, String>> teacherIntroList(HashMap<String, String> params){
		return teacherdao.teacherIntroList(params);
	}

	@Override
	public int teacherIntroListCount(HashMap<String, String> params){
		return teacherdao.teacherIntroListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> teacherIntro_offList(HashMap<String, String> params){
		return teacherdao.teacherIntro_offList(params);
	}

	@Override
	public int teacherIntro_offListCount(HashMap<String, String> params){
		return teacherdao.teacherIntro_offListCount(params);
	}

}
