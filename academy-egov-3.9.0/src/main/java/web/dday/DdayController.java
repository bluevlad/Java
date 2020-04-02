package web.dday;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.dday.service.DdayService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/dday")
@Controller
public class DdayController {
	@Autowired
	private DdayService service;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@RequestMapping(value="/list.do")
	public String list(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		String searchDdayName = CommonUtil.isNull(request.getParameter("searchDdayName"));
		String searchCategory = CommonUtil.isNull(request.getParameter("searchCategory"));
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		String searchMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"));
		if(searchMenuType.indexOf("OM") > -1) {
			params.put("MENUTYPE", "O");
		} else {
			params.put("MENUTYPE", "F");
		}
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		params.put("searchDdayName", searchDdayName);
		params.put("searchCategory", searchCategory);

		List<HashMap<String, String>> categoryList = service.categoryList(params);
		List<HashMap<String, String>> list = service.list(params);
		int listCount = service.listCount(params);

		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		model.addAttribute("searchDdayName", searchDdayName);
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("list", list);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
		return "dday/list";
	}

	@RequestMapping(value="/view.do")
	public String view(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();

		String DDAY_IDX = CommonUtil.isNull(request.getParameter("DDAY_IDX"), "-1");

		params.put("DDAY_IDX", DDAY_IDX);

		HashMap<String, String> view = service.view(params);
		List<HashMap<String, String>> categoryList = service.categoryList(params);

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		model.addAttribute("searchDdayName", CommonUtil.isNull(request.getParameter("searchDdayName")));
		model.addAttribute("searchCategory", CommonUtil.isNull(request.getParameter("searchCategory")));
		model.addAttribute("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		model.addAttribute("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		model.addAttribute("DDAY_IDX", DDAY_IDX);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("view", view);
		return "dday/view";
	}

	@RequestMapping(value="/insertProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String insertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");

		params.put("USER_ID",sessionMap.get("USER_ID"));
		params.put("DDAY_TYPE",CommonUtil.isNull(request.getParameter("DDAY_TYPE"), "P"));
		params.put("DDAY_CATEGORY",CommonUtil.isNull(request.getParameter("DDAY_CATEGORY")));
		params.put("DDAY_NAME",CommonUtil.isNull(request.getParameter("DDAY_NAME")));
		params.put("DDAY_DATE",CommonUtil.isNull(request.getParameter("DDAY_DATE")));
		params.put("DDAY_LINK",CommonUtil.isNull(request.getParameter("DDAY_LINK")));
		params.put("DDAY_ACTIVE",CommonUtil.isNull(request.getParameter("DDAY_ACTIVE")));

		service.insert(params);

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		model.addAttribute("searchDdayName", CommonUtil.isNull(request.getParameter("searchDdayName")));
		model.addAttribute("searchCategory", CommonUtil.isNull(request.getParameter("searchCategory")));
		model.addAttribute("currentPage", "1");
		model.addAttribute("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		return "forward:/dday/list.do";
	}

	@RequestMapping(value="/updateProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String updateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("DDAY_TYPE",CommonUtil.isNull(request.getParameter("DDAY_TYPE"), "P"));
		params.put("DDAY_CATEGORY",CommonUtil.isNull(request.getParameter("DDAY_CATEGORY")));
		params.put("DDAY_NAME",CommonUtil.isNull(request.getParameter("DDAY_NAME")));
		params.put("DDAY_DATE",CommonUtil.isNull(request.getParameter("DDAY_DATE")));
		params.put("DDAY_LINK",CommonUtil.isNull(request.getParameter("DDAY_LINK")));
		params.put("DDAY_ACTIVE",CommonUtil.isNull(request.getParameter("DDAY_ACTIVE")));
		params.put("DDAY_IDX",CommonUtil.isNull(request.getParameter("DDAY_IDX"), "-1"));

		service.update(params);

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		model.addAttribute("searchDdayName", CommonUtil.isNull(request.getParameter("searchDdayName")));
		model.addAttribute("searchCategory", CommonUtil.isNull(request.getParameter("searchCategory")));
		model.addAttribute("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		model.addAttribute("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		model.addAttribute("DDAY_IDX", CommonUtil.isNull(request.getParameter("DDAY_IDX"), "-1"));

		return "forward:/dday/view.do";
	}

	@RequestMapping(value="/deleteProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String deleteProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("DDAY_IDX",CommonUtil.isNull(request.getParameter("DDAY_IDX"), "-1"));

		service.delete(params);

		model.addAttribute("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID")));
		model.addAttribute("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE")));
		model.addAttribute("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM")));
		model.addAttribute("searchDdayName", CommonUtil.isNull(request.getParameter("searchDdayName")));
		model.addAttribute("searchCategory", CommonUtil.isNull(request.getParameter("searchCategory")));
		model.addAttribute("currentPage", "1");
		model.addAttribute("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		return "forward:/dday/list.do";
	}
}