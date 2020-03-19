/*
 * BgConf.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import modules.community.club.ClubManager;



/**
 * @author UBQ
 * 
 * 배경화면 관리
 */
public class BgConf extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){

        File skinFileDir = new File( _req.getRealPath( ClubManager.DEFAULT_SKIN_PATH + "/thumb" ) );
        File[] skinList = null;
        List list = new ArrayList();
        if (skinFileDir.isDirectory()) {
            skinList = skinFileDir.listFiles();
            if (skinList != null) {
                for (int i = 0; i < skinList.length - 1; i++) {
                    if(skinList[i].isFile()) {
                        list.add(skinList[i]);
                    }
                }
            }

        }
        result.setAttribute( "skinList", list );
        result.setForward( "bg_conf");
    }

}