package web.counsel;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.board.service.BoardService;
import web.counsel.service.CounselService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value = "/counsel")
@Controller
public class CounselUserController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Autowired
    private CounselService counselService;
    @Resource(name="boardServiceImpl")
    private BoardService boardService;

    /**
     * @Method Name : CounselUserList
     * @작성일 : 2015.01.07
     * @Method 설명 : 상담 신청자 조회
     */
    @RequestMapping(value = "/userlist.do")
    public String CounselUserList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(String.valueOf(params.get("subCurPage")));
        int pageRow = Integer.parseInt(String.valueOf(params.get("subPageRow")));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> rankList = boardService.getCategoryCode(params);
        model.addAttribute("rankList", rankList);

        String SEARCHCATEGORY = CommonUtil.isNull(request.getParameter("SEARCHCATEGORY_S"), "");
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
        params.put("SEARCHCATEGORY_S", SEARCHCATEGORY);

        //리스트
        List<HashMap<String, String>> list = counselService.getList(params);
        // 총 건수
        int listCount = counselService.getListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "counsel/userlist";
    }

    // 사용자 팝업
    @RequestMapping(value="/MemberView.pop")
    public String MemberView1(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");
        String SCH_DAY = CommonUtil.isNull(request.getParameter("SCH_DAY"), "");
        String TS_IDX = CommonUtil.isNull(request.getParameter("TS_IDX"), "");
        params.put("USER_ID", USER_ID);
        params.put("SCH_DAY", SCH_DAY);
        params.put("TS_IDX", TS_IDX);

        //사용자 상담 신청 내역
        List<HashMap<String, String>> user_counsel = counselService.getCounselUserReq(params);
        model.addAttribute("user_counsel", user_counsel);

        model.addAttribute("params", params);

        return "counsel/MemberView";
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
        params.put("subCurPage", CommonUtil.isNull(request.getParameter("subCurPage"), "1"));
        params.put("subPageRow", CommonUtil.isNull(request.getParameter("subPageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("TOP_MENU_ID",   CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
        params.put("MENUTYPE",  CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID")));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
        model.addAttribute("currentPage", params.get("currentPage"));
        model.addAttribute("pageRow", params.get("pageRow"));
        model.addAttribute("subCurPage", params.get("subCurPage"));
        model.addAttribute("subPageRow", params.get("subPageRow"));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));

        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHKEYWORD", URLDecoder.decode(CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""),"UTF-8"));
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
