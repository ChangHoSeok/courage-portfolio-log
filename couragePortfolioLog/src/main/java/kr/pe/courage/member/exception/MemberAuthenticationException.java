package kr.pe.courage.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "사용자 인증 실패")
public class MemberAuthenticationException extends Exception {
	private static final long serialVersionUID = 659007064035132441L;

	public MemberAuthenticationException() {
		super();
	}
	
	public MemberAuthenticationException(String message) {
		super(message);
	}
	
	public MemberAuthenticationException(Throwable cause) {
		super(cause);
	}
	
	public MemberAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
