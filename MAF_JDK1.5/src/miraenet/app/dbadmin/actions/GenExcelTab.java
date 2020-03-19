package miraenet.app.dbadmin.actions;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class GenExcelTab {
	private Log logger = LogFactory.getLog(GenExcelTab.class);

	HSSFWorkbook wb = null;

	HSSFSheet sheet = null;

	Map tableInfo = null;
	List tableList = null;
	private boolean drawed = false;
	public GenExcelTab(Map tableInfo, List tableList) {
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

		HSSFFont fontBold = wb.createFont();
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle cellStyleHeader = wb.createCellStyle();
		cellStyleHeader.setFont(fontBold);
		cellStyleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle cellStyleData = wb.createCellStyle();
		
		if(tableList != null) {
			sheet = wb.createSheet();
			nRow = 0;
			row = sheet.createRow(nRow++);
			wb.setSheetName(0, "Table List", HSSFWorkbook.ENCODING_UTF_16);
			drawCell(row,cellStyleHeader, nCol++,  "Table Name");
			drawCell(row,cellStyleHeader, nCol,  "Comments");
			
			
			for(  int i  = 0; i < tableList.size(); i ++) {
				row = sheet.createRow(nRow++);
				nCol = 0;
				TableBean bean = (TableBean) tableList.get(i);
				drawCell(row,cellStyleData, nCol ++,  bean.getName());
				drawCell(row,cellStyleData, nCol,  bean.getComments());
				
			}
			
		}
		
		if(tableInfo != null) {
			int tableCnt = 1;
			Set set = tableInfo.entrySet();
			String tableName = "";
			//반복자를 얻는다 
			Iterator i = set.iterator();
			//요소들을 출력한다 // .
			while(i.hasNext()) {
				nCol = 0;
				Map.Entry me = (Map.Entry)i.next();
				tableName = (String) me.getKey();
				List columns = (List) me.getValue();
				sheet = wb.createSheet();
				nRow = 0;
				wb.setSheetName(tableCnt ++, tableName, HSSFWorkbook.ENCODING_UTF_16);
				row = sheet.createRow(nRow++);
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
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					drawCell(row,cellStyleData, nCol++, (nR+1)+"");
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					drawCell(row,cellStyleData, nCol++, cBean.getName());
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					drawCell(row,cellStyleData, nCol++, cBean.getType());
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					drawCell(row,cellStyleData, nCol++, cBean.getNullable());
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					drawCell(row,cellStyleData, nCol++, cBean.getData_default());
					cellStyleData.setAlignment(HSSFCellStyle.ALIGN_LEFT);
					drawCell(row,cellStyleData, nCol++, cBean.getComments());
				}
				
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
