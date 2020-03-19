package maf.lib.excel.importer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.xml.XMLDigester;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.util.FileUtils;
import maf.util.StringUtils;
import maf.web.context.MafConfig;
import maf.web.exception.MissingParameterException;
import maf.web.http.HttpHeaderSender;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.beans.ResultMessage;

/**
 * Excel file import manager
 * Excelfile import하여 db에 넣어 준다. 
 * @author goindole
 *
 */
public class ExcelImportManager {
	final private Log logger = LogFactory.getLog(getClass());
	final static private int DEFAULT_MAX_ERROR_COUNT = 10;
	private int max_error_cnt = DEFAULT_MAX_ERROR_COUNT;
	final public static String IMPORT_ERROR_VIEW = "excel_import_error";
	private final String digestFile = "/WEB-INF/miraenet/excelUpload/ExcelImportDigestRules.xml";
	private ExcelImportInfo uploadInfo = null;
	private Map param = null;


	public ExcelImportManager(String infoFile, Map commonParameters)
	    throws MafException {
		if (!FileUtils.existFile(MafConfig.getRealPath(infoFile))) {
			throw new MafException("Info File Nor Found");
		}
		try {
			uploadInfo = (ExcelImportInfo) XMLDigester.digest(
			                                                  MafConfig.getRealPath(infoFile),
			                                                  MafConfig.getRealPath(digestFile));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (commonParameters != null) {
			this.param = commonParameters;
		} else {
			this.param = new HashMap();
		}
		
	}

	/**
	 * 첫번째 column은 꼭 차 있어야 함 오류가 낳으면 List에 오류 정보를 담아 보내줌 오류가 없으면 null;
	 * 
	 * @param oDb
	 * @param excel
	 * @return
	 * @throws MafException
	 */
	public ExcelErrorInfo process(MdbDriver oDb, UploadedFile excel) throws MafException {
		ExcelErrorInfo errorInfo = new ExcelErrorInfo(uploadInfo);
		int nRow = uploadInfo.getStartrow();
		int errCol = -1;
		
		try {
//			POIFSFileSystem fs = new POIFSFileSystem(
//			                                         new FileInputStream(
//			                                                             excel
//			                                                                  .getTempFile()));
//			
			POIFSFileSystem fs = new POIFSFileSystem(excel.getFileInputStream());
			
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0); 
			int lastrow = sheet.getLastRowNum();
			// String cellValue = null;
			String stopCellValue = null;
			oDb.setAutoCommit(false);
			String sqls[] = uploadInfo.getSqls();
			do {
				try {
					for (int nCol = 0; nCol < uploadInfo.size(); nCol++) {
						errCol = nCol;
						
						ExcelImportField field = uploadInfo.getExcelUploadField(nCol);
						HSSFRow row = sheet.getRow(nRow);
						HSSFCell cell = row.getCell(field.getCol());
						String cellValue = getCellValue(cell, field.getType());
						param.put(field.getFieldname(), cellValue);
						if (nCol == 0) {
							stopCellValue = cellValue;
						}
						
					}
					errCol = -1;
					if(!MafUtil.empty(stopCellValue)) {
						for (int i = 0; i < sqls.length; i++) {
							oDb.executeUpdate(sqls[i], param);
						}
					}
				} catch (Exception e) {
					logger.debug( "[" + Util.getColumnName(errCol) + nRow + "]"  + e.getClass() +"," + e.getMessage());
					errorInfo.addError(nRow, errCol, e.getMessage(), param);
				}
				nRow++;
			} while (!MafUtil.empty(stopCellValue)
			        && nRow <= lastrow
			        && errorInfo.errorCnt() < this.max_error_cnt);
			if (!errorInfo.isError()) {
				oDb.commit();
			} 
		} catch (Exception e) {
			logger.debug( "[ last row : " + nRow + "]" + e.getMessage()  );
			errorInfo.addError(nRow,-1, e.getMessage(), null);
			
		}
		if (errorInfo.isError()) {
			oDb.rollback();

		}
		oDb.setAutoCommit(true);
		return errorInfo;
	}

	
	/**
	 * Upload 설정 정보를 돌려 준다.
	 * 
	 * @return
	 */
	public final ExcelImportInfo getInfo() {
		return uploadInfo;
	}
	/**
	 * Sample Excel 파일을 다운로드
	 * 
	 */

