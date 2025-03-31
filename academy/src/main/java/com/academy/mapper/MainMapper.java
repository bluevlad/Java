package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.main.service.MenuVO;

/**
 * 로그인 관련 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.02.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.02.10  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface MainMapper {

	public ArrayList<JSONObject> selectRouterList(MenuVO menuVO);

	public ArrayList<JSONObject> selectLeftMenuList(MenuVO menuVO);

}
