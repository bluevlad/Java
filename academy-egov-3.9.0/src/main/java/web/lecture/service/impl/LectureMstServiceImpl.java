package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lecture.service.LectureMstService;

@Service(value="lecturemstservice")
public class LectureMstServiceImpl implements LectureMstService{
	@Autowired 
	private LectureMstDAO lecturemstdao;
	
	@Override
	public List<HashMap<String, String>> lecturemstList(HashMap<String, String> params){
		return lecturemstdao.lecturemstList(params);
	}		
	
	@Override
	public int lecturemstListCount(HashMap<String, String> params){
		return lecturemstdao.lecturemstListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeMstcodeSeq(HashMap<String, String> params){
		return lecturemstdao.getBridgeMstcodeSeq(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeMstcode(HashMap<String, String> params){
		return lecturemstdao.getBridgeMstcode(params);
	}
	
	@Override
	public List<HashMap<String, String>> getMstcode(HashMap<String, String> params){
		return lecturemstdao.getMstcode(params);
	}
	
	@Override
	public void lecturemstInsert(HashMap<String, String> params){
		lecturemstdao.lecturemstInsert(params);
	}
	
	@Override
	public void lectureBridgeInsert(HashMap<String, String> params){
		lecturemstdao.lectureBridgeInsert(params);
	}	
	
	@Override
	public void lectureBookInsert2(HashMap<String, String> params){
		lecturemstdao.lectureBookInsert2(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return lecturemstdao.lectureDataMemoViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return lecturemstdao.lectureDataViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params){
		return lecturemstdao.lectureWMV(params);
	}
	@Override
	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params){
		return lecturemstdao.lectureDown_Count(params);
	}
	

	@Override
	public int lectureDeleteCheck(HashMap<String, String> params){
		return lecturemstdao.lectureDeleteCheck(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return lecturemstdao.lectureView(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return lecturemstdao.lectureViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return lecturemstdao.lectureViewBookList(params);
	}
	
	@Override
	public void lectureBookDelete(HashMap<String, String> params){
		lecturemstdao.lectureBookDelete(params);
	}	
	
	@Override
	public void lecturemstUpdate(HashMap<String, String> params){
		lecturemstdao.lecturemstUpdate(params);
	}
	
	@Override
	public void lectureBookInsert(HashMap<String, String> params){
		lecturemstdao.lectureBookInsert(params);
	}
	
	@Override
	public void lectureDelete(HashMap<String, String> params){
		lecturemstdao.lectureDelete(params);
	}	
	
	@Override
	public void lectureBridgeDelete(HashMap<String, String> params){
		lecturemstdao.lectureBridgeDelete(params);
	}
	
	
	
}
