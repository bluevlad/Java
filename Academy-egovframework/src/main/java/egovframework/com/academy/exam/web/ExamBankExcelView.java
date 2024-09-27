package egovframework.com.academy.exam.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.academy.exam.service.ExamVO;
import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;

public class ExamBankExcelView extends AbstractPOIExcelView {
 
	private static final Logger LOGGER  = LoggerFactory.getLogger(ExamBankExcelView.class);
 
	@Override
	protected void buildExcelDocument(Map model, XSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        XSSFCell cell = null;
 
        LOGGER.debug("### buildExcelDocument start !!!");
 
        XSSFSheet sheet = wb.createSheet("Exam Bank List");
        sheet.setDefaultColumnWidth(12);
 
        // put text in first cell
        cell = getCell(sheet, 0, 0);
        setText(cell, "Exam Bank List");
 
        // set header information
        setText(getCell(sheet, 2, 0), "bankId");
        setText(getCell(sheet, 2, 1), "ItemId");
        setText(getCell(sheet, 2, 2), "itemTitle");
        setText(getCell(sheet, 2, 3), "answer");
        setText(getCell(sheet, 2, 4), "passAns");
        setText(getCell(sheet, 2, 5), "qPattern");
        setText(getCell(sheet, 2, 6), "ans1");
        setText(getCell(sheet, 2, 7), "ans2");
        setText(getCell(sheet, 2, 8), "ans3");
        setText(getCell(sheet, 2, 9), "ans4");
        setText(getCell(sheet, 2, 10), "ans5");
 
        LOGGER.debug("### buildExcelDocument cast");
 
 
        Map<String, Object> map = (Map<String, Object>) model.get("exmaBankMap");
        List<Object> lists = (List<Object>) map.get("examList");
 
        
        boolean isVO = false;
 
        if (lists.size() > 0) {
        	Object obj = lists.get(0);
        	isVO = obj instanceof ExamVO;
        }
 
        for (int i = 0; i < lists.size(); i++) {
 
        		LOGGER.debug("### buildExcelDocument VO : {} started!!", i);
 
        		ExamVO exambank = (ExamVO) lists.get(i);
 
	            cell = getCell(sheet, 3 + i, 0);
	            setText(cell, exambank.getQueId()+"");
 
	            cell = getCell(sheet, 3 + i, 1);
	            setText(cell, exambank.getQueTitle()+"");
 
	            cell = getCell(sheet, 3 + i, 2);
	            setText(cell, exambank.getQueDesc());
 
	            cell = getCell(sheet, 3 + i, 3);
	            setText(cell, exambank.getAnsDesc());
 
	            cell = getCell(sheet, 3 + i, 4);
	            setText(cell, exambank.getPassAns());
	            
	            cell = getCell(sheet, 3 + i, 5);
	            setText(cell, exambank.getQueType());
	            
	            cell = getCell(sheet, 3 + i, 6);
	            setText(cell, exambank.getAnsView1());
	            
	            cell = getCell(sheet, 3 + i, 7);
	            setText(cell, exambank.getAnsView2());
	            
	            cell = getCell(sheet, 3 + i, 8);
	            setText(cell, exambank.getAnsView3());
	            
	            cell = getCell(sheet, 3 + i, 9);
	            setText(cell, exambank.getAnsView4());
	            
	            cell = getCell(sheet, 3 + i, 10);
	            setText(cell, exambank.getAnsView5());
 
	            LOGGER.debug("### buildExcelDocument VO : {} end!!", i);
 
        }
    }
 
}