package kr.pe.courage.contact.domain;

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
}
