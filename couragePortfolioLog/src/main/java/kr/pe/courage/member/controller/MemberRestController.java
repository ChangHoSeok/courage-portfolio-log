package kr.pe.courage.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.courage.exception.SessionExpiredException;
import kr.pe.courage.member.domain.MemberChangePasswordValidate;
import kr.pe.courage.member.domain.MemberInvalidException;
import kr.pe.courage.member.domain.MemberNotFoundException;
import kr.pe.courage.member.domain.MemberSignInValidate;
import kr.pe.courage.member.domain.MemberUpdateValidate;
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
@RequestMapping("/member")
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
	 * @Method Name : signin
	 * @param session
	 * @param memberVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/signin",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> signIn(HttpSession session,
			@Validated(MemberSignInValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO loginMember = null;
		
		try {
			loginMember = memberService.getMemberLoginInfo(memberVO);
			
			resultMap.put("isLogin", Boolean.TRUE);
			resultMap.put("memberInfo", loginMember);
			
			setSessionAttribute(session, loginMember);
		} catch (MemberInvalidException e) {
			resultMap.put("isLogin", Boolean.FALSE);
		} catch (MemberNotFoundException e) {
			resultMap.put("isLogin", Boolean.FALSE);
		}
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원 인증해제
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 4.
	 * @Method Name : signOut
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping(value = "/signout",
			method = RequestMethod.POST)
	public void signOut(HttpSession session) throws Exception {
		session.removeAttribute(courageProperties.getSession().getKeys().getObject());
		session.removeAttribute(courageProperties.getSession().getKeys().getName());
		session.removeAttribute(courageProperties.getSession().getKeys().getEmail());
		session.removeAttribute(courageProperties.getSession().getKeys().getGravatarURL());
		session.invalidate();
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
	@RequestMapping(value = "",
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
	
	/**
	 * <pre>
	 * 1. 개요 : 회원정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2017. 11. 5.
	 * @Method Name : updateMember
	 * @param session
	 * @param memberVO
	 * @throws Exception
	 */
	@RequestMapping(value = "",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateMember(HttpSession session,
		@Validated(MemberUpdateValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		
		MemberVO loginInfo = (MemberVO) session.getAttribute(courageProperties.getSession().getKeys().getObject());
		memberVO.setSno(loginInfo.getSno());
		
		memberService.updateMember(memberVO);
		setSessionAttribute(session, memberService.getMember(memberVO));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원 비밀번호 변경
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 6.
	 * @Method Name : changePassword
	 * @param memberVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/password",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody Map<String, Object> changePassword(
			@Validated(MemberChangePasswordValidate.class) @RequestBody MemberVO memberVO) throws Exception {
		
		return null;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 세션 사용자 정보 설정
	 * </pre>
	 * 
	 * @Date	: 2017. 11. 5.
	 * @Method Name : setSessionAttribute
	 * @param session
	 * @param memberVO
	 */
	private void setSessionAttribute(HttpSession session, MemberVO memberVO) {
		session.setAttribute(courageProperties.getSession().getKeys().getObject(), memberVO);
		session.setAttribute(courageProperties.getSession().getKeys().getName(), memberVO.getName());
		session.setAttribute(courageProperties.getSession().getKeys().getEmail(), memberVO.getEmail());
		session.setAttribute(courageProperties.getSession().getKeys().getGravatarURL(), memberVO.getGravatarUrl());
	}
}