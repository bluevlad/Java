package egovframework.com.academy.exam.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamPassManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.uss.olp.qri.web.EgovQustnrRespondInfoController;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 시험 답안지 정보 처리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
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
public class ExamPassManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrRespondInfoController.class);

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	@Resource(name = "examPassManageService")
	private ExamPassManageService examPassManageService;

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
	@RequestMapping(value = "/academy/exam/pass/List.do")
	public String ExamList(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

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

		model.addAttribute("examList", examPassManageService.selectExamPassList(ExamVO));

		int totCnt = examPassManageService.selectExamPassListTotCnt(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "egovframework/com/academy/exam/ExamPassList";
	}

	/**
	 * 시험 답안지 정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/Detail.do")
	public String ExamDetail(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamVO.setExamNm(examManageService.selectExamDetail(ExamVO).getExamNm());
		ExamVO.setSbjNm(examManageService.selectSubjectDetail(ExamVO).getSbjNm());
		model.addAttribute("passList", examPassManageService.selectExamPassDetail(ExamVO));
		
		return "egovframework/com/academy/exam/ExamPassDetail";
	}

	/**
	 * 시험 답안지 정보를 조회한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/DetailAll.do")
	public String ExamDetailAll(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {
		
		ExamVO.setExamNm(examManageService.selectExamDetail(ExamVO).getExamNm());
		ExamVO.setSbjNm(examManageService.selectSubjectDetail(ExamVO).getSbjNm());
		model.addAttribute("passList", examPassManageService.selectExamPassDetail(ExamVO));
		
		return "egovframework/com/academy/exam/ExamPassDetailAll";
	}

	/**
	 * 시험 답안지등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/Regist.do")
	public String ExamRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamVO.setFirstIndex(0);
		ExamVO.setRecordCountPerPage(100);
		model.addAttribute("examList", examManageService.selectExamList(ExamVO));
		model.addAttribute("subjectList", examManageService.selectSubjectList(ExamVO));
		
		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/ExamPassRegist";
	}

	/**
	 * 시험 답안지등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/RegistAll.do")
	public String ExamRegistAll(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

		ExamVO.setFirstIndex(0);
		ExamVO.setRecordCountPerPage(100);
		model.addAttribute("examList", examManageService.selectExamList(ExamVO));
		model.addAttribute("subjectList", examManageService.selectSubjectList(ExamVO));
		
		model.addAttribute("ExamVO", ExamVO);
		return "egovframework/com/academy/exam/ExamPassRegistAll";
	}

	/**
	 * 시험 답안지정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/insert.do")
	public String insertExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, 
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamPassRegist";
		} else {
			String sKey = "";
			int len = 0;
	       	for(Object key:commandMap.keySet()){
	       		sKey = key.toString();
	       		len = sKey.length();
	       		if (len > 6 && sKey.substring(0, 6).equals("itemNo")) {
	       			//ExamVO.setItemNo(CommonUtil.parseInt(sKey.substring(6, len)));
	       			ExamVO.setPassAns(request.getParameter(sKey));
	    			examPassManageService.insertExamPass(ExamVO);
	       		}
	       	}
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/pass/List.do";
		}
	}

	/**
	 * 시험 답안지정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/insertAll.do")
	public String insertExamAll(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, 
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamPassRegistAll";
		} else {
			String sKey ="";
	       	for(Object key:commandMap.keySet()){
	       		sKey = key.toString();
	       		if(sKey.equals("AnsArr")){
	       			String ans = request.getParameter(sKey);
	       			int j = 1;
	    	       	for(int i=0; i< ans.length(); i++) {
		       			//ExamVO.setItemNo(i+1);
		       			ExamVO.setPassAns(ans.substring(i, j));
		    			examPassManageService.insertExamPass(ExamVO);
		    			j++;
	    	       	}
	       		}
	       	}
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/pass/List.do";
		}
	}

	/**
	 * 시험 답안지정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/update.do")
	public String updateExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, 
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamPassRegist";
		} else {
			examPassManageService.deleteExamPass(ExamVO);
			String sKey = "";
			int len = 0;
	       	for(Object key:commandMap.keySet()){
	       		sKey = key.toString();
	       		len = sKey.length();
	       		if (len > 6 && sKey.substring(0, 6).equals("itemNo")) {
	       			//ExamVO.setItemNo(CommonUtil.parseInt(sKey.substring(6, len)));
	       			ExamVO.setPassAns(request.getParameter(sKey));
	    			examPassManageService.insertExamPass(ExamVO);
	       		}
	       	}
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/pass/List.do";
		}
	}

	/**
	 * 시험 답안지정보를 신규로 등록한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/updateAll.do")
	public String updateExamAll(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, 
			@RequestParam Map<?, ?> commandMap, HttpServletRequest request, 	ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamPassRegistAll";
		} else {
			examPassManageService.deleteExamPass(ExamVO);
			String sKey ="";
			int len = 0;
	       	for(Object key:commandMap.keySet()){
	       		sKey = key.toString();
	       		len = sKey.length();
	       		if (len > 6 && sKey.substring(0, 6).equals("itemNo")) {
	       			//ExamVO.setItemNo(CommonUtil.parseInt(sKey.substring(6, len)));
	       			ExamVO.setPassAns(request.getParameter(sKey));
	    			examPassManageService.insertExamPass(ExamVO);
	       		}
	       	}
			
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/exam/pass/List.do";
		}
	}

	/**
	 * 기 등록된 시험 답안지정보를 삭제한다.
	 * @param ExamVO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/academy/exam/pass/delete.do")
	public String deleteExam(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/ExamPassDetail";
		} else {
			examPassManageService.deleteExamPass(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
			return "forward:/exam/pass/List.do";
		}
	}

}
