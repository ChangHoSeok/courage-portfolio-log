package kr.pe.courage.setup.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.setup.service.SetupService;

/**
 * <pre>
 * kr.pe.courage.setup.controller
 * SetupRestController.java
 * </pre>
 *
 * @Author	: ChangHo.Seok
 * @Date	: 2017. 11. 7.
 * @Version	: 1.0
 * @see
 * <pre>
 * PortfoLog REST Controller
 * 
 * << 개정이력 >>
 * 1. 일자 : 2017. 11. 7., 수정자 : ChangHo.Seok, 내용 : 최초등록
 * </pre>
 */
@RestController("setupRestController")
@RequestMapping("/setup")
public class SetupRestController {
	@Autowired
	private SetupService setupService;
	
	/**
	 * <pre>
	 * 1. 개요 : 설정정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo.Seok
	 * @Date	: 2017. 11. 7.
	 * @Method Name : getSetup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> getSetup() throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("setupInfo", setupService.retrieveSetup());
		
		return resultMap;
	}
}
