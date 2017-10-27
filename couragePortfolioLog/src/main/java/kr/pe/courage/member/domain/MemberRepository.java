package kr.pe.courage.member.domain;

import org.apache.ibatis.annotations.Mapper;

/**
 * <pre>
 * kr.pe.courage.member.domain
 * MemberRepository.java
 * </pre>
 *
 * @Author	: ChangHo.Seok
 * @Date	: 2017. 10. 27.
 * @Version	: 
 * @see
 * <pre>
 * << 개정이력 >>
 * 1. 일자 : 2017. 10. 27., 수정자 : ChangHo.Seok, 내용 : 초초등록
 * </pre>
 */
@Mapper
public interface MemberRepository {
	/**
	 * <pre>
	 * 1. 개요 : 로그인을 위한 사용자 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 10. 27.
	 * @Method Name : selectMemberForLogin
	 * @param memberVO
	 * @return
	 */
	public MemberVO selectMemberForLogin(MemberVO memberVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 10. 27.
	 * @Method Name : selectMemberCount
	 * @return
	 */
	public int selectMemberCount();
}
