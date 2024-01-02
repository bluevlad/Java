package com.willbes.web.mocktest.offExamReg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelRead4OffExmReg;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.mocktest.offExamReg.service.OffExamRegService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/offExamReg")
@Controller
public class OffExamRegController {

    /** log */
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    @Inject
    private FileSystemResource fsResource;	//DI

    @Resource(name="fileUtil")
    FileUtil fileUtil;

    @Resource(name="excelRead4OffExmReg")
    ExcelRead4OffExmReg excelUtil;

    @Autowired
    private OffExamRegService offExamRegservice;

    /**
     * @Method Name : offExamList
     * @작성일 : 2013. 9.
     * @Method 설명 : off응시자등록 리스트
     * @param request
     * @return
     */
    @RequestMapping(value="/offExamList.do")
    public String offExamList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> list = offExamRegservice.offExamList(params);
        int listCount = offExamRegservice.listCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        model.addAttribute("params", params);

        return "mocktest/offExamReg/list";
    }

    /**
     * @Method Name : offExamView
     * @작성일 : 2013. 9.
     * @Method 설명 : off응시자등록 상세뷰
     * @param request
     * @return
     */
    @RequestMapping(value="/offExamView.do")
    public String offExamView(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        String mockCode = request.getParameter("mockCode");
        params.put("mockCode", mockCode);

        HashMap<String, String> detailView = offExamRegservice.offExamView(params);
        model.addAttribute("detailView", detailView);

        String message = request.getParameter("message");
        if(null != message && !"".equals(message)) {
            params.put("message", message);
        }

        model.addAttribute("params", params);

        return "mocktest/offExamReg/view";
    }

    /**
     * @Method Name : offExamExcelRead
     * @작성일 : 2013. 9.
     * @Method 설명 : off응시자등록 엑셀 데이터 저장 process
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/offExamExcelRead.do")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public String offExamExcelRead(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest)
            throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        String mockCode = request.getParameter("mockCode");

        try {

            String adminId = params.get("USER_ID").toString();
            String rootPath = fsResource.getPath();
            String subPath = "excel/";

            MultipartFile uploadFile = multiRequest.getFile("uploadFile");
            HashMap<String, Object> fileMap = fileUtil.uploadFileWthField(uploadFile, rootPath, subPath);
            String fileFullPath = (String) fileMap.get("fileFullPath");
            String fileName = (String) fileMap.get("fileName");
            String fileSize = (String) fileMap.get("fileSize");

            params.put("mockCode", mockCode);

            // 파일 정보 insert
            HashMap<String, Object> fiileInfoMap = new HashMap<String, Object>();
            fiileInfoMap.put("mockCode", mockCode);
            fiileInfoMap.put("fileName", fileName);
            fiileInfoMap.put("filePath", fileFullPath);
            fiileInfoMap.put("fileSize", Integer.parseInt(fileSize));
            fiileInfoMap.put("fileCreateId", adminId);

            params.put("fileInfoMap", fiileInfoMap);

            HashMap<String, Object> read = excelUtil.readExcelXLS(rootPath+fileFullPath);
            ArrayList<OffExamRegVO> reads = new ArrayList<OffExamRegVO>();
            OffExamRegVO header = new OffExamRegVO();
            ArrayList<OffExamRegVO> wdata = new ArrayList<OffExamRegVO>();
            ArrayList<OffExamRegVO> errors = new ArrayList<OffExamRegVO>();
            if(null != read) {
                reads = (ArrayList<OffExamRegVO>)read.get("params");
                header = (OffExamRegVO)read.get("header");
                wdata = (ArrayList<OffExamRegVO>)read.get("wdata");
                errors = (ArrayList<OffExamRegVO>)read.get("errors");
            }

            String message = "";
            String[] headerNM = header.getHEADER_NM();
            if(null == wdata || wdata.size()<1) {
                if (log.isDebugEnabled()) {
                    log.debug("***** off응시자등록 엑셀 데이터 ***** ");
                    log.debug("***** reads.size() : " + reads.size());
                    log.debug("***** off응시자등록 엑셀 오류 데이터 ***** ");
                    log.debug("***** errors.size() : " + errors.size());

                    if(null != errors && errors.size()>0) {
                        for(OffExamRegVO vo : errors) {
                            String[] errsYN = vo.getERRS_YN();
                            String[] errsDESC = vo.getERRS_DESC();
                            for(int i=0; i< errsYN.length; i++) {
                                if("Y".equals(errsYN[i])) {
                                    log.debug("***** "+headerNM[i]+"필드 : " + errsDESC[0]);
                                }
                            }
                        }
                    }
                }

                model.addAttribute("reads", reads);
                model.addAttribute("exlerrors", errors);

                model.addAttribute("message", "등록 실패 - 오프라인 응시자 답안을 등록하지 못했습니다!");
                model.addAttribute("mockCode", mockCode);
            } else {
                params.put("excelFileData", wdata);
                if (log.isDebugEnabled()) {
                    log.debug("***** off응시자등록 엑셀 데이터 ***** ");
                    log.debug("***** wdata.size() : " + wdata.size());
                    log.debug("***** off응시자등록 엑셀 오류 데이터 ***** ");
                    log.debug("***** errors.size() : " + errors.size());

                    if(null != errors && errors.size()>0) {
                        for(OffExamRegVO vo : errors) {
                            String[] errsYN = vo.getERRS_YN();
                            String[] errsDESC = vo.getERRS_DESC();
                            for(int i=0; i< errsYN.length; i++) {
                                if("Y".equals(errsYN[i])) {
                                    log.debug("***** "+headerNM[i]+"필드 : " + errsDESC[0]);
                                }
                            }
                        }
                    }
                }

                ArrayList<OffExamRegVO> retErrors = offExamRegservice.insertExcelFileData(params);
                if (log.isDebugEnabled()) {
                    log.debug("***** off응시자등록 결과 데이터 ***** ");
                    log.debug("***** retErrors.size() : " + retErrors.size());

                    if(null != retErrors && retErrors.size()>0) {
                        int i = 0;
                        for(OffExamRegVO vo : retErrors) {
                            String errsYN = vo.getERR_YN();
                            String errsDESC = vo.getERR_DESC();
                            if("Y".equals(errsYN)) {
                                log.debug("***** "+(i++)+"  : " + errsDESC);
                            }
                        }
                    }
                }

                if(null != errors && errors.size()>0) {
                    message = "엑셀 데이터 오류("+errors.size()+"건)";
                }
                if(null != retErrors && retErrors.size()>0) {
                    if(null != message && !"".equals(message)) {
                        message = message + ", 데이터 등록 오류("+retErrors.size()+"건)";
                    } else {
                        message = "데이터 등록 오류("+retErrors.size()+"건)";
                    }
                }

                if(null != message && !"".equals(message)) {
                    message = "등록 성공["+message+"] - 오프라인 응시자 답안을 성공적으로 등록했습니다.";
                } else {
                    message = "등록 성공 - 오프라인 응시자 답안을 성공적으로 등록했습니다.";
                }

                model.addAttribute("reads", reads);
                model.addAttribute("exlerrors", errors);
                model.addAttribute("dberrors", retErrors);

                model.addAttribute("message", message);
                model.addAttribute("mockCode", mockCode);
            }
        } catch (Exception e) {
            model.addAttribute("message", "등록 실패 - 오프라인 응시자 답안을 등록하지 못했습니다!");
            model.addAttribute("mockCode", mockCode);
        }

        HashMap<String, String> detailView = offExamRegservice.offExamView(params);
        model.addAttribute("detailView", detailView);

        model.addAttribute("params", params);

        //return "redirect:/offExamReg/offExamView.do";
        return "mocktest/offExamReg/view";
    }

    /**
     * 응시자 답변 삭제 프로세스
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/offExamDelete.do")
    @ResponseBody
    public HashMap<String, String> offExamDelete(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request, model);

        params.put("mockCode", request.getParameter("mockCode"));

        String rootPath = fsResource.getPath();
        String fileFullPath = request.getParameter("fileFullPath");
        @SuppressWarnings("unused")
        int isSucess  = offExamRegservice.offExamDelete(params);
        @SuppressWarnings("unused")
        int isSucessGrade  = offExamRegservice.offExamGradeDelete(params);
        int isSucessFile  = offExamRegservice.offExamDeleteFile(params);

        File file = new File(rootPath+fileFullPath);
        file.delete();

        HashMap<String, String> result = new HashMap<String, String>();
        if(isSucessFile == 1){
            result.put("successmessage", "삭제되었습니다.");
        } else {
            result.put("errormessage", "삭제 실패하였습니다.");
        }
        return result;
    }

    /**
     * 응시자 답변 목록 리스트
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/offListPop.pop")
    public String offListPop(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        setParam(params, request, model);

        params.put("mockCode", request.getParameter("mockCode"));
        params.put("mockName", request.getParameter("mockName"));
        params.put("classCode", request.getParameter("classCode"));

        int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        List<HashMap<String, String>> list = offExamRegservice.offListPop(params);
        int listCount = offExamRegservice.popListCount(params);

        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
        model.addAttribute("mockCode", request.getParameter("mockCode"));
        model.addAttribute("list", list);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        model.addAttribute("params", params);

        return "mocktest/offExamReg/offListPop";
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
        params.put("searchType", CommonUtil.isNull(request.getParameter("searchType"), ""));
        params.put("searchText", CommonUtil.isNull(request.getParameter("searchText"), ""));
        model.addAttribute("currentPage", params.get("currentPage"));
        model.addAttribute("pageRow", params.get("pageRow"));
        model.addAttribute("searchText", params.get("searchText"));
        model.addAttribute("searchText", params.get("searchText"));

        params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
        params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
        params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
        params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
        model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
        model.addAttribute("MENU_ID", params.get("MENU_ID"));
        model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
        model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
    }

}
