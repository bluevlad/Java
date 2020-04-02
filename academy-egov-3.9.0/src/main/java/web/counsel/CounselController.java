package web.counsel;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.excel.ExcelDownloadView;
import web.util.paging.Paging;
import web.board.service.BoardService;
import web.counsel.service.CounselService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value = "/counsel")
@Controller
public class CounselController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Autowired
    private CounselService counselService;
    @Resource(name="boardServiceImpl")
    private BoardService boardService;

    /**
     * @Method Name : CounselList
     * @작성일 : 2015.01.05
     * @Method 설명 : 상담 운영 일정
     */
    @RequestMapping(value = "/list.do")
    public String CounselList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        String SDate = CommonUtil.isNull(request.getParameter("SDate"), "");
        String EDate = CommonUtil.isNull(request.getParameter("EDate"), "");
        if(SDate.equals("")){
            Calendar month6 = Calendar.getInstance();
            month6.add(Calendar.MONTH, -1);
            java.util.Date date6 = month6.getTime();
            String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);
            SDate = Delday6;
        }
        if(EDate.equals("")){
            Calendar month6 = Calendar.getInstance();
            month6.add(Calendar.MONTH, +1);
            java.util.Date date6 = month6.getTime();
            String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);
            EDate = Delday6;
        }
        params.put("SDate", SDate);
        params.put("EDate", EDate);

        String SEARCHCATEGORY = CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), "");
        List<String> slist = new ArrayList<String>();
        if(null != SEARCHCATEGORY && SEARCHCATEGORY.length() >1) {
            String[] cats = SEARCHCATEGORY.split(",");
            for(String cat : cats) {
                if(null != cat && cat.length()>0) {
                    slist.add(cat);
                }
            }
            params.put("CATE_LIST", slist);
        } else {
            params.put("CATE_LIST", null);
        }

        // 상담실 일정 리스트
        List<HashMap<String, String>> list = counselService.dayList(params);
        model.addAttribute("list", list);

        //동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
        List<HashMap<String, String>> rankList = boardService.getCategoryCode(params);
        model.addAttribute("rankList", rankList);

        model.addAttribute("params", params);

        return "counsel/list";
    }

    /**
     * @Method Name : boxOrderList
     * @작성일 : 2015.01.06
     * @Method 설명 : 상담실 예약 현황
     */
    @RequestMapping(value = "/view.do")
    public String boxOrderList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("SCH_DAY", CommonUtil.isNull(request.getParameter("SCH_DAY"), ""));
        model.addAttribute("list", counselService.counselList(params));
        model.addAttribute("rst", counselService.getCounselReqList(params));
        model.addAttribute("params", params);

        return "counsel/view";
    }

    /**
     * @Method Name : Schedule_add
     * @작성일 : 2015.01.07
     * @Method 설명 : 상담 일정 등록
     */
    @RequestMapping(value = "/Schedule_add.do")
    public String Schedule_add(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> time_table = counselService.getTimeTable(params);
        model.addAttribute("time_table", time_table);

        List<HashMap<String, String>> rankList = boardService.getCategoryCode(params);
        model.addAttribute("rankList", rankList);
        model.addAttribute("params", params);

        return "counsel/write";
    }

    /**
     * @Method Name : Schedule_Insert
     * @작성일 : 2013. 10.
     * @Method 설명 : 상담일정 등록 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Schedule_Insert.do")
    @ResponseBody
    public String Schedule_Insert(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        int count = 0;
        String CAT_CDs = CommonUtil.isNull(request.getParameter("CAT_CDs"), "");
        if(null != CAT_CDs && CAT_CDs.length() >1) {
            String[] cats = CAT_CDs.split(",");
            for(String cat : cats) {
                if(null != cat && cat.length()>0) {
                    params.put("CAT_CD", cat);
                    count += counselService.getScheduleCount(params);
                }
            }
        } else {
            count = counselService.getScheduleCount(params);
        }

        String result = "";
        if(count > 0){
            result = "schedule_over";
        }else{
            params.put("CAT_CDs", CAT_CDs);
            counselService.InsertSchedule(params);
        }

        return result;
    }

    /**
     * @Method Name : userlist
     * @작성일 : 2015.01.07
     * @Method 설명 : 상담 일정 수정
     */
    @RequestMapping(value = "/Schedule_update.do")
    public String Schedule_update(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        params.put("CAT_NM", CommonUtil.isNull(request.getParameter("CAT_NM"), ""));

        model.addAttribute("sch_table", counselService.getSchTable(params));
        model.addAttribute("params", params);

        return "counsel/update";
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 상담일정 수정 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Schedule_Modify.do")
    @ResponseBody
    public String Schedule_Modify(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);
        params.put("REQ_CNT", request.getParameterValues("REQ_CNT"));
        params.put("ISUSE", request.getParameterValues("ISUSE"));
        params.put("REQ_TYPE", request.getParameterValues("REQ_TYPE"));

        counselService.Sch_Modify(params);
        model.addAttribute("params", params);

        return "Y";
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 상담일정 삭제 프로세스
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/Schedule_delete.do")
    @ResponseBody
    public String Schedule_delete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);
        params.put("DEL_ARR", request.getParameterValues("DEL_ARR"));

        counselService.Sch_Delete(params);
        model.addAttribute("params", params);

        return "Y";
    }

    /**
     * @Method Name : presentReqList
     * @작성일 : 2015.04.30
     * @Method 설명 : 설명회 신청자 조회
     */
    @RequestMapping(value = "/presentReqList.do")
    public String presentReqList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String searchkey = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
        String searchtype = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
        String searchcode = CommonUtil.isNull(request.getParameter("SEARCHCODE"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("SEARCHKEYWORD", URLDecoder.decode(searchkey,"UTF-8"));
        params.put("SEARCHTYPE", searchtype);
        params.put("SEARCHCODE", searchcode);

        params.put("currentPage", String.valueOf(currentPage));
        params.put("pageRow", String.valueOf(pageRow));

        params.put("STARTNO", String.valueOf(startNo));
        params.put("ENDNO", String.valueOf(endNo));

        //리스트
        List<HashMap<String, String>> list = counselService.presentReqList(params);
        // 총 건수
        int listCount = counselService.presentReqListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("params", params);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("list", list);
        model.addAttribute("codelist", counselService.presentCodeList(params));
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        return "/counsel/presentReqList";
    }

    // 엑셀리스트
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/present_req_list_excel.do")
    public View excelDownload(ModelMap output, HttpServletRequest req) throws Exception {

        String searchkey = CommonUtil.isNull(req.getParameter("SEARCHKEYWORD"), "");
        String searchtype = CommonUtil.isNull(req.getParameter("SEARCHTYPE"), "");
        String searchcode = CommonUtil.isNull(req.getParameter("SEARCHCODE"), "");

        HashMap<String, String> params = new  HashMap<String, String>();

        params.put("SEARCHKEYWORD",URLDecoder.decode(searchkey,"UTF-8"));
        params.put("SEARCHTYPE",searchtype);
        params.put("SEARCHCODE", searchcode);
        params.put("STARTNO", "0");
        params.put("ENDNO", "10000");

        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

        //엑셀 리스트
        List<HashMap<String, String>> exe_list = counselService.presentReqList(params);

        String excelName = "설명회신청자리스트_" + simpleDate.format(date);

        List<String> headerList = new ArrayList<String>();
        headerList.add("NO");
        headerList.add("설명회");
        headerList.add("신청자아이디");
        headerList.add("신청자명");
        headerList.add("전화번호");
        headerList.add("이메일");
        headerList.add("신청일");

        int dCount = 0;
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : exe_list) {
            HashMap newMap = new HashMap();
            dCount++;
            int i = 0;
            newMap.put(i++, String.valueOf(dCount));
            newMap.put(i++, map.get("CODE_NM").toString());
            newMap.put(i++, map.get("USER_ID").toString());
            newMap.put(i++, map.get("USER_NM").toString());

            if(map.get("PHONE_NO") == null){
                newMap.put(i++, "");
            }else{
                newMap.put(i++, map.get("PHONE_NO").toString());
            }

            newMap.put(i++, map.get("EMAIL").toString());
            newMap.put(i++, map.get("REG_DT").toString());
            newList.add(newMap);    // 한줄 추가
        }

        output.addAttribute("excelName", excelName);
        output.addAttribute("headerList", headerList);
        output.addAttribute("dataList", newList);

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("AdmUserInfo");
        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("TOP_MENU_ID",   CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE",  CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID")));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));

        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));

        params.put("CAT_CD", CommonUtil.isNull(request.getParameter("CAT_CD"), ""));
        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE")));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE")));
        params.put("REQ_CNT", CommonUtil.isNull(request.getParameter("REQ_CNT")));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE")));
        params.put("REQ_TYPE", CommonUtil.isNull(request.getParameter("REQ_TYPE")));
        params.put("DATE_COUNT", CommonUtil.isNull(request.getParameter("DATE_COUNT")));
        params.put("TIME_COUNT", CommonUtil.isNull(request.getParameter("TIME_COUNT")));
        params.put("UPDATE_DATE", CommonUtil.isNull(request.getParameter("UPDATE_DATE")));
    }
}
