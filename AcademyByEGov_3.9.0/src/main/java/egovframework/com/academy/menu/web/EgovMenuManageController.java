package egovframework.com.academy.menu.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.academy.menu.service.EgovMenuManageService;
import egovframework.com.academy.menu.service.MenuManageVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 메뉴목록 관리및 메뉴생성, 사이트맵 생성을 처리하는 비즈니스 구현 클래스
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *	 2011.07.01	 서준식	   	메뉴정보 삭제시 참조되고 있는 하위 메뉴가 있는지 체크하는 로직 추가
 *	 2011.07.27	 서준식	   	deleteMenuManageList() 메서드에서 메뉴 멀티 삭제 버그 수정
 *	 2011.08.26	 정진오		IncludedInfo annotation 추가
 *	 2011.10.07	 이기하		보안취약점 수정(파일 업로드시 엑셀파일만 가능하도록 추가)
 *  2015.05.28	조정국			메뉴리스트관리 선택시 "정상적으로 조회되었습니다"라는 alert창이 제일 먼저 뜨는것 수정 : 출력메시지 주석처리
 *  2020.03.00	rainend		myProject 적용
 * </pre>
 */

@Controller
public class EgovMenuManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMenuManageController.class);

	/* Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;

    /** EgovMenuManageService */
	@Resource(name = "progrmManageService")
	private EgovProgrmManageService progrmManageService;

    /** EgovFileMngService */
//	@Resource(name="EgovFileMngService")
//	private EgovFileMngService fileMngService;

    /** EgovFileMngUtil */
//	@Resource(name="EgovFileMngUtil")
//	private EgovFileMngUtil fileUtil;

