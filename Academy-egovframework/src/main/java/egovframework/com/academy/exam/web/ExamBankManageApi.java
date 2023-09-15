package egovframework.com.academy.exam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.academy.exam.service.ExamBankManageService;
import egovframework.com.academy.exam.service.ExamVO;
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
public class ExamBankManageApi {

	@Resource(name = "examBankManageService")
	private ExamBankManageService examBankManageService;

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
	@RequestMapping(value = "/api/exam/ExamBankItemList")
	public ModelAndView ExamList(@ModelAttribute("ExamVO") ExamVO ExamVO) throws Exception {

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

		List<?> examBankItemList = examBankManageService.selectExamBankItemlList(ExamVO);
		if (!examBankItemList.isEmpty()) {
			modelAndView.addObject(examBankItemList);
		} else {
			modelAndView.addObject("resultMsg", "등록된 문제정보가 없습니다.");
		}
		
		return modelAndView;
	}

	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/exam/ExamBankItemDetail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ExamDetail(@PathVariable int id, @ModelAttribute("ExamVO") ExamVO ExamVO) throws Exception {

		modelAndView.setViewName("jsonView");

		ExamVO.setQueId(id);
		ExamVO examDetail = examBankManageService.selectExamBankItemDetail(ExamVO);
		
		modelAndView.addObject(examDetail);

		return modelAndView;
	}

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/exam/ExamBankItemInsert")
	public ModelAndView insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {

		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("examYear")) {
				ExamVO.setExamYear(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("examRound")) {
				ExamVO.setExamRound(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("sbjCd")) {
				ExamVO.setSbjCd(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queCount")) {
				ExamVO.setQueCount(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queId")) {
				ExamVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("queOption")) {
				ExamVO.setQueOption(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("regId")) {
				ExamVO.setRegId(request.getParameter(sKey)); 
			}
			if(sKey.equals("updId")) {
				ExamVO.setUpdId(request.getParameter(sKey)); 
			}
		}
		
		examBankManageService.insertExamBankItem(ExamVO);

		String regFlag = "ok";
		modelAndView.addObject("resultMsg", regFlag);
		
		return modelAndView;

	}

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/api/exam/ExamBankItemUpdate")
	public ModelAndView updateExam(@ModelAttribute("ExamVO") ExamVO ExamVO, @RequestParam Map<?, ?> reqMap, HttpServletRequest request) throws Exception {
		
		modelAndView.setViewName("jsonView");

		String sKey = ""; 
		for(Object key:reqMap.keySet()){ 
			sKey = key.toString();
			if(sKey.equals("queId")) {
				ExamVO.setQueId(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("examYear")) {
				ExamVO.setExamYear(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("examRound")) {
				ExamVO.setExamRound(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("sbjCd")) {
				ExamVO.setSbjCd(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("qCount")) {
				ExamVO.setQueCount(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("qOption")) {
				ExamVO.setQueOption(Integer.valueOf(request.getParameter(sKey))); 
			}
			if(sKey.equals("updId")) {
				ExamVO.setUpdId(request.getParameter(sKey)); 
			}
		}
		
			examBankManageService.updateExamBankItem(ExamVO);

			modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.update"));

			return modelAndView;
	}

	@RequestMapping(value = "/academy/exam/ExamBankExcel.do")
	public ModelAndView selectExmaBankExcelList(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {
	 
		List<?> examList = new ArrayList<ExamVO>();
		examList = examBankManageService.selectExamBankItemlList(ExamVO);
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("examList", examList);
	 
		return new ModelAndView("ExamBankExcelView", "exmaBankMap", map);
	}	
	
}
