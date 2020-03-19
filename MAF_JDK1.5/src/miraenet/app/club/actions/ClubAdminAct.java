/*
 * ClubAct.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.lib.Setter;
import maf.logger.Logging;
import maf.web.http.MyHttpServletRequest;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.ClubManager;
import miraenet.app.club.beans.ClubBoardBean;
import miraenet.app.club.beans.ClubMasterBean;
import miraenet.app.club.dao.ClubBoardDB;
import miraenet.app.club.dao.ClubDB;
import miraenet.app.club.dao.ClubMemberDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole 클럽 관련 명령 처리
 */
public class ClubAdminAct extends BaseClubAction {
    static  Log logger = LogFactory.getLog(ClubAdminAct.class);

	public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
		String msg = null;
		String forward_url = null;
		String forward = "message";

		// 페이지 전체 reload
		boolean reload = false;

		String cmd = _req.getParameter("cmd");
		Logging.log(this.getClass(), "Command : " + cmd);
		if (!sessionBean.getUsn().equals(super.mstBean.getC_sysopusn())) {
			result.setSuccess(false,  new ResultMessage(this.MESAGE_BUNDLE_NAME, "auth.noAuth"));
		} else {

			if ("clubinfo_modify".equals(cmd)) {

				try {
					ClubMasterBean club_info = new ClubMasterBean();
					Setter setter = new Setter(club_info, _req);
					setter.setProperty("*");
					club_info.setC_id(club_id);

					ClubDB.clubInfoModify(oDb, club_info);
					msg = "클럽정보 수정되었습니다.";
					reload = true;
					forward_url = "/club/default.do?club_id=" + club_id;
				} catch (Throwable e) {
					forward = "error";
					msg = "클럽정보 수정도중 오류가 발생하였습니다.";
				}

			} else if ("force_secede".equals(cmd)) {
				/*******************************************************************************************************************
				 * 클럽회원 강제탈퇴
				 ******************************************************************************************************************/
				String usn = _req.getP("usn");
				try {
					ClubMemberDB.secede(oDb, club_id, usn);
					forward_url = "/club/member_list.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 강제 탈퇴 하지 못했습니다.";
				}

			} else if ("auth".equals(cmd)) {
				/*******************************************************************************************************************
				 * 회원 승인
				 ******************************************************************************************************************/
				String is_auth = _req.getP("is_auth");
				String usn = _req.getP("usn");
				try {
					ClubMemberDB.memberAuth(oDb, club_id, usn, is_auth);
					if ("T".equals(is_auth)) {
						msg = "회원으로 승인 되었습니다.";
					} else {
						msg = "회원을 승인최소하여 비회원으로 처리하였습니다.";
					}
					forward_url = "/club/member_list.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 승인정보가 변경되지 않았습니다.";
				}

			} else if ("board_use".equals(cmd)) {
				/*******************************************************************************************************************
				 * 게시판 관리 (사용여부 설정)
				 ******************************************************************************************************************/
				String[] is_use = _req.getParameterValues("is_use");
				try {
					ClubBoardDB.boardUsableConf(oDb, is_use, club_id);
					forward_url = "/club/admin/board_conf.jsp?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 게시판 정보가 변경되지 않았습니다.";
				}

				reload = false;

			} else if ("board_add".equals(cmd)) {
				/*******************************************************************************************************************
				 * 게시판 추가
				 ******************************************************************************************************************/

				try {
					ClubBoardBean inputBean = new ClubBoardBean();
					Setter setter = new Setter(inputBean, _req);
					setter.setProperty("*");
					inputBean.setC_id(club_id);

					ClubBoardDB.createBoard(oDb, inputBean, sessionBean.getUsn());
					msg = "게시판이 생성되었습니다.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 게시판이 생성되지 못했습니다.";
				}

			} else if ("board_delete".equals(cmd)) {
				/*******************************************************************************************************************
				 * 게시판 삭제
				 ******************************************************************************************************************/
				String bid = _req.getParameter("bid");
				try {
					ClubBoardDB.deleteBoard(oDb, _req, club_id, bid);
					msg = "게시판이 삭제되었습니다.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 게시판이 삭제되지 못했습니다.";
				}

			} else if ("board_modify".equals(cmd)) {
				/*******************************************************************************************************************
				 * 게시판 수정
				 ******************************************************************************************************************/
				String bid = _req.getParameter("bid");
				String c_title = _req.getParameter("c_title");
				String fl_board_type = _req.getParameter("fl_board_type");
				try {
					ClubBoardDB.modifyBoard(oDb, club_id, bid, fl_board_type, c_title);
					msg = "게시판이 수정 되었습니다.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;

				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 게시판을 수정하지 못했습니다.";
				}

			} else if ("skin_change".equals(cmd)) {
				/*******************************************************************************************************************
				 * 배경이미지 (스킨) 변경
				 ******************************************************************************************************************/
				String c_bgtype = _req.getP("c_bgtype");

				String c_bg_image = _req.getP("c_bg_image");
				UploadedFile uFile1 = _req.getFileParameter("u_bg_file");
				Logging.log(this.getClass(), "c_bgtype =" + c_bgtype + ", " + "c_bg_image =" + c_bg_image + ", ");
				try {
					if ("S".equals(c_bgtype)) {
						ClubDB.changeSkinImage(oDb, club_id, "S", c_bg_image);
						msg = "배경이미지가 변경되었습니다.";
						forward_url = "/club/default.do?club_id=" + club_id;
					} else {
						if (uFile1 != null && uFile1.getFileSize() > 0) {
							ClubManager cm = new ClubManager(club_id);
							cm.saveSkin(_req, uFile1);
							ClubDB.changeSkinImage(oDb, club_id, "U", uFile1.getNewfilename());
							msg = "배경이미지가 변경되었습니다.";
							forward_url = "/club/default.do?club_id=" + club_id;
						} else {
							msg = "사용자 이미지가 전송되지 않았습니다.;";
							forward = "error";
						}
					}

					reload = true;
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 배경이미지를 수정하지 못했습니다.";
				}

			} else if ("club_logo_change".equals(cmd)) {
				/*******************************************************************************************************************
				 * 클럽 로고 변경
				 ******************************************************************************************************************/
				UploadedFile uFile1 = _req.getFileParameter("c_logo_image");
				try {

					if (uFile1 != null && uFile1.getFileSize() > 0) {
						ClubManager cm = new ClubManager(club_id);
						cm.saveLogo(_req, uFile1);
						ClubDB.changeLogoImage(oDb, club_id, uFile1.getNewfilename());
						reload = true;
						msg = "Logo 이미지가 변경되었습니다.";
						forward_url = "/club/default.do?club_id=" + club_id;
					} else {
						msg = "Logo 이미지가 전송되지 않았습니다.;";
						forward = "error";
					}
				} catch (Exception e) {
					forward = "error";
					msg = "시스템 오류로 인해 Logo이미지를 수정하지 못했습니다.";
				}
			} else if ("sendmail".equals(cmd)) {
				cmd_sendmail(club_id);
			} else if ("close_club_f".equals(cmd)) {
				cmd_close_club_f(club_id);
			} else {
				forward = "error";
				msg = "Invalid Command";
			}
			_req.setAttribute("next", forward_url);
			_req.setAttribute("message", msg);
			if (reload) {
				_req.setAttribute("target", "parent");
			}
			result.setForward(forward);
		}
	}

	/*******************************************************************************************************************************
	 * 클럽 완전 폐쇄
	 ******************************************************************************************************************************/
	private void cmd_close_club_f(String club_id) {
		// 클럽게시판 정보
		try {
			ClubManager cm = new ClubManager(club_id);
			cm.deleteClub(oDb);
			result.setNext("/club/closed.jsp");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setSuccess(false, new ResultMessage("club 삭제중 오류가 발생하였습니다."));
		}
	}
	
	/**
	 * 회원들에게 메일 보내기 
	 * @param club_id
	 * @param param
	 */
	private void cmd_sendmail(String club_id) {
		try {
			ClubManager cm = new ClubManager(club_id);
	        String subject = result.getP( "subject" );
	        String contents = result.getP( "content" );		
			int cnt = cm.sendMail(oDb, subject,contents, sessionBean);
	        
			result.setTarget("parent");
			result.setNext("/club/default.do?club_id=" + club_id);
			result.setSuccess(true, new ResultMessage(cnt + "통의 메일이 발송 되었습니다."));
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage("Error"));
		}
	}
}