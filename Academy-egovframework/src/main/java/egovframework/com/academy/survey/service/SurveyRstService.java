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
public interface SurveyRstService {

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  insertSurveyRst(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteSurveyRst(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  insertSurveyRstItem(SurveyVO SurveyVO) throws Exception;

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteSurveyRstItem(SurveyVO SurveyVO) throws Exception;

}
