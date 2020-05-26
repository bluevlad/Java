package egovframework.com.academy.box.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.box.service.BoxManageService;
import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.academy.schedule.web.ScheduleController;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 사물함 정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.05.21  			rainend          최초 생성
 * </pre>
 */

@Controller
public class BoxManageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@Resource(name = "boxManageService")
	private BoxManageService boxManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/box/mst/List.do")
	public String List(@ModelAttribute("searchVO") BoxVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("boxList", boxManageService.selectBoxList(searchVO));

		int totCnt = boxManageService.selectBoxListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/box/BoxMstList";
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/mst/Detail.do")
	public String Detail(@ModelAttribute("BoxVO") BoxVO BoxVO, ModelMap model) throws Exception {

		model.addAttribute("BoxVO", boxManageService.selectBoxDetail(BoxVO));
		model.addAttribute("boxList", boxManageService.selectBoxInfoList(BoxVO));
		
		return "egovframework/com/academy/box/BoxMstDetail";
	}

	/**
	 * 시험등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/mst/Regist.do")
	public String Regist(@ModelAttribute("BoxVO") BoxVO BoxVO, ModelMap model) throws Exception {

		model.addAttribute("BoxVO", BoxVO);
		return "egovframework/com/academy/box/BoxMstRegist";
	}

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/mst/insert.do")
	public String Insert(@ModelAttribute("BoxVO") BoxVO BoxVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/box/BoxMstRegist";
		} else {
			boxManageService.insertBox(BoxVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/box/mst/List.do";
		}
	}

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/mst/update.do")
	public String Update(@ModelAttribute("BoxVO") BoxVO BoxVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/box/BoxMstDetail";
		} else {
			boxManageService.updateBox(BoxVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/box/mst/List.do";
		}
	}

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/mst/delete.do")
	public String Delete(@ModelAttribute("BoxVO") BoxVO BoxVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/box/BoxMstDetail";
		} else {
			boxManageService.deleteBox(BoxVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
			return "forward:/box/mst/List.do";
		}
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/box/rent/Detail.do")
	public String Rent(@ModelAttribute("BoxVO") BoxVO BoxVO, ModelMap model) throws Exception {

		model.addAttribute("BoxVO", boxManageService.selectBoxRentInfo(BoxVO));
		model.addAttribute("RentList", boxManageService.selectBoxRentList(BoxVO));
		
		return "egovframework/com/academy/box/BoxRentDetail";
	}

}
