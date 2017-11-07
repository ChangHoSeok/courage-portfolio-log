package kr.pe.courage.setup.domain;

import org.apache.ibatis.annotations.Mapper;

/**
 * <pre>
 * kr.pe.courage.setup.domain
 * SetupRepository.java
 * </pre>
 *
 * @Author	: ChangHo.Seok
 * @Date	: 2017. 11. 7.
 * @Version	: 1.0
 * @see
 * <pre>
 * PortfoLog 설정 Repository
 * 
 * << 개정이력 >>
 * 1. 일자 : 2017. 11. 7., 수정자 : ChangHo.Seok, 내용 : 초초등록
 * </pre>
 */
@Mapper
public interface SetupRepository {
	/**
	 * <pre>
	 * 1. 개요 : 설정정보 조회 (마지막으로 등록/수정된 정보만 조회)
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 11. 7.
	 * @Method Name : selectSetup
	 * @return
	 */
	public SetupVO selectSetup();
	
	/**
	 * <pre>
	 * 1. 개요 : 설정정보의 갯수 조회
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 7.
	 * @Method Name : selectSetupCnt
	 * @return
	 */
	public int selectSetupCnt();
}
