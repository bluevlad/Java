package com.willbes.web.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.willbes.platform.util.CommonUtil;
import com.willbes.platform.util.MafUtil;

public class LectureOffListExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tm = CommonUtil.getCurrentDateTime().substring(0, 8) ;

		String excelName = "학원강의_" + tm;
		HSSFSheet sheet = workbook.createSheet("학원강의");

		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.GREEN.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLUE.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBottomBorderColor(HSSFColor.BLACK.index);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setLeftBorderColor(HSSFColor.GREEN.index);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setRightBorderColor(HSSFColor.BLUE.index);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setTopBorderColor(HSSFColor.BLACK.index);
		style2.setFillBackgroundColor(HSSFColor.WHITE.index);
		style2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFRow row = null;
		HSSFCell cell = null;
		
		int rowIndex = 0;
		row = sheet.createRow(rowIndex);

		//타이틀
		cell = row.createCell(0);
		cell.setCellValue("직종");
		cell.setCellStyle(style2);
		cell = row.createCell(1);
		cell.setCellValue("과목");
		cell.setCellStyle(style2);
		cell = row.createCell(2);
		cell.setCellValue("강사명");
		cell.setCellStyle(style2);
		cell = row.createCell(3);
		cell.setCellValue("강의명");
		cell.setCellStyle(style2);
		cell = row.createCell(4);
		cell.setCellValue("학습형태");
		cell.setCellStyle(style2);
		cell = row.createCell(5);
		cell.setCellValue("수강인원");
		cell.setCellStyle(style2);
		cell = row.createCell(6);
		cell.setCellValue("개강일");
		cell.setCellStyle(style2);
		cell = row.createCell(7);
		cell.setCellValue("종료일");
		cell.setCellStyle(style2);
		cell = row.createCell(8);
		cell.setCellValue("등록일");
		cell.setCellStyle(style2);
		cell = row.createCell(9);
		cell.setCellValue("개설여부");
		cell.setCellStyle(style2);
		cell = row.createCell(10);
		cell.setCellValue("수강료");
		cell.setCellStyle(style2);

		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)model.get("list");

		for(Map<String, Object> item : list) {
			row = sheet.createRow(++rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("CATEGORY_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("SUBJECT_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("SUBJECT_TEACHER_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("SUBJECT_TITLE")),""));
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("LEARNING_NM")),""));
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(Long.parseLong(String.valueOf(item.get("OFFERERCNT"))));
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("SUBJECT_OPEN_DATE")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("SUBJECT_END_DATE")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("REG_DT")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(9);
        	if (item.get("SUBJECT_ISUSE").equals('Y')) {
            	cell.setCellValue("개설");
        	}else{
            	cell.setCellValue("폐강");
        	}
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue(Long.parseLong(String.valueOf(item.get("SUBJECT_PRICE"))));
			cell.setCellStyle(style);
		}

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

