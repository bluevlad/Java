package web.player;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import web.util.CommonUtil;
import web.util.paging.Paging;
import web.lecture.service.LectureService;
import web.axis.*;
import web.axis.security.cryptography.*;

@RequestMapping(value="/player")
@Controller
public class PlayerController {

	@Autowired
	private LectureService lectureservice;

	/**
	 * @Method Name : write
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 등록폼
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/view.pop2")
	public String write(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> view = lectureservice.lectureView(params);
		HashMap<String, String> map = (HashMap)view.get(0);
		List<HashMap<String, String>> playerinfolist = lectureservice.playinfo(params);
		HashMap<String, Object> playerinfo = new  HashMap<String, Object>();
		playerinfo = (HashMap)playerinfolist.get(0);
		String title = map.get("SUBJECT_TITLE");

		String returnUrl = "";

		params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), "2"));
		params.put("Quality", CommonUtil.isNull(request.getParameter("Quality"), "low_Q"));

	//	if(params.get("GUBUN").equals("1")){
		//	if(playerinfo.get("MOVIE_URL").toString().substring(0,1).equals("m")){
		//		params.put("GUBUN",  "1");
		//	}else{
		//		params.put("GUBUN",  "2");
		//	}
		//}

		//if("2".equals(params.get("GUBUN"))){
			returnUrl = "/player/starplayer";
		//}else{
		//	returnUrl = "/player/axplayer";
		//}
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		model.addAttribute("USER_ID", loginInfo.get("USER_ID"));
		model.addAttribute("TITLE", title);
		model.addAttribute("playerinfo", playerinfo);
		model.addAttribute("params", params);
		return returnUrl;
	}

	/**
	 * @Method Name : view_left
	 * @작성일 : 2013. 11.
	 * @Method 설명 : LEFT
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/view_left.pop2")
	public String view_left(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> playerinfolist = lectureservice.playinfo(params);
		HashMap<String, Object> playerinfo = new  HashMap<String, Object>();
		playerinfo = (HashMap)playerinfolist.get(0);

		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");

		String userid = loginInfo.get("USER_ID");
		String usernm = loginInfo.get("USER_NM");
		String orderno = request.getParameter("orderno");
		String package_no = request.getParameter("package_no");
		String movie_no = request.getParameter("movie_no");
		String leccode = request.getParameter("leccode");

		String deny_info_path = "";
		String player_domain = "";
		String manual_capture = "";
		String auto_capture = "";
		String capture_url = "";
		String serverurl = "";
		String activeUrl = "";
		String activeVersion = "";
		String activeWidth = "";
		String activeHeight = "";
		String cautionMsg = "";
		String dual_diff_density_restrict = "";
		String adminUrl = "";
		//String cdn_flag = playerinfo.get("MOVIE_URL").toString().substring(0,13);
		String cdn_flag = playerinfo.get("MP4_URL").toString().substring(0,13);
		
		if("1".equals(params.get("GUBUN"))){
			if("mms://willbes".equals(cdn_flag)){
				adminUrl = "http://218.38.28.210/willbes/admin/"; // 실서버에서 사용
				//adminUrl = "http://willbes.starplayer.net/willbes/admin/";
			} else {
				adminUrl = "http://1.234.47.230/admin/";
			}
			try {
				deny_info_path = "http://www.axissoft.co.kr/cab/data.html";
				player_domain = "";
				manual_capture = "0";
				auto_capture = "0";
				capture_url = adminUrl + "abc.asp";
				serverurl = adminUrl + "info.asp";
				activeUrl = "http://www.axissoft.co.kr/cab/AxPlayer2231.cab";
				activeVersion = "2,2,3,1";
				activeWidth = "680";
				activeHeight = "390";
				cautionMsg = "";
				dual_diff_density_restrict = "0";
				/*
			AxManager axManager = new AxManager(adminUrl + "base.asp");
			deny_info_path = axManager.getQueryStringByName("deny_info_path");
			player_domain = axManager.getQueryStringByName("player_domain");
			manual_capture = axManager.getQueryStringByName("manual_capture");
			auto_capture = axManager.getQueryStringByName("auto_capture");
			capture_url = axManager.getQueryStringByName("capture_url");
			serverurl = axManager.getQueryStringByName("serverurl");
			activeUrl = axManager.getQueryStringByName("activeUrl");
			activeVersion = axManager.getQueryStringByName("activeVersion");
			activeWidth = axManager.getQueryStringByName("activeWidth");
			activeHeight = axManager.getQueryStringByName("activeHeight");
			cautionMsg = axManager.getQueryStringByName("cautionMsg");
			dual_diff_density_restrict = axManager.getQueryStringByName("dual_diff_density_restrict");
				 */
			} catch (Exception e) {
				deny_info_path = "http://www.axissoft.co.kr/cab/data.html";
				player_domain = "";
				manual_capture = "0";
				auto_capture = "0";
				capture_url = adminUrl + "abc.asp";
				serverurl = adminUrl + "info.asp";
				activeUrl = "http://www.axissoft.co.kr/cab/AxPlayer2231.cab";
				activeVersion = "2,2,3,1";
				activeWidth = "680";
				activeHeight = "390";
				cautionMsg = "";
				dual_diff_density_restrict = "0";
			}
			String movie_filename = "";
			String movie_speed = "";
			//movie_filename = playerinfo.getMovie_filename1();
			movie_filename = playerinfo.get("MOVIE_FILENAME1").toString();
			movie_speed = "500K";
			//String mediaurl = playerinfo.get("MOVIE_URL").toString() + "/"+ movie_filename; //mms://프로토콜제거
			String mediaurl = playerinfo.get("MP4_URL").toString() + "/"+ movie_filename; //mms://프로토콜제거
			mediaurl = mediaurl.replace("mms://", "");
			String user_id = "TEST";
			String phone = "";
			String username = "";
			String requestUrl = adminUrl + "axserverplugin.asp?method=license&id=" + user_id;
			String key = "";
			try {
				AxLicense license = new AxLicense(requestUrl);
				key = license.getLicense();
			} catch (Exception e) {
				key = "0000000000";
			}
			String divine = "\t";
			String infoALL = mediaurl + divine + user_id + divine + key
					+ divine + deny_info_path + divine + player_domain + divine
					+ manual_capture + divine + auto_capture + divine
					+ capture_url + divine + serverurl + divine + cautionMsg
					+ divine + phone + divine + username;
			StringEncrypter encrypter = new StringEncrypter("axissoft","axis1234");
			String infoValue = encrypter.encrypt(infoALL);

			model.addAttribute("infoValue", infoValue);
		}


		model.addAttribute("params", params);
		model.addAttribute("playerinfo" , playerinfo);
		model.addAttribute("dual_diff_density_restrict", dual_diff_density_restrict);
		model.addAttribute("activeUrl", activeUrl);
		model.addAttribute("activeVersion", activeVersion);
		model.addAttribute("activeWidth", activeWidth);
		model.addAttribute("activeHeight", activeHeight);

		if("1".equals(params.get("GUBUN"))){
			return "/player/axplayer_left";
		} else{
			if(params.get("BROWSER").equals("Chrome") || params.get("BROWSER").equals("Firefox")|| params.get("BROWSER").equals("Safari")){
				return "/player/starplayer_left2";
			}else{
				return "/player/starplayer_left";
			}
		}
	}

	/**
	 * @Method Name : view_right
	 * @작성일 : 2013. 11.
	 * @Method 설명 : RIGHT
	 * @param model
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/view_right.pop2")
	public String view_right(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = lectureservice.getCbMovie4_free_admin(params);
		for(int i=0; i<list.size(); i++){
			HashMap<String, String> map = (HashMap)list.get(i);
			String title = CommonUtil.rmStringKor(map.get("MOVIE_NAME").toString(),18);
			map.put("MOVIE_NAME", title);
			list.set(i, map);
		}

		int listCount = lectureservice.getCbMovie4_free_admin_count(params);
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("params", params);
		if("1".equals(params.get("GUBUN"))){
			return "/player/axplayer_right";
		} else
			return "/player/starplayer_right";
	}


	/**
	 * @Method Name : setParam
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파라미터 SETTING
	 * @param params
	 * @param request
	 * @return HashMap
	 * @throws Exception
	 */
	public void setParam(HashMap<String, String> params, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		HashMap<String, String> loginInfo = (HashMap)session.getAttribute("AdmUserInfo");
		params.put("REG_ID",loginInfo.get("USER_ID"));
		params.put("UPD_ID",loginInfo.get("USER_ID"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), "10"));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), "1"));
		params.put("Quality", CommonUtil.isNull(request.getParameter("Quality"), "low_Q"));

		params.put("MOVIE_NO", CommonUtil.isNull(request.getParameter("MOVIE_NO"), ""));
		params.put("BRIDGE_LECCODE", CommonUtil.isNull(request.getParameter("BRIDGE_LECCODE"), ""));
		params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE"), ""));
		params.put("MOVIE_ORDER1", CommonUtil.isNull(request.getParameter("MOVIE_ORDER1"), ""));
		params.put("MOVIE_ORDER2", CommonUtil.isNull(request.getParameter("MOVIE_ORDER2"), ""));
		params.put("LINK", CommonUtil.isNull(request.getParameter("LINK"), ""));
		params.put("BROWSER", CommonUtil.isNull(request.getParameter("BROWSER"),""));

	    params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"),""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
	}


}
