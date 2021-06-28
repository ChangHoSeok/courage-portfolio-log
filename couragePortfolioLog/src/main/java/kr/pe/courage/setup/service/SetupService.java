package kr.pe.courage.setup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.exception.DataNotFoundException;
import kr.pe.courage.setup.domain.SetupRepository;
import kr.pe.courage.setup.domain.SetupVO;

/**
 * <pre>
 * kr.pe.courage.setup.service
 * SetupService.java
 * </pre>
 *
 * @Author	: ChangHo.Seok
 * @Date	: 2017. 11. 7.
 * @Version	: 1.0
 * @see
 * <pre>
 * PortfoLog 설정 Service
 * 
 * << 개정이력 >>
 * 1. 일자 : 2017. 11. 7., 수정자 : ChangHo.Seok, 내용 : 최초등록
 * </pre>
 */
@Service("setupService")
public class SetupService {
	@Autowired
	private SetupRepository setupRepository;
	
	/**
	 * <pre>
	 * 1. 개요 : 설정정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 11. 7.
	 * @Method Name : getSetup
	 * @return
	 * @throws DataNotFoundException 
	 */
	public SetupVO retrieveSetup() throws DataNotFoundException {
		if (setupRepository.selectSetupCnt() <= 0) {
			throw new DataNotFoundException("설정 정보가 존재하지 않습니다.");
		}
		
		return setupRepository.selectSetup();
	}
}