//	@Resource(name = "excelZipService")
//    private EgovExcelService excelZipService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 메뉴리스트를 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "academy/menu/EgovMenuList"
     * @exception Exception
     */
    @IncludedInfo(name="메뉴리스트관리", order = 1090 ,gid = 60)
    @RequestMapping(value="/menu/EgovMenuListSelect.do")
    public String selectMenuList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
    	List<?> list_menulist = menuManageService.selectMenuList();
    	String resultMsg = egovMessageSource.getMessage("success.common.select");
        model.addAttribute("list_menulist", list_menulist);
        model.addAttribute("resultMsg", resultMsg);
      	return  "egovframework/com/academy/menu/EgovMenuList";
    }

    /**
     * 메뉴리스트의 메뉴정보를 등록한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "academy/menu/EgovMenuList"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuListInsert.do")
    public String insertMenuList(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    					BindingResult bindingResult, ModelMap model) throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}

		if(menuManageService.selectMenuNoByPk(menuManageVO) == 0){
        	menuManageService.insertMenuManage(menuManageVO);
    		resultMsg = egovMessageSource.getMessage("success.common.insert");
	        sLocationUrl = "forward:/menu/EgovMenuListSelect.do";
		}else{
    		resultMsg = egovMessageSource.getMessage("common.isExist.msg");
    		sLocationUrl = "forward:/menu/EgovMenuListSelect.do";
		}
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }

    /**
     * 메뉴리스트의 메뉴정보를 수정한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "academy/menu/EgovMenuList"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuListUpdt.do")
    public String updateMenuList(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    					BindingResult bindingResult, ModelMap model) throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}

		menuManageService.updateMenuManage(menuManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.update");
        sLocationUrl = "forward:/menu/EgovMenuListSelect.do";
	        
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }

    /**
     * 메뉴리스트의 메뉴정보를 삭제한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "academy/menu/EgovMenuList"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuListDelete.do")
    public String deleteMenuList(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    						BindingResult bindingResult, ModelMap model) throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}

		menuManageService.deleteMenuManage(menuManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.delete");
        sLocationUrl = "forward:/menu/EgovMenuListSelect.do";
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }

    
    /**
     * 메뉴정보목록을 상세화면 호출 및 상세조회한다.
     * @param req_menuNo  String
     * @return 출력페이지정보 "academy/menu/EgovMenuDetailSelectUpdt"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuManageListDetailSelect.do")
    public String selectMenuManage(@RequestParam("req_menuNo") String req_menuNo, 
    						@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
    	searchVO.setSearchKeyword(req_menuNo);

    	MenuManageVO resultVO = menuManageService.selectMenuManage(searchVO);
        model.addAttribute("menuManageVO", resultVO);

        return "egovframework/com/academy/menu/EgovMenuDetailSelectUpdt";
    }

    /**
     * 메뉴목록 리스트조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "academy/menu/EgovMenuManage"
     * @exception Exception
     */
    @IncludedInfo(name="메뉴관리리스트", order = 1091 ,gid = 60)
    @RequestMapping(value="/menu/EgovMenuManageSelect.do")
    public String selectMenuManageList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
    	// 내역 조회
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> list_menumanage = menuManageService.selectMenuManageList(searchVO);
		model.addAttribute("list_menumanage", list_menumanage);

        int totCnt = menuManageService.selectMenuManageListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

      	return "egovframework/com/academy/menu/EgovMenuManage";
    }

    /**
     * 메뉴목록 멀티 삭제한다.
     * @param checkedMenuNoForDel  String
     * @return 출력페이지정보 "forward:/menu/EgovMenuManageSelect.do"
     * @exception Exception
     */
    @RequestMapping("/menu/EgovMenuManageListDelete.do")
    public String deleteMenuManageList(@RequestParam("checkedMenuNoForDel") String checkedMenuNoForDel, 
    				@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
		String sLocationUrl = null;
    	String resultMsg    = "";

		String [] delMenuNo = checkedMenuNoForDel.split(",");
		menuManageVO.setMenuNo(Integer.parseInt(delMenuNo[0]));

		if (menuManageService.selectUpperMenuNoByPk(menuManageVO) != 0){
    		resultMsg = egovMessageSource.getMessage("fail.common.delete.upperMenuExist");
    		sLocationUrl = "forward:/menu/EgovMenuManageSelect.do";
		}else if (delMenuNo == null || (delMenuNo.length ==0)){
    		resultMsg = egovMessageSource.getMessage("fail.common.delete");
    		sLocationUrl = "forward:/menu/EgovMenuManageSelect.do";
		}else{
			menuManageService.deleteMenuManageList(checkedMenuNoForDel);
			resultMsg = egovMessageSource.getMessage("success.common.delete");
			sLocationUrl ="forward:/menu/EgovMenuManageSelect.do";
		}
		model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴정보를 등록화면으로 이동 및 등록 한다.
     * @param menuManageVO    MenuManageVO
     * @param commandMap      Map
     * @return 출력페이지정보 등록화면 호출시 "menu/EgovMenuRegist",
     *         출력페이지정보 등록처리시 "forward:/menu/EgovMenuManageSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuRegistInsert.do")
    public String insertMenuManage(@RequestParam Map<?, ?> commandMap, @ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    					BindingResult bindingResult, 	ModelMap model) throws Exception {
		String sLocationUrl = null;
    	String resultMsg    = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
        String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("insert")){
            beanValidator.validate(menuManageVO, bindingResult);
    		if (bindingResult.hasErrors()){
    			sLocationUrl = "egovframework/com/academy/menu/EgovMenuRegist";
    			return sLocationUrl;
    		}
    		if(menuManageService.selectMenuNoByPk(menuManageVO) == 0){
    			ComDefaultVO searchVO = new ComDefaultVO();
    			searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
    			if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
    	    		resultMsg = egovMessageSource.getMessage("fail.common.insert");
    		        sLocationUrl = "egovframework/com/academy/menu/EgovMenuRegist";
    			}else{
    	        	menuManageService.insertMenuManage(menuManageVO);
            		resultMsg = egovMessageSource.getMessage("success.common.insert");
    		        sLocationUrl = "forward:/menu/EgovMenuManageSelect.do";
    			}
    		}else{
        		resultMsg = egovMessageSource.getMessage("common.isExist.msg");
        		sLocationUrl = "egovframework/com/academy/menu/EgovMenuRegist";
    		}
    		model.addAttribute("resultMsg", resultMsg);
		}else{
            sLocationUrl = "egovframework/com/cademy/menu/EgovMenuRegist";
        }
		return sLocationUrl;
    }

    /**
     * 메뉴정보를 수정 한다.
     * @param menuManageVO  MenuManageVO
     * @return 출력페이지정보 "forward:/menu/EgovMenuManageSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuDetailSelectUpdt.do")
    public String updateMenuManage(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    					BindingResult bindingResult, ModelMap model) throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
        beanValidator.validate(menuManageVO, bindingResult);
		if (bindingResult.hasErrors()){
			sLocationUrl = "forward:/menu/EgovMenuManageListDetailSelect.do";
			return sLocationUrl;
		}
		ComDefaultVO searchVO = new ComDefaultVO();
		searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
		if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
    		resultMsg = egovMessageSource.getMessage("fail.common.update");
	        sLocationUrl = "forward:/menu/EgovMenuManageListDetailSelect.do";
		}else{
			menuManageService.updateMenuManage(menuManageVO);
	    	resultMsg = egovMessageSource.getMessage("success.common.update");
	       	sLocationUrl = "forward:/menu/EgovMenuManageSelect.do";
		}
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 메뉴정보를 삭제 한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "forward:/menu/EgovMenuManageSelect.do"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuManageDelete.do")
    public String deleteMenuManage(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
    	if (menuManageService.selectUpperMenuNoByPk(menuManageVO) != 0){
    		resultMsg = egovMessageSource.getMessage("fail.common.delete.upperMenuExist");
    		model.addAttribute("resultMsg", resultMsg);
    		return "forward:/menu/EgovMenuManageSelect.do";
		}

    	menuManageService.deleteMenuManage(menuManageVO);
    	resultMsg = egovMessageSource.getMessage("success.common.delete");
    	String _MenuNm = "%";
    	menuManageVO.setMenuNm(_MenuNm);
    	model.addAttribute("resultMsg", resultMsg);
      	return "forward:/menu/EgovMenuManageSelect.do";
    }

    /**
     * 메뉴리스트의 메뉴정보를 이동 메뉴목록을 조회한다.
     * @param searchVO  ComDefaultVO
     * @return 출력페이지정보 "academy/menu/EgovMenuMvmn"
     * @exception Exception
     */
    @RequestMapping(value="/sym/mnu/mpm/EgovMenuListSelectMvmn.do")
    public String selectMenuListMvmn(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}

    	List<?> list_menulist = menuManageService.selectMenuList();
        model.addAttribute("list_menulist", list_menulist);
      	return  "egovframework/com/academy/menu/EgovMenuMvmn";
    }

    /**
     * 메뉴리스트의 메뉴정보를 이동 메뉴목록을 조회한다. (New)
     * @param searchVO  ComDefaultVO
     * @return 출력페이지정보 "academy/menu/EgovMenuMvmn"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuListSelectMvmnNew.do")
    public String selectMenuListMvmnNew(@ModelAttribute("searchVO") ComDefaultVO searchVO, 	ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}

    	List<?> list_menulist = menuManageService.selectMenuList();
        model.addAttribute("list_menulist", list_menulist);
      	return  "egovframework/com/academy/menu/EgovMenuMvmnNew";
    }


    /*### 일괄처리 프로세스 ###*/

    /**
     * 메뉴생성 일괄삭제프로세스
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "academy/menu/EgovMenuBndeRegist"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuBndeAllDelete.do")
    public String menuBndeAllDelete(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
    	String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
    	menuManageService.menuBndeAllDelete();
    	resultMsg = egovMessageSource.getMessage("success.common.delete");
    	model.addAttribute("resultMsg", resultMsg);
        return "egovframework/com/academy/menu/EgovMenuBndeRegist";
    }


    /**
     * 메뉴일괄등록화면 호출 및  메뉴일괄등록처리 프로세스
     * @param commandMap    Map
     * @param menuManageVO  MenuManageVO
     * @param request       HttpServletRequest
     * @return 출력페이지정보 "academy/menu/EgovMenuBndeRegist"
     * @exception Exception
     */
    @RequestMapping(value="/menu/EgovMenuBndeRegist.do")
    public String menuBndeRegist(@RequestParam Map<?, ?> commandMap, final HttpServletRequest request,
    						@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        String sMessage  = "";
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/academy/login/EgovLoginUsr";
    	}
        String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
        if(sCmd.equals("bndeInsert")){
	    	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					// 2011.10.07 업로드 파일에 대한 확장자를 체크
					if (file.getOriginalFilename().endsWith(".xls")
							|| file.getOriginalFilename().endsWith(".xlsx")
							|| file.getOriginalFilename().endsWith(".XLS")
							|| file.getOriginalFilename().endsWith(".XLSX")) {

						if(menuManageService.menuBndeAllDelete()){
							sMessage = menuManageService.menuBndeRegist(menuManageVO, file.getInputStream());
				    	    resultMsg = sMessage;
						}else{
							resultMsg = egovMessageSource.getMessage("fail.common.msg");
							menuManageVO.setTmpCmd("EgovMenuBndeRegist Error!!");
					        model.addAttribute("resultVO", menuManageVO);
						}
					}else{
						LOGGER.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
						resultMsg = egovMessageSource.getMessage("fail.common.msg");
						model.addAttribute("resultMsg", resultMsg);
						return "egovframework/com/academy/menu/EgovMenuBndeRegist";
					}
					// *********** 끝 ***********

				}else{
					resultMsg = egovMessageSource.getMessage("fail.common.msg");
				}
			}
			sLocationUrl = "egovframework/com/academy/menu/EgovMenuBndeRegist";
	    	model.addAttribute("resultMsg", resultMsg);
        }else{
            sLocationUrl = "egovframework/com/academy/menu/EgovMenuBndeRegist";
        }
    	return sLocationUrl;
    }
}