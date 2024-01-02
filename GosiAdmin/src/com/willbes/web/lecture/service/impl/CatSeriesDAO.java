package com.willbes.web.lecture.service.impl;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class CatSeriesDAO extends CmmAbstractMapper {

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
