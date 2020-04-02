package web.manage.excel;

import java.text.DecimalFormat;
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

import web.util.CommonUtil;
import web.util.MafUtil;

public class TeacherExcelDownload extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String searchTeacherName = (String) model.get("searchTeacherName");
		String searchStartDate = (String) model.get("searchStartDate");
		String searchEndDate = (String) model.get("searchEndDate");
		
		List<Map<String, Object>> list = (List<Map<String, Object>>)model.get("list");
		List<Map<String, Object>> list2 = (List<Map<String, Object>>)model.get("list2");

		String excelName = searchTeacherName + "강사님의 강사료 정산 내역" + CommonUtil.getCurrentDate();
		HSSFSheet sheet = workbook.createSheet(excelName);
		for(int i = 0; i <= 5; i++) {
			sheet.autoSizeColumn(i);
			if(i == 2) {
				sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 5000);
			} else {
				sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 2000);
			}
			
		}
		
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

		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBottomBorderColor(HSSFColor.BLACK.index);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setLeftBorderColor(HSSFColor.GREEN.index);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setRightBorderColor(HSSFColor.BLUE.index);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setTopBorderColor(HSSFColor.BLACK.index);
		style3.setFillBackgroundColor(HSSFColor.WHITE.index);
		style3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

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
		
		HSSFCellStyle style3comma = workbook.createCellStyle();
		style3comma.setDataFormat(cdf.getFormat("#,##0"));
		style3comma.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3comma.setBottomBorderColor(HSSFColor.BLACK.index);
		style3comma.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3comma.setLeftBorderColor(HSSFColor.GREEN.index);
		style3comma.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3comma.setRightBorderColor(HSSFColor.BLUE.index);
		style3comma.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3comma.setTopBorderColor(HSSFColor.BLACK.index);
		style3comma.setFillBackgroundColor(HSSFColor.WHITE.index);
		style3comma.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style3comma.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		
		HSSFRow row = null;
		HSSFCell cell = null;
		
		int rowIndex = 0;
		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 5; i++) {
			cell = row.createCell(i);
			cell.setCellValue(searchTeacherName + "강사님의 강사료 정산 내역");
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)5));
		}		
		
		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 5; i++) {
			cell = row.createCell(i);
			cell.setCellValue("정산(등록)기간 : " + searchStartDate + "~" + searchEndDate);
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)5));
		}
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("구분");
		cell.setCellStyle(style2);

		cell = row.createCell(1);
		cell.setCellValue("수강자");
		cell.setCellStyle(style2);

		cell = row.createCell(2);
		cell.setCellValue("과목");
		cell.setCellStyle(style2);

		cell = row.createCell(3);
		cell.setCellValue("승인일");
		cell.setCellStyle(style2);

		cell = row.createCell(4);
		cell.setCellValue("금액");
		cell.setCellStyle(style2);

		cell = row.createCell(5);
		cell.setCellValue("입금구분");
		cell.setCellStyle(style2);
		
		DecimalFormat df = new DecimalFormat("#,##0");
		
		double pay110 = 0;
		double pay120 = 0;
		double pay130 = 0;
		double pay100 = 0;
		double pay110_su = 0;
		double pay120_su = 0;
		double pay130_su = 0;
		double total_price = 0;
		double price = 0;
		double etc1 = 0;
		double etc2 = 0;

		for(Map<String, Object> item : list) {
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("LEC_TYPE_CHOICE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("USER_NM")));
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("SUBJECT_TITLE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("ISCONFIRM"),"")));
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("TOTAL_PAY"))));
			cell.setCellStyle(stylecomma);
