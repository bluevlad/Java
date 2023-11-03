package egovframework.com.academy.box.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 사물함 관리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.10.00  			rainend          최초 생성
 * </pre>
 */

@Controller
public class BoxManageController {

	@Resource(name = "boxManageService")
	private BoxManageService boxManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험문제 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/box/List.do")
	public String List(@ModelAttribute("BoxVO") BoxVO BoxVO, ModelMap model) throws Exception {

		BoxVO.setPageUnit(propertyService.getInt("pageUnit"));
		BoxVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(BoxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(BoxVO.getPageUnit());
		paginationInfo.setPageSize(BoxVO.getPageSize());

		BoxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		BoxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		BoxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("resultList", boxManageService.selectBoxList(BoxVO));

		int totCnt = boxManageService.selectBoxListTotCnt(BoxVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/box/List";
	}

	/**
	 * 사물함 등록화면.
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Regist.do")
	public String Regist(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
		return "egovframework/com/academy/box/Regist";
	}

	/**
	 * 사물함 상세조회 조회한다.
	 * @param boxCd
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Detail.do")
	public String Detail(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
	
        model.addAttribute("boxCd", commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd"));
        BoxVO.setBoxCd((String) commandMap.get("boxCd"));

        model.addAttribute("BoxVO", boxManageService.selectBoxDetail(BoxVO));
		model.addAttribute("boxnumList", boxManageService.selectBoxNumList(BoxVO));

		return "egovframework/com/academy/box/Detail";
	}

	/**
	 * 사물함정보를 신규로 등록한다.
	 * @param BoxVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/box/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Insert.do")
	public String Insert(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		boxManageService.insertBox(BoxVO);

		int boxCount = CommonUtil.parseInt(commandMap.get("boxCount").toString());	// 사물함 총 갯수
		int startNum = CommonUtil.parseInt(commandMap.get("startNum").toString());	// 시작번호
		int endNum = CommonUtil.parseInt(commandMap.get("endNum").toString());	// 종료번호

		if (boxCount > 0){
			for( int i = startNum; i <= endNum; i++ ) {
				BoxVO.setBoxNum(i);
				boxManageService.insertBoxNum(BoxVO);
			}
		}

		return "redirect:/academy/box/List.do";
	}

	/**
	 * 사물함정보를 변경한다.
	 * @param BoxVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/academy/box/Insert"
	 * @throws Exception
	 */
	@RequestMapping(value="/academy/box/Update.do")
	public String Update(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		boxManageService.updateBox(BoxVO);

		return "redirect:/academy/box/List.do";
	}

	/**
	 * @Method Name : boxRentWrite
	 * @작성일 : 2023. 09
	 * @Method 설명 : 사물함 대여 신청 등록
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/box/RentWrite.do")
	public String boxRentWrite(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		// 사물함 BOX_CD로 상세정보를 가져온다.
        model.addAttribute("rentSeq", commandMap.get("rentSeq") == null ? "" : (String)commandMap.get("rentSeq"));
		BoxVO.setRentSeq(CommonUtil.parseInt(commandMap.get("rentSeq").toString()));

		BoxVO boxNumRentDetail = null;
		BoxVO boxNumRentOrderDetail = null;
		if (BoxVO.getRentSeq() > 0) {
			// 사물함 대여 신청 정보(현재)
			boxNumRentDetail = boxManageService.selectBoxNumRentDetail(BoxVO);
			
			// 사물함 대여 현재 결제 정보를 가져온다
			if (boxNumRentDetail != null){
				BoxVO.setOrderno(boxNumRentDetail.getOrderno());
				boxNumRentOrderDetail = boxManageService.selectBoxNumRentOrderDetail(BoxVO);
			}
		}

		if (boxNumRentOrderDetail != null){
			model.addAttribute("WMODE", "EDT");
		} else {
			model.addAttribute("WMODE", "INS");
		}

		BoxVO BoxDetail = boxManageService.selectBoxDetail(BoxVO);
		BoxDetail.setBoxNum(BoxVO.getBoxNum());
        model.addAttribute("BoxVO", BoxDetail);
		
		// 사물함 대여 결제 이력들
		model.addAttribute("boxNumRentOrderList", boxManageService.selectBoxNumRentOrderList(BoxVO));
		model.addAttribute("boxNumRentDetail", boxNumRentDetail);
		model.addAttribute("boxNumRentOrderDetail", boxNumRentOrderDetail);

		return "egovframework/com/academy/box/RentWrite";
	}

	/**
	 * @Method Name : updateBoxFlag
	 * @작성일 : 2023. 10
	 * @Method 설명 : 사물함 상태값 업데이트
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/academy/box/api/UpdateBoxFlag")
	@ResponseBody
	public String checkUser(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

        boxManageService.updateBoxFlag(BoxVO);

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
	@RequestMapping(value = "/academy/box/ExtendOrder.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String ExtendOrder(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		BoxVO.setRentMemo("사물함 데스크에서 연장");
		BoxVO.setPriceDiscountReason("사물함 데스크에서 연장");

		String Orderno = boxManageService.selectOrderno(BoxVO);
		
		BoxVO.setOrderno(Orderno);
		BoxVO.setItemno(BoxVO.getBoxCd()+"-"+BoxVO.getBoxNum());

		boxManageService.insertOrders(BoxVO);

		// 3. 오프라인 사물함 대여 결제정보 등록처리
		boxManageService.insertApprovals(BoxVO);

		boxManageService.insertOrderItem(BoxVO);

		// 4. 사물함 대여정보테이블(TB_OFF_BOX_RENT)에 추가
		// 기간을 연장한다. = 기존 종료일 + 2개월
		String rentEnd = BoxVO.getRentEnd();

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

		BoxVO.setRentStart(nRentStart);	// 연장시 시작일
		BoxVO.setRentEnd(nRentEnd);		// 연장시 종료일

		// 5. 사물함 세부정보(TB_OFF_BOX_NUM) 업데이트 boxNumUpdateProcess
		int rentSeq = boxManageService.getBoxRentSeq(BoxVO);
		BoxVO.setRentSeq(rentSeq);

		BoxVO.setKeyYn("N");  // 키 = 미반납
		BoxVO.setExtendYn("Y");  // 연장
		BoxVO.setRentType("OF");  // 오프라인
		
		boxManageService.insertBoxRent(BoxVO);

		boxManageService.updateBoxNum(BoxVO);

		return "redirect:/academy/box/List.do";
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
	@RequestMapping(value = "/academy/box/RentOrder.do")
	@Transactional( readOnly=false,  rollbackFor=Exception.class)
	public String RentOrder(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 사물함 관리 모드를 확인한다.
        model.addAttribute("WMODE", commandMap.get("WMODE") == null ? "" : (String)commandMap.get("WMODE"));

        if (BoxVO.getBoxFlag() == null) {
        	BoxVO.setBoxFlag("Y");
        }
        if (BoxVO.getKeyYn() == null) {
        	BoxVO.setKeyYn("R");
        }
        if (BoxVO.getRentType() == null) {
        	BoxVO.setRentType("OF");
        }
        if (BoxVO.getDepositYn() == null) {
        	BoxVO.setDepositYn("N");
        }
        if (BoxVO.getExtendYn() == null) {
        	BoxVO.setExtendYn("N");
        }
        if (BoxVO.getPayGubun() == null) {
        	BoxVO.setPayGubun("PAY140");
        }
        

		if ("INS".equals(model.get("WMODE"))) {
			// 사물함 신규 등록 처리
			// 1. 수강신청시에 사용할 새로운 주문번호를 생성한다.
			String Orderno = boxManageService.selectOrderno(BoxVO);
			BoxVO.setOrderno(Orderno);

			BoxVO.setItemno(BoxVO.getBoxCd()+"-"+BoxVO.getBoxNum());
			BoxVO.setMemo("사물함 데스크에서 신청");
			
			// 2. 오프라인 사물함 대여 주문 세부정보 등록 처리
			BoxVO.setIsCancel("0");
			BoxVO.setStatuscode("DLV105");
			boxManageService.insertOrderItem(BoxVO);

			// 3. 오프라인 사물함 대여 주문 등록 처리
			if ("OF".equals(BoxVO.getRentType())){
				BoxVO.setOrderType("1");
			} else{
				BoxVO.setOrderType("0");
			}

			boxManageService.insertOrders(BoxVO);

			// 3. 오프라인 사물함 대여 결제정보 등록처리
			boxManageService.insertApprovals(BoxVO);

			int rentSeq = boxManageService.getBoxRentSeq(BoxVO);
			BoxVO.setRentSeq(rentSeq);
			// 4. 사물함 대여정보테이블(acm_box_rent)에 추가
			boxManageService.insertBoxRent(BoxVO);

			// 5. 사물함 세부정보(acm_box_num) 업데이트 boxNumUpdate
			BoxVO.setBoxFlag("Y");  // 처음 등록할 때는 무조건 사용(Y)으로 저장한다.
			boxManageService.updateBoxNum(BoxVO);
		}

		if ("EDT".equals(model.get("WMODE"))) {
			// 사물함 대여 수정 처리

			// 1. 사물함 세부정보(acm_box_num) 업데이트 boxNumUpdate
			boxManageService.updateBoxNum(BoxVO);

			// 2. 사물함 대여정보(acm_box_rent) 업데이트 boxNumRentUpdate
			boxManageService.updateBoxNumRent(BoxVO);

			// 3. 사물함 주문 결제내역(acm_order_approvals) 업데이트 offPayUpdateProcess
			boxManageService.updateApprovals(BoxVO);
		}

		return "redirect:/academy/box/List.do";
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
	@RequestMapping(value = "/academy/box/Change.pop")
	public String boxChange(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		BoxVO.setPageUnit(propertyService.getInt("pageUnit"));
		BoxVO.setPageSize(propertyService.getInt("pageSize"));
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(BoxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(BoxVO.getPageUnit());
		paginationInfo.setPageSize(BoxVO.getPageSize());

		BoxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		BoxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 사물함 기존 정보
        model.addAttribute("usedBoxCd", commandMap.get("usedBoxCd") == null ? "" : (String)commandMap.get("usedBoxCd"));
        model.addAttribute("usedBoxNum", commandMap.get("usedBoxNum") == null ? "" : (String)commandMap.get("usedBoxNum"));
        model.addAttribute("usedRentSeq", commandMap.get("usedRentSeq") == null ? "" : (String)commandMap.get("usedRentSeq"));
        model.addAttribute("boxCd", commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd"));

		model.addAttribute("boxlist", boxManageService.selectBoxList(BoxVO));
		model.addAttribute("boxnumList", boxManageService.selectBoxNumList(BoxVO));

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
	@RequestMapping("/academy/box/ChangePop")
	public ModelAndView changePop(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap) throws Exception {

    	ModelAndView ret = new ModelAndView("jsonView");

		// 사물함 기존 정보
        String usedBoxCd = commandMap.get("usedBoxCd") == null ? "" : (String)commandMap.get("usedBoxCd");
        String usedBoxNum = commandMap.get("usedBoxNum") == null ? "" : (String)commandMap.get("usedBoxNum");

		// 사물함 신규 번호
        String boxCd = commandMap.get("boxCd") == null ? "" : (String)commandMap.get("boxCd");
        String boxNum = commandMap.get("boxNum") == null ? "" : (String)commandMap.get("boxNum");
        String rentSeq = commandMap.get("usedRentSeq") == null ? "" : (String)commandMap.get("usedRentSeq");

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//아이디 설정
		BoxVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		BoxVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		BoxVO.setBoxCd(usedBoxCd);
		BoxVO.setBoxNum(CommonUtil.parseInt(usedBoxNum));
		BoxVO.setRentSeq(CommonUtil.parseInt(rentSeq));

		BoxVO boxNumChange = boxManageService.selectBoxNumRentDetail(BoxVO);
		BoxVO.setUserId(boxNumChange.getUserId());
		BoxVO.setBoxFlag(boxNumChange.getBoxFlag());
		BoxVO.setRentSeq(boxNumChange.getRentSeq());
		BoxVO.setRentMemo(boxNumChange.getRentMemo());
		
		// 1. TB_OFF_BOX_NUM 테이블을 업데이트한다. (신규 선택한 곳에 기존 자료를 업데이트한다)
		BoxVO.setBoxCd(boxCd);
		BoxVO.setBoxNum(CommonUtil.parseInt(boxNum));
        boxManageService.updateboxNumChange(BoxVO);

		// 2. TB_OFF_BOX_NUM 테이블을 업데이트한다. (기존 자료 공간을 초기화 업데이트한다)
		BoxVO.setUsedBoxCd(usedBoxCd);
		BoxVO.setUsedBoxNum(CommonUtil.parseInt(usedBoxNum));
		boxManageService.updateboxNumReset(BoxVO);

	    // 3. TB_OFF_BOX_RENT 테이블을 업데이트한다. (기존 자료에 신규 사물함번호를 저장한다)
		boxManageService.updateBoxRentChange(BoxVO);

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

	@RequestMapping(value = "/academy/box/OrderView.do")
	public String boxOrderView(@ModelAttribute("BoxVO") BoxVO BoxVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        String orderno = commandMap.get("orderno") == null ? "" : (String)commandMap.get("orderno");
        String statuscode = commandMap.get("statuscode") == null ? "" : (String)commandMap.get("statuscode");
		
		BoxVO.setOrderno(orderno);
		BoxVO.setStatuscode(statuscode);
       
		
//		params.put("CALLPOSITION", CommonUtil.isNull(request.getParameter("CALLPOSITION"), "ORDERLIST"));

		if (!orderno.isEmpty()) {
			// 사물함 대여 신청 정보(현재)
			BoxVO boxOrderDetail  = boxManageService.selectBoxOrderDetail(BoxVO);

			model.addAttribute("boxOrderDetail", boxOrderDetail);

			return "egovframework/com/academy/box/OrderView";

		} else {
			return "redirect:/academy/box/RentWrite.do";
		}
	}

	
}
