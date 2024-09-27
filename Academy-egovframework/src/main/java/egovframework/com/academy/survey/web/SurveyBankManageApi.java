package egovframework.com.academy.survey.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.survey.service.SurveyBankManageService;
import egovframework.com.academy.survey.service.SurveyVO;
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
public class SurveyBankManageApi {

	@Resource(name = "surveyBankManageService")
	private SurveyBankManageService surveyBankManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	ModelAndView modelAndView = new ModelAndView();

	/**
	 * 시험문제 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/api/survey/SurveyBankItemList")
	public ModelAndView SurveyList(@ModelAttribute("SurveyVO") SurveyVO SurveyVO) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		SurveyVO.setPageUnit(propertyService.getInt("pageUnit"));
		SurveyVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(SurveyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(SurveyVO.getPageUnit());
		paginationInfo.setPageSize(SurveyVO.getPageSize());

		SurveyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		SurveyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		SurveyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> surveyBankItemList = surveyBankManageService.selectSurveyBankList(SurveyVO);
		if (!surveyBankItemList.isEmpty()) {
			modelAndView.addObject(surveyBankItemList);
		} else {
			modelAndView.addObject("resultMsg", "등록된 설문문항정보가 없습니다.");
		}
		
		return modelAndView;
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveyBankItemDetail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView SurveyDetail(@PathVariable int id, @ModelAttribute("SurveyVO") SurveyVO SurveyVO) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		SurveyVO.setQueId(id);
		SurveyVO surveyDetail = surveyBankManageService.selectSurveyBankDetail(SurveyVO);
		
		modelAndView.addObject(surveyDetail);

		return modelAndView;
	}

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveyBankItemInsert")
	public ModelAndView insertSurvey(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("queTitle")) {
				SurveyVO.setQueTitle(request.getParameter(sKey)); 
			}
			if(sKey.equals("queOwner")) {
				SurveyVO.setQueOwner(request.getParameter(sKey)); 
			}
			if(sKey.equals("queCount")) {
				SurveyVO.setQueCount(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queType")) {
				SurveyVO.setQueType(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw1")) {
				SurveyVO.setQueViw1(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw2")) {
				SurveyVO.setQueViw2(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw3")) {
				SurveyVO.setQueViw3(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw4")) {
				SurveyVO.setQueViw4(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw5")) {
				SurveyVO.setQueViw5(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw6")) {
				SurveyVO.setQueViw6(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw7")) {
				SurveyVO.setQueViw7(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw8")) {
				SurveyVO.setQueViw8(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw9")) {
				SurveyVO.setQueViw9(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw10")) {
				SurveyVO.setQueViw10(request.getParameter(sKey)); 
			}
			if(sKey.equals("queDesc")) {
				SurveyVO.setQueDesc(request.getParameter(sKey)); 
			}
			if(sKey.equals("isUse")) {
				SurveyVO.setIsUse(request.getParameter(sKey)); 
			}
			if(sKey.equals("regId")) {
				SurveyVO.setRegId(request.getParameter(sKey)); 
			}
			if(sKey.equals("updId")) {
				SurveyVO.setUpdId(request.getParameter(sKey)); 
			}
		}
		
		surveyBankManageService.insertSurveyBank(SurveyVO);

		modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		
		return modelAndView;

	}

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveyBankItemUpdate")
	public ModelAndView updateSurvey(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {
		
		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("queId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queTitle")) {
				SurveyVO.setQueTitle(request.getParameter(sKey)); 
			}
			if(sKey.equals("queOwner")) {
				SurveyVO.setQueOwner(request.getParameter(sKey)); 
			}
			if(sKey.equals("queCount")) {
				SurveyVO.setQueCount(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queType")) {
				SurveyVO.setQueType(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw1")) {
				SurveyVO.setQueViw1(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw2")) {
				SurveyVO.setQueViw2(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw3")) {
				SurveyVO.setQueViw3(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw4")) {
				SurveyVO.setQueViw4(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw5")) {
				SurveyVO.setQueViw5(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw6")) {
				SurveyVO.setQueViw6(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw7")) {
				SurveyVO.setQueViw7(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw8")) {
				SurveyVO.setQueViw8(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw9")) {
				SurveyVO.setQueViw9(request.getParameter(sKey)); 
			}
			if(sKey.equals("queViw10")) {
				SurveyVO.setQueViw10(request.getParameter(sKey)); 
			}
			if(sKey.equals("queDesc")) {
				SurveyVO.setQueDesc(request.getParameter(sKey)); 
			}
			if(sKey.equals("isUse")) {
				SurveyVO.setIsUse(request.getParameter(sKey)); 
			}
			if(sKey.equals("regId")) {
				SurveyVO.setRegId(request.getParameter(sKey)); 
			}
			if(sKey.equals("updId")) {
				SurveyVO.setUpdId(request.getParameter(sKey)); 
			}
		}
		
			surveyBankManageService.updateSurveyBank(SurveyVO);

			modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.update"));

			return modelAndView;
	}
	
}
