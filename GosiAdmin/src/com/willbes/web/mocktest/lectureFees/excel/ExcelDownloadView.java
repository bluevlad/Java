package com.willbes.web.mocktest.lectureFees.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelDownloadView extends AbstractExcelView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String excelName = (String) model.get("excelName");
		List<String> headerList = (List<String>)model.get("headerList");
		List<Map<String, String>> dataList = (List<Map<String, String>>)model.get("dataList");
		
		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow header = sheet.createRow(0);
		HSSFCell cell = null;
		
		for(int i = 0; i < headerList.size(); i++) {
			cell = header.createCell(i);
			cell.setCellValue((String)headerList.get(i));
		}
		
		for(int i = 0; i < dataList.size(); i++) {
			Map<String, String> data = (Map<String, String>) dataList.get(i);
			
			HSSFRow row = sheet.createRow((i+1));
			
			for(int j = 0; j < data.size(); j++) {
				cell = row.createCell(j);
				cell.setCellValue(data.get(j));
			}
		}
		
		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}