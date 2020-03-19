package maf.web.context.support;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import maf.exception.MafException;


public class MafWebListner {
	private static MafWebListner instance=new MafWebListner();
	private List hlist = null;
	private Log logger = LogFactory.getLog(getClass());
	
	private MafWebListner() {
		hlist = new ArrayList();
	}
	
	private void _process(HttpServletRequest request, HttpServletResponse response) throws MafException {
		
		for(int i =0; i < hlist.size(); i++) {
			BaseMafHandler obj = (BaseMafHandler) hlist.get(i);
			if(obj != null) {
				logger.debug(i + "," + obj.getClass() + " handler is process");
				try {
					obj.doWork(request, response);
				} catch (Exception e) {
					logger.error(obj.getClass() + " : " + e.getMessage());
				}
			} else {
				logger.debug(i + " handler is null");
			}
		}
	}
	
	private void _addHandler(BaseMafHandler object) {
		hlist.add(object);
	}
	
	public static synchronized MafWebListner getInstance() {
        return instance;
    }
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws MafException{
		instance._process(request, response);
	}
	
	public static void addHandler( BaseMafHandler object) {
		instance._addHandler(object);
	}
	
}
