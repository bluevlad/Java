package egovframework.com.academy.index;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.academy.menu.service.EgovMenuManageService;
import egovframework.com.academy.menu.service.MenuManageVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class IndexController implements ApplicationContextAware, InitializingBean {

//	private ApplicationContext applicationContext;

    /** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;


	public void afterPropertiesSet() throws Exception {}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
	}

	@RequestMapping("/index.do")
	public String index(ModelMap model) {
		return "egovframework/com/academy/main/Main";
	}

	@RequestMapping("/Top.do")
	public String top() {
		return "egovframework/com/academy/main/Top";
	}

	@RequestMapping("/Bottom.do")
	public String bottom() {
		return "egovframework/com/academy/main/Bottom";
	}

	@RequestMapping("/Content.do")
	public String setContent(ModelMap model) {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);
		return "egovframework/com/academy/main/Content";
	}

	@RequestMapping("/Left.do")
	public String setLeftMenu(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {

    	List<?> list_menulist = menuManageService.selectMainMenuLeft(menuManageVO);
		model.addAttribute("resultList", list_menulist);
		return "egovframework/com/academy/main/Left";
	}
}
