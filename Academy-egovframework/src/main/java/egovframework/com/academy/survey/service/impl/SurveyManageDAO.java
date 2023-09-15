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
@Repository("SurveyManageDAO")
public class SurveyManageDAO extends EgovComAbstractDAO {

    /**
	 * 설문관리 목록을 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyList(SurveyVO SurveyVO) throws Exception{
		return list("SurveyManage.selectSurveyList", SurveyVO);
	}

    /**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectSurveyListCnt(SurveyVO SurveyVO) throws Exception{
		return (Integer)selectOne("SurveyManage.selectSurveyListCnt", SurveyVO);
	}

    /**
	 * 설문관리를(을) 상세조회 한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public SurveyVO selectSuveyDetail(SurveyVO SurveyVO) throws Exception{
		return selectOne("SurveyManage.selectSuveyDetail", SurveyVO);
	}

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void insertSurvey(SurveyVO SurveyVO) throws Exception{
		insert("SurveyManage.insertSurvey", SurveyVO);
	}

    /**
	 * 설문관리를(을) 수정한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void updateSurvey(SurveyVO SurveyVO) throws Exception{
		insert("SurveyManage.updateSurvey", SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteSurvey(SurveyVO SurveyVO) throws Exception{
		//설문관리 삭제
		delete("SurveyManage.deleteSurvey", SurveyVO);
	}

    /**
	 * 설문 응답정보 목록을 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyRstList(SurveyVO SurveyVO) throws Exception{
		return list("SurveyManage.SurveyRstList", SurveyVO);
	}

    /**
	 * 설문 응답정답변보 목록을 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyAnswerList(SurveyVO SurveyVO) throws Exception{
		return list("SurveyManage.SurveyAnswerList", SurveyVO);
	}

    /**
	 * 설문 응답사용자정보 목록을 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyUserList(SurveyVO SurveyVO) throws Exception{
		return list("SurveyManage.SurveyUserList", SurveyVO);
	}

    /**
	 * 설문 문항정보 목록을 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception{
		return list("SurveyManage.SurveyBankList", SurveyVO);
	}

}
