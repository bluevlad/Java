package com.willbes.web.mocktest.lectureFees.excel;

import java.text.DecimalFormat;
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

public class lectureFeesExcelDownload extends AbstractExcelView {
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String searchTeacherName = (String) model.get("searchTeacherName");
		String searchStartDate = (String) model.get("searchStartDate");
		String searchEndDate = (String) model.get("searchEndDate");
		
		List<Map<String, Object>> list = (List<Map<String, Object>>)model.get("list");
		List<Map<String, Object>> refundlist = (List<Map<String, Object>>)model.get("refundlist");

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
		for(int i = 0; i <= 8; i++) {
			cell = row.createCell(i);
			cell.setCellValue(searchTeacherName + " 강사님의 강사료 정산 내역");
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)8));
		}		
		
		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 8; i++) {
			cell = row.createCell(i);
			cell.setCellValue("정산(등록)기간 : " + searchStartDate + "~" + searchEndDate);
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)8));
		}
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("이름/ID");
		cell.setCellStyle(style2);

		cell = row.createCell(1);
		cell.setCellValue("응시형태");
		cell.setCellStyle(style2);

		cell = row.createCell(2);
		cell.setCellValue("모의고사명");
		cell.setCellStyle(style2);

		cell = row.createCell(3);
		cell.setCellValue("신청일");
		cell.setCellStyle(style2);

		cell = row.createCell(4);
		cell.setCellValue("결제금액");
		cell.setCellStyle(style2);

		cell = row.createCell(5);
		cell.setCellValue("강사료지급율%");
		cell.setCellStyle(style2);

		cell = row.createCell(6);
		cell.setCellValue("입금구분");
		cell.setCellStyle(style2);

		cell = row.createCell(7);
		cell.setCellValue("수수료");
		cell.setCellStyle(style2);

		cell = row.createCell(8);
		cell.setCellValue("강사지급액");
		cell.setCellStyle(style2);
		
		DecimalFormat df = new DecimalFormat("#,##0");
		
        double cardTotal = 0; //카드 입금 총합
        double cashTotal=0; //현금 입금 총합
        double realTimeTotal=0; //계좌 입금 총합
        double vaconTotal=0; //가상계좌 입금 총합

        double cardChargeTotal = 0; //카드 수수료 총합
        double realTimeChargeTotal=0; //계좌이체 수수료 총합
        double vaconChargeTotal=0; //가상계좌이체 수수료 총합

        double supplyMoneyTotal = 0; //공급가액
        double moneyTotal = 0; //정산합계
        double TechTotal = 0; //강사료 합계
        double onechenTotal = 0; //원천세 합계
        double juminTotal = 0; //주민세 합계

        double realMoneyTotal = 0; //실지급액

		for(Map<String, Object> item : list) {
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("USRNAMEID")));
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("EXAMTYPE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("MOCKNAME")));
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(item.get("REG_DT")));
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("MONEY"))));
			cell.setCellStyle(stylecomma);
//			cell.setCellStyle(style);
						
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(item.get("PROPERCENT")));
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(String.valueOf(item.get("PAYMENTPARAM")));
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("COMMITION"))));
			cell.setCellStyle(stylecomma);
//			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("ALLOWANCE"))));
			cell.setCellStyle(stylecomma);