	public void sampleDownLoader(HttpServletRequest _req, HttpServletResponse _res) {

		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int nRow = 0;
		short nCol = 0;
		wb = new HSSFWorkbook();
		HSSFRow row = null;// sheet.createRow(nRow);
		HSSFFont fontBold = wb.createFont();
		fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyleHeader = wb.createCellStyle();
		cellStyleHeader.setFont(fontBold);
		cellStyleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFCellStyle cellStyleNormal = wb.createCellStyle();
		if (uploadInfo != null) {
			sheet = wb.createSheet();
			wb.setSheetName(0, "Sample Sheet", HSSFWorkbook.ENCODING_UTF_16);
			nRow = 0;
			row = sheet.createRow(nRow++);
			nCol = 0;
			ExcelImportField field = null;
			for (int i = 0; i < uploadInfo.size(); i++) {
				field = uploadInfo.getExcelUploadField(nCol);
//				drawCell(row, cellStyleHeader, nCol++, field.getTitle()
//				        + StringUtils.capsule("(", field.getDesc(), ")"),
//				         ExcelImportField.FIELD_TYPE_STRING);
				drawCell(row, cellStyleHeader, nCol++, field.getTitle(),
					         ExcelImportField.FIELD_TYPE_STRING);				
			}
			
			String sql = uploadInfo.getSampleSql();
			List sampleList = null;
			if(!MafUtil.empty(sql)) {
				MdbDriver oDb = null;
				try{
					oDb = Mdb.getMdbDriver();
					sampleList = oDb.selector(Map.class, sql, this.param);
				} catch (Exception e) {
					logger.debug(e);
		        } finally {
		            if (oDb != null) try {oDb.close();} catch (Exception ex) {}
		            oDb = null;
		        }
			}
			if( sampleList != null) {
				Map record = null;
				for(int i = 0; i < sampleList.size(); i++) {
					row = sheet.createRow(nRow++);
					nCol = 0;
					record = (Map) sampleList.get(i);
					for (int j = 0; j < uploadInfo.size(); j++) {
						field = uploadInfo.getExcelUploadField(nCol);
						drawCell(row, cellStyleNormal, nCol++, MafUtil.getString(record.get(field.getFieldname())), field.getType());
					}
				}
			} else {
				// for Sample from XML
				row = sheet.createRow(nRow++);
				nCol = 0;
				for (int i = 0; i < uploadInfo.size(); i++) {
					field = uploadInfo.getExcelUploadField(nCol);
					drawCell(row, cellStyleNormal, nCol++, field.getSample(), field.getType());
				}
			}
			HttpHeaderSender.setDownLoad(_req, _res, "sample.xls", true, false);
			try {
				ServletOutputStream ouputStream = _res.getOutputStream();
				try {
					wb.write(ouputStream);
					ouputStream.flush();
				} finally {
					if (ouputStream != null) {
						try {
							ouputStream.close();
						} catch (IOException ex) {
						}
					}
				}
			} catch (Exception e) {
				maf.logger.Logging.trace(e);
				// logger.error(e.getMessage());
			}
		} else {
			logger.debug("uploadInfo is null");
		}
	}

	/**
	 * Cell 하나 하나를 그림 
	 * @param row
	 * @param cellStyle
	 * @param nCol
	 * @param text
	 * @param cellType
	 */
	private void drawCell(HSSFRow row, HSSFCellStyle cellStyle, short nCol, String text,
	        String cellType) {
		HSSFCell cell = row.createCell(nCol);
		if (ExcelImportField.FIELD_TYPE_STRING.equals(cellType)) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		} else {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		}
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(text);
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 몇개의 Error까지 보여줄것인지 처리 기본값 (10) 
	 * 1에서 50개 까지 가능 
	 * @param maxError
	 */
	public void setMaxErrorCnt(int maxError) {
		if (maxError <= 0 || maxError >= 50) {
			this.max_error_cnt = this.DEFAULT_MAX_ERROR_COUNT;
		} else {
			this.max_error_cnt = maxError;
		}
	}

	/**
	 * cell의 값을 돌려 준다.
	 * null 이면 "" 을 돌려줌 
	 * @param cell
	 * @param type
	 * @return
	 */
	private String getCellValue(HSSFCell cell, String type) {
		String cellValue = null;
		if(cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				cellValue = cell.getStringCellValue();
				if (MafUtil.empty(cellValue)) {
					cellValue = cell.getNumericCellValue() + "";
				}
				break;
			case HSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(ExcelImportField.FIELD_TYPE_STRING.equals(type) || ExcelImportField.FIELD_TYPE_INTEGER.equals(type)) {
					double d = cell.getNumericCellValue();
					long x = Math.round(d);
//					String t =Double.toString(cell.getNumericCellValue());
					cellValue = x + "";
					
					
				} else {
					cellValue = cell.getNumericCellValue() + "";
				}
				
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
		}
		
		if(cellValue == null) {
			cellValue = "";
		}
		cellValue = cellValue.trim();
		if("null".equals(cellValue)) {
			return "";
		} else {
			return cellValue;
		}
	}
}
