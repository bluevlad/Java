package egovframework.com.academy.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.survey.service.SurveySetManageService;
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
@Service("surveySetManageService")
public class SurveySetManageServiceImpl extends EgovAbstractServiceImpl implements SurveySetManageService {

	@Resource(name="surveySetManageDAO")
	private SurveySetManageDAO surveySetManageDAO;

	/**
	 * @param SurveyVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveySetList(SurveyVO SurveyVO) throws Exception {
		return surveySetManageDAO.selectSurveySetList(SurveyVO);
	}

	/**
	 * @param SurveyVO 검색조건
	 * @return 총시험문제 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectSurveySetListTotCnt(SurveyVO SurveyVO) throws Exception {
		return surveySetManageDAO.selectSurveySetListTotCnt(SurveyVO);
	}

	/**
	 * @param SetCd 상세조회대상 시험문제코드
	 * @return SurveyVO 시험 상세정보
	 * @throws Exception
	 */
	@Override
	public SurveyVO selectSurveySetDetail(SurveyVO SurveyVO) throws Exception {
		return surveySetManageDAO.selectSurveySetDetail(SurveyVO);
	}

	/**
	 * @param SurveyVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertSurveySet(SurveyVO SurveyVO) throws Exception {
		surveySetManageDAO.insertSurveySet(SurveyVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param SurveyVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void updateSurveySet(SurveyVO SurveyVO) throws Exception {
		surveySetManageDAO.updateSurveySet(SurveyVO);
		
	}

	/**
	 * @param SurveyVO 검색조건
	 * @return List<?> 시험문제 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveySetItemList(SurveyVO SurveyVO) throws Exception {
		return surveySetManageDAO.selectSurveySetItemList(SurveyVO);
	}

	/**
	 * @param SurveyVO 시험문제 등록정보
	 * @throws Exception
	 */
	@Override
	public void insertSurveySetItem(SurveyVO SurveyVO) throws Exception {
		surveySetManageDAO.insertSurveySetItem(SurveyVO);
	}

	/**
	 * ExamVO 시험문제정보 수정
	 * @param SurveyVO 시험정보
	 * @throws Exception
	 */
	@Override
	public void deleteSurveySetItem(SurveyVO SurveyVO) throws Exception {
		surveySetManageDAO.deleteSurveySetItem(SurveyVO);
	}

}
