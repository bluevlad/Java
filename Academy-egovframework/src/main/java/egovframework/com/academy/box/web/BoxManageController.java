package egovframework.com.academy.box.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.api.util.CommonUtil;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 시험문제정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2021.08.00  			rainend          최초 생성
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
		if (!commandMap.get("rentSeq").toString().isEmpty()) {
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

		// 사물함 대여 결제 이력들
		model.addAttribute("boxNumRentOrderList", boxManageService.selectBoxNumRentOrderList(BoxVO));

        model.addAttribute("BoxVO", boxManageService.selectBoxDetail(BoxVO));
		model.addAttribute("boxNumRentDetail", boxNumRentDetail);
		model.addAttribute("boxNumRentOrderDetail", boxNumRentOrderDetail);

		return "egovframework/com/academy/box/RentWrite";
	}

}
