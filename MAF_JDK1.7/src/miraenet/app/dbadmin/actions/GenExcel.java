package miraenet.app.dbadmin.actions;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import maf.MafUtil;
import maf.util.StringUtils;
import miraenet.app.dbadmin.beans.ColumnBean;
import miraenet.app.dbadmin.beans.TableBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GenExcel {
	private Log logger = LogFactory.getLog(GenExcel.class);

	HSSFWorkbook wb = null;

	HSSFSheet sheet = null;

	Map tableInfo = null;
	List tableList = null;
	private boolean drawed = false;
	public GenExcel(Map tableInfo, List tableList) {
		this.tableList = tableList;
		this.tableInfo = tableInfo;
	}



	public void write(OutputStream os) throws Exception {
		if (!drawed) {
			this.draw();
		}
		wb.write(os);
		
	}

	private void draw() throws Exception {
		int nRow = 0;
		short nCol = 0;
		wb = new HSSFWorkbook();
		HSSFRow row = null;//sheet.createRow(nRow);
		TableBean bean = null;
		HSSFFont fontBold = wb.createFont();
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle cellStyleHeader = wb.createCellStyle();
		cellStyleHeader.setFont(fontBold);
		cellStyleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle cellStyleData = wb.createCellStyle();
		HSSFCellStyle cellStyleDataCenter = wb.createCellStyle();
		cellStyleDataCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		if(tableList != null) {
			sheet = wb.createSheet();
			nRow = 0;
			row = sheet.createRow(nRow++);
			wb.setSheetName(0, "Table List", HSSFWorkbook.ENCODING_UTF_16);
			drawCell(row,cellStyleHeader, nCol++,  "Table Name");
			drawCell(row,cellStyleHeader, nCol,  "Comments");

			for(  int i  = 0; i < tableList.size(); i ++) {
				 bean = (TableBean) tableList.get(i);
			
				if(tableInfo.containsKey(bean.getName())) {
					row = sheet.createRow(nRow++);
					nCol = 0;
					
					drawCell(row,cellStyleData, nCol ++,  bean.getName());
					drawCell(row,cellStyleData, nCol,  bean.getComments());
				}
				
			}
			
		}
		boolean openTableInfoSheet = false;
		for(  int i  = 0; i < tableList.size(); i ++) {
			 bean = (TableBean) tableList.get(i);
			if(tableInfo.containsKey(bean.getName())) {
				if(!openTableInfoSheet) {
					sheet = wb.createSheet();
					nRow = 0;
					wb.setSheetName(1, "Table Scheme", HSSFWorkbook.ENCODING_UTF_16);
					openTableInfoSheet = true;
				}
				String tableName = bean.getName();
				List columns = (List) tableInfo.get(tableName);
				

				row = sheet.createRow(nRow++);
				nCol = 0;
				drawCell(row, cellStyleHeader, nCol++, "Table Name  " );
				drawCell(row, cellStyleHeader, nCol++, tableName);
				row = sheet.createRow(nRow++);
				nCol = 0;
				
				drawCell(row,cellStyleHeader, nCol++, "Col #");
				drawCell(row,cellStyleHeader, nCol++, "Column Name");
				drawCell(row,cellStyleHeader, nCol++, "Data Type");
				drawCell(row,cellStyleHeader, nCol++, "Not Null ?");
				drawCell(row,cellStyleHeader, nCol++, "Data Def.");
				drawCell(row,cellStyleHeader, nCol++, "Comments");
				
				
				for (short nR = 0; nR < columns.size(); nR++) {
					row = sheet.createRow(nRow++);
					ColumnBean cBean = (ColumnBean) columns.get(nR);
					nCol = 0;
					
					drawCell(row,cellStyleDataCenter, nCol++, (nR+1)+"");
					drawCell(row,cellStyleData, nCol++, cBean.getName());
					drawCell(row,cellStyleData, nCol++, cBean.getType());
					drawCell(row,cellStyleDataCenter, nCol++, (cBean.isNotNull()?"F":"T"));
					drawCell(row,cellStyleData, nCol++, cBean.getData_default());
					drawCell(row,cellStyleData, nCol++, cBean.getComments());
				}
				nRow += 2;
			}
			
		}

		drawed = true;
	}

	private void drawCell(HSSFRow row,  HSSFCellStyle cellStyleHeader, short nCol, String text) {
		HSSFCell cell = row.createCell(nCol);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(text);
		cell.setCellStyle(cellStyleHeader);
	}

}
