package kr.pe.courage.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.exception.DataNotFoundException;
import kr.pe.courage.setup.domain.SetupRepository;
import kr.pe.courage.setup.domain.SetupVO;
import kr.pe.courage.setup.exception.ExistsSetupException;

@Service
public class AdminService {
	@Autowired
	private SetupRepository setupRepo;
	
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
}
