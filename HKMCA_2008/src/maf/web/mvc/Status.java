/*
 * 작성된 날짜: 2005-01-20
 * 
 */
package maf.web.mvc;

import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.configuration.CodeInfo;
import maf.context.i18n.MafResourceStore;
import maf.exception.MafException;
import maf.mdb.Mdb;
import maf.web.context.MafActionConfigStore;
import maf.web.context.MStore;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.mvc.pool.MvcResourcePool;

public class Status extends  BaseMafCommand {
    public void process(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {

        request.setAttribute("CONFIGUREAIONS", MafActionConfigStore.getInstance().getCommandConfigMap());
        request.setAttribute("RESOURCE", MafResourceStore.getInstance().getBundleMap());
        CodeInfo codeInfo  = (CodeInfo) MStore.getInstance().getConfig(MStore.KEY_CODE_DET_MAP);
        if (codeInfo != null) {
        	request.setAttribute("CODES", codeInfo.getInternalMap("MAP"));
        }
        String x = request.getRequestURL().toString();
        
        Map charsets = Charset.availableCharsets();
        String encname = null;
        try {
	        FileWriter filewriter = new FileWriter("out");
			encname = filewriter.getEncoding();
			filewriter.close();
        } catch (Exception ignore) {
			//throw new MafException(e);
		}
        request.setAttribute("x",x);
        request.setAttribute("charsets",charsets);
        request.setAttribute("def_charsets",encname);
        request.setAttribute("db_status",Mdb.getStatus());
        
        request.setAttribute("mvcPoolStatus",MvcResourcePool.getStatus());
        result.setForward("status");
    }
}