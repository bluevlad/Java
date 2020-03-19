
package maf.web.mvc.menu.configure;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import maf.lib.calendar.configure.HolidayDigesterException;
import maf.web.mvc.menu.XMLMenu;


public class XMLMenuFactory extends HttpServlet {
//    private Logger logger = Logger.getLogger(XMLMenuFactory.class);
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7902418627913302678L;
	public void init(ServletConfig config) throws ServletException {
        String configFilePath = null;
        configFilePath = config.getInitParameter("configFile");
        configFilePath = config.getServletContext().getRealPath(configFilePath);
        configure(configFilePath);
        
    }
    private   synchronized  void configure( String configFilePath) throws ServletException{
        
        try {
            //File configurationFile = new File(configFilePath);
            long t1 = System.currentTimeMillis();
        	System.out.println(">>>> XMLMenuFactory \n     - [" + configFilePath + "] Configure start!!! " );
            XMLMenu.getInstance().setConfig(XMLMenuDigester.digest(configFilePath));
            System.out.println("<<<< XMLMenuFactory Configured in " + ( System.currentTimeMillis()- t1) + " ms" );

        } catch (HolidayDigesterException ex) {
            maf.logger.Trace.getStackTrace(ex);
            throw new ServletException(ex.getMessage(), ex);
        }
    }
}
