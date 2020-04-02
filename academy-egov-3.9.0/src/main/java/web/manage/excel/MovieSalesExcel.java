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

public class MovieSalesExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchStartDate = (String) model.get("searchStartDate");
		String searchEndDate = (String) model.get("searchEndDate");
		String SEARCHTYPE = (String) model.get("SEARCHTYPE");

		String tm = CommonUtil.getCurrentDateTime().substring(8, 14) ;

		String excelName = "";
		if (SEARCHTYPE.equals("D")){
			excelName = "동영상매출_단과_";
		}else if (SEARCHTYPE.equals("P")){
			excelName = "동영상매출_패키지_";
		}else if (SEARCHTYPE.equals("Y")){
			excelName = "동영상매출_프리패스_";
		}else if (SEARCHTYPE.equals("L")){
			excelName = "도서매출_";
		}else{
			excelName = "동영상매출_단과_";
		}
		excelName = excelName + searchStartDate+"-"+searchEndDate + "_" + tm;

		HSSFSheet sheet = workbook.createSheet("동영상매출");

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
		cell.setCellValue("주문번호");
		cell.setCellStyle(style2);
		cell = row.createCell(2);
		cell.setCellValue("고객명");
		cell.setCellStyle(style2);
		cell = row.createCell(3);
		cell.setCellValue("아이디");
		cell.setCellStyle(style2);
		cell = row.createCell(4);
		cell.setCellValue("연락처");
		cell.setCellStyle(style2);
		cell = row.createCell(5);
		cell.setCellValue("주문방법");
		cell.setCellStyle(style2);
		cell = row.createCell(6);
		cell.setCellValue("결제수단");
		cell.setCellStyle(style2);
		cell = row.createCell(7);
		cell.setCellValue("상품구분");
		cell.setCellStyle(style2);
		cell = row.createCell(8);
		cell.setCellValue("상품코드");
		cell.setCellStyle(style2);
		cell = row.createCell(9);
		cell.setCellValue("상품명");
		cell.setCellStyle(style2);
		cell = row.createCell(10);
		cell.setCellValue("강의코드");
		cell.setCellStyle(style2);
		cell = row.createCell(11);
		cell.setCellValue("강의명");
		cell.setCellStyle(style2);
		cell = row.createCell(12);
		cell.setCellValue("교수명");
		cell.setCellStyle(style2);
		cell = row.createCell(13);
		cell.setCellValue("안분율");
		cell.setCellStyle(style2);
		cell = row.createCell(14);
		cell.setCellValue("배분율");
		cell.setCellStyle(style2);
		cell = row.createCell(15);
		cell.setCellValue("결제일");
		cell.setCellStyle(style2);
		cell = row.createCell(16);
		cell.setCellValue("환불일");
		cell.setCellStyle(style2);
		cell = row.createCell(17);
		cell.setCellValue("시작일");
		cell.setCellStyle(style2);
		cell = row.createCell(18);
		cell.setCellValue("종료일");
		cell.setCellStyle(style2);
		cell = row.createCell(19);
		cell.setCellValue("기준일");
		cell.setCellStyle(style2);
		cell = row.createCell(20);
		cell.setCellValue("전체");
		cell.setCellStyle(style2);
		cell = row.createCell(21);
		cell.setCellValue("잔여");
		cell.setCellStyle(style2);
		cell = row.createCell(22);
		cell.setCellValue("기준금액");
		cell.setCellStyle(style2);
		cell = row.createCell(23);
		cell.setCellValue("결제금액");
		cell.setCellStyle(style2);
		cell = row.createCell(24);
		cell.setCellValue("환불금액");
		cell.setCellStyle(style2);
		cell = row.createCell(25);
		cell.setCellValue("전체금액");
		cell.setCellStyle(style2);
		cell = row.createCell(26);
		cell.setCellValue("안분금액");
		cell.setCellStyle(style2);
		cell = row.createCell(27);
		cell.setCellValue("잔여금액");
		cell.setCellStyle(style2);
		cell = row.createCell(28);
		cell.setCellValue("사용금액");
		cell.setCellStyle(style2);

		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)model.get("list");
		int dCount = 1;  
		for(Map<String, Object> item : list) {
			row = sheet.createRow(++rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(dCount));
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("ORDERNO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("USER_NM"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("USER_ID"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("PHONE_NO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("OTYPE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("PAYNAME"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("PTYPE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("MGNTNO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUBJECT_TITLE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LECTURE_NO"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("LEC_TITLE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUBJECT_TEACHER"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("SUB_AVR"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(14);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("PAYMENT"))));
			cell.setCellStyle(style);
			cell = row.createCell(15);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("ORDER_DATE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(16);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("CANCEL_DATE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(17);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("START_DATE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(18);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("END_DATE"),"")));
			cell.setCellStyle(style);
			cell = row.createCell(19);
			cell.setCellValue(MafUtil.getFormatedText(String.valueOf(item.get("SEARCHENDDATE")), "????-??-??"));
			cell.setCellStyle(style);
			cell = row.createCell(20);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("PERIOD"))));
			cell.setCellStyle(style);
			cell = row.createCell(21);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("REST_PERIOD"))));
			cell.setCellStyle(style);
			cell = row.createCell(22);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("SUBJECT_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(23);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(24);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("CANCEL_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(25);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("DIV_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(26);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("SUB_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(27);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("REST_PRICE"))));
			cell.setCellStyle(style);
			cell = row.createCell(28);
			cell.setCellValue(MafUtil.parseLong(String.valueOf(item.get("USE_PRICE"))));
			cell.setCellStyle(style);

			dCount++;
		}

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

