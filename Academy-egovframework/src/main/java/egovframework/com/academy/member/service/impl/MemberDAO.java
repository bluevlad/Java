package egovframework.com.academy.member.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.member.service.MemberVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 회원정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.10.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2023.10.00  		rainend          최초 생성
 * </pre>
 */
@Repository("memberDAO")
public class MemberDAO extends EgovComAbstractDAO{

	public MemberVO selectMember(MemberVO MemberVO) {
		return getSqlSession().selectOne("member.selectMember", MemberVO);
	}
	
}
