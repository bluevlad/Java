package com.willbes.web.bannerManagement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.cmm.service.CmmUseService;
import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.bannerManagement.service.BannerManagementService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   : BannerController.java
 * @Project    :
 * @Date       : 2013. 12
 * @Author     :
 * @변경이력    : 2015.07
 * @프로그램 설명 : 배너관리
 *                   2015.07 - 기능 변경에 따라 프로그램 수정 변경
 */
@RequestMapping(value="/bannerManagement")
@Controller
public class BannerManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;
    @Resource(name = "CmmUseService")
    private CmmUseService cmmUseService;

    @Inject
    private FileSystemResource fsResource;
    @Resource(name="fileUtil")
    private FileUtil fileUtil;

    @Autowired
    private BannerManagementService  bannerManagementService;

    /**
     * @Method Name  : bannerList
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      :	배너 리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/bannerMgtList.do")
    public String popupList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> catekindlist = bannerManagementService.getCateKindList(params);
        model.addAttribute("catekindlist", catekindlist);
        List<HashMap<String, String>>menukindlist = bannerManagementService.getMenuKindList(params);
        model.addAttribute("menukindlist", menukindlist);
        Map<String, String> vo = new HashMap<String, String>();
        if("OM_ROOT".equals(String.valueOf(params.get("MENUTYPE")))) {
            vo.put("SYS_CD", "MAIN_BNNR");
        } else {
            vo.put("SYS_CD", "FMAIN_BNNR");
        }
        model.addAttribute("MAIN_BNNRs", cmmUseService.selectCmmCodeMapSortStr(vo));

        //배너리스트
        List<HashMap<String, String>> list = bannerManagementService.getBannerList(params);
        //총 건수 -운영자
        int listCount = bannerManagementService.getBannerListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/bannerManagement/bannerList";
    }

    /**
     * @Method Name  : bannerChange
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 일괄 변경
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/change.do")
    public String bannerChange(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] BANNER_NO = request.getParameterValues("BANNER_NO");
        String[] BANNER_TYP = request.getParameterValues("BANNER_TYP");
        String[] BANNER_TYPS = request.getParameterValues("BANNER_TYPS");

        ArrayList<HashMap<String, String>> banners = new ArrayList<HashMap<String, String>>();

        if(BANNER_NO != null){
            String[] BANNER_NOS;
            String b_typ = "";
            String b_typs = "";

            for(int i=0; i<BANNER_NO.length; i++){
                b_typ = BANNER_TYP[i];
                b_typs = BANNER_TYPS[i];
                if((null != b_typs && !"".equals(b_typs)) && !b_typ.equals(b_typs)) {
                    HashMap<String, String> banner = new HashMap<String, String>();

                    BANNER_NOS = BANNER_NO[i].split("#",2);
                    banner.put("SEQ", BANNER_NOS[0]);
                    banner.put("BANNER_TYP", BANNER_TYPS[i]);
                    banner.put("REG_ID", params.get("REG_ID"));

                    banners.add(banner);
                }
            }
            bannerManagementService.changeProcess(banners);
        }
        model.addAttribute("params",params);

        return "forward:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name  : add
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 마스터 등록 화면
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerAdd.do")
    public String add(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> catekindlist = bannerManagementService.getCateKindList(params);
        model.addAttribute("catekindlist", catekindlist);
        List<HashMap<String, String>>menukindlist = bannerManagementService.getMenuKindList(params);
        model.addAttribute("menukindlist", menukindlist);

        Map<String, String> vo = new HashMap<String, String>();
        if("OM_ROOT".equals(String.valueOf(params.get("MENUTYPE")))) {
            vo.put("SYS_CD", "MAIN_BNNR");
        } else {
            vo.put("SYS_CD", "FMAIN_BNNR");
        }
        List<HashMap<String, String>> banners = cmmUseService.selectCmmCodeMap(vo);
        model.addAttribute("MAIN_BNNRs", banners);

        model.addAttribute("params",params);
        return "/bannerManagement/bannerInsert";
    }

    /**
     *
     * @Method Name : getBannerCode
     * @작성일 : 2015. 09.
     * @Method 설명 : 배너 공통 코드 목록 조회: 메인/서브메인/서브페이지 구분, 학원/동영상 구분
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/getBannerCode")
    @ResponseBody
    public List<HashMap<String, String>>  getBannerCode(ModelMap model, HttpServletRequest request) throws Exception {
        String ONOFF_DIV = CommonUtil.isNull(request.getParameter("ONOFF_DIV"), "");
        String SCREEN_GUBUN = CommonUtil.isNull(request.getParameter("SCREEN_GUBUN"), "");
        String CATEGORY_CD = CommonUtil.isNull(request.getParameter("CATEGORY_CD"), "");

        Map<String, String> vo = new HashMap<String, String>();
        if("O".equals(ONOFF_DIV)) {
            if("M".equals(SCREEN_GUBUN)) {
                vo.put("SYS_CD", "MAIN_BNNR");
            } else if("H".equals(SCREEN_GUBUN)) {
                vo.put("SYS_CD", "MOBL_BNNR");
            } else {
                if(CATEGORY_CD.startsWith("O") && !"OM_012".equals(CATEGORY_CD)) {
                    vo.put("SYS_CD", "SUB_BNNR");
                } else {
                    vo.put("SYS_CD", "SUBM_BNNR");
                }
            }
        } else {
            if("M".equals(SCREEN_GUBUN)) {
                vo.put("SYS_CD", "FMAIN_BNNR");
            } else if("H".equals(SCREEN_GUBUN)) {
                vo.put("SYS_CD", "MOBL_BNNR");
            } else {
                if(CATEGORY_CD.startsWith("F") && !"FM_012".equals(CATEGORY_CD)) {
                    vo.put("SYS_CD", "FSUB_BNNR");
                } else {
                    vo.put("SYS_CD", "FSUBM_BNNR");
                }
            }
        }
        return cmmUseService.selectCmmCodeMapSortNum(vo);
    }

    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 마스터 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/insert.do")
    public String insertProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("SCREEN_GUBUN", CommonUtil.isNull(request.getParameter("SCREEN_GUBUN"), ""));
        params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
        params.put("BANNER_NO", CommonUtil.isNull(request.getParameter("BANNER_NO"), ""));
        params.put("BANNER_TITLE", CommonUtil.isNull(request.getParameter("BANNER_TITLE"), ""));
        params.put("BANNER_TYP", CommonUtil.isNull(request.getParameter("BANNER_TYP"), ""));
        params.put("OPEN_STARTDATE", CommonUtil.isNull(request.getParameter("OPEN_STARTDATE"), ""));
        params.put("OPEN_ENDDATE", CommonUtil.isNull(request.getParameter("OPEN_ENDDATE"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        //배너 등록
        bannerManagementService.insertProcess(params);

        model.addAttribute("params",params);

        return "redirect:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name  : view
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너관리 마스터 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerView.do")
    public String view(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"),""));

        List<HashMap<String, String>> catekindlist = bannerManagementService.getCateKindList(params);
        model.addAttribute("catekindlist", catekindlist);
        List<HashMap<String, String>>menukindlist = bannerManagementService.getMenuKindList(params);
        model.addAttribute("menukindlist", menukindlist);

        Map<String, String> vo = new HashMap<String, String>();
        if("OM_ROOT".equals(String.valueOf(params.get("MENUTYPE")))) {
            vo.put("SYS_CD", "MAIN_BNNR");
        } else {
            vo.put("SYS_CD", "FMAIN_BNNR");
        }
        model.addAttribute("MAIN_BNNRs", cmmUseService.selectCmmCodeMap(vo));

        model.addAttribute("view", bannerManagementService.view(params));
        model.addAttribute("params",params);

        return "/bannerManagement/bannerView";
    }

    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 마스터 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/update.do")
    public String updateProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("SCREEN_GUBUN", CommonUtil.isNull(request.getParameter("SCREEN_GUBUN"), ""));
        params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
        params.put("BANNER_NO", CommonUtil.isNull(request.getParameter("BANNER_NO"), ""));
        params.put("BANNER_TITLE", CommonUtil.isNull(request.getParameter("BANNER_TITLE"), ""));
        params.put("BANNER_TYP", CommonUtil.isNull(request.getParameter("BANNER_TYP"), ""));
        params.put("OPEN_STARTDATE", CommonUtil.isNull(request.getParameter("OPEN_STARTDATE"), ""));
        params.put("OPEN_ENDDATE", CommonUtil.isNull(request.getParameter("OPEN_ENDDATE"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        //배너 수정
        bannerManagementService.updateProcess(params);

        model.addAttribute("params",params);

        return "redirect:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 07.
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/delete.do")
    public String deleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));

        //배너 삭제
        bannerManagementService.deleteProcess(params);

        return "redirect:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name  : subList
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      :    배너 상세 리스트
     * @param model
     * @param req
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/bannerSList.do")
    public String subList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        List<HashMap<String, String>> list = null;
        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("subCurrentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("subPageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("subCurrentPage", String.valueOf(currentPage));
        params.put("subPageRow", String.valueOf(pageRow));
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        params.put("SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        model.addAttribute("view", bannerManagementService.view(params));

        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));

        //배너리스트
        list = bannerManagementService.getBannerSubList(params);
        //총 건수 -운영자
        int listCount = bannerManagementService.getBannerSubListCount(params);
        //페이징 처리
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
        model.addAttribute("params", params);

        return "/bannerManagement/bannerSList";
    }

    /**
     * @Method Name  : updateItemOrder
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 순서 변경
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/update_order.do")
    public String updateItemOrder(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String[] ITEM_ARR = request.getParameterValues("ITEM_IDX");
        String[] SEL_ITEM_IDX = request.getParameterValues("SEL_ITEM_IDX");
        String[] ROL_ARR = request.getParameterValues("ROL_IDX");
        String CHK_ISUSE = CommonUtil.isNull(request.getParameter("CHK_ISUSE"), "");

        ArrayList<HashMap<String, String>> banners = new ArrayList<HashMap<String, String>>();
        ArrayList<HashMap<String, String>> selbanners = new ArrayList<HashMap<String, String>>();

        if(ITEM_ARR != null){
            for(int i=0; i<ITEM_ARR.length; i++){
                HashMap<String, String> banner = new HashMap<String, String>();

                banner.put("SEQ", ITEM_ARR[i]);
                banner.put("ROL_IDX", ROL_ARR[i]);
                banner.put("REG_ID", params.get("REG_ID"));

                banners.add(banner);
            }

            bannerManagementService.updateItemOrder(banners);
        }
        if(SEL_ITEM_IDX != null){
        	for(int j=0; j<SEL_ITEM_IDX.length; j++){
        		String[] SEL_ARR = SEL_ITEM_IDX[j].split("_"); 
        		if(SEL_ARR != null && SEL_ARR.length > 0){
	        		HashMap<String, String> banner = new HashMap<String, String>();
	        		banner.put("SEQ", SEL_ARR[0]);
	        		banner.put("ROL_IDX", SEL_ARR[1]);
	        		if(null != CHK_ISUSE && !"".equals(CHK_ISUSE)) {
	                    banner.put("ISUSE", CHK_ISUSE);
	                }
	        		banner.put("REG_ID", params.get("REG_ID"));
	        		selbanners.add(banner);
        		}
            }
        	bannerManagementService.updateItemOrder(selbanners);
        }
        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        model.addAttribute("params",params);

        return "redirect:/bannerManagement/bannerSList.do?P_SEQ="+params.get("P_SEQ");
    }

    /**
     * @Method Name  : bannerInsertView
     * @Date         : 2015. 07
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 등록 화면
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerInsertView.do")
    public String bannerInsertView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        params.put("SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));

        List<HashMap<String, String>> view = bannerManagementService.view(params);
        String screenGubun = String.valueOf((view.get(0)).get("SCREEN_GUBUN"));
        String bannerNo = String.valueOf((view.get(0)).get("BANNER_NO"));
        String catCd = String.valueOf((view.get(0)).get("CATEGORY_CD"));
        if(null != screenGubun && "S".equals(screenGubun) && catCd.length() == 3) {
            if("OM_ROOT".equals(String.valueOf(params.get("MENUTYPE")))) {
                params.put("ONOFF_DIV", "O");
            } else {
                params.put("ONOFF_DIV", "F");
            }
            params.put("BANNER_NO", bannerNo);
            model.addAttribute("cate_list", bannerManagementService.getCateBannerList(params));
        }

        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        model.addAttribute("view", view);
        model.addAttribute("params",params);

        return "/bannerManagement/bannerSInsert";
    }

    /**
     * @Method Name  : bannerInsertProcess
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 등록 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerInsertProcess.do")
    public String bannerInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String banner_type = CommonUtil.isNull(request.getParameter("BANNER_TYP"), "");
        if(null != banner_type && !"".equals(banner_type)) {
            if("L".equals(banner_type) || "B".equals(banner_type)) {
                params.put("BANNER_IMAGE", CommonUtil.isNull(request.getParameter("BANNER_IMAGE"), ""));
                params.put("BANNER_LINK", CommonUtil.isNull(request.getParameter("BANNER_LINK"), ""));
            } else {
                String rootPath = fsResource.getPath();
                String subPath = "banner/";

                MultipartFile img = multipartRequest.getFile("BANNER_IMAGE");
                if(img != null && img.isEmpty() == false) {
                    HashMap<String, Object> fileMap1 = fileUtil.uploadFile(img, rootPath, subPath);
                    params.put("BANNER_IMAGE", fileMap1.get("fileFullPath").toString());
                    Thread.sleep(100);
                }
                MultipartFile thumImg = multipartRequest.getFile("BANNER_THUMBNAIL_IMAGE");
                if(thumImg != null && thumImg.isEmpty() == false) {
                    HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumImg, rootPath, subPath);
                    params.put("BANNER_THUMBNAIL_IMAGE", fileMap2.get("fileFullPath").toString());
                    Thread.sleep(100);
                }
            }
        }

        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        params.put("ROL_IDX", CommonUtil.isNull(request.getParameter("ROL_IDX"), ""));
        params.put("BANNER_SUBTITLE", CommonUtil.isNull(request.getParameter("BANNER_SUBTITLE"), ""));
        params.put("BANNER_NOTE", CommonUtil.isNull(request.getParameter("BANNER_NOTE"), ""));
        params.put("BANNER_LINK", CommonUtil.isNull(request.getParameter("BANNER_LINK"), ""));
        params.put("BANNER_LINK_TARGET", CommonUtil.isNull(request.getParameter("BANNER_LINK_TARGET"), ""));
        params.put("BANNER_SDT", CommonUtil.isNull(request.getParameter("BANNER_SDT"), ""));
        params.put("BANNER_EDT", CommonUtil.isNull(request.getParameter("BANNER_EDT"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("BANNER_NO", CommonUtil.isNull(request.getParameter("BANNER_NO"), ""));
        params.put("SCREEN_GUBUN", CommonUtil.isNull(request.getParameter("SCREEN_GUBUN"), ""));
        params.put("CATEGORY_CDs", CommonUtil.isNull(request.getParameter("codeStr"), ""));
        params.put("BANNER_TYP", banner_type);

        //배너 등록
        bannerManagementService.bannerInsertProcess(params);

        model.addAttribute("params",params);

        return "redirect:/bannerManagement/bannerSList.do?P_SEQ="+params.get("P_SEQ");
    }

    /**
     * @Method Name  : bannerDetail
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너관리 상세
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerDetail.do")
    public String bannerDetail(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        params.put("SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        model.addAttribute("view", bannerManagementService.view(params));

        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"),""));
        model.addAttribute("detail", bannerManagementService.bannerDetail(params));

        model.addAttribute("params",params);

        return "/bannerManagement/bannerSDetail";
    }

    /**
     * @Method Name  : bannerUpdateProcess
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 수정 process
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerUpdateProcess.do")
    public String bannerUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String banner_type = CommonUtil.isNull(request.getParameter("BANNER_TYP"), "");
        if(null != banner_type && !"".equals(banner_type)) {
            if("L".equals(banner_type) || "B".equals(banner_type)) {
                params.put("BANNER_IMAGE", CommonUtil.isNull(request.getParameter("BANNER_IMAGE"), ""));
                params.put("BANNER_LINK", CommonUtil.isNull(request.getParameter("BANNER_LINK"), ""));
            } else {
                String rootPath = fsResource.getPath();
                String subPath = "banner/";

                MultipartFile img = multipartRequest.getFile("BANNER_IMAGE");
                String imgBefore = CommonUtil.isNull(request.getParameter("BANNER_IMAGE_BEFORE"), "");
                String isImgDel = CommonUtil.isNull(request.getParameter("BANNER_IMAGE_DEL"), "");

                MultipartFile thumImg = multipartRequest.getFile("BANNER_THUMBNAIL_IMAGE");
                String thumImgBefore = CommonUtil.isNull(request.getParameter("BANNER_THUMBNAIL_IMAGE_BEFORE"), "");
                String isThumImgDel = CommonUtil.isNull(request.getParameter("BANNER_THUMBNAIL_IMAGE_DEL"), "");

                //배너이미지 다시 올린파일이 있으면 기존파일 지우고 새로운파일 업로드
                if(img != null && img.isEmpty() == false) {
                    if(!"".equals(imgBefore)) {
                        fileUtil.deleteFile(rootPath + imgBefore);
                    }
                    HashMap<String, Object> fileMap1 = fileUtil.uploadFile(img, rootPath, subPath);
                    params.put("BANNER_IMAGE", fileMap1.get("fileFullPath").toString());
                    Thread.sleep(100);
                } else {
                    if("Y".equals(isImgDel)){
                        //새로운 파일 올리지 않고 기존파일만 삭제할 경우
                        //validation 필수 check할경우 필요없는 기능
                        fileUtil.deleteFile(rootPath + imgBefore);
                        params.put("BANNER_IMAGE", "");
                    } else {
                        params.put("BANNER_IMAGE", imgBefore);
                    }
                }

                //썸네일 이미지 다시 올린파일이 있으면 기존파일 지우고 새로운파일 업로드
                if(thumImg != null && thumImg.isEmpty() == false) {
                    if(!"".equals(thumImgBefore)) {
                        fileUtil.deleteFile(rootPath + thumImgBefore);
                    }
                    HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumImg, rootPath, subPath);
                    params.put("BANNER_THUMBNAIL_IMAGE", fileMap2.get("fileFullPath").toString());
                    Thread.sleep(100);
                } else {
                    if("Y".equals(isThumImgDel)){
                        //새로운 파일 올리지 않고 기존파일만 삭제할 경우
                        //validation 필수 check할경우 필요없는 기능
                        fileUtil.deleteFile(rootPath + thumImgBefore);
                        params.put("BANNER_THUMBNAIL_IMAGE", "");
                    }else {
                        params.put("BANNER_THUMBNAIL_IMAGE", thumImgBefore);
                    }
                }
            }
        }

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("P_SEQ", CommonUtil.isNull(request.getParameter("P_SEQ"), ""));
        params.put("ROL_IDX", CommonUtil.isNull(request.getParameter("ROL_IDX"), ""));
        params.put("BANNER_SUBTITLE", CommonUtil.isNull(request.getParameter("BANNER_SUBTITLE"), ""));
        params.put("BANNER_NOTE", CommonUtil.isNull(request.getParameter("BANNER_NOTE"), ""));
        params.put("BANNER_LINK", CommonUtil.isNull(request.getParameter("BANNER_LINK"), ""));
        params.put("BANNER_LINK_TARGET", CommonUtil.isNull(request.getParameter("BANNER_LINK_TARGET"), ""));
        params.put("BANNER_SDT", CommonUtil.isNull(request.getParameter("BANNER_SDT"), ""));
        params.put("BANNER_EDT", CommonUtil.isNull(request.getParameter("BANNER_EDT"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));

        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("BANNER_NO", CommonUtil.isNull(request.getParameter("BANNER_NO"), ""));
        params.put("SCREEN_GUBUN", CommonUtil.isNull(request.getParameter("SCREEN_GUBUN"), ""));
        params.put("CATEGORY_CDs", CommonUtil.isNull(request.getParameter("codeStr"), ""));
        params.put("BANNER_TYP", banner_type);
        params.put("G_SEQ", CommonUtil.isNull(request.getParameter("G_SEQ"), ""));
        params.put("ORG_CATEGORY_CDs", CommonUtil.isNull(request.getParameter("orgCodeStr"), ""));

        //배너 등록
        bannerManagementService.bannerUpdateProcess(params);

        model.addAttribute("params",params);

        return "redirect:/bannerManagement/bannerSList.do?P_SEQ="+params.get("P_SEQ");
    }

    /**
     * @Method Name  : bannerDelete
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerDelete.do")
    public String bannerDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String rootPath = fsResource.getPath();
        String img = CommonUtil.isNull(request.getParameter("BANNER_IMAGE_BEFORE"), "");
        String thumImg = CommonUtil.isNull(request.getParameter("BANNER_THUMBNAIL_IMAGE_BEFORE"), "");

        if(!"".equals(img)){
            fileUtil.deleteFile(rootPath + img);
        }
        if(!"".equals(thumImg)){
            fileUtil.deleteFile(rootPath + thumImg);
        }

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));

        //배너 삭제
        bannerManagementService.bannerDelete(params);

        return "redirect:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name  : bannerCheckDelete
     * @Date         : 2013. 12
     * @Author       :
     * @변경이력      :
     * @Method 설명      : 배너 일괄 삭제
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerCheckDelete.do")
    public String bannerCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        String rootPath = fsResource.getPath();

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        String[] DEL_ARR_VALUE;

        if(DEL_ARR != null){
            for(int i=0; i<DEL_ARR.length; i++){
                DEL_ARR_VALUE = DEL_ARR[i].split(",",3);
                params.put("SEQ", DEL_ARR_VALUE[0]);
                if(!"".equals(DEL_ARR_VALUE[1]))fileUtil.deleteFile(rootPath + DEL_ARR_VALUE[1]);
                if(!"".equals(DEL_ARR_VALUE[2]))fileUtil.deleteFile(rootPath + DEL_ARR_VALUE[2]);
                bannerManagementService.bannerDelete(params);
            }
        }
        model.addAttribute("params",params);

        return "forward:/bannerManagement/bannerMgtList.do";
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2013. 12.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
        @SuppressWarnings("unused")
        HttpSession session = request.getSession(false);
        HashMap<String, String> loginInfo = (HashMap<String, String>)request.getSession().getAttribute("AdmUserInfo");
        params.put("REG_ID",loginInfo.get("USER_ID"));
        params.put("UPD_ID",loginInfo.get("USER_ID"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        model.addAttribute("currentPage", params.get("currentPage"));
        model.addAttribute("pageRow", params.get("pageRow"));

        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
        params.put("SEARCHBANNERNO", CommonUtil.isNull(request.getParameter("SEARCHBANNERNO"), ""));
        params.put("SEARCHISUSE", CommonUtil.isNull(request.getParameter("SEARCHISUSE"), ""));

        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHROLIDX", CommonUtil.isNull(request.getParameter("SEARCHROLIDX"), ""));

    }
}
