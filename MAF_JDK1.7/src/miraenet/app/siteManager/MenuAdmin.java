/*
 * menu_admin.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.MstMenuBean;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.menu.TreeMenu;
import maf.web.session.exception.SessionInvalidatedException;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *  
 */
public class MenuAdmin extends MafAction {
	private Log logger = LogFactory.getLog(MenuAdmin.class);
	String site = null;

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		if(super.sessionBean == null) {
			throw new SessionInvalidatedException();
		}
		this.site = _req.getP( "site", MiConfig.DEFAULT_SITE );
		_req.setAttribute( "site", site );
		
	}

	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        List sites = MstMenuDB.getSimpleSites( oDb );
        result.setAttribute( "sites", sites );
		result.setForward("default");
	}
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        List menus = MstMenuDB.getMenuTree( oDb, this.site );
        
        
        result.setAttribute( "menus", menus );
        result.setForward("tree");
	}	
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String pgid = _req.getParameter( "pgid", "" );
        MstMenuBean item = MstMenuDB.view( oDb, this.site, pgid );
//        List pgAuth = MstMenuDB.getPgAuth(oDb, site, pgid);

//        _req.setAttribute( "pgAuth", pgAuth );
        _req.setAttribute( "item", item );
        _req.setAttribute( "pgid", pgid );
        result.setForward("edit");
        


	}	
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		MstMenuBean mbean = new MstMenuBean();

		int retValue = 0;
        Setter setter = new Setter( mbean, _req );
        setter.setProperty( "*" );
        mbean.setUsn( super.sessionBean.getUsn() );
        mbean.setUpdate_id( super.sessionBean.getUsn() );
        mbean.setSite( _req.getParameter( "site" ) );
        mbean.setAdmin_usn( _req.getIntParameter( "ADMIN_USN" ) );

        String current_pgid = _req.getParameter( "current_pgid" );
        
        retValue = MstMenuDB.update( oDb, mbean, current_pgid );
//        boolean chk=false;
//        if(retValue != 0 ) {
//        	 chk = MstMenuDB.updatePgAuth(oDb, _req, site, mbean.getPgid());
//        }
        if (retValue > 0) {
        	menuReload();
    		String forward_url = "?reload=T&cmd=edit&site=" + mbean.getSite() + "&pgid=" + mbean.getPgid();

            result.setSuccess(true, new ResultMessage("message","update.ok", new Integer(retValue)));
            result.setNext(super.controlAction + forward_url);
        } else {

            result.setSuccess(false, new ResultMessage("message","update.fail"));
        }		

	}
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		MstMenuBean mbean = new MstMenuBean();
		int retValue = 0;
        Setter setter = new Setter( mbean, _req );
        setter.setProperty( "*" );
        mbean.setUsn( super.sessionBean.getUsn() );

        retValue = MstMenuDB.insert( oDb, mbean );
        if (retValue > 0) {
        	menuReload();
    		String forward_url = "?reload=T&cmd=add&site=" + mbean.getSite() + "&pnodeid=" + mbean.getPnodeid();

            result.setSuccess(true, new ResultMessage("message","insert.ok", new Integer(retValue)));
            result.setNext(super.controlAction + forward_url);            
        } else {
            result.setSuccess(false, new ResultMessage("message","insert.fail"));
        }
	}	
	
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String pgid = _req.getParameter( "pgid" );
        String pnodeid = _req.getParameter( "pnodeid" );
        int retValue = 0;

        retValue= MstMenuDB.delete( oDb, site, pgid );
        if (retValue > 0) {
        	menuReload();
        	String forward_url = "?reload=T&cmd=edit&site=" + site + "&pgid=" + pnodeid;
            result.setNext(forward_url);
            result.setSuccess(true, new ResultMessage("message", "delete.ok",  new Integer(retValue)));
        } else {
            result.setSuccess(false, new ResultMessage("message", "delete.fail.existChild"));
        }

	}	
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String pnodeid = _req.getParameter( "pnodeid", "HOME" );
        MstMenuBean item = new MstMenuBean();
        item.setPnodeid( pnodeid );
