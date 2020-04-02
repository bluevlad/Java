package web.gosi;

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
import org.springframework.web.servlet.View;

import egovframework.rte.fdl.property.EgovPropertyService;
import web.adminManagement.service.AdminManagementCodeService;
import web.gosi.service.GosiService;
import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.file.FileUtil;
import web.util.paging.Paging;

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
    
    @RequestMapping(value="/Gosi_event1_list.do")
    public String Gosi_event1_list(ModelMap model, HttpServletRequest request) throws Exception {
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
        List<HashMap<String, Object>> list = null;
        int listCount = 0;
    	list = gosiservice.Event1_List(params);
    	listCount = gosiservice.Event1_ListCount(params);
        
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/gosi/event1";
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
    @RequestMapping(value="/Gosi_event3_list.do")
    public String Gosi_event3_list(ModelMap model, HttpServletRequest request) throws Exception {
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

        List<HashMap<String, Object>> list = gosiservice.Event3_List(params);
        int listCount = gosiservice.Event3_ListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("eventList", eventList);
        return "/gosi/event3";
    }
    
    @RequestMapping(value="/event1.pop")
    public String Gosi_event1_pop(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"), ""));
        model.addAttribute("params", params);
        return "/gosi/event1_pop";
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

        String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        // 회원 리스트
        List<HashMap<String, Object>> list = gosiservice.Event1_List(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_인증표명단";
        List<String> headerList = new ArrayList<String>();
        headerList.add("아이디");
        headerList.add("이름");
        headerList.add("시험직렬");
        headerList.add("지역구분");
        headerList.add("응시번호");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, Object> map : list) {
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
            if(null != map.get("GOSI_AREA_FULL_NM") && !"".equals(map.get("GOSI_AREA_FULL_NM"))) {
                newMap.put(i++, map.get("GOSI_AREA_FULL_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("GOSI_AREA_NM") && !"".equals(map.get("GOSI_AREA_NM"))) {
                newMap.put(i++, map.get("GOSI_AREA_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("RST_NO") && !"".equals(map.get("RST_NO"))) {
                newMap.put(i++, map.get("RST_NO").toString()+"");
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
     * @Method Name : excelDownload
     * @작성일 : 2015. 06.
     * @Method 설명 : 회원 명단
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/excel3.do")
    public View excelDownload3(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);
        
        params.put("startNo", "0");
        params.put("endNo", propertiesService.getInt("maxUnitSize")+"");

        String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        // 회원 리스트
        List<HashMap<String, Object>> list = gosiservice.Event3_List(params);

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String excelName = simpleDate.format(date) + "_인증표명단";
        List<String> headerList = new ArrayList<String>();
        headerList.add("아이디");
        headerList.add("이름");
        headerList.add("시험직렬");
        headerList.add("지역구분");
        headerList.add("응시번호");
        headerList.add("등록일");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, Object> map : list) {
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
            if(null != map.get("GOSI_AREA_FULL_NM") && !"".equals(map.get("GOSI_AREA_FULL_NM"))) {
                newMap.put(i++, map.get("GOSI_AREA_FULL_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("GOSI_AREA_NM") && !"".equals(map.get("GOSI_AREA_NM"))) {
                newMap.put(i++, map.get("GOSI_AREA_NM").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("RST_NO") && !"".equals(map.get("RST_NO"))) {
                newMap.put(i++, map.get("RST_NO").toString()+"");
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
    
    @RequestMapping(value="/Gosi_event2_list.do")
    public String Gosi_event2_list(ModelMap model, HttpServletRequest request) throws Exception {
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
        
        List<HashMap<String, String>> list = gosiservice.Event2_List(params);
        int listCount = gosiservice.Event2_ListCount(params);
       // List<HashMap<String, String>> list = gosiservice.sample_List(params);
        //int listCount = gosiservice.sample_ListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        //model.addAttribute("kindlist", kindlist);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        return "/gosi/event2";
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
        
        //전체 시험 정보
        List<HashMap<String, String>> gosi_list = gosiservice.GosiList(params);
  		model.addAttribute("gosi_list", gosi_list);
  		//선택된 시험 정보
        params.put("SCH_GOSI_CD", params.get("GOSI_CD"));
        List<HashMap<String, String>> gosi_info = gosiservice.GosiList(params);
        model.addAttribute("gosi_info",gosi_info.get(0));
        
        List<HashMap<String, String>> list = gosiservice.getGosiAreaMst(params);
        
        List<HashMap<String, String>> analist = gosiservice.getTestArea(params);
        
        model.addAttribute("list", list);
        model.addAttribute("analist", analist);
        model.addAttribute("params", params);
  		
        if("2016_MST".equals(params.get("GOSI_CD"))){
        	return "/gosi/GosiAreaMst_modify_2016";
        }else{
        	return "/gosi/GosiAreaMst_modify";
        }
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
		
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));

		
        if(V_GOSI_GUBUN != null){
            for(int i=0; i<V_GOSI_GUBUN.length; i++){
                params.put("ISUSE", request.getParameter("ISUSE_"+V_GOSI_GUBUN[i]));
                params.put("SHOW_STAT", request.getParameter("SHOW_STAT_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_TYPE", request.getParameter("GOSI_TYPE_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_AREA", request.getParameter("GOSI_AREA_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_AREA_FULL_NM", request.getParameter("GOSI_AREA_FULL_NM_"+V_GOSI_GUBUN[i]));
                params.put("REQ_NUM", request.getParameter("REQ_NUM_"+V_GOSI_GUBUN[i]));
                params.put("USE_NUM", request.getParameter("USE_NUM_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_CMP_STAT", request.getParameter("GOSI_CMP_STAT_"+V_GOSI_GUBUN[i]));
                params.put("GOSI_CMP_STAT_2015", request.getParameter("GOSI_CMP_STAT_2015_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2015", request.getParameter("PASS_2015_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2016_S", request.getParameter("PASS_2016_S_"+V_GOSI_GUBUN[i]));
                params.put("PASS_2016_E", request.getParameter("PASS_2016_E_"+V_GOSI_GUBUN[i]));

                gosiservice.updateGosiAreaMst(params);
            }
        }

        return "forward:/gosi/GosiAreaMst_modify.do";
    }
    
    @RequestMapping(value="/GosiAna_update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiAreaAna_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] CH_IS_SHOW = request.getParameterValues("CH_IS_SHOW");
        if(CH_IS_SHOW != null){
        	params.put("IS_SHOW", "N");
            gosiservice.updateGosiCod(params);
            for(int i=0; i<CH_IS_SHOW.length; i++){
            	String[] arr = CH_IS_SHOW[i].split("_");
            	params.put("IS_SHOW", arr[0]);
            	params.put("GOSI_TYPE", arr[1]);
                gosiservice.updateGosiCod(params);
            }
        }else{
        	params.put("IS_SHOW", "N");
            gosiservice.updateGosiCod(params);
        }
        
        
        String[] V_GOSI_GUBUN = request.getParameterValues("GOSI_GUBUN");
		
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));

		
        if(V_GOSI_GUBUN != null){
            for(int i=0; i<V_GOSI_GUBUN.length; i++){
                params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE_"+V_GOSI_GUBUN[i]), ""));
                params.put("SHOW_STAT", CommonUtil.isNull(request.getParameter("SHOW_STAT_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_TYPE", CommonUtil.isNull(request.getParameter("GOSI_TYPE_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_AREA", CommonUtil.isNull(request.getParameter("GOSI_AREA_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_AREA_FULL_NM", CommonUtil.isNull(request.getParameter("GOSI_AREA_FULL_NM_"+V_GOSI_GUBUN[i]), ""));
                params.put("REQ_NUM", CommonUtil.isNull(request.getParameter("REQ_NUM_"+V_GOSI_GUBUN[i]), ""));
                params.put("USE_NUM", CommonUtil.isNull(request.getParameter("USE_NUM_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_CMP_STAT", CommonUtil.isNull(request.getParameter("GOSI_CMP_STAT_"+V_GOSI_GUBUN[i]), ""));
                
                params.put("PASS_RANKING", CommonUtil.isNull(request.getParameter("PASS_RANKING_"+V_GOSI_GUBUN[i]), ""));
                params.put("AVR_POINT", CommonUtil.isNull(request.getParameter("AVR_POINT_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_5_POINT", CommonUtil.isNull(request.getParameter("GOSI_5_POINT_"+V_GOSI_GUBUN[i]), ""));
                params.put("PASS_1_POINT", CommonUtil.isNull(request.getParameter("PASS_1_POINT_"+V_GOSI_GUBUN[i]), ""));
                params.put("MUST_PASS", CommonUtil.isNull(request.getParameter("MUST_PASS_"+V_GOSI_GUBUN[i]), ""));
                params.put("CAN_PASS", CommonUtil.isNull(request.getParameter("CAN_PASS_"+V_GOSI_GUBUN[i]), ""));
                params.put("PASS_2016", CommonUtil.isNull(request.getParameter("PASS_2016_"+V_GOSI_GUBUN[i]), ""));
                params.put("GOSI_CMP_STAT_LAST", CommonUtil.isNull(request.getParameter("GOSI_CMP_STAT_LAST_"+V_GOSI_GUBUN[i]), ""));
                params.put("CAN_PASS_LAST", CommonUtil.isNull(request.getParameter("CAN_PASS_LAST_"+V_GOSI_GUBUN[i]), ""));
                params.put("PASS_1_9_POINT", CommonUtil.isNull(request.getParameter("PASS_1_9_POINT_"+V_GOSI_GUBUN[i]), ""));
                params.put("CAN_PASS_L", CommonUtil.isNull(request.getParameter("CAN_PASS_L_"+V_GOSI_GUBUN[i]), ""));
                params.put("CAN_PASS_H", CommonUtil.isNull(request.getParameter("CAN_PASS_H_"+V_GOSI_GUBUN[i]), ""));
                
                
				
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

        List<HashMap<String, String>> list = gosiservice.getVodSubject(params);

		List<HashMap<String, String>> gosi_list = gosiservice.GosiList(params);

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
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));
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
		
        List<HashMap<String, String>> list = gosiservice.getGosiStatMst(params);
        
        //전체 시험 정보
        List<HashMap<String, String>> gosi_list = gosiservice.GosiList(params);
  		model.addAttribute("gosi_list", gosi_list);
  		//선택된 시험 정보
        params.put("SCH_GOSI_CD", params.get("GOSI_CD"));
        List<HashMap<String, String>> gosi_info = gosiservice.GosiList(params);
        model.addAttribute("gosi_info",gosi_info.get(0));
        
        model.addAttribute("list", list);
        model.addAttribute("params", params);

        return "/gosi/GosiStatView";
    }
    
    /**
     * @Method Name : GosiMakeResult
     * @작성일 : 2016. 04.
     * @Method 설명 : 총참여자수 관리자 입력값
     */
    @RequestMapping(value="/GosiUpdate.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiUpdate(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        String START_DATE = CommonUtil.isNull(request.getParameter("START_DATE"), "");
        String START_TIME = CommonUtil.isNull(request.getParameter("START_TIME"), "");
        String START_MIN = CommonUtil.isNull(request.getParameter("START_MIN"), "");
        String DATE = START_DATE+START_TIME+START_MIN;
        params.put("START_DATE", DATE);
        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        params.put("GOSI_COUNT", request.getParameter("GOSI_COUNT"));
        gosiservice.GosiUpdate(params);
        model.addAttribute("msg", "오픈시작일과 총참여자수를 조정하였습니다.");

        return "forward:/gosi/GosiStatView.do";
    }

    
    /**
     * @Method Name : GosiMakeResult
     * @작성일 : 2016. 04.
     * @Method 설명 : 개인별 성적 통계 산출
     */
    @RequestMapping(value="/GosiMakeResult.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeResult(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiResult(params);
        model.addAttribute("msg", "시험점수 집계가 완료되었습니다.");

        return "forward:/gosi/GosiStatView.do";
    }

    /**
     * @Method Name : GosiMakeAdjust
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 분석 - 조정점수 반영
     */
    @RequestMapping(value="/GosiMakeAdjust.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeAdjust(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiAdjustMst(params);
        model.addAttribute("msg", "조정점수가 반영되었습니다.");

        return "forward:/gosi/GosiStatView.do";
    }

    /**
     * @Method Name : GosiMakeStat
     * @작성일 : 2013. 10.
     * @Method 설명 : 국가고시 분석 수정
     */
    @RequestMapping(value="/GosiMakeStat.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiMakeStat(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiStatMst(params);
        model.addAttribute("msg", "시험통계 산출이 완료되었습니다.");

        return "forward:/gosi/GosiStatView.do";
    }
    
    /**
     * @Method Name : makeGosiRank
     * @작성일 : 2013. 10.
     * @Method 설명 : 랭킹 통계
     */
    @RequestMapping(value="/makeGosiRank.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeGosiRank(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", request.getParameter("GOSI_CD"));
        gosiservice.makeGosiRank(params);
        model.addAttribute("msg", "랭킹통계 산출이 완료되었습니다.");

        return "forward:/gosi/GosiStatView.do";
    }
    
    /**
     * @Method Name : makeGosiPer
     * @작성일 : 2017. 03
     * @Method 설명 : 랭킹 통계
     */
    @RequestMapping(value="/makeGosiPer.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeGosiPer(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));
        gosiservice.makeGosiPer(params);
        model.addAttribute("msg", "분포도 업데이트 완료되었습니다.");

        return "redirect:/gosi/GosiPer_modify.do";
    }
    
    /**
     * @Method Name : GosiPer_modify
     * @작성일 : 2017. 03.
     * @Method 설명 : 총점, 과목별 분포도 수정
     */
    @RequestMapping(value="/GosiPer_modify.do")
    public String GosiPer_modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        
        params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));
        List<HashMap<String, String>> list1 = gosiservice.getGosiScorePer(params);
        
        List<HashMap<String, String>> list2 = gosiservice.getSubjectScorePer(params);
        
        model.addAttribute("list", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("params", params);
        
        //전체 시험 정보
        List<HashMap<String, String>> gosi_list = gosiservice.GosiList(params);
        model.addAttribute("gosi_list", gosi_list);
  		//선택된 시험 정보
        params.put("SCH_GOSI_CD", params.get("GOSI_CD"));
        List<HashMap<String, String>> gosi_info = gosiservice.GosiList(params);
        model.addAttribute("gosi_info",gosi_info.get(0));

        return "/gosi/GosiPer_modify";
    }
    
    /**
     * @Method Name : GosiAreaMst_update
     * @작성일 : 2017. 03.
     * @Method 설명 : 국가고시 예상 합격선 수정
     */
    @RequestMapping(value="/GosiPer_update.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String GosiPer_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] V_GOSI_SUBJECT = request.getParameterValues("GOSI_SUBJECT");
		
		params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));
		
		params.put("SCORE_1_PER", CommonUtil.isNull(request.getParameter("SCORE_1_PER"), "0"));
		params.put("SCORE_2_PER", CommonUtil.isNull(request.getParameter("SCORE_2_PER"), "0"));
		params.put("SCORE_3_PER", CommonUtil.isNull(request.getParameter("SCORE_3_PER"), "0"));
		params.put("SCORE_4_PER", CommonUtil.isNull(request.getParameter("SCORE_4_PER"), "0"));
		params.put("SCORE_5_PER", CommonUtil.isNull(request.getParameter("SCORE_5_PER"), "0"));
		gosiservice.updateGosiScorePer(params);
        if(V_GOSI_SUBJECT != null){
            for(int i=0; i<V_GOSI_SUBJECT.length; i++){
            	params.put("SUBJECT_CD", V_GOSI_SUBJECT[i]);
                params.put("SBJ_1_PER", request.getParameter("SBJ_1_PER_"+V_GOSI_SUBJECT[i]));
                params.put("SBJ_2_PER", request.getParameter("SBJ_2_PER_"+V_GOSI_SUBJECT[i]));
                params.put("SBJ_3_PER", request.getParameter("SBJ_3_PER_"+V_GOSI_SUBJECT[i]));
                params.put("SBJ_4_PER", request.getParameter("SBJ_4_PER_"+V_GOSI_SUBJECT[i]));
                params.put("SBJ_5_PER", request.getParameter("SBJ_5_PER_"+V_GOSI_SUBJECT[i]));

                gosiservice.updateSubjectScorePer(params);
            }
        }

        return "redirect:/gosi/GosiPer_modify.do";
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
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
        params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
        params.put("SEARCHGUBN", "T");

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
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("USER_SEX", CommonUtil.isNull(request.getParameter("USER_SEX"), ""));

        params.put("IDX", CommonUtil.isNull(request.getParameter("IDX"), ""));
        params.put("NM", CommonUtil.isNull(request.getParameter("NM"), ""));
        params.put("P_IDX", CommonUtil.isNull(request.getParameter("P_IDX"), ""));
        params.put("LVL", CommonUtil.isNull(request.getParameter("LVL"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));

        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));
        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));

        params.put("S_YEAR", CommonUtil.isNull(request.getParameter("S_YEAR"), ""));
        params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTYPE2", CommonUtil.isNull(request.getParameter("SEARCHTYPE2"), ""));
        params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
        params.put("CONT", CommonUtil.isNull(request.getParameter("CONT"), ""));

        params.put("SEARCH_YEAR", CommonUtil.isNull(request.getParameter("SEARCH_YEAR"), ""));
        params.put("SEARCH_GUBUN", CommonUtil.isNull(request.getParameter("SEARCH_GUBUN"), ""));
        params.put("SEARCH_TYPE", CommonUtil.isNull(request.getParameter("SEARCH_TYPE"), ""));
        params.put("SEARCH_TYPE2", CommonUtil.isNull(request.getParameter("SEARCH_TYPE2"), ""));
        
        params.put("GOSI_CD", CommonUtil.isNull(request.getParameter("GOSI_CD"), "2017_MST_2"));
        params.put("searchEventNo", CommonUtil.isNull(request.getParameter("searchEventNo"), "2"));
        params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
        params.put("GOSI_AREA_FULL_NM", CommonUtil.isNull(request.getParameter("GOSI_AREA_FULL_NM"), ""));
        
        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("searchEventNo"), ""));
        
        HashMap<String, String> chkGosiInfo = gosiservice.chkGosiInfo(params);
        if("Y".equals(chkGosiInfo.get("IS_USE"))){
        	 params.put("IS_OLD", "N");
        }else{
        	params.put("IS_OLD", "Y");
        }
    }

}
