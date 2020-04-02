package web.lecture;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.util.CommonUtil;
import web.lecture.service.CategoryService;

/**
 * @FileName   : CategoryController.java
 * @Project    :
 * @Date       : 2015. 05. 20.
 * @Author     : miraenet
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/category")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService seriesCategoryService;

    /**
     * @Method Name  : list
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : iframe page
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/main.do")
    public String list(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> menuParams = new HashMap<String, Object>();
        setParam(menuParams, request, model);

        model.addAttribute("menuParams", menuParams);

        return "/lecture/category/main";
    }

    /**
     * @Method Name  : tree
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/tree.frm")
    public String tree(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        List<HashMap<String, Object>> menuList = seriesCategoryService.getSeriesCateTree();
        model.addAttribute("menuList",menuList);

        return "/lecture/category/tree";
    }

    /**
     * @Method Name  : add
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :  수정
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/add.frm")
    public String add(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        HashMap<String, Object> resultMap  = new HashMap<String, Object>();
        String CODE = request.getParameter("CODE");
        String ORDR = request.getParameter("ORDR");

        if(CODE!=null && CODE!=""){
            params.put("CODE", CODE);

            HashMap<String, Object> maxMenuMap  = new HashMap<String, Object>();
            maxMenuMap =   seriesCategoryService.getMaxOrdr(params);

            if(maxMenuMap == null){
                if(ORDR.equals("0")){ //Root
                    resultMap.put("ORDR", 1);
                }else{
                    resultMap.put("ORDR", (Integer.parseInt(ORDR) + 1)+"");
                }
            } else{
                resultMap.put("ORDR", maxMenuMap.get("ORDR"));
                resultMap.put("P_NAME", maxMenuMap.get("P_NAME"));
            }
            resultMap.put("P_CODE", CODE);
        }

        model.addAttribute("params", resultMap);

        return "/lecture/category/add";
    }

    /**
     * @Method Name  : detai
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/detail.frm")
    public String detail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CODE", request.getParameter("CODE"));
        HashMap<String, Object> detail = seriesCategoryService.getDetail(params);
        model.addAttribute("detail",detail);

        return "/lecture/category/view";
    }

    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	삭제 process- Ajax
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/delete_process.json")
    @ResponseBody
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public HashMap<String, Object> deleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CODE",request.getParameter("CODE"));
        int isDelete = seriesCategoryService.deleteProcess(params);
        params.put("isDelete",isDelete );

        model.addAttribute("VIEWCODE", CommonUtil.isNull(request.getParameter("CODE"), "CLASSCODE"));

        return params;
    }

    /**
     * @Method Name  : idCheck
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :	중복확인
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/idCheck.pop")
    public String idCheck(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("SRS_CD",request.getParameter("SRS_CD"));
        int idCount = seriesCategoryService.idCheck(params);

        String script = "javascript:self.close();";
        String message = "";
        if(idCount  > 0){
            message = "☞ 중복된 메뉴ID입니다. 다른 메뉴ID를 사용하여 등록해주십시요";
        }else{
            message = "☞ 등록가능한 메뉴ID입니다";
        }
        params.put("script", script);
        params.put("message", message);
        model.addAttribute("result", params);

        return "/lecture/category/checkIdPop";
    }


    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/insert_process.json")
    @ResponseBody
    public HashMap<String, Object> insertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CODE", CommonUtil.isNull(request.getParameter("CODE"), ""));
        params.put("NAME", CommonUtil.isNull(request.getParameter("NAME"), ""));
        params.put("USE_ON", CommonUtil.isNull(request.getParameter("USE_ON"), ""));
        params.put("USE_OFF", CommonUtil.isNull(request.getParameter("USE_OFF"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("ORDR", CommonUtil.isNull(request.getParameter("ORDR"), ""));
        params.put("P_CODE", CommonUtil.isNull(request.getParameter("P_CODE"), ""));

        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isInsert", seriesCategoryService.insertProcess(params)+"" );
        result.put("VIEWCODE", CommonUtil.isNull(request.getParameter("CODE"), "CLASSCODE"));

        return result;
    }

    /**
     * @Method Name  : update_process
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      :    수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/update_process.json")
    @ResponseBody
    public HashMap<String, Object> updateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new  HashMap<String, Object>();
        setParam(params, request, model);

        params.put("CODE", CommonUtil.isNull(request.getParameter("CODE"), ""));
        params.put("NAME", CommonUtil.isNull(request.getParameter("NAME"), ""));
        params.put("USE_ON", CommonUtil.isNull(request.getParameter("USE_ON"), ""));
        params.put("USE_OFF", CommonUtil.isNull(request.getParameter("USE_OFF"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("ORDR", CommonUtil.isNull(request.getParameter("ORDR"), ""));
        params.put("P_CODE", CommonUtil.isNull(request.getParameter("P_CODE"), ""));

        seriesCategoryService.updateProcess(params);
        model.addAttribute("params",params);

        HashMap<String, Object> result = new  HashMap<String, Object>();
        result.put("isUpdate", "1" );
        result.put("VIEWCODE", CommonUtil.isNull(request.getParameter("CODE"), "CLASSCODE"));

        return result;
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 05.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParam(HashMap<String, Object> params, HttpServletRequest request, ModelMap model) throws Exception {
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        model.addAttribute("VIEWCODE", CommonUtil.isNull(request.getParameter("CODE"), "CLASSCODE"));
        params.put("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "C"));
        model.addAttribute("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "C"));
    }

}
