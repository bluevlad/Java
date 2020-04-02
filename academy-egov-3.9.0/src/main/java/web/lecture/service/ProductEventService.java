package web.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductEventService {

	List<HashMap<String, String>> list(HashMap<String, String> params);
	
	int listCount(HashMap<String, String> params);

	HashMap<String, String> getOne(HashMap<String, String> params);

	List<HashMap<String, String>> list_prd(HashMap<String, String> params);

	void insert(HashMap<String, String> params);
	
	void update(HashMap<String, String> params);

	void lec_insert(HashMap<String, String> params);
	
	void lec_delete(HashMap<String, String> params);
	
	// 강의선택 팝업 카테고리 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaCatCdList(Map keyName);
	
	// 강의선택 팝업 학습형태 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getVwMenuMstTree_lec(Map keyName);
	
	// 강의선택 팝업 과목 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaSubjectCdList(Map keyName);
	
	// 강의선택 팝업  리스트
	@SuppressWarnings({ "rawtypes" })
	List getCbLecMstFreeOrderList(Map keyName);
		
	// 강의선택 팝업  카운트
	@SuppressWarnings({ "rawtypes" })
	int getCbLecMstListFreeOrderCount(Map keyName);

}
