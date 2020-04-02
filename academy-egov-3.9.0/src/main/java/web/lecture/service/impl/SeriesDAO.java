package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class SeriesDAO extends EgovComAbstractDAO {

    public List<HashMap<String, String>> seriesList(Object obj){
        return getSqlSession().selectList("series.seriesList", obj);
    }

    public int seriesListCount(Object obj){
        return getSqlSession().selectOne("series.seriesListCount", obj);
    }

    public void seriesInsert(Object obj){
        getSqlSession().insert("series.seriesInsert", obj);
    }

    public List<HashMap<String, String>> seriesView(Object obj){
        return getSqlSession().selectList("series.seriesView", obj);
    }

    public void seriesUpdate(Object obj){
        getSqlSession().update("series.seriesUpdate", obj);
    }

    public void seriesDelete(Object obj){
        getSqlSession().delete("series.seriesDelete", obj);
    }

    public int seriesCheck(Object obj){
        return getSqlSession().selectOne("series.seriesCheck", obj);
    }
}
