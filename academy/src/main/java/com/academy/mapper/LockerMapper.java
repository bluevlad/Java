package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.locker.service.LockerVO;

/**
 * 시험문제은행정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2021.08.00  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface LockerMapper {

    /**
     * @param LockerVO 검색조건
     * @return List 사물함 목록정보
     */
	ArrayList<JSONObject> selectLockerList(LockerVO lockerVO);

}
