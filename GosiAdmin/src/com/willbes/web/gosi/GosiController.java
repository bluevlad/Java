package com.willbes.web.gosi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.adminManagement.service.AdminManagementCodeService;
import com.willbes.web.gosi.service.GosiService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/gosi")
@Controller
public class GosiController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private GosiService gosiservice;
    
    @Autowired
    private AdminManagementCodeService codeService;
    
    @Resource(name="fileUtil")
    private FileUtil fileUtil;

    @Inject
    private FileSystemResource fsResource;

    /**
     * @Method Name : Gosi_sample_user_list
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 리스트
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_sample_user_list.do")
    public String Gosi_sample_user_list(ModelMap model, HttpServletRequest request) throws Exception {
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
        //List<HashMap<String, String>> kindlist = subjectservice.getKindList(params);
        List<HashMap<String, String>> list = gosiservice.sample_List(params);
        int listCount = gosiservice.sample_ListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        //model.addAttribute("kindlist", kindlist);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/gosi/list";
    }

    /**
     * @Method Name : Gosi_write
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 등록 페이지
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_write.do")
    public String Gosi_write(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        model.addAttribute("params", params);

        return "/gosi/write";
    }

    /**
     * @Method Name : Gosi_save
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 등록
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_save.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String Gosi_save(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";

        gosiservice.InsertSampleUser(params);
        Result = "Y";

        return Result;
    }

    /**
     * @Method Name : Gosi_modify
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 수정 페이지
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_modify.do")
    public String Gosi_modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);


        params.put("VIW_RST_NO", CommonUtil.isNull(request.getParameter("VIW_RST_NO"), ""));
        List<HashMap<String, String>> list = gosiservice.SampleIdView(params);

        model.addAttribute("list", list);
        model.addAttribute("params", params);
        return "/gosi/modify";
    }

    /**
     * @Method Name : Gosi_update
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_update.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String Gosi_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";

        gosiservice.SampleIdUpdate(params);
        Result = "Y";

        return Result;
    }

    /**
     * @Method Name : Gosi_delete
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 삭제
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_delete.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String Gosi_delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";

        gosiservice.SampleIdDelete(params);

        return Result;
    }

    /**
     * @Method Name : Gosi_listDelete
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 샘플아이디 리스트삭제
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_listDelete.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String Gosi_listDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");

        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                params.put("RST_NO", DEL_ARR[i]);
                gosiservice.SampleIdDelete(params);
            }
        }
        return "redirect:/gosi/Gosi_sample_user_list.do";
    }

    /**
     * @Method Name : GosiAreaMst_modify
     * @작성일 : 2015. 04.
     * @Method 설명 : 국가고시 예상 합격선 수정 화면
     */
    @RequestMapping(value="/GosiAreaMst_modify.do")
    public String GosiAreaMsti_modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        
        params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_GOSI_MST"));
        List<HashMap<String, String>> list = gosiservice.getGosiAreaMst(params);

        params.put("GOSI_TYPE", CommonUtil.isNull(request.getParameter("GOSI_TYPE"), "R"));
        model.addAttribute("gosi_list", gosiservice.GosiList(params));

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/gosi/GosiAreaMst_modify";
    }

    /**
     * @Method Name : GosiAreaMst_update
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 예상 합격선 수정
     */
    @RequestMapping(value="/GosiAreaMst_update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiAreaMst_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] V_GOSI_GUBUN = request.getParameterValues("GOSI_GUBUN");
		
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), ""));

		
        if(V_GOSI_GUBUN != null){
            for(int i=0; i<V_GOSI_GUBUN.length; i++){
                params.put("ISUSE", request.getParameter("ISUSE_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_TYPE", request.getParameter("GOSI_TYPE_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_AREA", request.getParameter("GOSI_AREA_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_AREA_FULL_NM", request.getParameter("GOSI_AREA_FULL_NM_"+V_GOSI_GUBUN[i]));
                params.put("REQ_NUM", request.getParameter("REQ_NUM_"+V_GOSI_GUBUN[i]));
                params.put("USE_NUM", request.getParameter("USE_NUM_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_CMP_STAT", request.getParameter("GOSI_CMP_STAT_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_CMP_STAT_2016", request.getParameter("GOSI_CMP_STAT_2016_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2016", request.getParameter("PASS_2016_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2017_S", request.getParameter("PASS_2017_S_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2017_E", request.getParameter("PASS_2017_E_"+V_GOSI_GUBUN[i]));

                gosiservice.updateGosiAreaMst(params);
            }
        }

        return "forward:/gosi/GosiAreaMst_modify.do";
    }

    /**
     * @Method Name : vod_modify
     * @작성일 : 2015. 04.
     * @Method 설명 : 해설강의 수정폼
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/vod_modify.do")
    public String vod_modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "AAA"));

        List<HashMap<String, String>> list = gosiservice.getVodSubject(params);

		List<HashMap<String, String>> gosi_list = gosiservice.GosiList(params);

		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("GOSI_CD", params.get("GOSI_CD"));
        model.addAttribute("list", list);
		model.addAttribute("gosi_list", gosi_list);
		
        return "/gosi/vod_modify";
    }

    /**
     * @Method Name : vod_update
     * @작성일 : 2015. 04.
     * @Method 설명 : 해설강의 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/vod_update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String vod_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] V_PRF_ID = request.getParameterValues("PRF_ID");
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "AAA"));
        if(V_PRF_ID != null){
            for(int i=0; i<V_PRF_ID.length; i++){
                params.put("PRF_ID", V_PRF_ID[i]);
                params.put("TITLE", request.getParameter("TITLE_"+V_PRF_ID[i]+"_"+i));
                params.put("VOD_URL", request.getParameter("VOD_URL_"+V_PRF_ID[i]+"_"+i));
                params.put("FILE_URL", request.getParameter("FILE_URL_"+V_PRF_ID[i]+"_"+i));
                params.put("ISUSE", request.getParameter("ISUSE_"+V_PRF_ID[i]+"_"+i));
                params.put("IDX", request.getParameter("IDX_"+V_PRF_ID[i]+"_"+i));
                params.put("T_NO", request.getParameter("T_NO_"+V_PRF_ID[i]+"_"+i));

                gosiservice.updateGosiVod(params);
            }
        }

        return "forward:/gosi/vod_modify.do";
    }

    /**
     * @Method Name : GosiStatView
     * @작성일 : 2015. 04.
     * @Method 설명 : 국가고시 분석 화면
     */
    @RequestMapping(value="/GosiStatView.do")
    public String GosiStatView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
		
        params.put("GOSI_TYPE", CommonUtil.isNull(request.getParameter("GOSI_TYPE"), "R"));
        model.addAttribute("gosi_list", gosiservice.GosiList(params));

        params.put("GOSI_CD", "2017_GOSI_MST");
        List<HashMap<String, String>> list = gosiservice.getGosiStatMst(params);
        if (list.size() == 0) {
            params.put("STAT_NO", "Y");
            list = gosiservice.getGosiStatMst(params);
        }

        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/gosi/GosiStatView";
    }

    /**
     * @Method Name : GosiMakeResult
     * @작성일 : 2016. 04.
     * @Method 설명 : 개인별 성적 통계 산출
     */
    @RequestMapping(value="/GosiMakeResult.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeResult(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiResult(params);

        return "Y";
    }

    /**
     * @Method Name : GosiMakeAdjust
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 분석 - 조정점수 반영
     */
    @RequestMapping(value="/GosiMakeAdjust.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeAdjust(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiAdjustMst(params);

        return "Y";
    }

    /**
     * @Method Name : GosiMakeStat
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 분석 수정
     */
    @RequestMapping(value="/GosiMakeStat.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeStat(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiStatMst(params);

        return "Y";
    }
    
    /**
     * @Method Name : Gosi_event3_list
     * @작성일 : 2016. 10. (2017. 03)
     * @Method 설명 : 인증이벤트 - 2017인증이벤트 추가
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Gosi_event_list.do")
    public String Gosi_event_list(ModelMap model, HttpServletRequest request) throws Exception {
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

		HashMap<String, Object> oparam = new  HashMap<String, Object>();
		oparam.put("SYS_CD", "FILE_EVENT");
		List<HashMap<String, Object>> eventList = codeService.getBaConfigCodeList(oparam);
        
		if ("".equals(params.get("EVENT_NO"))){
			HashMap<String, Object> eventView = eventList.get(0);
			params.put("EVENT_NO", eventView.get("CODE_VAL").toString());
		}

        List<HashMap<String, String>> list = gosiservice.Event_List(params);
        int listCount = gosiservice.Event_ListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("eventList", eventList);
        return "/gosi/gosi_event";
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
    @RequestMapping(value="/gosi_event_excel.do")
    public View gosi_event_excel(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);
        
        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        // 회원 리스트
        List<HashMap<String, String>> list = gosiservice.Event_List(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_인증표명단";
        List<String> headerList = new ArrayList<String>();
        headerList.add("아이디");
        headerList.add("이름");
        headerList.add("응시직렬");
        headerList.add("응시번호");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID"))) {
                newMap.put(i++, map.get("USER_ID").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("USER_NM") && !"".equals(map.get("USER_NM"))) {
                newMap.put(i++, map.get("USER_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ARM_NM") && !"".equals(map.get("ARM_NM"))) {
                newMap.put(i++, map.get("ARM_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("ARM_NO") && !"".equals(map.get("ARM_NO"))) {
                newMap.put(i++, map.get("ARM_NO").toString()+"");
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("REG_DD") && !"".equals(map.get("REG_DD"))) {
                newMap.put(i++, map.get("REG_DD").toString());
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
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTYPE2", CommonUtil.isNull(request.getParameter("SEARCHTYPE2"), ""));
        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCH_YEAR", CommonUtil.isNull(request.getParameter("SEARCH_YEAR"), ""));
        params.put("SEARCH_GUBUN", CommonUtil.isNull(request.getParameter("SEARCH_GUBUN"), "T"));

        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
        params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        params.put("AREA_01", CommonUtil.isNull(request.getParameter("AREA_01"), ""));
        params.put("AREA_02", CommonUtil.isNull(request.getParameter("AREA_02"), ""));
        params.put("RST_NO", CommonUtil.isNull(request.getParameter("RST_NO"), ""));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("USER_AGE", CommonUtil.isNull(request.getParameter("USER_AGE"), ""));
        params.put("STUDY_WAIT", CommonUtil.isNull(request.getParameter("STUDY_WAIT"), ""));
        params.put("STUDY_TYPE", CommonUtil.isNull(request.getParameter("STUDY_TYPE"), ""));
        params.put("ADD_POINT", CommonUtil.isNull(request.getParameter("ADD_POINT"), ""));
        params.put("EXAM_STAT", CommonUtil.isNull(request.getParameter("EXAM_STAT"), ""));
        params.put("SEL_SBJ_01", CommonUtil.isNull(request.getParameter("SEL_SBJ_01"), ""));
        params.put("SEL_SBJ_02", CommonUtil.isNull(request.getParameter("SEL_SBJ_02"), ""));
        params.put("SBJ_01", CommonUtil.isNull(request.getParameter("SBJ_01"), ""));
        params.put("SBJ_MO_01", CommonUtil.isNull(request.getParameter("SBJ_MO_01"), ""));
        params.put("SBJ_02", CommonUtil.isNull(request.getParameter("SBJ_02"), ""));
        params.put("SBJ_MO_02", CommonUtil.isNull(request.getParameter("SBJ_MO_02"), ""));
        params.put("SBJ_03", CommonUtil.isNull(request.getParameter("SBJ_03"), ""));
        params.put("SBJ_MO_03", CommonUtil.isNull(request.getParameter("SBJ_MO_03"), ""));
        params.put("SBJ_04", CommonUtil.isNull(request.getParameter("SBJ_04"), ""));
        params.put("SBJ_MO_04", CommonUtil.isNull(request.getParameter("SBJ_MO_04"), ""));
        params.put("SBJ_05", CommonUtil.isNull(request.getParameter("SBJ_05"), ""));
        params.put("SBJ_MO_05", CommonUtil.isNull(request.getParameter("SBJ_MO_05"), ""));
        params.put("USER_SEX", CommonUtil.isNull(request.getParameter("USER_SEX"), ""));

        params.put("IDX", CommonUtil.isNull(request.getParameter("IDX"), ""));
        params.put("NM", CommonUtil.isNull(request.getParameter("NM"), ""));
        params.put("P_IDX", CommonUtil.isNull(request.getParameter("P_IDX"), ""));
        params.put("LVL", CommonUtil.isNull(request.getParameter("LVL"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));


        params.put("S_YEAR", CommonUtil.isNull(request.getParameter("S_YEAR"), ""));
        params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), ""));
        params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
        params.put("CONT", CommonUtil.isNull(request.getParameter("CONT"), ""));

        params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
		
		params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("searchEventNo"), ""));

    }

}
