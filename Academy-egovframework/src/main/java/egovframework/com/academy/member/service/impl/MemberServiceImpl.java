package egovframework.com.academy.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.member.service.MemberService;
import egovframework.com.academy.member.service.MemberVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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
@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {

	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

	/**
	 * @return BoxVO 사물함 상세정보
	 * @throws Exception
	 */
	@Override
	public MemberVO selectMember(MemberVO MemberVO) throws Exception {
		return memberDAO.selectMember(MemberVO);
	}
	
}
