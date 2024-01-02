package com.willbes.web.tm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.tm.service.TMService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value = "/tm")
@Controller
public class TMController {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private TMService tmservice;

	/**
	 * @Method Name : tmAlloc.do
	 * @작성일 : 2013. 12.30
	 * @Method 설명 : TM회원 배정
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/tmAlloc.do")
	public String tmAlloc(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sToday = sdf.format(today);

		params.put("CMD", CommonUtil.isNull(request.getParameter("CMD"), "view"));
		params.put("USERTYPE", CommonUtil.isNull(request.getParameter("sUSERTYPE"), "A"));
		params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), sToday));
		params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), sToday));

		// TM직원(관리자) 정보 리스트
		List<HashMap<String, String>> tmadminlist = null;
		// TM 배정할 회원수 조회
		String allocCnt = "0";

		// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
		HashMap<String, String> paramstm = new HashMap<String, String>();
		paramstm.put("USER_ID", params.get("USER_ID"));
		List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
		if (isTmAdmin == null || isTmAdmin.isEmpty()) {
			// TM 통합관리자 이므로 회원 배정 가능
			if ("alloc".equals(params.get("CMD"))){
				// USERTYPE별 TM 직원별 배정하는 루틴
				String[] TmAdminIDs = request.getParameter("TMADMINS").split(",");
				String[] TmAdminNames = request.getParameter("TMADMINNAMES").split(",");
				String[] TmAdminANOs = request.getParameter("TMALLOCNOS").split(",");
				params.put("USERSOURCE", CommonUtil.isNull(request.getParameter("USERSOURCE"), ""));

				if(TmAdminIDs != null){
					for(int j=0; j<TmAdminIDs.length; j++){
						if (!"".equals(TmAdminIDs[j])) {
							params.put("TMADMINID", TmAdminIDs[j]);
							params.put("TMADMINNAME", TmAdminNames[j]);
							params.put("ALLOC_COUNT", TmAdminANOs[j]);
							tmservice.insertTBTMUsersProcess(params);
						}
					}
				}
			}
			// TM 배정할 회원수 조회
			allocCnt = tmservice.getTmMemberCount(params);
			params.put("TMMANAGERYN", "Y");
		} else {
			params.put("TMMANAGERYN", "N");  	// TM 상담원
		}

		tmadminlist = tmservice.tmAdminList(params);
		model.addAttribute("params", params);
		model.addAttribute("alloccnt", allocCnt);
		model.addAttribute("tmadminlist", tmadminlist);

		return "/tm/tmAlloc";
	}

	/**
	 * @Method Name : tmMemberList
	 * @작성일 : 2014. 1.1
	 * @Method 설명 : TM회원관리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/tmMemberList.do")
	public String tmMemberList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sToday = sdf.format(today);

		String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
		String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		if ("all".equals(params.get("CMD"))){
			// 전체 검색인 경우 시작일, 종료일 기간을 삭제한다.
			params.put("SDATE", "");
			params.put("EDATE", "");
		} else {
			params.put("SDATE", startDate);
			params.put("EDATE", endDate);
		}

		// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
		HashMap<String, String> paramstm = new HashMap<String, String>();
		paramstm.put("USER_ID", params.get("USER_ID"));
		List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
		if (isTmAdmin == null || isTmAdmin.isEmpty()) {
			params.put("TMADMINID", "");  // TM 통합관리자
		} else {
			params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
		}

		List<HashMap<String, String>> list = tmservice.tmMemberList(params);

		// 총 건수
		String slistCount = tmservice.tmMemberListCount(params);
		if ("".equals(slistCount) || slistCount.isEmpty()) {
			slistCount = "0";
		}
		int listCount = Integer.parseInt(slistCount);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		params.put("SDATE", startDate);
		params.put("EDATE", endDate);

		model.addAttribute("params", params);
		model.addAttribute("list", list);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/tm/tmMemberList";
	}

	/**
	 * @Method Name : tmMemberList excel 다운로드
	 * @작성일 : 2014. 1.1
	 * @Method 설명 : TM회원관리 엑셀 다운로드
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/tmMemberList_excel.do")
	public View tmMemberList_excel(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sToday = sdf.format(today);

		String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
		String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));

		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		if ("all".equals(params.get("CMD"))){
			// 전체 검색인 경우 시작일, 종료일 기간을 삭제한다.
			params.put("SDATE", "");
			params.put("EDATE", "");
		} else {
			params.put("SDATE", startDate);
			params.put("EDATE", endDate);
		}

		// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
		HashMap<String, String> paramstm = new HashMap<String, String>();
		paramstm.put("USER_ID", params.get("USER_ID"));
		List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
		if (isTmAdmin == null || isTmAdmin.isEmpty()) {
			params.put("TMADMINID", "");  // TM 통합관리자
		} else {
			params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
		}

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = tmservice.tmMemberListExcel(params);

		if(log.isDebugEnabled()){
			log.debug("***** exe_list : "+ exe_list) ;
			log.debug("***** exe_list.size() : "+ exe_list.size()) ;
		}

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "TM회원관리목록(" + startDate + "~" + endDate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("순번");
		headerList.add("이름");
		headerList.add("아이디");
		headerList.add("전화번호");
		headerList.add("핸드폰");
		headerList.add("분류");
		headerList.add("관리자");
		headerList.add("배정일");
		headerList.add("최종통화일");
		headerList.add("장바구니정보");

		int rno = 1;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> params1 = new HashMap<String, String>();
		List<HashMap<String, String>> cartlist = null;
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(rno++));
			newMap.put(i++, map.get("USERNAME"));
			newMap.put(i++, map.get("USERID"));
			newMap.put(i++, map.get("TEL"));
			newMap.put(i++, map.get("PHONE"));
			newMap.put(i++, map.get("USERTYPE_NM"));
			newMap.put(i++, map.get("ADMUSERNAME"));
			newMap.put(i++, map.get("ALLOCDATE"));
			newMap.put(i++, map.get("REGDATE"));
			// 장바구니인 경우 장바구니 정보 추가

			StringBuilder sCartInfo = new StringBuilder();
			if ("C".equals(map.get("USERTYPE"))) {
				params1.put("USER_ID",map.get("USERID"));
				if(log.isDebugEnabled()){
					log.debug("***** USER_ID : "+ map.get("USERID")) ;
				}
				// 장바구니 정보를 가져옴
				cartlist = tmservice.getTBCCCartInfo(params1);

				if(log.isDebugEnabled()){
					log.debug("***** list_second : "+ cartlist) ;
					log.debug("***** list_second.size() : "+ cartlist.size()) ;
				}

				if (cartlist.size() > 0) {
					for (int k = 0; k < cartlist.size(); k++) {
						HashMap map2 = (HashMap) cartlist.get(k);
						sCartInfo.append("[").append(map2.get("LECCODE")).append("] ").append(map2.get("LRNTYPE"));
						sCartInfo.append(map2.get("KDTYPE")).append(" ").append(map2.get("SUBJECT_TEACHER_NM"));
						sCartInfo.append(" ").append(map2.get("SUBJECT_TITLE")).append("]").append(map2.get("SUBJECT_TEACHER_NM"));
						sCartInfo.append(" (원가: ").append(map2.get("SUBJECT_PRICE")).append(") ☞ ").append(map2.get("DISCOUNT_PRICE"));
						sCartInfo.append("\n");
					}
				}
			}

			newMap.put(i++, sCartInfo.toString());

			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

		/**
		 * @Method Name : tmBoardList.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM상담내용
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@RequestMapping(value = "/tmBoardList.do")
		public String tmBoardList(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			String cmd = CommonUtil.isNull(request.getParameter("cmd"), "list");

			if ("delete".equals(cmd)) {
				// 상담내용 삭제
				HashMap<String, String> subparams = new HashMap<String, String>();
				subparams.put("IDX", CommonUtil.isNull(request.getParameter("idx"), "")); 	// idx
				subparams.put("VOCCODE", CommonUtil.isNull(request.getParameter("vcode"), "")); 	// voccode
				subparams.put("REQUSERID", CommonUtil.isNull(request.getParameter("userid"), "")); 	// userid
				tmservice.tmBoardDelete(subparams);
			}
			if ("modify".equals(cmd)) {
				// 상담내용 삭제
				HashMap<String, String> subparams = new HashMap<String, String>();
				subparams.put("IDX", CommonUtil.isNull(request.getParameter("idx"), "")); 	// idx
				subparams.put("VOCCODE", CommonUtil.isNull(request.getParameter("MVOCCODE"), "")); 	// voccode
				subparams.put("DUTYCODE", CommonUtil.isNull(request.getParameter("MDUTYCODE"), "")); 	// dutycode
				subparams.put("CONTENT", CommonUtil.isNull(request.getParameter("MCONTENT"), "")); 	// dutycode
				tmservice.tmBoardUpdate(subparams);
			}

			int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			int startNo = (currentPage - 1) * pageRow;
			int endNo = startNo + pageRow;

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);
			params.put("DUTYCODE", CommonUtil.isNull(request.getParameter("DUTYCODE"), ""));	// 카테고리 (직급 구분, 독서실 등)
			params.put("VOCCODE", CommonUtil.isNull(request.getParameter("VOCCODE"), "")); 	// 상담상태

			params.put("currentPage", String.valueOf(currentPage));
			params.put("pageRow", String.valueOf(pageRow));

			params.put("startNo", String.valueOf(startNo));
			params.put("endNo", String.valueOf(endNo));

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			List<HashMap<String, String>> list = tmservice.tmBoardList(params);

			// 총 건수
			String slistCount = tmservice.tmBoardListCount(params);
			if ("".equals(slistCount) || slistCount.isEmpty()) {
				slistCount = "0";
			}
			int listCount = Integer.parseInt(slistCount);

			//페이징 처리
			String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			model.addAttribute("params", params);
			model.addAttribute("list", list);
			model.addAttribute("vocCodelist", tmservice.getVOCCODEList(params));	// 상담상태(결과)
			model.addAttribute("dutyCodelist", tmservice.getDUTYCODEList(params));	// 직급 카테고리(독서실 포함)
			model.addAttribute("pagingHtml", pagingHtml);
			model.addAttribute("totalCount", listCount);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageRow", pageRow);
			model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

			return "/tm/tmBoardList";
		}

		/**
		 * @Method Name : tmBoardList_Excel.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM상담내용 엑셀 다운로드
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value = "/tmBoardList_excel.do")
		public View tmBoardList_excel(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);
			params.put("DUTYCODE", CommonUtil.isNull(request.getParameter("DUTYCODE"), ""));	// 카테고리 (직급 구분, 독서실 등)
			params.put("VOCCODE", CommonUtil.isNull(request.getParameter("VOCCODE"), "")); 	// 상담상태

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			//엑셀 리스트
			List<HashMap<String, String>> exe_list = tmservice.tmBoardListExcel(params);

			if(log.isDebugEnabled()){
				log.debug("***** exe_list : "+ exe_list) ;
				log.debug("***** exe_list.size() : "+ exe_list.size()) ;
			}

			Date date = new Date();
		    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

			String excelName = "TM 상담내용 목록(" + startDate + "~" + endDate + ")_" + simpleDate.format(date);

			List<String> headerList = new ArrayList<String>();
			headerList.add("No");
			headerList.add("접수시간");
			headerList.add("상담자");
			headerList.add("회원");
			headerList.add("아이디");
			headerList.add("전화번호");
			headerList.add("핸드폰");
			headerList.add("구분");
			headerList.add("분류");
			headerList.add("상담내용");

			int rno = 1;
			List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
			for(HashMap<String, String> map : exe_list) {
				HashMap newMap = new HashMap();
				int i = 0;
				newMap.put(i++, String.valueOf(rno++));
				newMap.put(i++, map.get("REG_DT"));
				newMap.put(i++, map.get("REGUSERNAME"));
				newMap.put(i++, map.get("REQUSERNAME"));
				newMap.put(i++, map.get("REQUSERID"));
				newMap.put(i++, map.get("TEL_NO"));
				newMap.put(i++, map.get("PHONE_NO"));
				newMap.put(i++, map.get("DUTYCODE_NM"));
				newMap.put(i++, map.get("VOCCODE_NM"));
				newMap.put(i++, map.get("CONTENT"));
				newList.add(newMap);
			}

			model.addAttribute("excelName", excelName);
			model.addAttribute("headerList", headerList);
			model.addAttribute("dataList", newList);

			return new ExcelDownloadView();
		}

		/**
		 * @Method Name : tmOrderList.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM 결제 리스트
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@RequestMapping(value = "/tmOrderList.do")
		public String tmOrderList(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			int startNo = (currentPage - 1) * pageRow;
			int endNo = startNo + pageRow;

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);

			params.put("currentPage", String.valueOf(currentPage));
			params.put("pageRow", String.valueOf(pageRow));

			params.put("startNo", String.valueOf(startNo));
			params.put("endNo", String.valueOf(endNo));

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			List<HashMap<String, String>> list = tmservice.tmOrderList(params);

			// 총 건수, 정상가격 합계, 구매가격 합계
			HashMap<String, String> resultMap = tmservice.tmOrderListCount(params);
			int listCount = 0;
			if (resultMap != null) {
				listCount = Integer.parseInt(String.valueOf(resultMap.get("CNT")));
			}

			//페이징 처리
			String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			model.addAttribute("params", params);
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("list", list);
			model.addAttribute("pagingHtml", pagingHtml);
			model.addAttribute("totalCount", listCount);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageRow", pageRow);
			model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

			return "/tm/tmOrderList";
		}

		/**
		 * @Method Name : tmBoardList_Excel.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM상담내용 엑셀 다운로드
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value = "/tmOrderList_excel.do")
		public View tmOrderList_excel(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);
			params.put("DUTYCODE", CommonUtil.isNull(request.getParameter("DUTYCODE"), ""));	// 카테고리 (직급 구분, 독서실 등)
			params.put("VOCCODE", CommonUtil.isNull(request.getParameter("VOCCODE"), "")); 	// 상담상태

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			//엑셀 리스트
			List<HashMap<String, String>> exe_list = tmservice.tmOrderListExcel(params);

			if(log.isDebugEnabled()){
				log.debug("***** exe_list : "+ exe_list) ;
				log.debug("***** exe_list.size() : "+ exe_list.size()) ;
			}

			Date date = new Date();
		    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

			String excelName = "TM 결제리스트(" + startDate + "~" + endDate + ")_" + simpleDate.format(date);

			List<String> headerList = new ArrayList<String>();
			headerList.add("No");
			headerList.add("주문번호");
			headerList.add("주문자");
			headerList.add("주문자 ID");
			headerList.add("결제일");
			headerList.add("강의명");
			headerList.add("강의금액");
			headerList.add("결제금액");
			headerList.add("상담원");
			headerList.add("상담원ID");
			headerList.add("배정일");
			headerList.add("최종통화일");

			int rno = 1;
			List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> params1 = new HashMap<String, String>();
			List<HashMap<String, String>> cartlist = null;
			for(HashMap<String, String> map : exe_list) {
				HashMap newMap = new HashMap();
				int i = 0;
				newMap.put(i++, String.valueOf(rno++));
				newMap.put(i++, map.get("ORDERNO"));
				newMap.put(i++, map.get("REQUSERNAME"));
				newMap.put(i++, map.get("REQUSERID"));
				newMap.put(i++, map.get("PAYDATE"));
				newMap.put(i++, map.get("SUBJECT_TITLE"));
				newMap.put(i++, map.get("SUBJECT_PRICE"));
				newMap.put(i++, map.get("PRICE"));
				newMap.put(i++, map.get("REGUSERNAME"));
				newMap.put(i++, map.get("REGUSERID"));
				newMap.put(i++, map.get("LASTALLOCDATE"));
				newMap.put(i++, map.get("LASTREGDATE"));

				newList.add(newMap);
			}

			model.addAttribute("excelName", excelName);
			model.addAttribute("headerList", headerList);
			model.addAttribute("dataList", newList);

			return new ExcelDownloadView();
		}

		/**
		 * @Method Name : tmRefundList.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM 환불취소리스트
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@RequestMapping(value = "/tmRefundList.do")
		public String tmRefundList(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			int startNo = (currentPage - 1) * pageRow;
			int endNo = startNo + pageRow;

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);

			params.put("currentPage", String.valueOf(currentPage));
			params.put("pageRow", String.valueOf(pageRow));

			params.put("startNo", String.valueOf(startNo));
			params.put("endNo", String.valueOf(endNo));

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			List<HashMap<String, String>> list = tmservice.tmRefundList(params);

			// 검색에 포함된 총 건수
			String slistCount = tmservice.tmRefundListCount(params);
			if ("".equals(slistCount) || slistCount.isEmpty()) {
				slistCount = "0";
			}
			int listCount = Integer.parseInt(slistCount);

			// 검색조건에 포함된 환불취소 금액
			HashMap<String, String> resultMap = tmservice.tmCancelPayment(params);

			//페이징 처리
			String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			model.addAttribute("params", params);
			model.addAttribute("list", list);
			model.addAttribute("pagingHtml", pagingHtml);
			model.addAttribute("totalCount", listCount);
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageRow", pageRow);
			model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

			return "/tm/tmRefundList";
		}


		/**
		 * @Method Name : tmRefundList_Excel.do
		 * @작성일 : 2014. 1.2
		 * @Method 설명 : TM상담내용 엑셀 다운로드
		 * @param model
		 * @param request
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value = "/tmRefundList_excel.do")
		public View tmRefundList_excel(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			setParam(params, request, model);

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String sToday = sdf.format(today);

			String startDate =  CommonUtil.isNull(request.getParameter("SDATE"), sToday);
			String endDate = CommonUtil.isNull(request.getParameter("EDATE"), sToday);
			params.put("DUTYCODE", CommonUtil.isNull(request.getParameter("DUTYCODE"), ""));	// 카테고리 (직급 구분, 독서실 등)
			params.put("VOCCODE", CommonUtil.isNull(request.getParameter("VOCCODE"), "")); 	// 상담상태

			params.put("SDATE", startDate);
			params.put("EDATE", endDate);

			// TM 총괄관리자는 TB_TM_ADMIN 에 없고 TM상담원만 TB_TM_ADMIN 에 있다
			HashMap<String, String> paramstm = new HashMap<String, String>();
			paramstm.put("USER_ID", params.get("USER_ID"));
			List<HashMap<String, String>> isTmAdmin = tmservice.tmIsTmAdmin(paramstm);
			if (isTmAdmin == null || isTmAdmin.isEmpty()) {
				params.put("TMADMINID", "");  // TM 통합관리자
			} else {
				params.put("TMADMINID", params.get("USER_ID"));  	// TM 상담원
			}

			//엑셀 리스트
			List<HashMap<String, String>> exe_list = tmservice.tmRefundListExcel(params);

			if(log.isDebugEnabled()){
				log.debug("***** exe_list : "+ exe_list) ;
				log.debug("***** exe_list.size() : "+ exe_list.size()) ;
			}

			Date date = new Date();
		    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

			String excelName = "TM 환불취소 목록(" + startDate + "~" + endDate + ")_" + simpleDate.format(date);

			List<String> headerList = new ArrayList<String>();
			headerList.add("No");
			headerList.add("주문번호");
			headerList.add("주문자");
			headerList.add("주문자ID");
			headerList.add("결제일");
			headerList.add("취소환불일");
			headerList.add("강의명");
			headerList.add("판매가");
			headerList.add("환불취소금액");
			headerList.add("상담원");
			headerList.add("상담원ID");
			headerList.add("배정일");
			headerList.add("최종통화일");


			int rno = 1;
			List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
			for(HashMap<String, String> map : exe_list) {
				HashMap newMap = new HashMap();
				int i = 0;
				newMap.put(i++, String.valueOf(rno++));
				newMap.put(i++, map.get("ORDERNO"));
				newMap.put(i++, map.get("REQUSERNAME"));
				newMap.put(i++, map.get("REQUSERID"));
				newMap.put(i++, map.get("CONFIRMDATE"));
				newMap.put(i++, map.get("CONFIRMCANCELDATE"));
				newMap.put(i++, map.get("SUBJECT_TITLE"));
				newMap.put(i++, map.get("SALEPRICE"));
				newMap.put(i++, map.get("CONFIRMCANCELPRICE"));
				newMap.put(i++, map.get("REGUSERNAME"));
				newMap.put(i++, map.get("REGUSERID"));
				newMap.put(i++, map.get("LASTALLOCDATE"));
				newMap.put(i++, map.get("LASTREGDATE"));

				newList.add(newMap);
			}

			model.addAttribute("excelName", excelName);
			model.addAttribute("headerList", headerList);
			model.addAttribute("dataList", newList);

			return new ExcelDownloadView();
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
		params.put("USER_ID",loginInfo.get("USER_ID"));
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
	    params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
	    params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
	    params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
		params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("SEARCHGUBN", "T");

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

}
