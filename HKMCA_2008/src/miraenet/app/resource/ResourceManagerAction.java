package miraenet.app.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;



import maf.MafConstant;
import maf.MafUtil;
import maf.context.i18n.MafResource;
import maf.context.i18n.MafResourceBundle;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.exception.MafException;
import maf.lib.excel.importer.Util;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.common.jason.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 다국어 메시지 관리 프로그램 
 * @author UBQ
 *
 */
public class ResourceManagerAction extends MafAction {
    final private Log  logger = LogFactory.getLog(ResourceManagerAction.class);
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
		
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
    	chkLogin();
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}
	
	/**
	 * bundle 목록을 Sort 하여 돌려줌 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		MafResourceStore instance = MafResourceStore.getInstance();
		Map bundle = instance.getBundleMap();
		SortedMap smap = new TreeMap(bundle);
		result.setAttribute("bundleMap", smap);
		result.setForward("list");
	}
	
	/**
	 * bundle 목록을 Sort 하여 돌려줌 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		MafResourceStore instance = MafResourceStore.getInstance();
		String bundle = _req.getP("bundle");
		String curLang = _req.getP("lang", LocaleUtil.getLocaleString(_req));
		
		MafResourceBundle resourceBundle = instance.getBundle(bundle);
		List langList = ResourceManagerDB.getLangList(super.oDb);
		
		result.setAttribute("curLang", curLang);
		result.setAttribute("defaultLocale", resourceBundle.getDefaultLocale());
		result.setAttribute("langList", langList);
		result.setAttribute("bundle", bundle);
		result.setAttribute("resourceBundle", resourceBundle);

		result.setForward("view");
	}
	
	/**
	 * 하나의 메시지를 수정 함 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpdateOne(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {

		String bundleName = _req.getP("bundle");
		String key = _req.getP("key");
		String message = _req.getP("message");
		String locale = _req.getP("locale");
		
		MafResourceStore instance = MafResourceStore.getInstance();
		boolean chk = instance.updateOneMessage(bundleName, key, locale, message);
		if(chk) {
			instance.saveResourceConfig(bundleName);
			JSONObject jobj = new JSONObject();
			jobj.put("result", "ok");
			result.setJson(jobj);
		}
		result.setForward("json");
	}
	
	/**
	 * Excel 파일 upload 하여 처리 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUploadExcel(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		boolean isOk = false;
		String bundleName = _req.getP("bundle");
		UploadedFile upfile = _req.getFileParameter("excelfile");
		String locale = _req.getP("lang");
		int COLUMN_KEY = 1; 
		int COLUMN_MESSAGE = 4; 
		
		String key = null;
		String message = null;
		
		MafResourceStore instance = MafResourceStore.getInstance();
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(
			                                         upfile.getFileInputStream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int lastrow = sheet.getLastRowNum();
			// String cellValue = null;
			String stopCellValue = null;
			int nRow = 1;
			int errCol = -1;
			boolean chk = false;
			do {
				try {
					HSSFRow row = sheet.getRow(nRow);
					HSSFCell cell_key = row.getCell((short) COLUMN_KEY);
					HSSFCell cell_message = row.getCell((short) COLUMN_MESSAGE);
					key = getCellValue(cell_key);
					message = getCellValue(cell_message);
					stopCellValue = key;
					if (!MafUtil.empty(stopCellValue) && !MafUtil.empty(message) ) {
						chk= instance.updateOneMessage(bundleName, key, locale,
						                                        message);
					}
					if(!chk) {
						stopCellValue = key + "_" + message;
					}
//					logger.debug(chk + ": " +locale+","+ cell_message.getCellType() + " [" + key + "]" + message );
				} catch (Exception e) {
					logger.debug("["
					        + Util.getColumnName(errCol)
					        + nRow
					        + "]"
					        + e.getClass()
					        + ","
					        + e.getMessage());
				}
				nRow++;
			} while (!MafUtil.empty(stopCellValue) && nRow <= lastrow);
			
			if(chk) {
				isOk = true;
			} else {
				logger.error(nRow +"," + stopCellValue );
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(isOk) {
			isOk = instance.saveResourceConfig(bundleName);
		
		}
		if(isOk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "view");
			next.addParam("bundle", bundleName);
			next.addParam("lang", locale);
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(1)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail"));
		}
		upfile.deleteAllFile();
		
	}
	
	/**
	 * cell의 값을 돌려 준다.
	 * null 이면 "" 을 돌려줌 
	 * @param cell
	 * @param type
	 * @return
	 */
	private String getCellValue(HSSFCell cell) {
		String cellValue = null;
		if(cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getStringCellValue();
			if (MafUtil.empty(cellValue)) {
				cellValue = cell.getNumericCellValue() + "";
			}
			break;
			
			case HSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			
			case HSSFCell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			
		}
		
		if(cellValue == null) {
			cellValue = "";
		}
		cellValue = cellValue.trim();
		if("null".equals(cellValue)) {
			return "";
		} else {
			return cellValue;
		}
	}
	
	public void cmdDownloadExcel(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		List data = new ArrayList();
		MafResourceStore instance = MafResourceStore.getInstance();
		String bundleName = _req.getP("bundle");
		String locale = _req.getP("lang");
		MafResourceBundle bundle = instance.getBundle(bundleName);
		SortedMap sm = new TreeMap(bundle.getResourceMap());
		
		if (sm != null) {
			
			
			Set set = sm.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map rec = new HashMap();
				rec.put("bundle", bundleName);
				Map.Entry me = (Map.Entry) i.next();
				MafResource rl = (MafResource) me.getValue();
				rec.put("key", rl.getKey());
				rec.put("default", rl.getMessage(rl.getDefaultLocale()));
				rec.put("locale", locale);
				rec.put("message", rl.getMessage(locale,false));
				data.add(rec);
			}
		}
		result.setFilename("resource_" + bundleName + ".xls");
		result.setDataSource(data);
		result.setForward("excel");
	}

}
