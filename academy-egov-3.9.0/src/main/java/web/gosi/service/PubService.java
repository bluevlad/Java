package web.gosi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PubService {
    
	List<HashMap<String, String>> getPubCategory(HashMap<String, String> params);
	HashMap<String, String> getPubCategoryOne(HashMap<String, String> params);
	int insertPubCategory(HashMap<String, String> params);
	void updatePubCategory(HashMap<String, String> params);
	int deletePubCategory(HashMap<String, String> params);
	
	List<HashMap<String, String>> getPubCategorySel(HashMap<String, String> params);
	List<HashMap<String, String>> getPubGubun(HashMap<String, String> params);

	List<HashMap<String, String>> getPubList(HashMap<String, String> params);
	int getPubListCount(HashMap<String, String> params);
	HashMap<String, String> getPubOne(HashMap<String, String> params);
	List<HashMap<String, String>> getPubFiles(HashMap<String, String> params);
    int getMaxPubNo(HashMap<String, String> params);
	
	void AttachFile_insert(HashMap<String, String> params);
	void AttachFile_delete(HashMap<String, String> params);
	void Pub_Insert(HashMap<String, String> params);
	void Pub_Update(HashMap<String, String> params);
	void Pub_delete(HashMap<String, String> params);

}
