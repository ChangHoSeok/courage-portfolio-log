package kr.pe.courage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "데이터가 존재하지 않습니다.")
public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 543952892804407860L;

	public DataNotFoundException() {
		super();
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	public DataNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
