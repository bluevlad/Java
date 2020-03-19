package modules.wlc.common.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.web.downloader.HttpFileSender;
import miraenet.app.downloader.CommonDownloadServlet;
import modules.wlc.common.config.WlcConfigSupport;

public class WlcDownloadServlet extends CommonDownloadServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cmd = request.getParameter("cmd");
        String os = request.getHeader("USER_AGENT");

        String fullfileName = "";
        String filePath = "";
        String fileName = request.getParameter("filename");
        String env = MafUtil.nvl(request.getParameter("type"), "webfolder_file");
        String fileExt = MafUtil.nvl(request.getParameter("ext"), "");
        String type = request.getParameter("type");
        if(null != request.getParameter("ext") && !"".equals(request.getParameter("ext"))) {
            filePath = WlcConfigSupport.getProperty(env, "") + "/"+ fileExt;
        } else {
            filePath = WlcConfigSupport.getProperty(env, "");
        }

        if(null == type || "".equals(type)) {
            fullfileName = request.getParameter("file");
            fileName = fullfileName.substring(fullfileName.lastIndexOf("/") + 1);
            filePath = fullfileName.substring(0, fullfileName.lastIndexOf("/"));
        }

        if (os == null) {
            os = request.getHeader("user-agent");
        }

        String fullpath = getServletContext().getRealPath(filePath);
        // fileName = fileName.replaceAll( "%20", " " );
        // fileName = URLDecoder.decode( fileName, "UTF-8" );
//        FileInfoBean fb = new FileInfoBean();
//        fb.setFilename(fileName);

        File file = new File(fullpath, fileName);

        HttpFileSender sm = new HttpFileSender(request, response);
        boolean dnyn = false;
        if ("save".equals(cmd) || "down".equals(cmd)) {
			dnyn = true;
		}
        sm.send(file, fileName, dnyn);
    }

}
