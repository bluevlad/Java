package com.willbes.web.room;

import java.io.File;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.room.service.RoomService;

import egovframework.rte.fdl.property.EgovPropertyService;

import com.willbes.web.memberManagement.service.MemberManagementService;

@RequestMapping(value = "/room")
@Controller
public class RoomController {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private RoomService roomService;
	@Autowired
	private MemberManagementService memberManagementService;
	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.07
	 * @Method 설명 : 독서실 관리 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomList.do")
	public String roomList(ModelMap model, HttpServletRequest request) throws Exception {
		List<HashMap<String, String>> list = null;
		HashMap<String, String> params = new HashMap<String, String>();

		// 독서실 정보 리스트
		list = roomService.roomList(params);

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("menuParams", menuParams);

		model.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("list", list);
		model.addAttribute("params", params);
		model.addAttribute("menuparams", menuParams);

		return "room/roomlist";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.07
	 * @Method 설명 : 독서실 관리 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomView.do")
	public String roomView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("menuParams", menuParams);
		//메뉴 param

		// 독서실 ROOM_CD로 상세정보를 가져온다.
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));

		HashMap<String, String> roomDetail  = roomService.getRoomDetail(params);
		List<HashMap<String, String>> roomNumList = roomService.getRoomNumList(params);

		//메뉴 param
		model.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param
		model.addAttribute("params",params);
		model.addAttribute("roomdetail",roomDetail);
		model.addAttribute("roomnumList",roomNumList);

		return "room/roomView";
	}

	/**
	 * @Method Name : roomInsert
	 * @작성일 : 2013. 11.08
	 * @Method 설명 : 독서실 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomInsert.do")
	public String roomInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("menuParams", menuParams);

		model.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "/room/roominsert";
	}


	/**
	 * @Method Name : roomInsert
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 독서실 대여 신청 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomRentWrite.do")
	public String roomRentWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		//메뉴 param

		// 독서실 ROOM_CD로 상세정보를 가져온다.
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("ROOM_NM", CommonUtil.isNull(request.getParameter("ROOM_NM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("ORDER_ID", "");

		HashMap<String, String> roomNumRentDetail  = null;
		HashMap<String, String> roomNumRentOrderDetail = null;
		if (!params.get("RENT_SEQ").isEmpty()) {
			// 독서실 대여 신청 정보(현재)
			roomNumRentDetail  = roomService.roomNumRentDetail(params);

			// 독서실 대여 현재 결제 정보를 가져온다
			if (!roomNumRentDetail.isEmpty()){
				params.put("ORDER_ID", CommonUtil.isNull(roomNumRentDetail.get("ORDER_ID"), ""));
				roomNumRentOrderDetail = roomService.roomNumRentOrderDetail(params);
			}
		}

		if (roomNumRentOrderDetail == null){
			model.addAttribute("WMODE", "INS");
		} else {
			model.addAttribute("WMODE", "EDT");
		}

		// 독서실 대여 결제 이력들
		List<HashMap<String, String>> roomNumRentOrderList = roomService.roomNumRentOrderList(params);

		//메뉴 param
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("ROOM_NM", params.get("ROOM_NM"));
		model.addAttribute("RENT_SEQ", params.get("RENT_SEQ"));
		model.addAttribute("roomnumrentdetail",roomNumRentDetail);
		model.addAttribute("roomnumrentorderdetail",roomNumRentOrderDetail);
		model.addAttribute("roomNumRentOrderList",roomNumRentOrderList);

		return "/room/roomRentWrite";
	}

	/**
	 * @Method Name : roomOrderList.do
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 독서실 신청 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomOrderList.do")
	public String roomOrderList(ModelMap model, HttpServletRequest request) throws Exception {

		List<HashMap<String, String>> list = null;

		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("menuParams", menuParams);
		//메뉴 param

		String message = CommonUtil.isNull(request.getParameter("message"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("CURRENTPAGE"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String orderstatuscode = CommonUtil.isNull(request.getParameter("ORDERSTATUSCODE"), "");
		String renttype = CommonUtil.isNull(request.getParameter("RENTTYPE"), "");
		String sdate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String edate = CommonUtil.isNull(request.getParameter("EDATE"), "");
    	String searchkey = CommonUtil.isNull(request.getParameter("SEARCHKEY"), "");
    	String searchtype = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("USER_ID", USER_ID);
		params.put("message", URLDecoder.decode(message,"UTF-8"));
		params.put("SDATE", sdate);
		params.put("EDATE", edate);

		params.put("RENTTYPE", renttype);
		params.put("ORDERSTATUSCODE", orderstatuscode);
		params.put("SEARCHKEY", URLDecoder.decode(searchkey,"UTF-8"));
		params.put("SEARCHTYPE", searchtype);
		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));

		params.put("STARTNO", String.valueOf(startNo));
		params.put("ENDNO", String.valueOf(endNo));

		if(sdate.equals("")){
			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -1);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);
			sdate = Delday6;
		}

		if(edate.equals("")){
			String ch1 = null;
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);
			edate = ch1;
		}

		params.put("SDATE", sdate);
		params.put("EDATE", edate);
		params.put("FLAG", "");

		//리스트
		list = roomService.getRoomOrderList(params);

		// 총 건수
		int listCount = roomService.getRoomOrderListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		//메뉴 param
		model.addAttribute("TOP_MENU_ID", menuParams.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", menuParams.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", menuParams.get("L_MENU_NM"));
		//메뉴 param

		return "/room/roomOrderList";
	}


	/**
	 * @Method Name : roomOrderList_excel.do
	 * @작성일 : 2013. 11.26
	 * @Method 설명 : 독서실 신청 엑셀 저장
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/roomOrderList_excel.do")
	public ExcelDownloadView roomOrderList_excel(ModelMap model, HttpServletRequest request) throws Exception {

		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");

		//메뉴 param
		HashMap<String, String> menuParams = new HashMap<String, String>();
		menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("menuParams", menuParams);
		//메뉴 param

		String message = CommonUtil.isNull(request.getParameter("message"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("CURRENTPAGE"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String orderstatuscode = CommonUtil.isNull(request.getParameter("ORDERSTATUSCODE"), "");
		String renttype = CommonUtil.isNull(request.getParameter("RENTTYPE"), "");
		String sdate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String edate = CommonUtil.isNull(request.getParameter("EDATE"), "");
    	String searchkey = CommonUtil.isNull(request.getParameter("SEARCHKEY"), "");
    	String searchtype = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("USER_ID", USER_ID);
		params.put("message", URLDecoder.decode(message,"UTF-8"));
		params.put("SDATE", sdate);
		params.put("EDATE", edate);

		params.put("RENTTYPE", renttype);
		params.put("ORDERSTATUSCODE", orderstatuscode);
		params.put("SEARCHKEY", URLDecoder.decode(searchkey,"UTF-8"));
		params.put("SEARCHTYPE", searchtype);
		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));

		params.put("STARTNO", String.valueOf(startNo));
		params.put("ENDNO", String.valueOf(endNo));

		if(sdate.equals("")){
			Calendar month6 = Calendar.getInstance();
			month6.add(Calendar.MONTH, -1);
			java.util.Date date6 = month6.getTime();
			String Delday6 = new SimpleDateFormat("yyyyMMdd").format(date6);
			sdate = Delday6;
		}

		if(edate.equals("")){
			String ch1 = null;
        	TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
			TimeZone.setDefault(tz);
			Date d1 = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			ch1 = sdf1.format(d1);
			edate = ch1;
		}

		params.put("SDATE", sdate);
		params.put("EDATE", edate);
		params.put("FLAG", "");

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = roomService.getRoomOrderListExcel(params);

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "독서실 신청 리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("신청구분");
		headerList.add("신청일시");
		headerList.add("성명(핸드폰)");
		headerList.add("품목(독서실)");
		headerList.add("결제금액");
		headerList.add("결제상태");
		headerList.add("결제종류");

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int icnt = 1;
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(icnt++));
			newMap.put(i++, map.get("ORDERNO").toString());
			newMap.put(i++, map.get("RENT_TYPE_NM").toString());
			String regDate = sdFormat.format(map.get("REG_DT"));
			newMap.put(i++, regDate);
			String userName = map.get("USER_NM").toString() + " (" +map.get("PHONE_NO").toString() + ")";
			newMap.put(i++, userName);
			String prodName = map.get("ROOM_NM").toString() + "  " + String.valueOf(map.get("ROOM_NUM")) + "번 독서실";
			newMap.put(i++, prodName);
			newMap.put(i++, String.valueOf(map.get("PRICE")));
			newMap.put(i++, map.get("STATUSCODE_NM").toString());
			newMap.put(i++, map.get("PAYCODE_NM").toString());

			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

	/**
	 * @Method Name : roomOrderView
	 * @작성일 : 2013. 11.22
	 * @Method 설명 : 독서실 대여 결제 및 환불 내역 보기
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */

	@RequestMapping(value = "/roomOrderView.do")
	public String roomOrderView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), "ORDERLIST"));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));

		//메뉴 param
		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		//메뉴 param

		model.addAttribute("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		model.addAttribute("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		model.addAttribute("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));


		if (!params.get("ORDERNO").isEmpty()) {
			// 독서실 대여 신청 정보(현재)
			HashMap<String, String> roomOrderDetail  = roomService.roomOrderDetail(params);

			model.addAttribute("ORDERNO",params.get("ORDERNO"));
			model.addAttribute("CALLPOSITION",params.get("CALLPOSITION"));
			model.addAttribute("roomOrderDetail",roomOrderDetail);

			return "/room/roomOrderView";

		} else {
			return "redirect:/room/roomRentWrite.do";
		}
	}


	/**
	 * @Method Name : roomOrderView
	 * @작성일 : 2013. 11.22
	 * @Method 설명 : 독서실 대여 주문정보 삭제 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomDeleteOrderProcess.do")
	public String roomDeleteOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), ""));
		params.put("ORDER_ID", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));


		//메뉴 param
		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		//메뉴 param

		model.addAttribute("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		model.addAttribute("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		model.addAttribute("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		model.addAttribute("CALLPOSITION",params.get("CALLPOSITION"));

		if (!"".equals(params.get("ORDER_ID"))) {

			// 독서실 대여 주문정보 삭제
			roomService.roomNumResetUpdateByOrderIdProcess(params);

			// 독서실 대여 주문정보 삭제 (주문번호 이용)
			roomService.roomDeleteRoomRentByOrderId(params);
		}
		if ("ROOMRENTWRITE".equals(params.get("CALLPOSITION"))) {
			return "redirect:/room/roomRentWrite.do";
		} else {
			return "redirect:/room/roomOrderList.do";
		}

	}


	/**
	 * @Method Name : idCheck
	 * @작성일 : 2013. 11.12
	 * @Method 설명 : 독서실 상태값 업데이트
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRoomFlagAsync.do")
	@ResponseBody
	public String updateRoomFlagAsync(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("ROOM_FLAG", CommonUtil.isNull(request.getParameter("ROOM_FLAG"), ""));
	    roomService.updateRoomFlagAsync(params);

	    String returnStr = "Y";
	    return returnStr;
	}


	/**
	 * @Method Name : roomInsertProcess
	 * @작성일 : 2013. 11.08
	 * @Method 설명 : 독서실 등록 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomInsertProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 사물항 정보
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NM", CommonUtil.isNull(request.getParameter("ROOM_NM"), ""));
		params.put("ROOM_COUNT", CommonUtil.isNull(request.getParameter("ROOM_COUNT"), "0"));
		params.put("START_NUM", CommonUtil.isNull(request.getParameter("START_NUM"), "0"));
		params.put("END_NUM", CommonUtil.isNull(request.getParameter("END_NUM"), "0"));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));

		//독서실 정보 등록
		roomService.roomInsertProcess(params);

		// 독서실 상세정보를 독서실 갯수만큼 생성한다.
		HashMap<String, String> subparams = new HashMap<String, String>();
		subparams.put("ROOM_CD", params.get("ROOM_CD"));
		subparams.put("UPDATE_ID",loginInfo.get("USER_ID"));

		int roomCount = Integer.parseInt(params.get("ROOM_COUNT"));	// 독서실 총 갯수
		int startNum = Integer.parseInt(params.get("START_NUM"));	// 시작번호
		int endNum = Integer.parseInt(params.get("END_NUM"));	// 종료번호

		if (roomCount > 0){
			for( int i=startNum; i<= endNum; i++ ) {
				subparams.put("ROOM_NUM", String.valueOf(i));
				roomService.roomNumInsertProcess(subparams);
			}
		}

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomList.do";
	}


	/**
	 * @Method Name : roomUpdateProcess
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 독서실 수정 저장 처리
	 * ① 독서실 대여(신청) 정보가 1개 이상 존재하면 독서실갯수, 시작번호, 종료번호를 제외한 항목만 수정한다.
	 * ② 독서실 대여정보가 없다면 대여(신청)정보 테이블(TB_OFF_ROOM_NUM)에서 해당 독서실코드를 삭제한 후 새로 생성한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomUpdateProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 정보
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NM", CommonUtil.isNull(request.getParameter("ROOM_NM"), ""));
		params.put("ROOM_COUNT", CommonUtil.isNull(request.getParameter("ROOM_COUNT"), "0"));
		params.put("START_NUM", CommonUtil.isNull(request.getParameter("START_NUM"), "0"));
		params.put("END_NUM", CommonUtil.isNull(request.getParameter("END_NUM"), "0"));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));

		// 1. 먼저 독서실정보를 업데이트한다.
		roomService.roomUpdateProcess(params);

		// 2. 독서실 좌석수만큼 루프돌면서 독서실 상세정보를 독서실 갯수만큼 생성한다. (기존에 있다면 SKIP)
		HashMap<String, String> subparams = new HashMap<String, String>();
		subparams.put("ROOM_CD", params.get("ROOM_CD"));
		subparams.put("UPDATE_ID",loginInfo.get("USER_ID"));

		int roomCount = Integer.parseInt(params.get("ROOM_COUNT"));	// 독서실 총 갯수
		int startNum = Integer.parseInt(params.get("START_NUM"));	// 시작번호
		int endNum = Integer.parseInt(params.get("END_NUM"));	// 종료번호
		int rnumCnt = 0;

		if (roomCount > 0){
			for( int i=startNum; i<= endNum; i++ ) {
				subparams.put("ROOM_NUM", String.valueOf(i));
				// 독서실 상세정보가 존재하는지 확인한다.
				rnumCnt = roomService.getRoomNumExistCount(subparams);
				if (rnumCnt < 1){
					// 없다면 독서실 상세정보를 신규 생성(추가)한다.
					roomService.roomNumInsertProcess(subparams);
				}
			}
		}

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomList.do";
	}


	/**
	 * @Method Name : roomDeleteProcess
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 독서실 삭제 처리
	 * ① 독서실 대여(신청) 정보가 1개 이상 존재하면 삭제할 수 없다.
     * ② 삭제가능하다면 독서실 신청이력(TB_OFF_ROOM_RENT), 개별정보(TB_OFF_ROOM_NUM), 독서실정보(TB_OFF_ROOM)를 삭제한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomDeleteProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 사물항 정보
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));

		String msgStr = "";
		if ("".equals(params.get("ROOM_CD"))) {
			msgStr = "삭제할 독서실 코드가 잘못되어 삭제할 수 없습니다.";
		} else
		{
			int roomYCnt = roomService.getRoomNumYCount(params);
			if (roomYCnt > 0){
				msgStr = "삭제할 독서실에 대여 정보가 있어 삭제할 수 없습니다. \n 먼저 독서실 대여 정보를 삭제하세요.";
			} else
			{
				// 지정한 독서실의 관련 모든 정보(3개 테이블) 삭제
				roomService.roomDeleteRoomAll(params);
				msgStr = "독서실 정보가 정상적으로 삭제되었습니다.";
			}
		}

		model.addAttribute("msgStr", msgStr);

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomList.do";
	}


	/**
	 * @Method Name : roomRentOrderProcess
	 * @작성일 : 2013. 11.12
	 * @Method 설명 : 독서실 대여 신청/수정 저장 처리
	 * ① 독서실 대여(신청) 정보가 1개 이상 존재하면 독서실갯수, 시작번호, 종료번호를 제외한 항목만 수정한다.
	 * ② 독서실 대여정보가 없다면 대여(신청)정보 테이블(TB_OFF_ROOM_NUM)에서 해당 독서실코드를 삭제한 후 새로 생성한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomRentOrderProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomRentOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 신청 정보
		params.put("WMODE", CommonUtil.isNull(request.getParameter("WMODE"), ""));
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NM", CommonUtil.isNull(request.getParameter("ROOM_NM"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("EXTEND_YN", CommonUtil.isNull(request.getParameter("EXTEND_YN"), "N"));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("USERID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		String[] roomFlag = request.getParameterValues("ROOM_FLAG");
		if (roomFlag != null && roomFlag.length>0){
			params.put("ROOM_FLAG", roomFlag[0]);
		} else {
			params.put("ROOM_FLAG", "Y");
		}
		String[] rentType = request.getParameterValues("RENT_TYPE");
		if (rentType != null && rentType.length>0){
			params.put("RENT_TYPE", rentType[0]);
		} else {
			params.put("RENT_TYPE", "OFF");
		}
		String[] approvalKind = request.getParameterValues("APPROVAL_KIND");
		if (approvalKind != null && approvalKind.length>0){
			params.put("APPROVAL_KIND", approvalKind[0]);
		} else {
			params.put("APPROVAL_KIND", "PAY140");
		}
		params.put("RENT_START", CommonUtil.isNull(request.getParameter("RENT_START"), ""));
		params.put("RENT_END", CommonUtil.isNull(request.getParameter("RENT_END"), ""));
		params.put("RENT_MEMO", CommonUtil.isNull(request.getParameter("RENT_MEMO"), ""));
		params.put("ORDER_ID", CommonUtil.isNull(request.getParameter("ORDER_ID"), ""));
		params.put("PRICE_GET_TOTAL", CommonUtil.isNull(request.getParameter("PRICE_GET_TOTAL"), "0"));
		params.put("PRICE_DISCOUNT_VALUE", CommonUtil.isNull(request.getParameter("PRICE_DISCOUNT_VALUE"), "0"));
		params.put("PRICE_GET_CARD", CommonUtil.isNull(request.getParameter("PRICE_GET_CARD"), "0"));
		params.put("PRICE_GET_CASH", CommonUtil.isNull(request.getParameter("PRICE_GET_CASH"), "0"));
		params.put("PRICE_DISCOUNT_REASON", CommonUtil.isNull(request.getParameter("PRICE_DISCOUNT_REASON"), ""));
		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("PAYCODE", CommonUtil.isNull(request.getParameter("APPROVAL_KIND"), "PAY140"));

		if ("PAY140".equals(params.get("PAYCODE"))){
			params.put("PAY_GUBUN", "현금");
		} else {
			params.put("PAY_GUBUN", "카드");
		}

		HashMap<String, Object> memparams = new  HashMap<String, Object>();
		memparams.put("USER_ID",request.getParameter("USER_ID"));
		HashMap<String, Object> memDetail  = memberManagementService.memberDetail(memparams);
		if (memDetail != null) {
			String UserTel = "";
			if (memDetail.get("PHONE_NO") == null || "".equals(memDetail.get("PHONE_NO"))) {
				UserTel = String.valueOf(memDetail.get("TEL_NO"));
			} else{
				UserTel = String.valueOf(memDetail.get("PHONE_NO"));
			}
			params.put("USER_TEL", UserTel);
			params.put("USER_NAME", memDetail.get("USER_NM").toString());
			params.put("USER_EMAIL", memDetail.get("EMAIL").toString());
		} else {
			params.put("USER_TEL", "");
			params.put("USER_NAME", "");
			params.put("USER_EMAIL", "");
		}
		params.put("USER_NM", params.get("USER_NAME"));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));
		params.put("ADMIN_ID",loginInfo.get("USER_ID"));

		if ("INS".equals(params.get("WMODE"))) {
			// 독서실 신규 등록 처리
			// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
			HashMap<String, String> ordernoparams = new HashMap<String, String>();
			ordernoparams.put("PTYPE", "T");
			String orderNo = roomService.getOffOrderNo(ordernoparams);
			params.put("ORDERNO", orderNo);
			params.put("ORDER_ID", orderNo);
			params.put("EXTEND_YN", "N");

			// 2. 오프라인 독서실 대여 주문 세부정보 등록 처리
			params.put("ISCANCEL", "0");
			params.put("STATUSCODE", "DLV105");
			roomService.offOrderMgntNoInsertProcess(params);

			// 3. 오프라인 독서실 대여 주문 등록 처리
			if ("OFF".equals(params.get("RENT_TYPE"))){
				params.put("ISOFFLINE","1");
			} else{
				params.put("ISOFFLINE","0");
			}

			if (log.isDebugEnabled()) {
				log.debug("***** ORDERNO : " + params.get("ORDERNO"));
			}
			roomService.offOrdersInsertProcess(params);

			// 3. 오프라인 독서실 대여 결제정보 등록처리
			roomService.offApprovalsInsertProcess(params);

			// 4. 독서실 대여정보테이블(TB_OFF_ROOM_RENT)에 추가
			roomService.roomRentInsertProcess(params);

			// 5. 독서실 세부정보(TB_OFF_ROOM_NUM) 업데이트 roomNumUpdateProcess
			int rentSeq = roomService.getRoomRentSeq(params);

			params.put("RENT_SEQ", String.valueOf(rentSeq));  // TB_OFF_RENT에 추가한 RENT_SEQ를 전달한다.
			params.put("ROOM_FLAG", "Y");  // 처음 등록할 때는 무조건 사용(Y)으로 저장한다.
			roomService.roomNumUpdateProcess(params);
		}

		if ("EDT".equals(params.get("WMODE"))) {
			// 독서실 대여 수정 처리
			// 1. 독서실 세부정보(TB_OFF_ROOM_NUM) 업데이트 roomNumUpdateProcess
			roomService.roomNumUpdateProcess(params);

			// 2. 독서실 대여정보(TB_OFF_ROOM_RENT) 업데이트 roomNumRentUpdateProcess
			roomService.roomNumRentUpdateProcess(params);

			// 3. 독서실 주문 결제내역(TB_OFF_APPROVALS) 업데이트 offPayUpdateProcess
			roomService.offApprovalsUpdateProcess(params);
		}

		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("RENT_SEQ", params.get("RENT_SEQ"));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomView.do";
	}

	/**
	 * @Method Name : roomExtendOrderProcess
	 * @작성일 : 2013. 11.13
	 * @Method 설명 : 독서실 대여 연장 처리
	 * - 신청구분: DE (데스크 접수)
     * - 주문번호 생성(TB_OFF_ORDERS에서 max(seq) + 1)
     * - 연장 기간 변경 : 시작일(기존 종료일+1),  종료일(기존종료일+2개월)
     * - TB_OFF_ORDERS 에 기존대여정보를 신규로 인서트한다.
     * - TB_OFF_ORDER_MGNT_NO 에 신규로 인서트 한다.  (obj_type='B')
     * - TB_OFF_APPROVALS 에 신규로 인서트 한다. (price_discount_type = 'W')
     * - TB_OFF_ROOM_NUM 에 최종 정보로 업데이트한다.
     * - TB_OFF_ROOM_RENT 에 신규로 인서트한다.
     *   (extend_yn='Y', key_yn='N', rent_type='OFF', deposit=10000,  2개월)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomExtendOrderProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomExtendOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 신청 정보
		params.put("WMODE", CommonUtil.isNull(request.getParameter("WMODE"), ""));
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NM", CommonUtil.isNull(request.getParameter("ROOM_NM"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("USERID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		String[] roomFlag = request.getParameterValues("ROOM_FLAG");
		if (roomFlag != null && roomFlag.length>0){
			params.put("ROOM_FLAG", roomFlag[0]);
		} else {
			params.put("ROOM_FLAG", "Y");
		}
		String[] approvalKind = request.getParameterValues("APPROVAL_KIND");
		if (approvalKind != null && approvalKind.length>0){
			params.put("APPROVAL_KIND", approvalKind[0]);
		} else {
			params.put("APPROVAL_KIND", "PAY140");
		}
		params.put("EXTEND_YN", "Y");  // 연장
		params.put("RENT_TYPE", "OFF");  // 오프라인
		params.put("PRICE_DISCOUNT_REASON", "독서실 데스크에서 연장");

		params.put("RENT_START", CommonUtil.isNull(request.getParameter("RENT_START"), ""));
		params.put("RENT_END", CommonUtil.isNull(request.getParameter("RENT_END"), ""));
		params.put("RENT_MEMO", CommonUtil.isNull(request.getParameter("RENT_MEMO"), ""));
		params.put("ORDER_ID", CommonUtil.isNull(request.getParameter("ORDER_ID"), ""));
		params.put("PRICE_GET_TOTAL", CommonUtil.isNull(request.getParameter("PRICE_GET_TOTAL"), "0"));
		params.put("PRICE_GET_CARD", CommonUtil.isNull(request.getParameter("PRICE_GET_CARD"), "0"));
		params.put("PRICE_GET_CASH", CommonUtil.isNull(request.getParameter("PRICE_GET_CASH"), "0"));
		params.put("PRICE_DISCOUNT_VALUE", CommonUtil.isNull(request.getParameter("PRICE_DISCOUNT_VALUE"), "0"));
		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("PAYCODE", CommonUtil.isNull(request.getParameter("APPROVAL_KIND"), "PAY140"));

		if ("PAY140".equals(params.get("PAYCODE"))){
			params.put("PAY_GUBUN", "현금");
		} else {
			params.put("PAY_GUBUN", "카드");
		}

		HashMap<String, Object> memparams = new  HashMap<String, Object>();
		memparams.put("USER_ID",request.getParameter("USER_ID"));
		HashMap<String, Object> memDetail  = memberManagementService.memberDetail(memparams);
		if (memDetail != null) {
			String UserTel = "";
			if (memDetail.get("PHONE_NO") == null || "".equals(memDetail.get("PHONE_NO"))) {
				UserTel = String.valueOf(memDetail.get("TEL_NO"));
			} else{
				UserTel = String.valueOf(memDetail.get("PHONE_NO"));
			}
			params.put("USER_TEL", UserTel);
			params.put("USER_NAME", memDetail.get("USER_NM").toString());
			params.put("USER_EMAIL", memDetail.get("EMAIL").toString());
		} else {
			params.put("USER_TEL", "");
			params.put("USER_NAME", "");
			params.put("USER_EMAIL", "");
		}
		params.put("USER_NM", params.get("USER_NAME"));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));
		params.put("ADMIN_ID",loginInfo.get("USER_ID"));

		// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
		HashMap<String, String> ordernoparams = new HashMap<String, String>();
		ordernoparams.put("PTYPE", "T");
		String orderNo = roomService.getOffOrderNo(ordernoparams);
		params.put("ORDERNO", orderNo);
		params.put("ORDER_ID", orderNo);

		// 2. 오프라인 독서실 대여 주문 세부정보 등록 처리
		params.put("ISCANCEL", "0");
		params.put("STATUSCODE", "DLV105");
		roomService.offOrderMgntNoInsertProcess(params);

		// 3. 오프라인 독서실 대여 주문 등록 처리
		if ("OFF".equals(params.get("RENT_TYPE"))){
			params.put("ISOFFLINE","1");
		} else{
			params.put("ISOFFLINE","0");
		}

		if (log.isDebugEnabled()) {
			log.debug("***** ORDERNO : " + params.get("ORDERNO"));
		}
		roomService.offOrdersInsertProcess(params);

		// 3. 오프라인 독서실 대여 결제정보 등록처리
		roomService.offApprovalsInsertProcess(params);

		// 4. 독서실 대여정보테이블(TB_OFF_ROOM_RENT)에 추가
		// 기간을 연장한다. = 기존 종료일 + 2개월
		String rentStart = params.get("RENT_START");
		String rentEnd = params.get("RENT_END");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date stdate = null;
		Date endate = null;
		try {
			stdate = dateFormat.parse(rentEnd);   // 기존 시작일
			endate = dateFormat.parse(rentEnd);   	// 기존 종료일
		} catch (ParseException e){
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(stdate);
		cal.add(Calendar.DATE, 1);
		String nRentStart = dateFormat.format(cal.getTime()); 	// 연장 시작일
		cal.setTime(endate);
		cal.add(Calendar.MONTH, 1);
		String nRentEnd = dateFormat.format(cal.getTime());  	// 연장 종료일

		params.put("RENT_START", nRentStart);	// 연장시 시작일
		params.put("RENT_END", nRentEnd);		// 연장시 종료일

		roomService.roomRentInsertProcess(params);

		// 5. 독서실 세부정보(TB_OFF_ROOM_NUM) 업데이트 roomNumUpdateProcess
		int rentSeq = roomService.getRoomRentSeq(params);
		params.put("RENT_SEQ", String.valueOf(rentSeq));  // TB_OFF_RENT에 추가한 RENT_SEQ를 전달한다.
		roomService.roomNumUpdateProcess(params);

		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("RENT_SEQ", params.get("RENT_SEQ"));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomRentWrite.do";
	}


	/**
	 * @Method Name : roomRefundProcess
	 * @작성일 : 2013. 11.13
	 * @Method 설명 : 독서실 대여 환불 처리
     * - TB_OFF_REFUND 테이블에 환불 정보를 인서트한다.(환불금액, 환불일, 유저아이디)
     * - TB_OFF_MGNT_NO 테이블에 환불 정보를 인서트한다.
     * - TB_OFF_APPROVALS 테이블의 REFUND_PRICE, REFUND_DATE 항목에 환불금액을 저장한다.
     * - TB_OFF_ROOM_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 독서실사용상태: 미사용)
     * - TB_OFF_ROOM_NUM 독서실 상세정보의 ROOM_FLAG = N, USERID = "" 초기화한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomRefundProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomRefundProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 신청 정보
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("ORDER_ID", CommonUtil.isNull(request.getParameter("ORDER_ID"), ""));
		params.put("REFUND_PRICE", CommonUtil.isNull(request.getParameter("REFUND_PRICE"), "0"));
		params.put("REFUND_DATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));

		// 1. 환불 테이블(TB_REFUND)에 삽입한다.
		roomService.offRefundInsertProcess(params);

		// 2. TB_OFF_ORDER_MGNT_NO에 삽입한다.
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("ADMIN_ID",loginInfo.get("USER_ID"));
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));

		params.put("ORDERNO", params.get("ORDER_ID"));
		params.put("PRICE_GET_TOTAL", String.valueOf((0 - Integer.parseInt(params.get("REFUND_PRICE")))));
		params.put("CANCELDATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));
		params.put("ISCANCEL", "2");
		params.put("STATUSCODE", "DLV230"); // 환불완료

		roomService.offOrderMgntNoInsertProcess(params);

		// 3. 오프라인 독서실 대여 결제정보 등록처리
//		params.put("REFUND_PRICE", CommonUtil.isNull(request.getParameter("REFUND_PRICE"), ""));
//		params.put("REFUND_DATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));
//
//		if (log.isDebugEnabled()) {
//			log.debug("***** ORDERNO : " + params.get("ORDERNO"));
//			log.debug("***** REFUND_PRICE : " + params.get("REFUND_PRICE"));
//			log.debug("***** REFUND_DATE : " + params.get("REFUND_DATE"));
//		}
//
//		roomService.offApprovalsRefundUpdateProcess(params);

	    // 4. TB_OFF_ROOM_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 독서실사용상태: 미사용)
		// RENT_END       = SYSDATE,  DEPOSIT_YN      = 'Y', KEY_YN	       = 'Y', UPDATE_DT      = SYSDATE
		roomService.roomNumRentRefundUpdateProcess(params);

		// 5. TB_OFF_ROOM_NUM 독서실 상세정보의 ROOM_FLAG = N, USERID = "" 초기화한다.
		// USERID    = '',ROOM_FLAG  = 'N',RENT_SEQ  = NULL, RENT_MEMO = '',
		params.put("ROOM_FLAG", "N");

		roomService.roomNumResetUpdateProcess(params);

		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("RENT_SEQ", "");
		model.addAttribute("ORDERNO", "");

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomRentWrite.do";
	}


	/**
	 * @Method Name : roomRefundPop
	 * @작성일 : 2013. 11.13
	 * @Method 설명 : 독서실 대여 환불 처리
	 * - ORDERID를 전달 받아 독서실 대여 신청 정보(현재)를 가져오고
     * - 독서실 대여 현재 결제 정보(TB_OFF_ORDERS , TB_OFF_ORDER_MGNT_NO , TB_OFF_APPROVALS)를 가져온다
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomRefundPop.pop")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomRefundPop(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 신청 정보
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("ORDER_ID", CommonUtil.isNull(request.getParameter("ORDER_ID"), ""));

		// 독서실 대여 신청 정보(현재)
		HashMap<String, String> roomNumRentDetail  = roomService.roomNumRentDetail(params);

		// 독서실 대여 현재 결제 정보를 가져온다
		HashMap<String, String> roomNumRentOrderDetail  = roomService.roomNumRentOrderDetail(params);

		HashMap<String, Object> memDetail = null;
		if (roomNumRentDetail != null) {
			HashMap<String, Object> memparams = new  HashMap<String, Object>();
			memparams.put("USER_ID",roomNumRentDetail.get("USERID"));
			memDetail  = memberManagementService.memberDetail(memparams);
		}
		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("RENT_SEQ", params.get("RENT_SEQ"));
		model.addAttribute("ORDER_ID", params.get("ORDER_ID"));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("memberdetail",memDetail);
		model.addAttribute("roomnumrentdetail",roomNumRentDetail);
		model.addAttribute("roomnumrentorderdetail",roomNumRentOrderDetail);

		return "/room/roomRefundPop";
	}

	/**
	 * @Method Name : roomChangePop
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 독서실 변경 처리
	 * - 기존 독서실 정보를 가져와서 팝업으로 표시한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomChangePop.pop")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomChangePop(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 기존 정보
		params.put("USED_ROOM_CD", CommonUtil.isNull(request.getParameter("USED_ROOM_CD"), ""));
		params.put("USED_ROOM_NUM", CommonUtil.isNull(request.getParameter("USED_ROOM_NUM"), ""));
		params.put("USED_RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));

		List<HashMap<String, String>> roomNumList = roomService.getRoomNumList(params);
		model.addAttribute("roomnumList",roomNumList);

		model.addAttribute("USED_ROOM_CD", params.get("USED_ROOM_CD"));
		model.addAttribute("USED_ROOM_NUM", params.get("USED_ROOM_NUM"));
		model.addAttribute("USED_RENT_SEQ", params.get("USED_RENT_SEQ"));
		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		return "/room/roomChangePop";
	}

	/**
	 * @Method Name : roomChangePopProcess
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 독서실 변경 처리
     * - TB_OFF_ROOM_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
     * - TB_OFF_ROOM_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
     * - TB_OFF_ROOM_RENT 테이블을 업데이트한다. (기존 자료에 신규 독서실번호를 저장한다)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomChangePopProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String roomChangePopProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		// 독서실 기존 정보
		params.put("USED_ROOM_CD", CommonUtil.isNull(request.getParameter("USED_ROOM_CD"), ""));
		params.put("USED_ROOM_NUM", CommonUtil.isNull(request.getParameter("USED_ROOM_NUM"), ""));
		params.put("USED_RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));

		// 독서실 신규 번호
		params.put("ROOM_CD", CommonUtil.isNull(request.getParameter("ROOM_CD"), ""));
		params.put("ROOM_NUM", CommonUtil.isNull(request.getParameter("ROOM_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));

		params.put("NEW_ROOM_CD", params.get("ROOM_CD"));
		params.put("NEW_ROOM_NUM", params.get("ROOM_NUM"));

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("UPDATE_ID",loginInfo.get("USER_ID"));

		// 1. TB_OFF_ROOM_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
		roomService.roomNumChangeUpdateProcess(params);

		// 2. TB_OFF_ROOM_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
		params.put("ROOM_CD", params.get("USED_ROOM_CD"));
		params.put("ROOM_NUM", params.get("USED_ROOM_NUM"));
		roomService.roomNumResetUpdateProcess(params);

	    // 3. TB_OFF_ROOM_RENT 테이블을 업데이트한다. (기존 자료에 신규 독서실번호를 저장한다)
		params.put("ROOM_CD", params.get("NEW_ROOM_CD"));
		params.put("ROOM_NUM", params.get("NEW_ROOM_NUM"));
		roomService.roomRentChangeUpdateProcess(params);

		model.addAttribute("ROOM_CD", params.get("ROOM_CD"));
		model.addAttribute("ROOM_NUM", params.get("ROOM_NUM"));
		model.addAttribute("RENT_SEQ", params.get("RENT_SEQ"));

		//메뉴 param
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		model.addAttribute("params",params);
		return "redirect:/room/roomRentWrite.do";
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
	public void setParam(HashMap<String, String> params,
			HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap) session.getAttribute("AdmUserInfo");
		params.put("REG_ID", loginInfo.get("USER_ID"));
		params.put("UPD_ID", loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("TOP_MENU_ID", 	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}

}
