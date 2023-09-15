package egovframework.com.academy.survey.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.academy.survey.service.SurveyVO;

import org.springframework.stereotype.Repository;
/**
 * 설문관리를 처리하는 Dao Class 구현
 * @author rainend
 * @since 2023.03.20
 * @version 1.0
 * @see
 */
@Repository("SurveyRstDao")
public class SurveyRstDAO extends EgovComAbstractDAO {

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void insertSurveyRst(SurveyVO SurveyVO) throws Exception{
		insert("SurveyRst.insertSurveyRst", SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteSurveyRst(SurveyVO SurveyVO) throws Exception{
		//설문관리 삭제
		delete("SurveyRst.deleteSurveyRst", SurveyVO);
	}

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void insertSurveyRstItem(SurveyVO SurveyVO) throws Exception{
		insert("SurveyRst.insertSurveyRstItem", SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteSurveyRstItem(SurveyVO SurveyVO) throws Exception{
		//설문관리 삭제
		delete("SurveyRst.deleteSurveyRstItem", SurveyVO);
	}

}
