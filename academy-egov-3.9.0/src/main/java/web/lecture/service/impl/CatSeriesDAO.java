package web.lecture.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class CatSeriesDAO extends EgovComAbstractDAO {

    public void catSeriesInsert(Object obj){
        getSqlSession().insert("catSeries.catSeriesInsert", obj);
    }

    public void catSeriesDeleteWthCatCd(Object obj){
        getSqlSession().delete("catSeries.catSeriesDeleteWthCatCd", obj);
    }

    public void catSeriesDeleteWthSrsCd(Object obj){
        getSqlSession().delete("catSeries.catSeriesDeleteWthSrsCd", obj);
    }
}
