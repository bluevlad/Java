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

import egovframework.com.academy.survey.service.SurveySetManageService;
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
public class SurveySetManageApi {

	@Resource(name = "surveySetManageService")
	private SurveySetManageService surveySetManageService;

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
	@RequestMapping(value = "/api/survey/SurveySetList")
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

		List<?> surveySetmList = surveySetManageService.selectSurveySetList(SurveyVO);
		if (!surveySetmList.isEmpty()) {
			modelAndView.addObject(surveySetmList);
		} else {
			modelAndView.addObject("resultMsg", "등록된 설문세트정보가 없습니다.");
		}
		
		return modelAndView;
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveySetDetail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView SurveyDetail(@PathVariable int id, @ModelAttribute("SurveyVO") SurveyVO SurveyVO) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		SurveyVO.setSetId(id);
		SurveyVO surveySetDetail = surveySetManageService.selectSurveySetDetail(SurveyVO);
		
		modelAndView.addObject(surveySetDetail);

		return modelAndView;
	}

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveySetInsert")
	public ModelAndView insertSurveySet(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("setTitle")) {
				SurveyVO.setSetTitle(request.getParameter(sKey)); 
			}
			if(sKey.equals("setDesc")) {
				SurveyVO.setSetDesc(request.getParameter(sKey)); 
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
		
		surveySetManageService.insertSurveySet(SurveyVO);

		modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		
		return modelAndView;

	}

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveySetUpdate")
	public ModelAndView updateSurveySet(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {
		
		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("setId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("setTitle")) {
				SurveyVO.setQueTitle(request.getParameter(sKey)); 
			}
			if(sKey.equals("setDesc")) {
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
		
			surveySetManageService.updateSurveySet(SurveyVO);

			modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.update"));

			return modelAndView;
	}

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveySetItemInsert")
	public ModelAndView insertSurveySetItem(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("setId")) {
				SurveyVO.setSetId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queSeq")) {
				SurveyVO.setQueSeq(Integer.valueOf(request.getParameter(sKey))); 
			}
		}
		
		surveySetManageService.insertSurveySetItem(SurveyVO);

		modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.insert"));
		
		return modelAndView;

	}

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param SurveyVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/survey/SurveySetItemDelete")
	public ModelAndView deleteSurveySetItem(@ModelAttribute("SurveyVO") SurveyVO SurveyVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {
		
		modelAndView.clear();
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("setId")) {
				SurveyVO.setSetId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queId")) {
				SurveyVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queSeq")) {
				SurveyVO.setQueSeq(Integer.valueOf(request.getParameter(sKey))); 
			}
		}
		
			surveySetManageService.deleteSurveySetItem(SurveyVO);

			modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.delete"));

			return modelAndView;
	}
	
}
