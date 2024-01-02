package com.willbes.web.manage;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.excel.ExcelDownloadView;
import com.willbes.platform.util.paging.Paging;
import com.willbes.web.manage.excel.TeacherAdjustDetailExcel;
import com.willbes.web.manage.service.CategorySaleService;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @FileName   	: TeacherCalculteController.java
 * @Project    		: willbesWebAdmin
 * @Date       		: 2014. 11. 01.
 * @Author     		: rainend
 * @변경이력    	:
 * @프로그램 설명 	: 경영관리 - 학원 강사료 정산
 */
@RequestMapping(value="/offmng/teacher")
@Controller
public class TeacherCalculteController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private CategorySaleService  service;

	/**
	 * @Method Name  : offTeacherAdjustList
	 * @Author       :
	 * @변경이력    : 2014.05.20
	 * @Method 설명  :	강사료정산 목록
	 * @param   :  model
	 * @param   :  req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/offTeacherAdjustList.do")
	public String offTeacherAdjustList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName 	= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		String searchType 	= CommonUtil.isNull(request.getParameter("searchType"),  	"SETTLE_DT");
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("searchTeacherName",			searchTeacherName);
		params.put("searchType",				searchType);
		params.put("currentPage",				currentPage);
		params.put("pageRow",					pageRow);
		params.put("searchType",				searchType);
		params.put("startNo",					startNo);
		params.put("endNo",						endNo);

		List<HashMap<String,Object>> list = service.offTeacherAdjustList(params);

		// 총 건수
		String slistCount = service.offTeacherAdjustListCount(params);
		if ("".equals(slistCount) || slistCount.isEmpty()) {
			slistCount = "0";
		}
		int listCount = Integer.parseInt(slistCount);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", 						list);
		model.addAttribute("searchStartDate", 	searchStartDate);
		model.addAttribute("searchEndDate", 		searchEndDate);
		model.addAttribute("searchTeacherName",searchTeacherName);
		model.addAttribute("params", 	params);
		model.addAttribute("TOP_MENU_ID", 		CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 			CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", 			CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/manage/offTeacherAdjustList";
	}

	/**
	 * @Method Name  : offTeacherAdjustDetail
	 * @Author       :
	 * @변경이력    : 2014.05.20
	 * @Method 설명  :	강사료정산 상세 내용
	 * @param   :  model
	 * @param   :  req
	 * @return
	 * @throws UnsupportedEncodingException
	*/

	@RequestMapping(value="/offTeacherAdjustDetail.do")
	public String offTeacherAdjustDetail(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName = CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		String searchType 		= CommonUtil.isNull(request.getParameter("searchType"),  	"SETTLE_DT");
		String searchTeacher 	= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchLeccode	= CommonUtil.isNull(request.getParameter("searchLeccode"),  	"");
		int currentPage 		= Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow 			= Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("searchTeacher", 			searchTeacher);
		params.put("searchLeccode", 			searchLeccode);
		params.put("searchTeacherName", 		searchTeacherName);
		params.put("searchType", 				searchType);
		params.put("currentPage",				currentPage);
		params.put("pageRow",					pageRow);

		// 정산테이블(TB_PAYSETTLEMENT)과 정산정보를 조회한다.
		List<HashMap<String,Object>> list = service.offTeacherAdjustDetail(params);
		// 수강신청내역을 조회한다.
		List<HashMap<String,Object>> orderlist = service.offAdjustOrderList(params);
		// 추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductlist = service.offAdjustDeductList(params);
		// 기타추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductetclist = service.offAdjustDeductEtcList(params);

		model.addAttribute("list", 					list);
		model.addAttribute("orderlist", 			orderlist);
		model.addAttribute("deductlist", 			deductlist);
		model.addAttribute("deductetclist", 		deductetclist);
		model.addAttribute("params", 	params);
		model.addAttribute("TOP_MENU_ID", 			CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 				CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", 				CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		return "/manage/offTeacherAdjustDetail";
	}

	/**
	 * @Method Name  : offTeacherAdjustInsert
	 * @Author       :
	 * @변경이력    : 2014.05.31
	 * @Method 설명  :	강사료정산 상세 내용 저장
	 * @param   :  model
	 * @param   :  req
	 * @return
	 * @throws UnsupportedEncodingException
	*/

	@RequestMapping(value="/offTeacherAdjustInsert.do")
	public String offTeacherAdjustInsert(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName = CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		String searchType 		= CommonUtil.isNull(request.getParameter("searchType"),  	"SETTLE_DT");
		String searchTeacher 	= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchLeccode	= CommonUtil.isNull(request.getParameter("searchLeccode"),  	"");
		int currentPage 		= Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow 			= Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String SEQ				= CommonUtil.isNull(request.getParameter("SEQ"),  	"0");
		String TEACHER_NM		= CommonUtil.isNull(request.getParameter("TEACHER_NM"),  	"");
		String PREAMOUNT		= CommonUtil.isNull(request.getParameter("hPREAMOUNT"),  	"");
		String AMOUNT			= CommonUtil.isNull(request.getParameter("hAMOUNT"),  	"");
		String TEACHERAMOUNT		= CommonUtil.isNull(request.getParameter("hTEACHERAMOUNT"),  	"");
		String CALCUCRITERIA_DTYPE		= CommonUtil.isNull(request.getParameter("CALCUCRITERIA_DTYPE"),  	"");
		String CALCUCRITERIA_JTYPE		= CommonUtil.isNull(request.getParameter("CALCUCRITERIA_JTYPE"),  	"");
		String CALCUCRITERIA_DVALUE		= CommonUtil.isNull(request.getParameter("CALCUCRITERIA_DVALUE"),  	"");
		String CALCUCRITERIA_JVALUE		= CommonUtil.isNull(request.getParameter("CALCUCRITERIA_JVALUE"),  	"");
		String TEACHERPAY		= CommonUtil.isNull(request.getParameter("TEACHERPAY"),  	"0");
		String WITHHOLDRATIO	= CommonUtil.isNull(request.getParameter("WITHHOLDRATIO"),  	"0");
		String WITHHOLDTAX		= CommonUtil.isNull(request.getParameter("WITHHOLDTAX"),  	"0");
		String ADJUSTAMOUNT		= CommonUtil.isNull(request.getParameter("ADJUSTAMOUNT"),  	"");

		String[] PSA_NO_ARR		= request.getParameterValues("PSA_NO");
		String[] ADDTYPE_ARR	= request.getParameterValues("ADDTYPE");
		String[] ADDMONEY_ARR	= request.getParameterValues("ADDMONEY");
		String[] ADDMEMO_ARR = request.getParameterValues("ADDMEMO");
		String[] ETCYN_ARR = request.getParameterValues("ETCYN");
		String[] hADDMONEY_ARR = request.getParameterValues("hADDMONEY");
		String[] hADDMONEYE_ARR = request.getParameterValues("hADDMONEYE");

//		DEDUCTAMOUNT
//		DEDUCTAMOUNT_ETC
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("searchTeacher", 			searchTeacher);
		params.put("searchLeccode", 			searchLeccode);
		params.put("searchTeacherName", 		searchTeacherName);
		params.put("searchType", 				searchType);
		params.put("currentPage",				currentPage);
		params.put("pageRow",					pageRow);

		int ideductAmount = 0;
		int ideductAmount_etc = 0;
		for(int j=0; j<hADDMONEY_ARR.length; j++){
			ideductAmount += Integer.parseInt(hADDMONEY_ARR[j]);
		}
		for(int j=0; j<hADDMONEYE_ARR.length; j++){
			ideductAmount_etc += Integer.parseInt(hADDMONEYE_ARR[j]);
		}

		HashMap<String, Object> params1 = new HashMap<String, Object>();
		params1.put("SEQ", 						SEQ);
		params1.put("TEACHER_ID", 				searchTeacher);
		params1.put("LECCODE", 					searchLeccode);
		params1.put("TEACHER_NM", 				TEACHER_NM);
		params1.put("PREAMOUNT", 				PREAMOUNT);
		params1.put("AMOUNT", 					AMOUNT);
		params1.put("TEACHERAMOUNT", 			TEACHERAMOUNT);
		params1.put("CALCUCRITERIA_DTYPE", 		CALCUCRITERIA_DTYPE);
		params1.put("CALCUCRITERIA_JTYPE", 		CALCUCRITERIA_JTYPE);
		params1.put("CALCUCRITERIA_DVALUE", 	CALCUCRITERIA_DVALUE);
		params1.put("CALCUCRITERIA_JVALUE", 	CALCUCRITERIA_JVALUE);
		params1.put("TEACHERPAY", 				TEACHERPAY);
		params1.put("WITHHOLDRATIO", 			WITHHOLDRATIO);
		params1.put("WITHHOLDTAX", 				WITHHOLDTAX);
		params1.put("ADJUSTAMOUNT", 			ADJUSTAMOUNT);
		params1.put("DEDUCTAMOUNT", 			String.valueOf(ideductAmount));
		params1.put("DEDUCTAMOUNT_ETC", 		String.valueOf(ideductAmount_etc));

		if("0".equals(params1.get("SEQ"))) {
			// 정산테이블(TB_PAYSETTLEMENT)에 저장한다.
			service.offTeacherAdjustInsert(params1);
		} else {
			// 정산테이블(TB_PAYSETTLEMENT)에 업데이트한다.
			service.offTeacherAdjustUpdate(params1);
		}

		HashMap<String, Object> params2 = new HashMap<String, Object>();
		params2.put("TEACHER_ID", 		searchTeacher);
		params2.put("LECCODE", 			searchLeccode);

		for(int j=0; j<PSA_NO_ARR.length; j++){
			if (ADDMEMO_ARR[j] != null && ADDMEMO_ARR[j].length() > 1) {
			params2.put("PSA_NO", 0);
			params2.put("PSA_NO", PSA_NO_ARR[j]);
			params2.put("ADDTYPE", ADDTYPE_ARR[j]);
			params2.put("ADDMONEY", ADDMONEY_ARR[j]);
			params2.put("ADDMEMO", ADDMEMO_ARR[j]);
			params2.put("ETCYN", ETCYN_ARR[j]);
			params2.put("ADDTYPE", ADDTYPE_ARR[j]);
			service.offAdjustDeductInsert(params2);
			}
		}


		// 정산테이블(TB_PAYSETTLEMENT)과 정산정보를 조회한다.
/*		List<HashMap<String,Object>> list = service.offTeacherAdjustDetail(params);
		// 수강신청내역을 조회한다.
		List<HashMap<String,Object>> orderlist = service.offAdjustOrderList(params);
		// 추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductlist = service.offAdjustDeductList(params);
		// 기타추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductetclist = service.offAdjustDeductEtcList(params);

		model.addAttribute("list", 					list);
		model.addAttribute("orderlist", 			orderlist);
		model.addAttribute("deductlist", 			deductlist);
		model.addAttribute("deductetclist", 		deductetclist);
*/
		model.addAttribute("params", 	params);
		model.addAttribute("searchStartDate", 			searchStartDate);
		model.addAttribute("searchEndDate", 			searchEndDate);
		model.addAttribute("searchTeacherName", 		searchTeacherName);
		model.addAttribute("searchType", 				searchType);
		model.addAttribute("currentPage",				currentPage);
		model.addAttribute("pageRow",					pageRow);
		model.addAttribute("TOP_MENU_ID", 			CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		model.addAttribute("MENUTYPE", 				CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		model.addAttribute("L_MENU_NM", 				CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		return "redirect:/offmng/teacher/offTeacherAdjustList.do";
	}



	@RequestMapping(value="/offTeacherAdjustExcelDownload.do")
	public View offTeacherAdjustExcelDownload(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {

		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName = CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		String searchType 		= CommonUtil.isNull(request.getParameter("searchType"),  	"SETTLE_DT");
		String searchTeacher 	= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"");
		String searchLeccode	= CommonUtil.isNull(request.getParameter("searchLeccode"),  	"");
		int currentPage 		= Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow 			= Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("searchTeacher", 			searchTeacher);
		params.put("searchLeccode", 			searchLeccode);
		params.put("searchTeacherName", 		searchTeacherName);
		params.put("searchType", 				searchType);
		params.put("currentPage",				currentPage);
		params.put("pageRow",					pageRow);

		// 정산테이블(TB_PAYSETTLEMENT)과 정산정보를 조회한다.
		List<HashMap<String,Object>> list = service.offTeacherAdjustDetail(params);
		// 수강신청내역을 조회한다.
		List<HashMap<String,Object>> orderlist = service.offAdjustOrderList(params);
		// 추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductlist = service.offAdjustDeductList(params);
		// 기타추가사항 정보를 조회한다.
		List<HashMap<String,Object>> deductetclist = service.offAdjustDeductEtcList(params);

		model.addAttribute("list", list);
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("deductlist", deductlist);
		model.addAttribute("deductetclist", deductetclist);
		model.addAttribute("searchTeacherName", searchTeacherName);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);

		return new TeacherAdjustDetailExcel();
	}


	/**
	 * @Method Name  : offTeacherAdjustExcel
	 * @Author       :
	 * @변경이력    : 2014.05.20
	 * @Method 설명  :	강사료정산 목록 엑셀 출력
	 * @param   :  model
	 * @param   :  req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/offTeacherAdjustExcel.do")
	public View offTeacherAdjustExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchStartDate 	= CommonUtil.isNull(request.getParameter("searchStartDate"), 	CommonUtil.getCurrentDate());
		String searchEndDate 	= CommonUtil.isNull(request.getParameter("searchEndDate"),  	CommonUtil.getCurrentDate());
		String searchTeacherName 	= CommonUtil.isNull(request.getParameter("searchTeacherName"),  	"");
		String searchType 	= CommonUtil.isNull(request.getParameter("searchType"),  	"SETTLE_DT");

		int startNo = 0;
		int endNo = 10000;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchStartDate", 			searchStartDate);
		params.put("searchEndDate", 			searchEndDate);
		params.put("searchTeacherName",			searchTeacherName);
		params.put("searchType",				searchType);
		params.put("startNo",					startNo);
		params.put("endNo",						endNo);

		List<HashMap<String,Object>> exec_list = service.offTeacherAdjustList(params);

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "강사료정산리스트(" + searchStartDate + "~" + searchEndDate + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("No.");
		headerList.add("정산일자");
		headerList.add("강사명");
		headerList.add("과목명");
		headerList.add("강의명");
		headerList.add("강의시작일");
		headerList.add("강의종료일");
//		headerList.add("단과반");
//		headerList.add("종합반");
		headerList.add("수강인원");
		headerList.add("공제전수강총액");
		headerList.add("수강총액");
		headerList.add("공제액");
		headerList.add("강사료산정대상금액");
		headerList.add("비율");
		headerList.add("강사료");
		headerList.add("원천세");
		headerList.add("가타공제액");
		headerList.add("정산금액");

//		int idcnt = 0;
//    	int ijcnt = 0;
    	int offercnt = 0;
    	int preamount = 0;
    	int amount = 0;
    	int deductamount = 0;
    	int teacheramount = 0;
    	int teacherpay = 0;
    	int withholdtax = 0;
    	int deductamount_etc = 0;
    	int adjustamount = 0;

		int rno = 1;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		for(HashMap<String, Object> map : exec_list) {

//			idcnt += Integer.parseInt(String.valueOf(map.get("DCNT")));
//			ijcnt += Integer.parseInt(String.valueOf(map.get("JCNT")));
			offercnt += Integer.parseInt(String.valueOf(map.get("OFFERERCNT")));
			preamount += Integer.parseInt(String.valueOf(map.get("PREAMOUNT")));
			amount += Integer.parseInt(String.valueOf(map.get("AMOUNT")));
			deductamount += Integer.parseInt(String.valueOf(map.get("DEDUCTAMOUNT")));
			teacheramount += Integer.parseInt(String.valueOf(map.get("TEACHERAMOUNT")));
			teacherpay += Integer.parseInt(String.valueOf(map.get("TEACHERPAY")));
			withholdtax += Integer.parseInt(String.valueOf(map.get("WITHHOLDTAX")));
			deductamount_etc += Integer.parseInt(String.valueOf(map.get("DEDUCTAMOUNT_ETC")));
			adjustamount += Integer.parseInt(String.valueOf(map.get("ADJUSTAMOUNT")));

			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(rno++));
			newMap.put(i++, map.get("SETTLE_DT"));
			newMap.put(i++, map.get("TEACHER_NM"));
			newMap.put(i++, map.get("SUBJECT_NM"));
			newMap.put(i++, map.get("SUBJECT_TITLE"));
			newMap.put(i++, map.get("MIN_DATE"));
			newMap.put(i++, map.get("MAX_DATE"));
//			newMap.put(i++, map.get("DCNT"));
//			newMap.put(i++, map.get("JCNT"));
//			newMap.put(i++, String.valueOf(Integer.parseInt(map.get("DCNT").toString()) + Integer.parseInt(map.get("JCNT").toString())));
			newMap.put(i++, map.get("OFFERERCNT"));
			newMap.put(i++, map.get("PREAMOUNT"));
			newMap.put(i++, map.get("AMOUNT"));
			newMap.put(i++, map.get("DEDUCTAMOUNT"));
			newMap.put(i++, map.get("TEACHERAMOUNT"));
			newMap.put(i++, map.get("CALCUCRITERIA_DVALUE"));
			newMap.put(i++, map.get("TEACHERPAY"));
			newMap.put(i++, map.get("WITHHOLDTAX"));
			newMap.put(i++, map.get("DEDUCTAMOUNT_ETC"));
			newMap.put(i++, map.get("ADJUSTAMOUNT"));
			newList.add(newMap);
		}
		HashMap newMap = new HashMap();
		int i = 0;
		newMap.put(i++, String.valueOf(rno++));
		newMap.put(i++, "");
		newMap.put(i++, "인원합계 :");
		newMap.put(i++, "");
		newMap.put(i++, "");
		newMap.put(i++, "");
		newMap.put(i++, "");
//		newMap.put(i++, String.valueOf(idcnt));
//		newMap.put(i++, String.valueOf(ijcnt));
//		newMap.put(i++, String.valueOf(idcnt+ijcnt));
		newMap.put(i++, String.valueOf(offercnt));
		newMap.put(i++, String.valueOf(preamount));
		newMap.put(i++, String.valueOf(amount));
		newMap.put(i++, String.valueOf(deductamount));
		newMap.put(i++, String.valueOf(teacheramount));
		newMap.put(i++, "");
		newMap.put(i++, String.valueOf(teacherpay));
		newMap.put(i++, String.valueOf(withholdtax));
		newMap.put(i++, String.valueOf(deductamount_etc));
		newMap.put(i++, String.valueOf(adjustamount));
		newList.add(newMap);

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

	/**
	 * @Method Name  : offTeacherOrdersExcel
	 * @Author       :
	 * @변경이력    : 2014.06.02
	 * @Method 설명  :	강사료정산의 수강내역 엑셀 출력
	 * @param   :  model
	 * @param   :  req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/offTeacherOrdersExcel.do")
	public View offTeacherOrdersExcel(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		// 검색조건
		String searchTeacher 	= CommonUtil.isNull(request.getParameter("searchTeacher"),  	"0");
		String searchLeccode	= CommonUtil.isNull(request.getParameter("searchLeccode"),  	"0");
		String teachername	= CommonUtil.isNull(request.getParameter("teachername"),  	"0");
		String startdate	= CommonUtil.isNull(request.getParameter("startdate"),  	"0");
		String enddate	= CommonUtil.isNull(request.getParameter("enddate"),  	"0");
		String subjecttitle	= CommonUtil.isNull(request.getParameter("subjecttitle"),  	"0");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchTeacher", 			searchTeacher);
		params.put("searchLeccode", 			searchLeccode);

		List<HashMap<String,Object>> exec_list = service.offAdjustOrderList(params);

		Date date = new Date();
	    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		String excelName = "강사료정산_수강내역("+ teachername + "," + startdate + "~" + enddate + "," + subjecttitle + ")_" + simpleDate.format(date);

		List<String> headerList = new ArrayList<String>();
		headerList.add("번호");
		headerList.add("주문번호");
		headerList.add("수강신청일");
		headerList.add("성명");
		headerList.add("연락처");
		headerList.add("이메일");
		headerList.add("현금");
		headerList.add("카드");
		headerList.add("예금");
		headerList.add("가상계좌");
		headerList.add("계좌이체");
		headerList.add("카드수수료");
		headerList.add("환불");
		headerList.add("합계");
		headerList.add("수강구분");
		headerList.add("비고");
		headerList.add("추가할인");

		int isum = 0;
		int rno = 1;

		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		for(HashMap<String, Object> map : exec_list) {

			isum = isum + Integer.parseInt(String.valueOf(map.get("PRICE_CASH"))) + Integer.parseInt(String.valueOf(map.get("PRICE_CARD")));
			isum = isum + Integer.parseInt(String.valueOf(map.get("PRICE_BANK"))) + Integer.parseInt(String.valueOf(map.get("PRICE_VACCT")));
			isum = isum + Integer.parseInt(String.valueOf(map.get("PRICE_DBANK"))) - Integer.parseInt(String.valueOf(map.get("CHARGE")));

			HashMap newMap = new HashMap();
			int i = 0;
			newMap.put(i++, String.valueOf(rno++));		// 번호
			newMap.put(i++, map.get("ORDERNO"));		// 주문번호
			newMap.put(i++, map.get("REG_DT"));			// 수강신청일
			newMap.put(i++, map.get("USER_NM"));		// 성명
			newMap.put(i++, map.get("PHONE_NO"));		// 연락처
			newMap.put(i++, map.get("EMAIL"));			// 이메일
			newMap.put(i++, map.get("PRICE_CASH"));		// 현금
			newMap.put(i++, map.get("PRICE_CARD"));		// 카드
			newMap.put(i++, map.get("PRICE_BANK"));		// 예금
			newMap.put(i++, map.get("PRICE_VACCT"));		// 가상계좌
			newMap.put(i++, map.get("PRICE_DBANK"));		// 계좌이체
			newMap.put(i++, map.get("CHARGE"));			// 카드수수료
			newMap.put(i++, map.get("REFUND"));			// 환불
			newMap.put(i++, String.valueOf(isum));		// 합계
			newMap.put(i++, map.get("PTYPE"));			// 수강구분
			newMap.put(i++, map.get("PRICE_DISCOUNT_REASON"));	// 비고
			newMap.put(i++, map.get("DISCOUNTPLUS"));	// 추가할인
			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

}