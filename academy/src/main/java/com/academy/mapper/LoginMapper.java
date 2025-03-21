package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.login.service.MemberVO;

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
public interface LoginMapper {

	public JSONObject getUser(MemberVO memberVO);

    public void insertUser(MemberVO memberVO);
    public void updateUser(MemberVO memberVO);

	public JSONObject searchId(MemberVO memberVO);
	public JSONObject searchPassword(MemberVO memberVO);

    public void updatePassword(MemberVO memberVO);
    public void updateToken(MemberVO memberVO);

}
