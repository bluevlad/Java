package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lecture.service.OpenLectureService;
import web.lecture.service.impl.OpenLectureDAO;

@Service(value="openlectureservice")
public class OpenLectureServiceImpl implements OpenLectureService{
	@Autowired 
	private OpenLectureDAO openlecturedao;
	
	@Override
	public List<HashMap<String, String>> openlectureList(HashMap<String, String> params){
		return openlecturedao.openlectureList(params);
	}		
	
	@Override
	public int openlectureListCount(HashMap<String, String> params){
		return openlecturedao.openlectureListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return openlecturedao.bookList(params);
	}		
	
	@Override
	public int bookListCount(HashMap<String, String> params){
		return openlecturedao.bookListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return openlecturedao.getBridgeLeccodeSeq(params);
	}
	
	@Override
	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return openlecturedao.getJongLeccodeSeq(params);
	}	
	
	@Override
	public List<HashMap<String, String>> getopenLeccode(HashMap<String, String> params){
		return openlecturedao.getopenLeccode(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return openlecturedao.getBridgeLeccode(params);
	}	
	
	@Override
	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params){
		return openlecturedao.BridgeLeccode(params);
	}
	
	@Override
	public void openlectureInsert(HashMap<String, String> params){
		openlecturedao.openlectureInsert(params);
	}
	
	@Override
	public void openlectureBridgeInsert(HashMap<String, String> params){
		openlecturedao.openlectureBridgeInsert(params);
	}	
	
	@Override
	public void openlectureBookInsert2(HashMap<String, String> params){
		openlecturedao.openlectureBookInsert2(params);
	}	
	
	@Override
	public void openlectureBookInsert(HashMap<String, String> params){
		openlecturedao.openlectureBookInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureViewList(HashMap<String, String> params){
		return openlecturedao.openlectureViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureView(HashMap<String, String> params){
		return openlecturedao.openlectureView(params);
	}		
	
	@Override
	public List<HashMap<String, String>> openlectureViewBookList(HashMap<String, String> params){
		return openlecturedao.openlectureViewBookList(params);
	}	
	
	@Override
	public void openlectureBookDelete(HashMap<String, String> params){
		openlecturedao.openlectureBookDelete(params);
	}	
	
	@Override
	public void openlectureUpdate(HashMap<String, String> params){
		openlecturedao.openlectureUpdate(params);
	}
	
	@Override
	public void openlectureIsUseUpdate(HashMap<String, String> params){
		openlecturedao.openlectureIsUseUpdate(params);
	}	
	
	@Override
	public void openlectureDelete(HashMap<String, String> params){
		openlecturedao.openlectureDelete(params);
	}	
	
	@Override
	public void openlectureBridgeDelete(HashMap<String, String> params){
		openlecturedao.openlectureBridgeDelete(params);
	}
	
	@Override
	public void lecMovUpdate(HashMap<String, String> params){
		openlecturedao.lecMovUpdate(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlectureSeqList(HashMap<String, String> params){
		return openlecturedao.openlectureSeqList(params);
	}
	
	@Override
	public void openlectureSeqUpdate(HashMap<String, String> params){
		openlecturedao.openlectureSeqUpdate(params);
	}	
	
	@Override
	public void Modify_Lecture_On_Off(HashMap<String, String> params){
		openlecturedao.Modify_Lecture_On_Off(params);
	}
	
	
	@Override
	public List<HashMap<String, String>> openlectureViewJongList(HashMap<String, String> params){
		return openlecturedao.openlectureViewJongList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureJongList(HashMap<String, String> params){
		return openlecturedao.openlectureJongList(params);
	}		
	
	@Override
	public int openlectureJongListCount(HashMap<String, String> params){
		return openlecturedao.openlectureJongListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlectureJongView(HashMap<String, String> params){
		return openlecturedao.openlectureJongView(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureJongSubjectList(HashMap<String, String> params){
		return openlecturedao.openlectureJongSubjectList(params);
	}		
	
	@Override
	public int openlectureJongSubjectListCount(HashMap<String, String> params){
		return openlecturedao.openlectureJongSubjectListCount(params);
	}	
	
	@Override
	public void openlectureLecJongInsert(HashMap<String, String> params){
		openlecturedao.openlectureLecJongInsert(params);
	}
	
	@Override
	public void openlectureChoiceJongNoInsert(HashMap<String, String> params){
		openlecturedao.openlectureChoiceJongNoInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureViewLeccodeList(HashMap<String, String> params){
		return openlecturedao.openlectureViewLeccodeList(params);
	}		
	
	@Override
	public void openlectureLecJongDelete(HashMap<String, String> params){
		openlecturedao.openlectureLecJongDelete(params);
	}
	
	@Override
	public void openlectureChoiceJongNoDelete(HashMap<String, String> params){
		openlecturedao.openlectureChoiceJongNoDelete(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlecturePayList(HashMap<String, String> params){
		return openlecturedao.openlecturePayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlectureJongPayList(HashMap<String, String> params){
		return openlecturedao.openlectureJongPayList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureDataMemoViewList(HashMap<String, String> params){
		return openlecturedao.openlectureDataMemoViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> openlectureDataViewList(HashMap<String, String> params){
		return openlecturedao.openlectureDataViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlectureDataMovieViewList(HashMap<String, String> params){
		return openlecturedao.openlectureDataMovieViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> openlectureDataMovieList(HashMap<String, String> params){
		return openlecturedao.openlectureDataMovieList(params);
	}	
	
	@Override
	public void openlectureMovieInsert(HashMap<String, String> params){
		openlecturedao.openlectureMovieInsert(params);
	}	
	
	@Override
	public void openlectureMovieDelete(HashMap<String, String> params){
		openlecturedao.openlectureMovieDelete(params);
	}	
	
	@Override
	public void openlectureMovieUpdate(HashMap<String, String> params){
		openlecturedao.openlectureMovieUpdate(params);
	}
	
	@Override
	public void openlectureMovieFileDelete(HashMap<String, String> params){
		openlecturedao.openlectureMovieFileDelete(params);
	}		
	
	
	
	
	@Override
	public void openlectureMovieMemoInsert(HashMap<String, String> params){
		openlecturedao.openlectureMovieMemoInsert(params);
	}
	
	@Override
	public void openlectureMovieMemoUpdate(HashMap<String, String> params){
		openlecturedao.openlectureMovieMemoUpdate(params);
	}
	
	@Override
	public void openlectureMovieMemoDelete(HashMap<String, String> params){
		openlecturedao.openlectureMovieMemoDelete(params);
	}
	
	
	@Override
	public int openlectureDeleteCheck(HashMap<String, String> params){
		return openlecturedao.openlectureDeleteCheck(params);
	}
	
	
	
	
	@Override
	public List<HashMap<String, String>> playinfo(HashMap<String, String> params){
		return openlecturedao.playinfo(params);
	}		
	
	@Override
	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params){
		return openlecturedao.getCbMovie4_free_admin(params);
	}		
	
	@Override
	public int getCbMovie4_free_admin_count(HashMap<String, String> params){
		return openlecturedao.getCbMovie4_free_admin_count(params);
	}
	
	@Override
	public HashMap<String, String> openlectureOnDetailS(HashMap<String, String> params) {
		return openlecturedao.openlectureOnDetailS(params);
	}
	@Override
	public void insertPmpDownLog(HashMap<String, String> params){
		openlecturedao.insertPmpDownLog(params);
	}	
	@Override
	public List<HashMap<String, String>> openlectureWMV(HashMap<String, String> params){
		return openlecturedao.openlectureWMV(params);
	}
	@Override
	public List<HashMap<String, String>> openlectureDown_Count(HashMap<String, String> params){
		return openlecturedao.openlectureDown_Count(params);
	}
}
