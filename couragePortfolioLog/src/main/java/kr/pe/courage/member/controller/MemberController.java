package kr.pe.courage.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.member.domain.MemberSignInValidate;
import kr.pe.courage.member.domain.MemberVO;

@RestController("memberController")
@RequestMapping("/member/*")
public class MemberController {
	
	@RequestMapping(value = "auth",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody MemberVO auth(HttpSession session,
			@Validated(MemberSignInValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		
		System.out.println("memberController :: auth");
		return null;
	}
}