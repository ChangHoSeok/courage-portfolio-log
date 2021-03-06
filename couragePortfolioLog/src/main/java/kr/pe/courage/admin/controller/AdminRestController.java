package kr.pe.courage.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.admin.service.AdminService;
import kr.pe.courage.common.CreateValidateGroup;
import kr.pe.courage.common.DeleteValidateGroup;
import kr.pe.courage.common.ModifyValidateGroup;
import kr.pe.courage.common.PaginationVO;
import kr.pe.courage.contact.domain.ContactReplyModifyValidateGroup;
import kr.pe.courage.contact.domain.ContactVO;
import kr.pe.courage.properties.CourageProperties;
import kr.pe.courage.setup.domain.SetupVO;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	@Autowired
	private CourageProperties courageProp;
	
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
	
	@RequestMapping(value = "/contact",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> retrieveContactList(ContactVO contactVO,
			@RequestParam(name = "size", required = false, defaultValue = "0") int size) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		contactVO.setPageSize(size <= 0 ? Integer.parseInt(courageProp.getProp("board.contact.page-size")) : size);
		resultMap.put("totalCnt", adminService.selectContactListCnt(contactVO));
		resultMap.put("resultList", adminService.selectContactList(contactVO));
		
		return resultMap;
	}
	
	@RequestMapping(value = "/contact/{sno}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> retrieveContactDetail(ContactVO contactVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("contactVO", adminService.selectContact(contactVO));
		
		return resultMap;
	}
	
	@RequestMapping(value = "/contact/{sno}/reply",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> modifyContactReply(@Validated(ContactReplyModifyValidateGroup.class) @RequestBody ContactVO contactVO, @PathVariable("sno") int sno) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		contactVO.setSno(sno);
		adminService.modifyContactReply(contactVO);
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	@RequestMapping(value = "/contact/{sno}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> deleteSetup(@Validated(DeleteValidateGroup.class) ContactVO contactVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		adminService.deleteContact(contactVO);
		resultMap.put("result", "success");
		
		return resultMap;
	}
}
