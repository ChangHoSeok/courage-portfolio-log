package kr.pe.courage.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.member.domain.MemberInvalidException;
import kr.pe.courage.member.domain.MemberNotFoundException;
import kr.pe.courage.member.domain.MemberSignInValidate;
import kr.pe.courage.member.domain.MemberVO;
import kr.pe.courage.member.service.MemberService;

/**
 * <pre>
 * kr.pe.courage.member.controller
 * MemberRestController.java
 * </pre>
 *
 * @Date	: 2017. 10. 27.
 * @Version	: 1.0
 * @see
 * <pre>
 * 회원관련 RestController
 * </pre>
 */
@RestController("memberController")
@RequestMapping("/member/*")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * <pre>
	 * 1. 개요 : 회원인증
	 * </pre>
	 * 
	 * @Date	: 2017. 10. 27.
	 * @Method Name : auth
	 * @param session
	 * @param memberVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "auth",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody MemberVO auth(HttpSession session,
			@Validated(MemberSignInValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		Map<String, Object> resultMap = null;
		MemberVO loginMember = null;
		
		try {
			loginMember = memberService.getMemberLoginInfo(memberVO);
		} catch (MemberInvalidException e) {
			
		} catch (MemberNotFoundException e) {
			
		}
		
		return loginMember;
	}
}