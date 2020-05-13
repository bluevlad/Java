package egovframework.com.academy.exam.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamStatService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.academy.schedule.web.ScheduleController;
import egovframework.com.api.CORSFilter;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시험(과목)정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.04.00  			rainend          최초 생성
 * </pre>
 */

@Controller
public class ExamApiController extends CORSFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	@Resource(name = "examStatService")
	private ExamStatService examStatService;

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
	@ResponseBody
	@RequestMapping(value = "/api/exam/list", method= RequestMethod.GET)
	public ModelAndView ExamList(@ModelAttribute("ExamVO") ExamVO ExamVO) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		ExamVO.setPageUnit(propertyService.getInt("pageUnit"));
		ExamVO.setPageSize(propertyService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ExamVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ExamVO.getPageUnit());
		paginationInfo.setPageSize(ExamVO.getPageSize());

		ExamVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ExamVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ExamVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> examList = examManageService.selectExamList(ExamVO);
		modelAndView.addObject(examList);
		
		return modelAndView;
	}

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
    @ResponseBody
  	@Transactional(readOnly=false,rollbackFor=Exception.class)
	@RequestMapping(value = "/api/exam/add")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		String sKey = "";
       	for(Object key:commandMap.keySet()){
       		sKey = key.toString();
       		LOGGER.debug("sKey : " +sKey);
       		if(sKey.equals("examNm")) {
       			ExamVO.setExamNm(sKey);
       		}
       		if(sKey.equals("isUse")) {
       			ExamVO.setIsUse(sKey);
       		}
       	}
		examManageService.insertExam(ExamVO);
		int uniqId = ExamVO.getExamCd();
		return uniqId+"";
	}

	/**
	 * 시험 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/api/exam/stat/sbj", method= RequestMethod.GET)
	public ModelAndView ExamStatSbjList(@ModelAttribute("ExamVO") ExamVO ExamVO) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		List<?> examStatSbjList = examStatService.selectExamStatSbjList(ExamVO);
		modelAndView.addObject(examStatSbjList);
		
		return modelAndView;
	}

}
