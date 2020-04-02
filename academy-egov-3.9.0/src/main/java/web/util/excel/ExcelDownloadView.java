package web.util.excel;

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

import web.util.MafUtil;

public class ExcelDownloadView extends AbstractExcelView {

    final static int maxRows = 50000;

    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        String excelName = (String) model.get("excelName");
        List<String> headerList = (List<String>)model.get("headerList");
        List<Map<Integer, Object>> dataList = (List<Map<Integer, Object>>)model.get("dataList");

        HSSFSheet sheet = workbook.createSheet(excelName);
        addHeaders(workbook, sheet, headerList);

        HSSFCellStyle style = createStyle(workbook);
        HSSFCellStyle stylecomma = createStyleComma(workbook);
        HSSFCell cell = null;
        for(int rowNum = 0; rowNum < dataList.size(); rowNum++) {
            Map<Integer, Object> data = (Map<Integer, Object>) dataList.get(rowNum);

            if(rowNum > 0 && rowNum%maxRows == 0) {
                sheet = workbook.createSheet(excelName+"-" + (rowNum/maxRows));
                addHeaders(workbook, sheet, headerList);
            }

            HSSFRow row = sheet.createRow((rowNum%maxRows)+1);
            for(int j = 0; j < data.size(); j++) {
                cell = row.createCell(j);
                if(null != data.get(j)) {
                	if (MafUtil.isNumeric(String.valueOf(data.get(j)))){
                		if ("0".equals( String.valueOf(data.get(j)).substring(0, 1) ) && MafUtil.parseInt(String.valueOf(data.get(j))) > 0 ) {
                            cell.setCellValue(String.valueOf(data.get(j)));
                            cell.setCellStyle(style);
                		}else{
                            cell.setCellValue(Double.valueOf(String.valueOf(data.get(j))));
                            cell.setCellStyle(stylecomma);
                		}
                	}else {
                        cell.setCellValue(String.valueOf(data.get(j)));
                        cell.setCellStyle(style);
                	}
                } else {
                    cell.setCellValue("");
                    cell.setCellStyle(style);
                }
            }
        }

        String fileName = excelName + ".xls";
        fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
    }

    private HSSFCellStyle createStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.GREEN.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLUE.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);

        return style;
    }

    private HSSFCellStyle createStyle2(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.GREEN.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLUE.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setFillBackgroundColor(HSSFColor.WHITE.index);
        style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    private HSSFCellStyle createStyleComma(HSSFWorkbook workbook) {
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
        return stylecomma;
   }    
    
    private void addHeaders(HSSFWorkbook workbook, HSSFSheet sheet, List<String> headerList) {
        HSSFRow header = sheet.createRow(0);
        HSSFCellStyle style = createStyle2(workbook);
        HSSFCell cell = null;
        for(int i = 0; i < headerList.size(); i++) {
            cell = header.createCell(i);
            cell.setCellValue((String)headerList.get(i));
            cell.setCellStyle(style);
        }
    }

}