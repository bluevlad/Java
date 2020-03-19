package maf.web.mvc.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import maf.MafUtil;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.view.support.ViewDataSource;

public class JsonViewer extends AbstractViewer {
//	public final static String CONTENTS_TYPE = "text/html; charset=UTF-8";
	public final static String CONTENTS_TYPE = "application/x-json;charset=UTF-8";
	private Log logger = LogFactory.getLog(JsonViewer.class);

	public JsonViewer() {
		super.setContentType(JsonViewer.CONTENTS_TYPE);
	}
	/**
	 * 
	 */
	public void render(ViewInfoConfig viewInfo, HttpServletRequest _req, HttpServletResponse _res) throws Exception {

		List list = (List) _req.getAttribute(ViewerSupport.DATA_SOURCE_KEY);
		Map parameters = (Map) _req.getAttribute(ViewerSupport.PARAM_KEY);
		ViewDataSource ds = new ViewDataSource(list);
		
		//JSONObject jobj=new JSONObject();
		JSONArray jobj = new JSONArray();
		
		int idx = 0;

		while (ds.next()) {
			Map record  = ds.getRecord();
			
			if(record != null) {
				JSONObject item=new JSONObject();
				//	반복자를 얻는다 // 
				Set set = record.entrySet();
				Iterator i = set.iterator();
				// 요소들을 출력한다 // 
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					item.put(MafUtil.getString(me.getKey()), me.getValue());
				}
				jobj.add(item);
				idx++;
			}
		}
		_res.setContentType(JsonViewer.CONTENTS_TYPE);
		_res.setHeader("Cache-Control", "no-cache");
		
		PrintWriter writer = _res.getWriter();
		try {
			writer.print(jobj.toString());
			writer.flush();
		} finally {
			if (writer != null) {
				
					writer.close();
				
			}
		}
//		ServletOutputStream ouputStream = _res.getOutputStream();
//        ouputStream.print(jobj.toString());
//        ouputStream.close();
	}
}