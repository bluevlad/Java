package egovframework.com.academy.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.lecture.service.LectureService;
import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service(value="lectureservice")
public class LectureServiceImpl extends EgovAbstractServiceImpl implements LectureService{

	@Resource(name="lectureDAO")
	private LectureDAO lecturedao;
	
	@Override
	public List<?> selectLectureList(LectureVO LectureVO) throws Exception{
		return lecturedao.selectLectureList(LectureVO);
	}		

	
	@Override
	public int selectLectureListCount(LectureVO LectureVO) throws Exception{
		return lecturedao.selectLectureListCount(LectureVO);
	}	
	
	@Override
	public List<HashMap<String, String>> bookList(HashMap<String, String> params) throws Exception{
		return lecturedao.bookList(params);
	}		
	
	@Override
	public int bookListCount(HashMap<String, String> params) throws Exception{
		return lecturedao.bookListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params) throws Exception{
		return lecturedao.getBridgeLeccodeSeq(params);
	}
	
	@Override
	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params) throws Exception{
		return lecturedao.getJongLeccodeSeq(params);
	}	
	
	@Override
	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params) throws Exception{
		return lecturedao.getLeccode(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params) throws Exception{
		return lecturedao.getBridgeLeccode(params);
	}	
	
	@Override
	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params) throws Exception{
		return lecturedao.BridgeLeccode(params);
	}
	
	@Override
	public void lectureInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureInsert(params);
	}
	
	@Override
	public void lectureBridgeInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureBridgeInsert(params);
	}	
	
	@Override
	public void lectureBookInsert2(HashMap<String, String> params) throws Exception{
		lecturedao.lectureBookInsert2(params);
	}	
	
	@Override
	public void lectureBookInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureBookInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureView(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureView(params);
	}		
	
	@Override
	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureViewBookList(params);
	}	
	
	@Override
	public void lectureBookDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureBookDelete(params);
	}	
	
	@Override
	public void lectureUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lectureUpdate(params);
	}
	
	@Override
	public void lectureIsUseUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lectureIsUseUpdate(params);
	}	
	
	@Override
	public void lectureDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureDelete(params);
	}	
	
	@Override
	public void lectureBridgeDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureBridgeDelete(params);
	}
	
	@Override
	public void lecMovUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lecMovUpdate(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureSeqList(params);
	}
	
	@Override
	public void lectureSeqUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lectureSeqUpdate(params);
	}	
	
	@Override
	public void Modify_Lecture_On_Off(HashMap<String, String> params) throws Exception{
		lecturedao.Modify_Lecture_On_Off(params);
	}
	
	
	@Override
	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureViewJongList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongList(params);
	}		
	
	@Override
	public int lectureJongListCount(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureYearList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureYearList(params);
	}		
	
	@Override
	public int lectureYearListCount(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureYearListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongView(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongSubjectList(params);
	}		
	
	@Override
	public int lectureJongSubjectListCount(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongSubjectListCount(params);
	}	
	
	@Override
	public void lectureLecJongInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureLecJongInsert(params);
	}
	
	@Override
	public void lectureChoiceJongNoInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureChoiceJongNoInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureViewLeccodeList(params);
	}		
	
	@Override
	public void lectureLecJongDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureLecJongDelete(params);
	}
	
	@Override
	public void lectureChoiceJongNoDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureChoiceJongNoDelete(params);
	}
	
	@Override
	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params) throws Exception{
		return lecturedao.lecturePayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureJongPayList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureFreePassPayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDataMemoViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDataViewList(params);
	}
	@Override
	public List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureMobileList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDataMovieViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDataMovieList(params);
	}	
	
	@Override
	public void lectureMovieInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieInsert(params);
	}	
	
	@Override
	public void lectureMovieDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieDelete(params);
	}	
	
	@Override
	public void lectureMovieUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieUpdate(params);
	}
	
	@Override
	public void lectureMovieFileDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieFileDelete(params);
	}		
	
	
	
	
	@Override
	public void lectureMovieMemoInsert(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieMemoInsert(params);
	}
	
	@Override
	public void lectureMovieMemoUpdate(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieMemoUpdate(params);
	}
	
	@Override
	public void lectureMovieMemoDelete(HashMap<String, String> params) throws Exception{
		lecturedao.lectureMovieMemoDelete(params);
	}
	
	
	@Override
	public int lectureDeleteCheck(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDeleteCheck(params);
	}
	
	
	
	
	@Override
	public List<HashMap<String, String>> playinfo(HashMap<String, String> params) throws Exception{
		return lecturedao.playinfo(params);
	}		
	
	@Override
	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params) throws Exception{
		return lecturedao.getCbMovie4_free_admin(params);
	}		
	
	@Override
	public int getCbMovie4_free_admin_count(HashMap<String, String> params) throws Exception{
		return lecturedao.getCbMovie4_free_admin_count(params);
	}
	
	@Override
	public HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) throws Exception {
		return lecturedao.lectureOnDetailS(params);
	}
	@Override
	public void insertPmpDownLog(HashMap<String, String> params) throws Exception{
		lecturedao.insertPmpDownLog(params);
	}	
	@Override
	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureWMV(params);
	}
	@Override
	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params) throws Exception{
		return lecturedao.lectureDown_Count(params);
	}
	
	@Override
	public List<HashMap<String, String>> YearIngList(HashMap<String, String> params) throws Exception{
		return lecturedao.YearIngList(params);
	}
	
	@Override
	public List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params) throws Exception{
		return lecturedao.MyYearIngList(params);
	}
	
	@Override
    public List<HashMap<String, String>> bookView(HashMap<String, String> params) throws Exception{
        return lecturedao.bookView(params);
    }
    
	
}
