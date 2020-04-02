package web.note.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.note.service.NoteService;

@Service(value="noteservice")
public class NoteServiceImpl implements NoteService{
	@Autowired 
	private NoteDAO notedao;
	
	@Override
	public List<HashMap<String, String>> noteList(HashMap<String, String> params){
		return notedao.noteList(params);
	}		
	
	@Override
	public int noteListCount(HashMap<String, String> params){
		return notedao.noteListCount(params);
	}
	
	@Override
	public HashMap<String, String> noteView(HashMap<String, String> params){
		return notedao.noteView(params);
	}		
	
	@Override
	public void noteUpdate(HashMap<String, String> params){
		notedao.noteUpdate(params);
	}	
	
	@Override
	public void noteDelete(HashMap<String, String> params){
		notedao.noteDelete(params);
	}	

	
			
}