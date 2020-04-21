package egovframework.com.academy.schedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.schedule.service.ScheduleVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
/**
 * 일정관리를 처리하는 Dao Class 구현
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
 *   2016.08.01  장동한          표준프레임워크 v3.6 개선
 *   2020.04.00  rainend		myProject 적용
 * </pre>
 */
@Repository("scheduleDao")
public class ScheduleDao extends EgovComAbstractDAO {


    /**
	 * 메인페이지/일정관리조회 목록을 Map(map)형식으로 조회한다.
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectSceduleMain(Map<?, ?> map) throws Exception{
		 return  list("ScehduleManage.selectSceduleMain", map);
	}

    /**
	 * 일정 목록을 Map(map)형식으로 조회한다.
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectScheduleRetrieve(Map<?, ?> map) throws Exception{
		 return  list("ScehduleManage.selectScheduleRetrieve", map);
	}


    /**
	 * 일정 목록을 VO(model)형식으로 조회한다.
	 * @param ScheduleVO - 조회할 정보가 담긴 VO
	 * @return ScheduleVO
	 * @throws Exception
	 */
	public ScheduleVO selectScheduleDetail(ScheduleVO ScheduleVO) throws Exception{
		return (ScheduleVO)selectOne("ScehduleManage.selectScheduleDetail", ScheduleVO);
	}

    /**
	 * 일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectScheduleList(ComDefaultVO searchVO) throws Exception{
		return list("ScehduleManage.selectScheduleList", searchVO);
	}

    /**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectScheduleListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("ScehduleManage.selectScheduleListCnt", searchVO);
	}

    /**
	 * 일정를(을) 등록한다.
	 * @param ScheduleVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	public void insertSchedule(ScheduleVO ScheduleVO) throws Exception{
		insert("ScehduleManage.insertSchedule", ScheduleVO);
	}

    /**
	 * 일정를(을) 수정한다.
	 * @param ScheduleVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	public void updateSchedule(ScheduleVO ScheduleVO) throws Exception{
		insert("ScehduleManage.updateSchedule", ScheduleVO);
	}

    /**
	 * 일정를(을) 삭제한다.
	 * @param ScheduleVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteSchedule(ScheduleVO ScheduleVO) throws Exception{
		// 일정관리 삭제
		delete("ScehduleManage.deleteSchedule", ScheduleVO);
	}
}
