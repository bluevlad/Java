package egovframework.com.academy.member.service;

public interface MemberService {
	
	/**
	 * 사용자 상세정보를 조회한다.
	 * @param MemberVO
	 * @return MemberVO
	 */
	public MemberVO selectMember(MemberVO MemberVO) throws Exception;

}
