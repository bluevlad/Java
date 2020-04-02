package web.note.service;

import java.util.HashMap;
import java.util.List;

public interface NoteService {

	List<HashMap<String, String>> noteList(HashMap<String, String> params);
	
	int noteListCount(HashMap<String, String> params);
	
	HashMap<String, String> noteView(HashMap<String, String> params);
	
	void noteUpdate(HashMap<String, String> params);
	
	void noteDelete(HashMap<String, String> params);
}
