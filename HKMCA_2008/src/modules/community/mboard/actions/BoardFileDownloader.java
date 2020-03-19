/*
 * BoardFileDownloader.java, @ 2005-03-15
 * 
 * Copyright (c) 2004 bluevlad All rights reserved.
 */
package modules.community.mboard.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.context.MafConfig;
import maf.web.downloader.HttpFileSender;
import modules.community.mboard.MBoardManager;

/**
 * @author bluevlad
 * 

 */
public class BoardFileDownloader extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2493335391756488394L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest( request, response );
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest( request, response );
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String path = request.getPathInfo();
            String aPath[] = path.split( "/" );
            
            
            String bid = aPath[1];
            String fid = aPath[aPath.length-1];
            String bidPath = MBoardManager.getBid2BidPath(bid);
           
            String cmd = request.getParameter( "cmd" );
            

//            SessionBean sessionBean = null;

            String realFileName = null;
            String filePath = null;

//            FileInfoBean fb = new FileInfoBean();
            File f = null;

            try {
//                sessionBean = (SessionBean) MySession.getSessionBean( request, response );
                realFileName = getRealFileName(bid, fid);
                if(aPath.length>3) {
                    filePath = MafConfig.getRealPath( MBoardManager.ATT_FILE_PATH + "/" + bidPath + "/thumb" );
                } else {
                    filePath = MafConfig.getRealPath( MBoardManager.ATT_FILE_PATH + "/" + bidPath );
                }
//                Logging.log( this.getClass(), filePath + "/" + realFileName);
            } catch (Throwable e) {
                response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            }
            if(MafUtil.empty(realFileName)) {
                response.sendError( HttpServletResponse.SC_NOT_FOUND );
                
            } else {
	            f = new File( filePath, fid );
	
	            HttpFileSender sm = new HttpFileSender( request, response );
	            boolean dnyn = false;
	            if ("save".equals(cmd) || "down".equals(cmd)) {
					dnyn = true;
				}
	            sm.send( f, realFileName, dnyn );
            }
        } catch (Throwable e) {
            response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        }

    }

    private String getRealFileName( String bid, String fid) {
//    	oDb.setDebug(true);
        String sql = "SELECT  ORI_FILENAME " +
        		"from MBBS_ATTACH " +
        		"where bid = :bid " +
        		"and REAL_FILENAME = :fid ";
        Map param = new HashMap();
        param.put("bid", bid );
        param.put("fid", fid );
        MdbDriver oDb = null;
        try {
        	oDb = Mdb.getMdbDriver();
            return (String) oDb.selectOne( sql, param );
        } catch (Exception e) {
            return null;
        } finally {
			if (oDb != null) {try {oDb.close();} catch (Exception e) {};}
        }
    }
}