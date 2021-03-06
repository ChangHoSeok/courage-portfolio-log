package kr.pe.courage.contact.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactRepository {
	/**
	 * <pre>
	 * 1. 개요 : Contact 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 6. 29.
	 * @Method Name : insertContact
	 * @param ContactVO
	 */
	public void insertContact(ContactVO vo);
	
	/**
	 * <pre>
	 * 1. 개요 : Contact 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 6. 30.
	 * @Method Name : selectContact
	 * @param ContactVO
	 * @return ContactVO
	 */
	public ContactVO selectContact(ContactVO vo);
	
	/**
	 * <pre>
	 * 1. 개요 : Contact 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 5.
	 * @Method Name : selectContactListCnt
	 * @param ContactVO
	 * @return int
	 */
	public int selectContactListCnt(ContactVO vo);
	
	/**
	 * <pre>
	 * 1. 개요 : Contact 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 2.
	 * @Method Name : selectContactList
	 * @param ContactVO
	 * @return List<ContactVO>
	 */
	public List<ContactVO> selectContactList(ContactVO vo);
	
	/**
	 * <pre>
	 * 1. 개요 : Contact 회신 상태 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 4.
	 * @Method Name : deleteContact
	 * @param ContactVO
	 */
	public void updateReply(ContactVO vo);
	
	/**
	 * <pre>
	 * 1. 개요 : Contact 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 4.
	 * @Method Name : deleteContact
	 * @param ContactVO
	 */
	public void deleteContact(ContactVO vo);
}
