package egovframework.com.academy.exam.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.com.academy.exam.service.ExamBankManageService;
import egovframework.com.academy.exam.service.ExamVO;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import egovframework.rte.fdl.excel.EgovExcelService;

/**
 * 시험문제관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2021.08.00  			rainend          최초 생성
 * </pre>
 */
@Service("examBankManageService")
public class ExamBankManageServiceImpl extends EgovAbstractServiceImpl implements ExamBankManageService {

	@Resource(name="examBankManageDAO")
	private ExamBankManageDAO examBankManageDAO;

	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamBankList(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험문제 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamBankListTotCnt(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankListTotCnt(ExamVO);
	}

	/**
	 * @param bankCd 상세조회대상 시험문제코드
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamBankDetail(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExamBank(ExamVO ExamVO) throws Exception {
		examBankManageDAO.insertExamBank(ExamVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateExamBank(ExamVO ExamVO) throws Exception {
		examBankManageDAO.updateExamBank(ExamVO);
	}

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectExamBankItemlList(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankItemlList(ExamVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험ㅁㄴ제 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectExamBankItemListTotCnt(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankItemListTotCnt(ExamVO);
	}

	/**
	 * @param bankCd 상세조회대상 시험문제코드
	 * @return ExamVO 시험문제 상세정보
	 * @throws Exception
	 */
	@Override
	public ExamVO selectExamBankItemDetail(ExamVO ExamVO) throws Exception {
		return examBankManageDAO.selectExamBankItemDetail(ExamVO);
	}

	/**
	 * @param ExamVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertExamBankItem(ExamVO ExamVO) throws Exception {
		examBankManageDAO.insertExamBankItem(ExamVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateExamBankItem(ExamVO ExamVO) throws Exception {
		examBankManageDAO.updateExamBankItem(ExamVO);
	}
	/*###  엑셀 일괄처리 프로세스 ###*/

	/**
	 * 시험문제 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<ExamVO> selectExamBankListExcel(InputStream inputStream) throws Exception {
		//int annvrsrySheetRowCnt = 0;
		//String xlsFile = null;

		String sBankId = null; //문제은행번호
		String sQCount = null; //지문수
		String sQNum = null; //문제번호
		String sItemTitle = null; //문제제목
		String sPassAns = null; //문제정답

		List<ExamVO> list = new ArrayList<ExamVO>();

		//String sBndtDe = null;
		HSSFWorkbook hssfWB = (HSSFWorkbook) excelZipService.loadWorkbook(inputStream);
		// 엑셀 파일 시트 갯수 확인 sheet = 1
		if (hssfWB.getNumberOfSheets() == 1) {
			HSSFSheet exmaBankSheet = hssfWB.getSheetAt(0); //문제은행 시트 가져오기
			//HSSFRow examBankRow = exmaBankSheet.getRow(1); //문제은행 row 가져오기
			//examBankSheetRowCnt = examBankRow.getPhysicalNumberOfCells(); //기념일 cell Cnt
			int rowsCnt = exmaBankSheet.getPhysicalNumberOfRows(); //행 갯수 가져오기

			//사용자ID	기념일자	양/음 구분	기념일구분	기념일명
			for (int j = 1; j < rowsCnt; j++) { //row 루프
				ExamVO ExamVO = new ExamVO();

				HSSFRow row = exmaBankSheet.getRow(j); //row 가져오기
				if (row != null) {
					//int cells = row.getPhysicalNumberOfCells(); //cell 갯수 가져오기
					HSSFCell cell = null;
					cell = row.getCell(0); //문제은행번호
					if (cell != null) {
						sBankId = (String) cell.getStringCellValue();
					}

					cell = row.getCell(1); //지문수
					if (cell != null) {
						sQCount = (String) cell.getStringCellValue();
					}

					cell = row.getCell(2); //문제번호
					if (cell != null) {
						sQNum = (String) cell.getStringCellValue();
					}

					cell = row.getCell(3); //문제제목
					if (cell != null) {
						sItemTitle = (String) cell.getStringCellValue();
					}

					cell = row.getCell(4); //문제정답
					if (cell != null) {
						sPassAns = (String) cell.getStringCellValue();
					}
					list.add(ExamVO);
				}
			}
		}

		return list;
	}

}
