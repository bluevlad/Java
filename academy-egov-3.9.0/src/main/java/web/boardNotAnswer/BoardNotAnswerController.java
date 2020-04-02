package web.boardNotAnswer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import web.util.StringUtil;
import web.util.CommonUtil;
import web.util.file.FileUtil;
import web.util.paging.Paging;
import web.board.service.BoardService;
import web.boardNotAnswer.service.BoardNotAnswerService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RequestMapping(value="/boardNotAnswer")
@Controller
public class BoardNotAnswerController {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;	//DI

	@Resource(name="fileUtil")
	FileUtil fileUtil;

	@Autowired
	private BoardNotAnswerService boardService;

    @Resource(name="boardServiceImpl")
	private BoardService boardService2;

		/**
		 * @Method Name : boardNotAnswerList
		 * @작성일 : 2013. 10
		 * @Method 설명 : 미응답 게시판 리스트
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/boardNotAnswerList.do")
		public String boardNotAnswerList(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();

			String searchMenuType = CommonUtil.isNull(request.getParameter("MENUTYPE"));
			if(searchMenuType.indexOf("OM") > -1) {
				params.put("ONOFF_DIV", "O");
			} else {
				params.put("ONOFF_DIV", "F");
			}

			int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			int startNo = (currentPage - 1) * pageRow;
			int endNo = startNo + pageRow;

			params.put("startNo", String.valueOf(startNo));
			params.put("endNo", String.valueOf(endNo));
			
			if("F".equals(params.get("ONOFF_DIV"))){
				//동적셀렉트 박스 구성을 위한 직급 리스트 가져오기
		        List<HashMap<String, String>> rankList = boardService.getRankCode(params);
				params.put("SEARCHCATEGORY",CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		        params.put("SEARCHCAMPUS_YN",CommonUtil.isNull(request.getParameter("SEARCHCAMPUS_YN"), ""));
		        params.put("SEARCHBOARD_OFF_TYPE",CommonUtil.isNull(request.getParameter("SEARCHBOARD_OFF_TYPE"), ""));
		        params.put("SEARCHKIND",CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		        params.put("SEARCHTEXT",CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		        model.addAttribute("rankList", rankList);
			}
			//해당 게시판 글 리스트
			List<HashMap<String, String>> list = boardService.boardList(params);
			int listCount = boardService.listCount(params);

			String pagingHtml = new Paging(currentPage, listCount, pageRow).getPagingHtml().toString();

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			model.addAttribute("params", params);
			//메뉴 param


			model.addAttribute("list", list);
			model.addAttribute("pagingHtml", pagingHtml);
			model.addAttribute("totalCount", listCount);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pageRow", pageRow);
			model.addAttribute("totalPage", (int) Math.ceil((double) listCount / pageRow));
			logger.info("게시판 리스트 : " + list);
			return "boardNotAnswer/boardNotAnswerList";
		}



	/**
	 * @Method Name : boardNotAnswerView
	 * @작성일 : 2013. 9.
	 * @Method 설명 : 미응답게시판글 보기 화면
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/boardNotAnswerView.do")
	public String boardNotAnswerView(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();

		params.put("currentPage",CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("ONOFF_DIV",CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
		params.put("BOARDVIEW_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
		params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
		params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
		params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));

		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
		String userName = "";
		if (sessionMap.get("USER_ROLE").equals("ADMIN")){
			userName = sessionMap.get("USER_NICKNM");
		}else{
			userName = sessionMap.get("USER_NM");
		}
		String userId = sessionMap.get("USER_ID");
		params.put("USERNAME", userName);
		params.put("REG_ID", userId);
		params.put("UPD_ID", userId);


		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		//조회수 증가
		//boardService.updateBoardHits(params);

		// 등록된 글 data
		HashMap<String, Object> detailView = boardService.getBoardDetail(params);
		List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);
		List<HashMap<String, String>> boardAttachFile_Img = boardService.boardAttachFile_Img(params);

		/*detailView.put("ISIMAGE","N");

		if(detailView.get("FILE_NAME") != null && detailView.get("FILE_NAME").toString() !=""  ){
		String isImage = detailView.get("FILE_NAME").toString().split("[.]")[1];
			if(isImage.equals("jpg")||isImage.equals("JPG")||isImage.equals("jpeg")||isImage.equals("JPEG")||isImage.equals("png")||isImage.equals("PNG")||isImage.equals("bmp")||isImage.equals("BMP")||isImage.equals("gif")||isImage.equals("GIF")){
				detailView.put("ISIMAGE","Y");
			}
		}*/
		model.addAttribute("params",params);
		model.addAttribute("detailView",detailView);
		model.addAttribute("boardAttachFile",boardAttachFile);
		model.addAttribute("boardAttachFile_Img",boardAttachFile_Img);
 		return "/boardNotAnswer/boardNotAnswerView";
	}


	/**
	 * @Method Name : boardNotAnswerModify
	 * @작성일 : 2013. 9.
	 * @Method 설명 : 미응답 게시판글 수정 화면
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/boardNotAnswerModify.do")
	public String boardNotAnswerModify(ModelMap model, HttpServletRequest request) throws Exception {
		HashMap<String, String> params = new  HashMap<String, String>();


		params.put("currentPage",CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV"));
		params.put("BOARDVIEW_MNG_SEQ",request.getParameter("BOARDVIEW_MNG_SEQ"));
		params.put("BOARDVIEW_SEQ",request.getParameter("BOARDVIEW_SEQ"));
		params.put("BOARDVIEWPARENT_SEQ",request.getParameter("BOARDVIEWPARENT_SEQ"));
		params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

		//메뉴 param
		params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
		params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
		params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

		model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
		model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
		model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
		//메뉴 param

		HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
		String userName = "";
		if (sessionMap.get("USER_ROLE").equals("ADMIN")){
			userName = sessionMap.get("USER_NICKNM");
		}else{
			userName = sessionMap.get("USER_NM");
		}
		String userId = sessionMap.get("USER_ID");
		params.put("USERNAME", userName);
		params.put("REG_ID", userId);
		params.put("UPD_ID", userId);

			//수정 체크박스를 위한 코드리스트
			List<HashMap<String, String>> list = boardService.getRankCode(params);

			//해당글의 코드데이터
			List<HashMap<String, String>> boardCodeList = boardService.getboardCodeList(params);
			// 등록된 글 data
			HashMap<String, Object> detailView = boardService.getBoardDetail(params);
			List<HashMap<String, String>> boardAttachFile = boardService.boardAttachFile(params);

			model.addAttribute("params",params);
			model.addAttribute("detailView",detailView);
			model.addAttribute("gbList",list);
			model.addAttribute("boardCodeList",boardCodeList);
			model.addAttribute("boardAttachFile",boardAttachFile);


 		return "/boardNotAnswer/boardNotAnswerModify";
	}


		/**
		 * @Method Name : boardNotAnswerUpdateProcess
		 * @작성일 : 2013. 9.
		 * @Method 설명 : 미응답 게시판글 수정프로세스
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/boardNotAnswerUpdateProcess.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String boardNotAnswerUpdateProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("ONOFF_DIV",request.getParameter("ONOFF_DIV"));
			params.put("CREATENAME",request.getParameter("CREATENAME"));
			params.put("REG_ID",request.getParameter("REG_ID"));
			params.put("NOTICE_TOP_YN",request.getParameter("NOTICE_TOP_YN"));
			params.put("OPEN_YN",request.getParameter("OPEN_YN"));
			params.put("SUBJECT",request.getParameter("SUBJECT"));
			params.put("CONTENT",request.getParameter("CONTENT"));
			
			params.put("CONTENT_NEW",StringUtil.fnStrCut(CommonUtil.isNull(request.getParameter("CONTENT"), ""),4000,true,false));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));


			params.put("BOARDVIEW_MNG_SEQ",request.getParameter("BOARDVIEW_MNG_SEQ"));
			params.put("BOARDVIEW_SEQ",request.getParameter("BOARDVIEW_SEQ"));
			params.put("BOARDVIEWPARENT_SEQ",request.getParameter("BOARDVIEWPARENT_SEQ"));
			params.put("BOARDVIEWCODENAME",request.getParameter("BOARDVIEWCODENAME"));

			String rootPath = fsResource.getPath();
			String subPath = "board/";

			Object bContentId = boardService.boardUpdateProcess(params);

			MultipartFile uploadFile = multiRequest.getFile("uploadFile");
			if(uploadFile != null && uploadFile.isEmpty() == false) {
				HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
				String file_path =  String.valueOf(fileMap.get("fileFullPath"));
				String file_name =  String.valueOf(fileMap.get("fileName"));

				params.put("FILE_PATH",file_path );
				params.put("FILE_NAME",file_name );
				params.put("REAL_FILE_NAME",uploadFile.getOriginalFilename().toString() );
				boardService.insertBoardFile(params);
			}else{
				params.put("FILE_PATH","" );
				params.put("FILE_NAME","" );
				params.put("REAL_FILE_NAME","");
			}

			//기존에 등록된 게시판글에 대한 직급코드를 지우고 다시 insert
			boardService.deleteTboardTestClass(params);
			logger.info("--------"+ params);
			//게시판에서 선택한 직급 구분 codeStr 배열화
			String [] code_arr = request.getParameter("codeStr").split("/");
			HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
			for (int i = 0; i < code_arr.length; i++) {
				boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARDVIEW_MNG_SEQ"));
				boardTestClassMap.put("BOARD_SEQ", params.get("BOARDVIEW_SEQ"));
				boardTestClassMap.put("CATEGORY_CODE", code_arr[i]);
				boardService.insertBoardCatInfo(boardTestClassMap);
			}

			model.addAttribute("params", params);
			return "forward:/boardNotAnswer/boardNotAnswerList.do";
		}


		/**
		 * @Method Name : boardNotAnswerReplyWrite
		 * @작성일 : 2013. 9.
		 * @Method 설명 : 미응답 게시판글 답변 등록화면
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/boardNotAnswerReplyWrite.do")
		public String boardNotAnswerReplyWrite(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new  HashMap<String, String>();

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("ONOFF_DIV",CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
			params.put("BOARDVIEW_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
			params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
			params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
			params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = "";
			if (sessionMap.get("USER_ROLE").equals("ADMIN")){
				userName = sessionMap.get("USER_NICKNM");
			}else{
				userName = sessionMap.get("USER_NM");
			}
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("REG_ID", userId);
			params.put("UPD_ID", userId);

			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			// 등록된 원본글 data
			HashMap<String, Object> detailView = boardService.getBoardDetail(params);

			model.addAttribute("params",params);
			model.addAttribute("detailView",detailView);
	 		return "/boardNotAnswer/boardNotAnswerReplyWrite";
		}

		/**
		 * @Method Name : boardNotAnswerReplyInsertProcess
		 * @작성일 : 2013. 9.
		 * @Method 설명 : 미응답 게시판 답변글 등록프로세스
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/boardNotAnswerReplyInsertProcess.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String boardNotAnswerReplyInsertProcess(ModelMap model, HttpServletRequest request, MultipartHttpServletRequest multiRequest) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();


			params.put("ONOFF_DIV",CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
			params.put("CREATENAME",CommonUtil.isNull(request.getParameter("CREATENAME"), ""));
			params.put("REG_ID",CommonUtil.isNull(request.getParameter("REG_ID"), ""));
			params.put("NOTICE_TOP_YN",CommonUtil.isNull(request.getParameter("NOTICE_TOP_YN"), ""));
			params.put("OPEN_YN",CommonUtil.isNull(request.getParameter("OPEN_YN"), ""));
			params.put("SUBJECT",CommonUtil.isNull(request.getParameter("SUBJECT"), ""));
			params.put("CONTENT",CommonUtil.isNull(request.getParameter("CONTENT"), ""));
			params.put("BOARD_TYPE",CommonUtil.isNull(request.getParameter("BOARD_TYPE"), ""));
			params.put("BOARD_REPLY",CommonUtil.isNull(request.getParameter("BOARD_REPLY"), ""));

			params.put("currentPage",CommonUtil.isNull(request.getParameter("currentPage"), "1"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("BOARDVIEW_MNG_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
			params.put("BOARDVIEW_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
			params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(request.getParameter("BOARDVIEWPARENT_SEQ"), ""));
			params.put("BOARDVIEWCODENAME",CommonUtil.isNull(request.getParameter("BOARDVIEWCODENAME"), ""));
			
			params.put("BOARD_OFF_TYPE",CommonUtil.isNull(request.getParameter("BOARD_OFF_TYPE"), ""));
			params.put("CAMPUS_YN",CommonUtil.isNull(request.getParameter("CAMPUS_YN"), ""));
	        params.put("CONTENT_NEW",StringUtil.fnStrCut(CommonUtil.isNull(request.getParameter("CONTENT"), ""),4000,false,false));
	       
			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			HashMap<String, String> sessionMap = (HashMap)request.getSession().getAttribute("AdmUserInfo");
			String userName = "";
			if (sessionMap.get("USER_ROLE").equals("ADMIN")){
				userName = sessionMap.get("USER_NICKNM");
			}else{
				userName = sessionMap.get("USER_NM");
			}
			String userId = sessionMap.get("USER_ID");
			params.put("USERNAME", userName);
			params.put("REG_ID", userId);
			params.put("UPD_ID", userId);

			String rootPath = fsResource.getPath();
			String subPath = "board/";

			HashMap<String, String> params2 = new  HashMap<String, String>();
			params2.put("BOARDVIEW_SEQ", params.get("BOARDVIEW_SEQ").toString());
			int isReply = boardService2.getIsReply(params2);

			if(isReply > 0){
				model.addAttribute("params", params);
				model.addAttribute("MESSAGE", "답글이 이미 등록되었습니다.");
				return "/board/message";
			}

			Object bContentId = boardService.boardReplyInsertProcess(params);

			MultipartFile uploadFile = multiRequest.getFile("uploadFile");
			if(uploadFile != null && uploadFile.isEmpty() == false) {
				HashMap<String, Object> fileMap = fileUtil.uploadFile(uploadFile, rootPath, subPath);
				String file_path =  String.valueOf(fileMap.get("fileFullPath"));
				String file_name =  String.valueOf(fileMap.get("fileName"));

				params.put("FILE_PATH",file_path );
				params.put("FILE_NAME",file_name );
				params.put("REAL_FILE_NAME",uploadFile.getOriginalFilename().toString() );
				boardService.insertBoardReplyFile(params);
			}else{
				params.put("FILE_PATH","" );
				params.put("FILE_NAME","" );
				params.put("REAL_FILE_NAME","");
			}



			//원본글의 게시판 코드 리스트 가져오기
			HashMap<String, String> getCodeParam  = new HashMap<String, String>();
			getCodeParam.put("BOARDVIEW_MNG_SEQ", String.valueOf(params.get("BOARDVIEW_MNG_SEQ")));
			getCodeParam.put("BOARDVIEW_SEQ", String.valueOf(params.get("BOARDVIEW_SEQ")));
			List<HashMap<String, String>> codeList = boardService.getboardCodeList(getCodeParam);

			logger.info("--------"+ params);
			//게시판에서 선택한 직급 구분 codeStr 배열화
			HashMap<String, Object> boardTestClassMap = new HashMap<String, Object>();
			for (int i = 0; i < codeList.size(); i++) {
				boardTestClassMap.put("BOARD_MNG_SEQ", params.get("BOARDVIEW_MNG_SEQ"));
				boardTestClassMap.put("BOARD_SEQ", params.get("bContentId"));
				boardTestClassMap.put("CATEGORY_CODE", codeList.get(i).get("CATEGORY_CODE"));

				boardService.insertBoardCatInfo(boardTestClassMap);
			}
			model.addAttribute("params", params);
			return "forward:/boardNotAnswer/boardNotAnswerList.do";
		}


		/**
		 * @Method Name  : getReplyData
		 * @Date         : 2013. 11.
		 * @변경이력      :
		 * @Method 설명      :  해당글이 리플을 가지고 있는지 체크
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/getReplyData.do")
		@ResponseBody
		public HashMap<String, String> getReplyData(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, String> params = new HashMap<String, String>();
			HashMap<String, String> result = new HashMap<String, String>();
			params.put("BOARD_MNG_SEQ", request.getParameter("BOARD_MNG_SEQ"));
			params.put("BOARDVIEW_SEQ", request.getParameter("BOARDVIEW_SEQ"));
			List<HashMap<String, String>> replayData = boardService.getReplyData(params);
			if(replayData.size()  > 0){
				result.put("message","N");
			}else{
				result.put("message","Y");
			}
			return result;
		}
		/**
		 * 게시판 수정 파일 삭제 프로세스
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="/boardNotAnswerDeleteFile.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		@ResponseBody
		public HashMap<String, String> boardNotAnswerDeleteFile(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("FILE_PATH", request.getParameter("FILE_PATH"));
			params.put("BOARDVIEW_SEQ", request.getParameter("BOARD_SEQ"));
			logger.info("param  : ", params);
			//삭제한 파일ID로 게시글 파일 ID 널처리
			//boardService.updateBoardFile(params);
			boardService.Modify_deleteBoardFileData(params);
			String rootPath = fsResource.getPath();
			String filePath = rootPath+params.get("FILE_PATH");
			File file = new File(String.valueOf(rootPath+params.get("FILE_PATH")));
			file.delete();
			HashMap<String, String> result = new HashMap<String, String>();
			try {
				result.put("successmessage", "삭제 완료");
			} catch (Exception e) {
				result.put("errormessage", "삭제 실패");
				return result;
			}
			return result;
		}

		/**
		 * @Method Name : boardNotAnswerDelete
		 * @작성일 : 2013. 11.
		 * @Method 설명 : 게시글 삭제프로세스
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/boardNotAnswerDelete.do")
		@Transactional(readOnly=false,rollbackFor=Exception.class)
		public String boardNotAnswerDelete(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new  HashMap<String, Object>();

			params.put("FILE_PATH",CommonUtil.isNull(String.valueOf(request.getParameter("FILE_PATH")), ""));

			params.put("currentPage",request.getParameter("currentPage"));
			params.put("pageRow",CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

			params.put("ONOFF_DIV",CommonUtil.isNull(String.valueOf(request.getParameter("ONOFF_DIV")), ""));
			params.put("BOARDVIEW_MNG_SEQ",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEW_MNG_SEQ")), ""));
			params.put("BOARDVIEW_SEQ",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEW_SEQ")), ""));
			params.put("BOARDVIEWPARENT_SEQ",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEWPARENT_SEQ")), ""));
			params.put("BOARDVIEWCODENAME",CommonUtil.isNull(String.valueOf(request.getParameter("BOARDVIEWCODENAME")), ""));


			//메뉴 param
			params.put("TOP_MENU_ID", CommonUtil.isNull(request.getParameter("TOP_MENU_ID"), ""));
			params.put("MENUTYPE", CommonUtil.isNull(request.getParameter("MENUTYPE"), ""));
			params.put("L_MENU_NM", CommonUtil.isNull(request.getParameter("L_MENU_NM"), ""));

			model.addAttribute("TOP_MENU_ID", params.get("TOP_MENU_ID"));
			model.addAttribute("MENUTYPE", params.get("MENUTYPE"));
			model.addAttribute("L_MENU_NM", params.get("L_MENU_NM"));
			//메뉴 param

			//원본글
			boardService.deleteBoardData(params);
			boardService.deleteBoardFileData(params);
			boardService.deleteTboardTestClass(params);

			//원본글의 파일존재 여부에따라
			if(String.valueOf(params.get("FILE_PATH")) != null  && String.valueOf(params.get("FILE_PATH")) != ""){
				String rootPath = fsResource.getPath();
				String filePath = String.valueOf(rootPath+params.get("FILE_PATH"));
				//파일 삭제
					File file = new File(filePath);
					file.delete();
			}
			//원본글 end
			model.addAttribute("params", params);
			return "forward:/boardNotAnswer/boardNotAnswerList.do";
		}
		
		/**
		 * @Method Name  : updateBoardReply
		 * @Date         : 2016. 11.
		 * @변경이력      :
		 * @Method 설명      :  미응답게시판 처리상태 변경
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		*/
		@RequestMapping(value="/updateBoardReply.do")
		@ResponseBody
		public HashMap<String, String> updateBoardReply(ModelMap model, HttpServletRequest request) throws Exception {
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, String> returnMsg = new HashMap<String, String>();
			params.put("BOARDVIEW_MNG_SEQ", CommonUtil.isNull(request.getParameter("BOARDVIEW_MNG_SEQ"), ""));
			params.put("BOARDVIEW_SEQ", CommonUtil.isNull(request.getParameter("BOARDVIEW_SEQ"), ""));
			params.put("BOARD_REPLY", CommonUtil.isNull(request.getParameter("BOARD_REPLY"), ""));
			boardService.updateBoardReply(params);
			returnMsg.put("resultmsg", params.get("BOARD_REPLY").toString());
			return returnMsg;
		}
}
