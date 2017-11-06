package kr.pe.courage.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "사용자 정보가 유효하지 않습니다.")
public class MemberInvalidException extends Exception {
	private static final long serialVersionUID = 5290679356218205642L;
	
	public MemberInvalidException() {
		super();
	}
	
	public MemberInvalidException(String message) {
		super(message);
	}
	
	public MemberInvalidException(Throwable cause) {
		super(cause);
	}
	
	public MemberInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}