//			cell.setCellStyle(style);
						
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(item.get("PAYCODE")));
			cell.setCellStyle(style);
			
	        pay110 = pay110 + Double.parseDouble(String.valueOf(item.get("PAY110_PRICE")));
	        pay120 = pay120 + Double.parseDouble(String.valueOf(item.get("PAY120_PRICE")));
	        pay130 = pay130 + Double.parseDouble(String.valueOf(item.get("PAY130_PRICE")));
	        pay100 = pay100 + Double.parseDouble(String.valueOf(item.get("PAY100_PRICE")));
	        
        	pay110_su = pay110_su + Double.parseDouble(String.valueOf(item.get("PAY110_SUSU")));
        	pay120_su = pay120_su + Double.parseDouble(String.valueOf(item.get("PAY120_SUSU")));
        	pay130_su = pay130_su + Double.parseDouble(String.valueOf(item.get("PAY130_SUSU")));

        	total_price = total_price + Double.parseDouble(String.valueOf(item.get("TOTAL_PAY")));
        	if (item.get("TEACHER_PAY") != null) {
        		price = price + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY")));
        	}
        	if (item.get("TEACHER_PAY_TEMP1") != null) {
        		etc1 = etc1 + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY_TEMP1")));
        	}
        	if (item.get("TEACHER_PAY_TEMP2") != null) {
        		etc2 = etc2 + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY_TEMP2")));
        	}
		}
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("소계");
		cell.setCellStyle(style3);
		cell = row.createCell(5);
		cell.setCellValue(list.size() + "명");
		cell.setCellStyle(style3);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("은행입금");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(pay120 + pay130 + pay100);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("신용카드");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(pay110);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("수강료계");
		cell.setCellStyle(style3);
		cell = row.createCell(5);
		cell.setCellValue(total_price);
		cell.setCellStyle(style3comma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("결제 수수료");
		cell = row.createCell(5);
		cell.setCellValue(pay110_su + pay120_su + pay130_su);
		cell.setCellStyle(stylecomma);
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellStyle(style3);
		cell.setCellValue("정산합계");
		cell = row.createCell(5);
		cell.setCellValue(total_price - (pay110_su + pay120_su + pay130_su));
		cell.setCellStyle(style3comma);
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("강사료");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(price);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("원천세");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(etc1);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("주민세");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(etc2);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("지급액");
		cell.setCellStyle(style3);
		cell = row.createCell(5);
		cell.setCellValue(price - (etc1 + etc2));
		cell.setCellStyle(style3comma);

		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 5; i++) {
			cell = row.createCell(i);
			cell.setCellValue("환불자 리스트");
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)5));
		}		
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("구분");
		cell.setCellStyle(style2);

		cell = row.createCell(1);
		cell.setCellValue("수강자");
		cell.setCellStyle(style2);

		cell = row.createCell(2);
		cell.setCellValue("과목");
		cell.setCellStyle(style2);

		cell = row.createCell(3);
		cell.setCellValue("승인일");
		cell.setCellStyle(style2);

		cell = row.createCell(4);
		cell.setCellValue("금액");
		cell.setCellStyle(style2);

		cell = row.createCell(5);
		cell.setCellValue("입금구분");
		cell.setCellStyle(style2);
		
		double r_total_price = 0;
		double r_price = 0;
		double r_etc1 = 0;
		double r_etc2 = 0;

		for(Map<String, Object> item : list2) {
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("LEC_TYPE_CHOICE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("USER_NM")));
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("SUBJECT_TITLE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(item.get("ISCONFIRM")));
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("TOTAL_PAY"))));
			cell.setCellStyle(stylecomma);
			
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(item.get("PAYCODE")));
			cell.setCellStyle(style);
			
			r_total_price = r_total_price + Double.parseDouble(String.valueOf(item.get("TOTAL_PAY")));
			r_price = r_price + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY")));
			r_etc1 = r_etc1 + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY_TEMP1")));
			r_etc2 = r_etc2 + Double.parseDouble(String.valueOf(item.get("TEACHER_PAY_TEMP2")));
		}
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("소계");
		cell.setCellStyle(style3);
		cell = row.createCell(5);
		cell.setCellValue(list2.size() + "명");
		cell.setCellStyle(style3);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("환불 총금액");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(r_total_price);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("환불 강사료");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(r_price);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("원천세");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(r_etc1);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("주민세");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(r_etc2);
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("실환불액");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue(r_price - (r_etc1 + r_etc2));
		cell.setCellStyle(stylecomma);

		row = sheet.createRow(++rowIndex);
		cell = row.createCell(4);
		cell.setCellValue("총 실지급액");
		cell.setCellStyle(style3);
		cell = row.createCell(5);
		cell.setCellValue(price - (etc1 + etc2) + (r_price - (r_etc1 + r_etc2)));
		cell.setCellStyle(style3comma);

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}