//        List pgAuth = MstMenuDB.getPgAuth(oDb, site, "HOME" );
        
    
//        _req.setAttribute( "pgAuth", pgAuth );
        _req.setAttribute( "item", item );
        _req.setAttribute( "pgid", pnodeid );

        result.setForward("write");
	}	
	private void menuReload() throws MafException {
        TreeMenu oTM = TreeMenu.getInstance(this.site);
        oTM.DataReset();
	}
	/*
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String forward = null;
        String cmd = _req.getP( "cmd", "default" );
        String site = _req.getP( "site", Config.DEFAULT_SITE );
        String forward_url = null;
        String msg = null;
        int retValue = 0;


        List utype = MstMenuDB.getUtype(oDb);
        _req.setAttribute( "UTYPES", utype );
        
        if ("default".equals( cmd )) {
            List sites = MstMenuDB.getSimpleSites( oDb );
            _req.setAttribute( "sites", sites );
            forward = "default";
        } else if ("tree".equals( cmd )) {
            TreeMenu oTM = TreeMenu.getInstance();
            oTM.DataReset();
            List menus = MstMenuDB.getMenuTree( oDb, site );

            _req.setAttribute( "menus", menus );
            forward = "tree";
        } else if ("edit".equals( cmd )) {
            String pgid = _req.getParameter( "pgid", "" );
            MstMenuBean item = MstMenuDB.view( oDb, site, pgid );
            List pgAuth = MstMenuDB.getPgAuth(oDb, site, pgid);
            _req.setAttribute( "pgAuth", pgAuth );
            _req.setAttribute( "item", item );
            _req.setAttribute( "pgid", pgid );
            forward = "edit";
            
        } else if ("write".equals( cmd )) { // 하위메뉴 추가 폼 
            String pnodeid = _req.getParameter( "pnodeid", "HOME" );
            MstMenuBean item = new MstMenuBean();
            item.setPnodeid( pnodeid );
            List pgAuth = MstMenuDB.getPgAuth(oDb, site, "HOME" );
            _req.setAttribute( "pgAuth", pgAuth );
            _req.setAttribute( "item", item );
            _req.setAttribute( "pgid", pnodeid );
            forward = "write";
        } else if ("insert".equals( cmd )) {
            MstMenuBean mbean = new MstMenuBean();

            Setter setter = new Setter( mbean, _req );
            setter.setProperty( "*" );
            mbean.setUsn( super.sessionBean.getUsn() );

            retValue = MstMenuDB.insert( oDb, mbean );
            if (retValue == 0) {
                msg = "PGID가 중복되었습니다.\\n다시 작성하여 주시기 바랍니다.";
                _req.setAttribute( "message", msg );
                forward = "error";
            } else {
                forward_url = "?reload=T&cmd=write&site=" + mbean.getSite() + "&pnodeid=" + mbean.getPnodeid();
                msg = null; //"새로운 메뉴를 등록하였습니다.";
                _req.setAttribute( "next", this.control_action + forward_url );
                _req.setAttribute( "message", msg );
                forward = "message";
            }

        } else if ("delete".equals( cmd )) {
            String pgid = _req.getParameter( "pgid" );
            String pnodeid = _req.getParameter( "pnodeid" );

            boolean chk = MstMenuDB.delete( oDb, site, pgid );
            if (chk) {
                msg = "하위 메뉴가 있어 삭제하지 못하였습니다.";
            } else {
                forward_url = "?reload=T&cmd=edit&site=" + site + "&pgid=" + pnodeid;
                msg = "삭제하였습니다.";
            }
            _req.setAttribute( "next", this.control_action + forward_url );
            _req.setAttribute( "message", msg );
            forward = "message";
        } else if ("update".equals( cmd )) {
            MstMenuBean mbean = new MstMenuBean();

            Setter setter = new Setter( mbean, _req );
            setter.setProperty( "*" );
            mbean.setUsn( super.sessionBean.getUsn() );
            mbean.setSite( _req.getParameter( "site" ) );
            mbean.setAdmin_usn( _req.getIntParameter( "ADMIN_USN" ) );

            String current_pgid = _req.getParameter( "current_pgid" );
            
            retValue = MstMenuDB.update( oDb, mbean, current_pgid );
            boolean chk = false;
            if(retValue != 0 ) {
            	 chk = MstMenuDB.updatePgAuth(oDb, _req, site, mbean.getPgid());
            }
            if (chk) {
                forward_url = "?reload=T&cmd=edit&site=" + mbean.getSite() + "&pgid=" + mbean.getPgid();
                msg = null; //"수정하였습니다.";
                _req.setAttribute( "next", this.control_action + forward_url );
                _req.setAttribute( "message", msg );
                forward = "message";
            } else {
                msg = "수정 중 오류가 발생하였습니다.";
                _req.setAttribute( "message", msg );
                forward = "error";
            }
            
        }

        return forward;
    }
    */
    
   
}
