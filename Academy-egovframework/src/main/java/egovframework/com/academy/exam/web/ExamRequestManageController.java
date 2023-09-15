package egovframework.com.academy.exam.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.exam.service.ExamManageService;
import egovframework.com.academy.exam.service.ExamPassManageService;
import egovframework.com.academy.exam.service.ExamRequestManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qri.web.EgovQustnrRespondInfoController;
import egovframework.com.utl.fcc.service.EgovStringUtil;
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
public class ExamRequestManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrRespondInfoController.class);

	@Resource(name = "examManageService")
	private ExamManageService examManageService;

	@Resource(name = "examPassManageService")
	private ExamPassManageService examPassManageService;

	@Resource(name = "examRequestManageService")
	private ExamRequestManageService examRequestManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	/**
	 * 시험 신청리스트
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/academy/exam/request/List.do")
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

		LOGGER.debug("examRequestList = " + examRequestManageService.offererList(ExamVO));
		model.addAttribute("examRequestList", examRequestManageService.offererList(ExamVO));

		int totCnt = examRequestManageService.offererListCount(ExamVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		LOGGER.debug("totCnt = " + examRequestManageService.offererListCount(ExamVO));
		
		return "egovframework/com/academy/exam/request/List";
	}

    /**
     * @Method Name : Detail
     * @Method 설명 : 신청자관리 주문상세
     * @param model
     */
    @RequestMapping(value="/academy/exam/request/Detail.do")
    public String offererView(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

        model.addAttribute("ExamVO", examRequestManageService.offererView(ExamVO));

        return "egovframework/com/academy/exam/request/Detail";
    }

    /**
     * @Method Name : Regist
     * @Method 설명 : 신청자관리 등록
     * @param model
     */
    @RequestMapping(value="/academy/exam/request/Regist.do")
    public String offererRegist(@ModelAttribute("ExamVO") ExamVO ExamVO, ModelMap model) throws Exception {

        return "egovframework/com/academy/exam/request/Regist";
    }

    /**
     * @Method Name : insert
     * @Method 설명 : 신청자 관리 등록 프로세스
     * @param model
     */
    @RequestMapping(value="/academy/exam/request/Insert.do")
    public String offererInsert(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/request/Regist";
		} else {
			// 로그인 객체 선언
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			// 아이디 설정
			ExamVO.setRegId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			ExamVO.setUpdId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			ExamVO.setReqId("1111");
			ExamVO.setExamFlag("N");
			
			examRequestManageService.offererInsert(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/academy/exam/request/List.do";
		}
    }

    /**
     * @Method Name : Update
     * @Method 설명 : 신청자관리 수정 프로세스
     * @param model
     */
    @RequestMapping(value="/academy/exam/request/Update.do")
    public String offererUpdate(@ModelAttribute("ExamVO") ExamVO ExamVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			return "egovframework/com/academy/exam/request/Detail";
		} else {
			examRequestManageService.offererUpdate(ExamVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/academy/exam/request/List.do";
		}

    }

}