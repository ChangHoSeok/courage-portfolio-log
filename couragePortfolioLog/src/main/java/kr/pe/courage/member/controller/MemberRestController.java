package kr.pe.courage.member.controller;

import java.util.HashMap;
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

import kr.pe.courage.exception.SessionExpiredException;
import kr.pe.courage.member.domain.MemberInvalidException;
import kr.pe.courage.member.domain.MemberNotFoundException;
import kr.pe.courage.member.domain.MemberSignInValidate;
import kr.pe.courage.member.domain.MemberVO;
import kr.pe.courage.member.service.MemberService;
import kr.pe.courage.properties.CourageProperties;

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
	
	@Autowired
	private CourageProperties courageProperties;
	
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
	public @ResponseBody Map<String, Object> auth(HttpSession session,
			@Validated(MemberSignInValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO loginMember = null;
		
		try {
			loginMember = memberService.getMemberLoginInfo(memberVO);
			
			resultMap.put("isLogin", Boolean.TRUE);
			resultMap.put("memberInfo", loginMember);
			
			session.setAttribute(courageProperties.getSession().getKeys().getObject(), loginMember);
			session.setAttribute(courageProperties.getSession().getKeys().getName(), loginMember.getName());
			session.setAttribute(courageProperties.getSession().getKeys().getEmail(), loginMember.getEmail());
			session.setAttribute(courageProperties.getSession().getKeys().getGravatarURL(), loginMember.getGravatarUrl());
		} catch (MemberInvalidException e) {
			resultMap.put("isLogin", Boolean.FALSE);
		} catch (MemberNotFoundException e) {
			resultMap.put("isLogin", Boolean.FALSE);
		}
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 정보 조회
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 1.
	 * @Method Name : getMyInfo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "me",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> getMyInfo(HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO loginMember = (MemberVO) session.getAttribute(courageProperties.getSession().getKeys().getObject());
		
		if (loginMember == null) {
			throw new SessionExpiredException();
		}
		
		resultMap.put("memberInfo", loginMember);
		
		return resultMap;
	}
}