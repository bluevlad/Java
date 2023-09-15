package egovframework.com.academy.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.survey.service.SurveyBankManageService;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 시험문제관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2023.02.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2023.02.00  			rainend          최초 생성
 * </pre>
 */
@Service("surveyBankManageService")
public class SurveyBankManageServiceImpl extends EgovAbstractServiceImpl implements SurveyBankManageService {

	@Resource(name="surveyBankManageDAO")
	private SurveyBankManageDAO surveyBankManageDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception {
		return surveyBankManageDAO.selectSurveyBankList(SurveyVO);
	}

	/**
	 * @param examSearchVO 검색조건
	 * @return 총시험문제 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectSurveyBankListTotCnt(SurveyVO SurveyVO) throws Exception {
		return surveyBankManageDAO.selectSurveyBankListTotCnt(SurveyVO);
	}

	/**
	 * @param bankCd 상세조회대상 시험문제코드
	 * @return ExamVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public SurveyVO selectSurveyBankDetail(SurveyVO SurveyVO) throws Exception {
		return surveyBankManageDAO.selectSurveyBankDetail(SurveyVO);
	}

	/**
	 * @param ExamVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertSurveyBank(SurveyVO SurveyVO) throws Exception {
		surveyBankManageDAO.insertSurveyBank(SurveyVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param ExamVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateSurveyBank(SurveyVO SurveyVO) throws Exception {
		surveyBankManageDAO.updateSurveyBank(SurveyVO);
	}

}
