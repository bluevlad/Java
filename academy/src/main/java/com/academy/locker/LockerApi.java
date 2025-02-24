package com.academy.locker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import com.academy.locker.service.LockerService;
import com.academy.locker.service.LockerVO;

@RestController
public class LockerApi extends CORSFilter {

    private LockerService lockerService;

    public LockerApi(LockerService lockerService) {
        this.lockerService = lockerService;
    }
    
	// 0. Spring Security 사용자권한 처리 - 추후 해당 부분 모두 추가
	/*
	 * Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	 * if(!isAuthenticated) { model.addAttribute("message",
	 * egovMessageSource.getMessage("fail.common.login")); return
	 * "egovframework/com/uat/uia/EgovLoginUsr"; }
	 * 
	 * //로그인 객체 선언 LoginVO loginVO =
	 * (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	 * 
	 * //아이디 설정 lockerVO.setRegId(loginVO == null ? "" :
	 * EgovStringUtil.isNullToString(loginVO.getUniqId())); lockerVO.setUpdId(loginVO
	 * == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	 */
    
	/**
	 * 사물함 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getLockerList")
	public ArrayList<JSONObject> list(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception, IOException, ParseException { 
		
		JSONObject jsonObject = new JSONObject();
		LockerVO lockerVO = new LockerVO();
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(lockerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(lockerVO.getPageUnit());
		paginationInfo.setPageSize(lockerVO.getPageSize());

		lockerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		lockerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		lockerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("lockers", lockerService.selectLockerList(lockerVO));

		int totCnt = lockerService.selectLockerListTotCnt(lockerVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return lockerService.selectLockerList(lockerVO);
	}

	/**
	 * 사물함 등록화면.
	 * @throws Exception
	 */
	@GetMapping(value = "/api/getLocker")
	public JSONObject get(@ModelAttribute("LockerVO") LockerVO lockerVO) throws Exception, IOException, ParseException { 

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("item", lockerService.getLocker(lockerVO));

		return jsonObject;
	}

