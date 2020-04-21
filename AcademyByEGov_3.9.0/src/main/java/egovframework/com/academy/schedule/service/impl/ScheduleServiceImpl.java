package egovframework.com.academy.schedule.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.schedule.service.ScheduleService;
import egovframework.com.academy.schedule.service.ScheduleVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/**
 * 일정관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2020.04.00  rainend		myProject 적용
 * </pre>
 */
@Service("scheduleService")
public class ScheduleServiceImpl extends EgovAbstractServiceImpl implements ScheduleService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="scheduleDao")
	private ScheduleDao dao;

	@Resource(name="deptSchdulManageIdGnrService")
	private EgovIdGnrService idgenService;


    /**
	 * 메인페이지/일정관리조회
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectSceduleMain(Map<?, ?> map) throws Exception{
		return dao.selectSceduleMain(map);
	}

    /**
	 * 일정 목록을 Map(map)형식으로 조회한다.
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectScheduleRetrieve(Map<?, ?> map) throws Exception{
		return dao.selectScheduleRetrieve(map);
	}

    /**
	 * 일정 목록을 VO(model)형식으로 조회한다.
	 * @param ScheduleVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public ScheduleVO selectScheduleDetail(ScheduleVO ScheduleVO) throws Exception{
		return dao.selectScheduleDetail(ScheduleVO);
	}

    /**
	 * 일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectScheduleList(ComDefaultVO searchVO) throws Exception{
		return dao.selectScheduleList(searchVO);
	}

    /**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectScheduleListCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectScheduleListCnt(searchVO);
	}

    /**
	 * 일정를(을) 등록한다.
	 * @param ScheduleVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertSchedule(ScheduleVO ScheduleVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();
		ScheduleVO.setScdId(sMakeId);

		dao.insertSchedule(ScheduleVO);
	}

    /**
	 * 일정를(을) 수정한다.
	 * @param ScheduleVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void updateSchedule(ScheduleVO ScheduleVO) throws Exception{
		dao.updateSchedule(ScheduleVO);
	}

    /**
	 * 일정를(을) 삭제한다.
	 * @param ScheduleVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteSchedule(ScheduleVO ScheduleVO) throws Exception{
		dao.deleteSchedule(ScheduleVO);
	}
}
