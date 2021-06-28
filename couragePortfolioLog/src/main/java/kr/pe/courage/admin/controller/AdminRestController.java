package kr.pe.courage.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.admin.service.AdminService;
import kr.pe.courage.common.CreateValidateGroup;
import kr.pe.courage.common.ModifyValidateGroup;
import kr.pe.courage.setup.domain.SetupVO;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/setup",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> createSetup(@Validated(CreateValidateGroup.class) @RequestBody SetupVO setupVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		adminService.insertSetupData(setupVO);
		System.out.println("pbSno :: " + setupVO.getPbSno());
		if (setupVO.getPbSno() > 0) {
			resultMap.put("pbSno", setupVO.getPbSno());
			resultMap.put("result", "success");
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/setup",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> modifySetup(@Validated(ModifyValidateGroup.class) @RequestBody SetupVO setupVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		adminService.updateSetupData(setupVO);
		resultMap.put("pbSno", setupVO.getPbSno());
		resultMap.put("result", "success");
		
		return resultMap;
	}
}
