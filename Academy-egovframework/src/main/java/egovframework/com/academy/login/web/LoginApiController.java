package egovframework.com.academy.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.api.CORSFilter;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uat.uia.service.EgovLoginService;

/*
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
*/

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.8.26	정진오          IncludedInfo annotation 추가
 *  2011.09.07  서준식          스프링 시큐리티 로그인 및 SSO 인증 로직을 필터로 분리
 *  2011.09.25  서준식          사용자 관리 컴포넌트 미포함에 대한 점검 로직 추가
 *  2011.09.27  서준식          인증서 로그인시 스프링 시큐리티 사용에 대한 체크 로직 추가
 *  2011.10.27  서준식          아이디 찾기 기능에서 사용자 리름 공백 제거 기능 추가
 *  2017.07.21  장동한          로그인인증제한 작업
 *  2018.10.26  신용호          로그인 화면에 message 파라미터 전달 수정
 *  2019.10.01	EFW Center	  로그인 인증세션 추가
 *  2020.03.00	rainend	  학원관리 로그인 적용
 *  </pre>
 */
@Controller
public class LoginApiController extends CORSFilter {

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

	/**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@RequestMapping(value = "/api/login/Login")
    @ResponseBody
	public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {

		// 2. 로그인 처리
		LoginVO resultVO = loginService.actionLogin(loginVO);
		
		// 3-1. 로그인 정보를 세션에 저장
		request.getSession().setAttribute("loginVO", resultVO);
		// 2019.10.01 로그인 인증세션 추가
		request.getSession().setAttribute("accessUser", resultVO.getUserSe().concat(resultVO.getId()));

		return "0";
	}

	/**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/api/login/Logout")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

		request.getSession().setAttribute("loginVO", null);

		return "redirect:/Content.do";
	}

}
