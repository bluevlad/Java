package com.willbes.web.box;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.annotation.Resource;
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
import com.willbes.platform.util.MafUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.box.service.BoxService;
import com.willbes.web.memberManagement.service.MemberManagementService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value = "/box")
@Controller
public class BoxController {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private BoxService boxService;
	@Autowired
	private MemberManagementService memberManagementService;
	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.07
	 * @Method 설명 : 사물함 관리 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxList.do")
	public String boxList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 정보 리스트
		List<HashMap<String, String>> list = boxService.boxList(params);

		model.addAttribute("list", list);
		model.addAttribute("params", params);

		return "/box/boxlist";
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.07
	 * @Method 설명 : 사물함 관리 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxView.do")
	public String boxView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

//		boxService.updateBoxFlag(params); //사물함 상태 업데이트

		HashMap<String, String> boxDetail  = boxService.getBoxDetail(params);
		List<HashMap<String, String>> boxNumList = boxService.getBoxNumList(params);

		model.addAttribute("boxdetail",boxDetail);
		model.addAttribute("boxnumList",boxNumList);
		model.addAttribute("params", params);

		return "/box/boxView";
	}
	
	/**
	 * @Method Name : boxInsert
	 * @작성일 : 2013. 11.08
	 * @Method 설명 : 사물함 등록 화면
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxInsert.do")
	public String boxInsert(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		model.addAttribute("params",params);
		return "/box/boxinsert";
	}
	
	/**
	 * @Method Name : boxInsertProcess
	 * @작성일 : 2013. 11.08
	 * @Method 설명 : 사물함 등록 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxInsertProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 정보
		params.put("BOX_NM", CommonUtil.isNull(request.getParameter("BOX_NM"), ""));
		params.put("BOX_COUNT", CommonUtil.isNull(request.getParameter("BOX_COUNT"), "0"));
		params.put("BOX_PRICE", CommonUtil.isNull(request.getParameter("BOX_PRICE"), "0"));
		params.put("DEPOSIT", CommonUtil.isNull(request.getParameter("DEPOSIT"), "0"));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("START_NUM", CommonUtil.isNull(request.getParameter("START_NUM"), "0"));
		params.put("END_NUM", CommonUtil.isNull(request.getParameter("END_NUM"), "0"));

		//사물함 정보 등록
		boxService.boxInsertProcess(params);

		// 사물함 상세정보를 사물함 갯수만큼 생성한다.
		HashMap<String, String> subparams = new HashMap<String, String>();
		subparams.put("BOX_CD", params.get("BOX_CD"));
		subparams.put("UPDATE_ID", params.get("USER_ID"));	

		int boxCount = Integer.parseInt(params.get("BOX_COUNT"));	// 사물함 총 갯수
		int startNum = Integer.parseInt(params.get("START_NUM"));	// 시작번호
		int endNum = Integer.parseInt(params.get("END_NUM"));	// 종료번호

		if (boxCount > 0){
			for( int i=startNum; i<= endNum; i++ ) {
				subparams.put("BOX_NUM", String.valueOf(i));
				boxService.boxNumInsertProcess(subparams);
			}
		}

		model.addAttribute("params",params);
		return "forward:/box/boxList.do";
	}

	/**
	 * @Method Name : boxUpdateProcess
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 사물함 수정 저장 처리
	 * ① 사물함 대여(신청) 정보가 1개 이상 존재하면 사물함갯수, 시작번호, 종료번호를 제외한 항목만 수정한다.
	 * ② 사물함 대여정보가 없다면 대여(신청)정보 테이블(TB_OFF_BOX_NUM)에서 해당 사물함코드를 삭제한 후 새로 생성한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxUpdateProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);
		
		String msgStr = "";

		// 사물함 정보
		params.put("BOX_COUNT", CommonUtil.isNull(request.getParameter("BOX_COUNT"), "0"));
		params.put("DEPOSIT", CommonUtil.isNull(request.getParameter("DEPOSIT"), "0"));
		params.put("BOX_PRICE", CommonUtil.isNull(request.getParameter("BOX_PRICE"), "0"));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("START_NUM", CommonUtil.isNull(request.getParameter("START_NUM"), "0"));
		params.put("END_NUM", CommonUtil.isNull(request.getParameter("END_NUM"), "0"));

		int boxYCnt = boxService.getBoxNumYCount(params);
		if (boxYCnt > 0){
			// 사물함 대여 정보가 있으므로 사물함갯수와 시작번호, 종료번호를 수정하지 않고 다른 항목만 업데이트한다.
			msgStr = "대여 정보가 있어 기본 정보만 변경되었습니다.";
			boxService.boxUpdate2Process(params);
		} else 	{
			// 사물함정보를 업데이트하고 대여(신청)정보는 삭제후 새로 생성한다.
			boxService.boxUpdateProcess(params);
			boxService.boxDeleteBoxNum(params);

			// 사물함 상세정보를 사물함 갯수만큼 생성한다.
			HashMap<String, String> subparams = new HashMap<String, String>();
			subparams.put("BOX_CD", params.get("BOX_CD"));
			subparams.put("UPDATE_ID", params.get("UPD_ID"));	

			int boxCount = Integer.parseInt(params.get("BOX_COUNT"));	// 사물함 총 갯수
			int startNum = Integer.parseInt(params.get("START_NUM"));	// 시작번호
			int endNum = Integer.parseInt(params.get("END_NUM"));	// 종료번호

			if (boxCount > 0){
				for( int i=startNum; i<= endNum; i++ ) {
					subparams.put("BOX_NUM", String.valueOf(i));
					boxService.boxNumInsertProcess(subparams);
				}
			}
			msgStr = "사물함 기본 정보가 변경되었습니다.";
		}
		model.addAttribute("msgStr", msgStr);
		model.addAttribute("params", params);

		return "forward:/box/boxView.do";
	}

	/**
	 * @Method Name : boxDeleteProcess
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 사물함 삭제 처리
	 * ① 사물함 대여(신청) 정보가 1개 이상 존재하면 삭제할 수 없다.
     * ② 삭제가능하다면 사물함 신청이력(TB_OFF_BOX_RENT), 개별정보(TB_OFF_BOX_NUM), 사물함정보(TB_OFF_BOX)를 삭제한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxDeleteProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxDeleteProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		String msgStr = "";
		if ("".equals(params.get("BOX_CD"))) {
			msgStr = "삭제할 사물함 코드가 잘못되어 삭제할 수 없습니다.";
		} else 	{
			int boxYCnt = boxService.getBoxNumYCount(params);
			if (boxYCnt > 0){
				msgStr = "삭제할 사물함에 대여 정보가 있어 삭제할 수 없습니다. \n 먼저 사물함 대여 정보를 삭제하세요.";
			} else 	{
				// 지정한 사물함의 관련 모든 정보(3개 테이블) 삭제
				boxService.boxDeleteBoxAll(params);
				msgStr = "사물함 정보가 정상적으로 삭제되었습니다.";
			}
		}

		model.addAttribute("msgStr", msgStr);
		model.addAttribute("params",params);
		
		return "forward:/box/boxList.do";
	}

	/**
	 * @Method Name : boxRentWrite
	 * @작성일 : 2013. 11.11
	 * @Method 설명 : 사물함 대여 신청 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxRentWrite.do")
	public String boxRentWrite(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 BOX_CD로 상세정보를 가져온다.
		params.put("SEQ", params.get("RENT_SEQ"));

		HashMap<String, String> boxNumRentDetail  = null;
		HashMap<String, String> boxNumRentOrderDetail = null;
		if (!params.get("SEQ").isEmpty()) {
			// 사물함 대여 신청 정보(현재)
			boxNumRentDetail  = boxService.boxNumRentDetail(params);

			// 사물함 대여 현재 결제 정보를 가져온다
			if (boxNumRentDetail != null && boxNumRentDetail.size() > 0){
				params.put("ORDERNO", boxNumRentDetail.get("ORDERNO"));
				boxNumRentOrderDetail = boxService.boxNumRentOrderDetail(params);
			}
		}

		if ( boxNumRentOrderDetail != null){
			model.addAttribute("WMODE", "EDT");
		} else {
			model.addAttribute("WMODE", "INS");
		}

		// 사물함 대여 결제 이력들
		List<HashMap<String, String>> boxNumRentOrderList = boxService.boxNumRentOrderList(params);

		HashMap<String, String> boxDetail  = boxService.getBoxDetail(params);
		model.addAttribute("boxDetail", boxDetail);

		model.addAttribute("boxNumRentDetail", boxNumRentDetail);
		model.addAttribute("boxNumRentOrderDetail", boxNumRentOrderDetail);
		model.addAttribute("boxNumRentOrderList", boxNumRentOrderList);
		model.addAttribute("params", params);

		return "/box/boxRentWrite";
	}

	/**
	 * @Method Name : updateBoxFlag
	 * @작성일 : 2015. 06. 12
	 * @Method 설명 : 사물함 상태값 업데이트
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */		
	@RequestMapping(value="/updateBoxFlag.do")	
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String updateBoxFlag(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

	    boxService.updateBoxFlagAsync(params);
	    
		model.addAttribute("msgStr", "사물함 대여정보가 정상적으로 변경되었습니다.");
		model.addAttribute("params", params);
		
		return "forward:/box/boxView.do";
	}		

	/**
	 * @Method Name : boxOrderList.do
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 사물함 신청 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxOrderList.do")
	public String boxOrderList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> list = null;

		String message = CommonUtil.isNull(request.getParameter("message"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String sdate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String edate = CommonUtil.isNull(request.getParameter("EDATE"), "");
    	String searchkey = CommonUtil.isNull(request.getParameter("SEARCHKEY"), "");

		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("message", URLDecoder.decode(message,"UTF-8"));

		params.put("RENTTYPE", CommonUtil.isNull(request.getParameter("RENTTYPE"), ""));
		params.put("ORDERSTATUSCODE", CommonUtil.isNull(request.getParameter("ORDERSTATUSCODE"), ""));
		params.put("SEARCHKEY", URLDecoder.decode(searchkey,"UTF-8"));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
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
		list = boxService.getBoxOrderList(params);

		// 총 건수
		int listCount = boxService.getBoxOrderListCount(params);
		
		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/box/boxOrderList";
	}
	
	/**
	 * @Method Name : boxOrderList_excel.do
	 * @작성일 : 2013. 11.26
	 * @Method 설명 : 사물함 신청 엑셀 저장
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/boxOrderList_excel.do")
	public ExcelDownloadView boxOrderList_excel(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

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
		List<HashMap<String, String>> exe_list = boxService.getBoxOrderListExcel(params);

		int exe_size = exe_list.size();
		
		System.out.println("exe_size ::" + exe_size);
		
		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "사물함 신청 리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("신청구분");
		headerList.add("신청일시");
		headerList.add("성명(핸드폰)");
		headerList.add("품목(사물함)");
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
			newMap.put(i++, MafUtil.nvlObj(map.get("ORDERNO"),"").toString());
			newMap.put(i++, MafUtil.nvlObj(map.get("RENT_TYPE_NM"),"").toString());
			String regDate = sdFormat.format(MafUtil.nvlObj(map.get("REG_DT"),"0"));
			newMap.put(i++, regDate);
			String userName = MafUtil.nvlObj(map.get("USER_NM"),"").toString() + " (" +MafUtil.nvlObj(map.get("PHONE_NO"),"").toString() + ")";
			newMap.put(i++, userName);
			String prodName = MafUtil.nvlObj(map.get("BOX_NM"),"").toString() + "  " + String.valueOf(MafUtil.nvlObj(map.get("BOX_NUM"),0)) + "번 사물함";
			newMap.put(i++, prodName);
			newMap.put(i++, String.valueOf(MafUtil.nvlObj(map.get("PRICE"),0)));
			newMap.put(i++, MafUtil.nvlObj(map.get("STATUSCODE_NM"),"").toString());
			newMap.put(i++, MafUtil.nvlObj(map.get("PAYCODE_NM"),"").toString());

			newList.add(newMap);
		}
		
		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);
		model.addAttribute("params", params);

		return new ExcelDownloadView();
		
		
	}

	/**
	 * @Method Name : boxExtOrderList.do
	 * @작성일 : 2015. 11. 13
	 * @Method 설명 : 사물함 사용만료 대상자 목록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxExtOrderList.do")
	public String boxExtOrderList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> list = null;

		String message = CommonUtil.isNull(request.getParameter("message"), "");
        String USER_ID = CommonUtil.isNull(request.getParameter("USER_ID"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("CURRENTPAGE"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		String sdate = CommonUtil.isNull(request.getParameter("SDATE"), "");
        String edate = CommonUtil.isNull(request.getParameter("EDATE"), "");

		params.put("message", URLDecoder.decode(message,"UTF-8"));
		params.put("SDATE", sdate);
		params.put("EDATE", edate);

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));

		params.put("STARTNO", String.valueOf(startNo));
		params.put("ENDNO", String.valueOf(endNo));

		if(sdate.equals("")){
			Calendar sday = Calendar.getInstance();
			sday.add(Calendar.DAY_OF_MONTH, 0);
			java.util.Date day = sday.getTime();
			String s_date = new SimpleDateFormat("yyyyMMdd").format(day);
			sdate = s_date;
		}

		if(edate.equals("")){
			Calendar eday = Calendar.getInstance();
			eday.add(Calendar.DATE, +5);
			java.util.Date day = eday.getTime();
			String e_date = new SimpleDateFormat("yyyyMMdd").format(day);
			edate = e_date;
		}

		params.put("SDATE", sdate);
		params.put("EDATE", edate);
		params.put("FLAG", "");

		//리스트
		list = boxService.getBoxExtOrderList(params);

		// 총 건수
		int listCount = boxService.getBoxExtOrderListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/box/boxExtOrderList";
	}
	
	/**
	 * @Method Name : boxExtOrderList_excel.do
	 * @작성일 : 2015. 11. 13
	 * @Method 설명 : 사물함 사용만료 대상자 엑셀 저장
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/boxExtOrderList_excel.do")
	public ExcelDownloadView boxExtOrderList_excel(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

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
			Calendar sday = Calendar.getInstance();
			sday.add(Calendar.DAY_OF_MONTH, 0);
			java.util.Date day = sday.getTime();
			String s_date = new SimpleDateFormat("yyyyMMdd").format(day);
			sdate = s_date;
		}

		if(edate.equals("")){
			Calendar eday = Calendar.getInstance();
			eday.add(Calendar.DATE, +5);
			java.util.Date day = eday.getTime();
			String e_date = new SimpleDateFormat("yyyyMMdd").format(day);
			edate = e_date;
		}

		params.put("SDATE", sdate);
		params.put("EDATE", edate);
		params.put("FLAG", "");

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = boxService.getBoxExtOrderListExcel(params);

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "사물함 신청 리스트(" + sdate + "~" + edate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("주문번호");
		headerList.add("성명");
		headerList.add("핸드폰");
		headerList.add("이메일");
		headerList.add("품목(사물함)");
		headerList.add("대여시작일");
		headerList.add("대여종료일");

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		int icnt = 1;
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(icnt++));
			newMap.put(i++, map.get("ORDERNO").toString());
			newMap.put(i++, map.get("USER_NM").toString());
			newMap.put(i++, map.get("PHONE_NO").toString());
			newMap.put(i++, map.get("EMAIL").toString()); 
			String prodName = map.get("BOX_NM").toString() + "  " + String.valueOf(map.get("BOX_NUM")) + "번 사물함";
			newMap.put(i++, prodName);
			newMap.put(i++, map.get("RENT_START").toString());
			newMap.put(i++, map.get("RENT_END").toString());

			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);
		model.addAttribute("params", params);

		return new ExcelDownloadView();
	}

	/**
	 * @Method Name : boxOrderView
	 * @작성일 : 2013. 11.22
	 * @Method 설명 : 사물함 대여 결제 및 환불 내역 보기
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */

	@RequestMapping(value = "/boxOrderView.do")
	public String boxOrderView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), "ORDERLIST"));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("STATUSCODE", CommonUtil.isNull(request.getParameter("STATUSCODE"), ""));

		if (!params.get("ORDERNO").isEmpty()) {
			// 사물함 대여 신청 정보(현재)
			HashMap<String, String> boxOrderDetail  = boxService.boxOrderDetail(params);

			model.addAttribute("ORDERNO", params.get("ORDERNO"));
			model.addAttribute("CALLPOSITION", params.get("CALLPOSITION"));
			model.addAttribute("boxOrderDetail", boxOrderDetail);
			model.addAttribute("params", params);

			return "/box/boxOrderView";

		} else {
			return "redirect:/box/boxRentWrite.do";
		}
	}


	/**
	 * @Method Name : boxDeleteOrderProcess
	 * @작성일 : 2013. 11.22
	 * @Method 설명 : 사물함 대여 주문정보 삭제 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxDeleteOrderProcess.do")
	public String boxDeleteOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), ""));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));

		model.addAttribute("CALLPOSITION",params.get("CALLPOSITION"));
		model.addAttribute("params", params);

		if (!"".equals(params.get("ORDERNO"))) {
			// 사물함 정보 변경
			boxService.boxNumResetUpdateByOrderIdProcess(params);

			// 사물함 대여정보 삭제 (주문번호 이용)
			boxService.boxDeleteBoxRentByOrderId(params);

			// 사물함 주문정보 삭제 (주문번호 이용)
			boxService.deleteBoxOrder(params);
		}
		
		if ("BOXRENTWRITE".equals(params.get("CALLPOSITION"))) {
			return "forward:/box/boxRentWrite.do";
		} else {
			return "forward:/box/boxOrderList.do";
		}

	}

	/**
	 * @Method Name : updateBoxFlagAsync
	 * @작성일 : 2013. 11.12
	 * @Method 설명 : 사물함 상태값 업데이트
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/updateBoxFlagAsync.do")
	public String updateBoxFlagAsync(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

	    boxService.updateBoxFlagAsync(params);

	    return "forward:/box/boxView.do";
	}

	/**
	 * @Method Name : boxRentOrderProcess
	 * @작성일 : 2013. 11.12
	 * @Method 설명 : 사물함 대여 신청/수정 저장 처리
	 * ① 사물함 대여(신청) 정보가 1개 이상 존재하면 사물함갯수, 시작번호, 종료번호를 제외한 항목만 수정한다.
	 * ② 사물함 대여정보가 없다면 대여(신청)정보 테이블(TB_OFF_BOX_NUM)에서 해당 사물함코드를 삭제한 후 새로 생성한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxRentOrderProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxRentOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 신청 정보
		params.put("WMODE", CommonUtil.isNull(request.getParameter("WMODE"), ""));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("USERID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		String[] boxFlag = request.getParameterValues("BOX_FLAG");
		if (boxFlag != null && boxFlag.length>0){
			params.put("BOX_FLAG", boxFlag[0]);
		} else {
			params.put("BOX_FLAG", "Y");
		}
		String[] keyYN = request.getParameterValues("KEY_YN");
		if (keyYN != null && keyYN.length>0){
			params.put("KEY_YN", keyYN[0]);
		} else {
			params.put("KEY_YN", "R");
		}
		String[] rentType = request.getParameterValues("RENT_TYPE");
		if (rentType != null && rentType.length>0){
			params.put("RENT_TYPE", rentType[0]);
		} else {
			params.put("RENT_TYPE", "OFF");
		}
		String[] depositYN = request.getParameterValues("DEPOSIT_YN");
		if (depositYN != null && depositYN.length>0){
			params.put("DEPOSIT_YN", depositYN[0]);
		} else {
			params.put("DEPOSIT_YN", "N");
		}
		String[] extendYN = request.getParameterValues("EXTEND_YN");
		if (extendYN != null && extendYN.length>0){
			params.put("EXTEND_YN", extendYN[0]);
		} else {
			params.put("EXTEND_YN", "N");
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
		params.put("DEPOSIT", CommonUtil.isNull(request.getParameter("DEPOSIT"), "0"));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
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
		params.put("ADMIN_ID", params.get("USER_ID"));

		if ("INS".equals(params.get("WMODE"))) {
			// 사물함 신규 등록 처리
			// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
			HashMap<String, String> ordernoparams = new HashMap<String, String>();
			ordernoparams.put("PTYPE", "S");
			String orderNo = boxService.getOffOrderNo(ordernoparams);
			params.put("ORDERNO", orderNo);

			// 2. 오프라인 사물함 대여 주문 세부정보 등록 처리
			params.put("ISCANCEL", "0");
			params.put("STATUSCODE", "DLV105");
			boxService.offOrderMgntNoInsertProcess(params);

			// 3. 오프라인 사물함 대여 주문 등록 처리
			if ("OFF".equals(params.get("RENT_TYPE"))){
				params.put("ISOFFLINE","1");
			} else{
				params.put("ISOFFLINE","0");
			}

			boxService.offOrdersInsertProcess(params);

			// 3. 오프라인 사물함 대여 결제정보 등록처리
			boxService.offApprovalsInsertProcess(params);

			// 4. 사물함 대여정보테이블(TB_OFF_BOX_RENT)에 추가
			boxService.boxRentInsertProcess(params);

			// 5. 사물함 세부정보(TB_OFF_BOX_NUM) 업데이트 boxNumUpdateProcess
			int rentSeq = boxService.getBoxRentSeq(params);

			params.put("RENT_SEQ", String.valueOf(rentSeq));  // TB_OFF_RENT에 추가한 RENT_SEQ를 전달한다.
			params.put("BOX_FLAG", "Y");  // 처음 등록할 때는 무조건 사용(Y)으로 저장한다.
			boxService.boxNumUpdateProcess(params);
		}

		if ("EDT".equals(params.get("WMODE"))) {
			// 사물함 대여 수정 처리

			// 1. 사물함 세부정보(TB_OFF_BOX_NUM) 업데이트 boxNumUpdateProcess
			boxService.boxNumUpdateProcess(params);

			// 2. 사물함 대여정보(TB_OFF_BOX_RENT) 업데이트 boxNumRentUpdateProcess
			boxService.boxNumRentUpdateProcess(params);

			// 3. 사물함 주문 결제내역(TB_OFF_APPROVALS) 업데이트 offPayUpdateProcess
			boxService.offApprovalsUpdateProcess(params);
		}

		model.addAttribute("params",params);
		return "forward:/box/boxView.do";
	}

	/**
	 * @Method Name : boxExtendOrderProcess
	 * @작성일 : 2013. 11.13
	 * @Method 설명 : 사물함 대여 연장 처리
	 * - 신청구분: DE (데스크 접수)
     * - 주문번호 생성(TB_OFF_ORDERS에서 max(seq) + 1)
     * - 연장 기간 변경 : 시작일(기존 종료일+1),  종료일(기존종료일+2개월)
     * - TB_OFF_ORDERS 에 기존대여정보를 신규로 인서트한다.
     * - TB_OFF_ORDER_MGNT_NO 에 신규로 인서트 한다.  (obj_type='B')
     * - TB_OFF_APPROVALS 에 신규로 인서트 한다. (price_discount_type = 'W')
     * - TB_OFF_BOX_NUM 에 최종 정보로 업데이트한다.
     * - TB_OFF_BOX_RENT 에 신규로 인서트한다.
     *   (extend_yn='Y', key_yn='N', rent_type='OFF', deposit=10000,  2개월)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxExtendOrderProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxExtendOrderProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 신청 정보
		params.put("WMODE", CommonUtil.isNull(request.getParameter("WMODE"), ""));
		params.put("ROW_COUNT", CommonUtil.isNull(request.getParameter("ROW_COUNT"), "0"));
		params.put("ROW_NUM", CommonUtil.isNull(request.getParameter("ROW_NUM"), "0"));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("USERID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		String[] boxFlag = request.getParameterValues("BOX_FLAG");
		if (boxFlag != null && boxFlag.length>0){
			params.put("BOX_FLAG", boxFlag[0]);
		} else {
			params.put("BOX_FLAG", "Y");
		}
		String[] depositYN = request.getParameterValues("DEPOSIT_YN");
		if (depositYN != null && depositYN.length>0){
			params.put("DEPOSIT_YN", depositYN[0]);
		} else {
			params.put("DEPOSIT_YN", "N");
		}
		String[] approvalKind = request.getParameterValues("APPROVAL_KIND");
		if (approvalKind != null && approvalKind.length>0){
			params.put("APPROVAL_KIND", approvalKind[0]);
		} else {
			params.put("APPROVAL_KIND", "PAY140");
		}
		params.put("KEY_YN", "N");  // 키 = 미반납
		params.put("EXTEND_YN", "Y");  // 연장
		params.put("RENT_TYPE", "OFF");  // 오프라인
		params.put("DEPOSIT", "10000");
		params.put("PRICE_DISCOUNT_REASON", "사물함 데스크에서 연장");
		params.put("PRICE_GET_TOTAL", "10000");

		params.put("RENT_START", CommonUtil.isNull(request.getParameter("RENT_START"), ""));
		params.put("RENT_END", CommonUtil.isNull(request.getParameter("RENT_END"), ""));
		params.put("RENT_MEMO", CommonUtil.isNull(request.getParameter("RENT_MEMO"), ""));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("PRICE_DISCOUNT_VALUE", CommonUtil.isNull(request.getParameter("PRICE_DISCOUNT_VALUE"), "0"));
		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("PAYCODE", CommonUtil.isNull(request.getParameter("APPROVAL_KIND"), "PAY140"));

		if ("PAY140".equals(params.get("PAYCODE"))){
			params.put("PAY_GUBUN", "현금");
			params.put("PRICE_GET_CARD", "0");
			params.put("PRICE_GET_CASH", "10000");
		} else {
			params.put("PAY_GUBUN", "카드");
			params.put("PRICE_GET_CARD", "10000");
			params.put("PRICE_GET_CASH", "0");
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
		params.put("ADMIN_ID", params.get("REG_ID"));

		// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
		HashMap<String, String> ordernoparams = new HashMap<String, String>();
		ordernoparams.put("PTYPE", "S");
		String orderNo = boxService.getOffOrderNo(ordernoparams); 
		params.put("ORDERNO", orderNo);

		// 2. 오프라인 사물함 대여 주문 세부정보 등록 처리
		params.put("ISCANCEL", "0");
		params.put("STATUSCODE", "DLV105");
		boxService.offOrderMgntNoInsertProcess(params);

		// 3. 오프라인 사물함 대여 주문 등록 처리
		if ("OFF".equals(params.get("RENT_TYPE"))){
			params.put("ISOFFLINE","1");
		} else{
			params.put("ISOFFLINE","0");
		}

		boxService.offOrdersInsertProcess(params);

		// 3. 오프라인 사물함 대여 결제정보 등록처리
		boxService.offApprovalsInsertProcess(params);

		// 4. 사물함 대여정보테이블(TB_OFF_BOX_RENT)에 추가
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
		cal.add(Calendar.MONTH, 2);
		String nRentEnd = dateFormat.format(cal.getTime());  	// 연장 종료일

		params.put("RENT_START", nRentStart);	// 연장시 시작일
		params.put("RENT_END", nRentEnd);		// 연장시 종료일

		boxService.boxRentInsertProcess(params);

		// 5. 사물함 세부정보(TB_OFF_BOX_NUM) 업데이트 boxNumUpdateProcess
		int rentSeq = boxService.getBoxRentSeq(params);
		params.put("RENT_SEQ", String.valueOf(rentSeq));  // TB_OFF_RENT에 추가한 RENT_SEQ를 전달한다.
		boxService.boxNumUpdateProcess(params);

		model.addAttribute("params",params);
		return "redirect:/box/boxRentWrite.do";
	}


	/**
	 * @Method Name : boxRefundProcess
	 * @작성일 : 2013. 11.13
	 * @Method 설명 : 사물함 대여 환불 처리
     * - TB_OFF_REFUND 테이블에 환불 정보를 인서트한다.(환불금액, 환불일, 유저아이디)
     * - TB_OFF_MGNT_NO 테이블에 환불 정보를 인서트한다.
     * - TB_OFF_APPROVALS 테이블의 REFUND_PRICE, REFUND_DATE 항목에 환불금액을 저장한다.
     * - TB_OFF_BOX_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 사물함사용상태: 미사용)
     * - TB_OFF_BOX_NUM 사물함 상세정보의 BOX_FLAG = N, USERID = "" 초기화한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxRefundProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxRefundProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 신청 정보
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("REFUND_PRICE", CommonUtil.isNull(request.getParameter("REFUND_PRICE"), ""));
		params.put("REFUND_DATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));

		// 1. 환불 테이블(TB_REFUND)에 삽입한다.
//		boxService.offRefundInsertProcess(params); --테스트중

		// 2. TB_OFF_ORDER_MGNT_NO에 삽입한다.
		params.put("ADMIN_ID", params.get("REG_ID"));

		params.put("ORDERNO", params.get("ORDERNO"));
		params.put("PRICE_GET_TOTAL", String.valueOf((0 - Integer.parseInt(params.get("REFUND_PRICE")))));
		params.put("CANCELDATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));
		params.put("ISCANCEL", "2");
		params.put("STATUSCODE", "DLV230"); // 환불완료

		boxService.offOrderMgntNoInsertProcess(params);

		// 3. 오프라인 사물함 대여 결제정보 등록처리
//		params.put("REFUND_PRICE", CommonUtil.isNull(request.getParameter("REFUND_PRICE"), ""));
//		params.put("REFUND_DATE", CommonUtil.isNull(request.getParameter("REFUND_DATE"), ""));
//
//		boxService.offApprovalsRefundUpdateProcess(params);

	    // 4. TB_OFF_BOX_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 사물함사용상태: 미사용)
		// RENT_END       = SYSDATE,  DEPOSIT_YN      = 'Y', KEY_YN	       = 'Y', UPDATE_DT      = SYSDATE

		boxService.boxNumRentRefundUpdateProcess(params);

		// 5. TB_OFF_BOX_NUM 사물함 상세정보의 BOX_FLAG = N, USERID = "" 초기화한다.
		// USERID    = '',BOX_FLAG  = 'N',RENT_SEQ  = NULL, RENT_MEMO = '',
		params.put("BOX_FLAG", "N");

		boxService.boxNumResetUpdateProcess(params);

		model.addAttribute("RENT_SEQ", "");
		model.addAttribute("ORDERNO", "");

		model.addAttribute("params",params);
		return "forward:/box/boxOrderList.do";
	}

	/**
	 * @Method Name : boxChangePop
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 사물함 변경 처리
	 * - 기존 사물함 정보를 가져와서 팝업으로 표시한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxChange.pop")
	public String boxChange(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 기존 정보
		params.put("USED_BOX_CD", CommonUtil.isNull(request.getParameter("USED_BOX_CD"), ""));
		params.put("USED_BOX_NUM", CommonUtil.isNull(request.getParameter("USED_BOX_NUM"), ""));
		params.put("USED_RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));
		params.put("BOX_CD", CommonUtil.isNull(request.getParameter("BOX_CD"), ""));

		List<HashMap<String, String>> list = boxService.boxList(params);
		model.addAttribute("boxlist", list);

		List<HashMap<String, String>> boxNumList = boxService.getBoxNumList(params);
		model.addAttribute("boxnumList",boxNumList);

		model.addAttribute("USED_BOX_CD", params.get("USED_BOX_CD"));
		model.addAttribute("USED_BOX_NUM", params.get("USED_BOX_NUM"));
		model.addAttribute("USED_RENT_SEQ", params.get("USED_RENT_SEQ"));
		model.addAttribute("BOX_CD", params.get("BOX_CD"));

		model.addAttribute("params", params);

		return "/box/boxChange_pop";
	}

	/**
	 * @Method Name : boxChangePopProcess
	 * @작성일 : 2013. 11.25
	 * @Method 설명 : 사물함 변경 처리
     * - TB_OFF_BOX_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
     * - TB_OFF_BOX_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
     * - TB_OFF_BOX_RENT 테이블을 업데이트한다. (기존 자료에 신규 사물함번호를 저장한다)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/boxChangePopProcess.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxChangePopProcess(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		setParam(params, request, model);

		// 사물함 기존 정보
		params.put("USED_BOX_CD", CommonUtil.isNull(request.getParameter("USED_BOX_CD"), ""));
		params.put("USED_BOX_NUM", CommonUtil.isNull(request.getParameter("USED_BOX_NUM"), ""));
		params.put("USED_RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));

		// 사물함 신규 번호
		params.put("BOX_CD", CommonUtil.isNull(request.getParameter("BOX_CD"), ""));
		params.put("BOX_NUM", CommonUtil.isNull(request.getParameter("BOX_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("USED_RENT_SEQ"), ""));

		params.put("NEW_BOX_CD", params.get("BOX_CD"));
		params.put("NEW_BOX_NUM", params.get("BOX_NUM"));

		// 1. TB_OFF_BOX_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
		boxService.boxNumChangeUpdateProcess(params);

		// 2. TB_OFF_BOX_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
		params.put("BOX_CD", params.get("USED_BOX_CD"));
		params.put("BOX_NUM", params.get("USED_BOX_NUM"));
		boxService.boxNumResetUpdateProcess(params);

	    // 3. TB_OFF_BOX_RENT 테이블을 업데이트한다. (기존 자료에 신규 사물함번호를 저장한다)
		params.put("BOX_CD", params.get("NEW_BOX_CD"));
		params.put("BOX_NUM", params.get("NEW_BOX_NUM"));
		boxService.boxRentChangeUpdateProcess(params);

		model.addAttribute("params",params);
		return "redirect:/box/boxRentWrite.do";
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
		HashMap<String, String> loginInfo = (HashMap) session.getAttribute("AdmUserInfo");
		params.put("USER_ID", loginInfo.get("USER_ID"));
		params.put("REG_ID", loginInfo.get("USER_ID"));
		params.put("UPD_ID", loginInfo.get("USER_ID"));
		params.put("UPDATE_ID", loginInfo.get("USER_ID"));
		
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

		params.put("BOX_CD", CommonUtil.isNull(request.getParameter("BOX_CD"), ""));
		params.put("BOX_NM", CommonUtil.isNull(request.getParameter("BOX_NM"), ""));
		params.put("BOX_NUM", CommonUtil.isNull(request.getParameter("BOX_NUM"), ""));
		params.put("RENT_SEQ", CommonUtil.isNull(request.getParameter("RENT_SEQ"), ""));
		params.put("BOX_FLAG", CommonUtil.isNull(request.getParameter("BOX_FLAG"), ""));
		
	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
	    params.put("MENU_ID", CommonUtil.isNull(request.getParameter("MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), "OM_ROOT"));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
		model.addAttribute("MENU_ID", params.get("MENU_ID"));
		
	}

}
