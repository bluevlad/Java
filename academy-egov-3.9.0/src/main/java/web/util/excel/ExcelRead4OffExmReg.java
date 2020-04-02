package web.util.excel;

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

import web.mocktest.offExamReg.OffExamRegVO;

/**
 * sample 엑셀 reader
 * @author developer
 *
 */
@Component("excelRead4OffExmReg")
public class ExcelRead4OffExmReg {

    public HashMap<String, Object> readExcelXLS(String filePath){
        FileInputStream fis = null;
        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        HashMap<String, Object> retParam = new HashMap<String, Object>();
        ArrayList<OffExamRegVO> params = new ArrayList<OffExamRegVO>();
        OffExamRegVO title = new OffExamRegVO();
        ArrayList<OffExamRegVO> errors = new ArrayList<OffExamRegVO>();
        ArrayList<OffExamRegVO> wdata = new ArrayList<OffExamRegVO>();

        try{
            fis = new FileInputStream(filePath);
            fs = new POIFSFileSystem(fis);
            wb = new HSSFWorkbook(fs);

            //첫번째 시트에만 데이터만 인식
            HSSFSheet sheet = wb.getSheetAt(0);

            //TITLE
            Row header = sheet.getRow(0);
            String[] headerNM = {"","","","",""};
            if(null != header) {
                for(int a = header.getFirstCellNum() ; a < header.getLastCellNum() ; a++ ){
                    Cell cell = header.getCell(a);
                    if(cell != null){
                        String value = cellTypeToString(cell);
                        switch (a) {
                        case 0 : title.setDIV_CD(value); headerNM[0] = value; break; // 고사구분
                        case 1 : title.setSUBJECT_NM(value); headerNM[1] = value; break; // 과목명
                        case 2 : title.setUSER_NM(value); headerNM[2] = value; break; // 성명
                        case 3 : title.setIDENTITY_ID(value); headerNM[3] = value; break; // 수험번호
                        case 4 : title.setMARKINGS(value); headerNM[4] = value; break; // 마킹정보
                        default : break; //
                        }
                    }
                }
                title.setHEADER_NM(headerNM);
            }

            //첫로우
            int firstRow = sheet.getFirstRowNum()+1;
            //데이터가 마지막으로 들어간  ROW
            int phyRow = sheet.getPhysicalNumberOfRows();
            for(int i = firstRow ; i < phyRow ; i++ ){
                OffExamRegVO vo = new OffExamRegVO();

                vo.setERR_YN("N");
                String[] errsYN = {"N","N","N","N","N"};
                String[] errsDESC = {"","","","",""};

                Row row = sheet.getRow(i);

                for(int a = row.getFirstCellNum() ; a < row.getLastCellNum() ; a++ ){
                    Cell cell = row.getCell(a);

                    if(cell != null){
                        String value = cellTypeToString(cell);

                        if(null != value && !"".equals(value)) {
                            errsYN[a] = "N";
                        } else {
                            errsYN[a] = "Y";
                            errsDESC[a] = headerNM[a] + " 데이터 오류";
                        }

                        switch (a) {
                        case 0 : vo.setDIV_CD(value); break; // 고사구분
                        case 1 : vo.setSUBJECT_NM(value); break; // 과목명
                        case 2 : vo.setUSER_NM(value); break; // 성명
                        case 3 : vo.setIDENTITY_ID(value); break; // 수험번호
                        case 4 : vo.setMARKINGS(value); break; // 마킹정보
                        default : break; //
                        }
                    }
                }

                vo.setERRS_YN(errsYN);
                vo.setERRS_DESC(errsDESC);

                params.add(vo);

                boolean flg = false;
                for(int j=0; j < errsYN.length; j++) {
                    if("Y".equals(errsYN[j])) {
                        flg = true;
                        continue;
                    }
                }
                if(!flg) {
                    wdata.add(vo);
                } else {
                    vo.setERR_YN("Y");
                    vo.setERR_DESC("빈데이터");
                    errors.add(vo);
                }
            }

            retParam.put("params", params);
            retParam.put("header", title);
            retParam.put("wdata", wdata);
            retParam.put("errors", errors);

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
        return retParam;
    }

    /**
     *
     * @param val
     * @return
     * @throws Exception
     */
    private static String cellTypeToString(Cell val) {
        String result = "";
        if(val != null){
            switch(val.getCellType()){
            //함수등으로 컬럼이 구성시 결과값에따른 Type이 정해짐
            case HSSFCell.CELL_TYPE_FORMULA :
                switch (val.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    int b = (int) val.getNumericCellValue();
                    result =Integer.toString(b);
                    break;
                    // Double.toString(val.getNumericCellValue());
                case Cell.CELL_TYPE_STRING:
                    result = val.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = Boolean.toString(val.getBooleanCellValue());
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
            case HSSFCell.CELL_TYPE_BOOLEAN :
                result = Boolean.toString(val.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR :
                result =  "";
                break;
            default:
                result =  "";
            }
        }else{
            result =  "";
        }
        return result;
    }
}
