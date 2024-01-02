package com.willbes.web.lectureOff;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.property.EgovPropertyService;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.MafUtil;
import com.willbes.platform.util.file.FileUtil;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.book.service.BookService;
import com.willbes.web.lectureOff.service.LectureOffService;
import com.willbes.web.lecture.service.TeacherService;
import com.willbes.platform.util.excel.ExcelDownloadView;

import com.willbes.web.excel.LecEventExcel;
import com.willbes.web.excel.LectureOffListExcel;

@RequestMapping(value="/lectureOff")
@Controller
public class LectureOffController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;
	@Autowired
	private BookService bookservice;
	@Autowired
	private LectureOffService lectureoffservice;
	@Autowired
	private TeacherService teacherservice;

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

		String today =  CommonUtil.getCurrentDate().substring(0,4) + "-" + CommonUtil.getCurrentDate().substring(4,6) + "-" + CommonUtil.getCurrentDate().substring(6,8);
		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = lectureoffservice.lectureList(params);
		int listCount = lectureoffservice.lectureListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("today", today);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lectureOff/list";
	}
	
	//강의 엑셀다운로드
	@RequestMapping(value="/LectureOffListExcel.do")
	public View LectureOffListExcel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("SEARCHGUBN", "T");
		params.put("SEARCHCODEISUSE", "Y");
		
		/* 페이징 */
        int startNo = 0;
        int endNo = startNo + 10000;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = lectureoffservice.lectureList(params);
        
        model.addAttribute("list", list);

		return new LectureOffListExcel();
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
	@RequestMapping(value="/multiUpdateUsg.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String updateMultiSubjectUsage(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String[] BRIDGE_LEC_ARR = request.getParameterValues("BRIDGE_LEC_ARR");
		String[] JONGSEQ_ARR = request.getParameterValues("JONGSEQ_ARR");
		params.put("SUBJECT_ISUSE", CommonUtil.isNull(request.getParameter("SUBJECT_ISUSE"), "Y"));
		String returnUrl = "";

		if(BRIDGE_LEC_ARR != null){
			for(int k=0; k<BRIDGE_LEC_ARR.length; k++){																		// 강의날짜 넣기
				if(!"".equals(BRIDGE_LEC_ARR[k]) && null != BRIDGE_LEC_ARR[k]){
					params.put("BRIDGE_LECCODES", BRIDGE_LEC_ARR[k]);
					lectureoffservice.lectureIsUseUpdate(params);
				}
			}
			returnUrl = "forward:/lectureOff/list.do";
		} else if (JONGSEQ_ARR != null){
			for(int k=0; k<JONGSEQ_ARR.length; k++){																		// 강의날짜 넣기
				if(!"".equals(JONGSEQ_ARR[k]) && null != JONGSEQ_ARR[k]){
					params.put("JONGSEQ_ARR", JONGSEQ_ARR[k]);
					lectureoffservice.lectureIsUseUpdate(params);
				}
			}
			returnUrl = "forward:/lectureOff/jongList.do";
		}
		returnUrl = CommonUtil.isNull(returnUrl, "forward:/lectureOff/list.do");
		return returnUrl;
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
	@RequestMapping(value="/multiUpdateProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String multiUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String[] BRIDGE_LEC_ARR = request.getParameterValues("BRIDGE_LEC_ARR");
		params.put("LEC_PROCESS", CommonUtil.isNull(request.getParameter("LEC_PROCESS"), "Y"));
		String returnUrl = "";

		for(int k=0; k<BRIDGE_LEC_ARR.length; k++){																		// 강의날짜 넣기
			if(!"".equals(BRIDGE_LEC_ARR[k]) && null != BRIDGE_LEC_ARR[k]){
				params.put("BRIDGE_LECCODES", BRIDGE_LEC_ARR[k]);
				lectureoffservice.lectureIsUseUpdate(params);
			}
		}

		returnUrl = "forward:/lectureOff/list.do";

		return returnUrl;
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
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("params", params);
		return "/lectureOff/write";
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
		List<HashMap<String, String>> list = lectureoffservice.bookList(params);
		int listCount = lectureoffservice.bookListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lectureOff/bookList_pop";
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
		String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
		String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String[] SAVDAY_ARR = request.getParameterValues("SAVDAY");
		String BRIDGE_LECCODE = "";
		String LECCODE = "";
		String SEQ = "";
		int RETURNSEQ = 0;

		List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureoffservice.getBridgeLeccodeSeq(params);			// 브릿지테이블 SEQ 구하기
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "R" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getBridgeLeccodeList = lectureoffservice.getBridgeLeccode(params);						// 브릿지테이블 BRIDGE_LECCODE 구하기

		if(getBridgeLeccodeList.size() > 0)
			BRIDGE_LECCODE = params.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
		else
			BRIDGE_LECCODE = params.get("PREFIX") + "00001";
		params.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

		params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() + cal.get(Calendar.YEAR));
		for(int i=0; i<CATEGORY_CD_ARR.length; i++){																					// 직종 루프
			params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);

			for(int j=0; j<LEARNING_CD_ARR.length; j++){																					// 학습형태 루프
				params.put("LEARNING_CD", LEARNING_CD_ARR[j]);

					List<HashMap<String, String>> getLeccodeList = lectureoffservice.getLeccode(params);
					if(getLeccodeList.size() > 0)
						LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
					else
						LECCODE = params.get("PREFIX") + "00001";
					params.put("LECCODE", LECCODE.replace(" ", ""));

					lectureoffservice.lectureInsert(params);																												// TB_LEC_MST (INSERT)
					params.put("SEQ", SEQ);
					lectureoffservice.lectureBridgeInsert(params);																										// TB_LEC_BRIDGE (INSERT)

					if(SAVDAY_ARR != null){
						int KNUM = 0;
						for(int k=0; k<SAVDAY_ARR.length; k++){																			// 강의날짜 넣기
							if(!"".equals(SAVDAY_ARR[k]) && null != SAVDAY_ARR[k]){
								KNUM = KNUM+1;
								params.put("NUM" , String.valueOf(KNUM));
								params.put("LEC_DATE", SAVDAY_ARR[k]);
								lectureoffservice.lectureOffDayInsert2(params);
							}
						}
					}

					if(JU_RSC_ID_ARR != null){
						for(int k=0; k<JU_RSC_ID_ARR.length; k++){																		// 주교재 루프
							params.put("RSC_ID", JU_RSC_ID_ARR[k]);
							params.put("FLAG","J");
							params.put("SUBJECT_BOOK_MEMO", request.getParameter("SUBJECT_BOOK_MEMO_"+JU_RSC_ID_ARR[k]));
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(BU_RSC_ID_ARR != null){
						for(int k=0; k<BU_RSC_ID_ARR.length; k++){																		// 부교재 루프
							params.put("RSC_ID", BU_RSC_ID_ARR[k]);
							params.put("FLAG","B");
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(SU_RSC_ID_ARR != null){
						for(int k=0; k<SU_RSC_ID_ARR.length; k++){																		// 수강생교재 루프
							params.put("RSC_ID", SU_RSC_ID_ARR[k]);
							params.put("FLAG","S");
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
			}
		}
		return "redirect:/lectureOff/list.do?LEC_TYPE_CHOICE=" + params.get("LEC_TYPE_CHOICE");
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

		List<HashMap<String, String>> view = lectureoffservice.lectureView(params);
		List<HashMap<String, String>> viewlist = lectureoffservice.lectureViewList(params);
		List<HashMap<String, String>> viewbooklist = lectureoffservice.lectureViewBookList(params);
		List<HashMap<String, String>> viewdatelist = lectureoffservice.lectureOffDayList(params);
		HashMap<String, String> vparams = new  HashMap<String, String>();
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		int lectureOrderCount = lectureoffservice.lectureDeleteCheck(params);

		model.addAttribute("lectureOrderCount", lectureOrderCount);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("view", view);
		model.addAttribute("viewlist", viewlist);
		model.addAttribute("viewbooklist", viewbooklist);
		model.addAttribute("viewdatelist", viewdatelist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lectureOff/modify";
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
		lecFileProcess(params, multipartRequest);
		params.put("BRIDGE_LEC", CommonUtil.isNull(request.getParameter("BRIDGE_LEC"), ""));
		params.put("UPDATE_FLAG", CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
		lectureoffservice.lectureBookDelete(params);
		lectureoffservice.lectureOffDayDelete(params);
		lectureoffservice.lectureUpdate(params);

		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String[] SAVDAY_ARR = request.getParameterValues("SAVDAY");

		if(SAVDAY_ARR != null){
			int KNUM = 0;
			for(int k=0; k<SAVDAY_ARR.length; k++){																		// 강의날짜 넣기
				if(!"".equals(SAVDAY_ARR[k]) && null != SAVDAY_ARR[k]){
					KNUM = KNUM+1;
					params.put("NUM" , String.valueOf(KNUM));
					params.put("LEC_DATE", SAVDAY_ARR[k]);
					if(params.get("UPDATE_FLAG").equals("ALL")){
						lectureoffservice.lectureOffDayInsert(params);
					}else{
						lectureoffservice.lectureOffDayInsert2(params);
					}
				}
			}
		}

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){																	// 주교재 루프
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				params.put("SUBJECT_BOOK_MEMO", request.getParameter("SUBJECT_BOOK_MEMO_"+JU_RSC_ID_ARR[k]));
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureoffservice.lectureBookInsert(params);
				}else{
					lectureoffservice.lectureBookInsert2(params);
				}
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){																	// 부교재 루프
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureoffservice.lectureBookInsert(params);
				}else{
					lectureoffservice.lectureBookInsert2(params);
				}
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){																	// 수강생교재 루프
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureoffservice.lectureBookInsert(params);
				}else{
					lectureoffservice.lectureBookInsert2(params);
				}
			}
		}
		return "forward:/lectureOff/list.do";
	}

	/**
	 * @Method Name : copyModify
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 복제폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/copy.do")
	public String copyModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> view = lectureoffservice.lectureView(params);
		List<HashMap<String, String>> viewlist = lectureoffservice.lectureViewList(params);
		List<HashMap<String, String>> viewbooklist = lectureoffservice.lectureViewBookList(params);
		List<HashMap<String, String>> viewdatelist = lectureoffservice.lectureOffDayList(params);
		HashMap<String, String> vparams = new  HashMap<String, String>();
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("view", view);
		model.addAttribute("viewlist", viewlist);
		model.addAttribute("viewbooklist", viewbooklist);
		model.addAttribute("viewdatelist", viewdatelist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lectureOff/copy";
	}

	/**
	 * @Method Name : newCopyModify
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 복제폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/newCopy.do")
	public String newCopyModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> view = lectureoffservice.lectureView(params);
		List<HashMap<String, String>> viewlist = lectureoffservice.lectureViewList(params);
		List<HashMap<String, String>> viewbooklist = lectureoffservice.lectureViewBookList(params);
		List<HashMap<String, String>> viewdatelist = lectureoffservice.lectureOffDayList(params);
		HashMap<String, String> vparams = new  HashMap<String, String>();
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("view", view);
		model.addAttribute("viewlist", viewlist);
		model.addAttribute("viewbooklist", viewbooklist);
		model.addAttribute("viewdatelist", viewdatelist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lectureOff/newCopy";
	}

	/**
	 * @Method Name : copySave
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 복제 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/copySave.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String copySave(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);
		if("".equals(params.get("SUBJECT_SUMNAIL"))){
			if(!"".equals(params.get("AS_SUBJECT_SUMNAIL"))){
				params.put("SUBJECT_SUMNAIL", params.get("AS_SUBJECT_SUMNAIL"));
			}
		}
		Calendar cal = Calendar.getInstance( );
		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String[] SAVDAY_ARR = request.getParameterValues("SAVDAY");
		String LECCODE = "";

		params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getLeccodeList = lectureoffservice.getLeccode(params);
		if(getLeccodeList.size() > 0)
			LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
		else
			LECCODE = params.get("PREFIX") + "00001";
		params.put("LECCODE", LECCODE.replace(" ", ""));

		lectureoffservice.lectureInsert(params);																												// TB_LEC_MST (INSERT)
		lectureoffservice.lectureBridgeInsert(params);																										// TB_LEC_BRIDGE (INSERT)

		if(SAVDAY_ARR != null){
			int KNUM = 0;
			for(int k=0; k<SAVDAY_ARR.length; k++){																					// 강의날짜 넣기
				if(!"".equals(SAVDAY_ARR[k]) && null != SAVDAY_ARR[k]){
					KNUM = KNUM+1;
					params.put("NUM" , String.valueOf(KNUM));
					params.put("LEC_DATE", SAVDAY_ARR[k]);
					lectureoffservice.lectureOffDayInsert2(params);
				}
			}
		}

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){																				// 주교재 루프
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){																				// 부교재 루프
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){																				// 수강생교재 루프
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
			}
		}
		return "redirect:/lectureOff/list.do?LEC_TYPE_CHOICE=" + params.get("LEC_TYPE_CHOICE");
	}

	/**
	 * @Method Name : newCopySave
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 신규복제 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/newCopySave.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String newCopySave(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);
		if("".equals(params.get("SUBJECT_SUMNAIL"))){
			if(!"".equals(params.get("AS_SUBJECT_SUMNAIL"))){
				params.put("SUBJECT_SUMNAIL", params.get("AS_SUBJECT_SUMNAIL"));
			}
		}
		Calendar cal = Calendar.getInstance( );
		String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
		String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String[] SAVDAY_ARR = request.getParameterValues("SAVDAY");
		String BRIDGE_LECCODE = "";
		String LECCODE = "";
		String SEQ = "";
		int RETURNSEQ = 0;

		List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureoffservice.getBridgeLeccodeSeq(params);			// 브릿지테이블 SEQ 구하기
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "R" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getBridgeLeccodeList = lectureoffservice.getBridgeLeccode(params);						// 브릿지테이블 BRIDGE_LECCODE 구하기

		if(getBridgeLeccodeList.size() > 0)
			BRIDGE_LECCODE = params.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
		else
			BRIDGE_LECCODE = params.get("PREFIX") + "00001";
		params.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

		params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() + cal.get(Calendar.YEAR));
		for(int i=0; i<CATEGORY_CD_ARR.length; i++){																					// 직종 루프
			params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);

			for(int j=0; j<LEARNING_CD_ARR.length; j++){																					// 학습형태 루프
				params.put("LEARNING_CD", LEARNING_CD_ARR[j]);

					List<HashMap<String, String>> getLeccodeList = lectureoffservice.getLeccode(params);
					if(getLeccodeList.size() > 0)
						LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
					else
						LECCODE = params.get("PREFIX") + "00001";
					params.put("LECCODE", LECCODE.replace(" ", ""));

					lectureoffservice.lectureInsert(params);																												// TB_LEC_MST (INSERT)
					params.put("SEQ", SEQ);
					lectureoffservice.lectureBridgeInsert(params);																										// TB_LEC_BRIDGE (INSERT)

					if(SAVDAY_ARR != null){
						int KNUM = 0;
						for(int k=0; k<SAVDAY_ARR.length; k++){																			// 강의날짜 넣기
							if(!"".equals(SAVDAY_ARR[k]) && null != SAVDAY_ARR[k]){
								KNUM = KNUM+1;
								params.put("NUM" , String.valueOf(KNUM));
								params.put("LEC_DATE", SAVDAY_ARR[k]);
								lectureoffservice.lectureOffDayInsert2(params);
							}
						}
					}

					if(JU_RSC_ID_ARR != null){
						for(int k=0; k<JU_RSC_ID_ARR.length; k++){																		// 주교재 루프
							params.put("RSC_ID", JU_RSC_ID_ARR[k]);
							params.put("FLAG","J");
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(BU_RSC_ID_ARR != null){
						for(int k=0; k<BU_RSC_ID_ARR.length; k++){																		// 부교재 루프
							params.put("RSC_ID", BU_RSC_ID_ARR[k]);
							params.put("FLAG","B");
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(SU_RSC_ID_ARR != null){
						for(int k=0; k<SU_RSC_ID_ARR.length; k++){																		// 수강생교재 루프
							params.put("RSC_ID", SU_RSC_ID_ARR[k]);
							params.put("FLAG","S");
							lectureoffservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
			}
		}
		return "redirect:/lectureOff/list.do?LEC_TYPE_CHOICE=" + params.get("LEC_TYPE_CHOICE");
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
		lectureoffservice.lectureDelete(params);
		lectureoffservice.lectureBridgeDelete(params);
		lectureoffservice.lectureOffDayDelete(params);
		lectureoffservice.lectureBookDelete(params);
		return "forward:/lectureOff/list.do";
	}

	/**
	 * @Method Name : seqList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 순서변경 목록 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/seqList.pop")
	public String seqList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = lectureoffservice.lectureSeqList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		return "/lectureOff/seqList";
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 순번 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/seqUpdate.pop")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String seqUpdate(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		String[] SEQARR = request.getParameterValues("SEQ");
		String[] LECCODEARR = request.getParameterValues("LECCODE");
		if(SEQARR != null){
			for(int i=0; i<SEQARR.length; i++){
				params.put("SEQ", SEQARR[i]);
				params.put("LECCODE", LECCODEARR[i]);
				lectureoffservice.lectureSeqUpdate(params);
			}
		}
		model.addAttribute("returnData","Y");;
		model.addAttribute("params", params);
		return "forward:/lectureOff/seqList.pop";
	}

	/**
	 * @Method Name : jongList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongList.do")
	public String jongList(ModelMap model, HttpServletRequest request) throws Exception {
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
		List<HashMap<String, String>> list = lectureoffservice.lectureJongList(params);
		int listCount = lectureoffservice.lectureJongListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lectureOff/jongList";
	}

	/**
	 * @Method Name : jongWrite
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 등록폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongWrite.do")
	public String jongWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("params", params);
		return "/lectureOff/jongWrite";
	}

	/**
	 * @Method Name : subjectList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 단과목록 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/subjectList.pop")
	public String subjectList(ModelMap model, HttpServletRequest request) throws Exception {
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
		List<HashMap<String, String>> list = lectureoffservice.lectureJongSubjectList(params);
		int listCount = lectureoffservice.lectureJongSubjectListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lectureOff/jongSubjectList_pop";
	}

    /**
     * @Method Name : searchLectureList
     * @작성일 : 2015. 07.
     * @Method 설명 : 단과목록 팝업
     * @param model
     * @param request
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/searchLecList.pop")
    public String searchLectureList(ModelMap model, HttpServletRequest request) throws Exception {
        HashMap<String, String> params = new  HashMap<String, String>();
        setParam(params, request, model);

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt("15");
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        params.put("SEARCHCODEISUSE", "Y");
        List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
        List<HashMap<String, String>> list = lectureoffservice.lectureJongSubjectList(params);
        int listCount = lectureoffservice.lectureJongSubjectListCount(params);
        String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

        model.addAttribute("kindlist", kindlist);
        model.addAttribute("formlist", formlist);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", listCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

        params.put("SRCHCODE", CommonUtil.isNull(request.getParameter("SRCHCODE"), ""));
        params.put("SRCHTXT", CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));

        model.addAttribute("params", params);

        return "/lectureOff/searchLectureList_pop";
    }

	/**
	 * @Method Name : jongSave
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 등록 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongSave.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String jongSave(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);
		Calendar cal = Calendar.getInstance( );
		String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
		String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
		String[] MST_LECCODE_ARR = request.getParameterValues("MST_LECCODE");
		String[] MST_LECCODE2_ARR = request.getParameterValues("MST_LECCODE2");
		String[] MST_LECCODE_SORT = request.getParameterValues("MST_LECCODE_SORT");
		String[] MST_LECCODE2_SORT = request.getParameterValues("MST_LECCODE2_SORT");
		String LECCODE = "";
		String MST_LECCODE = "";
		String SEQ = "";
		int RETURNSEQ = 0;

		List<HashMap<String, String>> getJongLeccodeSeqList = lectureoffservice.getJongLeccodeSeq(params);			// JONG 브릿지 SEQ 구하기
		if(getJongLeccodeSeqList.size() > 0){
			SEQ = getJongLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getJongLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}
		params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() + cal.get(Calendar.YEAR));
		for(int i=0; i<CATEGORY_CD_ARR.length; i++){
			params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);

			for(int j=0; j<LEARNING_CD_ARR.length; j++){
				params.put("LEARNING_CD", LEARNING_CD_ARR[j]);

					List<HashMap<String, String>> getLeccodeList = lectureoffservice.getLeccode(params);
					if(getLeccodeList.size() > 0)
						LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
					else
						LECCODE = params.get("PREFIX") + "00001";
					params.put("LECCODE", LECCODE.replace(" ", ""));

					lectureoffservice.lectureInsert(params);																				// TB_LEC_MST (INSERT)
					params.put("SEQ", SEQ);

					if("N".equals(params.get("LEC_TYPE_CHOICE"))){
						lectureoffservice.lectureChoiceJongNoInsert(params);															// TB_CHOICE_JONG_NO (INSERT)
					}

					if(MST_LECCODE_ARR != null){
						for(int k=0; k<MST_LECCODE_ARR.length; k++){
							params.put("MST_LECCODE", MST_LECCODE_ARR[k]);
							params.put("GUBUN","1");																						// 필수
							params.put("SORT", String.valueOf(k));
							if("N".equals(params.get("LEC_TYPE_CHOICE"))){
								params.put("SORT", MST_LECCODE_SORT[k]);
							}
							lectureoffservice.lectureLecJongInsert(params);																// TB_LEC_JONG (INSERT)
						}
					}

					if(MST_LECCODE2_ARR != null){
						for(int k=0; k<MST_LECCODE2_ARR.length; k++){
							params.put("MST_LECCODE", MST_LECCODE2_ARR[k]);
							params.put("GUBUN","2");																						// 선택
							params.put("SORT", String.valueOf(k));
							if("N".equals(params.get("LEC_TYPE_CHOICE"))){
								params.put("SORT", MST_LECCODE2_SORT[k]);
							}
							lectureoffservice.lectureLecJongInsert(params);																// TB_LEC_JONG (INSERT)
						}
					}

			}
		}
		return "redirect:/lectureOff/jongList.do?LEC_TYPE_CHOICE=" + params.get("LEC_TYPE_CHOICE");
	}

	/**
	 * @Method Name : jongModify
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 수정폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongModify.do")
	public String jongModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> view = lectureoffservice.lectureJongView(params);
		List<HashMap<String, String>> viewleccodelist = lectureoffservice.lectureViewLeccodeList(params);
		List<HashMap<String, String>> viewjonglist = lectureoffservice.lectureViewJongList(params);
		HashMap<String, String> vparams = new  HashMap<String, String>();
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		int lectureOrderCount = lectureoffservice.lectureDeleteCheck(params);

		model.addAttribute("lectureOrderCount", lectureOrderCount);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("view", view);
		model.addAttribute("viewleccodelist", viewleccodelist);
		model.addAttribute("viewjonglist", viewjonglist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lectureOff/jongModify";
	}

	/**
	 * @Method Name : jongUpdate
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 수정 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongUpdate.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String jongUpdate(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lecFileProcess(params, multipartRequest);
		Calendar cal = Calendar.getInstance( );
		String[] MST_LECCODE_ARR = request.getParameterValues("MST_LECCODE");
		String[] MST_LECCODE2_ARR = request.getParameterValues("MST_LECCODE2");
		String[] MST_LECCODE_SORT = request.getParameterValues("MST_LECCODE_SORT");
		String[] MST_LECCODE2_SORT = request.getParameterValues("MST_LECCODE2_SORT");

		lectureoffservice.lectureUpdate(params);
		lectureoffservice.lectureLecJongDelete(params);
		lectureoffservice.lectureChoiceJongNoDelete(params);

		if("N".equals(params.get("LEC_TYPE_CHOICE"))){
			lectureoffservice.lectureChoiceJongNoInsert(params);															// TB_CHOICE_JONG_NO (INSERT)
		}

		if(MST_LECCODE_ARR != null){
			for(int k=0; k<MST_LECCODE_ARR.length; k++){
				params.put("MST_LECCODE", MST_LECCODE_ARR[k]);
				params.put("GUBUN","1");																						// 필수
				params.put("SORT", String.valueOf(k));
				if("N".equals(params.get("LEC_TYPE_CHOICE"))){
					params.put("SORT", MST_LECCODE_SORT[k]);
				}
				lectureoffservice.lectureLecJongInsert(params);																// TB_LEC_JONG (INSERT)
			}
		}
		if(MST_LECCODE2_ARR != null){
			for(int k=0; k<MST_LECCODE2_ARR.length; k++){
				params.put("MST_LECCODE", MST_LECCODE2_ARR[k]);
				params.put("GUBUN","2");																						// 선택
				params.put("SORT", String.valueOf(k));
				if("N".equals(params.get("LEC_TYPE_CHOICE"))){
					params.put("SORT", MST_LECCODE2_SORT[k]);
				}
				lectureoffservice.lectureLecJongInsert(params);																// TB_LEC_JONG (INSERT)
			}
		}
		return "redirect:/lectureOff/jongList.do?LEC_TYPE_CHOICE=" + params.get("LEC_TYPE_CHOICE");
	}

	/**
	 * @Method Name : jongDelete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 강의 삭제 프로세스
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String jongDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		lectureoffservice.lectureDelete(params);
		lectureoffservice.lectureLecJongDelete(params);
		lectureoffservice.lectureChoiceJongNoDelete(params);
		return "forward:/lectureOff/jongList.do";
	}

	/**
	 * @Method Name : lecturePayList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 VOD유무/ PMP유무/ 동영상유무 클릭시 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/payList.pop")
	public String payList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
//		List<HashMap<String, String>> list = lectureoffservice.lecturePayList(params);
		params.put("LEC_TYPE", CommonUtil.isNull(request.getParameter("LEC_TYPE"), ""));
		List<HashMap<String, String>> list = lectureoffservice.off_lecturePayList(params);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		return "/lectureOff/payList_pop";
	}
	

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/payList_excel.do")
	public ExcelDownloadView excelDownload(ModelMap output, HttpServletRequest req) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, output);
		
		params.put("LEC_TYPE", CommonUtil.isNull(req.getParameter("LEC_TYPE"), ""));
		
		//엑셀 리스트
		List<HashMap<String, String>> exe_list =lectureoffservice.off_lecturePayList(params);

		

		List<String> headerList = new ArrayList<String>();

		headerList.add("수강자");
		headerList.add("아이디");
		headerList.add("연락처");
		headerList.add("과목명");
		headerList.add("수강금액");
		headerList.add("주문번호");
		headerList.add("주문구분");

		String 	excelName = "수강인원조회";

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			
			newMap.put(i++, CommonUtil.isNull(map.get("USER_NM")));
			newMap.put(i++, CommonUtil.isNull(map.get("USERID")));
			newMap.put(i++, CommonUtil.isNull(map.get("PHONE_NO")));
			newMap.put(i++, CommonUtil.isNull(map.get("SUBJECT_TITLE")));
			newMap.put(i++, CommonUtil.isNull(String.valueOf(map.get("REALPRICE"))));
			newMap.put(i++, CommonUtil.isNull(map.get("ORDERNO")));			
			newMap.put(i++, CommonUtil.isNull(map.get("PAYNM")));			

			newList.add(newMap);
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}
	
	/**
	 * @Method Name : lecturePayList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 VOD유무/ PMP유무/ 동영상유무 클릭시 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/selpayList.pop")
	public String selpayList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
//		List<HashMap<String, String>> list = lectureoffservice.lecturePayList(params);
		String BRIDGE_LECS_STR = CommonUtil.isNull(request.getParameter("BRIDGE_LECS_STR"), "");
		String real_lecs = "";
		if(BRIDGE_LECS_STR != null&&!BRIDGE_LECS_STR.equals("")){
			String[] LEC_ARR = BRIDGE_LECS_STR.split("_");
			if(LEC_ARR.length>0){
				for(int i=0;i<LEC_ARR.length;i++){
					if(i==0){
						real_lecs += "'"+LEC_ARR[i]+"'";
					}else{
						real_lecs += ",'"+LEC_ARR[i]+"'";
					}
				}
				params.put("BRIDGE_LECS", real_lecs);
				params.put("BRIDGE_LECS_STR", BRIDGE_LECS_STR);
				List<HashMap<String, String>> list = lectureoffservice.off_lecturePayList(params);
				model.addAttribute("list", list);
			}
		}
		model.addAttribute("params", params);
		return "/lectureOff/payList_sel_pop";
	}
	

	// 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/selpayList_excel.do")
	public ExcelDownloadView selexcelDownload(ModelMap output, HttpServletRequest req) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, req, output);

		//엑셀 리스트
		String BRIDGE_LECS_STR = CommonUtil.isNull(req.getParameter("BRIDGE_LECS_STR"), "");
		String real_lecs = "";
		String[] LEC_ARR = BRIDGE_LECS_STR.split("_");
		for(int i=0;i<LEC_ARR.length;i++){
			if(i==0){
				real_lecs += "'"+LEC_ARR[i]+"'";
			}else{
				real_lecs += ",'"+LEC_ARR[i]+"'";
			}
		}
		params.put("BRIDGE_LECS", real_lecs);
		params.put("BRIDGE_LECS_STR", BRIDGE_LECS_STR);
		List<HashMap<String, String>> exe_list = lectureoffservice.off_lecturePayList(params);
			output.addAttribute("list", exe_list);
	
		List<String> headerList = new ArrayList<String>();

		headerList.add("수강자");
		headerList.add("아이디");
		headerList.add("연락처");
		headerList.add("과목명");
		headerList.add("원수강료");
		
		headerList.add("수강금액");
		headerList.add("주문번호");
		headerList.add("주문구분");
		headerList.add("결제일");
		headerList.add("개강일");
		
		headerList.add("종료일");
		headerList.add("할인사유");
		headerList.add("메모");

		String excelName = "";

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			
			excelName = "수강인원조회";
			
			newMap.put(i++, CommonUtil.isNull(map.get("USER_NM")));
			newMap.put(i++, CommonUtil.isNull(map.get("USERID")));
			newMap.put(i++, CommonUtil.isNull(map.get("PHONE_NO")));
			newMap.put(i++, CommonUtil.isNull(map.get("SUBJECT_TITLE")));
			newMap.put(i++, CommonUtil.isNull(String.valueOf(map.get("SUBJECT_REAL_PRICE"))));
			
			newMap.put(i++, CommonUtil.isNull(String.valueOf(map.get("REALPRICE"))));
			newMap.put(i++, CommonUtil.isNull(map.get("ORDERNO")));
			newMap.put(i++, CommonUtil.isNull(map.get("PAYNM")));
			newMap.put(i++, CommonUtil.isNull(map.get("ISCONFIRM")));	
			newMap.put(i++, CommonUtil.isNull(map.get("SUBJECT_OPEN_DATE")));	
			
			newMap.put(i++, CommonUtil.isNull(map.get("SUBJECT_END_DATE")));	
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("PRICE_DISCOUNT_REASON"),"")));	
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("MEMO"),"")));

			newList.add(newMap);
		}

		output.addAttribute("excelName", excelName);
		output.addAttribute("headerList", headerList);
		output.addAttribute("dataList", newList);
		return new ExcelDownloadView();
	}

	/**
	 * @Method Name : jongPayList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 종합반 VOD유무/ PMP유무/ 동영상유무 클릭시 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/jongPayList.pop")
	public String jongPayList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> list = lectureoffservice.lectureJongPayList(params);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		return "/lectureOff/payList_pop";
	}

	/**
	 * @Method Name : dataViewList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 직종 클릭시 팝업
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/dataViewList.pop")
	public String dataViewList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> memolist = lectureoffservice.lectureDataMemoViewList(params);
		List<HashMap<String, String>> list = lectureoffservice.lectureDataViewList(params);
		model.addAttribute("memolist", memolist);
		model.addAttribute("list", list);
		model.addAttribute("params", params);
		return "/lectureOff/dataViewList_pop";
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
		Calendar cal = Calendar.getInstance( );
		String rootPath = fsResource.getPath();
		String subPath = "lecture_upload/" + cal.get(Calendar.YEAR) + "/" ;

		MultipartFile SUBJECT_SUMNAIL = multipartRequest.getFile("SUBJECT_SUMNAIL");
		if(SUBJECT_SUMNAIL != null && SUBJECT_SUMNAIL.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(SUBJECT_SUMNAIL, rootPath, subPath);
			params.put("SUBJECT_SUMNAIL", fileMap1.get("fileFullPath").toString());
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
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHPAYYN", CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCH_OPEN_DATE", CommonUtil.isNull(request.getParameter("SEARCH_OPEN_DATE"), ""));
		params.put("SEARCH_END_DATE", CommonUtil.isNull(request.getParameter("SEARCH_END_DATE"), ""));
		params.put("SEARCH_SUBJECT_ISUSE", CommonUtil.isNull(request.getParameter("SEARCH_SUBJECT_ISUSE"), ""));
		params.put("SEARCH_LEC_PROCESS", CommonUtil.isNull(request.getParameter("SEARCH_LEC_PROCESS"), ""));
		params.put("ADDBOOKAREA", CommonUtil.isNull(request.getParameter("ADDBOOKAREA"), ""));
		params.put("ADDAREA", CommonUtil.isNull(request.getParameter("ADDAREA"), ""));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("BRIDGE_LECCODE", CommonUtil.isNull(request.getParameter("BRIDGE_LECCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
		params.put("NO", CommonUtil.isNull(request.getParameter("NO"), "0"));
		params.put("SUBJECT_TYPE", CommonUtil.isNull(request.getParameter("SUBJECT_TYPE"), ""));
		params.put("SUBJECT_GUBUN", CommonUtil.isNull(request.getParameter("SUBJECT_GUBUN"), ""));
		params.put("SUBJECT_MEMBER_CNT", CommonUtil.isNull(request.getParameter("SUBJECT_MEMBER_CNT"), ""));
		params.put("WEEK1", CommonUtil.isNull(request.getParameter("WEEK1"), ""));
		params.put("WEEK2", CommonUtil.isNull(request.getParameter("WEEK2"), ""));
		params.put("WEEK3", CommonUtil.isNull(request.getParameter("WEEK3"), ""));
		params.put("WEEK4", CommonUtil.isNull(request.getParameter("WEEK4"), ""));
		params.put("WEEK5", CommonUtil.isNull(request.getParameter("WEEK5"), ""));
		params.put("WEEK6", CommonUtil.isNull(request.getParameter("WEEK6"), ""));
		params.put("WEEK7", CommonUtil.isNull(request.getParameter("WEEK7"), ""));
		params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE"), ""));
		params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
		params.put("PLAN", CommonUtil.isNull(request.getParameter("PLAN"), ""));
		params.put("SUBJECT_TEACHER", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER"), ""));
		params.put("SUBJECT_TEACHER_PAYMENT", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER_PAYMENT"), ""));
		params.put("SUBJECT_TITLE", CommonUtil.isNull(request.getParameter("SUBJECT_TITLE"), ""));
		params.put("SUBJECT_DESC", CommonUtil.isNull(request.getParameter("SUBJECT_DESC"), ""));
		params.put("SUBJECT_KEYWORD", CommonUtil.isNull(request.getParameter("SUBJECT_KEYWORD"), ""));
		params.put("SUBJECT_OPEN_DATE", CommonUtil.isNull(request.getParameter("SUBJECT_OPEN_DATE"), ""));
		params.put("SUBJECT_DISCOUNT", CommonUtil.isNull(request.getParameter("SUBJECT_DISCOUNT"), ""));
		params.put("SUBJECT_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), "0"));
		params.put("SUBJECT_REAL_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_REAL_PRICE"), "0"));
		params.put("SUBJECT_ISUSE", CommonUtil.isNull(request.getParameter("SUBJECT_ISUSE"), ""));
		params.put("LEC_GUBUN", CommonUtil.isNull(request.getParameter("LEC_GUBUN"), ""));
		params.put("REC_GUBUN", CommonUtil.isNull(request.getParameter("REC_GUBUN"), ""));
		params.put("SUBJECT_SJT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_SJT_CD"), ""));
		params.put("LEC_SCHEDULE", CommonUtil.isNull(request.getParameter("LEC_SCHEDULE"), ""));
		params.put("AS_SUBJECT_SUMNAIL", CommonUtil.isNull(request.getParameter("AS_SUBJECT_SUMNAIL"), ""));
		params.put("SUBJECT_SUMNAIL", CommonUtil.isNull(request.getParameter("SUBJECT_SUMNAIL"), ""));
		params.put("SUBJECT_SUMNAIL_DEL", CommonUtil.isNull(request.getParameter("SUBJECT_SUMNAIL_DEL"), ""));
		params.put("SUBJECT_SUMNAIL_DELNM", CommonUtil.isNull(request.getParameter("SUBJECT_SUMNAIL_DELNM"), ""));
        params.put("LEC_COUNT", CommonUtil.isNull(request.getParameter("LEC_COUNT"), ""));
        params.put("LEC_PROCESS", CommonUtil.isNull(request.getParameter("LEC_PROCESS"), ""));

		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

}
