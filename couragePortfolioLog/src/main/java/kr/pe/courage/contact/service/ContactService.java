package kr.pe.courage.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.contact.domain.ContactRepository;
import kr.pe.courage.contact.domain.ContactVO;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepo;
	
	public void createContact(ContactVO vo) {
		contactRepo.insertContact(vo);
	}
}