	/**
	 * 사물함정보를 신규로 등록한다.
	 * @param lockerVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@GetMapping(value="/api/lockerInsert")
	public String Insert(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
		
		lockerService.insertLocker(lockerVO);

		int boxCount = CommonUtil.parseInt(commandMap.get("boxCount").toString());	// 사물함 총 갯수
		int startNum = CommonUtil.parseInt(commandMap.get("startNum").toString());	// 시작번호
		int endNum = CommonUtil.parseInt(commandMap.get("endNum").toString());	// 종료번호

		if (boxCount > 0){
			for( int i = startNum; i <= endNum; i++ ) {
				lockerVO.setBoxNum(i);
				lockerService.insertLockerNum(lockerVO);
			}
		}

		return "redirect:/academy/box/List.do";
	}

	/**
	 * 사물함정보를 변경한다.
	 * @param ExamVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@GetMapping(value="/api/lockerUpdate")
	public String Update(@ModelAttribute("LockerVO") LockerVO lockerVO) throws Exception {

		// 0. Spring Security 사용자권한 처리

		lockerService.updateLocker(lockerVO);

		return "OK";
	}

	/**
	 * @Method Name : lockerRentWrite
	 * @작성일 : 2023. 09
	 * @Method 설명 : 사물함 대여 신청 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerRentWrite")
	public String boxRentWrite(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		// 사물함 BOX_CD로 상세정보를 가져온다.
        model.addAttribute("rentSeq", commandMap.get("rentSeq") == null ? "" : (String)commandMap.get("rentSeq"));
        lockerVO.setRentSeq(CommonUtil.parseInt(commandMap.get("rentSeq").toString()));

        JSONObject lockerNumRentDetail = null;
        JSONObject lockerNumRentOrderDetail = null;
		if (lockerVO.getRentSeq() > 0) {
			// 사물함 대여 신청 정보(현재)
			lockerNumRentDetail = lockerService.selectLockerNumRentDetail(lockerVO);
			
			// 사물함 대여 현재 결제 정보를 가져온다
			if (lockerNumRentDetail != null){
				lockerVO.setOrderno(lockerNumRentDetail.get("Orderno").toString());
				lockerNumRentOrderDetail = lockerService.selectLockerNumRentOrderDetail(lockerVO);
			}
		}

		if (lockerNumRentOrderDetail != null){
			model.addAttribute("WMODE", "EDT");
		} else {
			model.addAttribute("WMODE", "INS");
		}

		JSONObject BoxDetail = lockerService.getLocker(lockerVO);
        model.addAttribute("lockerVO", BoxDetail);
		
		// 사물함 대여 결제 이력들
		model.addAttribute("boxNumRentOrderList", lockerService.selectLockerNumRentOrderList(lockerVO));
		model.addAttribute("boxNumRentDetail", lockerNumRentDetail);
		model.addAttribute("boxNumRentOrderDetail", lockerNumRentOrderDetail);

		return "egovframework/com/academy/box/RentWrite";
	}

	/**
	 * @Method Name : updateLockerFlag
	 * @작성일 : 2023. 10
	 * @Method 설명 : 사물함 상태값 업데이트
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/UpdateLockerFlag")
	@ResponseBody
	public String checkUser(@ModelAttribute("LockerVO") LockerVO lockerVO) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리

        lockerService.updateLockerFlag(lockerVO);

		return "Y";
	}

	/**
	 * @Method Name : ExtendOrder
	 * @작성일 : 2023. 09
	 * @Method 설명 : 사물함 대여 연장 처리
	 * - 신청구분: DE (데스크 접수)
     * - 주문번호 생성(acm_orders에서 max(seq) + 1)
     * - 연장 기간 변경 : 시작일(기존 종료일+1),  종료일(기존종료일+2개월)
     * - acm_orders 에 기존대여정보를 신규로 인서트한다.
     * - acm_order_item 에 신규로 인서트 한다.  (obj_type='B')
     * - acm_order_approvals 에 신규로 인서트 한다. (price_discount_type = 'W')
     * - acm_box_num 에 최종 정보로 업데이트한다.
     * - acm_box_rent 에 신규로 인서트한다.
     *   (extend_yn='Y', key_yn='N', rent_type='OF', deposit=10000,  2개월)
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerExtendOrder")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String ExtendOrder(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리

		lockerVO.setRentMemo("사물함 데스크에서 연장");
		lockerVO.setPriceDiscountReason("사물함 데스크에서 연장");

		lockerVO.setOrderType("B");
		lockerVO.setOrderYear(CommonUtil.getCurrentYear());

		int seq = lockerService.getOrderSeq(lockerVO);
		String orderNo = lockerVO.getOrderType()+lockerVO.getOrderYear()+seq+"";
		
		lockerVO.setOrderno(orderNo);
		lockerVO.setItemno(lockerVO.getBoxCd()+"-"+lockerVO.getBoxNum());

		lockerService.insertOrders(lockerVO);

		// 3. 오프라인 사물함 대여 결제정보 등록처리
		lockerService.insertApprovals(lockerVO);

		lockerService.insertOrderItem(lockerVO);

		// 4. 사물함 대여정보테이블(TB_OFF_BOX_RENT)에 추가
		// 기간을 연장한다. = 기존 종료일 + 2개월
		String rentEnd = lockerVO.getRentEnd();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date stdate = null;
		Date endate = null;
		stdate = dateFormat.parse(rentEnd);   // 기존 시작일
		endate = dateFormat.parse(rentEnd);   	// 기존 종료일
		Calendar cal = Calendar.getInstance();
		cal.setTime(stdate);
		cal.add(Calendar.DATE, 1);
		String nRentStart = dateFormat.format(cal.getTime()); 	// 연장 시작일
		cal.setTime(endate);
		cal.add(Calendar.MONTH, 2);
		String nRentEnd = dateFormat.format(cal.getTime());  	// 연장 종료일

		lockerVO.setRentStart(nRentStart);	// 연장시 시작일
		lockerVO.setRentEnd(nRentEnd);		// 연장시 종료일

		// 5. 사물함 세부정보(TB_OFF_BOX_NUM) 업데이트 boxNumUpdateProcess
		int rentSeq = lockerService.getLockerRentSeq(lockerVO);
		lockerVO.setRentSeq(rentSeq);

		lockerVO.setKeyYn("N");  // 키 = 미반납
		lockerVO.setExtendYn("Y");  // 연장
		lockerVO.setRentType("OF");  // 오프라인
		
		lockerService.insertLockerRent(lockerVO);

		lockerService.updateLockerNum(lockerVO);

		return "forward:/academy/box/List.do";
	}

	/**
	 * @Method Name : lockerRentOrderProcess
	 * @작성일 : 2013. 11.12
	 * @Method 설명 : 사물함 대여 신청/수정 저장 처리
	 * ① 사물함 대여(신청) 정보가 1개 이상 존재하면 사물함갯수, 시작번호, 종료번호를 제외한 항목만 수정한다.
	 * ② 사물함 대여정보가 없다면 대여(신청)정보 테이블(TB_OFF_BOX_NUM)에서 해당 사물함코드를 삭제한 후 새로 생성한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerRentOrder")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public String RentOrder(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리

		// 사물함 관리 모드를 확인한다.
        model.addAttribute("WMODE", commandMap.get("WMODE") == null ? "" : (String)commandMap.get("WMODE"));

        if (lockerVO.getBoxFlag() == null) {
        	lockerVO.setBoxFlag("Y");
        }
        if (lockerVO.getKeyYn() == null) {
        	lockerVO.setKeyYn("R");
        }
        if (lockerVO.getRentType() == null) {
        	lockerVO.setRentType("OF");
        }
        if (lockerVO.getDepositYn() == null) {
        	lockerVO.setDepositYn("N");
        }
        if (lockerVO.getExtendYn() == null) {
        	lockerVO.setExtendYn("N");
        }
        

		if ("INS".equals(model.get("WMODE"))) {
			// 사물함 신규 등록 처리
			// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
			lockerVO.setOrderType("B");
			lockerVO.setOrderYear(CommonUtil.getCurrentYear());

			int seq = lockerService.getOrderSeq(lockerVO);
			String orderNo = lockerVO.getOrderType()+lockerVO.getOrderYear()+seq+"";
			
			lockerVO.setOrderno(orderNo);

			lockerVO.setItemno(lockerVO.getBoxCd()+"-"+lockerVO.getBoxNum());
//			lockerVO.setMemo("사물함 데스크에서 신청");
			
			// 2. 오프라인 사물함 대여 주문 세부정보 등록 처리
//			lockerVO.setIsCancel("0");
//			lockerVO.setStatuscode("DLV105");
			lockerService.insertOrderItem(lockerVO);

			// 3. 오프라인 사물함 대여 주문 등록 처리
			if ("OF".equals(lockerVO.getRentType())){
				lockerVO.setOrderType("1");
			} else{
				lockerVO.setOrderType("0");
			}

			lockerService.insertOrders(lockerVO);

			// 3. 오프라인 사물함 대여 결제정보 등록처리
			lockerService.insertApprovals(lockerVO);

			int rentSeq = lockerService.getLockerRentSeq(lockerVO);
			lockerVO.setRentSeq(rentSeq);
			// 4. 사물함 대여정보테이블(acm_box_rent)에 추가
			lockerService.insertLockerRent(lockerVO);

			// 5. 사물함 세부정보(acm_box_num) 업데이트 boxNumUpdate
			lockerVO.setBoxFlag("Y");  // 처음 등록할 때는 무조건 사용(Y)으로 저장한다.
			lockerService.updateLockerNum(lockerVO);
		}

		if ("EDT".equals(model.get("WMODE"))) {
			// 사물함 대여 수정 처리

			// 1. 사물함 세부정보(acm_box_num) 업데이트 boxNumUpdate
			lockerService.updateLockerNum(lockerVO);

			// 2. 사물함 대여정보(acm_box_rent) 업데이트 boxNumRentUpdate
			lockerService.updateLockerNumRent(lockerVO);

			// 3. 사물함 주문 결제내역(acm_order_approvals) 업데이트 offPayUpdateProcess
			lockerService.updateApprovals(lockerVO);
		}

		return "forward:/academy/box/List.do";
	}

	/**
	 * @Method Name : boxChangePop
	 * @작성일 : 2023. 10.
	 * @Method 설명 : 사물함 변경 처리
	 * - 기존 사물함 정보를 가져와서 팝업으로 표시한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerChange.pop")
	public String boxChange(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(lockerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(lockerVO.getPageUnit());
		paginationInfo.setPageSize(lockerVO.getPageSize());

		lockerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		lockerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		lockerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		model.addAttribute("paginationInfo", paginationInfo);
		
		// 사물함 기존 정보
        model.addAttribute("usedBoxCd", commandMap.get("usedBoxCd") == null ? "" : (String)commandMap.get("usedBoxCd"));
        model.addAttribute("usedBoxNum", commandMap.get("usedBoxNum") == null ? "" : (String)commandMap.get("usedBoxNum"));
        model.addAttribute("usedRentSeq", commandMap.get("usedRentSeq") == null ? "" : (String)commandMap.get("usedRentSeq"));
        model.addAttribute("boxCd", commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd"));

		model.addAttribute("boxlist", lockerService.selectLockerList(lockerVO));
		model.addAttribute("boxnumList", lockerService.selectLockerNumList(lockerVO));

		return "egovframework/com/academy/box/change_pop";
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
	@GetMapping("/api/lockerChangePop")
	public ModelAndView changePop(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap) throws Exception {

    	ModelAndView ret = new ModelAndView("jsonView");

		// 사물함 기존 정보
        String usedBoxCd = commandMap.get("usedBoxCd") == null ? "" : (String)commandMap.get("usedBoxCd");
        String usedBoxNum = commandMap.get("usedBoxNum") == null ? "" : (String)commandMap.get("usedBoxNum");

		// 사물함 신규 번호
        String boxCd = commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd");
        String boxNum = commandMap.get("boxNum") == null ? "" : (String)commandMap.get("boxNum");
        String rentSeq = commandMap.get("usedRentSeq") == null ? "" : (String)commandMap.get("usedRentSeq");

		//로그인 객체 선언
//		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//아이디 설정
//		lockerVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
//		lockerVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		lockerVO.setBoxCd(usedBoxCd);
		lockerVO.setBoxNum(CommonUtil.parseInt(usedBoxNum));
		lockerVO.setRentSeq(CommonUtil.parseInt(rentSeq));

		JSONObject boxNumChange = lockerService.selectLockerNumRentDetail(lockerVO);

//		lockerVO.setUserId(boxNumChange.getUserId());
//		lockerVO.setBoxFlag(boxNumChange.getBoxFlag());
//		lockerVO.setRentSeq(boxNumChange.getRentSeq());
//		lockerVO.setRentMemo(boxNumChange.getRentMemo());
		
		// 1. TB_OFF_BOX_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
		lockerVO.setBoxCd(boxCd);
		lockerVO.setBoxNum(CommonUtil.parseInt(boxNum));
        lockerService.updateLockerNumChange(lockerVO);

		// 2. TB_OFF_BOX_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
		lockerVO.setUsedBoxCd(usedBoxCd);
		lockerVO.setUsedBoxNum(CommonUtil.parseInt(usedBoxNum));
		lockerService.updateLockerNumReset(lockerVO);

	    // 3. TB_OFF_BOX_RENT 테이블을 업데이트한다. (기존 자료에 신규 사물함번호를 저장한다)
		lockerService.updateLockerRentChange(lockerVO);

		ret.addObject("ret", "OK");
		return ret;
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

	@GetMapping(value = "/academy/box/OrderView.do")
	public String boxOrderView(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        String orderno = commandMap.get("orderno") == null ? "" : (String)commandMap.get("orderno");
        String statuscode = commandMap.get("statuscode") == null ? "" : (String)commandMap.get("statuscode");
		
		lockerVO.setOrderno(orderno);
//		lockerVO.setStatuscode(statuscode);
		
//		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), "ORDERLIST"));

		if (!orderno.isEmpty()) {
			// 사물함 대여 신청 정보(현재)
			JSONObject lockerOrderDetail = lockerService.selectLockerOrderDetail(lockerVO);

			model.addAttribute("boxOrderDetail", lockerOrderDetail);

			return "egovframework/com/academy/box/OrderView";

		} else {
			return "forward:/academy/box/RentWrite.do";
		}
	}

	/**
	 * @Method Name : DeleteOrder
	 * @작성일 : 2023. 11.
	 * @Method 설명 : 사물함 대여 주문정보 삭제 처리
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerDeleteOrder")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String DeleteOrder(@ModelAttribute("LockerVO") LockerVO lockerVO) throws Exception {

		// 0. Spring Security 사용자권한 처리

		JSONObject boxOrderDetail  = lockerService.selectLockerOrderDetail(lockerVO);

		if (boxOrderDetail != null) {
			// 사물함 정보 변경
			lockerService.updateLockerNumReset(lockerVO);

			// 사물함 대여정보 삭제 (주문번호 이용)
			lockerService.deleteLockerRentByOrderId(lockerVO);

			// 사물함 주문정보 삭제 (주문번호 이용)
			lockerService.deleteLockerOrder(lockerVO);
		}
		
		return "OK";

	}


	/**
	 * @Method Name : boxRefundProcess
	 * @작성일 : 2023. 11.
	 * @Method 설명 : 사물함 대여 환불 처리
     * - ACM_ORDER_REFUND 테이블에 환불 정보를 인서트한다.(환불금액, 환불일, 유저아이디)
     * - ACM_ORDER_ITEM 테이블에 환불 정보를 인서트한다.
     * - ACM_ORDER_APPROVALS 테이블의 REFUND_PRICE, REFUND_DATE 항목에 환불금액을 저장한다.
     * - ACM_BOX_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 사물함사용상태: 미사용)
     * - ACM_BOX_NUM 사물함 상세정보의 BOX_FLAG = N, USERID = "" 초기화한다.
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@GetMapping(value = "/api/lockerRefund")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String boxRefund(@ModelAttribute("LockerVO") LockerVO lockerVO) throws Exception {

		// 0. Spring Security 사용자권한 처리
		
		// 1. 환불 테이블(TB_REFUND)에 삽입한다.
		lockerService.insertOrderRefund(lockerVO); //테스트중

		// 2. TB_OFF_ORDER_MGNT_NO에 삽입한다.
//		lockerVO.setConfirmid(loginVO.getUniqId());
//		lockerVO.setIsCancel("2");
//		lockerVO.setStatuscode("DLV230"); // 환불완료

		lockerService.insertOrderItemLocker(lockerVO);

		// 3. 오프라인 사물함 대여 결제정보 등록처리
		lockerService.updateOrderApprovalsRefund(lockerVO);

	    // 4. TB_OFF_BOX_RENT에서 업데이트 ( RENT_END, 승인:1, 키반납:Y, 사물함사용상태: 미사용)
		lockerService.updateLockerNumRentRefund(lockerVO);

		// 5. TB_OFF_BOX_NUM 사물함 상세정보의 BOX_FLAG = N, USERID = "" 초기화한다.
		lockerVO.setBoxFlag("N");

		lockerService.updateLockerNumReset(lockerVO);

		return "OK";
	}

}