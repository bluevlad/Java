package com.willbes.web.lecture;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

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

import com.willbes.cmm.service.CmmUseService;
import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.book.service.BookService;
import com.willbes.web.lecture.service.OpenLectureService;
import com.willbes.web.lecture.service.SubjectService;
import com.willbes.web.lecture.service.TeacherService;
import com.willbes.web.productOrder.service.ProductOrderService;

@RequestMapping(value="/openlecture")
@Controller
public class OpenLectureController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;
	@Autowired
	private BookService bookservice;
	@Autowired
	private OpenLectureService openlectureservice;
	@Autowired
	private TeacherService teacherservice;
    @Resource(name = "CmmUseService")
    private CmmUseService cmmUseService;
    @Autowired
	private SubjectService subjectservice;

	@Autowired
	private ProductOrderService productOrderService;

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 목록
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
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = openlectureservice.openlectureList(params);
		int listCount = openlectureservice.openlectureListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/openlecture/list";
	}




	/**
	 * @Method Name : write
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 등록폼
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
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		params.put("startNo", "1");
		params.put("endNo", "10000");
		params.put("ISUSE", "Y");
		
		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(params);
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(params);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);
        model.addAttribute("ICON_GUBUNs", ICON_GUBUNs);
        
        params.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(params);//공통코드 채널값 가져오기 위함

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectlist", subjectlist);
		model.addAttribute("teacherlist", teacherlist);
		model.addAttribute("open_code_commoncode", open_code_commoncode);
		model.addAttribute("params", params);
		return "/lecture/openlecture/write";
	}

	/**
	 * @Method Name : bookList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 교재 목록 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bookList.pop")
	public String bookList(ModelMap model, HttpServletRequest request) throws Exception {
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
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = openlectureservice.bookList(params);
		int listCount = openlectureservice.bookListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/openlecture/bookList_pop";
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/save.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String save(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);
		Calendar cal = Calendar.getInstance( );
		String BRIDGE_LECCODE = "";
		String LECCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeLeccodeSeqList = openlectureservice.getBridgeLeccodeSeq(params);			// 브릿지테이블 SEQ 구하기
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "O" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getLeccodeList = openlectureservice.getopenLeccode(params);
		if(getLeccodeList.size() > 0)
			LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("OPENLECCODE");
		else
			LECCODE = params.get("PREFIX") + "00001";
		params.put("OPENLECCODE", LECCODE.replace(" ", ""));
		
		openlectureservice.openlectureInsert(params);
		
		return "redirect:/openlecture/list.do";
	}

	/**
	 * @Method Name : modify
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 수정폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/modify.do")
	public String modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> view = openlectureservice.openlectureView(params);
//		List<HashMap<String, String>> viewlist = openlectureservice.openlectureViewList(params);
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		params.put("startNo", "1");
		params.put("endNo", "10000");
		params.put("ISUSE", "Y");
		
		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(params);
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(params);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);
        model.addAttribute("ICON_GUBUNs", ICON_GUBUNs);
        
        params.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(params);//공통코드 채널값 가져오기 위함

//		model.addAttribute("lectureOrderCount", lectureOrderCount);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("subjectlist", subjectlist);
		model.addAttribute("open_code_commoncode", open_code_commoncode);
		model.addAttribute("teacherlist", teacherlist);
		model.addAttribute("view", view);
//		model.addAttribute("viewlist", viewlist);
//		model.addAttribute("viewbooklist", viewbooklist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lecture/openlecture/modify";
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/update.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String update(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String rootPath = fsResource.getPath();
		lecFileProcess(params, multipartRequest);
		if("Y".equals(params.get("OPEN_FILE_DEL")) || !"".equals(params.get("OPEN_FILE")))
			fileUtil.deleteFile(rootPath + params.get("OPEN_FILE_DELNM"));
		
		openlectureservice.openlectureUpdate(params);
		

		return "forward:/openlecture/list.do";
	}







	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/delete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		openlectureservice.openlectureDelete(params);
		openlectureservice.openlectureBridgeDelete(params);
		openlectureservice.openlectureBookDelete(params);
		return "forward:/lecture/list.do";
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
	public HashMap<String, String> lecFileProcess(HashMap<String, String> params, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = fsResource.getPath();
		String subPath = "openlecture_upload/";
		
		MultipartFile OPEN_FILE = multipartRequest.getFile("OPEN_FILE");
		
		if(OPEN_FILE != null && OPEN_FILE.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(OPEN_FILE, rootPath, subPath);
			params.put("OPEN_FILE", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100); 
		}	
		
	
		return params;
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
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), "15"));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHPAYYN", CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("OPENLECCODE", CommonUtil.isNull(request.getParameter("OPENLECCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("OPENBUNRU", CommonUtil.isNull(request.getParameter("OPENBUNRU"), ""));
		params.put("OPEN_TITLE", CommonUtil.isNull(request.getParameter("OPEN_TITLE"), ""));
		params.put("OPEN_SJT_CD", CommonUtil.isNull(request.getParameter("OPEN_SJT_CD"), ""));
		params.put("OPEN_TEACHER", CommonUtil.isNull(request.getParameter("OPEN_TEACHER"), ""));
		params.put("OPEN_MEMO", CommonUtil.isNull(request.getParameter("OPEN_MEMO"), ""));
		params.put("OPEN_DESC", CommonUtil.isNull(request.getParameter("OPEN_DESC"), ""));
		params.put("OPEN_HIMOVIE_PATH", CommonUtil.isNull(request.getParameter("OPEN_HIMOVIE_PATH"), ""));
		params.put("OPEN_NOMALMOVIE_PATH", CommonUtil.isNull(request.getParameter("OPEN_NOMALMOVIE_PATH"), ""));
		params.put("OPEN_FILE", CommonUtil.isNull(request.getParameter("OPEN_FILE"), ""));
		params.put("OPEN_ISUSE", CommonUtil.isNull(request.getParameter("OPEN_ISUSE"), ""));
		params.put("OPEN_PASSWORD", CommonUtil.isNull(request.getParameter("OPEN_PASSWORD"), ""));
		params.put("OPEN_POINT", CommonUtil.isNull(request.getParameter("OPEN_POINT"), ""));
		params.put("OPEN_HIT", CommonUtil.isNull(request.getParameter("OPEN_HIT"), "0"));

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

}
