/*
 * ClubAct.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
 * @author goindole Ŭ�� ���� ��� ó��
 */
public class ClubAdminAct extends BaseClubAction {
    static  Log logger = LogFactory.getLog(ClubAdminAct.class);

	public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
		String msg = null;
		String forward_url = null;
		String forward = "message";

		// ������ ��ü reload
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
					msg = "Ŭ������ �����Ǿ����ϴ�.";
					reload = true;
					forward_url = "/club/default.do?club_id=" + club_id;
				} catch (Throwable e) {
					forward = "error";
					msg = "Ŭ������ �������� ������ �߻��Ͽ����ϴ�.";
				}

			} else if ("force_secede".equals(cmd)) {
				/*******************************************************************************************************************
				 * Ŭ��ȸ�� ����Ż��
				 ******************************************************************************************************************/
				String usn = _req.getP("usn");
				try {
					ClubMemberDB.secede(oDb, club_id, usn);
					forward_url = "/club/member_list.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� ���� Ż�� ���� ���߽��ϴ�.";
				}

			} else if ("auth".equals(cmd)) {
				/*******************************************************************************************************************
				 * ȸ�� ����
				 ******************************************************************************************************************/
				String is_auth = _req.getP("is_auth");
				String usn = _req.getP("usn");
				try {
					ClubMemberDB.memberAuth(oDb, club_id, usn, is_auth);
					if ("T".equals(is_auth)) {
						msg = "ȸ������ ���� �Ǿ����ϴ�.";
					} else {
						msg = "ȸ���� �����ּ��Ͽ� ��ȸ������ ó���Ͽ����ϴ�.";
					}
					forward_url = "/club/member_list.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� ���������� ������� �ʾҽ��ϴ�.";
				}

			} else if ("board_use".equals(cmd)) {
				/*******************************************************************************************************************
				 * �Խ��� ���� (��뿩�� ����)
				 ******************************************************************************************************************/
				String[] is_use = _req.getParameterValues("is_use");
				try {
					ClubBoardDB.boardUsableConf(oDb, is_use, club_id);
					forward_url = "/club/admin/board_conf.jsp?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� �Խ��� ������ ������� �ʾҽ��ϴ�.";
				}

				reload = false;

			} else if ("board_add".equals(cmd)) {
				/*******************************************************************************************************************
				 * �Խ��� �߰�
				 ******************************************************************************************************************/

				try {
					ClubBoardBean inputBean = new ClubBoardBean();
					Setter setter = new Setter(inputBean, _req);
					setter.setProperty("*");
					inputBean.setC_id(club_id);

					ClubBoardDB.createBoard(oDb, inputBean, sessionBean.getUsn());
					msg = "�Խ����� �����Ǿ����ϴ�.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� �Խ����� �������� ���߽��ϴ�.";
				}

			} else if ("board_delete".equals(cmd)) {
				/*******************************************************************************************************************
				 * �Խ��� ����
				 ******************************************************************************************************************/
				String bid = _req.getParameter("bid");
				try {
					ClubBoardDB.deleteBoard(oDb, _req, club_id, bid);
					msg = "�Խ����� �����Ǿ����ϴ�.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� �Խ����� �������� ���߽��ϴ�.";
				}

			} else if ("board_modify".equals(cmd)) {
				/*******************************************************************************************************************
				 * �Խ��� ����
				 ******************************************************************************************************************/
				String bid = _req.getParameter("bid");
				String c_title = _req.getParameter("c_title");
				String fl_board_type = _req.getParameter("fl_board_type");
				try {
					ClubBoardDB.modifyBoard(oDb, club_id, bid, fl_board_type, c_title);
					msg = "�Խ����� ���� �Ǿ����ϴ�.";
					forward_url = "/club/board_conf.do?club_id=" + club_id;

				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� �Խ����� �������� ���߽��ϴ�.";
				}

			} else if ("skin_change".equals(cmd)) {
				/*******************************************************************************************************************
				 * ����̹��� (��Ų) ����
				 ******************************************************************************************************************/
				String c_bgtype = _req.getP("c_bgtype");

				String c_bg_image = _req.getP("c_bg_image");
				UploadedFile uFile1 = _req.getFileParameter("u_bg_file");
				Logging.log(this.getClass(), "c_bgtype =" + c_bgtype + ", " + "c_bg_image =" + c_bg_image + ", ");
				try {
					if ("S".equals(c_bgtype)) {
						ClubDB.changeSkinImage(oDb, club_id, "S", c_bg_image);
						msg = "����̹����� ����Ǿ����ϴ�.";
						forward_url = "/club/default.do?club_id=" + club_id;
					} else {
						if (uFile1 != null && uFile1.getFileSize() > 0) {
							ClubManager cm = new ClubManager(club_id);
							cm.saveSkin(_req, uFile1);
							ClubDB.changeSkinImage(oDb, club_id, "U", uFile1.getNewfilename());
							msg = "����̹����� ����Ǿ����ϴ�.";
							forward_url = "/club/default.do?club_id=" + club_id;
						} else {
							msg = "����� �̹����� ���۵��� �ʾҽ��ϴ�.;";
							forward = "error";
						}
					}

					reload = true;
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� ����̹����� �������� ���߽��ϴ�.";
				}

			} else if ("club_logo_change".equals(cmd)) {
				/*******************************************************************************************************************
				 * Ŭ�� �ΰ� ����
				 ******************************************************************************************************************/
				UploadedFile uFile1 = _req.getFileParameter("c_logo_image");
				try {

					if (uFile1 != null && uFile1.getFileSize() > 0) {
						ClubManager cm = new ClubManager(club_id);
						cm.saveLogo(_req, uFile1);
						ClubDB.changeLogoImage(oDb, club_id, uFile1.getNewfilename());
						reload = true;
						msg = "Logo �̹����� ����Ǿ����ϴ�.";
						forward_url = "/club/default.do?club_id=" + club_id;
					} else {
						msg = "Logo �̹����� ���۵��� �ʾҽ��ϴ�.;";
						forward = "error";
					}
				} catch (Exception e) {
					forward = "error";
					msg = "�ý��� ������ ���� Logo�̹����� �������� ���߽��ϴ�.";
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
	 * Ŭ�� ���� ���
	 ******************************************************************************************************************************/
	private void cmd_close_club_f(String club_id) {
		// Ŭ���Խ��� ����
		try {
			ClubManager cm = new ClubManager(club_id);
			cm.deleteClub(oDb);
			result.setNext("/club/closed.jsp");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setSuccess(false, new ResultMessage("club ������ ������ �߻��Ͽ����ϴ�."));
		}
	}
	
	/**
	 * ȸ���鿡�� ���� ������ 
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
			result.setSuccess(true, new ResultMessage(cnt + "���� ������ �߼� �Ǿ����ϴ�."));
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage("Error"));
		}
	}
}