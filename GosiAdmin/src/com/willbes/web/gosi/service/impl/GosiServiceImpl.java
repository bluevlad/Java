package com.willbes.web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.gosi.service.GosiService;
import com.willbes.web.gosi.service.impl.GosiDAO;

@Service(value="gosiservice")
public class GosiServiceImpl implements GosiService{
    @Autowired 
    private GosiDAO gosidao;
    
    @Override
	public List<HashMap<String, String>> GosiList(HashMap<String, String> params){
		return gosidao.GosiList(params);
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
    public void makeGosiStandard(HashMap<String, String> params){
        gosidao.makeGosiStandard(params);
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
	public List<HashMap<String, String>> Event_List(HashMap<String, String> params) {
		return gosidao.Event_List(params);
	}

	@Override
	public int Event_ListCount(HashMap<String, String> params) {
		return gosidao.Event_ListCount(params);
	}
}
