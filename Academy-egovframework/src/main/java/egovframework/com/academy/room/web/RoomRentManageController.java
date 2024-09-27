package egovframework.com.academy.room.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.room.service.RoomManageService;
import egovframework.com.academy.room.service.RoomRentManageService;
import egovframework.com.academy.room.service.RoomVO;
import egovframework.com.api.CORSFilter;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 독서실 정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.01.21  			rainend          최초 생성
 * </pre>
 */

@Controller
public class RoomRentManageController extends CORSFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomRentManageController.class);

	@Resource(name = "roomManageService")
	private RoomManageService roomManageService;

	@Resource(name = "roomRentManageService")
	private RoomRentManageService roomRentManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	ModelAndView modelAndView = new ModelAndView();

	/**
	 * 독서실 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/api/room/rent/List", method = RequestMethod.GET)
	public ModelAndView List(@ModelAttribute("RoomVO") RoomVO RoomVO) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		RoomVO.setPageUnit(propertyService.getInt("pageUnit"));
		RoomVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(RoomVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(RoomVO.getPageUnit());
		paginationInfo.setPageSize(RoomVO.getPageSize());

		RoomVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		RoomVO.setLastIndex(paginationInfo.getLastRecordIndex());
		RoomVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> roomRentList = roomRentManageService.selectRoomRentList(RoomVO);
		if (!roomRentList.isEmpty()) {
			modelAndView.addObject(roomRentList);
		} else {
			modelAndView.addObject("resultMsg", "대여된 독서실이 없습니다.");
		}
		
		return modelAndView;
	}

	/**
	 * 독서실 상세정보를 조회한다.
	 * @param RoomVO
	 * @return String - 리턴 Url
	 */
	@GET
	@RequestMapping(value = "/api/room/rent/Detail")
	public ModelAndView Detail(@ModelAttribute("RoomVO") RoomVO RoomVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("roomCd")) {
				RoomVO.setRoomCd(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("roomNum")) {
				RoomVO.setRoomNum(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("rentSeq")) {
				RoomVO.setRentSeq(Integer.valueOf(request.getParameter(sKey))); 
			}
		}
		
		RoomVO = roomRentManageService.selectRoomRentDetail(RoomVO);

		modelAndView.addObject(RoomVO);
		
		return modelAndView;
	}

	/**
	 * 독서실 정보를 신규로 등록한다.
	 * @param RoomVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/room/rent/insert")
	public ModelAndView Insert(@ModelAttribute("RoomVO") RoomVO RoomVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("roomCd")) {
				RoomVO.setRoomNm(request.getParameter(sKey)); 
			}
			if(sKey.equals("roomNum")) {
				RoomVO.setRoomCount(Integer.valueOf(request.getParameter(sKey))); 
			}
//			if(sKey.equals("rentSeq")) {
//				RoomVO.setRoomPrice(Integer.valueOf(request.getParameter(sKey))); 
//			}
			if(sKey.equals("userId")) {
				RoomVO.setUserId(request.getParameter(sKey)); 
			}
			if(sKey.equals("rentStart")) {
				RoomVO.setRentStart(request.getParameter(sKey)); 
			}
			if(sKey.equals("rentEnd")) {
				RoomVO.setRentEnd(request.getParameter(sKey)); 
			}
			if(sKey.equals("extendYn")) {
				RoomVO.setExtendYn(request.getParameter(sKey)); 
			}
			if(sKey.equals("rentType")) {
				RoomVO.setRentType(request.getParameter(sKey)); 
			}
			if(sKey.equals("regId")) {
				RoomVO.setRegId(request.getParameter(sKey)); 
			}
			if(sKey.equals("updId")) {
				RoomVO.setUpdId(request.getParameter(sKey)); 
			}
		}

		roomRentManageService.insertRoomRent(RoomVO);
		modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		
		return modelAndView;
	}

	/**
	 * 기 등록된 독서실 정보를 수정한다.
	 * @param RoomVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/room/rent/update")
	public ModelAndView Update(@ModelAttribute("RoomVO") RoomVO RoomVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:commandMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("roomCd")) {
				RoomVO.setRegId(request.getParameter(sKey)); 
			}
			if(sKey.equals("roomNm")) {
				RoomVO.setRoomNm(request.getParameter(sKey)); 
			}
			if(sKey.equals("roomCount")) {
				RoomVO.setRoomCount(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("roomPrice")) {
				RoomVO.setRoomPrice(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("updId")) {
				RoomVO.setUpdId(request.getParameter(sKey)); 
			}
		}
			roomManageService.updateRoom(RoomVO);

			modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.update"));
			
			return modelAndView;
	}

	/**
	 * 기 등록된 독서실 정보를 삭제한다.
	 * @param RoomVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/room/rent/delete/{id}")
	public ModelAndView Delete(@PathVariable int id, @ModelAttribute("RoomVO") RoomVO RoomVO) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		RoomVO.setRoomCd(id);

		roomManageService.deleteRoom(RoomVO);
		
		modelAndView.addObject("message", egovMessageSource.getMessage("success.common.delete"));
		
		return modelAndView;
	}

}
