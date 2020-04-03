package web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.gosi.service.GosiService;
import web.gosi.service.impl.GosiDAO;

@Service(value="gosiservice")
public class GosiServiceImpl implements GosiService{
    @Autowired 
    private GosiDAO gosidao;
    
    @Override
	public List<HashMap<String, String>> GosiList(HashMap<String, String> params){
		return gosidao.GosiList(params);
	}	
    
    @Override
	public HashMap<String, String> chkGosiInfo(HashMap<String, String> params){
		return gosidao.chkGosiInfo(params);
	}
	
	@Override
    public List<HashMap<String, String>> sample_List(HashMap<String, String> params){
        return gosidao.sample_List(params);
    }       
    
    @Override
    public int sample_ListCount(HashMap<String, String> params){
        return gosidao.sample_ListCount(params);
    }
    
    @Override
    public List<HashMap<String, Object>> Event1_List(HashMap<String, String> params){
        return gosidao.Event1_List(params);
    }       
    
    @Override
    public int Event1_ListCount(HashMap<String, String> params){
        return gosidao.Event1_ListCount(params);
    }
    
    @Override
    public List<HashMap<String, String>> Event2_List(HashMap<String, String> params){
        return gosidao.Event2_List(params);
    }       
    
    @Override
    public int Event2_ListCount(HashMap<String, String> params){
        return gosidao.Event2_ListCount(params);
    }
    
    @Override
    public List<HashMap<String, Object>> Event3_List(HashMap<String, String> params){
        return gosidao.Event3_List(params);
    } 
    
    @Override
    public int Event3_ListCount(HashMap<String, String> params){
        return gosidao.Event3_ListCount(params);
    }
    
    @Override
    public void InsertSampleUser(HashMap<String, String> params){
        gosidao.InsertSampleUser(params);
    }
    
    @Override
    public List<HashMap<String, String>> SampleIdView(HashMap<String, String> params){
        return gosidao.SampleIdView(params);
    }
    
    @Override
    public void SampleIdUpdate(HashMap<String, String> params){
        gosidao.SampleIdUpdate(params);
    }
    
    @Override
    public void SampleIdDelete(HashMap<String, String> params){
        gosidao.SampleIdDelete(params);
    }
    
    @Override
    public List<HashMap<String, String>> getGosiAreaMst(HashMap<String, String> params){
        return gosidao.getGosiAreaMst(params);
    }       
    
    @Override
    public void updateGosiAreaMst(HashMap<String, String> params){
        gosidao.updateGosiAreaMst(params);
    }
    
    @Override
    public List<HashMap<String, String>> getVodSubject(HashMap<String, String> params){
        return gosidao.getVodSubject(params);
    }       
    
    @Override
    public void updateGosiVod(HashMap<String, String> params){
        gosidao.updateGosiVod(params);
    }
    
    @Override
    public List<HashMap<String, String>> getGosiStatMst(HashMap<String, String> params){
        return gosidao.getGosiStatMst(params);
    }       
    
    @Override
    public void makeGosiResult(HashMap<String, String> params){
        gosidao.makeGosiResult(params);
    }
    
    @Override
    public void makeGosiStatMst(HashMap<String, String> params){
        gosidao.makeGosiStatMst(params);
    }
    
    @Override
    public void makeGosiAdjustMst(HashMap<String, String> params){
        gosidao.makeGosiAdjustMst(params);
    }
    
    @Override
    public void makeGosiRank(HashMap<String, String> params){
        gosidao.makeGosiRank(params);
    }
    
    @Override
    public void GosiUpdate(HashMap<String, String> params){
        gosidao.GosiUpdate(params);
    }

	@Override
	public List<HashMap<String, String>> getTestArea(
			HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return gosidao.getTestArea(params);
	}

	@Override
	public void updateGosiCod(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		gosidao.updateGosiCod(params);
	}

	@Override
	public void makeGosiPer(HashMap<String, String> params) {
		gosidao.makeGosiPer(params);
	}

	@Override
	public List<HashMap<String, String>> getGosiScorePer(HashMap<String, String> params) {
		return gosidao.getGosiScorePer(params);
	}

	@Override
	public List<HashMap<String, String>> getSubjectScorePer(HashMap<String, String> params) {
		return gosidao.getSubjectScorePer(params);
	}

	@Override
	public void updateGosiScorePer(HashMap<String, String> params) {
		gosidao.updateGosiScorePer(params);
	}

	@Override
	public void updateSubjectScorePer(HashMap<String, String> params) {
		gosidao.updateSubjectScorePer(params);
	}
}
