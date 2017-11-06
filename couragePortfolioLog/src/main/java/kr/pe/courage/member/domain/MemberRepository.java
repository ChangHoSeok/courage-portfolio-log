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
	 * 1. 개요 : 사용자 정보 조회
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 5.
	 * @Method Name : selectMember
	 * @param memberVO
	 * @return
	 */
	public MemberVO selectMember(MemberVO memberVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 패스워드 암호화를 위한 SALT 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2017. 10. 29.
	 * @Method Name : selectMemberSalt
	 * @return
	 */
	public String selectMemberSalt();
	
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
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 패스워드 검증을 위한 조회
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 6.
	 * @Method Name : selectMemberPassword
	 * @param memberVO
	 * @return
	 */
	public int selectMemberPassword(MemberVO memberVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 5.
	 * @Method Name : updateMember
	 * @param memberVO
	 */
	public void updateMember(MemberVO memberVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 패스워드 변경
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 6.
	 * @Method Name : updateMemberPassword
	 * @param memberVO
	 */
	public void updateMemberPassword(MemberVO memberVO);
}
