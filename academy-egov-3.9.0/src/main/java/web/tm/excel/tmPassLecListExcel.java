package web.tm.excel;

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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import web.util.CommonUtil;
import web.util.MafUtil;

public class tmPassLecListExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tm = CommonUtil.getCurrentDateTime().substring(8, 14) ;

		String excelName = "프리패스_상품정보" + "_" + tm;

		HSSFSheet sheet = workbook.createSheet("프리패스_상품정보");

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

		HSSFCellStyle stylecomma = workbook.createCellStyle();
		HSSFDataFormat cdf = workbook.createDataFormat();
		stylecomma.setDataFormat(cdf.getFormat("#,##0"));
		stylecomma.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		stylecomma.setBottomBorderColor(HSSFColor.BLACK.index);
		stylecomma.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		stylecomma.setLeftBorderColor(HSSFColor.GREEN.index);
		stylecomma.setBorderRight(HSSFCellStyle.BORDER_THIN);
		stylecomma.setRightBorderColor(HSSFColor.BLUE.index);
		stylecomma.setBorderTop(HSSFCellStyle.BORDER_THIN);
		stylecomma.setTopBorderColor(HSSFColor.BLACK.index);
		
		HSSFRow row = null;
		HSSFCell cell = null;
		
		int rowIndex = 0;
		row = sheet.createRow(rowIndex);

		//타이틀
		cell = row.createCell(0);
		cell.setCellValue("일련번호");
		cell.setCellStyle(style2);
		cell = row.createCell(1);
		cell.setCellValue("강의코드");
		cell.setCellStyle(style2);
		cell = row.createCell(2);
		cell.setCellValue("직종");
		cell.setCellStyle(style2);
		cell = row.createCell(3);
		cell.setCellValue("분류");
		cell.setCellStyle(style2);
		cell = row.createCell(4);
		cell.setCellValue("강의명");
		cell.setCellStyle(style2);
		cell = row.createCell(5);
		cell.setCellValue("수강종료인원");
		cell.setCellStyle(style2);
		cell = row.createCell(6);
		cell.setCellValue("배정인원");
		cell.setCellStyle(style2);

		List<HashMap<String, String>> list = (List<HashMap<String, String>>)model.get("list");
		int dCount = 1;  
		for(Map<String, String> item : list) {
			row = sheet.createRow(++rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(dCount));
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LECCODE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("CATEGORY_NM"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("SUBJECT_PERIOD"))));
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUBJECT_TITLE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("TOT"))));
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("CNT"))));
			cell.setCellStyle(style);

			dCount++;
		}

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

