package com.willbes.web.mocktest.lectureFees.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelDownloadView2  {
	
	// Row 옵션
	 private String[] rowName = {"필드일","필드2","필드3","필드4","필드5","test"};
	 private String[] keys = {"test","test","test","test"};
	 private Integer[] cellSize = {1000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000};
	 private String sheetName = "sheet";
	 private int FIRSTLINE = 0;
	 private HSSFWorkbook workbook = new HSSFWorkbook();
	 private HSSFRow row;
	 private HSSFCell cell;
	 private HSSFSheet sheet = workbook.createSheet(sheetName);
	 
	 // 저장 이름
	 private String fileName = "TEST.xls";
	 //타이틀셀 길이 
	 private Integer Clength = 5000;
	 
	 //xls 엑셀로 작성
	 private HSSFCellStyle styleTitle;
	 private HSSFCellStyle styleContent;
	 
	 // 폰트 색,폰트 지정
	 private short TITLECOLOR = HSSFColor.BLACK.index;
	 private short CONTENTCOLOR = HSSFColor.BLACK.index;
	 private String TITLEFONT = HSSFFont.FONT_ARIAL;
	 private String CONTENTFONT = HSSFFont.FONT_ARIAL;
	 /**
	  * listMap arrayList형 
	  * @param listMap : DB데이터
	  * @param Option : false 기본폭 사용, ture : 커스텀 폭 지정해야함
	 * @return 
	 * @return 
	  * @throws IOException
	  */
	 
	 public HttpServletResponse DbtoExcel(ArrayList<HashMap<String, String>> listMap,Boolean Option,HttpServletResponse res) throws IOException{
	   
	  //타이틀 Font 설정.
	  HSSFFont titlefont = workbook.createFont();
	  titlefont.setFontName(TITLEFONT);
	  titlefont.setColor(TITLECOLOR);
	  
	  //노말 Font 설정.
	  HSSFFont font = workbook.createFont();
	  font.setFontName(CONTENTFONT);
	  font.setColor(CONTENTCOLOR);
	  
	  //제목의 스타일 지정
	  getNormalTitle(workbook);
	  styleTitle.setFont(titlefont);
	  
	  //노말 스타일 지정
	  getNormalContent(workbook);
	  styleContent.setFont(font);
	  
	  // 필드 길이 설정
	  for(int i = 0; i < rowName.length; i++){
	   if(Option){
	    sheet.setColumnWidth(i, cellSize[i]);
	   }else{
	    sheet.setColumnWidth(i, Clength);
	   }
	  }
	  //Row 생성
	  row = sheet.createRow(FIRSTLINE);
	  for(int i = 0; i < rowName.length;i++){
	   row.createCell(i).setCellValue(rowName[i]);
	   row.getCell(i).setCellStyle(styleTitle);
	  }
	  
	  //arrayList로 받은 DB내역 hashmap으로 arrayList(i)를 받아서 각 해당 cell 생성 
	  HashMap<String, String> ListMap = new HashMap<String, String>();
	  for(int i = 0; i<listMap.size(); i++){
	   row = sheet.createRow(i+1+FIRSTLINE);
	   ListMap = (HashMap<String, String>)listMap.get(i);
	   Iterator<String> it = ListMap.keySet().iterator();
	   while(it.hasNext()){
	    it.next();
	    for(int CellCount = 0;CellCount < keys.length; CellCount++){
	     row.createCell(CellCount).setCellValue(String.valueOf(ListMap.get(keys[CellCount].toUpperCase())));
	     row.getCell(CellCount).setCellStyle(styleContent);
	    }
	   }
	  }
	  //fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
	  //res.setContentType("application/x-msexcel");
	   //  res.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
	   //  res.setHeader("Content-Transfer-Encoding", "binary");	
	     //workbook.write(res.getOutputStream());
	     return res;
	 }
	 
	 public void excelDownLoad(HttpServletResponse res) throws IOException{
		 fileName = new String(fileName.getBytes("euc-kr"), "8859_1"); 
		 res.setContentType("application/x-msexcel");
	     res.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
	     res.setHeader("Content-Transfer-Encoding", "binary");	
		 workbook.write(res.getOutputStream());
	 }
	 
	 private void getNormalTitle(HSSFWorkbook workbook){
	  
	  styleTitle = workbook.createCellStyle();
	  styleTitle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); //색상
	  styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
	  styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  
	 }
	 private void getNormalContent(HSSFWorkbook workbook){
	  
	  styleContent = workbook.createCellStyle();
	  styleContent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
	  styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
	 }
	 
	 private void getMargContent(HSSFWorkbook workbook){
	  
	  styleContent = workbook.createCellStyle();
	  styleContent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
	  styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
	 }
	 
	 
	 /**
	  * 셀폭 설정 - 전체 셀
	  * @param Clength
	  */
	 public void setcellWidthEntry(Integer Clength){
	  this.Clength = Clength;
	 }
	 
	 /**
	  * 셀 폭 설정 - 개별 셀
	  * @param cellSize
	  */
	 public void setcellWidthCustom(Integer[] cellSize){
	  this.cellSize = cellSize;
	 }
	 /**
	  * 서버에 저장될 파일명 / 형식 : String
	  * @param fileName
	  */
	 public void setfileName(String fileName){
	  this.fileName = fileName;
	 }
	 
	 /**
	  * 엑셀 상단의 제목 / 형식 : String[]
	  * @param rowName
	  */
	 public void setrowName(String[] rowName){
	  this.rowName = rowName;
	 }
	 
	 /**
	  * DB에서 가저올 필드명 기입 / 형식 : String[]
	  * @param keys
	  */
	 public void setkeys(String[] keys){
	  this.keys = keys;
	 }
	 
	 /**
	  *  병합1
	  * 
	  */
	 @SuppressWarnings("deprecation")
	public void makeMerge(int h,int w,int rowStart,int colStart,int rowEnd,int colEnd,String setText){
	
		 HSSFCellStyle styleContent2 = workbook.createCellStyle();
		 styleContent2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		 styleContent2.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		 styleContent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 styleContent2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 styleContent2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 
		 row = sheet.createRow((short)h);
		 cell = row.createCell((short)w);
	  
		 cell.setCellValue(setText);
		 getMargContent(workbook);
		 cell.setCellStyle(styleContent2);
	     sheet.addMergedRegion(new Region(rowStart,(short)colStart,rowEnd,(short)colEnd));
	 }
	 /**
	  *  병합2
	  * 
	  */
	 @SuppressWarnings("deprecation")
	 public void makeMerge2(int h,int w,int rowStart,int colStart,int rowEnd,int colEnd,String setText){
		 HSSFCellStyle styleContent2 = workbook.createCellStyle();
		 styleContent2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		 styleContent2.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		 styleContent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 styleContent2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 styleContent2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 
		 row = sheet.createRow((short)h);
		 for(int i=0; i<=colEnd; i++)
		 {
			 cell = row.createCell((short)i);
			 cell.setCellStyle(styleContent2);
			 cell.setCellValue(setText);
		 } 
//		 row = sheet.createRow((short)h);
//		 cell = row.createCell((short)w);
//		 cell.setCellValue(setText);
		 
		 getMargContent(workbook);
//		 cell.setCellStyle(styleContent2);
		 sheet.addMergedRegion(new Region(rowStart,(short)colStart,rowEnd,(short)colEnd));
	 }
	 public void setTITLECOLOR(short tITLECOLOR) {
	  TITLECOLOR = tITLECOLOR;
	 }
	 public void setCONTENTCOLOR(short cONTENTCOLOR) {
	  CONTENTCOLOR = cONTENTCOLOR;
	 }
	 public void setTITLEFONT(String tITLEFONT) {
	  TITLEFONT = tITLEFONT;
	 }
	 public void setCONTENTFONT(String cONTENTFONT) {
	  CONTENTFONT = cONTENTFONT;
	 }
	 
	 public HSSFWorkbook getWorkbook(){
	  return this.workbook;
	 }
	 
	 public void setFIRSTLINE(int FIRSTLINE) {
	  this.FIRSTLINE = FIRSTLINE;
	 }
	 
	 public HSSFRow getRow() {
	  return row;
	 }
	 public void setRow(HSSFRow row) {
	  this.row = row;
	 }
	 public HSSFSheet getSheet() {
	  return sheet;
	 }
	 public void setSheet(HSSFSheet sheet) {
	  this.sheet = sheet;
	 }

}