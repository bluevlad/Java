package com.willbes.web.manage.excel;

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

public class TeacherAdjustDetailExcel extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String searchTeacherName = (String) model.get("searchTeacherName");
		String searchStartDate = (String) model.get("searchStartDate");
		String searchEndDate = (String) model.get("searchEndDate");
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)model.get("list");
		List<Map<String, Object>> orderlist = (List<Map<String, Object>>)model.get("orderlist");
		List<Map<String, Object>> deductlist = (List<Map<String, Object>>)model.get("deductlist");
		List<Map<String, Object>> deductetclist = (List<Map<String, Object>>)model.get("deductetclist");

		String excelName = searchTeacherName + "강사님의 강사료 정산 내역" + CommonUtil.getCurrentDate();
		HSSFSheet sheet = workbook.createSheet(excelName);
		for(int i = 0; i <= 15; i++) {
			sheet.autoSizeColumn(i);
			if(i == 0) {
				sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 3000);
			} else {
				sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
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
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

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
		for(int i = 0; i <= 3; i++) {
			cell = row.createCell(i);
			cell.setCellValue(searchTeacherName + "강사님의 강사료 정산 내역");
			cell.setCellStyle(style);
		}		
		sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)3));
		
		// 정산 내용 표시
		for(HashMap<String,Object> data : list) {
	
			// 강사명
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("강사명");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(data.get("TEACHER_NM")));
			cell.setCellStyle(style);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 강의기간
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("강의기간");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(data.get("STARTDATE")) + " ~ " + String.valueOf(data.get("ENDDATE"))); 
			cell.setCellStyle(style);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 강의명
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("강의명");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(data.get("SUBJECT_TITLE")));
			cell.setCellStyle(style);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 수강내역
			int ilstart = rowIndex+1;
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("수강내역");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue("번호");
			cell.setCellStyle(style2);
			cell = row.createCell(2);
			cell.setCellValue("주문번호");
			cell.setCellStyle(style2);
			cell = row.createCell(3);
			cell.setCellValue("수강신청일");
			cell.setCellStyle(style2);
			cell = row.createCell(4);
			cell.setCellValue("성명");
			cell.setCellStyle(style2);
			cell = row.createCell(5);
			cell.setCellValue("연락처");
			cell.setCellStyle(style2);
			cell = row.createCell(6);
			cell.setCellValue("수강료");
			cell.setCellStyle(style2);
			cell = row.createCell(7);
			cell.setCellStyle(style2);
			cell = row.createCell(8);
			cell.setCellStyle(style2);
			cell = row.createCell(9);
			cell.setCellStyle(style2);
			cell = row.createCell(10);
			cell.setCellStyle(style2);
			cell = row.createCell(11);
			cell.setCellStyle(style2);
			cell = row.createCell(12);
			cell.setCellStyle(style2);
			cell = row.createCell(13);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(rowIndex, (short)6, rowIndex, (short)11));
			cell = row.createCell(14);
			cell.setCellValue("수강구분");
			cell.setCellStyle(style2);
			cell = row.createCell(15);
			cell.setCellValue("비고");
			cell.setCellStyle(style2);
			cell = row.createCell(16);
			cell.setCellValue("추가할인");
			cell.setCellStyle(style2);
			cell = row.createCell(17);
			cell.setCellValue("세트할인");
			cell.setCellStyle(style2);
			
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)1, rowIndex, (short)1));
			cell = row.createCell(2);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)2, rowIndex, (short)2));
			cell = row.createCell(3);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)3, rowIndex, (short)3));
			cell = row.createCell(4);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)4, rowIndex, (short)4));
			cell = row.createCell(5);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)5, rowIndex, (short)5));
			cell = row.createCell(6);
			cell.setCellValue("현금");
			cell.setCellStyle(style2);
			cell = row.createCell(7);
			cell.setCellValue("카드");
			cell.setCellStyle(style2);
			cell = row.createCell(8);
			cell.setCellValue("예금");
			cell.setCellStyle(style2);
			cell = row.createCell(9);
			cell.setCellValue("가상계좌");
			cell.setCellStyle(style2);
			cell = row.createCell(10);
			cell.setCellValue("계좌이체");
			cell.setCellStyle(style2);
			cell = row.createCell(11);
			cell.setCellValue("카드수수료");
			cell.setCellStyle(style2);
			cell = row.createCell(12);
			cell.setCellValue("환불");
			cell.setCellStyle(style2);
			cell = row.createCell(13);
			cell.setCellValue("합계");
			cell.setCellStyle(style2);
			cell = row.createCell(14);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)14, rowIndex, (short)14));
			cell = row.createCell(15);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)15, rowIndex, (short)15));
			cell = row.createCell(16);
			cell.setCellStyle(style2);
			sheet.addMergedRegion(new Region(ilstart, (short)16, rowIndex, (short)16));
			cell = row.createCell(17);
			cell.setCellStyle(style2);			
			sheet.addMergedRegion(new Region(ilstart, (short)17, rowIndex, (short)17));
			int ipcount = 0;
			for(Map<String, Object> item : orderlist){
				if (Double.parseDouble(String.valueOf(item.get("REFUND"))) > -1 ){++ipcount;}
				row = sheet.createRow(++rowIndex);
				cell = row.createCell(0);
				cell.setCellValue("수강내역");
				cell.setCellStyle(style2);
				cell = row.createCell(1);
				cell.setCellValue(String.valueOf(rowIndex - ilstart -1 ));
				cell.setCellStyle(style);
				cell = row.createCell(2);
				cell.setCellValue(String.valueOf(item.get("ORDERNO")));
				cell.setCellStyle(style);
				cell = row.createCell(3);
				cell.setCellValue(String.valueOf(item.get("REG_DT")));
				cell.setCellStyle(style);
				cell = row.createCell(4);
				cell.setCellValue(String.valueOf(item.get("USER_NM")));
				cell.setCellStyle(style);
				cell = row.createCell(5);
				cell.setCellValue(String.valueOf(item.get("PHONE_NO")));
				cell.setCellStyle(style);
				cell = row.createCell(6);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("PRICE_CASH"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(7);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("PRICE_CARD"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(8);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("PRICE_BANK"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(9);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("PRICE_VACCT"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(10);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("PRICE_DBANK"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(11);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("CHARGE"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(12);
				cell.setCellValue( Double.parseDouble(String.valueOf(item.get("REFUND"))) );
				cell.setCellStyle(stylecomma);
				cell = row.createCell(13);
				double iSum = (Double.parseDouble(String.valueOf(item.get("PRICE_CASH")))+Double.parseDouble(String.valueOf(item.get("PRICE_CARD")))
				+Double.parseDouble(String.valueOf(item.get("PRICE_BANK")))+Double.parseDouble(String.valueOf(item.get("PRICE_VACCT")))
				+Double.parseDouble(String.valueOf(item.get("PRICE_DBANK")))-Double.parseDouble(String.valueOf(item.get("CHARGE"))));
				cell.setCellValue(iSum);
				cell.setCellStyle(stylecomma);
				cell = row.createCell(14);
				cell.setCellValue(String.valueOf(item.get("PTYPE")));
				cell.setCellStyle(style);
				cell = row.createCell(15);
				cell.setCellValue(String.valueOf(item.get("PRICE_DISCOUNT_REASON")));
				cell.setCellStyle(style);
				cell = row.createCell(16);
				cell.setCellValue(String.valueOf(item.get("DISCOUNTPLUS")));
				cell.setCellStyle(style);
				cell = row.createCell(17);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(ilstart, (short)0, rowIndex, (short)0));

			// 수강생수
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("수강생 수");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(ipcount));
			cell.setCellStyle(style);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 수수료공제전 금액
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("수수료 공제전 금액");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
//			cell.setCellValue(String.valueOf(data.get("PREAMOUNT")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("PREAMOUNT"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(stylecomma);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 총합계 금액
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("총합계 금액");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
//			cell.setCellValue(String.valueOf(data.get("AMOUNT")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("AMOUNT"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(stylecomma);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 추가사항
			if (deductlist.isEmpty()) {
				row = sheet.createRow(++rowIndex);
				cell = row.createCell(0);
				cell.setCellValue("추가사항");
				cell.setCellStyle(style2);
				for(int i = 1; i <= 17; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(style);
				}
				sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));

			} else {
				int iaddstart = rowIndex+1;
				for(Map<String, Object> item : deductlist){
					row = sheet.createRow(++rowIndex);
					cell = row.createCell(0);
					cell.setCellValue("추가사항");
					cell.setCellStyle(style2);
					cell = row.createCell(1);
					cell.setCellValue(String.valueOf(item.get("ADDMEMO")));
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell = row.createCell(3);
					cell.setCellStyle(style);
					sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)3));					
					cell = row.createCell(4);
					cell.setCellValue(String.valueOf(item.get("ATYPE"))+String.valueOf(item.get("ADDMONEY")));
					cell.setCellStyle(style);
					for(int i = 5; i <= 17; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(style);
					}
					sheet.addMergedRegion(new Region(rowIndex, (short)5, rowIndex, (short)15));					
				}
				sheet.addMergedRegion(new Region(iaddstart, (short)0, rowIndex, (short)0));
			}
	
			// 대상금액
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("대상금액");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
//			cell.setCellValue(String.valueOf(data.get("TEACHERAMOUNT")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("TEACHERAMOUNT"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 정산기준
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("정산기준");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue("단과반:");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(data.get("CALCUCRITERIA_DTYPE_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(data.get("CALCUCRITERIA_DVALUE")));
			cell.setCellStyle(style);
			for(int i = 4; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)4, rowIndex, (short)15));
			
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("정산기준");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue("종합반:");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(data.get("CALCUCRITERIA_JTYPE_NM")));
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(data.get("CALCUCRITERIA_JVALUE")));
			cell.setCellStyle(style);
			for(int i = 4; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)4, rowIndex, (short)15));
			sheet.addMergedRegion(new Region(rowIndex-1, (short)0, rowIndex, (short)0));
	
			// 정산기준 계산금액
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("정산기준 계산금액");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
//			cell.setCellValue(String.valueOf(data.get("TEACHERPAY")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("TEACHERPAY"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(stylecomma);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 원천징수
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("원천징수");
			cell.setCellStyle(style2);
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(data.get("WITHHOLDRATIO"))+" %");
			cell.setCellStyle(style);
			cell = row.createCell(2);
