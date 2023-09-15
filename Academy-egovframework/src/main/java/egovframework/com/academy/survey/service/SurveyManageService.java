package egovframework.com.academy.survey.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 설문관리를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public interface SurveyManageService {

    /**
	 * 설문정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyList(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectSurveyListCnt(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를 상세조회(Model) 한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
    public SurveyVO selectSuveyDetail(SurveyVO SurveyVO) throws Exception ;

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  insertSurvey(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 수정한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  updateSurvey(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteSurvey(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문응답정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyRstList(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문답변정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyAnswerList(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문응답자 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyUserList(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문문항정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception;

}
