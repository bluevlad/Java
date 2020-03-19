/*
 * Created on 2006. 11. 10
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view.excel;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import maf.MafUtil;
import maf.util.StringUtils;
import maf.web.context.MafCodeUtil;
import maf.web.mvc.view.support.ViewDataSource;
import maf.web.mvc.view.support.ViewerFieldBean;
import maf.web.mvc.view.support.ViewerInfoBean;

public class ExcelDrawer {
	private Log logger = LogFactory.getLog(ExcelDrawer.class);

	HSSFWorkbook wb = null;

	HSSFSheet sheet = null;

	ViewDataSource ds = null;

	Map param = null;
	Locale locale = null;

	private boolean drawed = false;

	ViewerInfoBean info = null;

	Map cacheMap = null;
	public ExcelDrawer(ViewerInfoBean info, ViewDataSource ds, Map param) {
		this.info = info;
		this.ds = ds;
		this.param = param;
		this.cacheMap = new HashMap();
	}
	
	public ExcelDrawer(ViewerInfoBean info, ViewDataSource ds, Map param, Locale locale) {
		this.info = info;
		this.ds = ds;
		this.param = param;
		this.cacheMap = new HashMap();
		this.locale = locale;
	}



	public void write(OutputStream os) throws Exception {
		if (!drawed) {
			this.draw();
		}
		wb.write(os);
	}

	private void draw() throws Exception {
		int nRow = 0;

		if (ds != null) {
			wb = new HSSFWorkbook();
			sheet = wb.createSheet();
			wb.setSheetName(0, "report", HSSFWorkbook.ENCODING_UTF_16);

			HSSFRow row = null;//sheet.createRow(nRow);
			HSSFCell cell = null;//row.createCell((short)0);
			String[] keys = info.getFieldKeySet();
			HSSFFont fontBold = wb.createFont();
			fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			row = sheet.createRow(nRow);
			HSSFCellStyle cellStyleHeader = wb.createCellStyle();
			cellStyleHeader.setFont(fontBold);
			cellStyleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			for (short nCol = 0; nCol < keys.length; nCol++) {
				ViewerFieldBean ib = info.getField(keys[nCol]);
				cell = row.createCell(nCol);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ib.getTitle());
				cell.setCellStyle(cellStyleHeader);
			}

			nRow++;

			while (ds.next()) {
				row = sheet.createRow(nRow);
				for (short nCol = 0; nCol < keys.length; nCol++) {
					ViewerFieldBean ib = info.getField(keys[nCol]);
					cell = row.createCell(nCol);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					HSSFCellStyle cellStyle = wb.createCellStyle();

					
					cellStyle.setAlignment(ib.getAlign());
					cellStyle.setDataFormat(ib.getFormatNo());
					cell.setCellStyle(cellStyle);
					cell.setCellType(ib.getType());
					if(HSSFCell.CELL_TYPE_STRING == ib.getType() ) {
						if(ib.hasCodeGroup()) {
							String v = getStringValue(ds.getFieldValue(ib.getId()));
							cell.setCellValue(MafUtil.nvl(MafCodeUtil.getCodeName(ib.getCodeGroup(), v, locale), v));
						} else {
							cell.setCellValue(getStringValue(ds.getFieldValue(ib.getId())));
						}
					} else {
						setDoubleValue(cell, ds.getFieldValue(ib.getId()));
					}
					
				}
				nRow++;
			}
			drawed = true;
		}
	}
	
	private String getStringValue(Object v) {
		if (v == null) {
			return "";
		} else {
			return v.toString();
		}
	}
	
	/**
	 * type이 숫자형일 경우 내용을 파악한 후 처리 함  
	 * @param cell
	 * @param v
	 */
	private void setDoubleValue(HSSFCell cell, Object v) {
		if (v == null) {
			cell.setCellValue("");
		} else {
			
			if (StringUtils.isNumeric(v.toString())) {
			
				cell.setCellValue(Double.parseDouble(v.toString()));
			} else {

				cell.setCellValue(v.toString());
			}
		}

	}
}
