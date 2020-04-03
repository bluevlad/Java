package web.mocktest.stats.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mocktest.stats.service.StatsService;
import web.mocktest.stats.service.impl.StatsDAO;

@Service(value="statsservice")
public class StatsServiceImpl implements StatsService{
	@Autowired 
	private StatsDAO statsDAO;

	@Override
	public List<HashMap<String, String>> statsList(HashMap<String, String> params){
		return statsDAO.statsList(params);
	}
	
	@Override
	public int statsListCount(HashMap<String, String> params){
		return statsDAO.statsListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> offererList(HashMap<String, String> params){
		return statsDAO.offererList(params);
	}
	
	@Override
	public int offererListCount(HashMap<String, String> params){
		return statsDAO.offererListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> offererListExcel(HashMap<String, String> params){
		return statsDAO.offererListExcel(params);
	}
	
	@Override
	public List<HashMap<String, String>> getSubjectList(HashMap<String, String> params){
		return statsDAO.getSubjectList(params);
	}

	
	@Override
	public List<HashMap<String, String>> statsSubjectList(HashMap<String, String> params){
		return statsDAO.statsSubjectList(params);
	}
	
	@Override
	public List<HashMap<String, String>> statsSubjectAnswerList(HashMap<String, String> params){
		return statsDAO.statsSubjectAnswerList(params);
	}		
	
	@Override
	public List<HashMap<String, String>> statsSubjectInfoList(HashMap<String, String> params){
		return statsDAO.statsSubjectInfoList(params);
	}

	@Override
	public List<HashMap<String, String>> statsTotalInfoList(HashMap<String, String> params){
		return statsDAO.statsTotalInfoList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalInfo1(HashMap<String, String> params){
		return statsDAO.statsTotalInfo1(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalInfo2(HashMap<String, String> params){
		return statsDAO.statsTotalInfo2(params);
	}	

	@Override
	public List<HashMap<String, String>> statsTotalInfo2_TblH(HashMap<String, String> params){
		return statsDAO.statsTotalInfo2_TblH(params);
	}
	
	@Override
	public List<HashMap<String, String>> statsTotalInfo2_Tbl(HashMap<String, String> params){
		return statsDAO.statsTotalInfo2_Tbl(params);
	}

	@Override
	public List<HashMap<String, String>> statsTotalInfo4SubjectList(HashMap<String, String> params){
		return statsDAO.statsTotalInfo4SubjectList(params);
	}
	
	@Override
	public List<HashMap<String, String>> statsTotalInfo4MockList(HashMap<String, String> params){
		return statsDAO.statsTotalInfo4MockList(params);
	}
	
	@Override
	public List<HashMap<String, String>> statsTotalInfo4MockSubjectScoreList(HashMap<String, String> params){
		return statsDAO.statsTotalInfo4MockSubjectScoreList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalList(HashMap<String, String> params){
		return statsDAO.statsTotalList(params);
	}
	
	@Override
	public int statsTotalListCount(HashMap<String, String> params){
		return statsDAO.statsTotalListCount(params);
	}

	@Override
	public List<HashMap<String, String>> statsTotalViewClassseriesList(HashMap<String, String> params){
		return statsDAO.statsTotalViewClassseriesList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalViewInfo(HashMap<String, String> params){
		return statsDAO.statsTotalViewInfo(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalView_TblH(HashMap<String, String> params){
		return statsDAO.statsTotalView_TblH(params);
	}	
	
	@Override
	public List<HashMap<String, String>> statsTotalSubjectView(HashMap<String, String> params){
		return statsDAO.statsTotalSubjectView(params);
	}		
	
	@Override
	public List<HashMap<String, String>> statsTotalSubjectViewAver(HashMap<String, String> params){
		return statsDAO.statsTotalSubjectViewAver(params);
	}
	
	@Override
	public int offererOnCntY(HashMap<String, String> params){
		return statsDAO.offererOnCntY(params);
	}		

	@Override
	public int offererOnCntN(HashMap<String, String> params){
		return statsDAO.offererOnCntN(params);
	}
	
	@Override
	public int offererOffCntY(HashMap<String, String> params){
		return statsDAO.offererOffCntY(params);
	}
	
	@Override
	public int offererOffCntN(HashMap<String, String> params){
		return statsDAO.offererOffCntN(params);
	}	
	
	@Override
	public int statsTotalCnt(HashMap<String, String> params){
		return statsDAO.statsTotalCnt(params);
	}	

    // 개인 시험 성적 정보 - 20170807
    @Override
    public int userExamInfo(HashMap<String, String> params){
        return statsDAO.userExamInfo(params);
    }
}
