package kr.pe.courage.member.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "사용자 정보가 존재하지 않습니다.")
public class MemberNotFoundException extends Exception {
	private static final long serialVersionUID = 5290679356218205642L;
	
	public MemberNotFoundException() {
		super();
	}
	
	public MemberNotFoundException(String message) {
		super(message);
	}
	
	public MemberNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public MemberNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