//			cell.setCellValue(String.valueOf(data.get("WITHHOLDTAX")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("WITHHOLDTAX"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 3; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(stylecomma);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)3, rowIndex, (short)15));
	
			// 기타 추가사항
			if (deductetclist.isEmpty()) {
				row = sheet.createRow(++rowIndex);
				cell = row.createCell(0);
				cell.setCellValue("기타 추가사항");
				cell.setCellStyle(style2);
				for(int i = 1; i <= 17; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(style);
				}
				sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));

			} else {
				int iaddestart = rowIndex+1;
				for(Map<String, Object> item : deductetclist){
					row = sheet.createRow(++rowIndex);
					cell = row.createCell(0);
					cell.setCellValue("기타 추가사항");
					cell.setCellStyle(style2);
					cell = row.createCell(1);
					cell.setCellValue(String.valueOf(item.get("ADDMEMO")));
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellStyle(style);
					cell = row.createCell(3);
					cell.setCellStyle(style);
					sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)3));					
					cell = row.createCell(4);
					cell.setCellValue(String.valueOf(item.get("ATYPE"))+String.valueOf(item.get("ADDMONEY")));
					cell.setCellStyle(style);
					for(int i = 5; i <= 17; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(style);
					}
					sheet.addMergedRegion(new Region(rowIndex, (short)5, rowIndex, (short)15));					
				}
				sheet.addMergedRegion(new Region(iaddestart, (short)0, rowIndex, (short)0));
			}			
			
			// 실지급액
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("실지급액");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
//			cell.setCellValue(String.valueOf(data.get("ADJUSTAMOUNT")));
			cell.setCellValue( Double.parseDouble(String.valueOf(data.get("ADJUSTAMOUNT"))) );
			cell.setCellStyle(stylecomma);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(stylecomma);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
	
			// 최근정산일자
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue("최근정산일자");
			cell.setCellStyle(style2);
	
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(data.get("SETTLE_DT")));
			cell.setCellStyle(style);
			for(int i = 2; i <= 17; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(rowIndex, (short)1, rowIndex, (short)15));
		}

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}

