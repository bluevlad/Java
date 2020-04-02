package web.manage.excel;

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

public class offReceivedExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchDate = (String) model.get("searchDate");
		String LEC = (String) model.get("LEC");
		String tm = CommonUtil.getCurrentDateTime().substring(8, 14) ;

		String excelName = "";
		
		if (LEC.equals("D")){
			excelName = "경찰학원_단과선수금_";
		}else{
			excelName = "경찰학원_종합반선수금_";
		}
		excelName = excelName + searchDate + "_" + tm;
		
		HSSFSheet sheet = workbook.createSheet("선수금");
		
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
		
		String cellName [] = {"주문번호","이름","아이디","결제수단","상품구분","상품코드","상품명","강의코드","강의명","교수명",
				"결제금액","안분율","안분금액","배분율","배분금액","개강일","종강일","강의일수","잔여일수","잔여금액","사용금액"};
		
        //타이틀
		for (int i=0; i<21; i++){
			cell = row.createCell(i);
			cell.setCellValue(cellName[i]);
			cell.setCellStyle(style2);
		}

		long sub_price = 0;
		long teacher_price = 0;
		long rest_price = 0;
		long use_price = 0;

		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)model.get("list");

		for(Map<String, Object> item : list) {

			row = sheet.createRow(++rowIndex);
			
			sub_price += MafUtil.parseDouble(String.valueOf(item.get("SUB_PRICE")));
			teacher_price += MafUtil.parseDouble(String.valueOf(item.get("TEACHER_PRICE")));
			rest_price += MafUtil.parseDouble(String.valueOf(item.get("REST_PRICE")));
			use_price += MafUtil.parseDouble(String.valueOf(item.get("USE_PRICE")));

			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("ORDERNO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("USER_NM"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("USER_ID"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("PAYNAME"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LEC_TYPE_CHOICE"),"")));
			cell.setCellStyle(style2);
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("MGNTNO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUBJECT_TITLE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LECTURE_NO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LEC_TITLE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUBJECT_TEACHER"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue(MafUtil.parseLong((String.valueOf(item.get("PRICE")))));
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUB_AVR"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("SUB_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("TEACHER_PAYMENT"))));
			cell.setCellStyle(style);
			cell = row.createCell(14);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("TEACHER_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(15);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("MIN_DATE")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(16);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("MAX_DATE")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(17);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LEC_SCHEDULE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(18);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("REST"))));
			cell.setCellStyle(style);
			cell = row.createCell(19);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("REST_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(20);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("USE_PRICE"))));
			cell.setCellStyle(style);

		}

		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 11; i++) {
			cell = row.createCell(i);
			cell.setCellValue("");
			cell.setCellStyle(style);
		}		
		
		cell = row.createCell(12);
		cell.setCellValue(sub_price);
		cell.setCellStyle(stylecomma);
		
		cell = row.createCell(13);
		cell.setCellValue("");
		cell.setCellStyle(style);
		
		cell = row.createCell(14);
		cell.setCellValue(teacher_price);
		cell.setCellStyle(stylecomma);

		for(int i = 15; i <= 18; i++) {
			cell = row.createCell(i);
			cell.setCellValue("");
			cell.setCellStyle(style);
		}		

		cell = row.createCell(19);
		cell.setCellValue(rest_price);
		cell.setCellStyle(stylecomma);

		cell = row.createCell(20);
		cell.setCellValue(use_price);
		cell.setCellStyle(stylecomma);
		
		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

