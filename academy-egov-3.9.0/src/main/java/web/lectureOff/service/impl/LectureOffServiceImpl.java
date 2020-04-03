package com.willbes.web.lectureOff.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lectureOff.service.LectureOffService;
import com.willbes.web.lectureOff.service.impl.LectureOffDAO;

@Service(value="lectureoffservice")
public class LectureOffServiceImpl implements LectureOffService{
	@Autowired 
	private LectureOffDAO lectureoffdao;
	
	@Override
	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return lectureoffdao.lectureList(params);
	}		
	
	@Override
	public int lectureListCount(HashMap<String, String> params){
		return lectureoffdao.lectureListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return lectureoffdao.bookList(params);
	}		
	
	@Override
	public int bookListCount(HashMap<String, String> params){
		return lectureoffdao.bookListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return lectureoffdao.getBridgeLeccodeSeq(params);
	}
	
	@Override
	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return lectureoffdao.getJongLeccodeSeq(params);
	}	
	
	@Override
	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params){
		return lectureoffdao.getLeccode(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return lectureoffdao.getBridgeLeccode(params);
	}	
	
	@Override
	public void lectureInsert(HashMap<String, String> params){
		lectureoffdao.lectureInsert(params);
	}
	
	@Override
	public void lectureBridgeInsert(HashMap<String, String> params){
		lectureoffdao.lectureBridgeInsert(params);
	}	
	
	@Override
	public void lectureBookInsert(HashMap<String, String> params){
		lectureoffdao.lectureBookInsert(params);
	}	
	
	@Override
	public void lectureBookInsert2(HashMap<String, String> params){
		lectureoffdao.lectureBookInsert2(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return lectureoffdao.lectureViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return lectureoffdao.lectureView(params);
	}		
	
	@Override
	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return lectureoffdao.lectureViewBookList(params);
	}	
	
	@Override
	public void lectureBookDelete(HashMap<String, String> params){
		lectureoffdao.lectureBookDelete(params);
	}	
	
	@Override
	public void lectureUpdate(HashMap<String, String> params){
		lectureoffdao.lectureUpdate(params);
	}
	
	@Override
	public void lectureIsUseUpdate(HashMap<String, String> params){
		lectureoffdao.lectureIsUseUpdate(params);
	}	
	
	@Override
	public void lectureDelete(HashMap<String, String> params){
		lectureoffdao.lectureDelete(params);
	}	
	
	@Override
	public void lectureBridgeDelete(HashMap<String, String> params){
		lectureoffdao.lectureBridgeDelete(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params){
		return lectureoffdao.lectureSeqList(params);
	}
	
	@Override
	public void lectureSeqUpdate(HashMap<String, String> params){
		lectureoffdao.lectureSeqUpdate(params);
	}	
	
	
	@Override
	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params){
		return lectureoffdao.lectureViewJongList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params){
		return lectureoffdao.lectureJongList(params);
	}		
	
	@Override
	public int lectureJongListCount(HashMap<String, String> params){
		return lectureoffdao.lectureJongListCount(params);
	}

	@Override
	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params){
		return lectureoffdao.lectureJongView(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params){
		return lectureoffdao.lectureJongSubjectList(params);
	}		
	
	@Override
	public int lectureJongSubjectListCount(HashMap<String, String> params){
		return lectureoffdao.lectureJongSubjectListCount(params);
	}	
	
	@Override
	public void lectureLecJongInsert(HashMap<String, String> params){
		lectureoffdao.lectureLecJongInsert(params);
	}
	
	@Override
	public void lectureChoiceJongNoInsert(HashMap<String, String> params){
		lectureoffdao.lectureChoiceJongNoInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params){
		return lectureoffdao.lectureViewLeccodeList(params);
	}		
	
	@Override
	public void lectureLecJongDelete(HashMap<String, String> params){
		lectureoffdao.lectureLecJongDelete(params);
	}
	
	@Override
	public void lectureChoiceJongNoDelete(HashMap<String, String> params){
		lectureoffdao.lectureChoiceJongNoDelete(params);
	}
	
	@Override
	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params){
		return lectureoffdao.lecturePayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> off_lecturePayList(HashMap<String, String> params){
		return lectureoffdao.off_lecturePayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params){
		return lectureoffdao.lectureJongPayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return lectureoffdao.lectureDataMemoViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return lectureoffdao.lectureDataViewList(params);
	}
	
	@Override
	public int lectureDeleteCheck(HashMap<String, String> params){
		return lectureoffdao.lectureDeleteCheck(params);
	}		
	
	@Override
	public void lectureOffDayDelete(HashMap<String, String> params){
		lectureoffdao.lectureOffDayDelete(params);
	}	
	
	@Override
	public void lectureOffDayInsert(HashMap<String, String> params){
		lectureoffdao.lectureOffDayInsert(params);
	}	
	
	@Override
	public void lectureOffDayInsert2(HashMap<String, String> params){
		lectureoffdao.lectureOffDayInsert2(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureOffDayList(HashMap<String, String> params){
		return lectureoffdao.lectureOffDayList(params);
	}	
}
