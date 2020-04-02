package web.eventManagement;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import web.util.CommonUtil;
import web.util.MafUtil;
import web.util.excel.ExcelDownloadView;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.eventManagement.service.EventManagementService;
import web.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @FileName   : BannerController.java
 * @Project    : Banner
 * @Date       : 2020.03
 * @Author     :
 * @변경이력    :
 * @프로그램 설명 : 배너관리
 */
@RequestMapping(value="/eventManagement")
@Controller
public class EventManagementController {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private EventManagementService  eventManagementService;

	@Autowired
	private TeacherService teacherservice;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;
	/**
	 * @Method Name  : eventList
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      :	배너 리스트
	 * @param model
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	*/
	@RequestMapping(value="/eventMgtList.do")
	public String eventManagementList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		List<HashMap<String, String>> list = null;
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		//배너리스트
		list = eventManagementService.getEventList(params);

		//총 건수 -운영자
		int listCount = eventManagementService.getEventListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		model.addAttribute("list", list);
		model.addAttribute("kindlist", kindlist);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/eventManagement/eventList";
	}

	/**
	 * @Method Name  : eventInsertView
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 등록 화면
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventInsertView.do")
	public String eventInsertView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("SEARCHGUBN", "T");

		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("params",params);
 		return "/eventManagement/eventInsert";
	}

	/**
	 * @Method Name  : eventInsertProcess
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 등록 프로세스
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventInsertProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String rootPath = fsResource.getPath();
		String subPath = "event/";
		String subAttchPath = "event/attch/";

		String[] CATEGORY_CODE_ARR = request.getParameterValues("CATEGORY_CODE");
		String[] NOTICE_GUBUN_ARR = request.getParameterValues("NOTICE_GUBUN");
		String NOTICE_GUBUN = "";
		if(NOTICE_GUBUN_ARR != null){
			for(int i=0; i<NOTICE_GUBUN_ARR.length; i++){
				if(!"".equals(NOTICE_GUBUN))NOTICE_GUBUN += ",";
				NOTICE_GUBUN += NOTICE_GUBUN_ARR[i];
			}
		}
		String CONTNT_GUBUN = CommonUtil.isNull(request.getParameter("CONTNT_GUBUN"), "");

		MultipartFile img = multipartRequest.getFile("CONTENTS_IMG");
		MultipartFile thumListImg = multipartRequest.getFile("LIST_THUMBNAIL");
		MultipartFile thumIssueImg = multipartRequest.getFile("ISSUE_THUMBNAIL");

		if(img != null && img.isEmpty() == false && "IMG".equals(CONTNT_GUBUN)) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(img, rootPath, subPath);
			params.put("CONTENTS_IMG", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(thumListImg != null && thumListImg.isEmpty() == false) {
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumListImg, rootPath, subPath);
			params.put("LIST_THUMBNAIL", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		}
		if(thumIssueImg != null && thumIssueImg.isEmpty() == false) {
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumIssueImg, rootPath, subPath);
			params.put("ISSUE_THUMBNAIL", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		}

		params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));

		params.put("NOTICE_GUBUN", NOTICE_GUBUN);
		params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
		params.put("MEMBER_GUBUN", CommonUtil.isNull(request.getParameter("MEMBER_GUBUN"), ""));
		params.put("START_DATE", CommonUtil.isNull(request.getParameter("START_DATE"), ""));
		params.put("START_TIME", CommonUtil.isNull(request.getParameter("START_TIME"), ""));
		params.put("END_DATE", CommonUtil.isNull(request.getParameter("END_DATE"), ""));
		params.put("END_TIME", CommonUtil.isNull(request.getParameter("END_TIME"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		if(!"IMG".equals(CONTNT_GUBUN))
			params.put("CONTENTS_TEXT", CommonUtil.isNull(request.getParameter("CONTENTS_TEXT"), ""));

		params.put("OPTION1_YN", CommonUtil.isNull(request.getParameter("OPTION1"), "N"));
		params.put("OPTION2_YN", CommonUtil.isNull(request.getParameter("OPTION2"), "N"));
		params.put("OPTION3_YN", CommonUtil.isNull(request.getParameter("OPTION3"), "N"));
		params.put("MESSAGEBOX", CommonUtil.isNull(request.getParameter("MESSAGEBOX"), ""));
		params.put("SMSNUM", CommonUtil.isNull(request.getParameter("SMSNUM"), ""));
		params.put("MESSAGEBYTE", CommonUtil.isNull(request.getParameter("MESSAGEBYTE"), "0"));
		String [] eventNos;
		eventNos = new String [CATEGORY_CODE_ARR.length];

		String[] OPTION_NAME = request.getParameterValues("OPTION_NAME");
		String[] PEOPLE_GUBUN = request.getParameterValues("PEOPLE_GUBUN");
		String[] PEOPLE_CNT = request.getParameterValues("PEOPLE_CNT");
		params.put("MULTI_SELECT_YN", CommonUtil.isNull(request.getParameter("MULTI_SELECT_YN"), "N"));
		params.put("OPTION2_HIDDEN", CommonUtil.isNull(request.getParameter("OPTION2_HIDDEN"), "Y"));

		/*이벤트 게시판 등록*/
		if(CATEGORY_CODE_ARR != null){
			for(int i=0; i<CATEGORY_CODE_ARR.length; i++){
				params.put("CATEGORY_CODE", CATEGORY_CODE_ARR[i].toString());
				//insert문
				eventManagementService.eventInsertProcess(params);
				eventNos[i] = params.get("eventNo");

				if("Y".equals(params.get("OPTION1_YN"))){
					//옵션1 테이블 저장
					for(int j=0; j<OPTION_NAME.length; j++){
						params.put("EVENT_NO", params.get("eventNo"));
						params.put("OPTION_NAME", OPTION_NAME[j].toString());
						params.put("PEOPLE_GUBUN", PEOPLE_GUBUN[j].toString());
						params.put("PEOPLE_CNT", PEOPLE_CNT[j].toString());
						eventManagementService.insertEventOption1(params);
					}
				}
				
				if("Y".equals(params.get("OPTION3_YN"))){
					//옵션3 테이블 저장
						params.put("EVENT_NO", params.get("eventNo"));
						int msgbyte = Integer.parseInt(params.get("MESSAGEBYTE"));
						if(msgbyte > 80){
							params.put("MMS_YN", "Y");
						}else{
							params.put("MMS_YN", "N");
						}
						eventManagementService.insertEventOption3(params);
				}
			}
		}

		/*첨부 파일저장(복수개)*/
		//에디터를 선택했을 때 첨부파일 저장
		if(!"IMG".equals(CONTNT_GUBUN)){
			List<MultipartFile> uploadFile = multipartRequest.getFiles("ATTACH_FILE");
			HashMap<String, Object> fileMap = null;

			if(uploadFile != null && uploadFile.isEmpty() == false) {
				for(int j =0; j < uploadFile.size(); j++){
					fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subAttchPath);
					String file_path =  String.valueOf(fileMap.get("fileFullPath"));
					String file_name =  String.valueOf(fileMap.get("fileName"));

					if("null".equals(file_path))continue;
					params.put("FILE_PATH",file_path );
					params.put("FILE_NAME",file_name );
					params.put("REAL_FILE_NAME",uploadFile.get(j).getOriginalFilename().toString() );
					params.put("EVENT_NO", params.get("eventNo"));
					/*첨부 파일저장(복수개)*/
					if(eventNos != null && !"null".equals(file_name)){
						for(int k=0; k < eventNos.length; k++){
							params.put("EVENT_NO", eventNos[k].toString());
							eventManagementService.insertEventFile(params);
						}
					}
				}
			}else{
				params.put("FILE_PATH","" );
				params.put("FILE_NAME","" );
				params.put("REAL_FILE_NAME","");
			}
		} else {

		}

		model.addAttribute("params",params);
 		return "redirect:/eventManagement/eventMgtList.do";
	}

	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너관리 상세
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventDetail.do")
	public String eventDetail(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		//params.put("SEARCHGUBN", "T");
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		List<HashMap<String, String>> list = null;
		List<HashMap<String, String>> listAll = null;
		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		params.put("currentPage", String.valueOf(currentPage));
		params.put("pageRow", String.valueOf(pageRow));
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));

		listAll = eventManagementService.eventDetailOption2ListAll(params);

		list = eventManagementService.eventDetailOption2List(params);
		//총 건수 -운영자
		int listCount = eventManagementService.eventDetailOption2ListCount(params);
		//페이징 처리
		String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

		HashMap<String, Object> view = eventManagementService.eventDetail(params);
		List<HashMap<String, String>> fileList = eventManagementService.eventFileList(params);

		model.addAttribute("view", view);
		model.addAttribute("fileList", fileList);
		model.addAttribute("list", list);
		model.addAttribute("listAll", listAll);
		model.addAttribute("params",params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("totalCount", listCount);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageRow", pageRow);
		model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));

		return "/eventManagement/eventDetail";
	}

	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너관리 상세 -댓글 등록
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventDetailComment.do")
	@ResponseBody
	public Map eventDetailComment(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		//params.put("SEARCHGUBN", "T");
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("TXT", CommonUtil.isNull(request.getParameter("EVENT_TXT"), ""));

		String result = "0";
		eventManagementService.insertEventOption2(params);
		result = "1";
		params.put("result", result);

		return params;
	}


	//off 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/eventCommentListExcel.do")
	public View eventCommentListExcel(ModelMap model, HttpServletRequest request) throws Exception {

		List list = null;

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		// 검색조건
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("rsltStartNo", "0");
		params.put("rsltEndNo", "5000");

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = eventManagementService.eventDetailOption2ListAll(params);

		String excelName = "이벤트 댓글 리스트";

		List<String> headerList = new ArrayList<String>();
		headerList.add("이름");
		headerList.add("아이디");
		headerList.add("전화번호");
		headerList.add("작성일");
		headerList.add("내용");

		int dCount = 1;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;

			newMap.put(i++, map.get("USER_NM").toString());
			newMap.put(i++, map.get("USER_ID").toString());
			newMap.put(i++, map.get("PHONE_NO").toString());
			newMap.put(i++, map.get("REG_DT"));
			newMap.put(i++, map.get("TXT").toString());

			dCount++;
			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너관리 상세 -댓글 등록
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventDetailCommentDelete.do")
	@ResponseBody
	public Map eventDetailCommentDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		//params.put("SEARCHGUBN", "T");
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		String NO = CommonUtil.isNull(request.getParameter("NO"),"");
		String result = "0";
		if(!"".equals(NO)){
			params.put("NO", NO);
			eventManagementService.deleteEventOption2(params);
			result = "1";
		}else if(NO != null){
			for(int i=0; i<DEL_ARR.length; i++){
				params.put("NO", DEL_ARR[i].toString());
				eventManagementService.deleteEventOption2(params);
				result = "1";
			}
		}
		params.put("result", result);

		return params;
	}

	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너관리 상세
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventUpdateView.do")
	public String eventUpdateView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		HashMap<String, Object> view = eventManagementService.eventDetail(params);
		List<HashMap<String, String>> fileList = eventManagementService.eventFileList(params);
		List<HashMap<String, String>> option1List = eventManagementService.eventDetailOption1List(params);

		model.addAttribute("kindlist", kindlist);
		model.addAttribute("view", view);
		model.addAttribute("fileList", fileList);
		model.addAttribute("option1List", option1List);

		model.addAttribute("params",params);
		return "/eventManagement/eventUpdate";
	}

	/**
	 * @Method Name  : eventUpdateProcess
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 수정 process
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventUpdateProcess.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String rootPath = fsResource.getPath();
		String subPath = "event/";
		String subAttchPath = "event/attch/";

		String[] NOTICE_GUBUN_ARR = request.getParameterValues("NOTICE_GUBUN");
		String NOTICE_GUBUN = "";
		if(NOTICE_GUBUN_ARR != null){
			for(int i=0; i<NOTICE_GUBUN_ARR.length; i++){
				if(!"".equals(NOTICE_GUBUN))NOTICE_GUBUN += ",";
				NOTICE_GUBUN += NOTICE_GUBUN_ARR[i];
			}
		}
		String CONTNT_GUBUN = CommonUtil.isNull(request.getParameter("CONTNT_GUBUN"), "");

		MultipartFile img = multipartRequest.getFile("CONTENTS_IMG");
		String imgBefore = CommonUtil.isNull(request.getParameter("CONTENTS_IMG_BEFORE"), "");
		String isImgDel = CommonUtil.isNull(request.getParameter("CONTENTS_IMG_DEL"), "");

		MultipartFile thumImg = multipartRequest.getFile("LIST_THUMBNAIL");
		String thumImgBefore = CommonUtil.isNull(request.getParameter("LIST_THUMBNAIL_BEFORE"), "");
		String isThumImgDel = CommonUtil.isNull(request.getParameter("LIST_THUMBNAIL_DEL"), "");

		MultipartFile thumIssueImg = multipartRequest.getFile("ISSUE_THUMBNAIL");
		String thumIssueImgBefore = CommonUtil.isNull(request.getParameter("ISSUE_THUMBNAIL_BEFORE"), "");
		String isthumIssueImgDel = CommonUtil.isNull(request.getParameter("ISSUE_THUMBNAIL_DEL"), "");
		int width = 743;
		int height = 1071;

		//이미지 다시 올린파일이 있으면 기존파일 지우고 새로운파일 업로드
		if(img != null && img.isEmpty() == false) {
			if(!"".equals(imgBefore)) {
				fileUtil.deleteFile(rootPath + imgBefore);
			}
//			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(img, rootPath, subPath, width, height, "");
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(img, rootPath, subPath);
			params.put("CONTENTS_IMG", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		} else {
			if("Y".equals(isImgDel)){
				//새로운 파일 올리지 않고 기존파일만 삭제할 경우
				//validation 필수 check할경우 필요없는 기능
				fileUtil.deleteFile(rootPath + imgBefore);
				params.put("CONTENTS_IMG", "");
			}else {
				params.put("CONTENTS_IMG", imgBefore);
			}
		}

		//리스트썸네일 이미지 다시 올린파일이 있으면 기존파일 지우고 새로운파일 업로드
		if(thumImg != null && thumImg.isEmpty() == false) {
			if(!"".equals(thumImgBefore)) {
				fileUtil.deleteFile(rootPath + thumImgBefore);
			}
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumImg, rootPath, subPath);
			params.put("LIST_THUMBNAIL", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		} else {
			if("Y".equals(isThumImgDel)){
				//새로운 파일 올리지 않고 기존파일만 삭제할 경우
				//validation 필수 check할경우 필요없는 기능
				fileUtil.deleteFile(rootPath + thumImgBefore);
				params.put("LIST_THUMBNAIL", "");
			} else {
				params.put("LIST_THUMBNAIL", thumImgBefore);
			}
		}

		//이슈썸네일 이미지 다시 올린파일이 있으면 기존파일 지우고 새로운파일 업로드
		if(thumIssueImg != null && thumIssueImg.isEmpty() == false) {
			if(!"".equals(thumIssueImgBefore)) {
				fileUtil.deleteFile(rootPath + thumIssueImgBefore);
			}
			HashMap<String, Object> fileMap2 = fileUtil.uploadFile(thumIssueImg, rootPath, subPath);
			params.put("ISSUE_THUMBNAIL", fileMap2.get("fileFullPath").toString());
			Thread.sleep(100);
		} else {
			if("Y".equals(isthumIssueImgDel)){
				//새로운 파일 올리지 않고 기존파일만 삭제할 경우
				//validation 필수 check할경우 필요없는 기능
				fileUtil.deleteFile(rootPath + thumIssueImgBefore);
				params.put("ISSUE_THUMBNAIL", "");
			}else {
				params.put("ISSUE_THUMBNAIL", thumIssueImgBefore);
			}
		}

		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"), ""));
		params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
		params.put("NOTICE_GUBUN", NOTICE_GUBUN);
		params.put("OPEN_YN", CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
		params.put("MEMBER_GUBUN", CommonUtil.isNull(request.getParameter("MEMBER_GUBUN"), ""));
		params.put("START_DATE", CommonUtil.isNull(request.getParameter("START_DATE"), ""));
		params.put("START_TIME", CommonUtil.isNull(request.getParameter("START_TIME"), ""));
		params.put("END_DATE", CommonUtil.isNull(request.getParameter("END_DATE"), ""));
		params.put("END_TIME", CommonUtil.isNull(request.getParameter("END_TIME"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		if(!"IMG".equals(CONTNT_GUBUN))
			params.put("CONTENTS_TEXT", CommonUtil.isNull(request.getParameter("CONTENTS_TEXT"), ""));
		else params.put("CONTENTS_TEXT", "");

		params.put("OPTION1_YN", CommonUtil.isNull(request.getParameter("OPTION1"), "N"));
		params.put("OPTION2_YN", CommonUtil.isNull(request.getParameter("OPTION2"), "N"));
		params.put("OPTION3_YN", CommonUtil.isNull(request.getParameter("OPTION3"), "N"));
		params.put("MESSAGEBOX", CommonUtil.isNull(request.getParameter("MESSAGEBOX"), ""));
		params.put("SMSNUM", CommonUtil.isNull(request.getParameter("SMSNUM"), ""));
		params.put("MESSAGEBYTE", CommonUtil.isNull(request.getParameter("MESSAGEBYTE"), "0"));

		String[] OPTION_NAME = request.getParameterValues("OPTION_NAME");
		String[] PEOPLE_GUBUN = request.getParameterValues("PEOPLE_GUBUN");
		String[] PEOPLE_CNT = request.getParameterValues("PEOPLE_CNT");
		params.put("MULTI_SELECT_YN", CommonUtil.isNull(request.getParameter("MULTI_SELECT_YN"), "N"));
		params.put("OPTION2_HIDDEN", CommonUtil.isNull(request.getParameter("OPTION2_HIDDEN"), "Y"));

		/*이벤트 게시판 등록*/
		//insert문
		eventManagementService.eventUpdateProcess(params);

		if("Y".equals(params.get("OPTION1_YN")) && OPTION_NAME != null){
			eventManagementService.deleteEventOption1(params);
			//옵션1 테이블 저장
			for(int j=0; j<OPTION_NAME.length; j++){
				params.put("OPTION_NAME", OPTION_NAME[j].toString());
				params.put("PEOPLE_GUBUN", PEOPLE_GUBUN[j].toString());
				params.put("PEOPLE_CNT", PEOPLE_CNT[j].toString());
				eventManagementService.insertEventOption1(params);
			}
		}else {
			eventManagementService.deleteEventOption1(params);
		}
		
		if("Y".equals(params.get("OPTION3_YN"))){
			eventManagementService.deleteEventOption3(params);
			//옵션3 테이블 저장
				int msgbyte = Integer.parseInt(params.get("MESSAGEBYTE"));
				if(msgbyte > 80){
					params.put("MMS_YN", "Y");
				}else{
					params.put("MMS_YN", "N");
				}
				eventManagementService.insertEventOption3(params);
		}
		else {
			eventManagementService.deleteEventOption3(params);
		}
		
		/*첨부 파일저장(복수개)*/
		//에디터를 선택했을 때 첨부파일 저장
		if(!"IMG".equals(CONTNT_GUBUN)){
			List<MultipartFile> uploadFile = multipartRequest.getFiles("ATTACH_FILE");
			HashMap<String, Object> fileMap = null;

			String[] FILE_NO = request.getParameterValues("FILE_DEL_ALL");
			if(FILE_NO != null){
				for(int i=0; i<FILE_NO.length; i++){
					params.put("FILE_NO", FILE_NO[i].toString());
					List<HashMap<String, String>> deleteFile = eventManagementService.eventFileList(params);
					fileUtil.deleteFile(rootPath + deleteFile.get(0).get("FILE_PATH"));
					eventManagementService.deleteEventFile(params);
				}
			}

			if(uploadFile != null && uploadFile.isEmpty() == false) {
				for(int j =0; j < uploadFile.size(); j++){
					fileMap = fileUtil.uploadFile(uploadFile.get(j), rootPath, subAttchPath);
					String file_path =  String.valueOf(fileMap.get("fileFullPath"));
					String file_name =  String.valueOf(fileMap.get("fileName"));

					if("null".equals(file_path))continue;
					params.put("FILE_PATH",file_path );
					params.put("FILE_NAME",file_name );
					params.put("REAL_FILE_NAME",uploadFile.get(j).getOriginalFilename().toString() );
					eventManagementService.insertEventFile(params);
				}
			}else{
				params.put("FILE_PATH","" );
				params.put("FILE_NAME","" );
				params.put("REAL_FILE_NAME","");
			}
		} else {
			//에디터 선택안했을때 첨부파일 지워야하게 되면 작성
		}

		model.addAttribute("params",params);
 		return "redirect:/eventManagement/eventDetail.do?EVENT_NO="+params.get("EVENT_NO");
	}

	/**
	 * @Method Name  : eventDelete
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 삭제
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/eventDelete.do")
	@ResponseBody
	public Map eventDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		//params.put("SEARCHGUBN", "T");
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));

		String result = "0";
		eventManagementService.eventDelete(params);
		result = "1";
		params.put("result", result);

		return params;
	}

	@RequestMapping(value="/eventResultList.do")
	public String eventResultList(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("SEARCHOPTIONNO", CommonUtil.isNull(request.getParameter("SEARCHOPTIONNO"),""));
		params.put("SEARCHKEYWORD", CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"),""));

		List<HashMap<String, String>> list = null;
		int rsltCurrentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("rsltCurrentPage"), "1"));
		int rsltPageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("rsltPageRow"), "20"));
		int rsltStartNo = (rsltCurrentPage - 1) * rsltPageRow;
		int rsltEndNo = rsltStartNo + rsltPageRow;

		params.put("rsltCurrentPage", String.valueOf(rsltCurrentPage));
		params.put("rsltPageRow", String.valueOf(rsltPageRow));
		params.put("rsltStartNo", String.valueOf(rsltStartNo));
		params.put("rsltEndNo", String.valueOf(rsltEndNo));

		//리스트
		HashMap<String, Object> view = eventManagementService.eventDetail(params);
		list = eventManagementService.getEventResultList(params);
		List<HashMap<String, String>> option1List = eventManagementService.eventDetailOption1List(params);

		//총 건수 -운영자
		int listCount = eventManagementService.getEventResultListCount(params);

		//페이징 처리
		String pagingHtml = new Paging(rsltCurrentPage, listCount, rsltPageRow).getPagingHtml().toString();

		model.addAttribute("view", view);
		model.addAttribute("list", list);
		model.addAttribute("option1List", option1List);
		model.addAttribute("params", params);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("rsltTotalCount", listCount);
		model.addAttribute("rsltCurrentPage", rsltCurrentPage);
		model.addAttribute("rsltPageRow", rsltPageRow);
		model.addAttribute("rsltTotalPage", (int) Math.ceil((double) listCount / rsltPageRow));

		return "/eventManagement/eventResultList";
	}

	//off 엑셀리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/eventResultListExcel.do")
	public View eventResultListExcel(ModelMap model, HttpServletRequest request) throws Exception {

		List list = null;

		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		// 검색조건
		params.put("EVENT_NO", CommonUtil.isNull(request.getParameter("EVENT_NO"),""));
		params.put("SEARCHOPTIONNO", CommonUtil.isNull(request.getParameter("SEARCHOPTIONNO"),""));
		params.put("SEARCHKEYWORD", CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"),""));
		params.put("rsltStartNo", "0");
		params.put("rsltEndNo", "10000");

		//엑셀 리스트
		List<HashMap<String, String>> exe_list = eventManagementService.getEventResultList(params);

		String excelName = "이벤트 참여자 리스트";

		List<String> headerList = new ArrayList<String>();
		headerList.add("NO");
		headerList.add("이름");
		headerList.add("아이디");
		headerList.add("전화번호");
		headerList.add("이메일");
		headerList.add("신청일자");
		headerList.add("신청구분");

		int dCount = 1;
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		for(HashMap<String, String> map : exe_list) {
			HashMap newMap = new HashMap();
			int i = 0;

			newMap.put(i++, String.valueOf(dCount));
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("USER_NAME"),"")));
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("USER_ID"),"")));
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("PHONE_NO"),"")));
			newMap.put(i++, String.valueOf(MafUtil.nvl(map.get("EMAIL"),"")));
			newMap.put(i++, String.valueOf(MafUtil.nvl(String.valueOf(map.get("REG_DT")),"")));
			newMap.put(i++, map.get("OPTION_NO").toString());

			dCount++;
			newList.add(newMap);
		}

		model.addAttribute("excelName", excelName);
		model.addAttribute("headerList", headerList);
		model.addAttribute("dataList", newList);

		return new ExcelDownloadView();
	}

	/**
	 * @Method Name  : eventCheckDelete
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 일괄 삭제
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	* /
	@RequestMapping(value="/eventCheckDelete.do")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String eventCheckDelete(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();
		setParam(params, request, model);

		String rootPath = fsResource.getPath();

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		String[] DEL_ARR_VALUE;

		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				DEL_ARR_VALUE = DEL_ARR[i].split(",",3);
				params.put("SEQ", DEL_ARR_VALUE[0]);
				if(!"".equals(DEL_ARR_VALUE[1]))fileUtil.deleteFile(rootPath + DEL_ARR_VALUE[1]);
				if(!"".equals(DEL_ARR_VALUE[2]))fileUtil.deleteFile(rootPath + DEL_ARR_VALUE[2]);
				eventManagementService.eventDelete(params);
			}
		}
		model.addAttribute("params",params);

		return "forward:/eventManagement/eventMgtList.do";
	} */

	/**
	 * @Method Name : setParam
	 * @작성일 : 2020.03.
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
		params.put("REG_NM",loginInfo.get("USER_NM"));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHKEYWORD", CommonUtil.isNull(request.getParameter("SEARCHKEYWORD"), ""));

		params.put("TOP_MENU_ID",	CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", 	CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));
		params.put("MENU_NM", CommonUtil.isNull(request.getParameter("MENU_NM")));
		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		model.addAttribute("MENU_NM", params.get("MENU_NM"));
	}
}
