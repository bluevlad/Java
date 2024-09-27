package egovframework.com.academy.survey.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.survey.service.SurveyManageService;
import egovframework.com.academy.survey.service.SurveyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/**
 * 설문관리를 처리하는 ServiceImpl Class 구현
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
@Service("SurveyManageService")
public class SurveyManageServiceImpl extends EgovAbstractServiceImpl implements SurveyManageService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="SurveyManageDAO")
	private SurveyManageDAO dao;

    /**
	 * 설문템플릿 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyList(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyList(SurveyVO);
	}

    /**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectSurveyListCnt(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyListCnt(SurveyVO);
	}

    /**
	 * 설문관리를(을) 상세조회 한다.
	 * @param SurveyVO - 설문정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public SurveyVO selectSuveyDetail(SurveyVO SurveyVO) throws Exception{
		return dao.selectSuveyDetail(SurveyVO);
	}

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertSurvey(SurveyVO SurveyVO) throws Exception {

		dao.insertSurvey(SurveyVO);
	}

    /**
	 * 설문관리를(을) 수정한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void updateSurvey(SurveyVO SurveyVO) throws Exception{
		dao.updateSurvey(SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteSurvey(SurveyVO SurveyVO) throws Exception{
		dao.deleteSurvey(SurveyVO);
	}

    /**
	 * 설문응답정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyRstList(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyRstList(SurveyVO);
	}

    /**
	 * 설문답변정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyAnswerList(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyAnswerList(SurveyVO);
	}

    /**
	 * 설문 응답자 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyUserList(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyUserList(SurveyVO);
	}

    /**
	 * 설문 문항정보 목록을 조회한다.
	 * @param SurveyVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception{
		return dao.selectSurveyBankList(SurveyVO);
	}

}
