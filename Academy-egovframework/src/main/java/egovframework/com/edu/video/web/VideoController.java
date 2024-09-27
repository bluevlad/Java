package egovframework.com.edu.video.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.box.service.BoxVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.edu.video.service.VideoService;
import egovframework.com.edu.video.service.VideoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 사물함 관리를  비지니스 클래스로 전달하고 처리된결과를  해당 웹 화면으로 전달하는 Controller를 정의한다
 * @author rainend
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   	수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2024.05		  			rainend          최초 생성
 * </pre>
 */

@Controller
public class VideoController {

	@Resource(name = "videoService")
	private VideoService videoService;

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
	@RequestMapping(value = "/edu/video/Main.do")
	public String List(@ModelAttribute("VideoVO") VideoVO VideoVO, ModelMap model) throws Exception {

		return "egovframework/com/edu/video/Main";
	}

	
}
