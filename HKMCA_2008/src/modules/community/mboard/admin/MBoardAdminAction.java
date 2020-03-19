/*
 * 작성된 날짜: 2005-02-16
 *  
 */
package modules.community.mboard.admin;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.util.SerializeHashtable;

/**
 * @author bluevlad, 2005-02-16
 * 
 */
public class MBoardAdminAction extends BaseMafCommand {
	protected final String MSG_PAGE = "message";

	protected final String ERROR_MSG_PAGE = "error";

	protected final String MBOARD_PATH = "/mboard";

	protected SerializeHashtable listOp = null;

	protected String listOpStr = "";

	protected String forward = null;

	public synchronized void process(MyHttpServletRequest _req, HttpServletResponse response) throws MafException {
		this.listOpStr = _req.getParameter("LISTOP", "");
		this.listOp = new SerializeHashtable(listOpStr);

		/*
		 * MyMBoardAction을 extends한 모든 Class에서 이메소드를 구현한다.
		 * 
		 */
		doWork(_req, response);
		listOp.put("cmd", "");
		result.setAttribute("listOp", listOp);

	}

	public void doWork(MyHttpServletRequest request, HttpServletResponse response) throws MafException {

	}

}