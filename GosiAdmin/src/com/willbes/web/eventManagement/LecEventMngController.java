package com.willbes.web.eventManagement;


import java.io.UnsupportedEncodingException;
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

import com.willbes.web.adminManagement.service.AdminManagementCodeService;
import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.book.service.BookService;
import com.willbes.web.eventManagement.service.LecEventMngService;
import com.willbes.web.excel.LecEventExcel;
import com.willbes.web.lecture.service.LectureService;
import com.willbes.web.lecture.service.TeacherService;
import com.willbes.web.productOrder.service.ProductOrderService;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @FileName   : LecEventMng.java
 * @Date       : 2016. 04
 * @변경이력    :
 * @프로그램 설명 : 강의 이벤트(코너) 관리
 */
@RequestMapping(value="/LecEventMng")
@Controller
public class LecEventMngController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private LecEventMngService  LecEventMngService;

	@Autowired
	private TeacherService teacherservice;
	@Autowired
	private BookService bookservice;
	@Autowired
	private LectureService lectureservice;
	
	@Autowired
    private ProductOrderService productOrderService;

	@Autowired
    private AdminManagementCodeService codeService;
	
	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;

	@RequestMapping(value="/eventMgtList.do")
	public String eventManagementList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> list = null;
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

		//강의 이벤트 리스트
		params.put("ONOFF_DIV", "L");
		list = LecEventMngService.getEventList(params);
		int listCount = LecEventMngService.getEventListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/eventManagement/lecEventList";
	}

	@RequestMapping(value="/eventWrite.do")
	public String eventInsertView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("SEARCHGUBN", "T");

		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("params",params);
 		return "/eventManagement/lecEventWrite";
	}

	@RequestMapping(value="/eventInsertProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("START_DATE", CommonUtil.isNull(request.getParameter("START_DATE"), ""));
		params.put("END_DATE", CommonUtil.isNull(request.getParameter("END_DATE"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("CONTENTS", CommonUtil.isNull(request.getParameter("CONTENTS"), ""));
		
		LecEventMngService.eventInsertProcess(params);

		model.addAttribute("params",params);
		
 		return "forward:/LecEventMng/eventMgtList.do";
	}

	@RequestMapping(value="/eventDetail.do")
	public String eventDetail(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		HashMap<String, Object> view = LecEventMngService.eventDetail(params);
		List<HashMap<String, String>> list = LecEventMngService.getEventlectureList(params);

		model.addAttribute("view", view);
		model.addAttribute("list", list);
		model.addAttribute("params",params);

		return "/eventManagement/lecEventEdit";
	}

	@RequestMapping(value="/eventUpdateProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"), ""));
		params.put("START_DATE", CommonUtil.isNull(request.getParameter("START_DATE"), ""));
		params.put("END_DATE", CommonUtil.isNull(request.getParameter("END_DATE"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("CONTENTS", CommonUtil.isNull(request.getParameter("CONTENTS"), ""));
		
		LecEventMngService.eventUpdateProcess(params);

		model.addAttribute("params",params);
 		return "forward:/LecEventMng/eventMgtList.do";
	}

	@RequestMapping(value="/eventView.pop")
	public String eventView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		HashMap<String, Object> view = LecEventMngService.eventDetail(params);
		List<HashMap<String, String>> list = LecEventMngService.getEventlectureList(params);

		model.addAttribute("view", view);
		model.addAttribute("list", list);
		model.addAttribute("params",params);
 		return "/eventManagement/lecEventView_pop";
	}

	@RequestMapping(value="/lectureList.do")
	public String lectureList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		HashMap<String, Object> view = LecEventMngService.eventDetail(params);
		List<HashMap<String, String>> list = LecEventMngService.getEventlectureList(params);

		model.addAttribute("view", view);
		model.addAttribute("list", list);
		model.addAttribute("params",params);

		return "/eventManagement/lectureList";
	}

	@RequestMapping(value="/lectureWrite.pop")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String lectureWrite(ModelMap model, HttpServletRequest request) throws Exception {

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

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");

		List<HashMap<String, String>> lec_list = LecEventMngService.getEventlectureList(params);

		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = LecEventMngService.lectureList(params);
		int listCount = LecEventMngService.lectureListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("lec_list", lec_list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/eventManagement/lectureWrite_pop";
	}

	@RequestMapping(value="/eventLectureInsertProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventLectureInsertProcess(ModelMap model, HttpServletRequest request, HttpServletRequest Request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String EVENT_NO = CommonUtil.isNull(request.getParameter("EVENT_NO"), "");
		String[] LEC_ARR = request.getParameterValues("LEC_ARR");

		params.put("EVENT_NO", EVENT_NO);
		/*이벤트 게시판 등록*/
		if(LEC_ARR != null){
			for(int i=0; i<LEC_ARR.length; i++){
				params.put("LECCODE", LEC_ARR[i].toString());
				//insert문
				LecEventMngService.insertEventLecture(params);
			}
		}

		model.addAttribute("params",params);
 		return "redirect:/LecEventMng/lectureWrite.pop";
	}

	@RequestMapping(value="/eventLecturedeleteProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventLecturedeleteProcess(ModelMap model, HttpServletRequest request, HttpServletRequest Request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String EVENT_NO = CommonUtil.isNull(request.getParameter("EVENT_NO"), "");
		String[] DEL_ARR = request.getParameterValues("DEL_ARR");

		params.put("EVENT_NO", EVENT_NO);
		/*이벤트 게시판 등록*/
		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				params.put("LECCODE", DEL_ARR[i].toString());
				//insert문
				LecEventMngService.deleteEventLecture(params);
			}
		}

		model.addAttribute("params",params);
 		return "redirect:/LecEventMng/lectureList.do?EVENT_NO="+EVENT_NO;
	}
	
	/**
	 * @Method Name  : reboundEventList
	 * @Date         : 2016. 11
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      :	리바운드 환승이벤트
	 * @param model
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/reboundEventList.do")
	public String reboundEventList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		
		String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);
        
        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("eCode"), "")); //인증이벤트 구분
        params.put("ARM_EVENT", CommonUtil.isNull(request.getParameter("ARM_EVENT"), "N")); //제대군인 이벤트 분류
        params.put("LECCODE_ARR", CommonUtil.isNull(request.getParameter("LECCODE_ARR"), ""));

        HashMap<String, Object> oparam = new  HashMap<String, Object>();
		oparam.put("SYS_CD", "LEC_EVENT");
		List<HashMap<String, Object>> eventList = codeService.getBaConfigCodeList(oparam);

		if ("".equals(params.get("EVENT_NO"))){
			HashMap<String, Object> eventView = eventList.get(0);
			params.put("EVENT_NO", eventView.get("CODE_VAL").toString());
		}
        
		/* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = LecEventMngService.getReboundEventList(params);
        int listCount = LecEventMngService.getReboundEventListCount(params);

    	String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("eventList", eventList);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("params", params);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/eventManagement/reboundEvent";
	}
	
	/**
     * @Method Name : excelDownload
     * @작성일 : 2016. 11.
     * @Method 설명 : 리바운드 환승이벤트 엑셀 다운로드
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
        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"), ""));
        params.put("ARM_EVENT", CommonUtil.isNull(request.getParameter("ARM_EVENT"), "N")); //제대군인 이벤트 분류
        params.put("LECCODE_ARR", CommonUtil.isNull(request.getParameter("LECCODE_ARR"), ""));
        List<HashMap<String, String>> list = null;
        if(params.get("LECCODE_ARR")!=null&&!"".equals(params.get("LECCODE_ARR"))){
        	String[] lecarr = params.get("LECCODE_ARR").toString().split("_");
        	if(lecarr!=null&&lecarr.length>0){
        		String LECCODE = "";
        		for(int i=0;i<lecarr.length;i++){
        			if(i==0){
        				LECCODE += "'"+lecarr[i]+"'";
        			}else{
        				LECCODE += ",'"+lecarr[i]+"'";
        			}
        		}
        		params.put("LECCODE", LECCODE);
        		list = LecEventMngService.getReboundEventList(params);
        	}
        }
        
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String fileTitle = "";
        if("Y".equals(params.get("ARM_EVENT"))){
        	fileTitle = "_제대인증이벤트명단";
        }else{
        	fileTitle = "_리바운드이벤트명단";
        }
        String excelName = simpleDate.format(date) + fileTitle;
        List<String> headerList = new ArrayList<String>();
        headerList.add("아이디");
        headerList.add("이름");
        headerList.add("전화번호");
        if("Y".equals(params.get("ARM_EVENT"))){
        	headerList.add("군무 기관명");
    		headerList.add("군별");
    		headerList.add("계급");
    		headerList.add("군번");
        }else{
        	headerList.add("타사명");
        }
		headerList.add("파일");
		headerList.add("등록일");
        headerList.add("승인여부");	 
        headerList.add("구매여부");
        List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> map : list) {
            HashMap newMap = new HashMap();
            int i = 0;
            if(null != map.get("USER_ID") && !"".equals(map.get("USER_ID"))) {
                newMap.put(i++, map.get("USER_ID").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("USER_NAME") && !"".equals(map.get("USER_NAME"))) {
                newMap.put(i++, map.get("USER_NAME").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("PHONE_NO") && !"".equals(map.get("PHONE_NO"))) {
                newMap.put(i++, map.get("PHONE_NO").toString());
            } else {
                newMap.put(i++, "");
            }
            if("Y".equals(params.get("ARM_EVENT"))){
            	if(null != map.get("ARM_NM") && !"".equals(map.get("ARM_NM"))) {
                    newMap.put(i++, map.get("ARM_NM").toString());
                } else {
                    newMap.put(i++, "");
                }
            	if(null != map.get("ARM_DIV") && !"".equals(map.get("ARM_DIV"))) {
                    newMap.put(i++, map.get("ARM_DIV").toString());
                } else {
                    newMap.put(i++, "");
                }
            	if(null != map.get("ARM_RANK") && !"".equals(map.get("ARM_RANK"))) {
                    newMap.put(i++, map.get("ARM_RANK").toString());
                } else {
                    newMap.put(i++, "");
                }
            	if(null != map.get("ARM_NO") && !"".equals(map.get("ARM_NO"))) {
                    newMap.put(i++, map.get("ARM_NO").toString());
                } else {
                    newMap.put(i++, "");
                }
            }else{
            	if(null != map.get("EVENT_TXT") && !"".equals(map.get("EVENT_TXT"))) {
                    newMap.put(i++, map.get("EVENT_TXT").toString());
                } else {
                    newMap.put(i++, "");
                }
            }
            if(null != map.get("FILE_PATH") && !"".equals(map.get("FILE_PATH"))) {
                newMap.put(i++, map.get("FILE_PATH").toString()+"");
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("REG_DD") && !"".equals(map.get("REG_DD"))) {
                newMap.put(i++, map.get("REG_DD").toString()+"");
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("IS_CHK") && !"".equals(map.get("IS_CHK"))) {
                newMap.put(i++, map.get("IS_CHK").toString());
            } else {
                newMap.put(i++, "");
            }
            if(null != map.get("IS_BUY") && !"".equals(map.get("IS_BUY"))) {
                newMap.put(i++, String.valueOf(map.get("IS_BUY")));
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
     * @Method Name : setReboundChk
     * @작성일 : 2016. 11.
     * @Method 설명 : 리바운드 환승이벤트 인증 유무 체크
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/update_rebound_chk.json")
    @ResponseBody
    public String setReboundChk(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);
        String returnMsg = "N";
        params.put("EVENT_USER_ID", CommonUtil.isNull(request.getParameter("EVENT_USER_ID"), ""));
        params.put("IS_CHK", CommonUtil.isNull(request.getParameter("IS_CHK"), ""));
        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"), ""));
        params.put("ARM_EVENT", CommonUtil.isNull(request.getParameter("ARM_EVENT"), "N")); //제대군인 이벤트 분류
        String phone_no = CommonUtil.isNull(request.getParameter("PHONE_NO"), ""); //받는 사람 전화번호
    	String send_no = CommonUtil.isNull(request.getParameter("SEND_NO"), ""); //보내는 사람 전화번호
    	String USER_NAME = CommonUtil.isNull(request.getParameter("USER_NAME"), ""); 
        if(!"".equals(params.get("EVENT_USER_ID"))
        		&&!"".equals(params.get("IS_CHK"))&&!"".equals(params.get("EVENT_NO"))){
        	if(!"Y".equals(params.get("ARM_EVENT"))){
	        	String message = "[윌비스 신광은 경찰]\n";
	        	if("Y".equals(params.get("IS_CHK"))){
	        		message += "리바운드 회원 인증이 완료 되었습니다.\n";
	        		message+="http://bit.ly/2fA1H3n";
	        	}else if("N".equals(params.get("IS_CHK"))){
	        		message += "리바운드 회원 인증 보류안내 \n";
	        		message+="자세한 사항은 고객센터로 문의 바랍니다";
	        	}
	    		params.put("message", message);
	    		params.put("phone", phone_no);
	    		params.put("send_no", send_no);
	    		productOrderService.insertSendMsgMultiSendUser(params);
        	}
    		LecEventMngService.updateReboundEventChk(params);
    		returnMsg = "Y";
        }
        return returnMsg;
    }
    
    @RequestMapping(value="/rebound.pop")
	public String reboundPop(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("EVENT_USER_ID", CommonUtil.isNull(request.getParameter("EVENT_USER_ID"), ""));
        params.put("IS_CHK", CommonUtil.isNull(request.getParameter("IS_CHK"), ""));
        params.put("FILE_PATH", CommonUtil.isNull(request.getParameter("FILE_PATH"),""));
        params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), "")); //받는 사람 전화번호
        params.put("SEND_NO", CommonUtil.isNull(request.getParameter("SEND_NO"), "")); //보내는 사람 전화번호
        params.put("USER_NAME", CommonUtil.isNull(request.getParameter("USER_NAME"), "")); 

		model.addAttribute("params",params);
		return "/eventManagement/reboundEvent_pop";
	}
	
	//인증이벤트 엑셀다운로드
	@RequestMapping(value="/LecEventExcel.do")
	public View LecEventExcel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String searchStartDate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String searchEndDate = CommonUtil.isNull(request.getParameter("EDATE"), "");
        params.put("SDATE", searchStartDate);
        params.put("EDATE", searchEndDate);

        params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("eCode"), "")); //인증이벤트 구분
        params.put("ARM_EVENT", CommonUtil.isNull(request.getParameter("ARM_EVENT"), "N")); //제대군인 이벤트 분류
        
		HashMap<String, Object> oparam = new  HashMap<String, Object>();
		oparam.put("SYS_CD", "LEC_EVENT");
		List<HashMap<String, Object>> eventList = codeService.getBaConfigCodeList(oparam);
		
        if (eventList.size() > 0) {
            for (int k = 0; k < eventList.size(); k++) {
                HashMap map = (HashMap) eventList.get(k);
        		if (map.get("CODE_VAL").toString().equals(params.get("EVENT_NO"))){
        			params.put("CODE_NM", map.get("CODE_NM").toString());
        		}
            }
        }

		/* 페이징 */
        int startNo = 0;
        int endNo = startNo + 2000;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */
        List<HashMap<String, String>> list = LecEventMngService.getReboundEventList(params);
        
        model.addAttribute("list", list);
        model.addAttribute("nm", params.get("CODE_NM"));
        model.addAttribute("cd", params.get("EVENT_NO"));

		return new LecEventExcel();

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
	public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("REG_NM",loginInfo.get("USER_NM"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD", CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID")));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
		
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHISUSE", CommonUtil.isNull(request.getParameter("SEARCHISUSE"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
	}
}
