package kr.pe.courage.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.member.domain.MemberInvalidException;
import kr.pe.courage.member.domain.MemberNotFoundException;
import kr.pe.courage.member.domain.MemberRepository;
import kr.pe.courage.member.domain.MemberVO;

/**
 * <pre>
 * kr.pe.courage.member.service
 * MemberService.java
 * </pre>
 *
 * @Author	: ChangHo.Seok
 * @Date	: 2017. 10. 27.
 * @Version	: 1.0
 * @see
 * <pre>
 * << 개정이력 >>
 * 1. 일자 : 2017. 10. 27., 수정자 : ChangHo.Seok, 내용 : 최초등록
 * </pre>
 */
@Service("memberService")
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 로그인 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 10. 27.
	 * @Method Name : getMemberLoginInfo
	 * @param memberVO
	 * @return
	 * @throws MemberInvalidException
	 * @throws MemberNotFoundException
	 */
	public MemberVO getMemberLoginInfo(MemberVO memberVO) throws MemberInvalidException, MemberNotFoundException {
		MemberVO resultVO = null;
		
		/**
		 *  사용자 정보는 반드시 단일 정보여야 한다.
		 *  사용자 정보가 없거나 복수개일 경우를 미리 확인
		 */
		int memberCnt = memberRepository.selectMemberCount();
		if (memberCnt != 1) {
			throw new MemberInvalidException("사용자 정보 갯수 : " + memberCnt);
		} else {
			resultVO = memberRepository.selectMemberForLogin(memberVO);
			
			// 사용자 정보가 존재하지 않음은 패스워드가 틀림을 의미 한다.
			if (resultVO == null) {
				throw new MemberNotFoundException("패스워드가 맞지않음");
			}
		}
		
		return resultVO;
	}
}
