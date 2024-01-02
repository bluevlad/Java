package com.willbes.platform.util.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * sample 엑셀 reader
 * @author developer
 *
 */
@Component("excelRead")
public class ExcelRead {

    public ArrayList<HashMap<String, String>> readExcelXLS(String filePath){
        FileInputStream fis = null;
        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        ArrayList<HashMap<String, String>> param = new ArrayList<HashMap<String, String>>();

        try{
            fis = new FileInputStream(filePath);
            fs = new POIFSFileSystem(fis);
            wb = new HSSFWorkbook(fs);
            //wb.getSheetName(0);

            //첫번째 시트에만 데이터만 인식
            HSSFSheet sheet = wb.getSheetAt(0);

            //첫로우  TITLE
            int firstRow = sheet.getFirstRowNum()+1;
            //데이터가 마지막으로 들어간  ROW
            int phyRow = sheet.getPhysicalNumberOfRows();
            //Map의 데이터 키로 사용될 첫번째 로우 데이터
            Row subjectRow = sheet.getRow(0);
            HashMap<String,String> p = null;
            for(int i = firstRow ; i < phyRow ; i++ ){
                p = new HashMap<String, String>();
                Row row = sheet.getRow(i);

                //마지막으로 들어간Cell
                //int phyCell = row.getPhysicalNumberOfCells();
                //	        	  p.put("saryoCode", saryoCode);
                //	        	  p.put("regId", regId);
                for(int a = 0 ; a < 14 ; a++ ){
                    //키값으로 사용할 첫번째 row 데이터
                    Cell subCell = subjectRow.getCell(a);
                    Cell cell = row.getCell(a);
                    String num =""+a;
                    if(a<10){
                        num="00"+a;
                    }
                    if(10 <= a && a < 100){
                        num="0"+a;
                    }
                    num =  num.trim();

                    if(cell != null){
                        String key = cellTypeToString(subCell);
                        String value = cellTypeToString(cell);
                        p.put(key, value);
                    }
                }
                param.add(p);
            }
        }catch(Exception e){
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return param;
    }

    /**
     *
     * @param excelFilePath excelFile경로
     * @param sheetName sheet이름
     * @return
     * @throws Exception
     */
    public static HashMap<String,Object> readToMap(String excelFilePath,String sheetName)throws Exception{
        FileInputStream fis = null;
        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        HashMap<String,Object> map=null;

        try{

            fis = new FileInputStream(excelFilePath);
            fs = new POIFSFileSystem(fis);
            wb = new HSSFWorkbook(fs);
            //sheet index로가저올때
            HSSFSheet sheet= wb.getSheetAt(0);

            //sheet 이름으로 가져올때
            //HSSFSheet sheet= wb.getSheet(sheetName);

            //값이 문자계열일때
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);

            //값이 함수로되어있을때
            Row row2 = sheet.getRow(1);
            Cell cell2 = row2.getCell(0);

            //값이 숫자계열일때
            /*
             * Double값으로 나와 #.#등으로 나옴 cast하여 정수값으로 가져옴
             * cellTypeToString 상황에따라 바꿔야한다
             */
            Row row3 = sheet.getRow(2);
            Cell cell3 = row3.getCell(0);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }

    /**
     *
     * @param val
     * @return
     * @throws Exception
     */
    public static String cellTypeToString(Cell val) throws Exception{
        String result = "";
        if(val != null){
            switch(val.getCellType()){
            //함수등으로 컬럼이 구성시 결과값에따른 Type이 정해짐
            case HSSFCell.CELL_TYPE_FORMULA :
                switch (val.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    result = Boolean.toString(val.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    int b = (int) val.getNumericCellValue();
                    result =Integer.toString(b);
                    break;
                    // Double.toString(val.getNumericCellValue());
                case Cell.CELL_TYPE_STRING:
                    result = val.getStringCellValue();
                    break;
                }
                break;
            case HSSFCell.CELL_TYPE_NUMERIC :
                //Double.toString(val.getNumericCellValue());
                int b = (int) val.getNumericCellValue();
                result =Integer.toString(b);
                break;
            case HSSFCell.CELL_TYPE_STRING :
                result = "" + val.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK :
                result = Boolean.toString(val.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR :
                result =  null;
                break;
            default:
                result =  null;
            }
        }else{
            result =  "";
        }
        return result;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ExcelRead.readToMap("E:\\LOG\\Book1.xls", "입력데이타");
    }

}
