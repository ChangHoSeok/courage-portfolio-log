package kr.pe.courage.member.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.common.link.gravatar.GravatarGenerator;
import kr.pe.courage.common.util.encrypt.SHAEncryptString;
import kr.pe.courage.member.domain.MemberRepository;
import kr.pe.courage.member.domain.MemberVO;
import kr.pe.courage.member.exception.MemberAuthenticationException;
import kr.pe.courage.member.exception.MemberInvalidException;
import kr.pe.courage.member.exception.MemberNotFoundException;

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
	
	@Autowired
	private GravatarGenerator gravatarGenerator;
	
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
		}
		
		String salt = memberRepository.selectMemberSalt();
		memberVO.setPassword(SHAEncryptString.encSHA512(memberVO.getPassword(), salt));
		
		resultVO = memberRepository.selectMemberForLogin(memberVO);
		
		// 사용자 정보가 존재하지 않음은 패스워드가 틀림을 의미 한다.
		if (resultVO == null) {
			throw new MemberNotFoundException("패스워드 인증 실패");
		}
		
		// Gravatar URL 설정
		resultVO.setGravatarUrl(gravatarGenerator.getGravatarURL(resultVO.getEmail()));
		
		return resultVO;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 조회
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 5.
	 * @Method Name : getMember
	 * @param memberVO
	 * @return
	 * @throws MemberNotFoundException
	 */
	public MemberVO getMember(MemberVO memberVO) throws MemberNotFoundException {
		MemberVO resultVO = memberRepository.selectMember(memberVO);
		
		// 사용자 정보가 존재하지 않음은 패스워드가 틀림을 의미 한다.
		if (resultVO == null) {
			throw new MemberNotFoundException("사용자 정보 미존재 [sno : " + memberVO.getSno() + "]");
		}
		
		// Gravatar URL 설정
		resultVO.setGravatarUrl(gravatarGenerator.getGravatarURL(resultVO.getEmail()));
		
		return resultVO;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 5.
	 * @Method Name : updateMember
	 * @param memberVO
	 */
	public void updateMember(MemberVO memberVO) {
		memberRepository.updateMember(memberVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 패스워드 변경
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 6.
	 * @Method Name : updateMemberPassword
	 * @param memberVO
	 * @throws MemberAuthenticationException
	 */
	public void updateMemberPassword(MemberVO memberVO) throws MemberAuthenticationException {
		System.out.println(memberVO.getPassword());
		System.out.println(SHAEncryptString.encSHA512("test1234!", "5e57d5ce-d43f-4459-b7dd-8a00af373c1e"));
		
		// 현재 비밀번호 검증
		String salt = memberRepository.selectMemberSalt();
		memberVO.setPassword(SHAEncryptString.encSHA512(memberVO.getPassword(), salt));
		
		if (memberRepository.selectMemberPassword(memberVO) <= 0) {
			throw new MemberAuthenticationException();
		}
		
		// 신규 패스워드 생성
		salt = UUID.randomUUID().toString();
		String password = SHAEncryptString.encSHA512(memberVO.getNewPassword(), salt);
		
		memberVO.setSalt(salt);
		memberVO.setPassword(password);
		memberRepository.updateMemberPassword(memberVO);
	}
}
