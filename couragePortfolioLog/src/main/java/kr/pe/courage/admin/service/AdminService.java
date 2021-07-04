package kr.pe.courage.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.contact.domain.ContactRepository;
import kr.pe.courage.contact.domain.ContactVO;
import kr.pe.courage.exception.DataNotFoundException;
import kr.pe.courage.setup.domain.SetupRepository;
import kr.pe.courage.setup.domain.SetupVO;
import kr.pe.courage.setup.exception.ExistsSetupException;

@Service
public class AdminService {
	@Autowired
	private SetupRepository setupRepo;
	@Autowired
	private ContactRepository contactRepo;
	
	/**
	 * <pre>
	 * 1. 개요 : 설정 정보 최초등록
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 6. 27.
	 * @Method Name : insertSetupData
	 * @param SetupVO
	 * @throws ExistsSetupException
	 */
	public void insertSetupData(SetupVO vo) throws ExistsSetupException {
		if (setupRepo.selectSetupCnt() > 0) {
			throw new ExistsSetupException();
		}
		
		setupRepo.insertSetup(vo);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 설정 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 6. 27.
	 * @Method Name : updateSetupData
	 * @param SetupVO
	 * @throws DataNotFoundException
	 */
	public void updateSetupData(SetupVO vo) throws DataNotFoundException {
		if (setupRepo.selectSetup() == null) {
			throw new DataNotFoundException();
		}
		
		setupRepo.updateSetup(vo);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : contact 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 2.
	 * @Method Name : selectContactList
	 * @param ContactVO
	 * @return List<ContactVO>
	 * @throws DataNotFoundException
	 */
	public List<ContactVO> selectContactList(ContactVO vo) {
		return contactRepo.selectContactList(vo);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : contact 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 6. 30.
	 * @Method Name : selectContact
	 * @param ContactVO
	 * @return ContactVO
	 * @throws DataNotFoundException
	 */
	public ContactVO selectContact(ContactVO vo) throws DataNotFoundException {
		ContactVO resultVO = contactRepo.selectContact(vo);
		
		if (resultVO == null) {
			throw new DataNotFoundException("정보가 존재하지 않습니다. [" + vo.getSno() + "]");
		}
		
		return resultVO;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : contact 회신 상태 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 4.
	 * @Method Name : deleteContact
	 * @param ContactVO
	 * @throws DataNotFoundException
	 */
	public void modifyContactReply(ContactVO vo) throws DataNotFoundException {
		ContactVO updateVO = contactRepo.selectContact(vo);
		
		if (updateVO == null) {
			throw new DataNotFoundException("삭제할 정보가 존재하지 않습니다. [" + vo.getSno() + "]");
		}
		
		contactRepo.updateReply(vo);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : contact 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2021. 7. 4.
	 * @Method Name : deleteContact
	 * @param ContactVO
	 * @throws DataNotFoundException
	 */
	public void deleteContact(ContactVO vo) throws DataNotFoundException {
		ContactVO deleteVO = contactRepo.selectContact(vo);
		
		if (deleteVO == null) {
			throw new DataNotFoundException("삭제할 정보가 존재하지 않습니다. [" + vo.getSno() + "]");
		}
		
		contactRepo.deleteContact(deleteVO);
	}
}
