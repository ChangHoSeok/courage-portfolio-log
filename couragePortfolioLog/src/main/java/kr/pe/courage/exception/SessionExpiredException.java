package kr.pe.courage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "세션만료")
public class SessionExpiredException extends Exception {
	private static final long serialVersionUID = 8129038928397216693L;

	public SessionExpiredException() {
		super();
	}
	
	public SessionExpiredException(String message) {
		super(message);
	}
	
	public SessionExpiredException(Throwable cause) {
		super(cause);
	}
	
	public SessionExpiredException(String message, Throwable cause) {
		super(message, cause);
	}
}