//			cell.setCellStyle(style);
			
            //공급가액
            supplyMoneyTotal = supplyMoneyTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
            //정산
            moneyTotal = moneyTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
            //강사료
            //double temp = Double.parseDouble(map.get("PROPERCENT"))/100; 2015.05.15 사용하는 곳이 없어 주석처리
            TechTotal =   TechTotal +((Integer.parseInt(String.valueOf(item.get("MONEY")))-Integer.parseInt(String.valueOf(item.get("COMMITION"))) )*(Double.parseDouble(item.get("PROPERCENT").toString())/100)) ;
            onechenTotal = onechenTotal + (Integer.parseInt(String.valueOf(item.get("ALLOWANCE"))) * 0.03);
            juminTotal = juminTotal + (Integer.parseInt(String.valueOf(item.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(item.get("PAYMENTTYPE")).equals("카드")) {
                cardTotal=cardTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
                cardChargeTotal=cardChargeTotal+Integer.parseInt(String.valueOf(item.get("COMMITION")));
            }
            else if(String.valueOf(item.get("PAYMENTTYPE")).equals("현금")) {
                cashTotal=cashTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
            }
            else if(String.valueOf(item.get("PAYMENTTYPE")).equals("계좌이체") ){
                realTimeTotal=realTimeTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
                realTimeChargeTotal=realTimeChargeTotal+Integer.parseInt(String.valueOf(item.get("COMMITION")));
                
            }
            else if(String.valueOf(item.get("PAYMENTTYPE")).equals("가상계좌")){
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(item.get("MONEY")));
                vaconChargeTotal=vaconChargeTotal+Integer.parseInt(String.valueOf(item.get("COMMITION")));
            }
		}
        //정산 합계 - 전체 금액에서 수수료를 뺀 값으로 수정 - 2013.10.16
        moneyTotal = moneyTotal -(cardChargeTotal+realTimeChargeTotal+vaconChargeTotal);
        //실지급액
        realMoneyTotal = TechTotal - (onechenTotal+juminTotal);
        //10원단위 절삭
        int int_realMoneyTotal = (int)(realMoneyTotal/100)*100;

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("신용카드입금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(cardTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("가상계좌입금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(vaconChargeTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("계좌이체 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(realTimeTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("현금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(cashTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("공급가액 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(supplyMoneyTotal);
		cell.setCellStyle(style3);
		
		row = sheet.createRow(++rowIndex);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("신용카드 수수료 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(cardChargeTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("가상계좌 수수료 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(vaconChargeTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("계좌이체 수수료 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(realTimeChargeTotal);
		cell.setCellStyle(style3);
		
		row = sheet.createRow(++rowIndex);
	
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("정산합계 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(moneyTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("강사료 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(TechTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("원천세 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(onechenTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("주민세 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(juminTotal);
		cell.setCellStyle(style3);
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("실지급액 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(int_realMoneyTotal);
		cell.setCellStyle(style3);

		row = sheet.createRow(++rowIndex);
		for(int i = 0; i <= 8; i++) {
			cell = row.createCell(i);
			cell.setCellValue("환불자 리스트");
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(rowIndex, (short)0, rowIndex, (short)8));
		}		
		
		row = sheet.createRow(++rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("이름/ID");
		cell.setCellStyle(style2);

		cell = row.createCell(1);
		cell.setCellValue("응시형태");
		cell.setCellStyle(style2);

		cell = row.createCell(2);
		cell.setCellValue("모의고사명");
		cell.setCellStyle(style2);

		cell = row.createCell(3);
		cell.setCellValue("신청일");
		cell.setCellStyle(style2);

		cell = row.createCell(4);
		cell.setCellValue("결제금액");
		cell.setCellStyle(style2);

		cell = row.createCell(5);
		cell.setCellValue("강사료지급률%");
		cell.setCellStyle(style2);

		cell = row.createCell(6);
		cell.setCellValue("입금구분");
		cell.setCellStyle(style2);

		cell = row.createCell(7);
		cell.setCellValue("수수료");
		cell.setCellStyle(style2);

		cell = row.createCell(8);
		cell.setCellValue("강사지급액");
		cell.setCellStyle(style2);
		

        // 환불현황
        double recardTotal = 0; //카드 입금 총합
        double recashTotal=0; //현금 입금 총합
        double rerealTimeTotal=0; //계좌 입금 총합
        double revaconTotal=0; //가상계좌 입금 총합

        double recardChargeTotal = 0; //카드 수수료 총합
        double rerealTimeChargeTotal=0; //계좌이체 수수료 총합
        double revaconChargeTotal=0; //가상계좌이체 수수료 총합

        double remoneyTotal = 0; //정산합계
        double reTechTotal = 0; //강사료 합계
        double reonechenTotal = 0; //원천세 합계
        double rejuminTotal = 0; //주민세 합계

        double rerealMoneyTotal = 0; //실지급액

        for(Map<String, Object> map : refundlist) {
            //정산
            remoneyTotal = remoneyTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            //강사료
            reTechTotal = reTechTotal+Integer.parseInt(String.valueOf(map.get("ALLOWANCE")));
            reonechenTotal = reonechenTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.03);
            rejuminTotal = rejuminTotal + (Integer.parseInt(String.valueOf(map.get("ALLOWANCE"))) * 0.01);

            if(String.valueOf(map.get("PAYMENTTYPE")).equals("카드")) {
                recardTotal=recardTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                recardChargeTotal=recardChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            if(String.valueOf(map.get("PAYMENTTYPE")).equals("현금")) {
                recashTotal=recashTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("계좌이체")){
                rerealTimeTotal=rerealTimeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                rerealTimeChargeTotal=rerealTimeChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
            else if(String.valueOf(map.get("PAYMENTTYPE")).equals("가상계좌")){
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("MONEY")));
                revaconChargeTotal=revaconChargeTotal+Integer.parseInt(String.valueOf(map.get("COMMITION")));
            }
        }
        //실지급액
        rerealMoneyTotal = reTechTotal - (reonechenTotal+rejuminTotal);
        //int int_rerealMoneyTotal = (int)rerealMoneyTotal; 2015.05.15 사용하는 곳이 없어 주석처리

		for(Map<String, Object> item : refundlist) {
			row = sheet.createRow(++rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(String.valueOf(item.get("USRNAMEID")));
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(item.get("EXAMTYPE")));
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(item.get("MOCKNAME")));
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(item.get("REG_DT")));
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(Double.parseDouble(String.valueOf(item.get("MONEY"))));
			cell.setCellStyle(stylecomma);
			
			cell = row.createCell(5);
			cell.setCellValue(String.valueOf(item.get("PROPERCENT")));
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(String.valueOf(item.get("PAYMENTPARAM")));
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue(String.valueOf(item.get("COMMITION")));
			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue(String.valueOf(item.get("ALLOWANCE")));
			cell.setCellStyle(style);
		}
		
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("신용카드입금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(recardTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("가상계좌입금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(revaconTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("계좌이체 입금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(rerealTimeTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("현금 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(recashTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("환불 총금액 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(remoneyTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("강사료 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(reTechTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("원천세 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(reonechenTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("주민세 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(rejuminTotal);
		cell.setCellStyle(style3);

        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("실환불액 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(rerealMoneyTotal);
		cell.setCellStyle(style3);

        int silTotalMomey = (int)((int_realMoneyTotal - (rerealMoneyTotal*-1))/100)*100;
        row = sheet.createRow(++rowIndex);
		cell = row.createCell(7);
		cell.setCellValue("실지급액 : ");
		cell.setCellStyle(style3);
		cell = row.createCell(8);
		cell.setCellValue(silTotalMomey);
		cell.setCellStyle(style3);

		String fileName = excelName + ".xls"; 
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}
}
