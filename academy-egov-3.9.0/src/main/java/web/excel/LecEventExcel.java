package web.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import web.util.CommonUtil;
import web.util.MafUtil;

public class LecEventExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nm = (String) model.get("nm");
		String cd = (String) model.get("cd");

		String tm = CommonUtil.getCurrentDateTime().substring(0, 8) ;

		String excelName = nm + "_" + tm;
		HSSFSheet sheet = workbook.createSheet("명단");

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
		cell.setCellValue("아이디");
		cell.setCellStyle(style2);
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell.setCellStyle(style2);
		cell = row.createCell(2);
		cell.setCellValue("전화번호");
		cell.setCellStyle(style2);
		if(cd.equals("53")){
			cell = row.createCell(3);
			cell.setCellValue("근무기관명");
			cell.setCellStyle(style2);
			cell = row.createCell(4);
			cell.setCellValue("군별");
			cell.setCellStyle(style2);
			cell = row.createCell(5);
			cell.setCellValue("계급");
			cell.setCellStyle(style2);
			cell = row.createCell(6);
			cell.setCellValue("군번");
			cell.setCellStyle(style2);
			cell = row.createCell(7);
			cell.setCellValue("가입경로");
			cell.setCellStyle(style2);
		}else if (cd.equals("70")){
			cell = row.createCell(3);
			cell.setCellValue("소속");
			cell.setCellStyle(style2);
			cell = row.createCell(5);
			cell.setCellValue("직위/직급");
			cell.setCellStyle(style2);
		}else{
			cell = row.createCell(3);
			cell.setCellValue("입력정보");
			cell.setCellStyle(style2);
		}
		cell = row.createCell(8);
		cell.setCellValue("승인여부");
		cell.setCellStyle(style2);
		cell = row.createCell(9);
		cell.setCellValue("승인일시");
		cell.setCellStyle(style2);
		cell = row.createCell(10);
		cell.setCellValue("구매여부");
		cell.setCellStyle(style2);
		cell = row.createCell(11);
		cell.setCellValue("구매일시");
		cell.setCellStyle(style2);

		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)model.get("list");

		for(Map<String, Object> item : list) {
			row = sheet.createRow(++rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("USER_ID")));
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("USER_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("PHONE_NO")));
			cell.setCellStyle(style);
			if(cd.equals("53")){
				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_NM")),""));
				cell.setCellStyle(style);
				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_DIV")),""));
				cell.setCellStyle(style);
				cell = row.createCell(5);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_RANK")),""));
				cell.setCellStyle(style);
				cell = row.createCell(6);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_NO")),""));
				cell.setCellStyle(style);
				cell = row.createCell(7);
				cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("EVENT_TXT"),"")));
				cell.setCellStyle(style);
			}else if (cd.equals("70")){
				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_NM")),""));
				cell.setCellStyle(style);
				cell = row.createCell(5);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("ARM_RANK")),""));
				cell.setCellStyle(style);
			}else{
				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("EVENT_TXT")),""));
				cell.setCellStyle(style);
			}
			cell = row.createCell(8);
			cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("IS_CHK")),""));
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("REG_DD")),""));
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue(CommonUtil.isNull(String.valueOf(item.get("IS_BUY")),""));
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue(String.valueOf(MafUtil.nvl(item.get("BUY_DD"),"")));
			cell.setCellStyle(style);
		}

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

