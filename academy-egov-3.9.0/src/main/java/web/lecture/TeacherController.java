package web.lecture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/teacher")
@Controller
public class TeacherController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;

	@Autowired
	private TeacherService teacherservice;

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 목록
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

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> list;
		int listCount = 0;
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

		if(params.get("SEARCHCATEGORY").equals("main")){
			list = teacherservice.teacherMainList(params);
			listCount = teacherservice.teacherMainListCount(params);
		}else if(params.get("SEARCHCATEGORY").equals("intro")){
			list = teacherservice.teacherIntroList(params); 
			listCount = teacherservice.teacherIntroListCount(params);
		}else if(params.get("SEARCHCATEGORY").equals("intro_off")){
			list = teacherservice.teacherIntro_offList(params); 
			listCount = teacherservice.teacherIntro_offListCount(params);
		}else{
			list = teacherservice.teacherList(params);
			listCount = teacherservice.teacherListCount(params);
		}

		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		model.addAttribute("params", params);

		return "/lecture/teacher/list";
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
	public String listDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null && DEL_ARR.length > 0){
            params.put("DEL_ARR", DEL_ARR);
				teacherservice.teacherIsUseUpdate(params);
			}

		return "forward:/teacher/list.do";
	}

	/**
	 * @Method Name : write
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 등록폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("SEARCHGUBN", "T");
		params.put("ONOFF_DIV", "");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("ONOFF_DIV", "O");
		List<HashMap<String, String>> onsubjectlist = teacherservice.getSubjectList(params);
		params.put("ONOFF_DIV", "F");
		List<HashMap<String, String>> offsubjectlist = teacherservice.getSubjectList(params);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("onsubjectlist", onsubjectlist);
		model.addAttribute("offsubjectlist", offsubjectlist);
		model.addAttribute("params", params);

		return "/lecture/teacher/write";
	}

	/**
	 * @Method Name : idCheck
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 id 중복체크
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/idCheck.do")
	@ResponseBody
	public String codeCheck(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

	    int listCount = teacherservice.teacherIdCheck(params);
	    String returnStr = "Y";
	    if(listCount > 0)
	    	returnStr = "N";
	    return returnStr;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/save.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String save(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

		lecFileProcess(params, multipartRequest);

        String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        String[] SETCARR = request.getParameterValues("SUBJECT_CD");
        params.put("ORI_CATEGORY_CODE", ORI_CATEGORY_CODE);
        params.put("CETCARR", CETCARR);
        params.put("SETCARR", SETCARR);

		teacherservice.teacherInsert(params);

		return "redirect:/teacher/list.do";
	}

	/**
	 * @Method Name : modify
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 수정폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> view = teacherservice.teacherView(params);
		params.put("ONOFF_DIV", "");
		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHGUBN", "E");
		params.put("ONOFF_DIV", "O");
		List<HashMap<String, String>> eonsubjectlist = teacherservice.getSubjectList(params); //온라인 제외한 과목
		params.put("ONOFF_DIV", "F");
		List<HashMap<String, String>> eoffsubjectlist = teacherservice.getSubjectList(params);//오프라인제외한 과목
		params.put("SEARCHGUBN", "M");
		params.put("ONOFF_DIV", "O");
		List<HashMap<String, String>> monsubjectlist = teacherservice.getSubjectList(params); //온라인 해당교수과목
		params.put("ONOFF_DIV", "F");
		List<HashMap<String, String>> moffsubjectlist = teacherservice.getSubjectList(params);//오프라인 해당교수과목
		List<HashMap<String, String>> mkindlist = teacherservice.getKindList(params);
		List<HashMap<String, String>> bookloglist = teacherservice.teacherBookLog(params);
		model.addAttribute("view", view);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("mkindlist", mkindlist);			// 강사가 선택한 카테고리
		model.addAttribute("eonsubjectlist", eonsubjectlist);		// 강사가 선택한것을 제외한 전체과목(온라인)
		model.addAttribute("eoffsubjectlist", eoffsubjectlist);		// 강사가 선택한것을 제외한 전체과목(오프라인)
		model.addAttribute("monsubjectlist", monsubjectlist);	// 강사가 선택한 과목(온라인)
		model.addAttribute("moffsubjectlist", moffsubjectlist);	// 강사가 선택한 과목(오프라인)
		model.addAttribute("bookloglist", bookloglist);		// 교재에 등록된 강사 저서 목록
		model.addAttribute("params", params);
		return "/lecture/teacher/modify";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String update(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);

		String rootPath = fsResource.getPath();
		if("Y".equals(params.get("PIC1_DEL")) || !"".equals(params.get("PIC1")))
			fileUtil.deleteFile(rootPath + params.get("PIC1_DELNM"));
		if("Y".equals(params.get("PIC2_DEL")) || !"".equals(params.get("PIC2")))
			fileUtil.deleteFile(rootPath + params.get("PIC2_DELNM"));
		if("Y".equals(params.get("PIC3_DEL")) || !"".equals(params.get("PIC3")))
			fileUtil.deleteFile(rootPath + params.get("PIC3_DELNM"));
		if("Y".equals(params.get("PIC4_DEL")) || !"".equals(params.get("PIC4")))
			fileUtil.deleteFile(rootPath + params.get("PIC4_DELNM"));
		if("Y".equals(params.get("OFF_PIC1_DEL")) || !"".equals(params.get("OFF_PIC1")))
			fileUtil.deleteFile(rootPath + params.get("OFF_PIC1_DELNM"));
		if("Y".equals(params.get("OFF_PIC2_DEL")) || !"".equals(params.get("OFF_PIC2")))
			fileUtil.deleteFile(rootPath + params.get("OFF_PIC2_DELNM"));
		if("Y".equals(params.get("OFF_PIC3_DEL")) || !"".equals(params.get("OFF_PIC3")))
			fileUtil.deleteFile(rootPath + params.get("OFF_PIC3_DELNM"));
		if("Y".equals(params.get("OFF_PIC4_DEL")) || !"".equals(params.get("OFF_PIC4")))
			fileUtil.deleteFile(rootPath + params.get("OFF_PIC4_DELNM"));
		if("Y".equals(params.get("OFF_PIC5_DEL")) || !"".equals(params.get("OFF_PIC5")))
			fileUtil.deleteFile(rootPath + params.get("OFF_PIC5_DELNM"));

        if("Y".equals(params.get("PIC5_DEL")) || !"".equals(params.get("PIC5")))
            fileUtil.deleteFile(rootPath + params.get("PIC5_DELNM"));
        if("Y".equals(params.get("PIC6_DEL")) || !"".equals(params.get("PIC6")))
            fileUtil.deleteFile(rootPath + params.get("PIC6_DELNM"));
        if("Y".equals(params.get("OFF_PIC6_DEL")) || !"".equals(params.get("OFF_PIC6")))
            fileUtil.deleteFile(rootPath + params.get("OFF_PIC6_DELNM"));
        if("Y".equals(params.get("PIC7_DEL")) || !"".equals(params.get("PIC7")))
            fileUtil.deleteFile(rootPath + params.get("PIC7_DELNM"));
        if("Y".equals(params.get("OFF_PIC7_DEL")) || !"".equals(params.get("OFF_PIC7")))
            fileUtil.deleteFile(rootPath + params.get("OFF_PIC7_DELNM"));
        if("Y".equals(params.get("PIC8_DEL")) || !"".equals(params.get("PIC8")))
            fileUtil.deleteFile(rootPath + params.get("PIC8_DELNM"));
        if("Y".equals(params.get("OFF_PIC8_DEL")) || !"".equals(params.get("OFF_PIC8")))
            fileUtil.deleteFile(rootPath + params.get("OFF_PIC8_DELNM"));
        if("Y".equals(params.get("PIC9_DEL")) || !"".equals(params.get("PIC9")))
            fileUtil.deleteFile(rootPath + params.get("PIC9_DELNM"));
        if("Y".equals(params.get("OFF_PIC9_DEL")) || !"".equals(params.get("OFF_PIC9")))
            fileUtil.deleteFile(rootPath + params.get("OFF_PIC9_DELNM"));
        if("Y".equals(params.get("PIC10_DEL")) || !"".equals(params.get("PIC10")))
            fileUtil.deleteFile(rootPath + params.get("PIC10_DELNM"));
        if("Y".equals(params.get("OFF_PIC10_DEL")) || !"".equals(params.get("OFF_PIC10")))
            fileUtil.deleteFile(rootPath + params.get("OFF_PIC10_DELNM"));
       // TeacherOnOff_YN, PRF_ONPIC1, PRF_ONPIC2, PRF_ONPIC3, PRF_OFFPIC1, PRF_OFFPIC2, PRF_OFFPIC3
        if("Y".equals(params.get("PRF_ONPIC1_DEL")) || !"".equals(params.get("PRF_ONPIC1")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_ONPIC1_DEL"));

        if("Y".equals(params.get("PRF_ONPIC2_DEL")) || !"".equals(params.get("PRF_ONPIC2")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_ONPIC2_DEL"));

        if("Y".equals(params.get("PRF_ONPIC3_DEL")) || !"".equals(params.get("PRF_ONPIC3")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_ONPIC3_DEL"));

        if("Y".equals(params.get("PRF_OFFPIC1_DEL")) || !"".equals(params.get("PRF_OFFPIC1")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_OFFPIC1_DEL"));

        if("Y".equals(params.get("PRF_OFFPIC2_DEL")) || !"".equals(params.get("PRF_OFFPIC2")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_OFFPIC2_DEL"));

        if("Y".equals(params.get("PRF_OFFPIC3_DEL")) || !"".equals(params.get("PRF_OFFPIC2")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_OFFPIC3_DEL"));

        //리스트 배너
        if("Y".equals(params.get("PRF_LISTONBANNER_DEL")) || !"".equals(params.get("PRF_LISTONBANNER")))
        	fileUtil.deleteFile(rootPath + params.get("PPRF_LISTONBANNER_DEL"));

        if("Y".equals(params.get("PRF_LISTOFFBANNER_DEL")) || !"".equals(params.get("PRF_LISTOFFBANNER")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_LISTOFFBANNER_DEL"));
        
        //교수소개 이미지
        if("Y".equals(params.get("PROF_IMG_DEL")) || !"".equals(params.get("PROF_IMG")))
        	fileUtil.deleteFile(rootPath + params.get("PROF_IMG_DEL"));

        if("Y".equals(params.get("OFF_PROF_IMG_DEL")) || !"".equals(params.get("OFF_PROF_IMG")))
        	fileUtil.deleteFile(rootPath + params.get("OFF_PROF_IMG_DEL"));
        
        //리스트 배너
        if("Y".equals(params.get("PRF_TOPONIMG_DEL")) || !"".equals(params.get("PRF_TOPONIMG")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_TOPONIMG_DEL"));

        if("Y".equals(params.get("PRF_TOPOFFIMG_DEL")) || !"".equals(params.get("PRF_TOPOFFIMG")))
        	fileUtil.deleteFile(rootPath + params.get("PRF_TOPOFFIMG_DEL"));

        String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        String[] SETCARR = request.getParameterValues("SUBJECT_CD");
        String[] OFF_SETCARR = request.getParameterValues("OFF_SUBJECT_CD");
        params.put("ORI_CATEGORY_CODE", ORI_CATEGORY_CODE);
        params.put("CETCARR", CETCARR);
        params.put("SETCARR", SETCARR);
        params.put("OFF_SETCARR", OFF_SETCARR);

		teacherservice.teacherUpdate(params);
		//etcProcess(params, request);
		return "redirect:/teacher/list.do";
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);

        String[] DEL_ARR = {params.get("USER_ID").toString()};
        params.put("DEL_ARR", DEL_ARR);

		teacherservice.teacherIsUseUpdate(params);

		return "forward:/teacher/list.do";
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 순번 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/seqUpdate.do")
	public String seqUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
		setParam(params, request, model);
		
		String[] SEQARR = request.getParameterValues("SEQ");
        if(SEQARR != null && SEQARR.length > 0){
            params.put("NUM", request.getParameterValues("Num"));
            params.put("SEQ", SEQARR);
            params.put("USER_ID", request.getParameterValues("PROFESSOR_USER_ID"));
            params.put("SUBJECT_CD", request.getParameterValues("SUBJECT_CD"));
				
				teacherservice.teacherSeqUpdate(params);
			}

        return "forward:/teacher/list.do";
    }

    /**
     * @Method Name : excelDownload
     * @작성일 : 2015. 06.
     * @Method 설명 : 회원 명단
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/excel.do")
    public View excelDownload(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        // 회원 리스트
        List<HashMap<String, String>> list = teacherservice.teacherList(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_교수자 명단";

        List<String> headerList = new ArrayList<String>();
        headerList.add("ID");
        headerList.add("이름");
        headerList.add("과목");
        headerList.add("분류");
        headerList.add("상태(ON)");
        headerList.add("상태(OFF)");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID").toString())) {
                newMap.put(i++, map.get("USER_ID").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM").toString())) {
                newMap.put(i++, map.get("USER_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("SUBJECT_NM") && !"".equals(map.get("SUBJECT_NM").toString())) {
                newMap.put(i++, map.get("SUBJECT_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("CATEGORY_NM") && !"".equals(map.get("CATEGORY_NM").toString())) {
                newMap.put(i++, map.get("CATEGORY_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ON_OPENYNNM") && !"".equals(map.get("ON_OPENYNNM").toString())) {
                newMap.put(i++, map.get("ON_OPENYNNM").toString());
            } else {
                newMap.put(i++, "");
		}
            if(null != map.get("OFF_OPENYNNM") && !"".equals(map.get("OFF_OPENYNNM").toString())) {
                newMap.put(i++, map.get("OFF_OPENYNNM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("REG_DT") && !"".equals(map.get("REG_DT"))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
                newMap.put(i++, (sdf.format(map.get("REG_DT"))).substring(0, 10));
            } else {
                newMap.put(i++, "");
            }

            newList.add(newMap);
        }
        model.addAttribute("excelName", excelName);
        model.addAttribute("headerList", headerList);
        model.addAttribute("dataList", newList);

        return new ExcelDownloadView();
	}

    /**
     * @Method Name : findTeacherList
     * @작성일 : 2015. 07.
     * @Method 설명 : 강사 목록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/find_teacher_list.json")
    @ResponseBody
    public  List<HashMap<String, String>> findTeacherList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHSBJTCD", CommonUtil.isNull(request.getParameter("SEARCHSBJTCD"), ""));

        return teacherservice.findTeacherList(params);
    }

    /**
     * @Method Name : list_pop
     * @작성일 : 2013. 10.
     * @Method 설명 : 강사 목록 팝업
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/list_pop.pop")
    public String listPop(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SRCHCODE", CommonUtil.isNull(request.getParameter("SRCHCODE"), ""));
        params.put("SRCHTXT", CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = teacherservice.teacherList(params);
        int listCount = teacherservice.teacherListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/lecture/teacher/list_pop";
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {//
		HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("ONOFFDIV", CommonUtil.isNull(request.getParameter("ONOFFDIV"), ""));

		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
		params.put("USER_NICKNM", CommonUtil.isNull(request.getParameter("USER_NICKNM"), ""));
		params.put("USER_POSITION", CommonUtil.isNull(request.getParameter("USER_POSITION"), ""));
		params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "PRF"));
		params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
		params.put("BIRTH_DAY", CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
		params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("ACCOUNT", CommonUtil.isNull(request.getParameter("ACCOUNT"), ""));
		params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
		params.put("USER_POINT", CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
		params.put("ON_OPENYN", CommonUtil.isNull(request.getParameter("ON_OPENYN"), "Y"));
		params.put("OFF_OPENYN", CommonUtil.isNull(request.getParameter("OFF_OPENYN"), "Y"));
		params.put("PRF_BRD_ON", CommonUtil.isNull(request.getParameter("PRF_BRD_ON"), "N"));
		params.put("PRF_BRD_OF", CommonUtil.isNull(request.getParameter("PRF_BRD_OF"), "N"));

        params.put("PAYMENT", CommonUtil.isNull(request.getParameter("PAYMENT"), ""));
        params.put("OFF_PAYMENT", CommonUtil.isNull(request.getParameter("OFF_PAYMENT"), ""));
        params.put("ON_URL", CommonUtil.isNull(request.getParameter("ON_URL"), ""));
        params.put("OFF_URL", CommonUtil.isNull(request.getParameter("OFF_URL"), ""));
        params.put("BRD_YN", CommonUtil.isNull(request.getParameter("BRD_YN"), ""));
        params.put("OFF_BRD_YN", CommonUtil.isNull(request.getParameter("OFF_BRD_YN"), ""));
        params.put("PROFILE", CommonUtil.isNull(request.getParameter("PROFILE"), ""));
        params.put("OFF_PROFILE", CommonUtil.isNull(request.getParameter("OFF_PROFILE"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("OFF_TITLE", CommonUtil.isNull(request.getParameter("OFF_TITLE"), ""));
		params.put("BOOK_LOG", CommonUtil.isNull(request.getParameter("BOOK_LOG"), ""));
        params.put("OFF_BOOK_LOG", CommonUtil.isNull(request.getParameter("OFF_BOOK_LOG"), ""));
		params.put("YPLAN", CommonUtil.isNull(request.getParameter("YPLAN"), ""));
		params.put("OFF_YPLAN", CommonUtil.isNull(request.getParameter("OFF_YPLAN"), ""));
		params.put("LECINFO", CommonUtil.isNull(request.getParameter("LECINFO"), ""));
		params.put("OFF_LECINFO", CommonUtil.isNull(request.getParameter("OFF_LECINFO"), ""));
        params.put("PROF_HTML", CommonUtil.isNull(request.getParameter("PROF_HTML"), ""));
        params.put("OFF_PROF_HTML", CommonUtil.isNull(request.getParameter("OFF_PROF_HTML"), ""));
        params.put("ON_OPENYN", CommonUtil.isNull(request.getParameter("ON_OPENYN"), "Y"));
        params.put("OFF_OPENYN", CommonUtil.isNull(request.getParameter("OFF_OPENYN"), "Y"));

		params.put("PIC1_DEL", CommonUtil.isNull(request.getParameter("PIC1_DEL"), ""));
		params.put("PIC2_DEL", CommonUtil.isNull(request.getParameter("PIC2_DEL"), ""));
		params.put("PIC3_DEL", CommonUtil.isNull(request.getParameter("PIC3_DEL"), ""));
		params.put("PIC4_DEL", CommonUtil.isNull(request.getParameter("PIC4_DEL"), ""));
        params.put("PIC5_DEL", CommonUtil.isNull(request.getParameter("PIC5_DEL"), ""));
        params.put("PIC6_DEL", CommonUtil.isNull(request.getParameter("PIC6_DEL"), ""));
        params.put("PIC7_DEL", CommonUtil.isNull(request.getParameter("PIC7_DEL"), ""));
        params.put("PIC8_DEL", CommonUtil.isNull(request.getParameter("PIC8_DEL"), ""));
        params.put("PIC9_DEL", CommonUtil.isNull(request.getParameter("PIC9_DEL"), ""));
        params.put("PIC10_DEL", CommonUtil.isNull(request.getParameter("PIC10_DEL"), ""));
		params.put("PIC1_DELNM", CommonUtil.isNull(request.getParameter("PIC1_DELNM"), ""));
		params.put("PIC2_DELNM", CommonUtil.isNull(request.getParameter("PIC2_DELNM"), ""));
		params.put("PIC3_DELNM", CommonUtil.isNull(request.getParameter("PIC3_DELNM"), ""));
		params.put("PIC4_DELNM", CommonUtil.isNull(request.getParameter("PIC4_DELNM"), ""));
        params.put("PIC5_DELNM", CommonUtil.isNull(request.getParameter("PIC5_DELNM"), ""));
        params.put("PIC6_DELNM", CommonUtil.isNull(request.getParameter("PIC6_DELNM"), ""));
        params.put("PIC7_DELNM", CommonUtil.isNull(request.getParameter("PIC7_DELNM"), ""));
        params.put("PIC8_DELNM", CommonUtil.isNull(request.getParameter("PIC8_DELNM"), ""));
        params.put("PIC9_DELNM", CommonUtil.isNull(request.getParameter("PIC9_DELNM"), ""));
        params.put("PIC10_DELNM", CommonUtil.isNull(request.getParameter("PIC10_DELNM"), ""));
		params.put("PIC1", "");
		params.put("PIC2", "");
		params.put("PIC3", "");
		params.put("PIC4", "");
        params.put("PIC5", "");
        params.put("PIC6", "");
        params.put("PIC7", "");
        params.put("PIC8", "");
        params.put("PIC9", "");
        params.put("PIC10", "");

		params.put("OFF_PAYMENT", CommonUtil.isNull(request.getParameter("OFF_PAYMENT"), ""));
		params.put("OFF_PIC1_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC1_DEL"), ""));
		params.put("OFF_PIC2_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC2_DEL"), ""));
		params.put("OFF_PIC3_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC3_DEL"), ""));
		params.put("OFF_PIC4_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC4_DEL"), ""));
		params.put("OFF_PIC5_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC5_DEL"), ""));
        params.put("OFF_PIC6_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC6_DEL"), ""));
        params.put("OFF_PIC7_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC7_DEL"), ""));
        params.put("OFF_PIC8_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC8_DEL"), ""));
        params.put("OFF_PIC9_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC9_DEL"), ""));
        params.put("OFF_PIC10_DEL", CommonUtil.isNull(request.getParameter("OFF_PIC10_DEL"), ""));
		params.put("OFF_PIC1_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC1_DELNM"), ""));
		params.put("OFF_PIC2_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC2_DELNM"), ""));
		params.put("OFF_PIC3_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC3_DELNM"), ""));
		params.put("OFF_PIC4_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC4_DELNM"), ""));
		params.put("OFF_PIC5_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC5_DELNM"), ""));
        params.put("OFF_PIC6_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC6_DELNM"), ""));
        params.put("OFF_PIC7_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC7_DELNM"), ""));
        params.put("OFF_PIC8_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC8_DELNM"), ""));
        params.put("OFF_PIC9_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC9_DELNM"), ""));
        params.put("OFF_PIC10_DELNM", CommonUtil.isNull(request.getParameter("OFF_PIC10_DELNM"), ""));

        params.put("TeacherOnOff_YN", CommonUtil.isNull(request.getParameter("TeacherOnOff_YN"), ""));
		params.put("PRF_ONPIC1_DELNM", CommonUtil.isNull(request.getParameter("PRF_ONPIC1_DELNM"), ""));
		params.put("PRF_ONPIC2_DELNM", CommonUtil.isNull(request.getParameter("PRF_ONPIC2_DELNM"), ""));
		params.put("PRF_ONPIC3_DELNM", CommonUtil.isNull(request.getParameter("PRF_ONPIC3_DELNM"), ""));
		params.put("PRF_OFFPIC1_DELNM", CommonUtil.isNull(request.getParameter("PRF_OFFPIC1_DELNM"), ""));
		params.put("PRF_OFFPIC2_DELNM", CommonUtil.isNull(request.getParameter("PRF_OFFPIC2_DELNM"), ""));
		params.put("PRF_OFFPIC3_DELNM", CommonUtil.isNull(request.getParameter("PRF_OFFPIC3_DELNM"), ""));
		params.put("PRF_LISTONBANNER_DEL", CommonUtil.isNull(request.getParameter("PRF_LISTONBANNER_DEL"), ""));
		params.put("PRF_LISTOFFBANNER_DEL", CommonUtil.isNull(request.getParameter("PRF_LISTOFFBANNER_DEL"), ""));
		params.put("PRF_LISTONBANNER_DELNM", CommonUtil.isNull(request.getParameter("PRF_LISTONBANNER_DELNM"), ""));
		params.put("PRF_LISTOFFBANNER_DELNM", CommonUtil.isNull(request.getParameter("PRF_LISTOFFBANNER_DELNM"), ""));
		params.put("PROF_IMG_DEL", CommonUtil.isNull(request.getParameter("PROF_IMG_DEL"), ""));
		params.put("OFF_PROF_IMG_DEL", CommonUtil.isNull(request.getParameter("OFF_PROF_IMG_DEL"), ""));
		params.put("PRF_TOPONIMG_DEL", CommonUtil.isNull(request.getParameter("PRF_TOPONIMG_DEL"), ""));
		params.put("PRF_TOPOFFIMG_DEL", CommonUtil.isNull(request.getParameter("PRF_TOPOFFIMG_DEL"), ""));
		params.put("OFF_PIC1", "");
		params.put("OFF_PIC2", "");
		params.put("OFF_PIC3", "");
		params.put("OFF_PIC4", "");
		params.put("OFF_PIC5", "");
        params.put("OFF_PIC6", "");
        params.put("OFF_PIC7", "");
        params.put("OFF_PIC8", "");
        params.put("OFF_PIC9", "");
        params.put("OFF_PIC10", "");


		params.put("PRF_ONPIC1", "");
		params.put("PRF_ONPIC2", "");
		params.put("PRF_ONPIC3", "");
		params.put("PRF_OFFPIC1", "");
		params.put("PRF_OFFPIC2", "");
		params.put("PRF_OFFPIC3", "");
		params.put("PRF_LISTONBANNER", "");
		params.put("PRF_LISTOFFBANNER", "");
		params.put("PROF_IMG", "");
		params.put("OFF_PROF_IMG", "");
		params.put("PRF_TOPONIMG", "");
		params.put("PRF_TOPOFFIMG", "");

        params.put("PIC7_TURL", CommonUtil.isNull(request.getParameter("PIC7_TURL"), ""));
        params.put("OFF_PIC7_TURL", CommonUtil.isNull(request.getParameter("OFF_PIC7_TURL"), ""));
        params.put("PIC8_TURL", CommonUtil.isNull(request.getParameter("PIC8_TURL"), ""));
        params.put("OFF_PIC8_TURL", CommonUtil.isNull(request.getParameter("OFF_PIC8_TURL"), ""));
        params.put("PIC9_TURL", CommonUtil.isNull(request.getParameter("PIC9_TURL"), ""));
        params.put("OFF_PIC9_TURL", CommonUtil.isNull(request.getParameter("OFF_PIC9_TURL"), ""));
        params.put("PIC10_TURL", CommonUtil.isNull(request.getParameter("PIC10_TURL"), ""));
        params.put("OFF_PIC10_TURL", CommonUtil.isNull(request.getParameter("OFF_PIC10_TURL"), ""));

        params.put("PIC6X", CommonUtil.isNull(request.getParameter("PIC6X"), ""));
        params.put("PIC6Y", CommonUtil.isNull(request.getParameter("PIC6Y"), ""));
        params.put("OFF_PIC6X", CommonUtil.isNull(request.getParameter("OFF_PIC6X"), ""));
        params.put("OFF_PIC6Y", CommonUtil.isNull(request.getParameter("OFF_PIC6Y"), ""));

        params.put("PROFILE_SUMMARY", CommonUtil.isNull(request.getParameter("PROFILE_SUMMARY"), ""));
        params.put("BOOK_LOG_SUMMARY", CommonUtil.isNull(request.getParameter("BOOK_LOG_SUMMARY"), ""));

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

	/**
	 * @Method Name : lecFileProcess
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파일처리 프로세스
	 * @param params
	 * @param request
	 * @return HashMap
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap<String, String> lecFileProcess(HashMap params, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = fsResource.getPath();
		String subPath = "member_upload/";

		MultipartFile PIC1 = multipartRequest.getFile("PIC1");
		MultipartFile PIC2 = multipartRequest.getFile("PIC2");
		MultipartFile PIC3 = multipartRequest.getFile("PIC3");
		MultipartFile PIC4 = multipartRequest.getFile("PIC4");
        MultipartFile PIC5 = multipartRequest.getFile("PIC5");
        MultipartFile PIC6 = multipartRequest.getFile("PIC6");
        MultipartFile PIC7 = multipartRequest.getFile("PIC7");
        MultipartFile PIC8 = multipartRequest.getFile("PIC8");
        MultipartFile PIC9 = multipartRequest.getFile("PIC9");
        MultipartFile PIC10 = multipartRequest.getFile("PIC10");
		MultipartFile OFF_PIC1 = multipartRequest.getFile("OFF_PIC1");
		MultipartFile OFF_PIC2 = multipartRequest.getFile("OFF_PIC2");
		MultipartFile OFF_PIC3 = multipartRequest.getFile("OFF_PIC3");
		MultipartFile OFF_PIC4 = multipartRequest.getFile("OFF_PIC4");
		MultipartFile OFF_PIC5 = multipartRequest.getFile("OFF_PIC5");
        MultipartFile OFF_PIC6 = multipartRequest.getFile("OFF_PIC6");
        MultipartFile OFF_PIC7 = multipartRequest.getFile("OFF_PIC7");
        MultipartFile OFF_PIC8 = multipartRequest.getFile("OFF_PIC8");
        MultipartFile OFF_PIC9 = multipartRequest.getFile("OFF_PIC9");
        MultipartFile OFF_PIC10 = multipartRequest.getFile("OFF_PIC10");

	    MultipartFile PRF_ONPIC1 = multipartRequest.getFile("PRF_ONPIC1");
		MultipartFile PRF_ONPIC2 = multipartRequest.getFile("PRF_ONPIC2");
		MultipartFile PRF_ONPIC3 = multipartRequest.getFile("PRF_ONPIC3");
		MultipartFile PRF_OFFPIC1 = multipartRequest.getFile("PRF_OFFPIC1");
		MultipartFile PRF_OFFPIC2 = multipartRequest.getFile("PRF_OFFPIC2");
		MultipartFile PRF_OFFPIC3 = multipartRequest.getFile("PRF_OFFPIC3");

		MultipartFile PRF_LISTONBANNER = multipartRequest.getFile("PRF_LISTONBANNER");
		MultipartFile PRF_LISTOFFBANNER = multipartRequest.getFile("PRF_LISTOFFBANNER");
		
		MultipartFile PROF_IMG = multipartRequest.getFile("PROF_IMG");
		MultipartFile OFF_PROF_IMG = multipartRequest.getFile("OFF_PROF_IMG");

		MultipartFile PRF_TOPONIMG = multipartRequest.getFile("PRF_TOPONIMG");
		MultipartFile PRF_TOPOFFIMG = multipartRequest.getFile("PRF_TOPOFFIMG");

		if(PIC1 != null && PIC1.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(PIC1, rootPath, subPath);
			params.put("PIC1", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PIC2 != null && PIC2.isEmpty() == false) {
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(PIC2, rootPath, subPath);
			params.put("PIC2", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PIC3 != null && PIC3.isEmpty() == false) {
			HashMap<String, Object> fileMap3 = fileUtil.uploadFile(PIC3, rootPath, subPath);
			params.put("PIC3", fileMap3.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PIC4 != null && PIC4.isEmpty() == false) {
			HashMap<String, Object> fileMap4 = fileUtil.uploadFile(PIC4, rootPath, subPath);
			params.put("PIC4", fileMap4.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PIC1 != null && OFF_PIC1.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(OFF_PIC1, rootPath, subPath);
			params.put("OFF_PIC1", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PIC2 != null && OFF_PIC2.isEmpty() == false) {
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(OFF_PIC2, rootPath, subPath);
			params.put("OFF_PIC2", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PIC3 != null && OFF_PIC3.isEmpty() == false) {
			HashMap<String, Object> fileMap3 = fileUtil.uploadFile(OFF_PIC3, rootPath, subPath);
			params.put("OFF_PIC3", fileMap3.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PIC4 != null && OFF_PIC4.isEmpty() == false) {
			HashMap<String, Object> fileMap4 = fileUtil.uploadFile(OFF_PIC4, rootPath, subPath);
			params.put("OFF_PIC4", fileMap4.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PIC5 != null && OFF_PIC5.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(OFF_PIC5, rootPath, subPath);
			params.put("OFF_PIC5", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}

        if(PIC5 != null && PIC5.isEmpty() == false) {
            HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PIC5, rootPath, subPath);
            params.put("PIC5", fileMap5.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(PIC6 != null && PIC6.isEmpty() == false) {
        	HashMap<String, Object> fileMap6 = fileUtil.uploadFile(PIC6, rootPath, subPath);
            params.put("PIC6", fileMap6.get("fileFullPath").toString());
            Thread.sleep(100);
        }

        if(OFF_PIC6 != null && OFF_PIC6.isEmpty() == false) {
        	HashMap<String, Object> fileMap6 = fileUtil.uploadFile(OFF_PIC6, rootPath, subPath);
            params.put("OFF_PIC6", fileMap6.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(PIC7 != null && PIC7.isEmpty() == false) {
            HashMap<String, Object> fileMap7 = fileUtil.uploadFile(PIC7, rootPath, subPath);
            params.put("PIC7", fileMap7.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(OFF_PIC7 != null && OFF_PIC7.isEmpty() == false) {
            HashMap<String, Object> fileMap7 = fileUtil.uploadFile(OFF_PIC7, rootPath, subPath);
            params.put("OFF_PIC7", fileMap7.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(PIC8 != null && PIC8.isEmpty() == false) {
            HashMap<String, Object> fileMap8 = fileUtil.uploadFile(PIC8, rootPath, subPath);
            params.put("PIC8", fileMap8.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(OFF_PIC8 != null && OFF_PIC8.isEmpty() == false) {
            HashMap<String, Object> fileMap8 = fileUtil.uploadFile(OFF_PIC8, rootPath, subPath);
            params.put("OFF_PIC8", fileMap8.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(PIC9 != null && PIC9.isEmpty() == false) {
            HashMap<String, Object> fileMap9 = fileUtil.uploadFile(PIC9, rootPath, subPath);
            params.put("PIC9", fileMap9.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(OFF_PIC9 != null && OFF_PIC9.isEmpty() == false) {
            HashMap<String, Object> fileMap9 = fileUtil.uploadFile(OFF_PIC9, rootPath, subPath);
            params.put("OFF_PIC9", fileMap9.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(PIC10 != null && PIC10.isEmpty() == false) {
            HashMap<String, Object> fileMap10 = fileUtil.uploadFile(PIC10, rootPath, subPath);
            params.put("PIC10", fileMap10.get("fileFullPath").toString());
            Thread.sleep(100);
        }
        if(OFF_PIC10 != null && OFF_PIC10.isEmpty() == false) {
            HashMap<String, Object> fileMap10 = fileUtil.uploadFile(OFF_PIC10, rootPath, subPath);
            params.put("OFF_PIC10", fileMap10.get("fileFullPath").toString());
            Thread.sleep(100);
        }

		if(PRF_ONPIC1 != null && PRF_ONPIC1.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_ONPIC1, rootPath, subPath);
			params.put("PRF_ONPIC1", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}

		if(PRF_ONPIC2 != null && PRF_ONPIC2.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_ONPIC2, rootPath, subPath);
			params.put("PRF_ONPIC2", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}

		if(PRF_ONPIC3 != null && PRF_ONPIC3.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_ONPIC3, rootPath, subPath);
			params.put("PRF_ONPIC3", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PRF_OFFPIC1 != null && PRF_OFFPIC1.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_OFFPIC1, rootPath, subPath);
			params.put("PRF_OFFPIC1", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}

		if(PRF_OFFPIC2 != null && PRF_OFFPIC2.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_OFFPIC2, rootPath, subPath);
			params.put("PRF_OFFPIC2", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PRF_OFFPIC3 != null && PRF_OFFPIC3.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_OFFPIC3, rootPath, subPath);
			params.put("PRF_OFFPIC3", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}


		if(PRF_LISTONBANNER != null && PRF_LISTONBANNER.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_LISTONBANNER, rootPath, subPath);
			params.put("PRF_LISTONBANNER", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PRF_LISTOFFBANNER != null && PRF_LISTOFFBANNER.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_LISTOFFBANNER, rootPath, subPath);
			params.put("PRF_LISTOFFBANNER", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		
		if(PROF_IMG != null && PROF_IMG.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PROF_IMG, rootPath, subPath);
			params.put("PROF_IMG", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(OFF_PROF_IMG != null && OFF_PROF_IMG.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(OFF_PROF_IMG, rootPath, subPath);
			params.put("OFF_PROF_IMG", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		
		if(PRF_TOPONIMG != null && PRF_TOPONIMG.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_TOPONIMG, rootPath, subPath);
			params.put("PRF_TOPONIMG", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(PRF_TOPOFFIMG != null && PRF_TOPOFFIMG.isEmpty() == false) {
			HashMap<String, Object> fileMap5 = fileUtil.uploadFile(PRF_TOPOFFIMG, rootPath, subPath);
			params.put("PRF_TOPOFFIMG", fileMap5.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		return params;
	}

}
