package kr.pe.courage.contact.controller;

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

import kr.pe.courage.common.CreateValidateGroup;
import kr.pe.courage.contact.domain.ContactVO;
import kr.pe.courage.contact.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactRestController {
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> createContact(@Validated(CreateValidateGroup.class) @RequestBody ContactVO vo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		contactService.createContact(vo);
		
		if (vo.getSno() > 0) {
			resultMap.put("sno", vo.getSno());
			resultMap.put("result", "success");
		}
		
		return resultMap;
	}
}
