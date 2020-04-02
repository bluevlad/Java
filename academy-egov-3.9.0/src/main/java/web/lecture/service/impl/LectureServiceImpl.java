package web.lecture.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.lecture.service.LectureService;
import web.lecture.service.impl.LectureDAO;

@Service(value="lectureservice")
public class LectureServiceImpl implements LectureService{
	@Autowired 
	private LectureDAO lecturedao;
	
	@Override
	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return lecturedao.lectureList(params);
	}		

	
	@Override
	public int lectureListCount(HashMap<String, String> params){
		return lecturedao.lectureListCount(params);
	}	
	
	@Override
	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return lecturedao.bookList(params);
	}		
	
	@Override
	public int bookListCount(HashMap<String, String> params){
		return lecturedao.bookListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return lecturedao.getBridgeLeccodeSeq(params);
	}
	
	@Override
	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return lecturedao.getJongLeccodeSeq(params);
	}	
	
	@Override
	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params){
		return lecturedao.getLeccode(params);
	}
	
	@Override
	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return lecturedao.getBridgeLeccode(params);
	}	
	
	@Override
	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params){
		return lecturedao.BridgeLeccode(params);
	}
	
	@Override
	public void lectureInsert(HashMap<String, String> params){
		lecturedao.lectureInsert(params);
	}
	
	@Override
	public void lectureBridgeInsert(HashMap<String, String> params){
		lecturedao.lectureBridgeInsert(params);
	}	
	
	@Override
	public void lectureBookInsert2(HashMap<String, String> params){
		lecturedao.lectureBookInsert2(params);
	}	
	
	@Override
	public void lectureBookInsert(HashMap<String, String> params){
		lecturedao.lectureBookInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return lecturedao.lectureViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return lecturedao.lectureView(params);
	}		
	
	@Override
	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return lecturedao.lectureViewBookList(params);
	}	
	
	@Override
	public void lectureBookDelete(HashMap<String, String> params){
		lecturedao.lectureBookDelete(params);
	}	
	
	@Override
	public void lectureUpdate(HashMap<String, String> params){
		lecturedao.lectureUpdate(params);
	}
	
	@Override
	public void lectureIsUseUpdate(HashMap<String, String> params){
		lecturedao.lectureIsUseUpdate(params);
	}	
	
	@Override
	public void lectureDelete(HashMap<String, String> params){
		lecturedao.lectureDelete(params);
	}	
	
	@Override
	public void lectureBridgeDelete(HashMap<String, String> params){
		lecturedao.lectureBridgeDelete(params);
	}
	
	@Override
	public void lecMovUpdate(HashMap<String, String> params){
		lecturedao.lecMovUpdate(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params){
		return lecturedao.lectureSeqList(params);
	}
	
	@Override
	public void lectureSeqUpdate(HashMap<String, String> params){
		lecturedao.lectureSeqUpdate(params);
	}	
	
	@Override
	public void Modify_Lecture_On_Off(HashMap<String, String> params){
		lecturedao.Modify_Lecture_On_Off(params);
	}
	
	
	@Override
	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params){
		return lecturedao.lectureViewJongList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params){
		return lecturedao.lectureJongList(params);
	}		
	
	@Override
	public int lectureJongListCount(HashMap<String, String> params){
		return lecturedao.lectureJongListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureYearList(HashMap<String, String> params){
		return lecturedao.lectureYearList(params);
	}		
	
	@Override
	public int lectureYearListCount(HashMap<String, String> params){
		return lecturedao.lectureYearListCount(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params){
		return lecturedao.lectureJongView(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params){
		return lecturedao.lectureJongSubjectList(params);
	}		
	
	@Override
	public int lectureJongSubjectListCount(HashMap<String, String> params){
		return lecturedao.lectureJongSubjectListCount(params);
	}	
	
	@Override
	public void lectureLecJongInsert(HashMap<String, String> params){
		lecturedao.lectureLecJongInsert(params);
	}
	
	@Override
	public void lectureChoiceJongNoInsert(HashMap<String, String> params){
		lecturedao.lectureChoiceJongNoInsert(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params){
		return lecturedao.lectureViewLeccodeList(params);
	}		
	
	@Override
	public void lectureLecJongDelete(HashMap<String, String> params){
		lecturedao.lectureLecJongDelete(params);
	}
	
	@Override
	public void lectureChoiceJongNoDelete(HashMap<String, String> params){
		lecturedao.lectureChoiceJongNoDelete(params);
	}
	
	@Override
	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params){
		return lecturedao.lecturePayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params){
		return lecturedao.lectureJongPayList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params){
		return lecturedao.lectureFreePassPayList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return lecturedao.lectureDataMemoViewList(params);
	}	
	
	@Override
	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return lecturedao.lectureDataViewList(params);
	}
	@Override
	public List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params){
		return lecturedao.lectureMobileList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params){
		return lecturedao.lectureDataMovieViewList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params){
		return lecturedao.lectureDataMovieList(params);
	}	
	
	@Override
	public void lectureMovieInsert(HashMap<String, String> params){
		lecturedao.lectureMovieInsert(params);
	}	
	
	@Override
	public void lectureMovieDelete(HashMap<String, String> params){
		lecturedao.lectureMovieDelete(params);
	}	
	
	@Override
	public void lectureMovieUpdate(HashMap<String, String> params){
		lecturedao.lectureMovieUpdate(params);
	}
	
	@Override
	public void lectureMovieFileDelete(HashMap<String, String> params){
		lecturedao.lectureMovieFileDelete(params);
	}		
	
	
	
	
	@Override
	public void lectureMovieMemoInsert(HashMap<String, String> params){
		lecturedao.lectureMovieMemoInsert(params);
	}
	
	@Override
	public void lectureMovieMemoUpdate(HashMap<String, String> params){
		lecturedao.lectureMovieMemoUpdate(params);
	}
	
	@Override
	public void lectureMovieMemoDelete(HashMap<String, String> params){
		lecturedao.lectureMovieMemoDelete(params);
	}
	
	
	@Override
	public int lectureDeleteCheck(HashMap<String, String> params){
		return lecturedao.lectureDeleteCheck(params);
	}
	
	
	
	
	@Override
	public List<HashMap<String, String>> playinfo(HashMap<String, String> params){
		return lecturedao.playinfo(params);
	}		
	
	@Override
	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params){
		return lecturedao.getCbMovie4_free_admin(params);
	}		
	
	@Override
	public int getCbMovie4_free_admin_count(HashMap<String, String> params){
		return lecturedao.getCbMovie4_free_admin_count(params);
	}
	
	@Override
	public HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) {
		return lecturedao.lectureOnDetailS(params);
	}
	@Override
	public void insertPmpDownLog(HashMap<String, String> params){
		lecturedao.insertPmpDownLog(params);
	}	
	@Override
	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params){
		return lecturedao.lectureWMV(params);
	}
	@Override
	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params){
		return lecturedao.lectureDown_Count(params);
	}
	
	@Override
	public List<HashMap<String, String>> oldFreeToNewFree(HashMap<String, String> params){
		return lecturedao.oldFreeToNewFree(params);
	}
	
	@Override
	public List<HashMap<String, String>> YearIngList(HashMap<String, String> params){
		return lecturedao.YearIngList(params);
	}
	
	@Override
	public List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params){
		return lecturedao.MyYearIngList(params);
	}
	
	@Override
    public List<HashMap<String, String>> bookView(HashMap<String, String> params){
        return lecturedao.bookView(params);
    }
    
	
	@Override
	public void oldFreeToNewFreeInsert(HashMap<String, String> params){
		Calendar cal = Calendar.getInstance( );
		
		//List<HashMap<String, String>> list = lectureservice.lectureList(params);
		List<HashMap<String, String>> oldfreetonewfree_list = lecturedao.oldFreeToNewFree(params);//무료강좌 리스트 조회 
		
		
		
		if(null != oldfreetonewfree_list && oldfreetonewfree_list.size() > 0) {
			 for(int i=0; i<oldfreetonewfree_list.size(); i++){
				 
				 	
				 
					
				    //현재 무료강좌의 LECCODE 코드 조회
		            HashMap<String, String> map = (HashMap)oldfreetonewfree_list.get(i);  //무료강좌 가져온것을 for loop돌면서 F코드로 tb_lec_mst, tb_lec_bridge, tb_movie에 넣는다.
		            String LECCODE = map.get("LECCODE");
		            
		            params.put("SHEARH_LECCODE", LECCODE);
		            
		            
		            //BRIDGE_LECCODE, SEQ값을 조회
		            String SEQ = ""; //bridgetable에 seq
					String BRIDGE_LECCODE = "";
					
					params.put("PREFIX", "R" + cal.get(Calendar.YEAR));  //leccode만들때 코드
					
					List<HashMap<String, String>> getBridgeLeccodeSeqList = lecturedao.getBridgeLeccodeSeq(params);			// 브릿지테이블 SEQ 구하기
					List<HashMap<String, String>> getBridgeLeccodeList = lecturedao.getBridgeLeccode(params);					// 브릿지테이블 BRIDGE_LECCODE 구하기
					if(getBridgeLeccodeList.size() > 0){
						BRIDGE_LECCODE = params.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
					}else{
						BRIDGE_LECCODE = params.get("PREFIX") + "00001";
					}
					
					params.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

					params.put("PREFIX", "F"+ cal.get(Calendar.YEAR));
					
					if(getBridgeLeccodeSeqList.size() > 0){
						SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
						params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
					}else{
						SEQ = "1";
						params.put("SEQ", "1");
					}
				 
		            
		            //단과 코드 만들기
		            List<HashMap<String, String>> getLeccodeList = lecturedao.getLeccode(params);
					if(getLeccodeList.size() > 0)
						LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
					else
						LECCODE = params.get("PREFIX") + "00001";
					params.put("LECCODE", LECCODE.replace(" ", ""));
					
					
		            //현재(FOR LOOP의 R코드(브리지 테이블에서) 조회
		            String rleccode = lecturedao.getRleccode(params);
		            
		            
		            //무료 특강 저장
		            lecturedao.oldFreeToNewFreeInsert(params);
		            
		            
		            params.put("SEQ", SEQ);
		            params.put("REG_ID", "ADMIN");
		            params.put("UPD_ID", "ADMIN");
		            lecturedao.lectureBridgeInsert(params);		
		            
/*		            System.out.println("새로운 BRIDGE_LECCODE="+params.get("BRIDGE_LECCODE"));
		            System.out.println("이전 BRIDGE_LECCODE="+rleccode);
		            System.out.println("이전 무료단과코드="+params.get("SHEARH_LECCODE"));
		            System.out.println("현재 단과코드="+params.get("LECCODE"));*/
		         

		            //tbmovie테이블에 값을 저장
		            params.put("RLECCODE", rleccode);
		            lecturedao.oldTbmovieToNewTbmovieInsert(params);		
		            
		            
		            
		        }
		}
		
	}
	@Override
	public void oldBogangFreeToNewBogangFree(HashMap<String, String> params){
		Calendar cal = Calendar.getInstance( );
		
		//List<HashMap<String, String>> list = lectureservice.lectureList(params);
		List<HashMap<String, String>> oldfreetonewfree_list = lecturedao.oldBogangFreeToNewBogangFree(params);//무료강좌 리스트 조회 
		
		
		
		if(null != oldfreetonewfree_list && oldfreetonewfree_list.size() > 0) {
			for(int i=0; i<oldfreetonewfree_list.size(); i++){
				
				
				
				
				//현재 무료강좌의 LECCODE 코드 조회
				HashMap<String, String> map = (HashMap)oldfreetonewfree_list.get(i);  //무료강좌 가져온것을 for loop돌면서 F코드로 tb_lec_mst, tb_lec_bridge, tb_movie에 넣는다.
				String LECCODE = map.get("LECCODE");
				
				params.put("SHEARH_LECCODE", LECCODE);
				
				
				//BRIDGE_LECCODE, SEQ값을 조회
				String SEQ = ""; //bridgetable에 seq
				String BRIDGE_LECCODE = "";
				
				params.put("PREFIX", "R" + cal.get(Calendar.YEAR));  //leccode만들때 코드
				
				List<HashMap<String, String>> getBridgeLeccodeSeqList = lecturedao.getBridgeLeccodeSeq(params);			// 브릿지테이블 SEQ 구하기
				List<HashMap<String, String>> getBridgeLeccodeList = lecturedao.getBridgeLeccode(params);					// 브릿지테이블 BRIDGE_LECCODE 구하기
				if(getBridgeLeccodeList.size() > 0){
					BRIDGE_LECCODE = params.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
				}else{
					BRIDGE_LECCODE = params.get("PREFIX") + "00001";
				}
				
				params.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));
				
				params.put("PREFIX", "F"+ cal.get(Calendar.YEAR));
				
				if(getBridgeLeccodeSeqList.size() > 0){
					SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
					params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
				}else{
					SEQ = "1";
					params.put("SEQ", "1");
				}
				
				
				//단과 코드 만들기
				List<HashMap<String, String>> getLeccodeList = lecturedao.getLeccode(params);
				if(getLeccodeList.size() > 0)
					LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
				else
					LECCODE = params.get("PREFIX") + "00001";
				params.put("LECCODE", LECCODE.replace(" ", ""));
				
				
				//현재(FOR LOOP의 R코드(브리지 테이블에서) 조회
				String rleccode = lecturedao.getRleccode(params);
				
				
				//무료 특강 저장
				lecturedao.oldBogangFreeToNewFreeBogangInsert(params);
				
				params.put("SEQ", SEQ);
				params.put("REG_ID", "ADMIN");
				params.put("UPD_ID", "ADMIN");
				lecturedao.lectureBridgeInsert(params);		
				
					 System.out.println("새로운 BRIDGE_LECCODE="+params.get("BRIDGE_LECCODE"));
		            System.out.println("이전 BRIDGE_LECCODE="+rleccode);
		            System.out.println("이전 무료단과코드="+params.get("SHEARH_LECCODE"));
		            System.out.println("현재 단과코드="+params.get("LECCODE"));
				
				
				//tbmovie테이블에 값을 저장
				params.put("RLECCODE", rleccode);
				lecturedao.oldTbmovieToNewTbmovieInsert(params);		
				
				
				
			}
		}
		
	}
	
	
	/*// 시험 답안 등록(초기화)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public int insertAnswerMouigosa(Object obj) {
        HashMap<String, Object> params = (HashMap<String, Object>)obj;

        List subject_list = mouigosadao.getSubjectList(params);
        if(null != subject_list && subject_list.size()>0) {
            for (int j=0; j<subject_list.size(); j++) {
                HashMap<String, Object> map = (HashMap<String, Object>) subject_list.get(j);
                params.put("ITEMID",map.get("ITEMID"));
                params.put("QUESTIONNUM",map.get("QUESTIONNUM"));

                for (int i=1; i<=Integer.parseInt(String.valueOf(map.get("QUESTIONNUM"))); i++) {
                    int QUESTIONNUMBER = i;
                    params.put("ANSWERNUMBER", "9");
                    params.put("CORRECTYN", "N");
                    params.put("QUESTIONNUMBER", QUESTIONNUMBER);

                    mouigosadao.insertAnswerMouigosa(params);
                }
            }
            return subject_list.size();
        } else {
            return 0;
        }
    }*/
	
	
	
}
