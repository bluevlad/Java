package web.mocktest.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.mocktest.mouigosa.service.MouigosaService;
import web.mocktest.stats.service.MockResultService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/mock/result")
@Controller
public class ResultController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private MockResultService mresultservice;
    @Autowired
    private MouigosaService mouigosaService;

    /**
     * @Method Name : mouigosaList
     * @작성일 : 2015. 12.
     * @Method 설명 : 모의고사 리스트
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/mouigosaList.do")
    public String mouigosaList(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

        // 검색조건
        String S_EXAMYEAR = CommonUtil.isNull(request.getParameter("S_EXAMYEAR"), "");
        String S_EXAMROUND = CommonUtil.isNull(request.getParameter("S_EXAMROUND"), "");
        String S_ISEXAMTYPEON = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEON"), "");
        String S_ISEXAMTYPEOFF = CommonUtil.isNull(request.getParameter("S_ISEXAMTYPEOFF"), "");
        String S_CLASSCODE = CommonUtil.isNull(request.getParameter("S_CLASSCODE"), "");
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        Map<String, Object> searchMap = new HashMap<String, Object>();

        searchMap.put("S_EXAMYEAR", S_EXAMYEAR);
        searchMap.put("S_EXAMROUND", S_EXAMROUND);
        searchMap.put("S_ISEXAMTYPEON", S_ISEXAMTYPEON);
        searchMap.put("S_ISEXAMTYPEOFF", S_ISEXAMTYPEOFF);
        searchMap.put("S_CLASSCODE", S_CLASSCODE);

        searchMap.put("currentPage", currentPage);
        searchMap.put("pageRow", pageRow);
        searchMap.put("startNo", String.valueOf(startNo));
        searchMap.put("endNo", String.valueOf(endNo));

        // 모의고사 리스트
        List<?> list = mouigosaService.getMouigosaRegistrationList(searchMap);

        //'직급' 리스트
        List<?> registration_list = mouigosaService.getRegistrationList(searchMap);
        // 총 건수
        int listCount = mouigosaService.getRegistrationCount(searchMap);

        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("registration_list", registration_list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageRow", pageRow);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("pagingHtml", pagingHtml);

		model.addAttribute("params", params);
        model.addAttribute("searchMap", searchMap);

        return "/mocktest/result/mouigosaList";
    }

    /**
     * @Method Name : mockResultView
     * @작성일 : 2015. 12.
     * @Method 설명 : 모의고사 결과
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/mockResultView.do")
    public String mockResultView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> mresult = mresultservice.getMockSubjectResultList(params);

        model.addAttribute("mresult", mresult);
        model.addAttribute("params", params);

        return "/mocktest/result/resultView";
    }

    /**
     * @Method Name : makeResult
     * @작성일 : 2015. 12.
     * @Method 설명 : 모의고사 결과 집계
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/makeResult.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeResult(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";
        mresultservice.copyMockSbjRst(params);

        mresultservice.makeMockSbjMst(params);

        model.addAttribute("params", params);
        Result = "Y";

        return Result;
    }

    /**
     * @Method Name : makeMockStndDev
     * @작성일 : 2015. 12.
     * @Method 설명 : 표준편차 산출
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/makeMockStndDev.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeMockStndDev(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";
        mresultservice.makeMockStndDev(params);

        model.addAttribute("params", params);
        Result = "Y";

        return Result;
    }

    /**
     * @Method Name : makeMockAdjust
     * @작성일 : 2015. 12.
     * @Method 설명 : 모의고사 조정점수 반영
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/makeMockAdjust.do")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String makeMockAdjust(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String Result = "";
        mresultservice.makeMockAdjust(params);

        model.addAttribute("params", params);
        Result = "Y";

        return Result;
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 04.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setParam(HashMap params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("USER_NM", loginInfo.get("USER_NM"));
        params.put("USER_ID", loginInfo.get("USER_ID"));
        params.put("USERNAME", loginInfo.get("USER_NM"));
        params.put("USERID", loginInfo.get("USER_ID"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

        params.put("MOCKCODE", request.getParameter("MOCKCODE"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
        model.addAttribute("MENU_NM", params.get("MENU_NM"));
    }

}
