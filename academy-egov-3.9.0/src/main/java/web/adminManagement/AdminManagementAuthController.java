package web.adminManagement;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;
import web.adminManagement.service.AdminManagementAuthService;
import web.util.CommonUtil;
import web.util.paging.Paging;

/**
 * @FileName   : AdminManagementController.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 메뉴
 */
@RequestMapping(value="/adminManagementAuth")
@Controller
public class AdminManagementAuthController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private AdminManagementAuthService adminManagementAuthService;

		/*
		 * ===========================================권한 관리=================================================
		 *
		 */

		/**
		 * @Method Name  : authContorlList
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      : 권한 관리 리스트
		 * @param model
		 * @param req
		 * @return
		 * @throws UnsupportedEncodingException
		*/
		@RequestMapping(value="/authControlList.do")
		public String authContorlList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
			List<HashMap<String, Object>> list = null;
			Map<String, Object> searchMap = new HashMap<String, Object>();

			//메뉴 param
			searchMap.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			searchMap.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			searchMap.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", searchMap.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", searchMap.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", searchMap.get("L_MENU_NM"));
			//메뉴 param

			// 검색조건
			String SEARCHTYPE = CommonUtil.isNull(request.getParameter("SEARCHTYPE"), "");
			String SEARCHKEYWORD = CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), "");
			int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			int startNo = (currentPage - 1) * pageRow;
			int endNo = startNo + pageRow;

			searchMap.put("SEARCHTYPE", SEARCHTYPE);
			searchMap.put("SEARCHKEYWORD", URLDecoder.decode(SEARCHKEYWORD,"UTF-8"));

			searchMap.put("currentPage", currentPage);
			searchMap.put("pageRow", pageRow);
			searchMap.put("startNo", String.valueOf(startNo));
			searchMap.put("endNo", String.valueOf(endNo));

			// 공통코드 리스트
			list = adminManagementAuthService.authContorlList(searchMap);


			// 총 건수 - 공통코드
			int listCount = adminManagementAuthService.authContorlListCount(searchMap);

			//페이징 처리
			String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

			model.addAttribute("list", list);
			model.addAttribute("searchMap", searchMap);
			model.addAttribute("pagingHtml", pagingHtml);
			model.addAttribute("totalCount", listCount);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageRow", pageRow);
			model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

			return "/adminManagementAuth/authControlList";
		}


		/**
		 * @Method Name  : authControlInsert
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      : 권한 관리 - 리스트 - 등록 화면
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlInsert.do")
		public String authControlInsert(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			model.addAttribute("params",params);
	 		return "/adminManagementAuth/authControlInsert";
		}


		/**
		 * @Method Name  : authControlInsertProcess
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	 권한 등록 process
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlInsertProcess.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String authControlInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE", "");
			params.put("SEARCHKEYWORD" , "");

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("SITE_ID",request.getParameter("SITE_ID")); //권한 ID
			params.put("SITE_NM",request.getParameter("SITE_NM")); //권한명
			params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV")); //권한 상태
			params.put("ISUSE",request.getParameter("ISUSE")); //권한 상태

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = sessionMap.get("USER_NM");
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("REG_ID", userId);

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			//코드 등록
			adminManagementAuthService.authControlInsertProcess(params);
			model.addAttribute("params",params);
	 		return "forward:/adminManagementAuth/authControlList.do";
		}


		/**
		 * @Method Name  : authControlDetail
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한관리 상세
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlDetail.do")
		public String authControlDetail(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("DETAIL_SITE_ID",request.getParameter("DETAIL_SITE_ID")); //권한 ID

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			//코드 select
			HashMap<String, Object> detail  =	adminManagementAuthService.authControlDetail(params);

			model.addAttribute("params",params);
			model.addAttribute("detail",detail);

			return "/adminManagementAuth/authControlDetail";
		}

		/**
		 * @Method Name  : authControlUpdateProcess
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한 수정 process
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlUpdateProcess.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String authControlUpdateProcess(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("DETAIL_SITE_ID",request.getParameter("DETAIL_SITE_ID"));
			params.put("SITE_ID",request.getParameter("SITE_ID"));
			params.put("SITE_NM",request.getParameter("SITE_NM"));
			params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV"));
			params.put("ISUSE",request.getParameter("ISUSE"));

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = sessionMap.get("USER_NM");
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("UPD_ID", userId);

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			//코드 등록
			adminManagementAuthService.authControlUpdateProcess(params);
			adminManagementAuthService.authControlMenuUpdateProcess(params);

			model.addAttribute("params",params);
	 		return "forward:/adminManagementAuth/authControlList.do";
		}

		/**
		 * @Method Name  : authControlDelete
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한관리 상세페이지 개별 삭제
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlDelete.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String authControlDelete(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE", "");
			params.put("SEARCHKEYWORD" , "");

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("DETAIL_SITE_ID",request.getParameter("DETAIL_SITE_ID")); //상세 SITE_ID

			adminManagementAuthService.authControlDelete(params); //권한 삭제
			adminManagementAuthService.deleteSiteIdAuthMenu(params); // 권한별 등록 메뉴 삭제

			//메뉴 param
			HashMap<String, String> menuParams = new HashMap<String, String>();
			menuParams.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			menuParams.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			menuParams.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
			model.addAttribute("menuParams", menuParams);
			//메뉴 param

			model.addAttribute("params",params);
	 		return "redirect:/adminManagementAuth/authControlList.do";
		}

		/**
		 * @Method Name  : authControlCheckDelete
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한 checkbox delete
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlCheckDelete.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String authControlCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE", "");
			params.put("SEARCHKEYWORD" , "");

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("deleteIds",request.getParameter("deleteIds")); //삭제 할 권한 SITE_ID 's
			//코드 일괄 삭제
			adminManagementAuthService.authControlCheckDelete(params); //권한 삭제
			adminManagementAuthService.deleteSiteIdCheckAuthMenu(params); // 권한별 등록 메뉴 삭제
			model.addAttribute("params",params);

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			return "redirect:/adminManagementAuth/authControlList.do";
		}

		/**
		 * @Method Name  : authControlMenuList
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한별 등록된 메뉴  리스트
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authControlMenuList.do")
		public String authControlMenuList(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();
			String onoff = request.getParameter("MENU_ONOFF");
			params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("DETAIL_SITE_ID",request.getParameter("DETAIL_SITE_ID")); //권한 SITE_ID
			params.put("MENU_ONOFF",onoff); //MENU_ONOFF

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = sessionMap.get("USER_NM");
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("UPD_ID", userId);
			//메뉴 리스트
			List<HashMap<String, Object>> menuList = null;
			List<HashMap<String, Object>> authMenuList = null;
			authMenuList = adminManagementAuthService.authMenuList(params); //등록된 메뉴 리스트

			if(onoff.equals("ON")){
				menuList  =	adminManagementAuthService.authControlOnMenuList(params); // 권한별 온라인 메뉴 리스트
			}else if(onoff.equals("OFF")){
				menuList  =	adminManagementAuthService.authControlOffMenuList(params);	// 권한별 오프라인 메뉴 리스트
            }else if(onoff.equals("TEST")){
                menuList  = adminManagementAuthService.authControlTestMenuList(params);  // 권한별 모의고사 메뉴 리스트
			}
			//권한별 설정 메뉴 리스트

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			model.addAttribute("params",params);
			model.addAttribute("menuList",menuList);
			model.addAttribute("authMenuList",authMenuList);

			return "/adminManagementAuth/authControlMenuList";
		}

		/**
		 * @Method Name  : authMenuInsertProcess
		 * @Date         : 2020.03
		 * @Author       : rainend
		 * @변경이력      :
		 * @Method 설명      :	권한 등록 process
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/authMenuInsertProcess.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String authMenuInsertProcess(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("SEARCHTYPE",CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
			params.put("SEARCHKEYWORD",CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
			params.put("MENU_ONOFF",request.getParameter("MENU_ONOFF"));
			params.put("DETAIL_SITE_ID",request.getParameter("DETAIL_SITE_ID"));

			String menu = request.getParameter("insertMenuId");
			String[] menu_arr = menu.split(",");

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = sessionMap.get("USER_NM");
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("REG_ID", userId);

			//우선 지우고 등록
			adminManagementAuthService.deleteAuthMenu(params);

			for (int i = 0; i < menu_arr.length; i++) {
				params.put("MENU_ID", String.valueOf(menu_arr[i]) );
				adminManagementAuthService.authMenuInsertProcess(params);
			}
			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			model.addAttribute("params",params);

	 		return "redirect:/adminManagementAuth/authControlList.do";
		}

}
