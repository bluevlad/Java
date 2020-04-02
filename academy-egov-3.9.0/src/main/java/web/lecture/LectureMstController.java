package web.lecture;

import java.util.Calendar;
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

import web.util.CommonUtil;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.book.service.BookService;
import web.lecture.service.LectureMstService;
import web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/lecturemst")
@Controller
public class LectureMstController {
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
	private LectureMstService lecturemstservice;
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

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = lecturemstservice.lecturemstList(params);
		int listCount = lecturemstservice.lecturemstListCount(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("formlist", formlist);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "/lecture/lecturemst/list";
	}




	/**
	 * @Method Name : write
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 강의 등록폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/write.do")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params", params);
		return "/lecture/lecturemst/write";
	}

	/**
	 * 등록페이지 과목(강사) 검색 기능 및 리스트
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subject_teacher_search.pop")
	public String subject_teacher_search(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("params", params);
		return "/lecture/lecturemst/subject_teacher_search";
	}

	/**
	 * 등록페이지 과목(강사) 검색 기능 및 리스트
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subject_teacher_search_modify.pop")
	public String subject_teacher_search_modify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("params", params);
		return "/lecture/lecturemst/subject_teacher_search_modify";
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
	public String save(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		Calendar cal = Calendar.getInstance( );
		String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
		String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String BRIDGE_MSTCODE = "";
		String MSTCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeMstcodeSeqList = lecturemstservice.getBridgeMstcodeSeq(params);			// 브릿지테이블 SEQ 구하기
		if(getBridgeMstcodeSeqList.size() > 0){
			SEQ = getBridgeMstcodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeMstcodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "R" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getBridgeMstcodeList = lecturemstservice.getBridgeMstcode(params);					// 브릿지테이블 BRIDGE_MSTCODE 구하기

		if(getBridgeMstcodeList.size() > 0)
			BRIDGE_MSTCODE = params.get("PREFIX") + getBridgeMstcodeList.get(0).get("BRIDGE_MSTCODE");
		else
			BRIDGE_MSTCODE = params.get("PREFIX") + "00001";
		params.put("BRIDGE_MSTCODE", BRIDGE_MSTCODE.replace(" ", ""));

		params.put("PREFIX", "M" + cal.get(Calendar.YEAR));
//		for(int i=0; i<CATEGORY_CD_ARR.length; i++){																				// 직종 루프
//			params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);

//			for(int j=0; j<LEARNING_CD_ARR.length; j++){																				// 학습형태 루프
//				params.put("LEARNING_CD", LEARNING_CD_ARR[j]);

					List<HashMap<String, String>> getLeccodeList = lecturemstservice.getMstcode(params);
					if(getLeccodeList.size() > 0)
						MSTCODE = params.get("PREFIX") + getLeccodeList.get(0).get("MSTCODE");
					else
						MSTCODE = params.get("PREFIX") + "00001";
					params.put("MSTCODE", MSTCODE.replace(" ", ""));

					lecturemstservice.lecturemstInsert(params);																												// TB_LEC_MST (INSERT)
					params.put("SEQ", SEQ);
					lecturemstservice.lectureBridgeInsert(params);																										// TB_LEC_BRIDGE (INSERT)

					if(JU_RSC_ID_ARR != null){
						for(int k=0; k<JU_RSC_ID_ARR.length; k++){																	// 주교재 루프
							params.put("RSC_ID", JU_RSC_ID_ARR[k]);
							params.put("FLAG","J");
							lecturemstservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(BU_RSC_ID_ARR != null){
						for(int k=0; k<BU_RSC_ID_ARR.length; k++){																	// 부교재 루프
							params.put("RSC_ID", BU_RSC_ID_ARR[k]);
							params.put("FLAG","B");
							lecturemstservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
					if(SU_RSC_ID_ARR != null){
						for(int k=0; k<SU_RSC_ID_ARR.length; k++){																	// 수강생교재 루프
							params.put("RSC_ID", SU_RSC_ID_ARR[k]);
							params.put("FLAG","S");
							lecturemstservice.lectureBookInsert2(params);																								// TB_PLUS_CA_BOOK (INSERT)
						}
					}
//			}
//		}
		return "redirect:/lecturemst/list.do";
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
	public String update(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("BRIDGE_LEC", CommonUtil.isNull(request.getParameter("BRIDGE_LEC"), ""));
		params.put("UPDATE_FLAG", CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
		lecturemstservice.lectureBookDelete(params);
		lecturemstservice.lecturemstUpdate(params);

		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){																	// 주교재 루프
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}

			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){																	// 부교재 루프
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){																	// 수강생교재 루프
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}
			}
		}
		return "forward:/lecturemst/list.do";
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
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("ORDERID", CommonUtil.isNull(request.getParameter("ORDERID"), ""));
		List<HashMap<String, String>> memolist = lecturemstservice.lectureDataMemoViewList(params);
		List<HashMap<String, String>> list = lecturemstservice.lectureDataViewList(params);
		List<HashMap<String, String>> wmv = lecturemstservice.lectureWMV(params);
		List<HashMap<String, String>> down_count = lecturemstservice.lectureDown_Count(params);
		model.addAttribute("memolist", memolist);
		model.addAttribute("list", list);
		model.addAttribute("wmv", wmv);
		model.addAttribute("down_count", down_count);
		model.addAttribute("params", params);
		return "/lecture/lecture/dataViewList_pop";
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

		List<HashMap<String, String>> view = lecturemstservice.lectureView(params);
		//List<HashMap<String, String>> viewlist = lecturemstservice.lectureViewList(params); //해당 강의에 만들어긴 강좌 가져오기
		List<HashMap<String, String>> viewbooklist = lecturemstservice.lectureViewBookList(params);
		HashMap<String, String> vparams = new  HashMap<String, String>();
		String sdelyn = "Y";		// 관련글 삭제 가능여부
		String rdelyn = "Y";		// 현재 선택글 삭제가능여부

//		params.put("SEARCHGUBN", "T");
//		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
//		params.put("SEARCHCODEISUSE", "Y");
//		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
		//int lectureOrderCount = lecturemstservice.lectureDeleteCheck(params);

		//model.addAttribute("lectureOrderCount", lectureOrderCount);
//		model.addAttribute("kindlist", kindlist);
//		model.addAttribute("formlist", formlist);
		model.addAttribute("subjectteacherlist", subjectteacherlist);
		model.addAttribute("view", view);
		//model.addAttribute("viewlist", viewlist);
		model.addAttribute("viewbooklist", viewbooklist);
		model.addAttribute("rdelyn", rdelyn);
		model.addAttribute("sdelyn", sdelyn);
		model.addAttribute("params", params);
		return "/lecture/lecturemst/modify";
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
		lecturemstservice.lectureDelete(params);
		lecturemstservice.lectureBridgeDelete(params);
		lecturemstservice.lectureBookDelete(params);
		return "forward:/lecturemst/list.do";
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
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("ADDBOOKAREA", CommonUtil.isNull(request.getParameter("ADDBOOKAREA"), ""));
		params.put("ADDAREA", CommonUtil.isNull(request.getParameter("ADDAREA"), ""));
		params.put("JSEQ", CommonUtil.isNull(request.getParameter("JSEQ"), ""));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("BRIDGE_MSTCODE", CommonUtil.isNull(request.getParameter("BRIDGE_MSTCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
		params.put("MSTCODE", CommonUtil.isNull(request.getParameter("MSTCODE"), ""));
		params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
		params.put("PLAN", CommonUtil.isNull(request.getParameter("PLAN"), ""));
		params.put("SUBJECT_TEACHER", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER"), ""));
		params.put("SUBJECT_TEACHER_PAYMENT", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER_PAYMENT"), ""));
		params.put("SUBJECT_TITLE", CommonUtil.isNull(request.getParameter("SUBJECT_TITLE"), ""));
		params.put("SUBJECT_DESC", CommonUtil.isNull(request.getParameter("SUBJECT_DESC"), ""));
		params.put("SUBJECT_MEMO", CommonUtil.isNull(request.getParameter("SUBJECT_MEMO"), ""));
		params.put("SUBJECT_KEYWORD", CommonUtil.isNull(request.getParameter("SUBJECT_KEYWORD"), ""));
		params.put("SUBJECT_PERIOD", CommonUtil.isNull(request.getParameter("SUBJECT_PERIOD"), ""));
		params.put("SUBJECT_OFF_OPEN_YEAR", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_YEAR"), ""));
		params.put("SUBJECT_OFF_OPEN_MONTH", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_MONTH"), ""));
		params.put("SUBJECT_OFF_OPEN_DAY", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_DAY"), ""));
		params.put("SUBJECT_DISCOUNT", CommonUtil.isNull(request.getParameter("SUBJECT_DISCOUNT"), ""));

		if("N".equals(params.get("LEC_TYPE_CHOICE"))){
			params.put("SUBJECT_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), ""));
		}else{
			params.put("SUBJECT_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), "0"));
		}

		params.put("SUBJECT_POINT", CommonUtil.isNull(request.getParameter("SUBJECT_POINT"), "0"));
		params.put("SUBJECT_MOVIE", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE"), "0"));
		params.put("SUBJECT_PMP", CommonUtil.isNull(request.getParameter("SUBJECT_PMP"), "0"));
		params.put("SUBJECT_MOVIE_PMP", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_PMP"), "0"));
		params.put("SUBJECT_MOVIE_MP4", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_MP4"), "0"));
		params.put("SUBJECT_MOVIE_VOD_MP4", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_VOD_MP4"), "0"));
		params.put("SUBJECT_OPTION", CommonUtil.isNull(request.getParameter("SUBJECT_OPTION"), ""));
		params.put("SUBJECT_ISUSE", CommonUtil.isNull(request.getParameter("SUBJECT_ISUSE"), ""));
		params.put("SUBJECT_SJT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_SJT_CD"), ""));
		params.put("SUBJECT_VOD_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_VOD_DEFAULT_PATH"), ""));
		params.put("SUBJECT_WIDE_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_WIDE_DEFAULT_PATH"), ""));
		params.put("SUBJECT_MP4_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_MP4_DEFAULT_PATH"), ""));
		params.put("SUBJECT_PMP_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_PMP_DEFAULT_PATH"), ""));
		params.put("SUBJECT_PASS", CommonUtil.isNull(request.getParameter("SUBJECT_PASS"), ""));
		params.put("SUBJECT_JANG", CommonUtil.isNull(request.getParameter("SUBJECT_JANG"), ""));
		params.put("RE_COURSE", CommonUtil.isNull(request.getParameter("RE_COURSE"), ""));
		params.put("LEC_SCHEDULE", CommonUtil.isNull(request.getParameter("LEC_SCHEDULE"), ""));
		params.put("NO", CommonUtil.isNull(request.getParameter("NO"), "0"));
		params.put("ADD_LOW", CommonUtil.isNull(request.getParameter("ADD_LOW"), ""));
		params.put("ADD_MOVIE_ORDER1", CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER1"), ""));
		params.put("ADD_MOVIE_ORDER2", CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER2"), ""));
		params.put("ADD_MOVIE_NAME", CommonUtil.isNull(request.getParameter("ADD_MOVIE_NAME"), ""));
		params.put("ADD_MOVIE_DESC", CommonUtil.isNull(request.getParameter("ADD_MOVIE_DESC"), ""));
		params.put("ADD_MOVIE_URL", CommonUtil.isNull(request.getParameter("ADD_MOVIE_URL"), ""));
		params.put("ADD_WIDE_URL", CommonUtil.isNull(request.getParameter("ADD_WIDE_URL"), ""));
		params.put("ADD_MOVIE_FILENAME1", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME1"), ""));
		params.put("ADD_MP4_URL", CommonUtil.isNull(request.getParameter("ADD_MP4_URL"), ""));
		params.put("ADD_MOVIE_FILENAME2", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME2"), ""));
		params.put("ADD_PMP_URL", CommonUtil.isNull(request.getParameter("ADD_PMP_URL"), ""));
		params.put("ADD_MOVIE_FILENAME3", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME3"), ""));
		params.put("ADD_MOVIE_FILENAME4", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME4"), ""));
		params.put("ADD_PMP_FILENAME", CommonUtil.isNull(request.getParameter("ADD_PMP_FILENAME"), ""));
		params.put("ADD_MOVIE_TIME", CommonUtil.isNull(request.getParameter("ADD_MOVIE_TIME"), ""));
		params.put("ADD_MOVIE_FREE_FLAG", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FREE_FLAG"), "N"));
		params.put("FILE_DEL_NO", CommonUtil.isNull(request.getParameter("FILE_DEL_NO"), ""));
		params.put("FILE_DEL_NAME", CommonUtil.isNull(request.getParameter("FILE_DEL_NAME"), ""));
		params.put("MOVIE_ORDER1", CommonUtil.isNull(request.getParameter("MOVIE_ORDER1"), ""));
		params.put("MOVIE_ORDER2", CommonUtil.isNull(request.getParameter("MOVIE_ORDER2"), ""));
		params.put("MST_TEXT", CommonUtil.isNull(request.getParameter("MST_TEXT"), ""));
		params.put("POSITION", CommonUtil.isNull(request.getParameter("POSITION"), ""));
	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("LEC_GUBUN", CommonUtil.isNull(request.getParameter("LEC_GUBUN"), ""));
		params.put("REC_GUBUN", CommonUtil.isNull(request.getParameter("REC_GUBUN"), ""));
		params.put("MOV_ING", CommonUtil.isNull(request.getParameter("MOV_ING"), ""));
		params.put("MST_USE_YN", CommonUtil.isNull(request.getParameter("MST_USE_YN"), ""));
		params.put("TEACHER_NM", CommonUtil.isNull(request.getParameter("TEACHER_NM"), ""));
		params.put("TEACHER_SUBJECT", CommonUtil.isNull(request.getParameter("TEACHER_SUBJECT"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

}
