package web.lecture.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface SeriesService {

	List<HashMap<String, String>> seriesList(Object obj);

	int seriesListCount(Object obj);

    @Transactional(readOnly=false,rollbackFor=Exception.class)
	void seriesInsert(Object obj);

	List<HashMap<String, String>> seriesView(Object obj);

    @Transactional(readOnly=false,rollbackFor=Exception.class)
	void seriesUpdate(Object obj);

    @Transactional(readOnly=false,rollbackFor=Exception.class)
	void seriesDelete(Object obj);

	int seriesCheck(Object obj);
}
