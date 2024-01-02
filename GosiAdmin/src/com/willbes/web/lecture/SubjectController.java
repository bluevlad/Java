package com.willbes.web.lecture;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.lecture.service.SubjectService;
import com.willbes.web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/subject")
@Controller
public class SubjectController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private SubjectService subjectservice;

    @Autowired
    private TeacherService teacherservice;

    /**
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        // 강의관리>과목관리>상세화면에서 리스트로 돌아올때 활성/비활성을 모두 조회하기 위해
        params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), ""));
        
        List<HashMap<String, String>> list = subjectservice.subjectList(params);
        int listCount = subjectservice.subjectListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/lecture/subject/list";
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2013. 10.
     * @Method 설명 : 목록에서 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/listDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String listDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                params.put("SUBJECT_CD", DEL_ARR[i]);
                subjectservice.subjectDelete(params);
                subjectservice.subjectCategoryDelete(params);
            }
        }
        return "forward:/subject/list.do";
    }

    /**
     * @Method Name : write
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 등록폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/write.do")
    public String write(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        model.addAttribute("params", params);
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        model.addAttribute("kindlist", kindlist);

        return "/lecture/subject/write";
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목중복체크
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/codeCheck.do")
    @ResponseBody
    public String codeCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        params.put("SUBJECT_NM", URLDecoder.decode(params.get("SUBJECT_NM"),"UTF-8"));
        int listCount = subjectservice.subjectCheck(params);
        String returnStr = "Y";
        if(listCount > 0)
            returnStr = "N";
        return returnStr;
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/save.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String save(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        params.put("SUBJECT_CD", subjectservice.subjectGetCode(params));
        subjectservice.subjectInsert(params);
        subjectservice.subjectCategoryDelete(params);
        subjectservice.subjectCategoryOrderDelete(params);
		subjectservice.main_category_subject_order_Insert(params);

        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        if(CETCARR != null){
            for(int i=0; i<CETCARR.length; i++){
                params.put("CATEGORY_CODE", CETCARR[i]);
                subjectservice.subjectCategoryInsert(params);
                params.put("IDX", (i+1)+"");
                if("OM_ROOT".equals(params.get("MENUTYPE"))){ //온라인 페이지에서 과목 등록 시
                	params.put("ONOFF_DIV", "O");
                    subjectservice.subjectCategoryOrderInsert(params);
                }else if("FM_ROOT".equals(params.get("MENUTYPE"))){ //오프라인 페이지에서 과목 등록 시
                	params.put("ONOFF_DIV", "F");
                	subjectservice.subjectCategoryOrderInsert(params);
                }
            }
        }

        return "redirect:/subject/list.do";
    }

    /**
     * @Method Name : modify
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 수정폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/modify.do")
    public String modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        
        if("OM_ROOT".equals(params.get("MENUTYPE"))){
			params.put("ONOFF_DIV", "O");
		}else if("FM_ROOT".equals(params.get("MENUTYPE"))){
			params.put("ONOFF_DIV", "F");
		}
        
        List<HashMap<String, String>> list = subjectservice.subjectView(params);
        List<HashMap<String, String>> categoryList = subjectservice.subjectCategoryView(params);
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

        model.addAttribute("kindlist", kindlist);
        model.addAttribute("list", list);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("params", params);
        return "/lecture/subject/modify";
    }

    /**
     * @Method Name : update
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        subjectservice.subjectUpdate(params);
        //      subjectservice.subjectCategoryDelete(params);
        //		subjectservice.subjectCategoryOrderDelete(params);

        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        String subjectStr = "";
        if(CETCARR != null && CETCARR.length > 0){ //하나 이상 직렬 체크시
        	List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        	for(int i=0; i<CETCARR.length; i++){
	                params.put("ONOFF_DIV", "");
	                params.put("CATEGORY_CODE", CETCARR[i]);
	                int sbjCnt = subjectservice.chkSubjectCategoryCnt(params);
	                if(sbjCnt == 0){
	                	subjectservice.subjectCategoryInsert(params);
	                }
	                params.put("IDX", (i+1)+"");
	                if("OM_ROOT".equals(params.get("MENUTYPE"))){ //온라인 페이지에서 과목 수정 시
	                	 params.put("ONOFF_DIV", "O");
	                     int onSbjCnt = subjectservice.chkSubjectCategoryOrderCnt(params);
	                     if(onSbjCnt == 0){
	                     	subjectservice.subjectCategoryOrderInsert(params);
	                     }else{
	                    	 if(params.get("ORI_IDX") == null || "".equals(params.get("ORI_IDX"))){//기존 순서값 가져오기.
		                    	 String OriIdx = subjectservice.getSubjectCategoryOrderIdx(params); 
		                    	 params.put("ORI_IDX", OriIdx);
	                    	 }
	                    	 subjectservice.subjectCategoryOrderDeleteByOnoff(params); //기존 온라인 데이터 삭제
	                    	 subjectservice.subjectCategoryOrderInsert(params);
	                     }
	                }else if("FM_ROOT".equals(params.get("MENUTYPE"))){ //오프라인 페이지에서 과목 수정 시
	                	params.put("ONOFF_DIV", "F");
	                    int offSbjCnt = subjectservice.chkSubjectCategoryOrderCnt(params);
	                    if(offSbjCnt == 0){
	                    	subjectservice.subjectCategoryOrderInsert(params);
	                    }else{
	                    	if(params.get("ORI_IDX") == null || "".equals(params.get("ORI_IDX"))){//기존 순서값 가져오기.
	                    	String OriIdx = subjectservice.getSubjectCategoryOrderIdx(params); 
	                    	params.put("ORI_IDX", OriIdx);
	                	 }
	                	 subjectservice.subjectCategoryOrderDeleteByOnoff(params); //기존 오프라인 데이터 삭제
	                	 subjectservice.subjectCategoryOrderInsert(params);
	                    }
	                }
	                subjectStr += CETCARR[i]+",";
	                params.put("CATEGORY_CODE", "");
        	}
        }else{ //전체 직렬 미체크시
        	if("OM_ROOT".equals(params.get("MENUTYPE"))){ //온라인 페이지에서 과목 수정 시
        		params.put("ONOFF_DIV", "O");
        		subjectservice.subjectCategoryOrderDeleteByOnoff(params); //기존 온라인 데이터 삭제
        		params.put("ONOFF_DIV", "F");
        		int offobjCnt = subjectservice.chkSubjectCategoryOrderCnt(params);
                if(offobjCnt == 0){//오프라인에 대한 과목 직렬 없으면 TB_SUBJECT_CATEGORY의 과목도 삭제
                	subjectservice.subjectCategoryDelete(params);
                }
        	}else if("FM_ROOT".equals(params.get("MENUTYPE"))){ //오프라인 페이지에서 과목 수정 시
           		params.put("ONOFF_DIV", "F");
           		subjectservice.subjectCategoryOrderDeleteByOnoff(params); //기존 오프라인 데이터 삭제
           		params.put("ONOFF_DIV", "O");
        		int onobjCnt = subjectservice.chkSubjectCategoryOrderCnt(params); 
                if(onobjCnt == 0){ //온라인에 대한 과목 직렬 없으면 TB_SUBJECT_CATEGORY의 과목도 삭제
                	subjectservice.subjectCategoryDelete(params);
                }
        	}
        }
        
        if(!"".equals(subjectStr)){
        	List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
	        for(int j=0; j< kindlist.size();j++){ //전체 직렬이랑 체크된 직렬 비교 
	        	if(!subjectStr.contains(kindlist.get(j).get("CODE"))){
		    		params.put("CATEGORY_CODE", kindlist.get(j).get("CODE"));
		    		if("OM_ROOT".equals(params.get("MENUTYPE"))){ //온라인 페이지에서 과목 수정 시
		        		params.put("ONOFF_DIV", "O");
		        		subjectservice.subjectCategoryOrderDeleteByOnoff(params); //체크 안된 직렬 과목 삭제
		        		params.put("ONOFF_DIV", "F");
		        		int offobjCnt = subjectservice.chkSubjectCategoryOrderCnt(params);
		                if(offobjCnt == 0){//오프라인에 대한 과목 직렬 없으면 TB_SUBJECT_CATEGORY의 직렬에 대한 과목도 삭제
		                	subjectservice.subjectCategoryDeleteByCat(params);
		                }
		        	}else if("FM_ROOT".equals(params.get("MENUTYPE"))){ //오프라인 페이지에서 과목 수정 시
		           		params.put("ONOFF_DIV", "F");
		           		subjectservice.subjectCategoryOrderDeleteByOnoff(params); //체크 안된 직렬 과목 삭제
		           		params.put("ONOFF_DIV", "O");
		        		int onobjCnt = subjectservice.chkSubjectCategoryOrderCnt(params); 
		                if(onobjCnt == 0){ //온라인에 대한 과목 직렬 없으면 TB_SUBJECT_CATEGORY의 직렬에 대한 과목도 삭제
		                	subjectservice.subjectCategoryDeleteByCat(params);
		                }
		        	}
	        	}
	        	params.put("CATEGORY_CODE", "");
	        }
        }

        return "redirect:/subject/list.do";
    }

    /**
     * @Method Name : delete
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/delete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        subjectservice.subjectDelete(params);
        subjectservice.subjectCategoryDelete(params);
        return "forward:/subject/list.do";
    }

    /**
     * @Method Name : findCategorySubjectList
     * @작성일 : 2015. 07.
     * @Method 설명 : 직종/급별 과목 목록
     * @param model
     * @param request
     * @return List<HashMap<String, String>>
     * @throws Exception
     */
    @RequestMapping(value="/find_category_subject.json")
    @ResponseBody
    public List<HashMap<String, String>> findSubjectCategoryList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));

        return subjectservice.findSubjectCategoryList(params);
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2013. 10.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), "30"));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
        params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("SEARCHGUBN", "T");
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
    }

}
