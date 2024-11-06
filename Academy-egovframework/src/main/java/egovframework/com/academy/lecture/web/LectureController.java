package egovframework.com.academy.lecture.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.lecture.service.LecFormService;
import egovframework.com.academy.lecture.service.LectureService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.academy.lecture.service.SubjectService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class LectureController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

//	@Inject
//	private FileSystemResource fsResource;
//	@Resource(name = "fileUtil")
//	private FileUtil fileUtil;
	@Resource(name = "lectureservice")
	private LectureService lectureservice;
	@Resource(name = "subjectService")
	private SubjectService subjectService;
	@Resource(name = "lecFormService")
	private LecFormService lecFormService;
//	@Resource(name = "CmmUseService")
//	private CmmUseService cmmUseService;
//	@Autowired
//	private SubjectService subjectService;
//	@Autowired
//	private ProductOrderService productOrderService;

	/**
	 * @Method Name : list
	 * @작성일 : 2023. 11.
	 * @Method 설명 : 단과 강의 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/leture/lecture/List.do")
	public String List(@ModelAttribute("LectureVO") LectureVO LectureVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        model.addAttribute("lecTypeChoice", commandMap.get("lecTypeChoice") == null ? "" : (String)commandMap.get("lecTypeChoice"));

		LectureVO.setPageUnit(propertyService.getInt("pageUnit"));
		LectureVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(LectureVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(LectureVO.getPageUnit());
		paginationInfo.setPageSize(LectureVO.getPageSize());

		LectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		LectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
		LectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        model.addAttribute("SEARCHGUBN", "T");
//		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
        model.addAttribute("SEARCHCODEISUSE", "T");
//		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		model.addAttribute("resultList", lectureservice.selectLectureList(LectureVO));
		
		int totCnt = lectureservice.selectLectureListCount(LectureVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

//		model.addAttribute("kindlist", kindlist);
//		model.addAttribute("formlist", formlist);

		return "egovframework/com/academy/lecture/lecture/List";
	}

	/**
	  @Method Name : modify
	  @작성일 : 2013. 11.
	  @Method 설명 : 단과 강의 수정폼
	  @param model
	  @param request
	  @return String
	  @throws Exception
	*/
	@RequestMapping(value="/academy/leture/lecture/Detail.do")
	public String detail(@ModelAttribute("LectureVO") LectureVO LectureVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception { 

		model.addAttribute("LectureVO", lectureservice.selectLectureDetail(LectureVO)); 
		String leccode = LectureVO.getLecCd();
		LectureVO.setLecCd("D201101435");
		model.addAttribute("viewbooklist", lectureservice.selectLectureViewBookList(LectureVO)); //주교재
		LectureVO.setLecCd(leccode);
//		model.addAttribute("viewlist", lectureservice.selectLectureViewList(LectureVO));
//      model.addAttribute("SEARCHGUBN", "T");
//		model.addAttribute("kindlist", subjectService.selectCategoryList(LectureVO));
//      model.addAttribute("SEARCHCODEISUSE", "Y");
//		model.addAttribute("formlist", lecFormService.getFormList(LectureVO)); 
//		model.addAttribute("subjectteacherlist", lectureservice.selectSubjectTeacherList(LectureVO));
//		model.addAttribute("lectureOrderCount", lectureservice.selectLectureOrderCnt(LectureVO));
//		model.addAttribute("rdelyn", "Y"); // 현재 선택글 삭제가능여부
//		model.addAttribute("sdelyn", "Y"); // 관련글  삭제 가능여부 
		  
		return "egovframework/com/academy/lecture/lecture/Detail"; 
	}
		  
		  
		  /*
		  
		  @RequestMapping(value="/listpop.pop") public String list_pop(ModelMap model,
		  HttpServletRequest request) throws Exception {
		  
		  HashMap<String, String> params = new HashMap<String, String>();
		  setParam(params, request, model); 페이징 int currentPage =
		  Integer.parseInt(params.get("currentPage")); int pageRow = 4;
		  params.put("pageRow", "4"); int startNo = (currentPage - 1) * pageRow; int
		  endNo = startNo + pageRow; params.put("startNo", String.valueOf(startNo));
		  params.put("endNo", String.valueOf(endNo)); 페이징
		  
		  params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
		  teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
		  List<HashMap<String, String>> formlist =
		  bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
		  lectureservice.lectureList(params); int listCount =
		  lectureservice.lectureListCount(params); String pagingHtml = new
		  Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
		  
		  model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
		  formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
		  listCount); model.addAttribute("pagingHtml", pagingHtml);
		  model.addAttribute("params", params); model.addAttribute("totalPage", (int)
		  Math.ceil((double) listCount / pageRow)); return "/lecture/lecture/listpop";
		  }
		  
		  @RequestMapping(value="/couponpop.pop") public String coupon_pop(ModelMap
		  model, HttpServletRequest request) throws Exception {
		  
		  HashMap<String, String> params = new HashMap<String, String>();
		  setParam(params, request, model); 페이징 int currentPage =
		  Integer.parseInt(params.get("currentPage")); int pageRow = 4;
		  params.put("pageRow", "4"); int startNo = (currentPage - 1) * pageRow; int
		  endNo = startNo + pageRow; params.put("startNo", String.valueOf(startNo));
		  params.put("endNo", String.valueOf(endNo)); 페이징
		  
		  List<HashMap<String, String>> list =
		  productOrderService.getTmCouponList(params);
		  
		  // 쿠폰발행 팝업 총 건수 int listCount = productOrderService.getTmCouponCount(params);
		  
		  String pagingHtml = new Paging(currentPage, listCount,
		  pageRow).getPagingHtml().toString(); model.addAttribute("list", list);
		  model.addAttribute("totalCount", listCount); //
		  model.addAttribute("pagingHtml", pagingHtml); model.addAttribute("params",
		  params); model.addAttribute("totalPage", (int) Math.ceil((double) listCount /
		  pageRow)); return "/lecture/lecture/couponpop"; }
		  
		  @RequestMapping(value="/mo_couponpop.pop") public String
		  mu_coupon_pop(ModelMap model, HttpServletRequest request) throws Exception {
		  
		  HashMap<String, String> params = new HashMap<String, String>();
		  setParam(params, request, model); 페이징 int currentPage =
		  Integer.parseInt(params.get("currentPage")); int pageRow = 4;
		  params.put("pageRow", "4"); int startNo = (currentPage - 1) * pageRow; int
		  endNo = startNo + pageRow; params.put("startNo", String.valueOf(startNo));
		  params.put("endNo", String.valueOf(endNo));
		  
		  페이징
		  
		  List<HashMap<String, String>> list =
		  productOrderService.getTmMoCouponList(params);
		  
		  // 쿠폰발행 팝업 총 건수 int listCount =
		  productOrderService.getTmMoCouponCount(params);
		  
		  String pagingHtml = new Paging(currentPage, listCount,
		  pageRow).getPagingHtml().toString(); model.addAttribute("list", list);
		  model.addAttribute("totalCount", listCount); //
		  model.addAttribute("pagingHtml", pagingHtml); model.addAttribute("params",
		  params); model.addAttribute("totalPage", (int) Math.ceil((double) listCount /
		  pageRow)); return "/lecture/lecture/mo_couponpop"; }
		  
		  @RequestMapping(value="/Modify_Lecture_On_Off.do")
		  
		  @ResponseBody
		  
		  @Transactional(readOnly=false,rollbackFor=Exception.class) public String
		  Modify_Lecture_On_Off(ModelMap model, HttpServletRequest request) throws
		  Exception {
		  
		  HashMap<String, String> params = new HashMap<String, String>();
		  setParam(params, request, model);
		  
		  페이징 int currentPage = Integer.parseInt(params.get("currentPage")); int
		  pageRow = Integer.parseInt(params.get("pageRow")); int startNo = (currentPage
		  - 1) * pageRow; int endNo = startNo + pageRow; String flag =
		  CommonUtil.isNull(request.getParameter("flag"), ""); String flag2 =
		  CommonUtil.isNull(request.getParameter("flag2"), "");
		  
		  params.put("startNo", String.valueOf(startNo)); params.put("endNo",
		  String.valueOf(endNo)); params.put("flag2", flag2); 페이징
		  
		  if(flag.equals("ON")){ params.put("FLAG", "Y"); }else{ params.put("FLAG",
		  "N"); }
		  
		  
		  if(flag2.equals("list")){ // 단과 강의관리 리스트에서 개설여부 수정시 params.put("RCODE",
		  CommonUtil.isNull(request.getParameter("Rcode"), "")); List<HashMap<String,
		  String>> BridgeLeccode = lectureservice.BridgeLeccode(params);
		  
		  if(BridgeLeccode.size() > 0){ for(int i = 0; i < BridgeLeccode.size(); i++){
		  String GET_CODE = String.valueOf(BridgeLeccode.get(i)).substring(9, 19);
		  params.put("GET_CODE", GET_CODE);
		  lectureservice.Modify_Lecture_On_Off(params); } } }else
		  if(flag2.equals("jlist") || flag2.equals("ylist")){ // 종합반,패키지,연회원패키지 강의관리
		  리스트에서 개설여부 수정시 params.put("GET_CODE",
		  CommonUtil.isNull(request.getParameter("Rcode"), ""));
		  lectureservice.Modify_Lecture_On_Off(params); }else{ // 단과 강의관리 상세페이지에서 개설여부
		  수정시 params.put("GET_CODE", CommonUtil.isNull(request.getParameter("Rcode"),
		  "")); lectureservice.Modify_Lecture_On_Off(params); }
		  
		  return flag; }
		  
		  
		 /**
		 * @Method Name : write
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 등록폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/write.do") public String write(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model);
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * Map<String, String> vo = new HashMap<String, String>(); vo.put("SYS_CD",
	 * "ICON_GUBUN"); List<HashMap<String, String>> ICON_GUBUNs =
	 * cmmUseService.selectCmmCodeMap(vo); model.addAttribute("ICON_GUBUNs",
	 * ICON_GUBUNs);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("params", params); return "/lecture/lecture/write"; }
	 * 
	 * 
	 *//**
		 * @Method Name : bookList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 교재 목록 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/bookList.pop") public String bookList(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model); 페이징 int
	 * currentPage = Integer.parseInt(params.get("currentPage")); int pageRow =
	 * Integer.parseInt(params.get("pageRow")); int startNo = (currentPage - 1) *
	 * pageRow; int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
	 * lectureservice.bookList(params); int listCount =
	 * lectureservice.bookListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("params", params); model.addAttribute("totalPage", (int)
	 * Math.ceil((double) listCount / pageRow)); return
	 * "/lecture/lecture/bookList_pop"; }
	 * 
	 *//**
		 * @Method Name : save
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/save.do")
	 * 
	 * @Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * save(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
	 * String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID"); String
	 * BRIDGE_LECCODE = ""; String LECCODE = ""; String SEQ = "";
	 * 
	 * List<HashMap<String, String>> getBridgeLeccodeSeqList =
	 * lectureservice.getBridgeLeccodeSeq(params); // 브릿지테이블 SEQ 구하기
	 * if(getBridgeLeccodeSeqList.size() > 0){ SEQ =
	 * getBridgeLeccodeSeqList.get(0).get("SEQ"); params.put("SEQ",
	 * getBridgeLeccodeSeqList.get(0).get("SEQ")); }else{ SEQ = "1";
	 * params.put("SEQ", "1"); }
	 * 
	 * params.put("PREFIX", "R" + cal.get(Calendar.YEAR)); List<HashMap<String,
	 * String>> getBridgeLeccodeList = lectureservice.getBridgeLeccode(params); //
	 * 브릿지테이블 BRIDGE_LECCODE 구하기
	 * 
	 * if(getBridgeLeccodeList.size() > 0) BRIDGE_LECCODE = params.get("PREFIX") +
	 * getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE"); else BRIDGE_LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("BRIDGE_LECCODE",
	 * BRIDGE_LECCODE.replace(" ", ""));
	 * 
	 * params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() +
	 * cal.get(Calendar.YEAR)); for(int i=0; i<CATEGORY_CD_ARR.length; i++){ // 직종
	 * 루프 params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);
	 * 
	 * for(int j=0; j<LEARNING_CD_ARR.length; j++){ // 학습형태 루프
	 * params.put("LEARNING_CD", LEARNING_CD_ARR[j]);
	 * 
	 * List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * params.put("SEQ", SEQ); lectureservice.lectureBridgeInsert(params); //
	 * TB_LEC_BRIDGE (INSERT)
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ // 부교재
	 * 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(SU_RSC_ID_ARR != null){ for(int k=0; k<SU_RSC_ID_ARR.length; k++){ //
	 * 수강생교재 루프 params.put("RSC_ID", SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } } }
	 * } return "redirect:/lecture/list.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 * @RequestMapping(value="/freesave.do")
	 * 
	 * @Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * freesave(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
	 * String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID"); String
	 * BRIDGE_LECCODE = ""; String LECCODE = ""; String SEQ = "";
	 * 
	 * List<HashMap<String, String>> getBridgeLeccodeSeqList =
	 * lectureservice.getBridgeLeccodeSeq(params); // 브릿지테이블 SEQ 구하기
	 * if(getBridgeLeccodeSeqList.size() > 0){ SEQ =
	 * getBridgeLeccodeSeqList.get(0).get("SEQ"); params.put("SEQ",
	 * getBridgeLeccodeSeqList.get(0).get("SEQ")); }else{ SEQ = "1";
	 * params.put("SEQ", "1"); }
	 * 
	 * params.put("PREFIX", "R" + cal.get(Calendar.YEAR)); List<HashMap<String,
	 * String>> getBridgeLeccodeList = lectureservice.getBridgeLeccode(params); //
	 * 브릿지테이블 BRIDGE_LECCODE 구하기
	 * 
	 * if(getBridgeLeccodeList.size() > 0) BRIDGE_LECCODE = params.get("PREFIX") +
	 * getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE"); else BRIDGE_LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("BRIDGE_LECCODE",
	 * BRIDGE_LECCODE.replace(" ", ""));
	 * 
	 * params.put("PREFIX", "F" + cal.get(Calendar.YEAR)); for(int i=0;
	 * i<CATEGORY_CD_ARR.length; i++){ // 직종 루프 params.put("CATEGORY_CD",
	 * CATEGORY_CD_ARR[i]);
	 * 
	 * //for(int j=0; j<LEARNING_CD_ARR.length; j++){ // 학습형태 루프
	 * params.put("LEARNING_CD", "");
	 * 
	 * List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * params.put("SEQ", SEQ); lectureservice.lectureBridgeInsert(params); //
	 * TB_LEC_BRIDGE (INSERT)
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ // 부교재
	 * 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(SU_RSC_ID_ARR != null){ for(int k=0; k<SU_RSC_ID_ARR.length; k++){ //
	 * 수강생교재 루프 params.put("RSC_ID", SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * //} } return "redirect:/lecture/freelist.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : write
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 무료강좌 등록 페이지
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/freewrite.do") public String freewrite(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * Map<String, String> vo = new HashMap<String, String>(); vo.put("SYS_CD",
	 * "ICON_GUBUN"); List<HashMap<String, String>> ICON_GUBUNs =
	 * cmmUseService.selectCmmCodeMap(vo); model.addAttribute("ICON_GUBUNs",
	 * ICON_GUBUNs);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("params", params); return "/lecture/lecture/freewrite"; }
	 * 
	 *//**
		 * 무료강좌 리스트 페이지
		 * 
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/freelist.do") public String freelist(ModelMap model,
	 * HttpServletRequest request) throws Exception {
	 * 
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); 페이징 int currentPage =
	 * Integer.parseInt(params.get("currentPage")); int pageRow =
	 * Integer.parseInt(params.get("pageRow")); int startNo = (currentPage - 1) *
	 * pageRow; int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
	 * lectureservice.lectureList(params); int listCount =
	 * lectureservice.lectureListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("params", params); model.addAttribute("totalPage", (int)
	 * Math.ceil((double) listCount / pageRow)); return "/lecture/lecture/freelist";
	 * }
	 * 
	 * @RequestMapping(value="/freemodify.do") public String freemodify(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureView(params);
	 * List<HashMap<String, String>> viewlist =
	 * lectureservice.lectureViewList(params); List<HashMap<String, String>>
	 * viewbooklist = lectureservice.lectureViewBookList(params); //HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params); int
	 * lectureOrderCount = lectureservice.lectureDeleteCheck(params);
	 * 
	 * Map<String, String> vo = new HashMap<String, String>(); vo.put("SYS_CD",
	 * "ICON_GUBUN"); List<HashMap<String, String>> ICON_GUBUNs =
	 * cmmUseService.selectCmmCodeMap(vo); model.addAttribute("ICON_GUBUNs",
	 * ICON_GUBUNs);
	 * 
	 * model.addAttribute("lectureOrderCount", lectureOrderCount);
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("view", view); model.addAttribute("viewlist", viewlist);
	 * model.addAttribute("viewbooklist", viewbooklist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/freemodify"; }
	 * 
	 *//**
		 * @Method Name : FreeLec_listDelete
		 * @작성일 : 2013. 10.
		 * @Method 설명 : 목록에서 삭제 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/FreeLec_listDelete.do")
	 * 
	 * @Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * listDelete(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); String[] DEL_ARR =
	 * request.getParameterValues("DEL_ARR"); if(DEL_ARR != null){ for(int i=0;
	 * i<DEL_ARR.length; i++){ //params.put("SUBJECT_CD", DEL_ARR[i]); String Lcode
	 * = DEL_ARR[i].split("/")[0]; String Bcode = DEL_ARR[i].split("/")[1];
	 * 
	 * params.put("LECCODE", Lcode); params.put("BRIDGE_LECCODE", Bcode);
	 * params.put("BRIDGE_LEC", Bcode);
	 * 
	 * lectureservice.lectureDelete(params);
	 * lectureservice.lectureBridgeDelete(params);
	 * lectureservice.lectureBookDelete(params); } } return
	 * "forward:/lecture/freelist.do"; }
	 * 
	 *//**
		 * @Method Name : lecview
		 * @작성일 : 2017. 07.
		 * @Method 설명 : 강의간략정보
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/lecview.html") public String lecview(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> lecview = lectureservice.lectureView(params);
	 * 
	 * model.addAttribute("lecview", lecview); return "/lecture/lecture/lecview"; }
	 * 
	 * @RequestMapping(value="/bookPopup.html") public String bookInfoPopup(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * model.addAttribute("params", params);
	 * 
	 * params.put("RSC_ID", CommonUtil.isNull(request.getParameter("rscVal")));
	 * List<HashMap<String, String>> view = lectureservice.bookView(params);
	 * 
	 * model.addAttribute("view", view); return "/lecture/lecture/commonBookPop"; }
	 * 
	 *//**
		 * @Method Name : update
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 수정 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/update.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * update(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); params.put("BRIDGE_LEC",
	 * CommonUtil.isNull(request.getParameter("BRIDGE_LEC"), ""));
	 * params.put("UPDATE_FLAG",
	 * CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
	 * 
	 * String SUBJECT_DESC = params.get("SUBJECT_DESC"); SUBJECT_DESC =
	 * SUBJECT_DESC.replaceAll("&lt;", "<").replaceAll("&gt;",
	 * ">").replaceAll("&quot;", "\"");
	 * 
	 * params.put("SUBJECT_DESC", SUBJECT_DESC);
	 * lectureservice.lectureBookDelete(params);
	 * lectureservice.lectureUpdate(params);
	 * 
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); }
	 * 
	 * } } if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ //
	 * 부교재 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); } } } if(SU_RSC_ID_ARR != null){
	 * for(int k=0; k<SU_RSC_ID_ARR.length; k++){ // 수강생교재 루프 params.put("RSC_ID",
	 * SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); } } }
	 * 
	 * return "forward:/lecture/list.do"; }
	 *//**
		 * @Method Name : update
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 수정 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/freeupdate.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * freeupdate(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); params.put("BRIDGE_LEC",
	 * CommonUtil.isNull(request.getParameter("BRIDGE_LEC"), ""));
	 * params.put("UPDATE_FLAG",
	 * CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
	 * lectureservice.lectureBookDelete(params);
	 * lectureservice.lectureUpdate(params);
	 * 
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * 
	 * // CALL SP_LECTURE_BOOK_INSERT( // #{LECCODE, mode=IN}, // #{RSC_ID,
	 * mode=IN}, // #{FLAG, mode=IN}, // #{BRIDGE_LEC, mode=IN}, // #{result,
	 * jdbcType=INTEGER, mode=OUT})
	 * 
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); }
	 * 
	 * } } if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ //
	 * 부교재 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); } } } if(SU_RSC_ID_ARR != null){
	 * for(int k=0; k<SU_RSC_ID_ARR.length; k++){ // 수강생교재 루프 params.put("RSC_ID",
	 * SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * if(params.get("UPDATE_FLAG").equals("ALL")){
	 * lectureservice.lectureBookInsert(params); }else{
	 * lectureservice.lectureBookInsert2(params); } } } return
	 * "forward:/lecture/freelist.do"; }
	 * 
	 *//**
		 * @Method Name : copyModify
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 복제폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/copy.do") public String copyModify(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureView(params);
	 * List<HashMap<String, String>> viewlist =
	 * lectureservice.lectureViewList(params); List<HashMap<String, String>>
	 * viewbooklist = lectureservice.lectureViewBookList(params); HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("view", view); model.addAttribute("viewlist", viewlist);
	 * model.addAttribute("viewbooklist", viewbooklist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/copy"; }
	 * 
	 *//**
		 * @Method Name : copyModify
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 복제폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/freecopy.do") public String freeModify(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureView(params);
	 * List<HashMap<String, String>> viewlist =
	 * lectureservice.lectureViewList(params); List<HashMap<String, String>>
	 * viewbooklist = lectureservice.lectureViewBookList(params); HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("view", view); model.addAttribute("viewlist", viewlist);
	 * model.addAttribute("viewbooklist", viewbooklist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/freecopy"; }
	 * 
	 *//**
		 * @Method Name : newCopyModify
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 신규복제폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/newCopy.do") public String newCopyModify(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureView(params);
	 * List<HashMap<String, String>> viewlist =
	 * lectureservice.lectureViewList(params); List<HashMap<String, String>>
	 * viewbooklist = lectureservice.lectureViewBookList(params); HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("subjectteacherlist", subjectteacherlist);
	 * model.addAttribute("view", view); model.addAttribute("viewlist", viewlist);
	 * model.addAttribute("viewbooklist", viewbooklist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/newCopy"; }
	 * 
	 *//**
		 * @Method Name : copySave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 복제 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/copySave.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * copySave(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID"); String LECCODE = "";
	 * 
	 * params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() +
	 * cal.get(Calendar.YEAR)); List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * lectureservice.lectureBridgeInsert(params); // TB_LEC_BRIDGE (INSERT)
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ // 부교재
	 * 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(SU_RSC_ID_ARR != null){ for(int k=0; k<SU_RSC_ID_ARR.length; k++){ //
	 * 수강생교재 루프 params.put("RSC_ID", SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * 
	 * return "redirect:/lecture/list.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : newCopySave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 강의 신규복제 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/newCopySave.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * newCopySave(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
	 * String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
	 * String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID"); String[]
	 * BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID"); String[]
	 * SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID"); String
	 * BRIDGE_LECCODE = ""; String LECCODE = ""; String SEQ = "";
	 * 
	 * List<HashMap<String, String>> getBridgeLeccodeSeqList =
	 * lectureservice.getBridgeLeccodeSeq(params); // 브릿지테이블 SEQ 구하기
	 * if(getBridgeLeccodeSeqList.size() > 0){ SEQ =
	 * getBridgeLeccodeSeqList.get(0).get("SEQ"); params.put("SEQ",
	 * getBridgeLeccodeSeqList.get(0).get("SEQ")); }else{ SEQ = "1";
	 * params.put("SEQ", "1"); }
	 * 
	 * params.put("PREFIX", "R" + cal.get(Calendar.YEAR)); List<HashMap<String,
	 * String>> getBridgeLeccodeList = lectureservice.getBridgeLeccode(params); //
	 * 브릿지테이블 BRIDGE_LECCODE 구하기
	 * 
	 * if(getBridgeLeccodeList.size() > 0) BRIDGE_LECCODE = params.get("PREFIX") +
	 * getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE"); else BRIDGE_LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("BRIDGE_LECCODE",
	 * BRIDGE_LECCODE.replace(" ", ""));
	 * 
	 * params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() +
	 * cal.get(Calendar.YEAR)); for(int i=0; i<CATEGORY_CD_ARR.length; i++){ // 직종
	 * 루프 params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);
	 * 
	 * for(int j=0; j<LEARNING_CD_ARR.length; j++){ // 학습형태 루프
	 * params.put("LEARNING_CD", LEARNING_CD_ARR[j]);
	 * 
	 * List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * params.put("SEQ", SEQ); lectureservice.lectureBridgeInsert(params); //
	 * TB_LEC_BRIDGE (INSERT)
	 * 
	 * if(JU_RSC_ID_ARR != null){ for(int k=0; k<JU_RSC_ID_ARR.length; k++){ // 주교재
	 * 루프 params.put("RSC_ID", JU_RSC_ID_ARR[k]); params.put("FLAG","J");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(BU_RSC_ID_ARR != null){ for(int k=0; k<BU_RSC_ID_ARR.length; k++){ // 부교재
	 * 루프 params.put("RSC_ID", BU_RSC_ID_ARR[k]); params.put("FLAG","B");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } }
	 * if(SU_RSC_ID_ARR != null){ for(int k=0; k<SU_RSC_ID_ARR.length; k++){ //
	 * 수강생교재 루프 params.put("RSC_ID", SU_RSC_ID_ARR[k]); params.put("FLAG","S");
	 * lectureservice.lectureBookInsert2(params); // TB_PLUS_CA_BOOK (INSERT) } } }
	 * } return "redirect:/lecture/list.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : delete
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 강의 삭제 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/delete.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * delete(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); lectureservice.lectureDelete(params);
	 * lectureservice.lectureBridgeDelete(params);
	 * lectureservice.lectureBookDelete(params); return "forward:/lecture/list.do";
	 * }
	 * 
	 *//**
		 * @Method Name : seqList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 강의 순서변경 목록 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/seqList.pop") public String seqList(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model);
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
	 * lectureservice.lectureSeqList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("params",
	 * params); return "/lecture/lecture/seqList"; }
	 * 
	 *//**
		 * @Method Name : seqUpdate
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 강의 순번 수정 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/seqUpdate.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * seqUpdate(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); String[] SEQARR =
	 * request.getParameterValues("SEQ"); String[] LECCODEARR =
	 * request.getParameterValues("LECCODE"); if(SEQARR != null){ for(int i=0;
	 * i<SEQARR.length; i++){ params.put("SEQ", SEQARR[i]); params.put("LECCODE",
	 * LECCODEARR[i]); lectureservice.lectureSeqUpdate(params); } }
	 * model.addAttribute("returnData","Y");; model.addAttribute("params", params);
	 * return "forward:/lecture/seqList.pop"; }
	 * 
	 *//**
		 * @Method Name : jongList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 목록
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongList.do") public String jongList(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model); 페이징 int
	 * currentPage = Integer.parseInt(params.get("currentPage")); int pageRow =
	 * Integer.parseInt(params.get("pageRow")); int startNo = (currentPage - 1) *
	 * pageRow; int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
	 * lectureservice.lectureJongList(params); int listCount =
	 * lectureservice.lectureJongListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("params", params); model.addAttribute("totalPage", (int)
	 * Math.ceil((double) listCount / pageRow)); return "/lecture/lecture/jongList";
	 * }
	 *//**
		 * @Method Name : jongList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 연회원패키지
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/yearlist.do") public String YearList(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model); 페이징 int
	 * currentPage = Integer.parseInt(params.get("currentPage")); int pageRow =
	 * Integer.parseInt(params.get("pageRow")); int startNo = (currentPage - 1) *
	 * pageRow; int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>> list =
	 * lectureservice.lectureYearList(params); int listCount =
	 * lectureservice.lectureYearListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("params", params); model.addAttribute("totalPage", (int)
	 * Math.ceil((double) listCount / pageRow)); return "/lecture/lecture/YearList";
	 * }
	 * 
	 *//**
		 * @Method Name : jongWrite
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 등록폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongWrite.do") public String jongWrite(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("params", params); return
	 * "/lecture/lecture/jongWrite"; }
	 *//**
		 * @Method Name : jongWrite
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 등록폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/yearWrite.do") public String yearwrite(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); List<HashMap<String, String>>
	 * subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("params", params); return
	 * "/lecture/lecture/yearWrite"; }
	 * 
	 *//**
		 * @Method Name : subjectList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 단과목록 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/subjectList.pop") public String subjectList(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model); 페이징
	 * int currentPage = Integer.parseInt(params.get("currentPage")); int pageRow =
	 * Integer.parseInt(params.get("pageRow")); int startNo = (currentPage - 1) *
	 * pageRow; int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * String orgnLecTypeChoice = params.get("LEC_TYPE_CHOICE"); //단과만 조회하므로 잠시 D로
	 * 바꾼다. params.put("LEC_TYPE_CHOICE", "D"); List<HashMap<String, String>>
	 * formlist = bookservice.getLearningFormList(params);
	 * params.put("LEC_TYPE_CHOICE", orgnLecTypeChoice); List<HashMap<String,
	 * String>> list = lectureservice.lectureJongSubjectList(params); int listCount
	 * = lectureservice.lectureJongSubjectListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("params", params); model.addAttribute("totalPage", (int)
	 * Math.ceil((double) listCount / pageRow)); return
	 * "/lecture/lecture/jongSubjectList_pop"; }
	 * 
	 *//**
		 * @Method Name : searchLectureList
		 * @작성일 : 2015. 07.
		 * @Method 설명 : 단과목록 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/searchLecList.pop") public String
	 * searchLectureList(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * 
	 * 페이징 int currentPage = Integer.parseInt(params.get("currentPage")); int
	 * pageRow = Integer.parseInt("15"); int startNo = (currentPage - 1) * pageRow;
	 * int endNo = startNo + pageRow; params.put("startNo",
	 * String.valueOf(startNo)); params.put("endNo", String.valueOf(endNo)); 페이징
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * params.put("LEC_TYPE_CHOICE", "D"); List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params);
	 * 
	 * model.addAttribute("SEARCHSUBJECTs", teacherservice.getSubjectList(params));
	 * //과목 목록 model.addAttribute("SEARCHPRFIDs",
	 * teacherservice.teacherAllList(params)); //강사 목록
	 * 
	 * params.put("SEARCHSBJTCD",
	 * CommonUtil.isNull(request.getParameter("SEARCHSBJTCD"), ""));
	 * params.put("SEARCHPRFID",
	 * CommonUtil.isNull(request.getParameter("SEARCHPRFID"), ""));
	 * 
	 * List<HashMap<String, String>> list =
	 * lectureservice.lectureJongSubjectList(params); int listCount =
	 * lectureservice.lectureJongSubjectListCount(params); String pagingHtml = new
	 * Paging(currentPage, listCount, pageRow).getPagingHtml().toString();
	 * 
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("list", list); model.addAttribute("totalCount",
	 * listCount); model.addAttribute("pagingHtml", pagingHtml);
	 * model.addAttribute("totalPage", (int) Math.ceil((double) listCount /
	 * pageRow));
	 * 
	 * params.put("SRCHCODE", CommonUtil.isNull(request.getParameter("SRCHCODE"),
	 * "")); params.put("SRCHTXT",
	 * CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));
	 * 
	 * model.addAttribute("params", params);
	 * 
	 * return "/lecture/lecture/searchLectureList_pop"; }
	 * 
	 *//**
		 * @Method Name : jongSave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongSave.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * jongSave(ModelMap model, HttpServletRequest request,
	 * MultipartHttpServletRequest multipartRequest) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); lecFileProcess(params, multipartRequest);
	 * Calendar cal = Calendar.getInstance( ); String[] CATEGORY_CD_ARR =
	 * request.getParameterValues("CATEGORY_CD"); String[] LEARNING_CD_ARR =
	 * request.getParameterValues("LEARNING_CD"); String[] MST_LECCODE_ARR =
	 * request.getParameterValues("MST_LECCODE"); String[] MST_LECCODE2_ARR =
	 * request.getParameterValues("MST_LECCODE2"); String[] MST_LECCODE_SORT =
	 * request.getParameterValues("MST_LECCODE_SORT"); String[] MST_LECCODE2_SORT =
	 * request.getParameterValues("MST_LECCODE2_SORT"); String LECCODE = ""; String
	 * MST_LECCODE = ""; String SEQ = "";
	 * 
	 * List<HashMap<String, String>> getJongLeccodeSeqList =
	 * lectureservice.getJongLeccodeSeq(params); // JONG 브릿지 SEQ 구하기
	 * if(getJongLeccodeSeqList.size() > 0){ SEQ =
	 * getJongLeccodeSeqList.get(0).get("SEQ"); params.put("SEQ",
	 * getJongLeccodeSeqList.get(0).get("SEQ")); }else{ SEQ = "1"; params.put("SEQ",
	 * "1"); } params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() +
	 * cal.get(Calendar.YEAR)); for(int i=0; i<CATEGORY_CD_ARR.length; i++){
	 * params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);
	 * 
	 * for(int j=0; j<LEARNING_CD_ARR.length; j++){ params.put("LEARNING_CD",
	 * LEARNING_CD_ARR[j]);
	 * 
	 * List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * params.put("SEQ", SEQ);
	 * 
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ //params.put("SEQ",
	 * String.valueOf(SEQ)); lectureservice.lectureChoiceJongNoInsert(params); //
	 * TB_CHOICE_JONG_NO (INSERT) }
	 * 
	 * if(MST_LECCODE_ARR != null){ for(int k=0; k<MST_LECCODE_ARR.length; k++){
	 * params.put("MST_LECCODE", MST_LECCODE_ARR[k]); params.put("GUBUN","1"); // 필수
	 * params.put("SORT", String.valueOf(k));
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ params.put("SORT",
	 * MST_LECCODE_SORT[k]); } lectureservice.lectureLecJongInsert(params); //
	 * TB_LEC_JONG (INSERT) } } if(MST_LECCODE2_ARR != null){ for(int k=0;
	 * k<MST_LECCODE2_ARR.length; k++){ params.put("MST_LECCODE",
	 * MST_LECCODE2_ARR[k]); params.put("GUBUN","2"); // 선택 params.put("SORT",
	 * String.valueOf(k)); if("N".equals(params.get("LEC_TYPE_CHOICE"))){
	 * params.put("SORT", MST_LECCODE2_SORT[k]); }
	 * lectureservice.lectureLecJongInsert(params); // TB_LEC_JONG (INSERT) } }
	 * 
	 * } } return "redirect:/lecture/jongList.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : jongSave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/yearSave.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * yearSave(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
	 * String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
	 * String[] MST_LECCODE_ARR = request.getParameterValues("MST_LECCODE");
	 * String[] MST_LECCODE2_ARR = request.getParameterValues("MST_LECCODE2");
	 * String[] MST_LECCODE_SORT = request.getParameterValues("MST_LECCODE_SORT");
	 * String[] MST_LECCODE2_SORT = request.getParameterValues("MST_LECCODE2_SORT");
	 * String LECCODE = ""; String MST_LECCODE = ""; String SEQ = "";
	 * 
	 * List<HashMap<String, String>> getJongLeccodeSeqList =
	 * lectureservice.getJongLeccodeSeq(params); // JONG 브릿지 SEQ 구하기
	 * if(getJongLeccodeSeqList.size() > 0){ SEQ =
	 * getJongLeccodeSeqList.get(0).get("SEQ"); params.put("SEQ",
	 * getJongLeccodeSeqList.get(0).get("SEQ")); }else{ SEQ = "1"; params.put("SEQ",
	 * "1"); } params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() +
	 * cal.get(Calendar.YEAR)); for(int i=0; i<CATEGORY_CD_ARR.length; i++){
	 * params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);
	 * 
	 * //for(int j=0; j<LEARNING_CD_ARR.length; j++){ //params.put("LEARNING_CD",
	 * LEARNING_CD_ARR[j]);
	 * 
	 * List<HashMap<String, String>> getLeccodeList =
	 * lectureservice.getLeccode(params); if(getLeccodeList.size() > 0) LECCODE =
	 * params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE"); else LECCODE =
	 * params.get("PREFIX") + "00001"; params.put("LECCODE", LECCODE.replace(" ",
	 * ""));
	 * 
	 * lectureservice.lectureInsert(params); // TB_LEC_MST (INSERT)
	 * params.put("SEQ", SEQ);
	 * 
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ //params.put("SEQ",
	 * String.valueOf(SEQ)); lectureservice.lectureChoiceJongNoInsert(params); //
	 * TB_CHOICE_JONG_NO (INSERT) }
	 * 
	 * if(MST_LECCODE_ARR != null){ for(int k=0; k<MST_LECCODE_ARR.length; k++){
	 * params.put("MST_LECCODE", MST_LECCODE_ARR[k]); params.put("GUBUN","1"); // 필수
	 * params.put("SORT", String.valueOf(k));
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ params.put("SORT",
	 * MST_LECCODE_SORT[k]); } lectureservice.lectureLecJongInsert(params); //
	 * TB_LEC_JONG (INSERT) } } if(MST_LECCODE2_ARR != null){ for(int k=0;
	 * k<MST_LECCODE2_ARR.length; k++){ params.put("MST_LECCODE",
	 * MST_LECCODE2_ARR[k]); params.put("GUBUN","2"); // 선택 params.put("SORT",
	 * String.valueOf(k)); if("N".equals(params.get("LEC_TYPE_CHOICE"))){
	 * params.put("SORT", MST_LECCODE2_SORT[k]); }
	 * lectureservice.lectureLecJongInsert(params); // TB_LEC_JONG (INSERT) } // }
	 * 
	 * } } return "redirect:/lecture/jongList.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : jongModify
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 수정폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongModify.do") public String jongModify(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureJongView(params);
	 * List<HashMap<String, String>> viewleccodelist =
	 * lectureservice.lectureViewLeccodeList(params); List<HashMap<String, String>>
	 * viewjonglist = lectureservice.lectureViewJongList(params); HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); int lectureOrderCount =
	 * lectureservice.lectureDeleteCheck(params);
	 * 
	 * model.addAttribute("lectureOrderCount", lectureOrderCount);
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("view", view);
	 * model.addAttribute("viewleccodelist", viewleccodelist);
	 * model.addAttribute("viewjonglist", viewjonglist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/jongModify"; }
	 *//**
		 * @Method Name : jongModify
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 수정폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/yearModify.do") public String yearModify(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * List<HashMap<String, String>> view = lectureservice.lectureJongView(params);
	 * List<HashMap<String, String>> viewleccodelist =
	 * lectureservice.lectureViewLeccodeList(params); List<HashMap<String, String>>
	 * viewjonglist = lectureservice.lectureViewJongList(params); HashMap<String,
	 * String> vparams = new HashMap<String, String>(); String sdelyn = "Y"; // 관련글
	 * 삭제 가능여부 String rdelyn = "Y"; // 현재 선택글 삭제가능여부
	 * 
	 * params.put("SEARCHGUBN", "T"); List<HashMap<String, String>> kindlist =
	 * teacherservice.getKindList(params); params.put("SEARCHCODEISUSE", "Y");
	 * List<HashMap<String, String>> formlist =
	 * bookservice.getLearningFormList(params); int lectureOrderCount =
	 * lectureservice.lectureDeleteCheck(params);
	 * 
	 * model.addAttribute("lectureOrderCount", lectureOrderCount);
	 * model.addAttribute("kindlist", kindlist); model.addAttribute("formlist",
	 * formlist); model.addAttribute("view", view);
	 * model.addAttribute("viewleccodelist", viewleccodelist);
	 * model.addAttribute("viewjonglist", viewjonglist);
	 * model.addAttribute("rdelyn", rdelyn); model.addAttribute("sdelyn", sdelyn);
	 * model.addAttribute("params", params); return "/lecture/lecture/yearModify"; }
	 * 
	 *//**
		 * @Method Name : jongUpdate
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 수정 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongUpdate.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * jongUpdate(ModelMap model, HttpServletRequest request,
	 * MultipartHttpServletRequest multipartRequest) throws Exception { String
	 * rootPath = fsResource.getPath(); HashMap<String, String> params = new
	 * HashMap<String, String>(); setParam(params, request, model);
	 * lecFileProcess(params, multipartRequest); Calendar cal =
	 * Calendar.getInstance( ); String[] MST_LECCODE_ARR =
	 * request.getParameterValues("MST_LECCODE"); String[] MST_LECCODE2_ARR =
	 * request.getParameterValues("MST_LECCODE2"); String[] MST_LECCODE_SORT =
	 * request.getParameterValues("MST_LECCODE_SORT"); String[] MST_LECCODE2_SORT =
	 * request.getParameterValues("MST_LECCODE2_SORT");
	 * 
	 * lectureservice.lectureUpdate(params);
	 * lectureservice.lectureLecJongDelete(params);
	 * lectureservice.lectureChoiceJongNoDelete(params);
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){
	 * lectureservice.lectureChoiceJongNoInsert(params); // TB_CHOICE_JONG_NO
	 * (INSERT) }
	 * 
	 * if(MST_LECCODE_ARR != null){ for(int k=0; k<MST_LECCODE_ARR.length; k++){
	 * params.put("MST_LECCODE", MST_LECCODE_ARR[k]); params.put("GUBUN","1"); // 필수
	 * params.put("SORT", String.valueOf(k));
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ params.put("SORT",
	 * MST_LECCODE_SORT[k]); } lectureservice.lectureLecJongInsert(params); //
	 * TB_LEC_JONG (INSERT) } } if(MST_LECCODE2_ARR != null){ for(int k=0;
	 * k<MST_LECCODE2_ARR.length; k++){ params.put("MST_LECCODE",
	 * MST_LECCODE2_ARR[k]); params.put("GUBUN","2"); // 선택 params.put("SORT",
	 * String.valueOf(k)); if("N".equals(params.get("LEC_TYPE_CHOICE"))){
	 * params.put("SORT", MST_LECCODE2_SORT[k]); }
	 * lectureservice.lectureLecJongInsert(params); // TB_LEC_JONG (INSERT) } }
	 * if("Y".equals(params.get("MUST_PRF_IMG_DEL")) ||
	 * !"".equals(params.get("MUST_PRF_IMG"))) fileUtil.deleteFile(rootPath +
	 * params.get("MUST_PRF_IMG_DEL")); if("Y".equals(params.get("SEL_PRF_IMG_DEL"))
	 * || !"".equals(params.get("SEL_PRF_IMG"))) fileUtil.deleteFile(rootPath +
	 * params.get("SEL_PRF_IMG_DEL")); return
	 * "redirect:/lecture/jongList.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 *//**
		 * @Method Name : jongUpdate
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 수정 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/yearUpdate.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * yearUpdate(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); Calendar cal = Calendar.getInstance( );
	 * String[] MST_LECCODE_ARR = request.getParameterValues("MST_LECCODE");
	 * String[] MST_LECCODE2_ARR = request.getParameterValues("MST_LECCODE2");
	 * String[] MST_LECCODE_SORT = request.getParameterValues("MST_LECCODE_SORT");
	 * String[] MST_LECCODE2_SORT = request.getParameterValues("MST_LECCODE2_SORT");
	 * 
	 * 
	 * List<HashMap<String, String>> getJongLeccodeSeqList =
	 * lectureservice.getJongLeccodeSeq(params); // JONG 브릿지 SEQ 구하기
	 * if(getJongLeccodeSeqList.size() > 0){ params.put("SEQ",
	 * getJongLeccodeSeqList.get(0).get("SEQ")); }else{ params.put("SEQ", "1"); }
	 * 
	 * lectureservice.lectureUpdate(params);
	 * lectureservice.lectureLecJongDelete(params);
	 * lectureservice.lectureChoiceJongNoDelete(params);
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){
	 * lectureservice.lectureChoiceJongNoInsert(params); // TB_CHOICE_JONG_NO
	 * (INSERT) }
	 * 
	 * if(MST_LECCODE_ARR != null){ for(int k=0; k<MST_LECCODE_ARR.length; k++){
	 * params.put("MST_LECCODE", MST_LECCODE_ARR[k]); params.put("GUBUN","1"); // 필수
	 * params.put("SORT", String.valueOf(k));
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))||"Y".equals(params.get(
	 * "LEC_TYPE_CHOICE"))){ params.put("SORT", MST_LECCODE_SORT[k]); }
	 * lectureservice.lectureLecJongInsert(params); // TB_LEC_JONG (INSERT) } }
	 * if(MST_LECCODE2_ARR != null){ for(int k=0; k<MST_LECCODE2_ARR.length; k++){
	 * params.put("MST_LECCODE", MST_LECCODE2_ARR[k]); params.put("GUBUN","2"); //
	 * 선택 params.put("SORT", String.valueOf(k));
	 * if("N".equals(params.get("LEC_TYPE_CHOICE"))||"Y".equals(params.get(
	 * "LEC_TYPE_CHOICE"))){ params.put("SORT", MST_LECCODE2_SORT[k]); }
	 * lectureservice.lectureLecJongInsert(params); // TB_LEC_JONG (INSERT) } }
	 * 
	 * return "redirect:/lecture/yearlist.do?LEC_TYPE_CHOICE=" +
	 * params.get("LEC_TYPE_CHOICE"); }
	 * 
	 *//**
		 * @Method Name : jongDelete
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 강의 삭제 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongDelete.do")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * jongDelete(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); lectureservice.lectureDelete(params);
	 * lectureservice.lectureLecJongDelete(params);
	 * lectureservice.lectureChoiceJongNoDelete(params); return
	 * "forward:/lecture/jongList.do"; }
	 * 
	 *//**
		 * @Method Name : lecturePayList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 VOD유무/ PMP유무/ 동영상유무 클릭시 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/payList.pop") public String payList(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>(); setParam(params, request, model);
	 * List<HashMap<String, String>> list = lectureservice.lecturePayList(params);
	 * model.addAttribute("list", list); model.addAttribute("params", params);
	 * return "/lecture/lecture/payList_pop"; }
	 * 
	 *//**
		 * @Method Name : jongPayList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 종합반 VOD유무/ PMP유무/ 동영상유무 클릭시 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/jongPayList.pop") public String jongPayList(ModelMap
	 * model, HttpServletRequest request) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * List<HashMap<String, String>> list =
	 * lectureservice.lectureJongPayList(params); model.addAttribute("list", list);
	 * model.addAttribute("params", params); return "/lecture/lecture/payList_pop";
	 * }
	 * 
	 * @RequestMapping(value="/FreePassPayList.pop") public String
	 * FreePassPayList(ModelMap model, HttpServletRequest request) throws Exception
	 * { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); List<HashMap<String, String>> list =
	 * lectureservice.lectureFreePassPayList(params); model.addAttribute("list",
	 * list); model.addAttribute("params", params); return
	 * "/lecture/lecture/FreePasspayList_pop"; }
	 * 
	 * @RequestMapping(value="/FreePassDetailList.pop") public String
	 * FreePassDetailList(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * 
	 * params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"),
	 * "")); params.put("ORDERNO",
	 * CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
	 * 
	 * List<HashMap<String, String>> viewleccodelist =
	 * lectureservice.YearIngList(params); List<HashMap<String, String>> list =
	 * lectureservice.MyYearIngList(params);
	 * 
	 * model.addAttribute("list", list); model.addAttribute("viewleccodelist",
	 * viewleccodelist); model.addAttribute("params", params);
	 * 
	 * return "/lecture/lecture/FreePassDetailList_pop"; }
	 * 
	 *//**
		 * @Method Name : dataViewList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 직종 클릭시 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataViewList.pop") public String
	 * dataViewList(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); params.put("ORDERNO",
	 * CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
	 * params.put("ORDERID", CommonUtil.isNull(request.getParameter("ORDERID"),
	 * "")); List<HashMap<String, String>> memolist =
	 * lectureservice.lectureDataMemoViewList(params); List<HashMap<String, String>>
	 * list = lectureservice.lectureDataViewList(params); List<HashMap<String,
	 * String>> wmv = lectureservice.lectureWMV(params); List<HashMap<String,
	 * String>> down_count = lectureservice.lectureDown_Count(params);
	 * model.addAttribute("memolist", memolist); model.addAttribute("list", list);
	 * model.addAttribute("wmv", wmv); model.addAttribute("down_count", down_count);
	 * model.addAttribute("params", params); return
	 * "/lecture/lecture/dataViewList_pop"; }
	 * 
	 *//**
		 * @Method Name : dataMovieViewList
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieViewList.pop") public String
	 * dataMovieViewList(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); List<HashMap<String, String>> memolist =
	 * lectureservice.lectureDataMemoViewList(params); List<HashMap<String, String>>
	 * list = lectureservice.lectureDataMovieList(params); List<HashMap<String,
	 * String>> view = lectureservice.lectureView(params);
	 * model.addAttribute("memolist", memolist); model.addAttribute("list", list);
	 * model.addAttribute("view", view); model.addAttribute("params", params);
	 * return "/lecture/lecture/dataMovieViewList_pop"; }
	 * 
	 *//**
		 * @Method Name : pmpDownload
		 * @작성일 : 2013. 12.
		 * @Method 설명 : pmp 다운로드
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/pmpDownloadView.pop3") public String
	 * pmpSampleDownloadView(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * 
	 * params.put("MOVIE_NO",
	 * CommonUtil.isNull(request.getParameter("MOVIE_NO"),""));
	 * 
	 * HashMap<String, String> detail = lectureservice.lectureOnDetailS(params); //
	 * 해당 선택형 종합반의 강의 정보를 조회한다. model.addAttribute("params", params);
	 * model.addAttribute("detail" , detail); return
	 * "/lecture/lecture/pmpPopupView"; }
	 *//**
		 * @Method Name : pmpDownloadLog
		 * @작성일 : 2013. 12.
		 * @Method 설명 : pmp 다운로드 로그
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/pmpDownloadLog.pop3") public void
	 * pmpDownloadLog(ModelMap model, HttpServletRequest request,
	 * HttpServletResponse response) throws Exception { HashMap<String, String>
	 * params = new HashMap<String, String>(); setParam(params, request, model);
	 * 
	 * String userid = CommonUtil.isNull(request.getParameter("userid"),""); String
	 * cid = CommonUtil.isNull(request.getParameter("cid"),""); String downloginfo =
	 * CommonUtil.isNull(request.getParameter("downloginfo"),"");
	 * 
	 * pmp다운로그를 남기위 함 시작
	 * 
	 * String filename = ""; String desc = ""; String ch = null; TimeZone tz = new
	 * SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" ); TimeZone.setDefault(tz); Date d
	 * = new Date(); SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); ch =
	 * sdf.format(d); filename = "DownLog"+ch+".txt"; SimpleDateFormat sdf1 = new
	 * SimpleDateFormat("yyyyMMdd HH:mm:ss"); ch = sdf1.format(d);
	 * 
	 * String contents_server = MirProperties.getProperty("Globals.DomainURL")+"/";
	 * 
	 * desc =
	 * contents_server+"  : "+ch+" : "+"/pmp/DownLog.asp : [ID = "+userid+"]";
	 * fileUtil.filemake(filename, desc, "pmpDownlog/"); desc =
	 * contents_server+"  : "+ch+" : "+"/pmp/DownLog.asp : [CID = "+cid+"]";
	 * fileUtil.filemake(filename, desc, "pmpDownlog/"); desc =
	 * contents_server+"  : "+ch+" : "+"/pmp/DownLog.asp : [추가정보 = "+downloginfo+
	 * "]"; fileUtil.filemake(filename, desc, "pmpDownlog/"); pmp다운로그를 남기위 함 종료 DB에
	 * 저장하기 위함 시작 params.put("USER_ID", userid); params.put("MOVIE_NO", cid);
	 * params.put("DOWNLOGINFO", downloginfo);
	 * lectureservice.insertPmpDownLog(params); DB에 저장하기 위함 종료
	 * model.addAttribute("params", params);
	 * 
	 * PrintWriter out = response.getWriter(); out.print("<res>0000</res>"); }
	 *//**
		 * @Method Name : dataMovieSave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 등록
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieSave.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * dataMovieSave(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); int MOVIE_ORDER2 =
	 * Integer.parseInt(params.get("ADD_MOVIE_ORDER2")); for(int i=0;
	 * i<Integer.parseInt(params.get("ADD_LOW")); i++){
	 * params.put("ADD_MOVIE_ORDER2", String.valueOf(MOVIE_ORDER2));
	 * lectureservice.lectureMovieInsert(params); MOVIE_ORDER2 += 1; }
	 * model.addAttribute("params", params); return "/lecture/lecture/returnMsg"; }
	 * 
	 *//**
		 * @Method Name : dataMovieDelete
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 삭제
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieDelete.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * dataMovieAdd(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * HashMap<String, String> vparams = new HashMap<String, String>();
	 * setParam(params, request, model); vparams.put("BRIDGE_LECCODE",
	 * params.get("BRIDGE_LECCODE")); // 동영상 정보 삭제 String[] DEL_ARR =
	 * request.getParameterValues("DEL_ARR"); if(DEL_ARR != null){ for(int i=0;
	 * i<DEL_ARR.length; i++){ String[] DEL_VAL_ARR = DEL_ARR[i].split(",");
	 * vparams.put("MOVIE_NO", DEL_VAL_ARR[0]); vparams.put("MOVIE_ORDER1",
	 * DEL_VAL_ARR[2]); vparams.put("MOVIE_ORDER2", DEL_VAL_ARR[3]);
	 * lectureservice.lectureMovieDelete(vparams); } } // 메모 정보 삭제 String[]
	 * DELMEMO_ARR = request.getParameterValues("DELMEMO_ARR"); if(DELMEMO_ARR !=
	 * null){ for(int i=0; i<DELMEMO_ARR.length; i++){ String[] DELMEMO_ARR_SPLIT =
	 * DELMEMO_ARR[i].toString().split(","); vparams.put("POSITION",
	 * DELMEMO_ARR_SPLIT[0]); vparams.put("MOVIE_ORDER1", DELMEMO_ARR_SPLIT[1]);
	 * vparams.put("MOVIE_ORDER2", DELMEMO_ARR_SPLIT[2]);
	 * lectureservice.lectureMovieMemoDelete(vparams); } }
	 * 
	 * model.addAttribute("params", params); return "/lecture/lecture/returnMsg"; }
	 * 
	 *//**
		 * @Method Name : dataMovieUpdate
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 수정
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieUpdate.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * dataMovieUpdate(ModelMap model, HttpServletRequest request) throws Exception
	 * { HashMap<String, String> params = new HashMap<String, String>();
	 * HashMap<String, String> vparams = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * 
	 * String[] MOVIE_NO = request.getParameterValues("MOVIE_NO"); String[]
	 * MOVIE_ORDER1 = request.getParameterValues("MOVIE_ORDER1"); String[]
	 * MOVIE_ORDER2 = request.getParameterValues("MOVIE_ORDER2"); String[]
	 * MOVIE_ORDERS1 = request.getParameterValues("MOVIE_ORDERS1"); String[]
	 * MOVIE_ORDERS2 = request.getParameterValues("MOVIE_ORDERS2"); String[]
	 * MOVIE_NAME = request.getParameterValues("MOVIE_NAME"); String[] MOVIE_DESC =
	 * request.getParameterValues("MOVIE_DESC"); String[] MOVIE_URL =
	 * request.getParameterValues("MOVIE_URL"); String[] WIDE_URL =
	 * request.getParameterValues("WIDE_URL"); String[] MOVIE_FILENAME1 =
	 * request.getParameterValues("MOVIE_FILENAME1"); String[] MP4_URL =
	 * request.getParameterValues("MP4_URL"); String[] MOVIE_FILENAME2 =
	 * request.getParameterValues("MOVIE_FILENAME2"); String[] MOVIE_FILENAME3 =
	 * request.getParameterValues("MOVIE_FILENAME3"); String[] MOVIE_FILENAME4 =
	 * request.getParameterValues("MOVIE_FILENAME4"); String[] PMP_URL =
	 * request.getParameterValues("PMP_URL"); String[] PMP_FILENAME =
	 * request.getParameterValues("PMP_FILENAME"); String[] MOVIE_TIME =
	 * request.getParameterValues("MOVIE_TIME"); String[] MOVIE_FREE_FLAG =
	 * request.getParameter("MOVIE_FREE_FLAG").toString().split(","); String[] STOP
	 * = request.getParameter("STOP").toString().split(","); String[]
	 * MOVIE_DATA_FILE_YN =
	 * request.getParameter("MOVIE_DATA_FILE_YN").toString().split(","); String[]
	 * MOVIE_DATA_FILENAME = request.getParameterValues("MOVIE_DATA_FILENAME");
	 * 
	 * if(MOVIE_NO != null){ for(int i=0; i<MOVIE_NO.length; i++){
	 * vparams.put("MOVIE_NO", MOVIE_NO[i]); vparams.put("MOVIE_ORDER1",
	 * MOVIE_ORDER1[i]); vparams.put("MOVIE_ORDER2", MOVIE_ORDER2[i]);
	 * vparams.put("MOVIE_NAME", MOVIE_NAME[i]); vparams.put("MOVIE_DESC",
	 * MOVIE_DESC[i]); vparams.put("MOVIE_URL", MOVIE_URL[i]);
	 * vparams.put("WIDE_URL", WIDE_URL[i]); vparams.put("MOVIE_FILENAME1",
	 * MOVIE_FILENAME1[i]); vparams.put("MOVIE_FILENAME4", MOVIE_FILENAME4[i]);
	 * if(!MOVIE_FILENAME1[i].equals("")){ vparams.put("MOVIE_FILENAME1_FLAG",
	 * MOVIE_FILENAME1[i].substring( MOVIE_FILENAME1[i].length()-3,
	 * MOVIE_FILENAME1[i].length())); } if(!MOVIE_FILENAME4[i].equals("")){
	 * vparams.put("MOVIE_FILENAME4_FLAG", MOVIE_FILENAME4[i].substring(
	 * MOVIE_FILENAME4[i].length()-3, MOVIE_FILENAME4[i].length())); }
	 * 
	 * if(!MOVIE_FILENAME4[i].equals("") && MOVIE_FILENAME2[i].equals("")){
	 * vparams.put("MOVIE_FILENAME2", MOVIE_FILENAME4[i].substring(0,
	 * MOVIE_FILENAME4[i].length()-6)+"_3.mp4"); // 저화질 _3 }else{
	 * vparams.put("MOVIE_FILENAME2", MOVIE_FILENAME2[i]); // 저화질 _3 }
	 * 
	 * if(!MOVIE_FILENAME4[i].equals("") && MOVIE_FILENAME3[i].equals("")){
	 * vparams.put("MOVIE_FILENAME3", MOVIE_FILENAME4[i].substring(0,
	 * MOVIE_FILENAME4[i].length()-6)+"_2.mp4"); // 고화질 _2 }else{
	 * vparams.put("MOVIE_FILENAME3", MOVIE_FILENAME3[i]); // 고화질 _2 }
	 * vparams.put("MP4_URL", MP4_URL[i]); vparams.put("PMP_URL", PMP_URL[i]);
	 * vparams.put("PMP_FILENAME", PMP_FILENAME[i]); vparams.put("MOVIE_TIME",
	 * MOVIE_TIME[i]); vparams.put("MOVIE_DATA_FILENAME", MOVIE_DATA_FILENAME[i]);
	 * vparams.put("MOVIE_FREE_FLAG", MOVIE_FREE_FLAG[i]); vparams.put("STOP",
	 * STOP[i]); vparams.put("MOVIE_DATA_FILE_YN", MOVIE_DATA_FILE_YN[i]);
	 * lectureservice.lectureMovieUpdate(vparams); } }
	 * 
	 * vparams.put("BRIDGE_LECCODE", params.get("BRIDGE_LECCODE")); String[]
	 * POSITION = request.getParameterValues("POSITION"); String[] MST_TEXT =
	 * request.getParameterValues("MST_TEXT"); String MOV_ING =
	 * request.getParameter("MOV_ING"); if(MST_TEXT != null){ for(int i=0;
	 * i<MST_TEXT.length; i++){ vparams.put("POSITION", POSITION[i]);
	 * vparams.put("MOVIE_ORDERS1", MOVIE_ORDERS1[i]); vparams.put("MOVIE_ORDERS2",
	 * MOVIE_ORDERS2[i]); vparams.put("MST_TEXT", MST_TEXT[i]);
	 * lectureservice.lectureMovieMemoUpdate(vparams); } } if(MOV_ING != null){
	 * vparams.put("MOV_ING", MOV_ING); lectureservice.lecMovUpdate(vparams); }
	 * 
	 * model.addAttribute("params", params); return "/lecture/lecture/returnMsg"; }
	 * 
	 *//**
		 * @Method Name : dataMovieFile
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 파일업로드 폼
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieFile.pop") public String
	 * dataMovieFile(ModelMap model, HttpServletRequest request) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model); params.put("LOWNUM",
	 * request.getParameter("LOWNUM")); params.put("BLECCODE",
	 * request.getParameter("BLECCODE")); model.addAttribute("params", params);
	 * return "/lecture/lecture/dataMovieFile"; }
	 * 
	 *//**
		 * @Method Name : dataMovieFileSave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 파일업로드 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieFileSave.pop") public String
	 * dataMovieFileSave(ModelMap model, HttpServletRequest request,
	 * MultipartHttpServletRequest multipartRequest) throws Exception {
	 * HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * 
	 * MultipartFile ATTACH_FILE = multipartRequest.getFile("ATTACH_FILE"); String
	 * bridgePath = CommonUtil.isNull(request.getParameter("BLECCODE"), "");
	 * 
	 * Calendar cal = Calendar.getInstance( ); String rootPath =
	 * fsResource.getPath(); //String subPath = "lecture_upload/" +
	 * cal.get(Calendar.YEAR) + "/" ; String subPath = "lecture_upload/" +
	 * bridgePath + "/" ;
	 * 
	 * if(ATTACH_FILE != null && ATTACH_FILE.isEmpty() == false) { HashMap<String,
	 * Object> fileMap1 = fileUtil.uploadFileForRealName(ATTACH_FILE, rootPath,
	 * subPath); params.put("ATTACH_FILE", fileMap1.get("fileFullPath").toString());
	 * }
	 * 
	 * params.put("LOWNUM", request.getParameter("LOWNUM"));
	 * params.put("RETURNDATA", "Y"); model.addAttribute("params", params); return
	 * "/lecture/lecture/dataMovieFile"; }
	 * 
	 *//**
		 * @Method Name : dataMovieFileDelete
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 파일삭제 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieFileDelete.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * dataMovieFileDelete(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * lectureservice.lectureMovieFileDelete(params); String rootPath =
	 * fsResource.getPath(); fileUtil.deleteFile(rootPath +
	 * CommonUtil.isNull(params.get("FILE_DEL_NAME"), ""));
	 * model.addAttribute("params", params); return "/lecture/lecture/returnMsg"; }
	 * 
	 *//**
		 * @Method Name : dataMovieMemoSave
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 단과 PMP/MP4/동영상 클릭시 팝업 메모 등록 프로세스
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
	/*
	 * @RequestMapping(value="/dataMovieMemoSave.pop")
	 * //@Transactional(readOnly=false,rollbackFor=Exception.class) public String
	 * dataMovieMemoSave(ModelMap model, HttpServletRequest request) throws
	 * Exception { HashMap<String, String> params = new HashMap<String, String>();
	 * setParam(params, request, model);
	 * lectureservice.lectureMovieMemoInsert(params); model.addAttribute("params",
	 * params); return "/lecture/lecture/returnMsg"; }
	 * 
	 *//**
		 * @Method Name : lecFileProcess
		 * @작성일 : 2013. 10.
		 * @Method 설명 : 파일처리 프로세스
		 * @param params
		 * @param request
		 * @return HashMap
		 * @throws Exception
		 */
	/*
	 * @SuppressWarnings({ "rawtypes", "unchecked" }) public HashMap<String, String>
	 * lecFileProcess(HashMap params, MultipartHttpServletRequest multipartRequest)
	 * throws Exception { String rootPath = fsResource.getPath(); String subPath =
	 * "lecture_upload/";
	 * 
	 * 
	 * MultipartFile MUST_PRF_IMG = multipartRequest.getFile("MUST_PRF_IMG");
	 * MultipartFile SEL_PRF_IMG = multipartRequest.getFile("SEL_PRF_IMG");
	 * 
	 * 
	 * 
	 * 
	 * if(MUST_PRF_IMG != null && MUST_PRF_IMG.isEmpty() == false) { HashMap<String,
	 * Object> fileMap1 = fileUtil.uploadFile(MUST_PRF_IMG, rootPath, subPath);
	 * params.put("MUST_PRF_IMG", fileMap1.get("fileFullPath").toString());
	 * Thread.sleep(100); } if(SEL_PRF_IMG != null && SEL_PRF_IMG.isEmpty() ==
	 * false) { HashMap<String, Object> fileMap2 = fileUtil.uploadFile(SEL_PRF_IMG,
	 * rootPath, subPath); params.put("SEL_PRF_IMG",
	 * fileMap2.get("fileFullPath").toString()); Thread.sleep(100); }
	 * 
	 * return params; }
	 * 
	 * 
	 * @RequestMapping(value="/oldFreeToNewFree.do")
	 * 
	 * @ResponseBody public HashMap<String, String> oldFreeToNewFree(ModelMap model,
	 * HttpServletRequest request) throws Exception { HashMap<String, String> params
	 * = new HashMap<String, String>();
	 * 
	 * setParam(params, request, model);
	 * 
	 * lectureservice.oldFreeToNewFreeInsert(params);
	 * 
	 * HashMap<String, String> OrdernoMap = new HashMap<String, String>();
	 * 
	 * 
	 * OrdernoMap.put("RES","성공");
	 * 
	 * return OrdernoMap; }
	 * 
	 * @RequestMapping(value="/oldBogangFreeToNewBogangFree.do")
	 * 
	 * @ResponseBody public HashMap<String, String>
	 * oldBogangFreeToNewBogangFree(ModelMap model, HttpServletRequest request)
	 * throws Exception { HashMap<String, String> params = new HashMap<String,
	 * String>();
	 * 
	 * setParam(params, request, model);
	 * 
	 * lectureservice.oldBogangFreeToNewBogangFree(params);
	 * 
	 * HashMap<String, String> OrdernoMap = new HashMap<String, String>();
	 * 
	 * 
	 * OrdernoMap.put("RES","성공");
	 * 
	 * return OrdernoMap; }
	 * 
	 * 
	 *//**
		 * @Method Name : setParam
		 * @작성일 : 2013. 10.
		 * @Method 설명 : 파라미터 SETTING
		 * @param params
		 * @param request
		 * @return HashMap
		 * @throws Exception
		 *//*
			 * public void setParam(HashMap<String, String> params, HttpServletRequest
			 * request, ModelMap model) throws Exception { HttpSession session =
			 * request.getSession(false); HashMap<String, String> loginInfo =
			 * (HashMap)session.getAttribute("AdmUserInfo");
			 * params.put("REG_ID",loginInfo.get("USER_ID"));
			 * params.put("UPD_ID",loginInfo.get("USER_ID")); params.put("currentPage",
			 * CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			 * params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"),
			 * propertiesService.getInt("pageUnit")+""));
			 * 
			 * params.put("SEARCHTYPE",
			 * CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			 * params.put("SEARCHTEXT",
			 * CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
			 * params.put("SEARCHPAYYN",
			 * CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
			 * params.put("SEARCHPAYTYPE",
			 * CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
			 * params.put("SEARCHTEXT",
			 * CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
			 * params.put("SEARCHKIND",
			 * CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
			 * params.put("SEARCHFORM",
			 * CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
			 * params.put("SEARCHYEAR",
			 * CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
			 * params.put("SEARCHOPENPAGE",
			 * CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
			 * params.put("SEARCHISUSE",
			 * CommonUtil.isNull(request.getParameter("SEARCHISUSE"), ""));
			 * params.put("S_LEC_BAESU",
			 * CommonUtil.isNull(request.getParameter("S_LEC_BAESU"), ""));
			 * 
			 * params.put("ADDBOOKAREA",
			 * CommonUtil.isNull(request.getParameter("ADDBOOKAREA"), ""));
			 * params.put("ADDAREA", CommonUtil.isNull(request.getParameter("ADDAREA"),
			 * "")); params.put("JSEQ", CommonUtil.isNull(request.getParameter("JSEQ"),
			 * "")); params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
			 * params.put("BRIDGE_LECCODE",
			 * CommonUtil.isNull(request.getParameter("BRIDGE_LECCODE"), ""));
			 * params.put("CATEGORY_CD",
			 * CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
			 * params.put("LEARNING_CD",
			 * CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
			 * params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE"),
			 * "")); params.put("ORG_LECCODE",
			 * CommonUtil.isNull(request.getParameter("ORG_LECCODE"), ""));
			 * params.put("LEC_TYPE_CHOICE",
			 * CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
			 * params.put("PLAN", CommonUtil.isNull(request.getParameter("PLAN"), ""));
			 * params.put("SUBJECT_TEACHER",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER"), ""));
			 * params.put("SUBJECT_TEACHER_PAYMENT",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER_PAYMENT"), ""));
			 * params.put("SUBJECT_TITLE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_TITLE"), ""));
			 * params.put("SUBJECT_DESC",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_DESC"), ""));
			 * params.put("SUBJECT_MEMO",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MEMO"), ""));
			 * params.put("SUBJECT_KEYWORD",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_KEYWORD"), ""));
			 * params.put("SUBJECT_PERIOD",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PERIOD"), ""));
			 * params.put("SUBJECT_OFF_OPEN_YEAR",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_YEAR"), ""));
			 * params.put("SUBJECT_OFF_OPEN_MONTH",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_MONTH"), ""));
			 * params.put("SUBJECT_OFF_OPEN_DAY",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_DAY"), ""));
			 * params.put("SUBJECT_DISCOUNT",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_DISCOUNT"), ""));
			 * params.put("SEARCHGIFT",
			 * CommonUtil.isNull(request.getParameter("SEARCHGIFT"), ""));
			 * params.put("GIFT_SEARCHTYPE",
			 * CommonUtil.isNull(request.getParameter("GIFT_SEARCHTYPE"), ""));
			 * params.put("SEARCHGIFTTEXT",
			 * CommonUtil.isNull(request.getParameter("SEARCHGIFTTEXT"), ""));
			 * params.put("GIFT_FLAG", CommonUtil.isNull(request.getParameter("GIFT_FLAG"),
			 * "")); params.put("GIFT_LECCODE",
			 * CommonUtil.isNull(request.getParameter("GIFT_LECCODE"), ""));
			 * params.put("MO_COUPON_CCODE",
			 * CommonUtil.isNull(request.getParameter("MO_COUPON_CCODE"), ""));
			 * params.put("MO_COUPON_NAME",
			 * CommonUtil.isNull(request.getParameter("MO_COUPON_NAME"), ""));
			 * params.put("GIFT_LECCODE",
			 * CommonUtil.isNull(request.getParameter("GIFT_LECCODE"), ""));
			 * params.put("GIFT_COUPON_CCODE",
			 * CommonUtil.isNull(request.getParameter("GIFT_COUPON_CCODE"), ""));
			 * params.put("COUPON_NAME",
			 * CommonUtil.isNull(request.getParameter("COUPON_NAME"), ""));
			 * params.put("GIFT_NAME", CommonUtil.isNull(request.getParameter("GIFT_NAME"),
			 * "")); params.put("TEACHERNO",
			 * CommonUtil.isNull(request.getParameter("TEACHERNO"), ""));
			 * params.put("MO_SEARCHTYPE",
			 * CommonUtil.isNull(request.getParameter("MO_SEARCHTYPE"), ""));
			 * params.put("SEARCHMOTEXT",
			 * CommonUtil.isNull(request.getParameter("SEARCHMOTEXT"), ""));
			 * 
			 * if("N".equals(params.get("LEC_TYPE_CHOICE"))){ params.put("SUBJECT_PRICE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), "")); }else{
			 * params.put("SUBJECT_PRICE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), "0")); }
			 * 
			 * params.put("SUBJECT_POINT",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_POINT"), "0"));
			 * params.put("SUBJECT_MOVIE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE"), "0"));
			 * params.put("SUBJECT_PMP",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PMP"), "0"));
			 * params.put("SUBJECT_MOVIE_PMP",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_PMP"), "0"));
			 * params.put("SUBJECT_MOVIE_MP4",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_MP4"), "0"));
			 * params.put("SUBJECT_MOVIE_VOD_MP4",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_VOD_MP4"), "0"));
			 * params.put("SUBJECT_OPTION",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_OPTION"), ""));
			 * params.put("SUBJECT_ISUSE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_ISUSE"), ""));
			 * params.put("SUBJECT_SJT_CD",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_SJT_CD"), ""));
			 * params.put("SUBJECT_VOD_DEFAULT_PATH",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_VOD_DEFAULT_PATH"), ""));
			 * params.put("SUBJECT_WIDE_DEFAULT_PATH",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_WIDE_DEFAULT_PATH"), ""));
			 * params.put("SUBJECT_MP4_DEFAULT_PATH",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MP4_DEFAULT_PATH"), ""));
			 * params.put("SUBJECT_PMP_DEFAULT_PATH",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PMP_DEFAULT_PATH"), ""));
			 * params.put("SUBJECT_PASS",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_PASS"), ""));
			 * params.put("SUBJECT_JANG",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_JANG"), ""));
			 * params.put("RE_COURSE", CommonUtil.isNull(request.getParameter("RE_COURSE"),
			 * "")); params.put("LEC_SCHEDULE",
			 * CommonUtil.isNull(request.getParameter("LEC_SCHEDULE"), ""));
			 * params.put("LEC_COUNT", CommonUtil.isNull(request.getParameter("LEC_COUNT"),
			 * "")); params.put("NO", CommonUtil.isNull(request.getParameter("NO"), "0"));
			 * params.put("ADD_LOW", CommonUtil.isNull(request.getParameter("ADD_LOW"),
			 * "")); params.put("ADD_MOVIE_ORDER1",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER1"), ""));
			 * params.put("ADD_MOVIE_ORDER2",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER2"), ""));
			 * params.put("ADD_MOVIE_NAME",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_NAME"), ""));
			 * params.put("ADD_MOVIE_DESC",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_DESC"), ""));
			 * params.put("ADD_MOVIE_URL",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_URL"), ""));
			 * params.put("ADD_WIDE_URL",
			 * CommonUtil.isNull(request.getParameter("ADD_WIDE_URL"), ""));
			 * params.put("ADD_MOVIE_FILENAME1",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME1"), ""));
			 * params.put("ADD_MP4_URL",
			 * CommonUtil.isNull(request.getParameter("ADD_MP4_URL"), ""));
			 * params.put("ADD_MOVIE_FILENAME2",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME2"), ""));
			 * params.put("ADD_PMP_URL",
			 * CommonUtil.isNull(request.getParameter("ADD_PMP_URL"), ""));
			 * params.put("ADD_MOVIE_FILENAME3",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME3"), ""));
			 * params.put("ADD_MOVIE_FILENAME4",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME4"), ""));
			 * params.put("ADD_PMP_FILENAME",
			 * CommonUtil.isNull(request.getParameter("ADD_PMP_FILENAME"), ""));
			 * params.put("ADD_MOVIE_TIME",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_TIME"), ""));
			 * params.put("ADD_MOVIE_FREE_FLAG",
			 * CommonUtil.isNull(request.getParameter("ADD_MOVIE_FREE_FLAG"), "N"));
			 * params.put("FILE_DEL_NO",
			 * CommonUtil.isNull(request.getParameter("FILE_DEL_NO"), ""));
			 * params.put("FILE_DEL_NAME",
			 * CommonUtil.isNull(request.getParameter("FILE_DEL_NAME"), ""));
			 * params.put("MOVIE_ORDER1",
			 * CommonUtil.isNull(request.getParameter("MOVIE_ORDER1"), ""));
			 * params.put("MOVIE_ORDER2",
			 * CommonUtil.isNull(request.getParameter("MOVIE_ORDER2"), ""));
			 * params.put("MST_TEXT", CommonUtil.isNull(request.getParameter("MST_TEXT"),
			 * "")); params.put("POSITION",
			 * CommonUtil.isNull(request.getParameter("POSITION"), ""));
			 * params.put("TOP_MENU_ID",
			 * CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
			 * params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"),
			 * "")); params.put("L_MENU_NM",
			 * CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
			 * params.put("LEC_GUBUN", CommonUtil.isNull(request.getParameter("LEC_GUBUN"),
			 * "")); params.put("REC_GUBUN",
			 * CommonUtil.isNull(request.getParameter("REC_GUBUN"), ""));
			 * params.put("MOV_ING", CommonUtil.isNull(request.getParameter("MOV_ING"),
			 * "")); params.put("ICON_GUBUN",
			 * CommonUtil.isNull(request.getParameter("ICON_GUBUN"), ""));
			 * params.put("FREE_TAB", CommonUtil.isNull(request.getParameter("FREE_TAB"),
			 * "")); params.put("SUBJECT_MONITORMODE",
			 * CommonUtil.isNull(request.getParameter("SUBJECT_MONITORMODE"), ""));
			 * params.put("LEC_BAESU", CommonUtil.isNull(request.getParameter("LEC_BAESU"),
			 * "00")); params.put("DownType",
			 * CommonUtil.isNull(request.getParameter("DownType"), "X"));
			 * 
			 * params.put("MUST_PRF_IMG_DEL",
			 * CommonUtil.isNull(request.getParameter("MUST_PRF_IMG_DEL"), ""));
			 * params.put("SEL_PRF_IMG_DEL",
			 * CommonUtil.isNull(request.getParameter("SEL_PRF_IMG_DEL"), ""));
			 * params.put("MUST_PRF_IMG_DELNM",
			 * CommonUtil.isNull(request.getParameter("MUST_PRF_IMG_DELNM"), ""));
			 * params.put("SEL_PRF_IMG_DELNM",
			 * CommonUtil.isNull(request.getParameter("SEL_PRF_IMG_DELNM"), ""));
			 * 
			 * params.put("MUST_PRF_IMG", ""); params.put("SEL_PRF_IMG", "");
			 * 
			 * model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			 * model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			 * model.addAttribute("L_MENU_NM", params.get("L_MENU_NM")); }
			 */
}