package maf.web.mvc.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import maf.exception.MafException;
import maf.web.mvc.beans.SimpleResult;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafActionSupport {
	private final static Log logger = LogFactory.getLog(MafActionSupport.class);

	/**
	 * ViewInfo Config »ý¼º 
	 * @param viewInfoMap
	 * @param result
	 * @param _req
	 * @param _res
	 * @return
	 */
	public static ViewInfoConfig getViewInfoConfig(ViewInfoMap viewInfoMap, SimpleResult result, HttpServletRequest _req,
			HttpServletResponse _res) throws MafException {
		
		ViewInfoConfig vc = viewInfoMap.getViewInfoConfig(result.getForward());
		vc.setFilename(result.getFilename());
		if (vc == null) {

			logger.error(result.getForward() + " not found");
		}
		if (result.isDownload()) {
			logger.debug("download");
			_res.setContentType("application/x-msdownload");
			_res.setHeader("Content-Disposition", "attachment;filename=\"" + result.getFilename() + " \"");
		}
		if (!result.isUseTemplate()) {
			vc.setTemplateName(null);
		}
		if (result.isCache()) {
			_res.setHeader("Cache-Control", "no-cache, must-revalidate");
			_res.setHeader("Pragma", "no-cache");
			_res.setHeader("Expires", "0");
		}
		if (result.isSuccess()) {
			Set set = result.entrySet();
			if (set != null) {
				Iterator i = set.iterator();
				if (i != null) {
					while (i.hasNext()) {
						Map.Entry me = (Map.Entry) i.next();
						_req.setAttribute((String) me.getKey(), me.getValue());
					}
				}
				i = null;
			}

			set = null;

			if (result.haveMessage()) {
				_req.setAttribute("message", result.getMessage(_req));
			}

			if (result.getNext() != null) {
				_req.setAttribute("next", result.getNext());
			}

			if (result.getTarget() != null) {
				_req.setAttribute("target", result.getTarget());
			}

		} else {
			_req.setAttribute("message", result.getMessage(_req));
//			logger.debug("Error : " + result.getMessage(_req));
			result.destroy();
		}
		return vc;
	}
	

}
