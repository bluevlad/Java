package web.mocktest.stats.service;

import java.util.HashMap;
import java.util.List;

public interface StatsService {

	List<HashMap<String, String>> statsList(HashMap<String, String> params);
	
	int statsListCount(HashMap<String, String> params);
	
	List<HashMap<String, String>> offererList(HashMap<String, String> params);
	
	int offererListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> offererListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> getSubjectList(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsSubjectList(HashMap<String, String> params);

	List<HashMap<String, String>> statsSubjectAnswerList(HashMap<String, String> params);

	List<HashMap<String, String>> statsSubjectInfoList(HashMap<String, String> params);	
	
	List<HashMap<String, String>> statsTotalInfoList(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalInfo1(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalInfo2(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalInfo2_TblH(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalInfo2_Tbl(HashMap<String, String> params);

	List<HashMap<String, String>> statsTotalInfo4SubjectList(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalInfo4MockList(HashMap<String, String> params);
		
	List<HashMap<String, String>> statsTotalInfo4MockSubjectScoreList(HashMap<String, String> params);	
	
	List<HashMap<String, String>> statsTotalList(HashMap<String, String> params);
	
	int statsTotalListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> statsTotalViewClassseriesList(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalViewInfo(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalView_TblH(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalSubjectView(HashMap<String, String> params);
	
	List<HashMap<String, String>> statsTotalSubjectViewAver(HashMap<String, String> params);
	
	int offererOnCntY(HashMap<String, String> params);
	
	int offererOnCntN(HashMap<String, String> params);
	
	int offererOffCntY(HashMap<String, String> params);
	
	int offererOffCntN(HashMap<String, String> params);

	int statsTotalCnt(HashMap<String, String> params);

    // 개인 시험 성적 정보 - 20170807
    int userExamInfo(HashMap<String, String> params);
}
