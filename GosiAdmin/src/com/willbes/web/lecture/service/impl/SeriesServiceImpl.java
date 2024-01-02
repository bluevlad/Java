package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lecture.service.SeriesService;

@Service(value="seriesService")
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SeriesDAO seriesDao;

    @Autowired
    private CatSeriesDAO catSeriesDao;

    @Override
    public List<HashMap<String, String>> seriesList(Object obj){
        return seriesDao.seriesList(obj);
    }

    @Override
    public int seriesListCount(Object obj){
        return seriesDao.seriesListCount(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void seriesInsert(Object obj){
        seriesDao.seriesInsert(obj);

        String[] CAT_CDs = (String[])((HashMap<String, Object>)obj).get("CAT_CDs");
        if(null != CAT_CDs && CAT_CDs.length > 0) {
            for(int i=0; i<CAT_CDs.length; i++) {
                ((HashMap<String, Object>)obj).put("CAT_CD", CAT_CDs[i]);
                ((HashMap<String, Object>)obj).put("ORDR", (i+1));
                catSeriesDao.catSeriesInsert(obj);
            }
        }
    }

    @Override
    public List<HashMap<String, String>> seriesView(Object obj){
        return seriesDao.seriesView(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void seriesUpdate(Object obj){
        seriesDao.seriesUpdate(obj);

        catSeriesDao.catSeriesDeleteWthSrsCd(obj);

        String[] CAT_CDs = (String[])((HashMap<String, Object>)obj).get("CAT_CDs");
        if(null != CAT_CDs && CAT_CDs.length > 0) {
            for(int i=0; i<CAT_CDs.length; i++) {
                ((HashMap<String, Object>)obj).put("CAT_CD", CAT_CDs[i]);
                ((HashMap<String, Object>)obj).put("ORDR", (i+1));
                catSeriesDao.catSeriesInsert(obj);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void seriesDelete(Object obj){
        String[] DEL_ARR = (String[])((HashMap<String, Object>)obj).get("SRS_CDs");
        if(DEL_ARR != null) {
            for(int i=0; i<DEL_ARR.length; i++) {
                ((HashMap<String, Object>)obj).put("SRS_CD", DEL_ARR[i]);
                seriesDao.seriesDelete(obj);
                catSeriesDao.catSeriesDeleteWthSrsCd(obj);
            }
        }
    }

    @Override
    public int seriesCheck(Object obj){
        return seriesDao.seriesCheck(obj);
    }

}
