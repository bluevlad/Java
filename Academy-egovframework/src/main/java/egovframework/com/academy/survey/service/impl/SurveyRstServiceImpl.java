package egovframework.com.academy.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.survey.service.SurveyRstService;
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
@Service("SurveyRstService")
public class SurveyRstServiceImpl extends EgovAbstractServiceImpl implements SurveyRstService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="SurveyRstDao")
	private SurveyRstDAO dao;

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertSurveyRst(SurveyVO SurveyVO) throws Exception {

		dao.insertSurveyRst(SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteSurveyRst(SurveyVO SurveyVO) throws Exception{
		dao.deleteSurveyRst(SurveyVO);
	}

    /**
	 * 설문관리를(을) 등록한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertSurveyRstItem(SurveyVO SurveyVO) throws Exception {

		dao.insertSurveyRstItem(SurveyVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param SurveyVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteSurveyRstItem(SurveyVO SurveyVO) throws Exception{
		dao.deleteSurveyRstItem(SurveyVO);
	}

}
